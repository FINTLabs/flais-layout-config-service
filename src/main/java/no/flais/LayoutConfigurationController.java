package no.flais;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/configuration")
public class LayoutConfigurationController {

    private final LayoutConfigurationRepository repository;

    public LayoutConfigurationController(LayoutConfigurationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<LayoutConfiguration> getLayoutConfiguration() {
        List<LayoutConfiguration> all = repository.findAll();
        if (all.size() > 0) {
            return ResponseEntity.ok(all.get(0));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LayoutConfiguration> newLayoutConfiguration(@RequestBody LayoutConfiguration layoutConfiguration) {
        List<LayoutConfiguration> all = repository.findAll();

        if (all.size() == 0) {
            applyLayoutConfigurationToMainPodlets(layoutConfiguration);
            applyBasePathToMainPodlets(layoutConfiguration);
            return ResponseEntity.ok(repository.save(layoutConfiguration));
        }

        if (all.size() == 1) {
            return ResponseEntity.ok(all.get(0));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<LayoutConfiguration> updateLayoutConfiguration(@RequestBody LayoutConfiguration layoutConfiguration) {
        List<LayoutConfiguration> all = repository.findAll();

        if (all.size() == 1) {
            applyLayoutConfigurationToMainPodlets(layoutConfiguration);
            applyBasePathToMainPodlets(layoutConfiguration);
            return ResponseEntity.ok(repository.save(layoutConfiguration));

        }
        return ResponseEntity.badRequest().build();

    }

    private void applyBasePathToMainPodlets(LayoutConfiguration layoutConfiguration) {
        layoutConfiguration
                .getAppBarMenuMainLayout()
                .getMain()
                .forEach(mainPodlet -> {
                    if (!mainPodlet.getPath().startsWith(layoutConfiguration.getBasePath())) {
                        mainPodlet.setPath(UriComponentsBuilder.fromPath(layoutConfiguration.getBasePath())
                                .path(mainPodlet.getPath()).build().getPath());
                    }
                });
    }

    private void applyLayoutConfigurationToMainPodlets(LayoutConfiguration layoutConfiguration) {
        layoutConfiguration
                .getAppBarMenuMainLayout()
                .getMain()
                .forEach(mainPodlet -> mainPodlet.setAppBarMenuMainLayout(layoutConfiguration.getAppBarMenuMainLayout()));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearLayoutConfiguration() {
        repository.deleteAll();
        return ResponseEntity.accepted().build();
    }
}

package no.flais;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/layout/menu")
public class MenuController {

    private final LayoutConfigurationRepository repository;

    public MenuController(LayoutConfigurationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getLayoutConfiguration() {
        List<LayoutConfiguration> all = repository.findAll();
        if (all.size() > 0) {
            return ResponseEntity.ok(
                    all.get(0)
                            .getAppBarMenuMainLayout()
                            .getMain()
                            .stream()
                            .map(MenuItem::fromMainPodlet)
                            .collect(Collectors.toList())
            );
        }
        return ResponseEntity.notFound().build();
    }
}

package no.flais;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class MenuItem {
    private String title;
    private String path;
    private String icon;

    public static MenuItem fromMainPodlet(MainPodlet podlet) {
        return MenuItem.builder()
                .icon(podlet.getIcon())
                .path(podlet.getPath())
                .title(podlet.getTitle())
                .build();
    }
}

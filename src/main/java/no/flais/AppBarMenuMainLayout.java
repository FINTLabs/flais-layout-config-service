package no.flais;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "appbar_menu_main_layout")
public class AppBarMenuMainLayout {
    @Id
    //@JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "app_bar_podlet_id")
    private AppBarPodlet appbar;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_podlet_id")
    private MenuPodlet menu;

    @OneToMany(mappedBy = "appBarMenuMainLayout", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MainPodlet> main = new LinkedHashSet<>();

}

package no.flais;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "layout_configuration")
public class LayoutConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    //@JsonIgnore
    private Long id;


    private String name;

    private String basePath;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "appbar_menu_main_layout_id")
    private AppBarMenuMainLayout appBarMenuMainLayout;


}

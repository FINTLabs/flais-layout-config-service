package no.flais;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "main_podlet")
public class MainPodlet extends Podlet {

    private String title;
    private String icon;
    @Column(nullable = false)
    private int menuOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appbar_menu_main_layout_id")
    private AppBarMenuMainLayout appBarMenuMainLayout;
}

package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "iis_theme")
public class ThemeEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "theme", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CampaignEntity> campaigns;

}

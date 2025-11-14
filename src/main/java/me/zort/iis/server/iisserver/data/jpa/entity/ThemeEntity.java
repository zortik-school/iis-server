package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "iis_theme")
public class ThemeEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "theme", orphanRemoval = true)
    private List<CampaignEntity> campaigns;

}

package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "iis_campaign")
public class CampaignEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "campaign", orphanRemoval = true)
    private List<StepEntity> steps;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", nullable = false)
    private ThemeEntity theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private UserEntity assignedUser;

}

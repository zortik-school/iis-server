package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "iis_step")
public class StepEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "step", orphanRemoval = true)
    private List<ActivityEntity> activities;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private UserEntity assignedUser;

}

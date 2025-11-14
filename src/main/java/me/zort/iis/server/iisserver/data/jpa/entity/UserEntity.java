package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "iis_user")
public class UserEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "assignedUser")
    private List<CampaignEntity> assignedCampaigns;

    @OneToMany(mappedBy = "assignedUser")
    private List<StepEntity> assignedSteps;

    @OneToMany(mappedBy = "assignedUser")
    private List<ActivityEntity> assignedActivities;

}

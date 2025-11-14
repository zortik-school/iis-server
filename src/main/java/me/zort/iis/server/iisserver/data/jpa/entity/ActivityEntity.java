package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;

@Entity(name = "iis_activity")
public class ActivityEntity {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "finish_note")
    private String finishNote;

    @Column(name = "finish_date")
    private Long finishDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "step_id", nullable = false)
    private StepEntity step;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private UserEntity assignedUser;

}

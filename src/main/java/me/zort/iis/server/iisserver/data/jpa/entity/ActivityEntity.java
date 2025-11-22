package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import me.zort.iis.server.iisserver.data.jpa.JpaEntity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "iis_activity")
public class ActivityEntity implements JpaEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date", nullable = false)
    private long startDate;

    @Column(name = "end_date", nullable = false)
    private long endDate;

    @Column(name = "finish_note")
    private String note;

    @Column(name = "finish_date")
    private Long noteDate;

    @Enumerated(EnumType.STRING)
    private ActivityState state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "step_id", nullable = false)
    private StepEntity step;

    @OneToMany(mappedBy = "activity")
    @ToString.Exclude
    private List<ActivityMembershipEntity> memberships;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ActivityEntity that = (ActivityEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

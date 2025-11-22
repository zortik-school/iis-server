package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import me.zort.iis.server.iisserver.data.jpa.JpaEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "iis_user")
public class UserEntity implements JpaEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Role role;


    @OneToMany(mappedBy = "assignedUser")
    @ToString.Exclude
    private List<CampaignEntity> assignedCampaigns;

    @OneToMany(mappedBy = "assignedUser")
    @ToString.Exclude
    private List<StepEntity> assignedSteps;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ActivityMembershipEntity> activityMemberships;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserEntity that = (UserEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

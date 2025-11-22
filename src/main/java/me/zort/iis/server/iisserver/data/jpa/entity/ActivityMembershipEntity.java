package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;
import me.zort.iis.server.iisserver.data.jpa.JpaEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "iis_activity_memberships")
public class ActivityMembershipEntity implements JpaEntity<ActivityMembershipId> {
    @EmbeddedId
    private ActivityMembershipId id;

    @ManyToOne
    @MapsId("activityId")
    private ActivityEntity activity;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ActivityMembershipEntity that = (ActivityMembershipEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}

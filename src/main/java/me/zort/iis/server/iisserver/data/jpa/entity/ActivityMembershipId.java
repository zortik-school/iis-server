package me.zort.iis.server.iisserver.data.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ActivityMembershipId {
    private Long activityId;
    private Long userId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ActivityMembershipId that = (ActivityMembershipId) o;
        return activityId != null && Objects.equals(activityId, that.activityId)
                && userId != null && Objects.equals(userId, that.userId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(activityId, userId);
    }
}

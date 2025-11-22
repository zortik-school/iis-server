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
@Entity(name = "iis_campaign_memberships")
public class CampaignMembershipEntity implements JpaEntity<CampaignMembershipId> {
    @EmbeddedId
    private CampaignMembershipId id;

    @ManyToOne
    @MapsId("campaignId")
    private CampaignEntity campaign;

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
        CampaignMembershipEntity that = (CampaignMembershipEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}

package me.zort.iis.server.iisserver.mapper.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipId;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityMembership;
import me.zort.iis.server.iisserver.domain.campaign.impl.ActivityMembershipImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaActivityMembershipMapper implements JpaMapper<ActivityMembership, ActivityMembershipEntity> {
    private final EntityManager entityManager;

    @Override
    public ActivityMembership toDomain(ActivityMembershipEntity entity) {
        return new ActivityMembershipImpl(entity.getActivity().getId(), entity.getUser().getId());
    }

    @Override
    public ActivityMembershipEntity toEntity(ActivityMembership domain) {
        ActivityMembershipEntity entity = new ActivityMembershipEntity();
        entity.setId(new ActivityMembershipId(domain.getActivityId(), domain.getUserId()));
        entity.setActivity(entityManager.getReference(ActivityEntity.class, domain.getActivityId()));
        entity.setUser(entityManager.getReference(UserEntity.class, domain.getUserId()));

        return entity;
    }
}

package me.zort.iis.server.iisserver.mapper.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.StepEntity;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.impl.ActivityImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaActivityMapper implements JpaMapper<Activity, ActivityEntity> {
    private final EntityManager entityManager;

    @Override
    public Activity toDomain(ActivityEntity entity) {
        return new ActivityImpl(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStep().getId(), entity.getStartDate(), entity.getEndDate(), entity.getState());
    }

    @Override
    public ActivityEntity toEntity(Activity domain) {
        ActivityEntity entity = new ActivityEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setStep(entityManager.getReference(StepEntity.class, domain.getStepId()));
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setState(domain.getState());

        return entity;
    }
}

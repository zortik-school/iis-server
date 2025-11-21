package me.zort.iis.server.iisserver.mapper.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.StepEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.domain.campaign.StepImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCampaignStepMapper implements JpaMapper<Step, StepEntity> {
    private final EntityManager entityManager;

    @Override
    public Step toDomain(StepEntity entity) {
        return new StepImpl(
                entity.getId(),
                entity.getName(),
                entity.getCampaign().getId(),
                entity.isActive(), entity.getAssignedUser() != null ? entity.getAssignedUser().getId() : null);
    }

    @Override
    public StepEntity toEntity(Step domain) {
        StepEntity entity = new StepEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setActive(domain.isActive());
        entity.setCampaign(entityManager.getReference(CampaignEntity.class, domain.getCampaignId()));
        if (domain.getAssignedUserId() != null) {
            entity.setAssignedUser(entityManager.getReference(UserEntity.class, domain.getAssignedUserId()));
        }

        return entity;
    }
}

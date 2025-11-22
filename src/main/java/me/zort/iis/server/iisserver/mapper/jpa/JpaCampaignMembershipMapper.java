package me.zort.iis.server.iisserver.mapper.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipId;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembership;
import me.zort.iis.server.iisserver.domain.campaign.impl.CampaignMembershipImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCampaignMembershipMapper implements JpaMapper<CampaignMembership, CampaignMembershipEntity> {
    private final EntityManager entityManager;

    @Override
    public CampaignMembership toDomain(CampaignMembershipEntity entity) {
        return new CampaignMembershipImpl(entity.getCampaign().getId(), entity.getUser().getId());
    }

    @Override
    public CampaignMembershipEntity toEntity(CampaignMembership domain) {
        CampaignMembershipEntity entity = new CampaignMembershipEntity();
        entity.setId(new CampaignMembershipId(domain.getCampaignId(), domain.getUserId()));
        entity.setCampaign(entityManager.getReference(CampaignEntity.class, domain.getCampaignId()));
        entity.setUser(entityManager.getReference(UserEntity.class, domain.getUserId()));

        return entity;
    }
}

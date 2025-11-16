package me.zort.iis.server.iisserver.mapper.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ThemeEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.CampaignImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCampaignMapper implements JpaMapper<Campaign, CampaignEntity> {
    private final EntityManager entityManager;

    @Override
    public Campaign toDomain(CampaignEntity entity) {
        return new CampaignImpl(
                entity.getId(),
                entity.getName(),
                entity.getAssignedUser() != null ? entity.getAssignedUser().getId() : null, entity.getTheme().getId());
    }

    @Override
    public CampaignEntity toEntity(Campaign domain) {
        ThemeEntity themeEntityRef = entityManager.getReference(ThemeEntity.class, domain.getThemeId());
        UserEntity userEntityRef = domain.getAssignedUserId() != null
                ? entityManager.getReference(UserEntity.class, domain.getAssignedUserId())
                : null;

        CampaignEntity entity = new CampaignEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setTheme(themeEntityRef);
        entity.setAssignedUser(userEntityRef);
        return entity;
    }
}

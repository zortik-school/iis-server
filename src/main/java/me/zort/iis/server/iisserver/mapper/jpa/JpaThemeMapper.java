package me.zort.iis.server.iisserver.mapper.jpa;

import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ThemeEntity;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.impl.ThemeImpl;
import org.springframework.stereotype.Component;

@Component
public class JpaThemeMapper implements JpaMapper<Theme, ThemeEntity> {

    @Override
    public Theme toDomain(ThemeEntity entity) {
        return new ThemeImpl(entity.getId(), entity.getName(), entity.getDescription());
    }

    @Override
    public ThemeEntity toEntity(Theme domain) {
        ThemeEntity entity = new ThemeEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());

        return entity;
    }
}

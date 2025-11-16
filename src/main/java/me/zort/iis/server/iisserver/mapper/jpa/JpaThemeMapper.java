package me.zort.iis.server.iisserver.mapper.jpa;

import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ThemeEntity;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.ThemeImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaThemeMapper implements JpaMapper<Theme, ThemeEntity> {

    @Override
    public Theme toDomain(ThemeEntity entity) {
        return new ThemeImpl(entity.getId(), entity.getName(), entity.getDescription());
    }

    @Override
    public ThemeEntity toEntity(Theme domain) {
        return new ThemeEntity(domain.getId(), domain.getName(), domain.getDescription(), List.of());
    }
}

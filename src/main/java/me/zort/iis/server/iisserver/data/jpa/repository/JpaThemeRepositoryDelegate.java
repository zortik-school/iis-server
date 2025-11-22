package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ThemeEntity;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.impl.ThemeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaThemeRepositoryDelegate extends JpaCrudRepository<Theme, ThemeEntity, Long> implements ThemeRepository {

    public JpaThemeRepositoryDelegate(
            JpaThemeRepository repository,
            JpaMapper<Theme, ThemeEntity> mapper, IdAdjustmentStrategy<Long> idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
    }
}

package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignEntity;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.CampaignRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCampaignRepositoryDelegate extends JpaCrudRepository<Campaign, CampaignEntity, Long> implements CampaignRepository {
    private final JpaCampaignRepository repository;
    private final JpaMapper<Campaign, CampaignEntity> mapper;

    public JpaCampaignRepositoryDelegate(
            JpaCampaignRepository repository,
            JpaMapper<Campaign, CampaignEntity> mapper, IdAdjustmentStrategy idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<Campaign> findAllByThemeId(long themeId, Pageable pageable) {
        return repository.findAllByTheme_Id(themeId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Campaign> findAllByAssignedUserId(long userId, Pageable pageable) {
        return repository.findAllByAssignedUser_Id(userId, pageable).map(mapper::toDomain);
    }
}

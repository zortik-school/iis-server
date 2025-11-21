package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.StepEntity;
import me.zort.iis.server.iisserver.domain.campaign.CampaignStepRepository;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JpaCampaignStepRepositoryDelegate extends JpaCrudRepository<Step, StepEntity, Long> implements CampaignStepRepository {
    private final JpaCampaignStepRepository repository;
    private final JpaMapper<Step, StepEntity> mapper;

    public JpaCampaignStepRepositoryDelegate(
            JpaCampaignStepRepository repository, JpaMapper<Step, StepEntity> mapper, IdAdjustmentStrategy idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Step> findByCampaignIdAndActiveTrue(long campaignId) {
        return repository.findByCampaign_IdAndActiveTrue(campaignId).map(mapper::toDomain);
    }

    @Override
    public Stream<Step> findAllByCampaignId(long campaignId) {
        return repository.findAllByCampaign_Id(campaignId).stream().map(mapper::toDomain);
    }

    @Override
    public void setActiveFalseByCampaignId(long campaignId) {
        repository.setActiveFalseByCampaignId(campaignId);
    }
}

package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.StepEntity;
import me.zort.iis.server.iisserver.domain.campaign.impl.CampaignStepRepository;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JpaCampaignStepRepositoryDelegate extends JpaCrudRepository<Step, StepEntity, Long> implements CampaignStepRepository {
    private final JpaCampaignStepRepository repository;
    private final JpaMapper<Step, StepEntity> mapper;

    public JpaCampaignStepRepositoryDelegate(
            JpaCampaignStepRepository repository, JpaMapper<Step, StepEntity> mapper, IdAdjustmentStrategy<Long> idAdjustmentStrategy) {
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
    public List<Step> findAllByCampaignIdIn(List<Long> campaignIds) {
        return repository.findAllByCampaign_IdIn(campaignIds).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Page<Step> findAllByAssignedUserId(long userId, Pageable pageable) {
        return repository.findAllByAssignedUser_Id(userId, pageable).map(mapper::toDomain);
    }

    @Override
    public void setActiveFalseByCampaignId(long campaignId) {
        repository.setActiveFalseByCampaignId(campaignId);
    }
}

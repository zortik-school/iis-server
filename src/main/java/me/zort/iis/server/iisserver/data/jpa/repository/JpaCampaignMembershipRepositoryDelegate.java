package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipId;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembership;
import me.zort.iis.server.iisserver.domain.campaign.impl.CampaignMembershipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCampaignMembershipRepositoryDelegate
        extends JpaCrudRepository<CampaignMembership, CampaignMembershipEntity, CampaignMembershipId> implements CampaignMembershipRepository {
    private final JpaCampaignMembershipRepository repository;
    private final JpaMapper<CampaignMembership, CampaignMembershipEntity> mapper;

    public JpaCampaignMembershipRepositoryDelegate(
            JpaCampaignMembershipRepository repository,
            JpaMapper<CampaignMembership, CampaignMembershipEntity> mapper, IdAdjustmentStrategy<CampaignMembershipId> idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void deleteByCampaignIdAndUserId(Long campaignId, Long userId) {
        repository.deleteByCampaign_IdAndUser_Id(campaignId, userId);
    }

    @Override
    public boolean existsByCampaignIdAndUserId(Long campaignId, Long userId) {
        return repository.existsByCampaign_IdAndUser_Id(campaignId, userId);
    }

    @Override
    public Page<CampaignMembership> findAllByCampaignId(Long campaignId, Pageable pageable) {
        return repository.findAllByCampaign_Id(campaignId, pageable).map(mapper::toDomain);
    }

    @Override
    public List<CampaignMembership> findAllByUserId(Long userId) {
        return repository.findAllByUser_Id(userId).stream().map(mapper::toDomain).toList();
    }
}

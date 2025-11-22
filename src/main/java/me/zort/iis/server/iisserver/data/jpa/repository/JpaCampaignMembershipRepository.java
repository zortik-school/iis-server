package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCampaignMembershipRepository extends JpaRepository<CampaignMembershipEntity, CampaignMembershipId> {

    void deleteByCampaign_IdAndUser_Id(Long campaignId, Long userId);

    boolean existsByCampaign_IdAndUser_Id(Long campaignId, Long userId);

    Page<CampaignMembershipEntity> findAllByCampaign_Id(Long campaignId, Pageable pageable);

    List<CampaignMembershipEntity> findAllByUser_Id(Long userId);
}

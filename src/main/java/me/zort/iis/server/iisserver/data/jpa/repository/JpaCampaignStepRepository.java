package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.StepEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JpaCampaignStepRepository extends JpaRepository<StepEntity, Long> {

    @Modifying
    @Query("UPDATE iis_step s SET s.active = false WHERE s.campaign.id = :campaignId")
    void setActiveFalseByCampaignId(@Param("campaignId") long campaignId);

    Optional<StepEntity> findByCampaign_IdAndActiveTrue(@NonNull Long id);

    List<StepEntity> findAllByCampaign_Id(long campaignId);

    Page<StepEntity> findAllByAssignedUser_Id(Long assignedUserId, Pageable pageable);

    List<StepEntity> findAllByCampaign_IdIn(Collection<Long> campaignIds);
}

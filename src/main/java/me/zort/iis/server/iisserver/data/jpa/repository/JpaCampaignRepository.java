package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface JpaCampaignRepository extends JpaRepository<CampaignEntity, Long> {

    Page<CampaignEntity> findAllByTheme_Id(@NonNull Long id, Pageable pageable);

    Page<CampaignEntity> findAllByAssignedUser_Id(@NonNull Long id, Pageable pageable);
}

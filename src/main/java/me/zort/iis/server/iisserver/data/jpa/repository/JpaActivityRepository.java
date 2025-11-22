package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.ActivityEntity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface JpaActivityRepository extends JpaRepository<ActivityEntity, Long> {

    Page<ActivityEntity> findAllByStep_Id(Long stepId, Pageable pageable);

    Page<ActivityEntity> findAllByStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            ActivityState state, long startDate, long endDate, Pageable pageable);

    Page<ActivityEntity> findAllByStep_IdInAndStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Collection<Long> ids, ActivityState state, long startDate, long endDate, Pageable pageable);
}

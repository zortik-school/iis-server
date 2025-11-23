package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.ActivityEntity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaActivityRepository extends JpaRepository<ActivityEntity, Long> {

    Page<ActivityEntity> findAllByStep_Id(Long stepId, Pageable pageable);


    @Query("""
    SELECT a FROM iis_activity a
    WHERE a.step.id IN :stepIds
      AND a.state = :state
      AND a.startDate <= :startDate
      AND a.endDate >= :endDate
      AND NOT EXISTS (
          SELECT m FROM iis_activity_memberships m
          WHERE m.activity.id = a.id 
            AND m.user.id = :userId
      )
    """)
    Page<ActivityEntity> findAllAvailableForUserInSteps(
            @Param("stepIds") Collection<Long> stepIds,
            @Param("state") ActivityState state,
            @Param("startDate") long startDate,
            @Param("endDate") long endDate,
            @Param("userId") long userId,
            Pageable pageable);
}

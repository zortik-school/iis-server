package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipId;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaActivityMembershipRepository extends JpaRepository<ActivityMembershipEntity, ActivityMembershipId> {

    void deleteByActivity_IdAndUser_Id(Long activityId, Long userId);

    boolean existsByActivity_IdAndUser_Id(Long activityId, Long userId);

    Page<ActivityMembershipEntity> findAllByActivity_Id(Long activityId, Pageable pageable);

    Page<ActivityMembershipEntity> findAllByUser_Id(Long userId, Pageable pageable);

    @Query("""
    SELECT m FROM iis_activity_memberships m
    WHERE m.user.id = :userId
      AND m.activity.state = :state
      AND m.activity.step.active = :stepActive
    """)
    Page<ActivityMembershipEntity> findAllByUserIdAndActivityStateAndStepActive(
            @Param("userId") long userId,
            @Param("state") ActivityState state,
            @Param("stepActive") boolean stepActive,
            Pageable pageable
    );
}

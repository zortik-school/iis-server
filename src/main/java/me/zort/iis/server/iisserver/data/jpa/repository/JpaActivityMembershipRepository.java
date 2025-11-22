package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaActivityMembershipRepository extends JpaRepository<ActivityMembershipEntity, ActivityMembershipId> {

    void deleteByActivity_IdAndUser_Id(Long activityId, Long userId);

    boolean existsByActivity_IdAndUser_Id(Long activityId, Long userId);

    Page<ActivityMembershipEntity> findAllByActivity_Id(Long activityId, Pageable pageable);

    Page<ActivityMembershipEntity> findAllByUser_Id(Long userId, Pageable pageable);
}

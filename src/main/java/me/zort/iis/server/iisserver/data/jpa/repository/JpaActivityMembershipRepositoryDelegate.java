package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipEntity;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipId;
import me.zort.iis.server.iisserver.domain.campaign.ActivityMembership;
import me.zort.iis.server.iisserver.domain.campaign.impl.ActivityMembershipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaActivityMembershipRepositoryDelegate
        extends JpaCrudRepository<ActivityMembership, ActivityMembershipEntity, ActivityMembershipId> implements ActivityMembershipRepository {
    private final JpaActivityMembershipRepository repository;
    private final JpaMapper<ActivityMembership, ActivityMembershipEntity> mapper;

    public JpaActivityMembershipRepositoryDelegate(
            JpaActivityMembershipRepository repository,
            JpaMapper<ActivityMembership, ActivityMembershipEntity> mapper,
            IdAdjustmentStrategy<ActivityMembershipId> idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void deleteByActivityIdAndUserId(long activityId, long userId) {
        repository.deleteByActivity_IdAndUser_Id(activityId, userId);
    }

    @Override
    public boolean existsByActivityIdAndUserId(long activityId, long userId) {
        return repository.existsByActivity_IdAndUser_Id(activityId, userId);
    }

    @Override
    public Page<ActivityMembership> findAllByActivityId(long activityId, Pageable pageable) {
        return repository.findAllByActivity_Id(activityId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<ActivityMembership> findAllByUserId(long userId, Pageable pageable) {
        return repository.findAllByUser_Id(userId, pageable).map(mapper::toDomain);
    }
}

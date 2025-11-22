package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityEntity;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.impl.ActivityRepository;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaActivityRepositoryDelegate extends JpaCrudRepository<Activity, ActivityEntity, Long> implements ActivityRepository {
    private final JpaActivityRepository repository;
    private final JpaMapper<Activity, ActivityEntity> mapper;

    public JpaActivityRepositoryDelegate(
            JpaActivityRepository repository,
            JpaMapper<Activity, ActivityEntity> mapper, IdAdjustmentStrategy<Long> idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<Activity> findAllByStepId(Long stepId, Pageable pageable) {
        return repository.findAllByStep_Id(stepId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Activity> findAllByStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            ActivityState state, long startDate, long endDate, Pageable pageable) {
        return repository
                .findAllByStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(state, startDate, endDate, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Activity> findAllByStepIdInAndStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            List<Long> stepIds, ActivityState state, long startDate, long endDate, Pageable pageable) {
        return repository
                .findAllByStep_IdInAndStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(stepIds, state, startDate, endDate, pageable)
                .map(mapper::toDomain);
    }
}

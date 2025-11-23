package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.ActivityMembership;
import me.zort.iis.server.iisserver.domain.campaign.ActivityMembershipService;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserAlreadyInActivityException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserNotInActivityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityMembershipServiceImpl implements ActivityMembershipService {
    private final ActivityMembershipRepository repository;

    @Override
    public ActivityMembership addMemberToActivity(long activityId, long userId) {
        if (repository.existsByActivityIdAndUserId(activityId, userId)) {
            throw new UserAlreadyInActivityException();
        }

        ActivityMembership membership = new ActivityMembershipImpl(activityId, userId);

        membership = repository.save(membership);

        // TODO: event

        return membership;
    }

    @Override
    public void removeMemberFromActivity(long activityId, long userId) {
        if (!repository.existsByActivityIdAndUserId(activityId, userId)) {
            throw new UserNotInActivityException();
        }

        repository.deleteByActivityIdAndUserId(activityId, userId);

        // TODO: event
    }

    @Override
    public boolean isMemberOfActivity(long activityId, long userId) {
        return repository.existsByActivityIdAndUserId(activityId, userId);
    }

    @Override
    public Page<ActivityMembership> getMembershipsForUser(long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<ActivityMembership> getActiveMembershipsForUser(long userId, Pageable pageable) {
        return repository.findAllByUserIdAndActivityStateAndStepActive(userId, ActivityState.OPEN, true, pageable);
    }

    @Override
    public Page<ActivityMembership> getMembershipsForActivity(long activityId, Pageable pageable) {
        return repository.findAllByActivityId(activityId, pageable);
    }
}

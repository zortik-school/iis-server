package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final CampaignStepRepository stepRepository;

    @Override
    public Activity createActivity(CreateActivityArgs args) {
        Activity activity = new ActivityImpl(
                -1,
                args.getName(),
                args.getDescription(), args.getStepId(), args.getStartDate(), args.getEndDate(), ActivityState.CLOSED);
        activity = activityRepository.save(activity);

        // TODO: event

        return activity;
    }

    @Override
    public void deleteActivity(long activityId) {
        activityRepository.deleteById(activityId);

        // TODO: event
    }

    @Override
    public void setActivityState(long activityId, ActivityState state) {
        Activity activity = activityRepository
                .findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));
        activity.setState(state);

        activityRepository.save(activity);

        // TODO: event
    }

    @Override
    public Optional<Activity> getActivity(long activityId) {
        return activityRepository.findById(activityId);
    }

    @Override
    public Page<Activity> getActivitiesForStep(long stepId, Pageable pageable) {
        return activityRepository.findAllByStepId(stepId, pageable);
    }

    @Override
    public Page<Activity> getAvailableActivitiesForCampaigns(long userId, List<Long> campaignIds, Pageable pageable) {
        List<Step> steps = stepRepository.findAllByCampaignIdIn(campaignIds);
        if (steps.isEmpty()) {
            return Page.empty(pageable);
        }

        List<Long> stepIds = steps.stream()
                .filter(Step::isActive)
                .map(Step::getId)
                .toList();

        long now = System.currentTimeMillis();

        return activityRepository.findAllByStepIdInAndStateAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndUserNotInMembership(
                stepIds, ActivityState.OPEN, now, now, userId, pageable);
    }

    @Override
    public boolean isActivityOfCampaign(long activityId, long campaignId) {
        Optional<Activity> opt = activityRepository.findById(activityId);
        if (opt.isEmpty()) {
            return false;
        }

        Activity activity = opt.get();

        Step step = stepRepository.findById(activity.getStepId()).orElse(null);
        if (step == null) {
            return false;
        }

        return step.getCampaignId() == campaignId;
    }

    @Override
    public Page<Activity> getAllActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }
}

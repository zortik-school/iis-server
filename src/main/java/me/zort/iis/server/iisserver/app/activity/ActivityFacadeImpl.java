package me.zort.iis.server.iisserver.app.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.PrivilegesResolver;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserService;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityFacadeImpl implements ActivityFacade {
    private final UserService userService;
    private final ActivityService activityService;
    private final ActivityMembershipService activityMembershipService;
    private final CampaignMembershipService campaignMembershipService;
    private final PrivilegesResolver privilegesResolver;

    @Override
    public Activity createActivity(CreateActivityArgs args) {
        return activityService.createActivity(args);
    }

    @Override
    public void deleteActivity(long activityId) {
        activityService.deleteActivity(activityId);
    }

    @Override
    public Activity getActivity(long activityId) {
        return activityService.getActivity(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));
    }

    @Override
    public void openActivity(long activityId) {
        activityService.setActivityState(activityId, ActivityState.OPEN);
    }

    @Override
    public void closeActivity(long activityId, CloseActivityOptions options) {
        activityService.setActivityState(activityId, ActivityState.CLOSED);

        ActivityNoteInput note = options.getNote();
        if (note != null) {
            // TODO: note from options
        }
    }

    @Override
    public void addUserToActivity(long activityId, long userId) {
        activityMembershipService.addMemberToActivity(activityId, userId);
    }

    @Transactional
    @Override
    public void removeUserFromActivity(long activityId, long userId) {
        activityMembershipService.removeMemberFromActivity(activityId, userId);
    }

    @Override
    public Page<Activity> getActivitiesForStep(long stepId, Pageable pageable) {
        return activityService.getActivitiesForStep(stepId, pageable);
    }

    @Override
    public Page<Activity> getAvailableActivities(long userId, Pageable pageable) {
        User user = userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_ACTIVITIES)) {
            // Has access to all activities
            return activityService.getAvailableActivities(pageable);
        } else {
            List<Long> campaignIds = campaignMembershipService.getCampaignMembershipsOfUser(userId)
                    .stream()
                    .map(CampaignMembership::getCampaignId)
                    .toList();

            return activityService.getAvailableActivitiesForCampaigns(campaignIds, pageable);
        }
    }

    @Override
    public Page<Activity> getAssignedActivities(long userId, Pageable pageable) {
        return activityMembershipService
                .getMembershipsForUser(userId, pageable)
                .map(membership -> activityService.getActivity(membership.getActivityId()).get()); // TODO: more secure get
    }

    @Override
    public Page<User> getMembersOfActivity(long activityId, Pageable pageable) {
        return activityMembershipService
                .getMembershipsForActivity(activityId, pageable)
                .map(membership -> userService.getUser(membership.getUserId()).get()); // TODO: more secure get
    }

    @Override
    public Page<Activity> getAllActivities(Pageable pageable) {
        return activityService.getAllActivities(pageable);
    }
}

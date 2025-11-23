package me.zort.iis.server.iisserver.app.access;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserService;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignZoneServiceImpl implements CampaignZoneService {
    private final CampaignMembershipService campaignMembershipService;
    private final UserService userService;
    private final CampaignStepService campaignStepService;
    private final ActivityService activityService;

    @Override
    public void addUserToCampaignZone(long campaignZoneId, long userId) {
        campaignMembershipService.addUserToCampaign(campaignZoneId, userId);
    }

    @Override
    public void removeUserFromCampaignZone(long campaignZoneId, long userId) {
        campaignMembershipService.removeUserFromCampaign(campaignZoneId, userId);
    }

    @Override
    public Page<User> getMembersOfZone(long campaignZoneId, Pageable pageable) {
        return campaignMembershipService.getCampaignMembershipsOfCampaign(campaignZoneId, pageable)
                .map(membership -> userService.getUser(membership.getUserId())
                        .orElseThrow(() -> new UserNotFoundException(membership.getUserId()))); // TODO: safe filtering
    }

    @Override
    public List<Long> getCampaignZoneIdsForUser(long userId) {
        return campaignMembershipService.getCampaignMembershipsOfUser(userId)
                .stream()
                .map(CampaignMembership::getCampaignId)
                .toList();
    }

    @Override
    public long getCampaignZoneIdForCampaign(long campaignId) {
        return campaignId;
    }

    @Override
    public long getCampaignZoneIdForCampaignStep(long campaignStepId) {
        Step step = campaignStepService
                .getStep(campaignStepId)
                .orElseThrow(() -> new StepNotFoundException(campaignStepId));

        return getCampaignZoneIdForCampaign(step.getCampaignId());
    }

    @Override
    public long getCampaignZoneIdForActivity(long activityId) {
        Activity activity = activityService
                .getActivity(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));

        return getCampaignZoneIdForCampaignStep(activity.getStepId());
    }
}

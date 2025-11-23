package me.zort.iis.server.iisserver.app.access;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessStrategyImpl implements AccessStrategy {
    private final PrivilegesResolver privilegesResolver;
    private final CampaignService campaignService;
    private final CampaignStepService campaignStepService;
    private final ActivityService activityService;
    private final ActivityMembershipService activityMembershipService;

    @Override
    public boolean canViewTheme(long themeId, User user) {
        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_THEMES)) {
            return true;
        }

        if (campaignService.isAnyCampaignOfThemeAssignedToUser(themeId, user.getId())) {
            // Is assigned to any campaign of this theme
            return true;
        }

        return false;
    }

    @Override
    public boolean canViewAssignedCampaigns(User user) {
        return privilegesResolver.getGrantedPrivileges(user).contains(Privilege.VIEW_ASSIGNED_CAMPAIGNS);
    }

    @Override
    public boolean canViewAssignedCampaignSteps(User user) {
        return privilegesResolver.getGrantedPrivileges(user).contains(Privilege.VIEW_ASSIGNED_STEPS);
    }

    @Override
    public CampaignAccessRole getCampaignAccessRole(long campaignId, User user) {
        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_CAMPAIGNS)) {
            return CampaignAccessRole.ADMIN;
        }

        if (campaignService.getCampaign(campaignId)
                .map(campaign -> campaign.getAssignedUserId() != null && campaign.getAssignedUserId().equals(user.getId()))
                .orElse(false)) {
            // User is assigned to this campaign
            return CampaignAccessRole.EDITOR;
        }

        return CampaignAccessRole.NONE;
    }

    @Override
    public CampaignStepAccessRole getCampaignStepAccessRole(long campaignStepId, User user) {
        Optional<Step> step = campaignStepService.getStep(campaignStepId);
        if (step.isEmpty()) {
            throw new StepNotFoundException(campaignStepId);
        }

        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_STEPS)) {
            return CampaignStepAccessRole.ADMIN;
        }

        if (step.get().getAssignedUserId() != null && step.get().getAssignedUserId() == user.getId()) {
            // User is assigned to this step
            return CampaignStepAccessRole.EDITOR;
        }

        if (getCampaignAccessRole(step.get().getCampaignId(), user).canManageSteps()) {
            // User can manage the campaign of this step
            return CampaignStepAccessRole.ADMIN;
        }

        return CampaignStepAccessRole.NONE;
    }

    @Override
    public ActivityAccessRole getActivityAccessRole(long activityId, User user) {
        Activity activity = activityService
                .getActivity(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));

        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_ACTIVITIES)) {
            // Has global activity management privilege
            return ActivityAccessRole.ADMIN;
        }

        if (getCampaignStepAccessRole(activity.getStepId(), user).canManageActivities()) {
            // User can manage the step of this activity
            return ActivityAccessRole.ADMIN;
        }

        if (activityMembershipService.isMemberOfActivity(activityId, user.getId())) {
            // User is assigned to this activity
            return ActivityAccessRole.EXECUTOR;
        }

        return ActivityAccessRole.NONE;
    }
}

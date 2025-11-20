package me.zort.iis.server.iisserver.app.access;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.campaign.CampaignService;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessStrategyImpl implements AccessStrategy {
    private final PrivilegesResolver privilegesResolver;
    private final CampaignService campaignService;

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
    public boolean canInspectCampaign(long campaignId, User user) {
        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_CAMPAIGNS)) {
            return true;
        }

        if (campaignService.getCampaign(campaignId)
                .map(campaign -> campaign.getAssignedUserId() != null && campaign.getAssignedUserId().equals(user.getId()))
                .orElse(false)) {
            // User is assigned to this campaign
            return true;
        }

        return false;
    }
}

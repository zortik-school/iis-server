package me.zort.iis.server.iisserver.app.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembershipService;
import me.zort.iis.server.iisserver.domain.campaign.CampaignService;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserService;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CampaignFacadeImpl implements CampaignFacade {
    private final CampaignService campaignService;
    private final CampaignMembershipService campaignMembershipService;
    private final UserService userService;

    @Override
    public Campaign createCampaign(CreateCampaignArgs args) {
        return campaignService.createCampaign(args);
    }

    @Override
    public void deleteCampaign(long campaignId) {
        campaignService.deleteCampaign(campaignId);
    }

    @Override
    public void addUserToCampaign(long campaignId, long userId) {
        campaignMembershipService.addUserToCampaign(campaignId, userId);
    }

    @Transactional
    @Override
    public void removeUserFromCampaign(long campaignId, long userId) {
        campaignMembershipService.removeUserFromCampaign(campaignId, userId);
    }

    @Override
    public Campaign getCampaign(long campaignId) {
        return campaignService.getCampaign(campaignId).orElseThrow(() -> new CampaignNotFoundException(campaignId));
    }

    @Override
    public void assignUser(long campaignId, @Nullable Long userId) {
        if (userId != null) {
            userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
        }

        campaignService.assignUser(campaignId, userId);
    }

    @Override
    public Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable) {
        return campaignService.getAssignedCampaigns(userId, pageable);
    }

    @Override
    public Page<Campaign> getCampaignsForTheme(long themeId, Pageable pageable) {
        return campaignService.getAllCampaignsForTheme(themeId, pageable);
    }

    @Override
    public Page<User> getMembersOfCampaign(long campaignId, Pageable pageable) {
        return campaignMembershipService.getCampaignMembershipsOfCampaign(campaignId, pageable)
                .map(membership -> userService.getUser(membership.getUserId())
                        .orElseThrow(() -> new UserNotFoundException(membership.getUserId()))); // TODO: safe filtering
    }

    @Override
    public Page<Campaign> getAllCampaigns(Pageable pageable) {
        return campaignService.getAllCampaigns(pageable);
    }
}

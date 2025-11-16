package me.zort.iis.server.iisserver.app.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignFacadeImpl implements CampaignFacade {
    private final CampaignService campaignService;

    @Override
    public Campaign createCampaign(CreateCampaignArgs args) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet");
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
    public Page<Campaign> getAllCampaigns(Pageable pageable) {
        return campaignService.getAllCampaigns(pageable);
    }
}

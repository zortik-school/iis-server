package me.zort.iis.server.iisserver.startup.seeders;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.campaign.CreateCampaignArgs;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@RequiredArgsConstructor
public class CampaignSeeder implements Seeder {
    private final CampaignFacade campaignFacade;

    @Override
    public void seed() {
        if (!campaignFacade.getAllCampaigns(PageRequest.of(0, 1)).isEmpty()) {
            return;
        }

        CreateCampaignArgs args = CreateCampaignArgs.builder()
                .name("Just a campaign")
                .themeId(1)
                .build();
        campaignFacade.createCampaign(args);
    }
}

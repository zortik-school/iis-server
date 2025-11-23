package me.zort.iis.server.iisserver.startup.seeders;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.activity.CreateActivityArgs;
import me.zort.iis.server.iisserver.app.campaign.AddStepArgs;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.campaign.CreateCampaignArgs;
import me.zort.iis.server.iisserver.app.theme.CreateThemeArgs;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CampaignSeeder implements Seeder {
    private final ThemeFacade themeFacade;
    private final CampaignFacade campaignFacade;
    private final CampaignStepFacade campaignStepFacade;
    private final ActivityFacade activityFacade;

    @Override
    public void seed() {
        long themeId = seedThemes();

        long campaignId = seedCampaigns(themeId);

        long stepId = seedSteps(campaignId);

        seedActivities(stepId);
    }

    private long seedThemes() {
        CreateThemeArgs args = CreateThemeArgs.builder()
                .name("Just a theme")
                .description("This is really just a theme.")
                .build();
        return themeFacade.createTheme(args).getId();
    }

    private long seedCampaigns(long themeId) {
        CreateCampaignArgs args = CreateCampaignArgs.builder()
                .name("Just a campaign")
                .themeId(themeId)
                .build();
        return campaignFacade.createCampaign(args).getId();
    }

    private long seedSteps(long campaignId) {
        long stepId = campaignStepFacade.addStep(AddStepArgs.builder()
                .name("First Step")
                .campaignId(campaignId)
                .build()).getId();
        campaignStepFacade.addStep(AddStepArgs.builder()
                .name("Second Step")
                .campaignId(campaignId)
                .build());
        campaignStepFacade.addStep(AddStepArgs.builder()
                .name("Third Step")
                .campaignId(campaignId)
                .build());

        return stepId;
    }

    private void seedActivities(long stepId) {
        long now = System.currentTimeMillis();

        activityFacade.createActivity(CreateActivityArgs.builder()
                .name("Test activity")
                .description("This is just a test activity.")
                .startDate(now)
                .endDate(now + 30L * 24 * 60 * 60 * 1000) // +30 days
                .stepId(stepId)
                .build());
    }
}

package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Step;

@Getter
public class CampaignStepFullModel extends CampaignStepModel {
    private final boolean active;

    public CampaignStepFullModel(Step step, boolean active) {
        super(step);
        this.active = active;
    }
}

package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Step;

@Getter
public class CampaignStepModel {
    private final long id;
    private final String name;

    public CampaignStepModel(Step step) {
        this.id = step.getId();
        this.name = step.getName();
    }
}

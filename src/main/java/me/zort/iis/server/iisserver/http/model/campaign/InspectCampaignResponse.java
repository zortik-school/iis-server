package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.InspectCampaignOp;

@Getter
public class InspectCampaignResponse {
    private final CampaignModel campaign;
    private final Long assignedUserId;

    public InspectCampaignResponse(InspectCampaignOp.Result result) {
        this.campaign = new CampaignModel(result.getCampaign());
        this.assignedUserId = result.getCampaign().getAssignedUserId();
    }
}

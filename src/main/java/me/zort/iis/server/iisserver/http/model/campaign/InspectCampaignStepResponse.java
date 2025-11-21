package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.InspectCampaignStepOp;

import java.util.Map;

@Getter
public class InspectCampaignStepResponse {
    private final CampaignStepModel step;
    private final Long assignedUserId;
    private final Map<InspectCampaignStepOp.AccessPrivilege, Boolean> accessPrivileges;

    public InspectCampaignStepResponse(InspectCampaignStepOp.Result result) {
        this.step = new CampaignStepModel(result.getStep());
        this.assignedUserId = result.getStep().getAssignedUserId();
        this.accessPrivileges = Map.copyOf(result.getAccessPrivileges());
    }
}

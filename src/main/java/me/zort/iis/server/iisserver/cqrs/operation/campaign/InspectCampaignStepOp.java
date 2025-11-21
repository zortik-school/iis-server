package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Step;

import java.util.Map;

@Getter
@AllArgsConstructor
public class InspectCampaignStepOp implements Operation<InspectCampaignStepOp.Result> {
    private final long stepId;

    @Getter
    @AllArgsConstructor
    public static class Result {
        private final Step step;
        private final Map<AccessPrivilege, Boolean> accessPrivileges;

    }

    public enum AccessPrivilege {
        ASSIGN_STAFF,

    }
}

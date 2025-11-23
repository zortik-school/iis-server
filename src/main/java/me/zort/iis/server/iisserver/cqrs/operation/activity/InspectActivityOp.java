package me.zort.iis.server.iisserver.cqrs.operation.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Activity;

import java.util.Map;

@Getter
@AllArgsConstructor
public class InspectActivityOp implements Operation<InspectActivityOp.Result> {
    private final long activityId;

    @Getter
    @AllArgsConstructor
    public static class Result {
        private final Activity activity;
        private final Map<AccessPrivilege, Boolean> accessPrivileges;

    }

    public enum AccessPrivilege {
        CHANGE_STATE,
        ASSIGN_STAFF,

    }
}

package me.zort.iis.server.iisserver.http.model.activity;

import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.operation.activity.InspectActivityOp;

import java.util.Map;

@Getter
public class InspectActivityResponse {
    private final ActivityModel activity;
    private final Map<InspectActivityOp.AccessPrivilege, Boolean> accessPrivileges;

    public InspectActivityResponse(InspectActivityOp.Result result) {
        this.activity = new ActivityModel(result.getActivity());
        this.accessPrivileges = Map.copyOf(result.getAccessPrivileges());
    }
}

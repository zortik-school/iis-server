package me.zort.iis.server.iisserver.cqrs.operation.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class DeleteActivityOp implements Command {
    private final long activityId;

}

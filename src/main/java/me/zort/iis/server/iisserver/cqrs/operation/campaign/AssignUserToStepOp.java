package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;
import org.jetbrains.annotations.Nullable;

@Getter
@AllArgsConstructor
public class AssignUserToStepOp implements Command {
    private final long stepId;
    @Nullable
    private final Long userId;

}

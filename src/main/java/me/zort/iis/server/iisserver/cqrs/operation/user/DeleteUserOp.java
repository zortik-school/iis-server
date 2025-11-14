package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@Builder
public class DeleteUserOp implements Command {
    private final long userId;

}

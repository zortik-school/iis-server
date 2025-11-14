package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;
import me.zort.iis.server.iisserver.domain.user.Role;

@Getter
@Builder
public class ChangeUserRoleOp implements Command {
    private final long userId;
    private final Role role;

}

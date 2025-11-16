package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.Command;
import me.zort.iis.server.iisserver.domain.user.Role;

@Getter
@RequiredArgsConstructor
public class ChangeUserRoleOp implements Command {
    private final long userId;
    private final Role role;

}

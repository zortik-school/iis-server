package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@RequiredArgsConstructor
public class DeleteUserOp implements Command {
    private final long userId;

}

package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class ChangePasswordOp implements Command {
    private final String oldPassword;
    private final String newPassword;

}

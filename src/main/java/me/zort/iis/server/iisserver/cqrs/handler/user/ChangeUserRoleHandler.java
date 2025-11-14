package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.ChangeUserRoleOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeUserRoleHandler extends CommandHandler<ChangeUserRoleOp> {
    private final UserFacade userFacade;

    @Override
    public void execute(ChangeUserRoleOp operation) {
        userFacade.setUserRole(operation.getUserId(), operation.getRole());
    }

    @Override
    public Class<ChangeUserRoleOp> getOperationType() {
        return ChangeUserRoleOp.class;
    }
}

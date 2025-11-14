package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.DeleteUserOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserHandler extends CommandHandler<DeleteUserOp> {
    private final UserFacade userFacade;

    @Override
    public void execute(DeleteUserOp operation) {
        userFacade.deleteUser(operation.getUserId());
    }

    @Override
    public Class<DeleteUserOp> getOperationType() {
        return DeleteUserOp.class;
    }
}

package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.GetUserOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserHandler implements OperationHandler<GetUserOp, User> {
    private final UserFacade userFacade;

    @Override
    public User handle(GetUserOp operation) {
        return userFacade.getUser(operation.getUserId());
    }

    @Override
    public Class<GetUserOp> getOperationType() {
        return GetUserOp.class;
    }
}

package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.GetUsersOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersHandler implements OperationHandler<GetUsersOp, Page<User>> {
    private final UserFacade userFacade;
    
    @Override
    public Page<User> handle(GetUsersOp operation) {
        return userFacade.getUsers(operation.getPageable());
    }

    @Override
    public Class<GetUsersOp> getOperationType() {
        return GetUsersOp.class;
    }
}

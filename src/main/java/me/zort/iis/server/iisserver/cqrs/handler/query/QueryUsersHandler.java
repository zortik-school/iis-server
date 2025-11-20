package me.zort.iis.server.iisserver.cqrs.handler.query;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.query.QueryFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.query.QueryUsersOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueryUsersHandler implements OperationHandler<QueryUsersOp, Page<User>> {
    private final QueryFacade queryFacade;

    @Override
    public Page<User> handle(QueryUsersOp operation) {
        return queryFacade.queryUsers(operation.getQuery(), operation.getPageable());
    }

    @Override
    public Class<QueryUsersOp> getOperationType() {
        return QueryUsersOp.class;
    }
}

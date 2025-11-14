package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.PrivilegesResolver;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.GetPrivilegesForUserOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPrivilegesForUserHandler implements OperationHandler<GetPrivilegesForUserOp, List<Privilege>> {
    private final PrivilegesResolver privilegesResolver;

    @Override
    public List<Privilege> handle(GetPrivilegesForUserOp operation) {
        return privilegesResolver.getGrantedPrivileges(operation.getUser());
    }

    @Override
    public Class<GetPrivilegesForUserOp> getOperationType() {
        return GetPrivilegesForUserOp.class;
    }
}

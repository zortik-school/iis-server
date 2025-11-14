package me.zort.iis.server.iisserver.cqrs.filter;

import me.zort.iis.server.iisserver.aop.access.RequirePrivilege;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.cqrs.operation.user.ChangeUserRoleOp;
import me.zort.iis.server.iisserver.cqrs.operation.user.DeleteUserOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * Filter that ensures only users with MANAGE_USERS privilege can perform user management operations.
 */
@Component
public class UserManagementFilter extends AggregateFilter {
    private static final List<Class<? extends Operation<?>>> MANAGED_OPERATIONS = List.of(
            // User management operations handled by this filter
            ChangeUserRoleOp.class,
            DeleteUserOp.class
    );

    public UserManagementFilter() {
        super(MANAGED_OPERATIONS);
    }

    @RequirePrivilege(privilege = Privilege.MANAGE_USERS)
    @Override
    public Object filter(Operation<Object> operation, Supplier<Object> next) {
        // We really do nothing here, it's just for the aop purposes
        return next.get();
    }
}

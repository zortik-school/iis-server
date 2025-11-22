package me.zort.iis.server.iisserver.cqrs.filter;

import me.zort.iis.server.iisserver.aop.access.RequirePrivilege;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.cqrs.operation.activity.GetActivitiesOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class ActivityManagementFilter extends AggregateFilter {
    private static final List<Class<? extends Operation<?>>> MANAGED_OPERATIONS = List.of(
            // Activities management operations handled by this filter
            GetActivitiesOp.class
    );

    public ActivityManagementFilter() {
        super(MANAGED_OPERATIONS);
    }

    @RequirePrivilege(privilege = Privilege.MANAGE_ACTIVITIES)
    @Override
    public Object filter(Operation<Object> operation, Supplier<Object> next) {
        return next.get();
    }
}

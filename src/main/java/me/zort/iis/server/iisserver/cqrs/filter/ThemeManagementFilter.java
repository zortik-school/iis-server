package me.zort.iis.server.iisserver.cqrs.filter;

import me.zort.iis.server.iisserver.aop.access.RequirePrivilege;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.cqrs.operation.theme.GetAllThemesOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * Filter that manages theme-related operations, ensuring that only, ensuring that only
 * users with the MANAGE_THEMES privilege can execute them.
 */
@Component
public class ThemeManagementFilter extends AggregateFilter {
    private static final List<Class<? extends Operation<?>>> MANAGED_OPERATIONS = List.of(
            // Theme management operations handled by this filter
            GetAllThemesOp.class
    );

    public ThemeManagementFilter() {
        super(MANAGED_OPERATIONS);
    }

    @RequirePrivilege(privilege = Privilege.MANAGE_THEMES)
    @Override
    public Object filter(Operation<Object> operation, Supplier<Object> next) {
        return next.get();
    }
}

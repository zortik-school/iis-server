package me.zort.iis.server.iisserver.cqrs.filter;

import me.zort.iis.server.iisserver.aop.access.RequirePrivilege;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetAllCampaignsOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CampaignManagementFilter extends AggregateFilter {
    private static final List<Class<? extends Operation<?>>> MANAGED_OPERATIONS = List.of(
            // Theme management operations handled by this filter
            GetAllCampaignsOp.class
    );

    public CampaignManagementFilter() {
        super(MANAGED_OPERATIONS);
    }

    @RequirePrivilege(privilege = Privilege.MANAGE_CAMPAIGNS)
    @Override
    public Object filter(Operation<Object> operation, Supplier<Object> next) {
        return next.get();
    }
}

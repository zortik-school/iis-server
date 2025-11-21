package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AssignUserToCampaignOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignUserToCampaignHandler extends CommandHandler<AssignUserToCampaignOp> {
    private final CampaignFacade campaignFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(AssignUserToCampaignOp operation) {
        if (!accessStrategy.getCampaignAccessRole(operation.getCampaignId(), userProvider.getCurrentUser()).canAssignStaff()) {
            throw new NoAccessException("User does not have access to assign users to this campaign.");
        }

        campaignFacade.assignUser(operation.getCampaignId(), operation.getUserId());
    }

    @Override
    public Class<AssignUserToCampaignOp> getOperationType() {
        return AssignUserToCampaignOp.class;
    }
}

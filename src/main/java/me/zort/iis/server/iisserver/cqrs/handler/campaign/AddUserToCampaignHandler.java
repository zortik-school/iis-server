package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AddUserToCampaignOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUserToCampaignHandler extends CommandHandler<AddUserToCampaignOp> {
    private final CampaignFacade campaignFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(AddUserToCampaignOp operation) {
        if (!accessStrategy
                .getCampaignAccessRole(operation.getCampaignId(), userProvider.getCurrentUser())
                .canManageMembers()) {
            throw new NoAccessException("User does not have permission to add members to this campaign.");
        }

        campaignFacade.addUserToCampaign(operation.getCampaignId(), operation.getUserId());
    }

    @Override
    public Class<AddUserToCampaignOp> getOperationType() {
        return AddUserToCampaignOp.class;
    }
}

package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.GetUsersForCampaignOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersForCampaignHandler implements OperationHandler<GetUsersForCampaignOp, Page<User>> {
    private final CampaignFacade campaignFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Page<User> handle(GetUsersForCampaignOp operation) {
        if (!accessStrategy
                .getCampaignAccessRole(operation.getCampaignId(), userProvider.getCurrentUser())
                .canManageMembers()) {
            throw new NoAccessException("User has no access to view campaign members");
        }

        return campaignFacade.getMembersOfCampaign(operation.getCampaignId(), operation.getPageable());
    }

    @Override
    public Class<GetUsersForCampaignOp> getOperationType() {
        return GetUsersForCampaignOp.class;
    }
}

package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetAllCampaignsOp;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetAssignedCampaignsOp;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetThemeCampaignsOp;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.campaign.CampaignModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CampaignsController {
    private final OperationExecutor operationExecutor;

    @GetMapping("/campaigns")
    public PageResponse<CampaignModel> getCampaigns(
            @RequestParam(value = "assigned", required = false) Boolean assigned,
            @RequestParam(value = "themeId", required = false) Long themeId,
            Pageable pageable) {
        Page<Campaign> campaigns;
        if (assigned != null) {
            campaigns = operationExecutor.dispatch(new GetAssignedCampaignsOp(pageable));
        } else if (themeId != null) {
            campaigns = operationExecutor.dispatch(new GetThemeCampaignsOp(themeId, pageable));
        } else {
            campaigns = operationExecutor.dispatch(new GetAllCampaignsOp(pageable));
        }

        return PageResponse.fromPage(campaigns, CampaignModel::new);
    }
}

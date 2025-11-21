package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.ActivateCampaignStepOp;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AddCampaignStepOp;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.campaign.AddCampaignStepRequest;
import me.zort.iis.server.iisserver.http.model.campaign.CampaignStepModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CampaignStepsController {
    private final OperationExecutor operationExecutor;

    @PostMapping("/steps")
    public CampaignStepModel addStep(@RequestBody AddCampaignStepRequest body) {
        Step step = operationExecutor.dispatch(new AddCampaignStepOp(body.getCampaignId(), body.getName()));

        return new CampaignStepModel(step);
    }

    @PostMapping("/steps/{id}/activate")
    public BlankResponse activateStep(@PathVariable long id) {
        operationExecutor.dispatch(new ActivateCampaignStepOp(id));

        return BlankResponse.getInstance();
    }
}

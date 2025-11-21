package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.campaign.AddCampaignStepRequest;
import me.zort.iis.server.iisserver.http.model.campaign.AssignUserToStepRequest;
import me.zort.iis.server.iisserver.http.model.campaign.CampaignStepModel;
import me.zort.iis.server.iisserver.http.model.campaign.InspectCampaignStepResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/steps")
@RequiredArgsConstructor
public class CampaignStepsController {
    private final OperationExecutor operationExecutor;

    @GetMapping
    public PageResponse<CampaignStepModel> getSteps(
            @RequestParam(value = "assigned", required = false) Boolean assigned,
            Pageable pageable) {
        Page<Step> steps;
        if (assigned != null) {
            steps = operationExecutor.dispatch(new GetAssignedCampaignStepsOp(pageable));
        } else {
            steps = operationExecutor.dispatch(new GetAllCampaignStepsOp(pageable));
        }

        return PageResponse.fromPage(steps, CampaignStepModel::new);
    }

    @PostMapping
    public CampaignStepModel addStep(@RequestBody AddCampaignStepRequest body) {
        Step step = operationExecutor.dispatch(new AddCampaignStepOp(body.getCampaignId(), body.getName()));

        return new CampaignStepModel(step);
    }

    @GetMapping("/{id}/inspect")
    public InspectCampaignStepResponse inspectStep(@PathVariable long id) {
        InspectCampaignStepOp.Result result = operationExecutor.dispatch(new InspectCampaignStepOp(id));

        return new InspectCampaignStepResponse(result);
    }

    @PostMapping("/{id}/assign")
    public BlankResponse assignUser(@PathVariable long id, @RequestBody AssignUserToStepRequest body) {
        operationExecutor.dispatch(new AssignUserToStepOp(id, body.getUserId()));

        return BlankResponse.getInstance();
    }

    @PostMapping("/{id}/activate")
    public BlankResponse activateStep(@PathVariable long id) {
        operationExecutor.dispatch(new ActivateCampaignStepOp(id));

        return BlankResponse.getInstance();
    }
}

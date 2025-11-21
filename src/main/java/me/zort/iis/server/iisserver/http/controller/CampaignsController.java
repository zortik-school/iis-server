package me.zort.iis.server.iisserver.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.campaign.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignsController {
    private final OperationExecutor operationExecutor;

    @PostMapping
    public CreateCampaignResponse createCampaign(@RequestBody CreateCampaignRequest body) {
        Campaign campaign = operationExecutor.dispatch(new CreateCampaignOp(body.getName(), body.getThemeId()));

        return new CreateCampaignResponse(campaign);
    }

    @DeleteMapping("/{id}")
    public BlankResponse deleteCampaign(@PathVariable long id) {
        operationExecutor.dispatch(new DeleteCampaignOp(id));

        return BlankResponse.getInstance();
    }

    @GetMapping("/{id}/inspect")
    public InspectCampaignResponse inspectCampaign(@PathVariable long id) {
        InspectCampaignOp.Result result = operationExecutor.dispatch(new InspectCampaignOp(id));

        return new InspectCampaignResponse(result);
    }

    @GetMapping("/{id}/steps")
    public List<CampaignStepFullModel> getCampaignSteps(@PathVariable long id) {
        Optional<Step> activeStep = operationExecutor.dispatch(new GetActiveCampaignStepOp(id));

        return operationExecutor.dispatch(new GetCampaignStepsForCampaignOp(id))
                .stream()
                .map(step -> {
                    boolean isActive = activeStep.isPresent() && activeStep.get().getId() == step.getId();

                    return new CampaignStepFullModel(step, isActive);
                })
                .toList();
    }

    @PostMapping("/{id}/assign")
    public BlankResponse assignUserToCampaign(
            @PathVariable long id, @Valid @RequestBody AssignUserToCampaignRequest body) {
        operationExecutor.dispatch(new AssignUserToCampaignOp(id, body.getUserId()));

        return BlankResponse.getInstance();
    }

    @GetMapping
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

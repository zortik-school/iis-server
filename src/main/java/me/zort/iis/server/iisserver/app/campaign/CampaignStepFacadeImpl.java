package me.zort.iis.server.iisserver.app.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.CampaignService;
import me.zort.iis.server.iisserver.domain.campaign.CampaignStepService;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignStepFacadeImpl implements CampaignStepFacade {
    private final CampaignService campaignService;
    private final CampaignStepService campaignStepService;

    @Override
    public Step addStep(AddStepArgs args) {
        return campaignStepService.addStep(args);
    }

    @Override
    public void deleteStep(long stepId) {
        campaignStepService.deleteStep(stepId);
    }

    @Override
    public Step getStep(long stepId) {
        return campaignStepService.getStep(stepId).orElseThrow(() -> new StepNotFoundException(stepId));
    }

    @Override
    public void assignUserToStep(long stepId, @Nullable Long userId) {
        campaignStepService.assignUserToStep(stepId, userId);
    }

    @Transactional
    @Override
    public void activateStep(long stepId) {
        campaignStepService.activateStep(stepId);
    }

    @Override
    public Optional<Step> getActiveStepForCampaign(long campaignId) {
        campaignService
                .getCampaign(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));

        return campaignStepService.getActiveStep(campaignId);
    }

    @Override
    public List<Step> getStepsForCampaign(long campaignId) {
        return campaignStepService.getAllStepsForCampaign(campaignId);
    }

    @Override
    public Page<Step> getAssignedCampaignSteps(long userId, Pageable pageable) {
        return campaignStepService.getAssignedSteps(userId, pageable);
    }

    @Override
    public Page<Step> getAllCampaignSteps(Pageable pageable) {
        return campaignStepService.getAllSteps(pageable);
    }
}

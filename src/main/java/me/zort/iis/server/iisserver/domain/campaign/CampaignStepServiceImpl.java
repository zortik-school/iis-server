package me.zort.iis.server.iisserver.domain.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CampaignStepServiceImpl implements CampaignStepService {
    private final CampaignStepRepository repository;

    @Override
    public Step addStep(AddCampaignStepArgs args) {
        Step step = new StepImpl(-1, args.getName(), args.getCampaignId(), false, null);

        step = repository.save(step);

        // TODO: event

        return step;
    }

    @Override
    public void deleteStep(long stepId) {
        if (!repository.existsById(stepId)) {
            throw new StepNotFoundException(stepId);
        }

        repository.deleteById(stepId);
    }

    @Override
    public void assignUserToStep(long stepId, @Nullable Long userId) {
        Step step = repository.findById(stepId).orElseThrow(() -> new StepNotFoundException(stepId));
        step.setAssignedUserId(userId);

        repository.save(step);

        // TODO: event
    }

    @Override
    public void activateStep(long stepId) {
        Step step = repository.findById(stepId).orElseThrow(() -> new StepNotFoundException(stepId));
        step.setActive(true);

        repository.setActiveFalseByCampaignId(step.getCampaignId());

        repository.save(step);

        // TODO: event
    }

    @Override
    public Optional<Step> getStep(long stepId) {
        return repository.findById(stepId);
    }

    @Override
    public Optional<Step> getActiveStep(long campaignId) {
        return repository.findByCampaignIdAndActiveTrue(campaignId);
    }

    @Override
    public List<Step> getAllStepsForCampaign(long campaignId) {
        return prepareStepsListing(repository.findAllByCampaignId(campaignId));
    }

    @Override
    public Page<Step> getAssignedSteps(long userId, Pageable pageable) {
        return repository.findAllByAssignedUserId(userId, pageable);
    }

    @Override
    public Page<Step> getAllSteps(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Prepare steps for listing.
     *
     * @param steps the stream of steps to be prepared
     * @return the list of prepared steps
     */
    private static List<Step> prepareStepsListing(Stream<Step> steps) {
        return steps
                .sorted(Comparator.comparingLong(Step::getId))
                .toList();
    }
}

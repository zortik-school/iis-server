package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.CampaignService;
import me.zort.iis.server.iisserver.domain.campaign.CreateCampaignArgs;
import me.zort.iis.server.iisserver.domain.campaign.event.CampaignCreatedEvent;
import me.zort.iis.server.iisserver.domain.campaign.event.CampaignDeletedEvent;
import me.zort.iis.server.iisserver.domain.campaign.event.CampaignUserAssignedEvent;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Campaign createCampaign(CreateCampaignArgs args) {
        Campaign campaign = new CampaignImpl(-1, args.getName(), null, args.getThemeId());

        campaign = repository.save(campaign);

        eventPublisher.publishEvent(new CampaignCreatedEvent(campaign.getId()));

        return campaign;
    }

    @Override
    public void deleteCampaign(long campaignId) {
        repository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException(campaignId));

        repository.deleteById(campaignId);

        eventPublisher.publishEvent(new CampaignDeletedEvent(campaignId));
    }

    @Override
    public Optional<Campaign> getCampaign(long campaignId) {
        return repository.findById(campaignId);
    }

    @Override
    public void assignUser(long campaignId, Long userId) {
        Campaign campaign = repository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException(campaignId));
        campaign.setAssignedUserId(userId);

        repository.save(campaign);

        if (userId != null) {
            eventPublisher.publishEvent(new CampaignUserAssignedEvent(campaignId, userId));
        }
    }

    @Override
    public Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable) {
        return repository.findAllByAssignedUserId(userId, pageable);
    }

    @Override
    public boolean isAnyCampaignOfThemeAssignedToUser(long themeId, long userId) {
        return repository.existsByAssignedUserIdAndThemeId(userId, themeId);
    }

    @Override
    public Page<Campaign> getAllCampaigns(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Campaign> getAllCampaignsForTheme(long themeId, Pageable pageable) {
        return repository.findAllByThemeId(themeId, pageable);
    }
}

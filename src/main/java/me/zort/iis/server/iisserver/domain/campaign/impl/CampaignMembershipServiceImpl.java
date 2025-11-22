package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembership;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembershipService;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserAlreadyInCampaignException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserNotInCampaignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignMembershipServiceImpl implements CampaignMembershipService {
    private final CampaignMembershipRepository repository;

    @Override
    public CampaignMembership addUserToCampaign(long campaignId, long userId) {
        if (repository.existsByCampaignIdAndUserId(campaignId, userId)) {
            throw new UserAlreadyInCampaignException();
        }

        CampaignMembership membership = new CampaignMembershipImpl(campaignId, userId);

        membership = repository.save(membership);

        // TODO: event

        return membership;
    }

    @Override
    public void removeUserFromCampaign(long campaignId, long userId) {
        if (!repository.existsByCampaignIdAndUserId(campaignId, userId)) {
            throw new UserNotInCampaignException();
        }

        repository.deleteByCampaignIdAndUserId(campaignId, userId);
    }

    @Override
    public List<CampaignMembership> getCampaignMembershipsOfUser(long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Page<CampaignMembership> getCampaignMembershipsOfCampaign(long campaignId, Pageable pageable) {
        return repository.findAllByCampaignId(campaignId, pageable);
    }
}

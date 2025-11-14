package me.zort.iis.server.iisserver.domain.campaign;

import java.util.List;

public interface CampaignService {

    // TODO

    List<Campaign> getAssignedCampaigns(long userId);

    List<Campaign> getAllCampaigns();
}

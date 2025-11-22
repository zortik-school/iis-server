package me.zort.iis.server.iisserver.domain.campaign;

public interface CampaignMembership {

    /**
     * The campaign ID.
     *
     * @return ID of the campaign associated with this membership.
     */
    long getCampaignId();

    /**
     * The user ID.
     *
     * @return ID of the user associated with this membership.
     */
    long getUserId();
}

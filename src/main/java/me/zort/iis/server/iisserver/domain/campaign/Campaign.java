package me.zort.iis.server.iisserver.domain.campaign;

public interface Campaign extends Managed {

    /**
     * The unique identifier of the campaign.
     *
     * @return ID of the campaign.
     */
    long getId();

    /**
     * The name of the campaign.
     *
     * @return Name of the campaign.
     */
    String getName();

    long getThemeId();
}

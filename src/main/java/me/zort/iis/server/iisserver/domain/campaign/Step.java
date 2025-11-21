package me.zort.iis.server.iisserver.domain.campaign;

public interface Step extends Managed {

    /**
     * The unique identifier of the step.
     *
     * @return ID of the step.
     */
    long getId();

    /**
     * The name of the step.
     *
     * @return Name of the step.
     */
    String getName();

    /**
     * The ID of the campaign associated with the step.
     *
     * @return ID of the campaign.
     */
    long getCampaignId();

    /**
     * Sets the active status of the step.
     *
     * @param active true to set the step as active, false otherwise.
     */
    void setActive(boolean active);

    /**
     * Indicates whether the step is currently active.
     *
     * @return true if the step is active, false otherwise.
     */
    boolean isActive();
}

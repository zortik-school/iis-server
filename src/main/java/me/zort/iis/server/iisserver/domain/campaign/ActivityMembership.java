package me.zort.iis.server.iisserver.domain.campaign;

public interface ActivityMembership {

    /**
     * Gets the ID of the activity associated with this membership.
     *
     * @return activity ID
     */
    long getActivityId();

    /**
     * Gets the ID of the user associated with this activity membership.
     *
     * @return user ID
     */
    long getUserId();
}

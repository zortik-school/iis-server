package me.zort.iis.server.iisserver.domain.campaign;

import java.util.Date;

public interface Activity {

    /**
     * The unique ID of the activity.
     *
     * @return activity ID
     */
    long getId();

    /**
     * The name of the activity.
     *
     * @return name
     */
    String getName();

    /**
     * The description of the activity.
     *
     * @return description
     */
    String getDescription();

    /**
     * The step ID this activity belongs to.
     *
     * @return step ID
     */
    long getStepId();

    /**
     * The start date of the activity.
     *
     * @return start date
     */
    long getStartDate();

    /**
     * The end date of the activity.
     *
     * @return end date
     */
    long getEndDate();

    /**
     * Sets the current state of the activity.
     *
     * @param state activity state
     */
    void setState(ActivityState state);

    /**
     * The current state of the activity.
     *
     * @return activity state
     */
    ActivityState getState();
}

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
    Date getStartDate();

    /**
     * The end date of the activity.
     *
     * @return end date
     */
    Date getEndDate();

    /**
     * The current state of the activity.
     *
     * @return activity state
     */
    ActivityState getState();
}

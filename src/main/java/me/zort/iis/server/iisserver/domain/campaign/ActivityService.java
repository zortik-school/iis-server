package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.InvalidActivityStateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    /**
     * Creates a new activity based on the provided arguments.
     *
     * @param args The arguments required to create the activity.
     * @return The newly created activity.
     */
    Activity createActivity(CreateActivityArgs args);

    /**
     * Deletes an activity by its ID.
     *
     * @param activityId The ID of the activity to be deleted.
     * @throws ActivityNotFoundException if the activity with the given ID does not exist.
     */
    void deleteActivity(long activityId);

    /**
     * Sets the state of an activity.
     *
     * @param activityId The ID of the activity to update.
     * @param state The new state to set for the activity.
     * @throws ActivityNotFoundException if the activity with the given ID does not exist.
     * @throws InvalidActivityStateException if the state transition is invalid.
     */
    void setActivityState(long activityId, ActivityState state);

    /**
     * Retrieves an activity by its unique ID.
     *
     * @param activityId The ID of the activity to retrieve.
     * @return An Optional containing the activity if found, or empty if not found.
     */
    Optional<Activity> getActivity(long activityId);

    /**
     * Retrieves a paginated list of activities associated with the specified step ID.
     *
     * @param stepId   The ID of the step to filter activities.
     * @param pageable Pagination information.
     * @return A paginated list of activities for the given step ID.
     */
    Page<Activity> getActivitiesForStep(long stepId, Pageable pageable);

    /**
     * Retrieves a paginated list of activities available.
     * By available, it is meant that the activities are open for participation.
     *
     * @param userId The ID of the user for whom to retrieve available activities.
     * @param campaignIds List of campaign IDs to filter activities.
     * @param pageable    Pagination information.
     * @return A paginated list of activities available for the given campaign IDs.
     */
    Page<Activity> getAvailableActivitiesForCampaigns(long userId, List<Long> campaignIds, Pageable pageable);

    /**
     * Checks if a specific activity belongs to a given campaign.
     *
     * @param activityId The ID of the activity to check.
     * @param campaignId The ID of the campaign to check against.
     * @return true if the activity belongs to the campaign, false otherwise.
     */
    boolean isActivityOfCampaign(long activityId, long campaignId);

    /**
     * Retrieves a paginated list of all activities.
     *
     * @param pageable Pagination information.
     * @return A paginated list of all activities.
     */
    Page<Activity> getAllActivities(Pageable pageable);
}

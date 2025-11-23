package me.zort.iis.server.iisserver.app.activity;

import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.InvalidActivityStateException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserAlreadyInActivityException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserNotInActivityException;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityFacade {

    /**
     * Creates a new activity based on the provided arguments.
     *
     * @param args the arguments required to create the activity
     * @return the created activity
     */
    Activity createActivity(CreateActivityArgs args);

    /**
     * Deletes an activity by its ID.
     *
     * @param activityId the ID of the activity to be deleted
     * @throws ActivityNotFoundException if the activity does not exist
     */
    void deleteActivity(long activityId);

    /**
     * Retrieves an activity by its ID.
     *
     * @param activityId the ID of the activity to be retrieved
     * @return the activity with the specified ID
     * @throws ActivityNotFoundException if the activity does not exist
     */
    Activity getActivity(long activityId);

    /**
     * Opens an activity by its ID.
     *
     * @param activityId the ID of the activity to be opened
     * @throws InvalidActivityStateException if the activity cannot be opened
     */
    void openActivity(long activityId);

    /**
     * Closes an activity by its ID with the specified options.
     *
     * @param activityId the ID of the activity to be closed
     * @param options the options for closing the activity
     * @throws InvalidActivityStateException if the activity cannot be closed
     */
    void closeActivity(long activityId, CloseActivityOptions options);

    /**
     * Adds a user to a specific activity.
     *
     * @param activityId the ID of the activity
     * @param userId the ID of the user to be added
     * @throws UserAlreadyInActivityException if the user is already a member of the activity
     * @throws ActivityNotFoundException if the activity does not exist
     */
    void addUserToActivity(long activityId, long userId);

    /**
     * Removes a user from a specific activity.
     *
     * @param activityId the ID of the activity
     * @param userId the ID of the user to be removed
     * @throws UserNotInActivityException if the user is not a member of the activity
     * @throws ActivityNotFoundException if the activity does not exist
     */
    void removeUserFromActivity(long activityId, long userId);

    /**
     * Retrieves a paginated list of activities for a specific step.
     *
     * @param stepId the ID of the step for which to retrieve activities
     * @param pageable pagination information
     * @return a page of activities for the specified step
     */
    Page<Activity> getActivitiesForStep(long stepId, Pageable pageable);

    /**
     * Retrieves a paginated list of activities available to a specific user.
     *
     * @param userId the ID of the user for whom to retrieve available activities
     * @param pageable pagination information
     * @return a page of activities available to the user
     * @throws UserNotFoundException if the user does not exist
     */
    Page<Activity> getAvailableActivities(long userId, Pageable pageable);

    /**
     * Retrieves a paginated list of activities assigned to a specific user.
     *
     * @param userId the ID of the user for whom to retrieve assigned activities
     * @param pageable pagination information
     * @return a page of activities assigned to the user
     * @throws UserNotFoundException if the user does not exist
     */
    Page<Activity> getAssignedActivities(long userId, Pageable pageable);

    /**
     * Retrieves a paginated list of users who are members of a specific activity.
     *
     * @param activityId the ID of the activity
     * @param pageable pagination information
     * @return a page of users who are members of the activity
     * @throws ActivityNotFoundException if the activity does not exist
     */
    Page<User> getMembersOfActivity(long activityId, Pageable pageable);

    /**
     * Retrieves a paginated list of all activities.
     *
     * @param pageable pagination information
     * @return a page of all activities
     */
    Page<Activity> getAllActivities(Pageable pageable);
}

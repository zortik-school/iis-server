package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.UserAlreadyInActivityException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserNotInActivityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityMembershipService {

    /**
     * Adds a user as a member to a specific activity.
     *
     * @param activityId The ID of the activity.
     * @param userId The ID of the user.
     * @return The created ActivityMembership object.
     * @throws UserAlreadyInActivityException if the user is already a member of the activity.
     */
    ActivityMembership addMemberToActivity(long activityId, long userId);

    /**
     * Removes a user from a specific activity.
     *
     * @param activityId The ID of the activity.
     * @param userId The ID of the user.
     * @throws UserNotInActivityException if the user is not a member of the activity.
     */
    void removeMemberFromActivity(long activityId, long userId);

    /**
     * Checks if a user is a member of a specific activity.
     *
     * @param activityId The ID of the activity.
     * @param userId The ID of the user.
     * @return true if the user is a member of the activity, false otherwise.
     */
    boolean isMemberOfActivity(long activityId, long userId);

    /**
     * Retrieves all activity memberships for a specific user.
     *
     * @param userId The ID of the user.
     * @param pageable Pagination information.
     * @return A list of ActivityMembership objects for the user.
     */
    Page<ActivityMembership> getMembershipsForUser(long userId, Pageable pageable);

    /**
     * Retrieves all activity memberships for a specific activity.
     *
     * @param activityId The ID of the activity.
     * @param pageable Pagination information.
     * @return A page of ActivityMembership objects for the activity.
     */
    Page<ActivityMembership> getMembershipsForActivity(long activityId, Pageable pageable);
}

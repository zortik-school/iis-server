package me.zort.iis.server.iisserver.domain.campaign.impl;

import me.zort.iis.server.iisserver.domain.campaign.ActivityMembership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityMembershipRepository {

    /**
     * Saves the given activity membership.
     *
     * @param membership the activity membership to save
     * @return the saved activity membership
     */
    ActivityMembership save(ActivityMembership membership);

    /**
     * Deletes the activity membership by activity ID and user ID.
     *
     * @param activityId the ID of the activity
     * @param userId  the ID of the user
     */
    void deleteByActivityIdAndUserId(long activityId, long userId);

    /**
     * Checks if a membership exists for the given activity ID and user ID.
     *
     * @param activityId the ID of the activity
     * @param userId the ID of the user
     * @return true if a membership exists, false otherwise
     */
    boolean existsByActivityIdAndUserId(long activityId, long userId);

    /**
     * Finds all activity memberships by activity ID with pagination.
     *
     * @param activityId the ID of the activity
     * @param pageable pagination information
     * @return a page of activity memberships for the given activity ID
     */
    Page<ActivityMembership> findAllByActivityId(long activityId, Pageable pageable);

    /**
     * Finds all activity memberships by user ID with pagination.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a page of activity memberships for the given user ID
     */
    Page<ActivityMembership> findAllByUserId(long userId, Pageable pageable);
}

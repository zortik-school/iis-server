package me.zort.iis.server.iisserver.domain.campaign.impl;

import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {

    /**
     * Saves an activity to the repository.
     *
     * @param activity the activity to be saved
     * @return the saved activity
     */
    Activity save(Activity activity);

    /**
     * Deletes an activity from the repository by its ID.
     *
     * @param id the ID of the activity to be deleted
     */
    void deleteById(Long id);

    /**
     * Finds an activity by its ID.
     *
     * @param id the ID of the activity to be found
     * @return an Optional containing the found activity, or empty if not found
     */
    Optional<Activity> findById(Long id);

    /**
     * Finds all activities associated with a specific step ID, with pagination.
     *
     * @param stepId   the ID of the step
     * @param pageable pagination information
     * @return a paginated list of activities for the given step ID
     */
    Page<Activity> findAllByStepId(Long stepId, Pageable pageable);

    /**
     * Finds all activities by state and within a specific date range, with pagination.
     *
     * @param state the state of the activities to be found
     * @param startDate the lower bound of the start date
     * @param endDate the upper bound of the end date
     * @param pageable pagination information
     * @return a paginated list of activities matching the criteria
     */
    Page<Activity> findAllByStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            ActivityState state, long startDate, long endDate, Pageable pageable);

    /**
     * Finds all activities associated with a list of step IDs, filtered by state and date range, with pagination.
     *
     * @param stepIds  the list of step IDs
     * @param state    the state of the activities to be found
     * @param startDate the lower bound of the start date
     * @param endDate the upper bound of the end date
     * @param pageable pagination information
     * @return a paginated list of activities matching the criteria
     */
    Page<Activity> findAllByStepIdInAndStateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            List<Long> stepIds, ActivityState state, long startDate, long endDate, Pageable pageable);

    /**
     * Finds all activities with pagination.
     *
     * @param pageable pagination information
     * @return a paginated list of all activities
     */
    Page<Activity> findAll(Pageable pageable);
}

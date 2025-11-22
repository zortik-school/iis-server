package me.zort.iis.server.iisserver.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.activity.*;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.activity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivitiesController {
    private final OperationExecutor operationExecutor;

    @GetMapping
    public PageResponse<ActivityModel> getActivities(
            @RequestParam(value = "assigned", required = false) Boolean assigned,
            @RequestParam(value = "available", required = false) Boolean available, Pageable pageable) {
        Page<Activity> page;
        if (assigned != null) {
            page = operationExecutor.dispatch(new GetAssignedActivitiesOp(pageable));
        } else if (available != null) {
            page = operationExecutor.dispatch(new GetAvailableActivitiesOp(pageable));
        } else {
            page = operationExecutor.dispatch(new GetActivitiesOp(pageable));
        }

        return PageResponse.fromPage(page, ActivityModel::new);
    }

    @PostMapping
    public ActivityModel createActivity(@Valid @RequestBody CreateActivityRequest body) {
        Activity activity = operationExecutor.dispatch(new CreateActivityOp(
                body.getName(), body.getDescription(), body.getStepId(), body.getStartDate(), body.getEndDate()));

        return new ActivityModel(activity);
    }

    @DeleteMapping("/{id}")
    public BlankResponse deleteActivity(@PathVariable long id) {
        operationExecutor.dispatch(new DeleteActivityOp(id));

        return BlankResponse.getInstance();
    }

    @PostMapping("/{id}/adduser")
    public BlankResponse addUserToActivity(@PathVariable long id, @Valid @RequestBody AddUserToActivityRequest body) {
        operationExecutor.dispatch(new AddUserToActivityOp(id, body.getUserId()));

        return BlankResponse.getInstance();
    }

    @PostMapping("/{id}/removeuser")
    public BlankResponse removeUserFromActivity(
            @PathVariable long id, @Valid @RequestBody RemoveUserFromActivityRequest body) {
        operationExecutor.dispatch(new RemoveUserFromActivityOp(id, body.getUserId()));

        return BlankResponse.getInstance();
    }

    @PostMapping("/{id}/close")
    public BlankResponse closeActivity(
            @PathVariable long id,
            @Valid @RequestBody CloseActivityRequest body, @AuthenticationPrincipal User user) {
        operationExecutor.dispatch(new CloseActivityOp(id, body.toActivityNoteInput(user.getId())));

        return BlankResponse.getInstance();
    }
}

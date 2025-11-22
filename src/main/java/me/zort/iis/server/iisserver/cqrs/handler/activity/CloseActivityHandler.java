package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.access.ActivityAccessRole;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.activity.CloseActivityOptions;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.CloseActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.ActivityNoteInput;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CloseActivityHandler extends CommandHandler<CloseActivityOp> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(CloseActivityOp operation) {
        ActivityAccessRole accessRole = accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser());

        boolean rejected = false;

        ActivityNoteInput note = operation.getNote();
        if (note == null && !accessRole.canCloseForcibly()) {
            // No note provided and user cannot close forcibly
            rejected = true;
        } else if (note != null && !accessRole.canCloseWithNote()) {
            // Note provided but user cannot close with note
            rejected = true;
        }
        if (rejected) {
            throw new NoAccessException("User does not have permission to close this activity with the provided options.");
        }

        CloseActivityOptions options = CloseActivityOptions.builder()
                .note(note)
                .build();
        activityFacade.closeActivity(operation.getActivityId(), options);
    }

    @Override
    public Class<CloseActivityOp> getOperationType() {
        return CloseActivityOp.class;
    }
}

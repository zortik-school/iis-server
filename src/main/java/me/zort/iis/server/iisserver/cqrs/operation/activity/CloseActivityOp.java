package me.zort.iis.server.iisserver.cqrs.operation.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;
import me.zort.iis.server.iisserver.domain.campaign.ActivityNoteInput;
import org.jetbrains.annotations.Nullable;

@Getter
@AllArgsConstructor
public class CloseActivityOp implements Command {
    private final long activityId;
    @Nullable
    private final ActivityNoteInput note;

}

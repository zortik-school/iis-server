package me.zort.iis.server.iisserver.app.activity;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.ActivityNoteInput;

@Getter
@Builder
public class CloseActivityOptions {
    @Builder.Default
    private ActivityNoteInput note = null;

}

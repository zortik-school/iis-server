package me.zort.iis.server.iisserver.domain.campaign;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ActivityNoteInput {
    private final long authorId;
    private final String content;

}

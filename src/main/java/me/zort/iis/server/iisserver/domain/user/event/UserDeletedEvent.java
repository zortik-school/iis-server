package me.zort.iis.server.iisserver.domain.user.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeletedEvent {
    private final long userId;

}

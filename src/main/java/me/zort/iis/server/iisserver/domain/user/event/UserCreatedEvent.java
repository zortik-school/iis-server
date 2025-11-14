package me.zort.iis.server.iisserver.domain.user.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.user.User;

@Getter
@AllArgsConstructor
public class UserCreatedEvent {
    private final User user;

}

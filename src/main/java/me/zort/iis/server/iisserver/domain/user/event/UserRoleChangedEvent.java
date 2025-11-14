package me.zort.iis.server.iisserver.domain.user.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;

@Getter
@AllArgsConstructor
public class UserRoleChangedEvent {
    private final User user;
    private final Role oldRole;
    private final Role newRole;

}

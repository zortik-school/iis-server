package me.zort.iis.server.iisserver.http.model.user;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;

import java.util.List;

@Getter
public class IdentityResponse {
    private final long id;
    private final String username;
    private final String name;
    private final String role;

    private final List<Privilege> privileges;

    public IdentityResponse(User user, List<Privilege> privileges) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole().name();

        this.privileges = privileges;
    }
}

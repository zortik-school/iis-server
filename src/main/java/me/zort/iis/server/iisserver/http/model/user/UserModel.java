package me.zort.iis.server.iisserver.http.model.user;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.user.User;

@Getter
public class UserModel {
    private final long id;
    private final String username;
    private final String name;
    private final String role;

    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole().name();
    }
}

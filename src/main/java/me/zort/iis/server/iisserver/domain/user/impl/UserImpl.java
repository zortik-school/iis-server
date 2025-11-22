package me.zort.iis.server.iisserver.domain.user.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;

@Data
@AllArgsConstructor
public class UserImpl implements User {
    private final long id;
    private final String username;
    private String passwordHash;
    private final String name;
    private Role role;

}

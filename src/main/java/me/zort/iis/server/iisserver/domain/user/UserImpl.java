package me.zort.iis.server.iisserver.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserImpl implements User {
    private final long id;
    private final String username;
    private String passwordHash;
    private final String name;
    private final Role role;

}

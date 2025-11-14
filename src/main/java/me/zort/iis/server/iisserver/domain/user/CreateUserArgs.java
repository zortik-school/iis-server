package me.zort.iis.server.iisserver.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserArgs {
    private String username;
    private String name;

    @Builder.Default
    private String passwordHash = null;

    @Builder.Default
    private Role role = Role.USER;

}

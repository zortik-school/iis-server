package me.zort.iis.server.iisserver.app.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginArgs {
    private final String username;
    private final String password;

}

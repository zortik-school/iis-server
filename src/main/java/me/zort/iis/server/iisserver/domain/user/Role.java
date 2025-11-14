package me.zort.iis.server.iisserver.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}

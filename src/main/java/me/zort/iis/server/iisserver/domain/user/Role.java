package me.zort.iis.server.iisserver.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER(null),

    ADMIN(USER);

    @Getter(onMethod_ = @Nullable)
    private final Role inheritsFrom;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

    /**
     * Get this role and all roles it inherits from, in order from this role to the topmost inherited role.
     *
     * @return List of roles including this role and all inherited roles.
     */
    public List<Role> getInheritedRoles() {
        List<Role> roles = new ArrayList<>();

        Role current = this;
        while (current != null) {
            roles.add(current);

            current = current.getInheritsFrom();
        }

        return roles;
    }
}

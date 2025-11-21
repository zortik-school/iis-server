package me.zort.iis.server.iisserver.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER(null, List.of()),

    STEP_MANAGER(USER, List.of(Privilege.VIEW_ASSIGNED_STEPS)),

    CAMPAIGN_MANAGER(STEP_MANAGER, List.of(Privilege.VIEW_ASSIGNED_CAMPAIGNS)),

    ADMIN(CAMPAIGN_MANAGER, List.of(Privilege.MANAGE_USERS, Privilege.MANAGE_THEMES, Privilege.MANAGE_CAMPAIGNS, Privilege.MANAGE_STEPS));

    @Getter(onMethod_ = @Nullable)
    private final Role inheritsFrom;
    /**
     * Privileges that are unique to this role (not inherited).
     */
    private final List<Privilege> uniquePrivileges;

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

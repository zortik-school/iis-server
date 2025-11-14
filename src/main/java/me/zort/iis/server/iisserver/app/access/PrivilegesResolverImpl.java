package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesResolverImpl implements PrivilegesResolver {

    @Override
    public List<Privilege> getGrantedPrivileges(User user) {
        return user.getRole().getInheritedRoles()
                .stream()
                .flatMap(role -> getUniquePrivilegesForRole(role).stream())
                .distinct()
                .toList();
    }

    /**
     * Get privileges for specific role.
     *
     * @param role the role
     * @return list of privileges
     */
    private static List<Privilege> getUniquePrivilegesForRole(Role role) {
        if (role == Role.ADMIN) {
            return List.of(Privilege.MANAGE_USERS);
        } else {
            return List.of();
        }
    }
}

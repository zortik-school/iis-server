package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesResolverImpl implements PrivilegesResolver {

    @Override
    public List<Privilege> getGrantedPrivileges(User user) {
        return user.getRole().getInheritedRoles()
                .stream()
                .flatMap(role -> role.getUniquePrivileges().stream())
                .distinct()
                .toList();
    }
}

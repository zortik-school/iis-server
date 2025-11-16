package me.zort.iis.server.iisserver.app.access;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessStrategyImpl implements AccessStrategy {
    private final PrivilegesResolver privilegesResolver;

    @Override
    public boolean canViewTheme(long themeId, User user) {
        if (privilegesResolver.getGrantedPrivileges(user).contains(Privilege.MANAGE_THEMES)) {
            return true;
        }

        // TODO: is part of any campaign of this theme

        return false;
    }
}

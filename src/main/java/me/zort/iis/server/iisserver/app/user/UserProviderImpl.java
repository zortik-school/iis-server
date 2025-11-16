package me.zort.iis.server.iisserver.app.user;

import me.zort.iis.server.iisserver.app.user.exception.NotAuthenticatedException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProviderImpl implements UserProvider {

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthenticatedException();
        }

        if (!(authentication.getPrincipal() instanceof User user)) {
            throw new IllegalStateException("Authentication principal is not of type User");
        }

        return user;
    }
}

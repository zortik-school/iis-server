package me.zort.iis.server.iisserver.app.user;

import me.zort.iis.server.iisserver.app.user.exception.NotAuthenticatedException;
import me.zort.iis.server.iisserver.domain.user.User;

public interface UserProvider {

    /**
     * Returns the currently authenticated user.
     *
     * @return the current User
     * @throws NotAuthenticatedException if no user is authenticated
     */
    User getCurrentUser();
}

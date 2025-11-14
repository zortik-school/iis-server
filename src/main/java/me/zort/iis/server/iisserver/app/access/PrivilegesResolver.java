package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;

import java.util.List;

public interface PrivilegesResolver {

    /**
     * Retrieves the list of privileges granted to the specified user.
     *
     * @param user the user whose privileges are to be retrieved
     * @return a list of privileges granted to the user
     * @throws UserNotFoundException if the user does not exist
     */
    List<Privilege> getGrantedPrivileges(User user);
}

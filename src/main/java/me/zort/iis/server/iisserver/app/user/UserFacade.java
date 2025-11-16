package me.zort.iis.server.iisserver.app.user;

import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFacade {

    /**
     * Sets the role of a user.
     *
     * @param userId the ID of the user whose role is to be set
     * @param role the new role to be assigned to the user
     * @throws UserNotFoundException If the user with the given ID does not exist
     */
    void setUserRole(long userId, Role role);

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to be deleted
     * @throws UserNotFoundException If the user with the given ID does not exist
     */
    void deleteUser(long userId);

    /**
     * Retrieves a paginated list of users.
     *
     * @param pageable pagination information
     * @return a page of users
     */
    Page<User> getUsers(Pageable pageable);
}

package me.zort.iis.server.iisserver.domain.user;

import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;

import java.util.Optional;

public interface UserService {

    /**
     * Creates a new user with the provided arguments.
     *
     * @param args the arguments required to create a user
     * @return the created User object
     * @throws UserConflictException If a conflict occurs (e.g., username already exists)
     */
    User createUser(CreateUserArgs args);

    void deleteUser(long id);

    Optional<User> getUser(long id);

    Optional<User> getUserByUsername(String username);

    /**
     * Sets the role of a user identified by the given ID.
     *
     * @param id the ID of the user
     * @param role the new role to assign to the user
     * @throws UserNotFoundException If no user with the given ID exists
     */
    void setUserRole(long id, Role role);
}

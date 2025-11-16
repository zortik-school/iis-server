package me.zort.iis.server.iisserver.domain.user;

import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Deletes a user identified by the given ID.
     *
     * @param id the unique ID of the user to delete
     */
    void deleteUser(long id);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the unique ID of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    Optional<User> getUser(long id);

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    Optional<User> getUserByUsername(String username);

    /**
     * Sets the role of a user identified by the given ID.
     *
     * @param id the ID of the user
     * @param role the new role to assign to the user
     * @throws UserNotFoundException If no user with the given ID exists
     */
    void setUserRole(long id, Role role);

    /**
     * Retrieves a paginated list of users.
     *
     * @param pageable pagination information
     * @return a page of users
     */
    Page<User> getUsers(Pageable pageable);
}

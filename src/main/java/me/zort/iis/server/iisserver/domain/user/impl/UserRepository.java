package me.zort.iis.server.iisserver.domain.user.impl;

import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

    /**
     * Saves a user to the repository.
     *
     * @param user the user to save
     * @return the saved user
     * @throws UserConflictException If a user with the same username already exists
     */
    User save(User user);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the found user, or empty if not found
     */
    Optional<User> findById(Long id);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the found user, or empty if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(Long id);

    /**
     * Finds all users with pagination.
     *
     * @param pageable pagination information
     * @return a page of users
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Finds all users whose names contain the specified substring, with pagination.
     *
     * @param name the substring to search for in names
     * @param pageable pagination information
     * @return a page of users whose names contain the specified substring
     */
    Page<User> findAllByNameContaining(String name, Pageable pageable);
}

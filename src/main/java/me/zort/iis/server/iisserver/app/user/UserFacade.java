package me.zort.iis.server.iisserver.app.user;

public interface UserFacade {

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to be deleted
     */
    void deleteUser(long userId);
}

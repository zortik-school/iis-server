package me.zort.iis.server.iisserver.domain.user;

public interface User {

    /**
     * Sets the user's password hash.
     *
     * @param passwordHash the password hash to set
     */
    void setPasswordHash(String passwordHash);

    /**
     * User's unique identifier.
     *
     * @return Unique ID of the user.
     */
    long getId();

    /**
     * User's username.
     *
     * @return Username of the user.
     */
    String getUsername();

    /**
     * User's password hash.
     *
     * @return Password hash of the user.
     */
    String getPasswordHash();

    /**
     * User's name.
     *
     * @return Name of the user.
     */
    String getName();

    /**
     * User's role.
     *
     * @return Role of the user.
     */
    Role getRole();
}

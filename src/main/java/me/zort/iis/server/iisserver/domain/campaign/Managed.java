package me.zort.iis.server.iisserver.domain.campaign;

public interface Managed {

    /**
     * Gets the ID of the user assigned to manage this entity.
     *
     * @return the assigned user's ID
     */
    long getAssignedUserId();
}

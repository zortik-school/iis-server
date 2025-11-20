package me.zort.iis.server.iisserver.domain.campaign;

import org.jetbrains.annotations.Nullable;

public interface Managed {

    /**
     * Sets the ID of the user assigned to manage this entity.
     *
     * @param userId the assigned user's ID
     */
    void setAssignedUserId(@Nullable Long userId);

    /**
     * Gets the ID of the user assigned to manage this entity.
     *
     * @return the assigned user's ID
     */
    @Nullable
    Long getAssignedUserId();
}

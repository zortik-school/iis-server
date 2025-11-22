package me.zort.iis.server.iisserver.data;

import org.jetbrains.annotations.Nullable;

public interface IdAdjustmentStrategy<ID> {

    /**
     * Adjusts the supplied ID before inserting it into the database.
     *
     * @param suppliedId the ID to be adjusted
     * @return the adjusted ID
     */
    ID adjustIdBeforeInsert(@Nullable ID suppliedId);
}

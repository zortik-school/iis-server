package me.zort.iis.server.iisserver.data;

public interface IdAdjustmentStrategy {

    /**
     * Adjusts the supplied ID before inserting it into the database.
     *
     * @param suppliedId the ID to be adjusted
     * @return the adjusted ID
     */
    Long adjustIdBeforeInsert(long suppliedId);
}

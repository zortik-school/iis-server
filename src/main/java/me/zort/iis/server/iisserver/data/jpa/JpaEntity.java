package me.zort.iis.server.iisserver.data.jpa;

public interface JpaEntity<ID> {

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id the ID to set
     */
    void setId(ID id);

    /**
     * Gets the unique identifier of the entity.
     *
     * @return the ID of the entity
     */
    ID getId();
}

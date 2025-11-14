package me.zort.iis.server.iisserver.data.jpa;

/**
 * Generic JPA mapper interface.
 *
 * @param <T> the domain model type
 * @param <E> the JPA entity type
 */
public interface JpaMapper<T, E> {

    /**
     * Maps a JPA entity to a domain model.
     *
     * @param entity the JPA entity
     * @return the corresponding domain model
     */
    T toDomain(E entity);

    /**
     * Maps a domain model to a JPA entity.
     *
     * @param domain the domain model
     * @return the corresponding JPA entity
     */
    E toEntity(T domain);
}

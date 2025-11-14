package me.zort.iis.server.iisserver.data.jpa;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Generic CRUD repository for JPA entities.
 *
 * @param <T> domain model type
 * @param <E> JPA entity type
 * @param <ID> identifier type
 */
@RequiredArgsConstructor
public class JpaCrudRepository<T, E extends JpaEntity<ID>, ID> {
    private final CrudRepository<E, ID> repository;
    private final JpaMapper<T, E> mapper;
    private final IdAdjustmentStrategy idAdjustmentStrategy;

    /**
     * Saves the given domain model.
     *
     * @param domainModel the domain model to save
     * @return the saved domain model
     */
    @SuppressWarnings("unchecked") // Type safe
    public T save(T domainModel) {
        E entity = mapper.toEntity(domainModel);

        if (entity.getId() instanceof Long id) {
            Long adjustedId = idAdjustmentStrategy.adjustIdBeforeInsert(id);

            if (!Objects.equals(adjustedId, id)) {
                ((JpaEntity<Long>) entity).setId(adjustedId);
            }
        }

        entity = repository.save(entity);

        return mapper.toDomain(entity);
    }

    /**
     * Finds a domain model by its identifier.
     *
     * @param id the identifier
     * @return an Optional containing the found domain model, or empty if not found
     */
    public Optional<T> findById(ID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    /**
     * Deletes a domain model by its identifier.
     *
     * @param id the identifier
     */
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    /**
     * Checks if a domain model exists by its identifier.
     *
     * @param id the identifier
     * @return true if exists, false otherwise
     */
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    /**
     * Finds all domain models.
     *
     * @return list of all domain models
     */
    public List<T> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .toList();
    }
}

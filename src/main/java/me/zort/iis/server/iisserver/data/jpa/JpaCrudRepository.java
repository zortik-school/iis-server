package me.zort.iis.server.iisserver.data.jpa;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Generic CRUD repository for JPA entities.
 *
 * @param <T> domain model type
 * @param <E> JPA entity type
 * @param <ID> identifier type
 */
@RequiredArgsConstructor
public class JpaCrudRepository<T, E extends JpaEntity<ID>, ID> {
    private final JpaRepository<E, ID> repository;
    private final JpaMapper<T, E> mapper;
    private final IdAdjustmentStrategy<ID> idAdjustmentStrategy;

    /**
     * Saves the given domain model.
     *
     * @param domainModel the domain model to save
     * @return the saved domain model
     */
    public T save(T domainModel) {
        E entity = mapper.toEntity(domainModel);

        ID id = entity.getId();
        id = idAdjustmentStrategy.adjustIdBeforeInsert(id);

        entity.setId(id);

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
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    /**
     * Finds all domain models with pagination.
     *
     * @param pageable pagination information
     * @return a page of domain models
     */
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }
}

package org.epoch.data.repository;

import java.util.Optional;

import org.epoch.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository accessing database.
 *
 * @author Marshal
 * @since 2022/6/18
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#save(Object)
     */
    <S extends T> S saveOne(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
     * as the {@literal Iterable} passed as an argument.
     * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its entities is
     *                                  {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#saveAll(Iterable)
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#findById(Object)
     */
    Optional<T> findById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#existsById(Object)
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    Iterable<T> findAll();

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param ids must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     * {@literal ids}.
     * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#findById(Object)
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     * @see org.springframework.data.repository.CrudRepository#count()
     */
    long count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     * @see org.springframework.data.repository.CrudRepository#deleteById(Object)
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#delete(Object)
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
     * @see org.springframework.data.repository.CrudRepository#deleteAll(Iterable)
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the repository.
     *
     * @see org.springframework.data.repository.CrudRepository#deleteAll()
     */
    void deleteAll();


    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort
     * @return all entities sorted by the given options
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(Sort)
     */
    Iterable<T> findAll(Sort sort);

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(Pageable)
     */
    Page<T> findAll(Pageable pageable);


    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object and query object.
     *
     * @param pageable
     * @param query
     * @return a page of entities
     */
    <Q> Page<T> findAll(Pageable pageable, Q query);
}

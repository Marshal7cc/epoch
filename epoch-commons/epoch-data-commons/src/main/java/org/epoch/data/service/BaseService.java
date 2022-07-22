package org.epoch.data.service;

import java.util.List;

import org.epoch.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Marshal
 * @since 2022/7/20
 */
public interface BaseService<DOMAIN, ID> {

    /**
     * Saves a given domain object by repository.
     *
     * @param domain
     * @return
     */
    DOMAIN save(DOMAIN domain);


    /**
     * Saves all given domain objects by repository.
     *
     * @param domains
     * @return
     */
    List<DOMAIN> saveAll(List<DOMAIN> domains);


    /**
     * Retrieves an domain object by its id.
     *
     * @param id
     * @return
     */
    DOMAIN findById(ID id);

    /**
     * Returns whether an domain object with the given id exists.
     *
     * @param id
     * @return
     */
    boolean existsById(ID id);


    /**
     * Returns all domain objects of the type.
     *
     * @return
     */
    List<DOMAIN> findAll();

    /**
     * Returns all domain objects with the given IDs.
     *
     * @param ids
     * @return
     */
    List<DOMAIN> findAllById(List<ID> ids);


    /**
     * Returns all domain objects meeting the restriction provided in the {@code Query} object.
     *
     * @param query
     * @param <Q>
     * @return
     */
    <Q> List<DOMAIN> findAll(Q query);

    /**
     * Returns the number of domain objects available.
     *
     * @return the number of domain objects.
     */
    long count();

    /**
     * Deletes the domain object with the given id.
     *
     * @param id must not be {@literal null}.
     */
    void deleteById(ID id);

    /**
     * Deletes a given domain object.
     *
     * @param domain must not be {@literal null}.
     */
    void delete(DOMAIN domain);

    /**
     * Deletes the given domain objects.
     *
     * @param domains must not be {@literal null}. Must not contain {@literal null} elements.
     */
    void deleteAll(List<DOMAIN> domains);

    /**
     * Deletes all domain objects managed by the repository.
     */
    void deleteAll();

    /**
     * Returns a {@link Page} of domain objects meeting the paging restriction provided in the {@code Pageable} object and the {@code Query} object.
     *
     * @param pageable
     * @param query
     * @return a page of domain objects
     */
    <Q> Page<DOMAIN> findAll(Pageable pageable, Q query);
}

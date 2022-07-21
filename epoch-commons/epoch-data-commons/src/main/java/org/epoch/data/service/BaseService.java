package org.epoch.data.service;

import org.epoch.data.domain.Page;
import org.epoch.data.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

/**
 * @author Marshal
 * @since 2022/7/20
 */
public interface BaseService<D, T,ID> {


    @SuppressWarnings("rawtypes")
    BaseRepository<T,ID> getRepository();

    D save(D domain);


    D findById(ID id);


    void removeById(ID id);


    <Q> Page<D> findAll(Pageable pageable, Q query);
}

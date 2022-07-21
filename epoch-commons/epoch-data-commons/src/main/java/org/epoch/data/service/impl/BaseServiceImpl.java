package org.epoch.data.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.epoch.core.util.BaseConverter;
import org.epoch.data.domain.Page;
import org.epoch.data.repository.BaseRepository;
import org.epoch.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Pageable;

/**
 * @author Marshal
 * @since 2022/7/21
 */
public class BaseServiceImpl<R extends BaseRepository<T, ID>, D, T, ID> implements BaseService<D, T, ID> {
    protected final Class<D> dClass;
    protected final Class<T> tClass;

    @Autowired
    protected R repository;

    @SuppressWarnings("unchecked")
    protected BaseServiceImpl() {
        this.dClass = (Class<D>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseServiceImpl.class))[1];
        this.tClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseServiceImpl.class))[2];
    }

    @Override
    public BaseRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public D save(D domain) {
        T entity = BaseConverter.parseObject(domain, tClass);
        repository.saveOne(entity);
        return domain;
    }

    @Override
    public D findById(ID id) {
        Optional<T> optional = repository.findById(id);
        return optional.map(t -> BaseConverter.parseObject(t, dClass)).orElse(null);
    }

    @Override
    public void removeById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public <Q> Page<D> findAll(Pageable pageable, Q query) {
        Page<T> tPage = repository.findAll(pageable, query);
        return new Page<>(tPage.getPageInfo(), BaseConverter.parseArray(tPage.getContent(), dClass));
    }
}

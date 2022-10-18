package org.epoch.data.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.epoch.core.util.GenericTypeConverter;
import org.epoch.data.domain.Page;
import org.epoch.data.repository.BaseRepository;
import org.epoch.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @since 2022/7/21
 */
public class BaseServiceImpl<R extends BaseRepository<T, ID>, DO, T, ID extends Serializable> implements BaseService<DO, ID> {

    @Autowired
    protected R repository;

    protected final Class<T> entityClass;
    protected final Class<DO> domainClass;

    @SuppressWarnings("unchecked")
    protected BaseServiceImpl() {
        this.domainClass = (Class<DO>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseServiceImpl.class))[1];
        this.entityClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseServiceImpl.class))[2];
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DO save(DO domain) {
        T entity = GenericTypeConverter.parseObject(domain, entityClass);
        repository.saveOne(entity);
        return GenericTypeConverter.parseObject(entity, domainClass);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DO> saveAll(List<DO> domains) {
        List<T> entities = GenericTypeConverter.parseArray(domains, entityClass);
        repository.saveAll(entities);
        return GenericTypeConverter.parseArray(entities, domainClass);
    }

    @Override
    public DO findById(ID id) {
        return repository.findById(id).map(t -> GenericTypeConverter.parseObject(t, domainClass)).orElse(null);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<DO> findAll() {
        return GenericTypeConverter.parseArray(repository.findAll(), domainClass);
    }

    @Override
    public List<DO> findAllById(List<ID> ids) {
        return GenericTypeConverter.parseArray(repository.findAllById(ids), domainClass);
    }

    @Override
    public <Q> List<DO> findAll(Q query) {
        return GenericTypeConverter.parseArray(repository.findAll(query), domainClass);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DO domain) {
        repository.delete(GenericTypeConverter.parseObject(domain, entityClass));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<DO> domains) {
        repository.deleteAll(GenericTypeConverter.parseArray(domains, entityClass));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public <Q> Page<DO> findAll(Pageable pageable, Q query) {
        Page<T> tPage = repository.findAll(pageable, query);
        return new Page<>(tPage.getPageInfo(), GenericTypeConverter.parseArray(tPage.getContent(), domainClass));
    }
}

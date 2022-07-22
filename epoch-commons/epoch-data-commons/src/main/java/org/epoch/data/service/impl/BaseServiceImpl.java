package org.epoch.data.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
    public DO save(DO domain) {
        T entity = BaseConverter.parseObject(domain, entityClass);
        repository.saveOne(entity);
        return BaseConverter.parseObject(entity, domainClass);
    }

    @Override
    public List<DO> saveAll(List<DO> domains) {
        List<T> entities = BaseConverter.parseArray(domains, entityClass);
        repository.saveAll(entities);
        return BaseConverter.parseArray(entities, domainClass);
    }

    @Override
    public DO findById(ID id) {
        return repository.findById(id).map(t -> BaseConverter.parseObject(t, domainClass)).orElse(null);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<DO> findAll() {
        return BaseConverter.parseArray(repository.findAll(), domainClass);
    }

    @Override
    public List<DO> findAllById(List<ID> ids) {
        return BaseConverter.parseArray(repository.findAllById(ids), domainClass);
    }

    @Override
    public <Q> List<DO> findAll(Q query) {
        return BaseConverter.parseArray(repository.findAll(query), domainClass);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(DO domain) {
        repository.delete(BaseConverter.parseObject(domain, entityClass));
    }

    @Override
    public void deleteAll(List<DO> domains) {
        repository.deleteAll(BaseConverter.parseArray(domains, entityClass));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public <Q> Page<DO> findAll(Pageable pageable, Q query) {
        Page<T> tPage = repository.findAll(pageable, query);
        return new Page<>(tPage.getPageInfo(), BaseConverter.parseArray(tPage.getContent(), domainClass));
    }
}

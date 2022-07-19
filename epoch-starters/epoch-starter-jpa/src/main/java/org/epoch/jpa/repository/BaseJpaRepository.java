package org.epoch.jpa.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.epoch.core.util.TypeConverter;
import org.epoch.data.domain.Page;
import org.epoch.data.repository.BaseRepository;
import org.epoch.data.repository.query.QueryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marshal
 * @since 2022/7/8
 */
public class BaseJpaRepository<R extends JpaRepository<T, ID>, T, ID> implements BaseRepository<T, ID> {
    @Autowired
    protected R repository;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected BaseJpaRepository() {
        this.entityClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseRepository.class))[0];
    }

    @Override
    public <S extends T> S saveOne(S entity) {
        return repository.save(entity);
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public <Q> List<T> findAll(Q query) {
        if (Objects.isNull(query)) {
            return findAll();
        }
        return repository.findAll(Example.of(TypeConverter.parseObject(entityClass, query)));
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
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
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return QueryHelper.getPage(repository.findAll(PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize())));
    }

    @Override
    public <Q> Page<T> findAll(Pageable pageable, Q query) {
        return QueryHelper.getPage(repository.findAll(
                Example.of(TypeConverter.parseObject(entityClass, query)),
                PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()))
        );
    }
}

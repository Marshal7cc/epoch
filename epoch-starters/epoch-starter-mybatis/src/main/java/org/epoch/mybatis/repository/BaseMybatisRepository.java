package org.epoch.mybatis.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.epoch.core.convert.CommonConverter;
import org.epoch.data.domain.Page;
import org.epoch.data.repository.BaseRepository;
import org.epoch.mybatis.repository.query.QueryHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @since 2022/7/7
 */
@NoRepositoryBean
@SuppressWarnings("unchecked")
public class BaseMybatisRepository<R extends BaseMapper<T>, T, ID extends Serializable>
        extends ServiceImpl<R, T> implements BaseRepository<T, ID> {
    private final Class<T> entityClass;
    @Autowired
    protected R mapper;

    protected BaseMybatisRepository() {
        // Resolve entity class.
        this.entityClass = (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 1);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S saveOne(@NotNull S entity) {
        super.saveOrUpdate(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        Collection<T> entityList = Lists.newArrayList();
        CollectionUtils.addAll(entityList, entities.iterator());
        super.saveOrUpdateBatch(entityList);
        return entities;
    }

    @Override
    public Optional<T> findById(@NotNull ID id) {
        return Optional.ofNullable(super.getById(id));
    }

    @Override
    public boolean existsById(ID id) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        String keyProperty = tableInfo.getKeyProperty();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(keyProperty, id);

        return mapper.exists(queryWrapper);
    }

    @Override
    public Iterable<T> findAll() {
        return mapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        Collection<Serializable> idList = Lists.newArrayList();
        CollectionUtils.addAll(idList, ids.iterator());
        return mapper.selectBatchIds(idList);
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@NotNull ID id) {
        mapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotNull T entity) {
        mapper.deleteById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Iterable<? extends T> entities) {
        Collection<T> entityList = Lists.newArrayList();
        CollectionUtils.addAll(entityList, entities.iterator());
        super.removeBatchByIds(entityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        mapper.delete(Wrappers.emptyWrapper());
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        return mapper.selectList(QueryHelper.getQueryWrapper(sort));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> pageResponse = super.page(page, Wrappers.emptyWrapper());

        return QueryHelper.getPage(pageResponse);
    }

    @Override
    public <Q> Page<T> findAll(Pageable pageable, Q query) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageable.getPageNumber(), pageable.getPageSize());

        QueryWrapper<T> queryWrapper = Wrappers.query(CommonConverter.beanConvert(entityClass, query));
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> pageResponse = super.page(page, queryWrapper);

        return QueryHelper.getPage(pageResponse);
    }
}

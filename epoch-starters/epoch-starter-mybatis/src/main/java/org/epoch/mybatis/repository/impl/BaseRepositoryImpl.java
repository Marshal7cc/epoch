package org.epoch.mybatis.repository.impl;


import java.util.List;

import com.github.pagehelper.PageHelper;
import org.epoch.core.base.BaseConstants;
import org.epoch.core.exception.OptimisticLockException;
import org.epoch.mybatis.repository.BaseRepository;
import org.epoch.mybatis.util.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Marshal
 * @date 2018/1/7
 */
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<T> implements BaseRepository<T>, BaseConstants {

    @Autowired
    Mapper<T> mapper;

    @Override
    public List<T> select(int page, int size, T condition) {
        PageHelper.startPage(page, size);
        return mapper.select(condition);
    }

    @Override
    public List<T> select(T condition) {
        return mapper.select(condition);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectOne(T condition) {
        return mapper.selectOne(condition);
    }

    @Override
    public T selectByPrimaryKey(Object record) {
        return mapper.selectByPrimaryKey(record);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) throws OptimisticLockException {
        int updateRows = mapper.updateByPrimaryKey(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) throws OptimisticLockException {
        int updateRows = mapper.updateByPrimaryKeySelective(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int deleteByPrimaryKey(Object record) {
        return mapper.deleteByPrimaryKey(record);
    }

    @Override
    public int submit(T record) throws OptimisticLockException {
        if (EntityHelper.isPrimaryKeyNull(record)) {
            return this.insertSelective(record);
        } else {
            return this.updateByPrimaryKey(record);
        }
    }

    @Override
    public void batchSubmit(List<T> records) throws OptimisticLockException {
        for (T record : records) {
            submit(record);
        }
    }

    @Override
    public void batchDelete(Object[] keys) {
        for (Object primaryKey : keys) {
            this.deleteByPrimaryKey(primaryKey);
        }
    }

    @Override
    public void batchDelete(List<T> records) {
        records.forEach(item -> this.deleteByPrimaryKey(item));
    }


    /**
     * 检查数据库乐观锁
     *
     * @param updateRows
     * @throws OptimisticLockException
     */
    private void checkOptimisticLock(int updateRows) throws OptimisticLockException {
        if (updateRows < 1) {
            throw new OptimisticLockException();
        }
    }
}

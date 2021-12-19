package org.epoch.starter.mybatis.repository;

import java.util.List;

import org.epoch.core.exception.OptimisticLockException;
import org.epoch.starter.core.proxy.AopProxy;

/**
 * 基础资源库
 *
 * @param <T> domain
 * @author Marshal
 */
public interface BaseRepository<T> extends AopProxy<T>, ParallelOperations<T> {

    /**
     * 分页条件查询方法
     *
     * @param page      页码
     * @param size      数量
     * @param condition 条件
     * @return
     */
    List<T> select(int page, int size, T condition);

    /**
     * 条件查询方法
     *
     * @param condition 条件
     * @return
     */
    List<T> select(T condition);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据条件查找一条数据
     *
     * @param condition
     * @return
     */
    T selectOne(T condition);

    /**
     * 根据主键查找
     *
     * @param record
     * @return
     */
    T selectByPrimaryKey(Object record);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 更新记录
     *
     * @param record
     * @return
     * @throws OptimisticLockException
     */
    int updateByPrimaryKey(T record) throws OptimisticLockException;

    /**
     * 更新记录
     *
     * @param record
     * @return
     * @throws OptimisticLockException
     */
    int updateByPrimaryKeySelective(T record) throws OptimisticLockException;

    /**
     * 根据主键删除
     *
     * @param record
     * @return
     */
    int deleteByPrimaryKey(Object record);

    /**
     * 批量删除
     *
     * @param keys
     */
    void batchDelete(Object[] keys);

    /**
     * 批量删除
     *
     * @param records
     */
    void batchDelete(List<T> records);

    /**
     * 提交记录
     *
     * @param records
     * @return
     * @throws OptimisticLockException
     */
    int submit(T records) throws OptimisticLockException;

    /**
     * 批量提交
     *
     * @param records
     * @throws OptimisticLockException
     */
    void batchSubmit(List<T> records) throws OptimisticLockException;
}

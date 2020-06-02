package com.marshal.epoch.mybatis.service;

import com.marshal.epoch.mybatis.exception.ExcelException;
import com.marshal.epoch.mybatis.exception.OptimisticException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 基础资源库
 *
 * @param <T> domain
 * @author Marshal
 */
public interface BaseRepository<T> {

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
     * @throws OptimisticException
     */
    int updateByPrimaryKey(T record) throws OptimisticException;

    /**
     * 更新记录
     *
     * @param record
     * @return
     * @throws OptimisticException
     */
    int updateByPrimaryKeySelective(T record) throws OptimisticException;

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
     * @throws OptimisticException
     */
    int submit(T records) throws OptimisticException;

    /**
     * 批量提交
     *
     * @param records
     * @throws OptimisticException
     */
    void batchSubmit(List<T> records) throws OptimisticException;

    /**
     * 标准化excel导入
     *
     * @param file
     * @param clazz
     * @throws ExcelException
     */
    void excelImport(MultipartFile file, Class clazz) throws ExcelException;

    /**
     * 标准化excel导出
     *
     * @param response
     * @param records
     * @param clazz
     * @param fileName
     * @throws ExcelException
     */
    void excelExport(HttpServletResponse response, List<T> records, Class clazz, String fileName) throws ExcelException;

}

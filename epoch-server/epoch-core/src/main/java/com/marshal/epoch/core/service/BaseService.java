package com.marshal.epoch.core.service;

import com.marshal.epoch.core.exception.ExcelException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BaseService<T> {

    List<T> select(T condition, int pageNum, int pageSize);

    List<T> select(T condition);

    List<T> selectAll();

    T selectOne(T condition);

    T selectByPrimaryKey(Object record);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int deleteByPrimaryKey(Object record);

    void batchDelete(Object[] keys);

    void batchDelete(List<T> records);

    int submit(T records);

    void batchSubmit(List<T> records);

    void excelImport(MultipartFile file, Class clazz) throws ExcelException;

    void excelExport(HttpServletResponse response, List<T> records, Class clazz, String fileName) throws ExcelException;

}

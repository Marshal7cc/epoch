package com.marshal.epoch.mybatis.service.impl;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.github.pagehelper.PageHelper;
import com.marshal.epoch.core.base.BaseConstants;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.mybatis.exception.ExcelException;
import com.marshal.epoch.mybatis.exception.OptimisticException;
import com.marshal.epoch.mybatis.service.BaseRepository;
import com.marshal.epoch.mybatis.util.DomainUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public int updateByPrimaryKey(T record) throws OptimisticException {
        int updateRows = mapper.updateByPrimaryKey(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) throws OptimisticException {
        int updateRows = mapper.updateByPrimaryKeySelective(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int deleteByPrimaryKey(Object record) {
        return mapper.deleteByPrimaryKey(record);
    }

    @Override
    public int submit(T record) throws OptimisticException {
        if (DomainUtil.isPrimaryKeyNull(record)) {
            return this.insertSelective(record);
        } else {
            return this.updateByPrimaryKey(record);
        }
    }

    @Override
    public void batchSubmit(List<T> records) throws OptimisticException {
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

    @Override
    public void excelImport(MultipartFile file, Class clazz) throws ExcelException {
        List<T> list;
        ImportParams params = new ImportParams();
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
        } catch (Exception e) {
            throw new ExcelException(ResponseMessage.FAIL);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            batchSubmit(list);
        }
    }

    @Override
    public void excelExport(HttpServletResponse response, List<T> records, Class clazz, String fileName)
            throws ExcelException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, records);
        try {
            Response.responseExcel(workbook, fileName, response);
        } catch (IOException e) {
            throw new ExcelException(ResponseMessage.FAIL);
        }
    }

    /**
     * 检查数据库乐观锁
     *
     * @param updateRows
     * @throws OptimisticException
     */
    private void checkOptimisticLock(int updateRows) throws OptimisticException {
        if (updateRows < 1) {
            throw new OptimisticException();
        }
    }
}

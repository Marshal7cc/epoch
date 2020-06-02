package com.marshal.epoch.mybatis.base;

import java.util.List;

import com.github.pagehelper.Page;
import com.marshal.epoch.core.base.BaseApi;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.mybatis.domain.AuditDomain;
import com.marshal.epoch.mybatis.service.BaseRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * <p>通用controller</p>
 *
 * <pre>
 *     提供了基础的增删改查接口
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/5/30
 */
public class BaseController<T extends AuditDomain, R extends BaseRepository> implements BaseApi<T> {

    /**
     * 基础资源库
     */
    @Autowired
    protected R repository;

    @Override
    @ApiOperation(value = "E列表查询")
    public ResponseEntity<Page<T>> list(int page, int size, T condition) {
        List<T> list = repository.select(page, size, condition);
        return Response.success(list);
    }

    @Override
    @ApiOperation(value = "E详情查看")
    public ResponseEntity<T> detail(Long id) {
        return Response.success(repository.selectByPrimaryKey(id));
    }

    @Override
    @ApiOperation(value = "E记录创建")
    public ResponseEntity<T> create(T dto) {
        repository.submit(dto);
        return Response.success();
    }

    @Override
    @ApiOperation(value = "E记录更新")
    public ResponseEntity<T> update(T dto) {
        repository.submit(dto);
        return Response.success();
    }

    @Override
    @ApiOperation(value = "E批量删除")
    public ResponseEntity<T> delete(List<T> list) {
        repository.batchDelete(list);
        return Response.success();
    }

    /**
     * <pre>
     * 剔除请求参数两边的空格
     * </pre>
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }
}

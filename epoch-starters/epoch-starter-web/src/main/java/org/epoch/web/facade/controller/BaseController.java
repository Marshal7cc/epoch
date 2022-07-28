package org.epoch.web.facade.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.validation.Validator;

import io.swagger.annotations.ApiOperation;
import org.epoch.core.util.GenericTypeConverter;
import org.epoch.core.util.ValidUtils;
import org.epoch.data.domain.Page;
import org.epoch.data.service.BaseService;
import org.epoch.web.facade.BaseFacade;
import org.epoch.web.facade.vo.BaseVO;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * <p>通用controller</p>
 *
 * <pre>
 *     提供了基础的增删改查接口
 * </pre>
 *
 * @author Marshal
 * @date 2020/5/30
 */
public class BaseController<S extends BaseService<DO, ID>, VO extends BaseVO, QUERY, DO, ID extends Serializable>
        implements BaseFacade<VO, QUERY, ID> {

    @Autowired
    protected S service;
    protected final Class<DO> doClass;
    protected final Class<VO> voClass;
    @Autowired
    private Validator validator;

    @SuppressWarnings("unchecked")
    protected BaseController() {
        this.voClass = (Class<VO>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[1];
        this.doClass = (Class<DO>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[3];
    }

    @Override
    @ApiOperation(value = "列表查询")
    public ResponseEntity<Page<VO>> selectPage(int page, int size, QUERY condition) {
        Page<DO> dPage = service.findAll(PageRequest.of(page, size), condition);
        Page<VO> vPage = new Page<>(
                dPage.getPageInfo(),
                GenericTypeConverter.parseArray(dPage.getContent(), voClass)
        );
        return Response.success(vPage);
    }

    @Override
    @ApiOperation(value = "详情查看")
    public ResponseEntity<VO> findById(ID id) {
        return Response.success(GenericTypeConverter.parseObject(service.findById(id), voClass));
    }

    @Override
    @ApiOperation(value = "创建记录")
    public ResponseEntity<VO> insertItem(VO baseVO) {
        return Response.success(GenericTypeConverter.parseObject(service.save(GenericTypeConverter.parseObject(baseVO, doClass)), voClass));
    }

    @Override
    @ApiOperation(value = "更新记录")
    public ResponseEntity<VO> updateItem(ID id, VO baseVO) {
        baseVO.setId(String.valueOf(id));
        return Response.success(GenericTypeConverter.parseObject(service.save(GenericTypeConverter.parseObject(baseVO, doClass)), voClass));
    }

    @Override
    @ApiOperation(value = "批量删除")
    public ResponseEntity<Void> removeAll(List<VO> list) {
        service.deleteAll(GenericTypeConverter.parseArray(list, doClass));
        return Response.success();
    }

    @Override
    @ApiOperation(value = "主键删除")
    public ResponseEntity<Void> removeOne(ID id) {
        service.deleteById(id);
        return Response.success();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }

    /**
     * 验证单个对象
     *
     * @param object 验证对象
     * @param groups 验证分组
     * @param <T>    Bean 泛型
     */
    protected <T> void validObject(T object, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, object, groups);
    }

    /**
     * 验证单个对象
     *
     * @param object  验证对象
     * @param groups  验证分组
     * @param process 异常信息处理
     * @param <T>     Bean 泛型
     */
    protected <T> void validObject(T object, ValidUtils.ValidationResult process, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, object, process, groups);
    }

    /**
     * 迭代验证集合元素，使用默认异常信息处理
     *
     * @param collection Bean集合
     * @param groups     验证组
     * @param <T>        Bean 泛型
     */
    protected <T> void validList(Collection<T> collection, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, collection, groups);
    }

    /**
     * 迭代验证集合元素，使用默认异常信息处理
     *
     * @param collection Bean集合
     * @param groups     验证组
     * @param process    异常信息处理
     * @param <T>        Bean 泛型
     */
    protected <T> void validList(Collection<T> collection, ValidUtils.ValidationResult process, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, collection, process, groups);
    }
}

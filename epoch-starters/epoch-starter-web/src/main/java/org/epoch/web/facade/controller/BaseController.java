package org.epoch.web.facade.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import org.epoch.core.util.BaseConverter;
import org.epoch.data.domain.Auditable;
import org.epoch.data.service.BaseService;
import org.epoch.web.facade.BaseFacade;
import org.epoch.web.facade.dto.BaseDTO;
import org.epoch.web.facade.vo.BaseVO;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
public class BaseController<S extends BaseService<D, T, ID>, D extends BaseDTO, V extends BaseVO, T extends Auditable<ID>, ID extends Serializable>
        extends AbstractController
        implements BaseFacade<D, V, ID> {

    @Autowired
    protected S service;

    protected final Class<D> dClass;
    protected final Class<V> vClass;
    protected final Class<T> tClass;

    @SuppressWarnings("unchecked")
    protected BaseController() {
        this.dClass = (Class<D>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[1];
        this.vClass = (Class<V>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[2];
        this.tClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[3];
    }

    @Override
    @ApiOperation(value = "列表查询")
    public <Q> ResponseEntity<Page<V>> selectPage(int page, int size, Q condition) {
        return Response.success(service.findAll(PageRequest.of(page, size), condition));
    }

    @Override
    @ApiOperation(value = "详情查看")
    public ResponseEntity<V> findById(ID id) {
        return Response.success(service.findById(id));
    }

    @Override
    @ApiOperation(value = "创建记录")
    public ResponseEntity<D> insert(D baseDTO) {
        service.save(baseDTO);
        return Response.success(baseDTO);
    }

    @Override
    @ApiOperation(value = "更新记录")
    public ResponseEntity<D> update(ID id, D baseDTO) {
        baseDTO.setId(String.valueOf(id));
        service.save(baseDTO);
        return Response.success(baseDTO);
    }

    @Override
    @ApiOperation(value = "批量删除")
    public ResponseEntity<Void> remove(List<D> list) {
        service.getRepository().deleteAll(BaseConverter.parseArray(list, tClass));
        return Response.success();
    }

    @Override
    @ApiOperation(value = "主键删除")
    public ResponseEntity<Void> removeById(ID id) {
        service.removeById(id);
        return Response.success();
    }
}

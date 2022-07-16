package org.epoch.web.facade.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import org.epoch.core.convert.CommonConverter;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.data.domain.Auditable;
import org.epoch.data.repository.BaseRepository;
import org.epoch.web.facade.BaseFacade;
import org.epoch.web.facade.dto.BaseDTO;
import org.epoch.web.facade.query.BaseQuery;
import org.epoch.web.facade.vo.BaseVO;
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
public class BaseController<R extends BaseRepository<T, ID>, D extends BaseDTO, V extends BaseVO, Q extends BaseQuery, T extends Auditable<ID>, ID extends Serializable>
        extends AbstractController
        implements BaseFacade<D, V, Q, ID> {

    @Autowired
    protected R baseRepository;

    protected final Class<D> dClass;
    protected final Class<V> vClass;
    protected final Class<Q> qClass;
    protected final Class<T> tClass;

    @SuppressWarnings("unchecked")
    protected BaseController() {
        this.dClass = (Class<D>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[1];
        this.vClass = (Class<V>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[2];
        this.qClass = (Class<Q>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[3];
        this.tClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseController.class))[4];
    }

    @Override
    @ApiOperation(value = "列表查询")
    public ResponseEntity<Page<V>> selectPage(int page, int size, Q condition) {
        return Response.success(baseRepository.findAll(PageRequest.of(page, size), condition));
    }

    @Override
    @ApiOperation(value = "详情查看")
    public ResponseEntity<V> findById(ID id) {
        return Response.success(baseRepository.findById(id));
    }

    @Override
    @ApiOperation(value = "创建记录")
    public ResponseEntity<D> insert(D baseDTO) {
        baseRepository.saveOne(CommonConverter.parseObject(tClass, baseDTO));
        return Response.success(baseDTO);
    }

    @Override
    @ApiOperation(value = "更新记录")
    public ResponseEntity<D> update(ID id, D baseDTO) {
        baseDTO.setId(String.valueOf(id));
        baseRepository.saveOne(CommonConverter.parseObject(tClass, baseDTO));
        return Response.success(baseDTO);
    }

    @Override
    @ApiOperation(value = "批量删除")
    public ResponseEntity<Void> remove(List<D> list) {
        baseRepository.deleteAll(CommonConverter.parseArray(tClass, list));
        return Response.success();
    }

    @Override
    @ApiOperation(value = "主键删除")
    public ResponseEntity<Void> removeById(ID id) {
        baseRepository.deleteById(id);
        return Response.success();
    }
}

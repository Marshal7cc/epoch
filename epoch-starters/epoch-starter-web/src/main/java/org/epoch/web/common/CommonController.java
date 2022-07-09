package org.epoch.web.common;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import org.epoch.core.convert.CommonConverter;
import org.epoch.core.rest.PageableData;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.data.domain.Auditable;
import org.epoch.data.repository.BaseRepository;
import org.epoch.web.domain.BaseDTO;
import org.epoch.web.domain.BaseQuery;
import org.epoch.web.domain.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

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
public class CommonController<R extends BaseRepository<T, ID>,
        D extends BaseDTO, V extends BaseVO, Q extends BaseQuery,
        T extends Auditable, ID extends Serializable> extends BaseController
        implements BaseFacade<D, V, Q, ID> {


    protected Class<D> dClass;
    protected Class<V> vClass;
    protected Class<Q> qClass;
    protected Class<T> tClass;

    @SuppressWarnings("unchecked")
    protected CommonController() {
        this.dClass = (Class<D>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), CommonController.class))[1];
        this.vClass = (Class<V>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), CommonController.class))[2];
        this.qClass = (Class<Q>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), CommonController.class))[3];
        this.tClass = (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), CommonController.class))[4];

    }

    @Autowired
    protected R baseRepository;

    @Override
    @ApiOperation(value = "列表查询")
    public ResponseEntity<PageableData<V>> selectPage(int page, int size, Q condition) {
        List<V> list = null;
        return Response.success(list);
    }

    @Override
    @ApiOperation(value = "详情查看")
    public ResponseEntity<V> findById(ID id) {
        return Response.success(baseRepository.findById(id));
    }

    @Override
    @ApiOperation(value = "记录创建")
    public ResponseEntity<D> insert(D dto) {
        baseRepository.saveOne(CommonConverter.beanConvert(tClass, dto));
        return Response.success(dto);
    }

    @Override
    @ApiOperation(value = "记录更新")
    public ResponseEntity<D> update(D dto) {
        baseRepository.saveOne(CommonConverter.beanConvert(tClass, dto));
        return Response.success(dto);
    }

    @Override
    @ApiOperation(value = "批量删除")
    public ResponseEntity<Void> remove(List<D> list) {
        baseRepository.deleteAll(CommonConverter.listConverter(tClass, list));
        return Response.success();
    }
}
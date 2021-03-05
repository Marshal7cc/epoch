package org.epoch.mybatis.common;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.epoch.core.base.BaseController;
import org.epoch.core.rest.PageableData;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.mybatis.domain.entity.BaseEntity;
import org.epoch.mybatis.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CommonController<T extends BaseEntity, R extends BaseRepository<T>> extends BaseController implements CommonApi<T> {

    /**
     * 基础资源库
     */
    @Autowired
    protected R repository;

    @Override
    @ApiOperation(value = "E列表查询")
    public ResponseEntity<PageableData<T>> list(int page, int size, T condition) {
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
        return Response.success(dto);
    }

    @Override
    @ApiOperation(value = "E记录更新")
    public ResponseEntity<T> update(T dto) {
        repository.submit(dto);
        return Response.success(dto);
    }

    @Override
    @ApiOperation(value = "E批量删除")
    public ResponseEntity<T> delete(List<T> list) {
        repository.batchDelete(list);
        return Response.success();
    }
}

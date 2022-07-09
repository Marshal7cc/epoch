package org.epoch.web.common;

import java.io.Serializable;
import java.util.List;

import org.epoch.core.rest.PageableData;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.web.domain.BaseQuery;
import org.epoch.web.domain.BaseDTO;
import org.epoch.web.domain.BaseVO;
import org.springframework.web.bind.annotation.*;

/**
 * <p>基础通用API</p>
 *
 * @author Marshal
 * @date 2020/5/31
 */
public interface BaseFacade<D extends BaseDTO, V extends BaseVO, Q extends BaseQuery, ID extends Serializable> {

    /**
     * 基础列表查询
     *
     * @param page  页码
     * @param size  数量
     * @param query 查询条件
     * @return 结果集合
     */
    @GetMapping("{page}/{size}")
    ResponseEntity<PageableData<V>> selectPage(@PathVariable("page") int page,
                                               @PathVariable("size") int size,
                                               Q query);

    /**
     * 基础详情
     *
     * @param id 主键
     * @return 记录详情
     */
    @GetMapping("/{id}")
    ResponseEntity<V> findById(@PathVariable("id") ID id);

    /**
     * 基础新建
     *
     * @param dto 记录属性
     * @return
     */
    @PostMapping
    ResponseEntity<D> insert(@RequestBody D dto);

    /**
     * 基础更新
     *
     * @param dto 记录属性
     * @return
     */
    @PutMapping
    ResponseEntity<D> update(@RequestBody D dto);

    /**
     * 批量删除
     *
     * @param list 待删除记录集合
     * @return
     */
    @DeleteMapping
    ResponseEntity<Void> remove(@RequestBody List<D> list);
}
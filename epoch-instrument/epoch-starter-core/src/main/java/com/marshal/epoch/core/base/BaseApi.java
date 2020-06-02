package com.marshal.epoch.core.base;

import java.util.List;

import com.github.pagehelper.Page;
import com.marshal.epoch.core.rest.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>基础通用API</p>
 *
 * @author Marshal Yuan
 * @date 2020/5/31
 */
public interface BaseApi<T> {

    /**
     * 基础列表查询
     *
     * @param page      页码
     * @param size      数量
     * @param condition 查询条件
     * @return 结果集合
     */
    @GetMapping("{page}/{size}")
    ResponseEntity<Page<T>> list(@PathVariable("page") int page,
                                 @PathVariable("size") int size,
                                 T condition);

    /**
     * 基础详情
     *
     * @param id 主键
     * @return 记录详情
     */
    @GetMapping("/{id}")
    ResponseEntity<T> detail(@PathVariable("id") Long id);

    /**
     * 基础新建
     *
     * @param dto 记录属性
     * @return
     */
    @PostMapping
    ResponseEntity<T> create(@RequestBody T dto);

    /**
     * 基础更新
     *
     * @param dto 记录属性
     * @return
     */
    @PutMapping
    ResponseEntity<T> update(@RequestBody T dto);

    /**
     * 批量删除
     *
     * @param list 待删除记录列表
     * @return
     */
    @DeleteMapping
    ResponseEntity<T> delete(@RequestBody List<T> list);
}

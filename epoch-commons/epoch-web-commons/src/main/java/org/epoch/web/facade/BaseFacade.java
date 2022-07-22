package org.epoch.web.facade;

import java.util.List;

import org.epoch.data.domain.Page;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>基础通用Facade</p>
 *
 * @author Marshal
 * @date 2020/5/31
 */
public interface BaseFacade<VO, QUERY, ID> {

    /**
     * 基础列表查询
     *
     * @param page  页码
     * @param size  数量
     * @param query 查询条件
     * @return 结果集合
     */
    @GetMapping("{page}/{size}")
    ResponseEntity<Page<VO>> selectPage(@PathVariable("page") int page,
                                        @PathVariable("size") int size,
                                        QUERY query);

    /**
     * 基础详情
     *
     * @param id 主键
     * @return 记录详情
     */
    @GetMapping("/{id}")
    ResponseEntity<VO> findById(@PathVariable("id") ID id);

    /**
     * 基础新建
     *
     * @param vo 记录属性
     * @return
     */
    @PostMapping
    ResponseEntity<VO> insertItem(@RequestBody VO vo);

    /**
     * 基础更新
     *
     * @param id 主键
     * @param vo 记录属性
     * @return
     */
    @PutMapping("/{id}")
    ResponseEntity<VO> updateItem(@PathVariable("id") ID id, @RequestBody VO vo);

    /**
     * 删除单条
     *
     * @param id primary key
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeOne(@PathVariable("id") ID id);

    /**
     * 批量删除
     *
     * @param list 待删除记录集合
     * @return
     */
    @DeleteMapping
    ResponseEntity<Void> removeAll(@RequestBody List<VO> list);

}

package com.marshal.epoch.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2019/3/4
 * @desc: oauth2测试入口
 */
@RestController
public class TestEndPoints {

    @GetMapping("/api/goods/{goodsId}")
    public String getGoods(@PathVariable Long goodsId) {
        return "商品：" + goodsId;
    }

    @GetMapping("/api/orders/{ordersId}")
    public String getOrders(@PathVariable Long ordersId) {
        return "商品：" + ordersId;
    }
}

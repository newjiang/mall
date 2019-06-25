package com.shao.mall.order.api.controller;

import com.github.pagehelper.PageInfo;
import com.shao.mall.common.model.Page;
import com.shao.mall.common.model.Result;
import com.shao.mall.order.api.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author newjiang
 * @date 2019/6/24 23:18
 * @description: 订单控制层接口
 */
@RequestMapping("order")
public interface IOrderController {
    /**
     * 添加
     */
    @PutMapping
    Result<Order> add(Order order);

    /**
     * 删除
     */
    @DeleteMapping
    Result<?> delete(String id);

    /**
     * 批量删除
     */
    @DeleteMapping
    @RequestMapping("batch")
    Result<?> delete(List<String> id);

    /**
     * 更新
     */
    @PostMapping
    Result<Order> update(Order order);

    /**
     * 查询
     */
    @GetMapping
    Result<PageInfo<Order>> query(Order order, Page page);
}

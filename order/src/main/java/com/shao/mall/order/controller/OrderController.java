package com.shao.mall.order.controller;

import com.github.pagehelper.PageInfo;
import com.shao.mall.common.model.Page;
import com.shao.mall.common.model.Result;
import com.shao.mall.order.api.controller.IOrderController;
import com.shao.mall.order.api.model.Order;
import com.shao.mall.order.api.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description: 订单控制层接口实现
 */
@RestController
public class OrderController implements IOrderController {

    @Autowired
    private IOrderService orderService;

    @Override
    public Result<Order> add(@RequestBody Order order) {
        orderService.add(order);
        return Result.success(order);
    }

    @Override
    public Result<?> delete(@RequestBody String id) {
        orderService.delete(id);
        return Result.success();
    }

    @Override
    public Result<?> delete(@RequestBody List<String> ids) {
        orderService.delete(ids);
        return Result.success();
    }

    @Override
    public Result<Order> update(@RequestBody Order order) {
        orderService.update(order);
        return Result.success(order);
    }

    @Override
    public Result<PageInfo<Order>> query(Order order, @Valid Page page) {
        PageInfo<Order> info = orderService.query(order, page);
        return Result.success(info);
    }
}

package com.shao.mall.order.controller;

import com.shao.mall.common.model.Result;
import com.shao.mall.order.model.Order;
import com.shao.mall.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public Result<Order> add(Order order) {
        orderService.add(order);
        Order o = orderService.find(order.getOrderId());
        return Result.success(o);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Result<Order> delete(@PathVariable("id") int orderId) {
        orderService.delete(orderId);
        return Result.success();
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Result<Order> update(Order order){
        Order update = orderService.update(order);
        return Result.success(update);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result<Order> find(@PathVariable("id") int orderId){
        Order order = orderService.find(orderId);
        return Result.success(order);
    }

}

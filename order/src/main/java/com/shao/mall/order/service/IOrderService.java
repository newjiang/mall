package com.shao.mall.order.service;

import com.shao.mall.order.model.Order;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
public interface IOrderService {

    int add(Order order);

    void delete(int orderId);

    Order update(Order order);

    Order find(int orderId);
}

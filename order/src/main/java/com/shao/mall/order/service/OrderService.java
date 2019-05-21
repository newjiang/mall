package com.shao.mall.order.service;

import com.shao.mall.common.utils.Bean2Map;
import com.shao.mall.order.dao.OrderMapper;
import com.shao.mall.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int add(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public void delete(int orderId) {
        orderMapper.delete(orderId);
    }

    @Override
    public Order update(Order order) {
        Map<String, Object> map = Bean2Map.toMap(order);
        orderMapper.update(order.getOrderId(), map);
        return find(order.getOrderId());
    }

    @Override
    public Order find(int orderId) {
        return orderMapper.find(orderId);
    }

}

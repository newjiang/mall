package com.shao.mall.order.service;

import com.shao.mall.common.utils.Bean2Map;
import com.shao.mall.order.dao.OrderMapper;
import com.shao.mall.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "order", key = "#orderId")
    @Override
    public void delete(int orderId) {
        orderMapper.delete(orderId);
    }

    @CachePut(value = "order", key = "#order.orderId")
    @Override
    public Order update(Order order) {
        Map<String, Object> map = Bean2Map.toMap(order);
        orderMapper.update(order.getOrderId(), map);
        return find(order.getOrderId());
    }

    @Cacheable(value = "order", key = "#orderId")
    @Override
    public Order find(int orderId) {
        return orderMapper.find(orderId);
    }

}

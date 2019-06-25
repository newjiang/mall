package com.shao.mall.order.api.service;

import com.github.pagehelper.PageInfo;
import com.shao.mall.common.model.Page;
import com.shao.mall.order.api.model.Order;

import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description: 订单业务层接口
 */
public interface IOrderService {

    /**
     * 添加
     */
    void add(Order order);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 批量删除
     */
    void delete(List<String> ids);

    /**
     * 更新
     */
    void update(Order order);

    /**
     * 查询
     */
    PageInfo<Order> query(Order order, Page page);


}

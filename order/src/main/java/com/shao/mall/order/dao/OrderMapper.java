package com.shao.mall.order.dao;

import com.shao.mall.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@Mapper
public interface OrderMapper {

    int insert(Order order);

    void update(@Param("id")int id, @Param("map") Map<String, Object> map);

    void delete(int orderId);

    Order find(int orderId);
}

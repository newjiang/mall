package com.shao.mall.order.mapper;

import com.shao.mall.order.api.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description: 订单持久层接口
 */
@Mapper
public interface OrderMapper {

    /**
     * 添加
     */
    int insert(@Param("map") Map<String, Object> map);

    /**
     * 批量添加
     */
    int insertBatch(@Param("map") Map<String, Object> map);

    /**
     * 删除
     */
    int delete(String id);

    /**
     * 批量删除
     */
    int deleteBatch(List<String> ids);

    /**
     * 更新
     */
    int update(@Param("map") Map<?, ?> map, @Param("id") String id);

    /**
     * 查询
     */
    List<Order> query(Order order);

}

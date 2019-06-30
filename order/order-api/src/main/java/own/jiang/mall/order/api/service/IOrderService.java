package own.jiang.mall.order.api.service;


import own.jiang.mall.order.api.model.Order;
import own.jiang.mall.common.model.Page;

import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
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
     * 更新
     */
    void update(Order order);

    /**
     * 查询
     */
    List<Order> query(Page<Order> page);

}

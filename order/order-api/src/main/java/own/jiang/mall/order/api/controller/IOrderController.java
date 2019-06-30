package own.jiang.mall.order.api.controller;


import org.springframework.web.bind.annotation.*;
import own.jiang.mall.order.api.model.Order;
import own.jiang.mall.common.model.Result;

import java.util.List;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/6/24 23:18
 * @description: TODO
 */
@RequestMapping("order")
public interface IOrderController {
    /**
     * 添加
     *
     * @param order
     * @return
     */
    @PutMapping
    Result<Order> add(Order order);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    Result<Object> delete(String id);

    /**
     * 更新
     *
     * @param order
     * @return
     */
    @PostMapping
    Result<Order> update(Order order);

    /**
     * 查询
     *
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    Result<List<Order>> query(@RequestParam Map<String, Object> map);
}

package own.jiang.mall.order.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import own.jiang.mall.order.api.controller.IOrderController;
import own.jiang.mall.order.api.model.Order;
import own.jiang.mall.order.api.service.IOrderService;
import own.jiang.mall.common.model.Page;
import own.jiang.mall.common.model.Result;
import own.jiang.mall.common.utils.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
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
    public Result<Object> delete(@RequestBody String id) {
        orderService.delete(id);
        return Result.success();
    }

    @Override
    public Result<Order> update(@RequestBody Order order) {
        orderService.update(order);
        return Result.success(order);
    }

    @Override
    public Result<List<Order>> query(@RequestParam Map<String, Object> map) {
        Page<Order> page = BeanMap.toPage(Order.class, map);
        List<Order> list = orderService.query(page);
        return Result.success(list);
    }

}

package own.jiang.mall.order.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jiang.mall.order.api.model.Order;
import own.jiang.mall.order.api.service.IOrderService;
import own.jiang.mall.common.code.GUID;
import own.jiang.mall.common.exception.BaseException;
import own.jiang.mall.common.model.BaseExceptionEnum;
import own.jiang.mall.common.model.Page;
import own.jiang.mall.common.utils.BeanMap;
import own.jiang.mall.order.server.dao.OrderMapper;

import java.util.Date;
import java.util.List;
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

    @Autowired
    private GUID guid;

    @Override
    public void add(Order order) {
        order.setOrderId(guid.nextId());
        order.setCreateTime(new Date());
        Map<String, Object> map = BeanMap.toSqlMap(order);
        int insert = orderMapper.insert(map);
        if (insert < 1) {
            throw new BaseException(BaseExceptionEnum.INSERT_ERROR);
        }
    }

    @Override
    public void delete(String id) {
        int delete = orderMapper.delete(id);
        if (delete < 1) {
            throw new BaseException(BaseExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public void update(Order order) {
        Map<String, Object> map = BeanMap.toMap(order);
        int update = orderMapper.update(map, order.getOrderId());
        if (update < 1) {
            throw new BaseException(BaseExceptionEnum.UPDATE_ERROR);
        }
    }

    @Override
    public List<Order> query(Page<Order> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Order> list = orderMapper.query(page.getParam());
        PageInfo<Order> info = new PageInfo<>(list);
        return info.getList();
    }

}

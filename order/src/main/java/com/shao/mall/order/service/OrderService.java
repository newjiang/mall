package com.shao.mall.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shao.mall.common.code.GUID;
import com.shao.mall.common.exception.BaseException;
import com.shao.mall.common.model.BaseExceptionEnum;
import com.shao.mall.common.model.Page;
import com.shao.mall.common.utils.Bean2Map;
import com.shao.mall.order.api.model.Order;
import com.shao.mall.order.api.service.IOrderService;
import com.shao.mall.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description: 订单业务层接口实现
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GUID guid;

    @Transactional
    @Override
    public void add(Order order) {
        order.setOrderId(guid.nextId());
        order.setCreateTime(new Date());
        Map<String, Object> map = Bean2Map.toMap(order);
        int insert = orderMapper.insert(map);
        if (insert < 1) {
            throw new BaseException(BaseExceptionEnum.INSERT_ERROR);
        }
    }

    @Transactional
    @Override
    public void delete(String id) {
        int delete = orderMapper.delete(id);
        if (delete < 1) {
            throw new BaseException(BaseExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public void delete(List<String> ids) {
        int delete = orderMapper.deleteBatch(ids);
        if (delete < 1) {
            throw new BaseException(BaseExceptionEnum.DELETE_ERROR);
        }
    }

    @Transactional
    @Override
    public void update(Order order) {
        Map<String, Object> map = Bean2Map.toMap(order);
        int update = orderMapper.update(map, order.getOrderId());
        if (update < 1) {
            throw new BaseException(BaseExceptionEnum.UPDATE_ERROR);
        }
    }

    @Override
    public PageInfo<Order> query(Order order, Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Order> list = orderMapper.query(order);
        PageInfo<Order> info = new PageInfo<>(list);
        return info;
    }

}

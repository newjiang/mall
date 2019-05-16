package com.shao.mall.order.controller;

import com.shao.mall.common.MessageEnum;
import com.shao.mall.common.Result;
import com.shao.mall.order.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/15 22:53
 * @description: 测试统一返回前端的代码格式
 */
@RestController
public class TestController {

    @RequestMapping("success")
    public Result<Order> success() {
        Order order = new Order();
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        list.add("list4");
        list.add("list5");
        order.setList(list);
        return Result.success();
    }

    @RequestMapping("failure")
    public Result<List<String>> failure() {
        return Result.failure(MessageEnum.failure.getValue());
    }
}

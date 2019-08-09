package com.jiang.mall.user.server.controller;

import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.user.api.controller.IUsercontrller;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.vo.UserVo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: newjiang
 * @date: 2019/8/9 16:47
 * @description: todo
 **/
@RestController
public class Usercontrller implements IUsercontrller {

    @Override
    public Result<User> add(User dto) {
        return null;
    }

    @Override
    public Result<Object> delete(List<String> ids) {
        return null;
    }

    @Override
    public Result<User> update(User dto) {
        return null;
    }

    @Override
    public Result<PageData<UserVo>> query(Map<String, Object> map) {
        return null;
    }
}

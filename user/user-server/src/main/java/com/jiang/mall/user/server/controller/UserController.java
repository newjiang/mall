package com.jiang.mall.user.server.controller;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.user.api.controller.IUserController;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.service.IUserService;
import com.jiang.mall.user.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: newjiang
 * @date: 2019/8/14 16:44
 * @description: todo
 **/
@RestController
public class UserController implements IUserController {
    @Autowired
    private IUserService userService;
    @Override
    public Result<User> add(User dto) {
        userService.add(dto);
        return Result.success(dto);
    }

    @Override
    public Result<Object> delete(List<String> ids) {
        userService.delete(ids);
        return Result.success();
    }

    @Override
    public Result<User> update(User dto) {
        userService.update(dto);
        return Result.success(dto);
    }

    @Override
    public Result<PageData<UserVo>> query(Map<String, Object> map) {
        Page<User> page = BeanMap.toPage(map, User.class);
        PageInfo<UserVo> info = userService.query(page);
        return Result.success(info);
    }
}

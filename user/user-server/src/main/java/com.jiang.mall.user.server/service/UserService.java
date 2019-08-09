package com.jiang.mall.user.server.service;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.service.IUserService;
import com.jiang.mall.user.api.vo.UserVo;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: newjiang
 * @date: 2019/8/9 16:51
 * @description: todo
 **/
public class UserService implements IUserService {

    @Override
    public void add(User entity) {

    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public PageInfo<UserVo> query(Page<User> page) {
        return null;
    }
}

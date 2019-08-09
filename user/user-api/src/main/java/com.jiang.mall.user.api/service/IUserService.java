package com.jiang.mall.user.api.service;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.vo.UserVo;

import java.util.List;

public interface IUserService {
    /**
     * 添加
     */
    void add(User entity);

    /**
     * 删除
     */
    void delete(List<String> ids);

    /**
     * 更新
     */
    void update(User entity);

    /**
     * 查询
     */
    PageInfo<UserVo> query(Page<User> page);

}

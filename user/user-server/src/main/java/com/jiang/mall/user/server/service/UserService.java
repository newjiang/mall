package com.jiang.mall.user.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.code.GUID;
import com.jiang.mall.common.en.BaseErrorEnum;
import com.jiang.mall.common.exception.BaseException;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.service.IUserService;
import com.jiang.mall.user.api.vo.UserVo;
import com.jiang.mall.user.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: newjiang
 * @date: 2019/8/14 16:44
 * @description: todo
 **/
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GUID guid;

    @Override
    public void add(User entity) {
        entity.setUserId(guid.nextId());
        Map<String, Object> map = BeanMap.toMap(entity);
        int insert = userMapper.insert(map);
        if (insert < 1) {
            throw new BaseException(BaseErrorEnum.INSERT_ERROR);
        }
    }

    @Override
    public void delete(List<String> ids) {
        int delete = userMapper.delete(ids);
        if (delete < ids.size()) {
            throw new BaseException(BaseErrorEnum.DELETE_ERROR);
        }

    }

    @Override
    public void update(User entity) {
        Map<String, Object> map = BeanMap.toMap(entity);
        int insert = userMapper.update(map, entity.getUserId());
        if (insert < 1) {
            throw new BaseException(BaseErrorEnum.UPDATE_ERROR);
        }
    }

    @Override
    public PageInfo<UserVo> query(Page<User> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize(), page.isTotal());
        List<UserVo> list = userMapper.query(page.getParam());
        return new PageInfo<>(list);
    }
}

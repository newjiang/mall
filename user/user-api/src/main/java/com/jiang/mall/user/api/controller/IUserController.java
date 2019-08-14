package com.jiang.mall.user.api.controller;

import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author: newjiang
 * @date: 2019/8/14 16:36
 * @description: todo
 **/
@RequestMapping("user")
public interface IUserController {
    /**
     * 添加
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<User> add(@RequestBody User dto);

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Object> delete(@RequestBody List<String> ids);

    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<User> update(@RequestBody User dto);

    /**
     * 查询
     */
    @RequestMapping(method = RequestMethod.GET)
    Result<PageData<UserVo>> query(@RequestParam Map<String, Object> map);
}

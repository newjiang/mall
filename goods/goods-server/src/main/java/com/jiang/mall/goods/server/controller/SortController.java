package com.jiang.mall.goods.server.controller;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.goods.api.controller.ISortController;
import com.jiang.mall.goods.api.model.Sort;
import com.jiang.mall.goods.api.service.ISortService;
import com.jiang.mall.goods.api.vo.SortVo;
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
public class SortController implements ISortController {

    @Autowired
    private ISortService userService;

    @Override
    public Result<Sort> add(Sort dto) {
        userService.add(dto);
        return Result.success(dto);
    }

    @Override
    public Result<Object> delete(List<String> ids) {
        userService.delete(ids);
        return Result.success();
    }

    @Override
    public Result<Sort> update(Sort dto) {
        userService.update(dto);
        return Result.success(dto);
    }

    @Override
    public Result<PageData<SortVo>> query(Map<String, Object> map) {
        Page<Sort> page = BeanMap.toPage(map, Sort.class);
        PageInfo<SortVo> info = userService.query(page);
        return Result.success(info);
    }
}

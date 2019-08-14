package com.jiang.mall.goods.api.controller;

import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.goods.api.model.Sort;
import com.jiang.mall.goods.api.vo.SortVo;
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
@RequestMapping("sort")
public interface ISortController {
    /**
     * 添加
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Sort> add(@RequestBody Sort dto);

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Object> delete(@RequestBody List<String> ids);

    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Sort> update(@RequestBody Sort dto);

    /**
     * 查询
     */
    @RequestMapping(method = RequestMethod.GET)
    Result<PageData<SortVo>> query(@RequestParam Map<String, Object> map);
}

package com.jiang.mall.system.api.controller;

import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.system.api.model.Select;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("select")
public interface ISelectController {

    /**
     * 添加
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Select> add(@RequestBody Select dto);

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Object> delete(@RequestBody List<String> ids);

    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Select> update(@RequestBody Select dto);

    /**
     * 查询
     */
    @RequestMapping(method = RequestMethod.GET)
    Result<PageData<Select>> query(@RequestParam Map<String, Object> map);

    /**
     * 下拉框查询
     * @param number 序号
     * @param item   选项
     */
    @RequestMapping(value = "option", method = RequestMethod.GET)
    Result<List<String>> option(@RequestParam("number") String number,
                                @RequestParam(value = "item", required = false) String item);
}

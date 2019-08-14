package com.jiang.mall.system.server.controller;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.model.PageData;
import com.jiang.mall.common.model.Result;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.system.api.controller.ISelectController;
import com.jiang.mall.system.api.model.Select;
import com.jiang.mall.system.api.service.ISelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SelectController implements ISelectController {

    @Autowired
    private ISelectService selectService;

    @Override
    public Result<Select> add(@RequestBody Select dto) {
        selectService.add(dto);
        return Result.success(dto);
    }

    @Override
    public Result<Object> delete(@RequestBody List<String> ids) {
        selectService.delete(ids);
        return Result.success();
    }

    @Override
    public Result<Select> update(@RequestBody Select dto) {
        selectService.update(dto);
        return Result.success(dto);
    }

    @Override
    public Result<PageData<Select>> query(@RequestParam Map<String, Object> map) {
        Page<Select> page = BeanMap.toPage(map, Select.class);
        PageInfo<Select> info = selectService.query(page);
        return Result.success(info);
    }

    @Override
    public Result<List<String>> option(@RequestParam("number") String number,
                                       @RequestParam(value = "item", required = false) String item) {
        List<String> list = selectService.option(number, item);
        return Result.success(list);
    }
}

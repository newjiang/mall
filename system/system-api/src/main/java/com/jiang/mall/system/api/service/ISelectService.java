package com.jiang.mall.system.api.service;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.system.api.model.Select;

import java.util.List;

public interface ISelectService {
    /**
     * 添加
     */
    void add(Select entity);

    /**
     * 删除
     */
    void delete(List<String> ids);

    /**
     * 更新
     */
    void update(Select entity);

    /**
     * 查询
     */
    PageInfo<Select> query(Page<Select> page);

    /**
     * 下拉框查询
     *
     * @param number 序号
     * @param item   选项
     */
    List<String> option(String number, String item);

}

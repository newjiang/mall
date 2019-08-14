package com.jiang.mall.goods.api.service;

import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.goods.api.model.Sort;
import com.jiang.mall.goods.api.vo.SortVo;

import java.util.List;

public interface ISortService {
    /**
     * 添加
     */
    void add(Sort entity);

    /**
     * 删除
     */
    void delete(List<String> ids);

    /**
     * 更新
     */
    void update(Sort entity);

    /**
     * 查询
     */
    PageInfo<SortVo> query(Page<Sort> page);
}

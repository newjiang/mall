package com.jiang.mall.goods.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.mall.common.code.GUID;
import com.jiang.mall.common.en.BaseErrorEnum;
import com.jiang.mall.common.exception.BaseException;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.goods.api.model.Sort;
import com.jiang.mall.goods.api.service.ISortService;
import com.jiang.mall.goods.api.vo.SortVo;
import com.jiang.mall.goods.server.mapper.SortMapper;
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
public class SortService implements ISortService {

    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private GUID guid;

    @Override
    public void add(Sort entity) {
        entity.setSortId(guid.nextId());
        Map<String, Object> map = BeanMap.toMap(entity);
        int insert = sortMapper.insert(map);
        if (insert < 1) {
            throw new BaseException(BaseErrorEnum.INSERT_ERROR);
        }
    }

    @Override
    public void delete(List<String> ids) {
        int delete = sortMapper.delete(ids);
        if (delete < ids.size()) {
            throw new BaseException(BaseErrorEnum.DELETE_ERROR);
        }

    }

    @Override
    public void update(Sort entity) {
        Map<String, Object> map = BeanMap.toMap(entity);
        int insert = sortMapper.update(map, entity.getSortId());
        if (insert < 1) {
            throw new BaseException(BaseErrorEnum.UPDATE_ERROR);
        }
    }

    @Override
    public PageInfo<SortVo> query(Page<Sort> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize(), page.isTotal());
        List<SortVo> list = sortMapper.query(page.getParam());
        return new PageInfo<>(list);
    }
}

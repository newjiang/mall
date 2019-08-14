package com.jiang.mall.system.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.jiang.mall.common.code.GUID;
import com.jiang.mall.common.en.BaseErrorEnum;
import com.jiang.mall.common.exception.BaseException;
import com.jiang.mall.common.model.Page;
import com.jiang.mall.common.utils.BeanMap;
import com.jiang.mall.system.api.model.Select;
import com.jiang.mall.system.api.service.ISelectService;
import com.jiang.mall.system.server.mapper.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectService implements ISelectService {

    @Autowired
    private SelectMapper selectMapper;

    @Autowired
    private GUID guid;

    @Override
    public void add(Select entity) {
        entity.setId(guid.nextId());
        if (entity.getName() !=null){
            int max = selectMapper.queryMax();
            entity.setNumber(max);
        }
        Map<String, Object> map = BeanMap.toSqlMap(entity);
        int insert = selectMapper.insert(map);
        if (insert < 1) {
            throw new BaseException(BaseErrorEnum.INSERT_ERROR);
        }
    }

    @Override
    public void delete(List<String> ids) {
        int delete = selectMapper.delete(ids);
        if (delete < 1) {
            throw new BaseException(BaseErrorEnum.DELETE_ERROR);
        }
    }

    @Override
    public void update(Select entity) {
        Map<String, Object> map = BeanMap.toSqlMap(entity);
        int update = selectMapper.update(map, entity.getId());
        if (update < 1) {
            throw new BaseException(BaseErrorEnum.UPDATE_ERROR);
        }
    }

    @Override
    public PageInfo<Select> query(Page<Select> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize(), page.isTotal());
        List<Select> list = selectMapper.query(page.getParam());
        return new PageInfo<>(list);
    }

    @Override
    public List<String> option(String number, String item) {
        return selectMapper.option(number, item);
    }
}

package com.jiang.mall.system.server.mapper;

import com.jiang.mall.system.api.model.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SelectMapper {
    /**
     * 添加
     */
    int insert(@Param("map") Map<String, Object> map);

    /**
     * 删除
     */
    int delete(List<String> list);

    /**
     * 更新
     */
    int update(@Param("map") Map<?, ?> map, @Param("id") String id);

    /**
     * 根据ID查询商品
     */
    List<Select> query(Select entity);

    /**
     * 查询下拉框
     * @param number 序号
     * @param item  值
     */
    List<String> option(@Param("number") String number, @Param("item") String item);

    /**
     * 获取最大的序号
     */
    int queryMax();
}

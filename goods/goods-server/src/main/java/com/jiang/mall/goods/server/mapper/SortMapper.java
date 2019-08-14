package com.jiang.mall.goods.server.mapper;

import com.jiang.mall.goods.api.model.Sort;
import com.jiang.mall.goods.api.vo.SortVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SortMapper {
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
     * 查询
     */
    List<SortVo> query(Sort entity);

}

package com.jiang.mall.user.server.mapper;

import com.jiang.mall.user.api.model.User;
import com.jiang.mall.user.api.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
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
    List<UserVo> query(User user);
}

package own.jiang.mall.order.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import own.jiang.mall.order.api.model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@Mapper
public interface OrderMapper {

    /**
     * 添加
     */
    int insert(@Param("map") Map<String, Object> map);

    /**
     * 删除
     */
    int delete(String id);

    /**
     * 更新
     */
    int update(@Param("map") Map<?, ?> map, @Param("id") String id);

    /**
     * 查询
     */
    List<Order> query(Order order);

}

package com.shao.mall.order.test;


import com.shao.mall.common.Serializer;
import com.shao.mall.order.model.Order;

/**
 * @author newjiang
 * @date 2019/5/16
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        testSerializer();
    }

    // 测试序列化和反序列化
    public static void testSerializer() {
        // 需要序列化的对象
        Order order = new Order();
        order.setId("1");
        order.setName("序列化");
        order.setMessage("测试序列化");

        // 进行序列化
        Serializer<Order> serializer = new Serializer<>(order);
        byte[] bytes = serializer.serialize();
        System.out.println("序列化:" + bytes);

        // 进行反序列化
        Order temp = serializer.deserialize(bytes);
        System.out.println("反序列化:" + temp);
    }
}

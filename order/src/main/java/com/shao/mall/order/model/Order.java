package com.shao.mall.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@Setter
@Getter
public class Order implements Serializable {

    //订单
    private int orderId;

    //用户ID
    private int userId;

    //订单状态
    private int orderStatusId;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completedTime;

    //总金额
    private double totalCount;

    //实际支付金额
    private double actualCount;

    //邮费
    private double postCount;

    //支付方式
    private int paymentType;

}

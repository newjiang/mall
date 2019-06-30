package own.jiang.mall.order.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description:
 */
@Setter
@Getter
@ToString
public class Order implements Serializable {

    //订单
    private String orderId;

    //用户ID
    private String userId;

    //订单状态
    private String orderStatusId;

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
    private String paymentType;

}

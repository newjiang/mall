package com.shao.mall.order.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author newjiang
 * @date 2019/5/15 22:55
 * @description: TODO
 */
@Data
public class Order {

    private String id;

    private String name;

    private String message;

    private List<String> list;

}

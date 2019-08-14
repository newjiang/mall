package com.jiang.mall.system.api.model;

import com.jiang.mall.common.model.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 下拉选择框的实体
 */
@Setter
@Getter
@ToString
public class Select extends Entity implements Serializable {

    // ID
    private String id;

    // 下拉框名称
    private String name;

    // 下来选项
    private String item;

    // 父ID
    private String parentId;

    // 状态
    private String status;

    // 序号
    private Integer number;

}

package com.jiang.mall.goods.api.model;

import com.jiang.mall.common.model.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: newjiang
 * @date: 2019/8/14 16:37
 * @description: todo
 **/
@Setter
@Getter
@ToString
public class Sort extends Entity implements Serializable {

    // 分类ID
    private String sortId;

    // 父ID
    private String parentId;

    // 分类名称
    private String sortName;

    // 分类等级
    private Integer sortLevel;

}

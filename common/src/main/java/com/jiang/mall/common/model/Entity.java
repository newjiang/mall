package com.jiang.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: newjiang
 * @date: 2019/8/9 16:17
 * @description: todo
 **/
@Setter
@Getter
@ToString
public class Entity {

    // 创建时间
    private long createTime;

    // 创建人
    private String creator;

    // 创建人ID
    private String creatorId;

    // 创建时间
    private long createdTime;

    // 最后修改人
    private String editor;

    // 最后修改人ID
    private String editorId;

    // 最后修改时间
    private Date editedTime;

}

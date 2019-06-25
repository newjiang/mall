package com.shao.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author newjiang
 * @date 2019/6/13
 * @description: 用于处理Delete请求，接受ID的DTO类
 */
@Setter
@Getter
@ToString
public class ID {
    // id
    @NotNull(message = "id不能为空")
    private String id;
}

package com.jiang.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author newjiang
 * @date 2019/6/5 22:34
 * @description: 分页查询参数设置类
 */
@Setter
@Getter
@ToString
public class Page<T> {

    // 页号
    @Min(value = 1, message = "页号最小为1")
    private int pageNo;

    // 页数
    @Min(value = 1, message = "页数最小为1")
    @Max(value = 200, message = "页数最大为200")
    private int pageSize;

    // 是否查询全部
    private boolean total;

    // 查询对象
    private T param;

    public Page() {

    }

    public Page(int pageNo, int pageSize, boolean total) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Page(int pageNo, int pageSize, boolean total, T param) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.param = param;
    }
}

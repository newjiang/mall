package com.jiang.mall.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PageData<T> {

    // 总数
    public long total;

    // 集合
    public List<T> list;

    public PageData() {
    }

    public PageData(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}

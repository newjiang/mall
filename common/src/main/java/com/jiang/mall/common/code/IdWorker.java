package com.jiang.mall.common.code;

/**
 * @author newjiang
 * @date 2019/6/4
 * @description: ID组件
 */
public class IdWorker implements GUID{

    private final SnowflakeIdWorker worker;

    public IdWorker(SnowflakeIdWorker worker) {
        this.worker = worker;
    }

    @Override
    public String nextId() {
        return String.valueOf(worker.nextId());
    }

}

package com.chenlx.sm.demo.apply.expressno.service;

import java.util.List;

import com.chenlx.sm.demo.apply.expressno.entity.ApplyExpressNoEventEntity;
import com.chenlx.sm.demo.core.RoutingDataSource;

public interface ApplyExpressNoEventService {

    /**
     * 测试组合事务：多个DAO方法组合
     */
    @RoutingDataSource("master-dataSource")
    void test();
    
    /**
     * 新增
     * @param applyExpressNoEvent
     * @return
     */
    @RoutingDataSource("slave-dataSource")
    void add(ApplyExpressNoEventEntity applyExpressNoEvent);

    /**
     * 根据标识删除记录
     * @param id
     * @return
     */
    void deleteById(Long id);

    /**
     * 根据标识更新记录
     * @param applyExpressNoEvent
     * @return
     */
    void updateById(ApplyExpressNoEventEntity applyExpressNoEvent);
    
    /**
     * 根据标识获取记录
     * @param id
     * @return
     */
    ApplyExpressNoEventEntity getById(Long id);
    
    /**
     * 获取所有记录
     * @return
     */
    List<ApplyExpressNoEventEntity> getAll();
    
}

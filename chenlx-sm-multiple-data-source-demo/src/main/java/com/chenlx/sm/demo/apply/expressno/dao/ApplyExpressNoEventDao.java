package com.chenlx.sm.demo.apply.expressno.dao;

import java.util.List;

import com.chenlx.sm.demo.apply.expressno.entity.ApplyExpressNoEventEntity;

/**
 * 申请电子面单号事件表DAO
 * 
 * @author Richard
 * @date 2017年3月12日
 * @time 下午1:26:02
 */
public interface ApplyExpressNoEventDao {
    
    /**
     * 新增
     * @param applyExpressNoEvent
     * @return
     */
    int add(ApplyExpressNoEventEntity applyExpressNoEvent);

    /**
     * 根据标识删除记录
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据标识更新记录
     * @param applyExpressNoEvent
     * @return
     */
    int updateById(ApplyExpressNoEventEntity applyExpressNoEvent);
    
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

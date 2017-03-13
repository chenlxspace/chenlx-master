package com.chenlx.sm.demo.apply.expressno.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenlx.sm.demo.apply.expressno.dao.ApplyExpressNoEventDao;
import com.chenlx.sm.demo.apply.expressno.entity.ApplyExpressNoEventEntity;
import com.chenlx.sm.demo.apply.expressno.service.ApplyExpressNoEventService;

@Service
@Transactional
public class ApplyExpressNoEventServiceImpl implements ApplyExpressNoEventService {

    @Autowired
    private ApplyExpressNoEventDao applyExpressNoEventDao;
    
    @Override
    public void add(ApplyExpressNoEventEntity applyExpressNoEvent) {
        applyExpressNoEventDao.add(applyExpressNoEvent);
    }

    @Override
    public void deleteById(Long id) {
        applyExpressNoEventDao.deleteById(id);
    }

    @Override
    public void updateById(ApplyExpressNoEventEntity applyExpressNoEvent) {
        applyExpressNoEventDao.updateById(applyExpressNoEvent);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplyExpressNoEventEntity getById(Long id) {
        return applyExpressNoEventDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplyExpressNoEventEntity> getAll() {
        return applyExpressNoEventDao.getAll();
    }

    @Override
    public void test() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        applyExpressNoEventDao.add(applyExpressNoEvent);
        
        Long id = applyExpressNoEvent.getId();
        
        ApplyExpressNoEventEntity applyExpressNoEventUpdate = applyExpressNoEventDao.getById(id);
        
        applyExpressNoEventUpdate.setContent("update ");
        applyExpressNoEventUpdate.setLastUpdateTime(new Date());
        applyExpressNoEventUpdate.setLastUpdateUserId(2L);
        
        applyExpressNoEventDao.updateById(applyExpressNoEventUpdate);
        
    }

}

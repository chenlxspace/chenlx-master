package com.chenlx.sm.demo.apply.expressno.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlx.sm.demo.apply.expressno.entity.ApplyExpressNoEventEntity;

@ActiveProfiles("develop")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class ApplyExpressNoEventDaoTest {

    @Autowired
    private ApplyExpressNoEventDao applyExpressNoEventDao;

    @Test
    public void testAdd() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        int result = applyExpressNoEventDao.add(applyExpressNoEvent);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testDeleteById() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        int result = applyExpressNoEventDao.add(applyExpressNoEvent);
        Assert.assertEquals(1, result);
        Long id = applyExpressNoEvent.getId();

        result = applyExpressNoEventDao.deleteById(id);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testUpdateById() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        int result = applyExpressNoEventDao.add(applyExpressNoEvent);
        Assert.assertEquals(1, result);

        applyExpressNoEvent.setContent("Update");
        result = applyExpressNoEventDao.updateById(applyExpressNoEvent);
        Assert.assertEquals(1, result);

    }

    @Test
    public void testGetById() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        int result = applyExpressNoEventDao.add(applyExpressNoEvent);
        Assert.assertEquals(1, result);
        Long id = applyExpressNoEvent.getId();

        ApplyExpressNoEventEntity queryResult = applyExpressNoEventDao.getById(id);
        System.out.println(queryResult);
    }

    @Test
    public void testGetAll() {
        List<ApplyExpressNoEventEntity> list = applyExpressNoEventDao.getAll();
        for (ApplyExpressNoEventEntity applyExpressNoEventEntity : list) {
            System.out.println(applyExpressNoEventEntity);
        }
    }

}

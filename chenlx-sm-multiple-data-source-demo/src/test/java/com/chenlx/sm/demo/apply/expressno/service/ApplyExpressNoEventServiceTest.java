package com.chenlx.sm.demo.apply.expressno.service;

import static org.junit.Assert.*;

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
public class ApplyExpressNoEventServiceTest {

    @Autowired
    private ApplyExpressNoEventService applyExpressNoEventService;
    
    @Test
    public void testAdd() {
        ApplyExpressNoEventEntity applyExpressNoEvent = new ApplyExpressNoEventEntity();
        applyExpressNoEvent.setCreateUserId(1L);
        applyExpressNoEvent.setLastUpdateUserId(1L);
        applyExpressNoEvent.init();
        applyExpressNoEventService.add(applyExpressNoEvent);
    }

    @Test
    public void testTest() {
        applyExpressNoEventService.test();
    }
    
    @Test
    public void testDeleteById() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateById() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetById() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAll() {
        fail("Not yet implemented");
    }

}

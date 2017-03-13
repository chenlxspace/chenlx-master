package com.chenlx.sm.demo.core;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Spring 注解式事务 也是利用AOP实现的
 * 动态数据源 Aspect 也是利用AOP实现的
 * Spring 事务是基于数据源的
 * 故要实现动态数据源切换，且在同一个数据源中保证事务起作用
 * 需要注意二者顺序：事务起作用前要把数据源切换回来
 * 
 * @author Richard
 * @date 2017年3月12日
 * @time 下午8:11:11
 */
@Component
@Aspect
@Order(1)
public class MultipleDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class.getName());

    /**
     * 任何返回类型 com.chenlx.sm.demo及子包
     * 
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Before("execution(* com.chenlx.sm.demo.apply.expressno.service..*(..))")
      public void doAround(JoinPoint point) throws Throwable {
        logger.info("进入切面");
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class<?>[] classz = target.getClass().getInterfaces();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        Method m = classz[0].getMethod(method, parameterTypes);
        if (m != null && m.isAnnotationPresent(RoutingDataSource.class)) {
            RoutingDataSource data = m.getAnnotation(RoutingDataSource.class);
            MultipleDataSourceHolder.setDataSource(data.value());
            logger.info("进入切面" + data.value());
        }

    }
    
    
//  @Around("execution(* com.chenlx.sm.demo.apply.expressno.service..*(..))")
//  public Object doAround(ProceedingJoinPoint point) throws Throwable {
//      logger.info("进入切面");
//      Object target = point.getTarget();
//      String method = point.getSignature().getName();
//
//      Class<?>[] classz = target.getClass().getInterfaces();
//
//      Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
//      Method m = classz[0].getMethod(method, parameterTypes);
//      if (m != null && m.isAnnotationPresent(RoutingDataSource.class)) {
//          RoutingDataSource data = m.getAnnotation(RoutingDataSource.class);
//          MultipleDataSourceHolder.setDataSource(data.value());
//          logger.info("进入切面" + data.value());
//      }
//
//      return point.proceed();
//  }
}

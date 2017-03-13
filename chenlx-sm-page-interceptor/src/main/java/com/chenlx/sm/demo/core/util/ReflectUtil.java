package com.chenlx.sm.demo.core.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * @author Richard
 * @date 2017年3月13日
 * @time 上午10:08:39
 */
public class ReflectUtil {
    
    /**
     * 获取Field Value
     * 
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        
        final Field field = ReflectUtil.getField(obj, fieldName);
        
        if (field != null) {
            field.setAccessible(true);
            
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    
    /**
     * 获取Field
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getField(Object obj, String fieldName) {
        Field field = null;
        
        //递归遍历至父类, 获取该Field
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
        }
        
        return field;
    }
    
    /**
     * Field Value 赋值
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        final Field field = ReflectUtil.getField(obj, fieldName);
        
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

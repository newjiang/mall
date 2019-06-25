package com.shao.mall.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author newjiang
 * @date 2019/5/21
 * @description: javabean 转 map
 */
public class Bean2Map {

    public static Map<String, Object> toMap(Object bean) {

        BeanInfo beanInfo = null;

        if (bean == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        try {
            beanInfo = Introspector.getBeanInfo(bean.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : ps) {
            String key = propertyDescriptor.getName();
            if (!"class".equals(key)) {
                Method getter = propertyDescriptor.getReadMethod();
                Object value = null;
                try {
                    value = getter.invoke(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                map.put(toUnderline(key), value);
            }
        }

        return map;
    }

    /**
     * 驼峰转换为下划线
     *
     * @param column 行
     * @return 转化后的数据
     */
    public static String toUnderline(String column) {

        StringBuilder result = new StringBuilder();

        if (column != null && column.length() > 0) {

            result.append(column.substring(0, 1).toLowerCase());

            for (int i = 1; i < column.length(); i++) {
                char ch = column.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }

        }
        return result.toString().toUpperCase();
    }
}

package com.jiang.mall.common.utils;

import com.google.gson.Gson;
import com.jiang.mall.common.exception.BaseException;
import com.jiang.mall.common.model.Page;

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
 * @description: JavaBean 与 Map 互转的工具类
 */
public class BeanMap {

    /**
     * JavaBean 转 Map
     *
     * @param bean JavaBean
     * @return
     */
    public static Map<String, Object> toMap(Object bean) {
        return toMap(bean, false);
    }

    /**
     * JavaBean 转 用于Sql的Map
     *
     * @param bean JavaBean
     * @return Key:大写,驼峰命名
     */
    public static Map<String, Object> toSqlMap(Object bean) {
        return toMap(bean, true);
    }

    /**
     * JavaBean 转 Map
     *
     * @param bean JavaBean
     * @param b    Key是否转大写驼峰命名
     * @return
     */
    private static Map<String, Object> toMap(Object bean, boolean b) {

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

        for (PropertyDescriptor pd : ps) {
            String key = pd.getName();
            if (!"class".equals(key)) {
                Method getter = pd.getReadMethod();
                Object value = null;
                try {
                    value = getter.invoke(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (b) {
                    map.put(toUnderline("`" + key + "`"), value);
                } else {
                    map.put(key, value);
                }
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
    private static String toUnderline(String column) {

        StringBuilder result = new StringBuilder();

        if (column != null && column.length() > 0) {

            for (int i = 0; i < column.length(); i++) {
                char ch = column.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(ch);
                } else {
                    result.append(ch);
                }
            }

        }
        return result.toString().toUpperCase();
    }

    /**
     * Map 转 JavaBean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map<String, Object> map, Class<T> clazz) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            return gson.fromJson(json, clazz);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Map转Bean异常");
        }
    }

    /**
     * Map 转 Page<T>
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Page<T> toPage(Map<String, Object> map, Class<T> clazz) {
        T bean = toBean(map, clazz);
        int no = 1;
        int size = 10;
        boolean isTotal = false;
        try {
            if (map.containsKey("pageSize")) {
                size = Integer.valueOf(map.get("pageSize").toString());
                if (size > 200 || size < 1) {
                    throw new BaseException("页数最小为1，最大为200");
                }
            }
            if (map.containsKey("pageNo")) {
                no = Integer.valueOf(map.get("pageNo").toString());
                if (no < 1) {
                    throw new BaseException("页号必须大于0");
                }
            }
            if (map.containsKey("isTotal")) {
                isTotal = Boolean.valueOf(map.get("isTotal").toString());
            } else {
                isTotal = true;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return new Page(no, size, isTotal, bean);
    }

}

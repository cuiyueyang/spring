package com.example.day1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/19 13:54</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class BeanFactory {

    /**
     * bean容器
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 注册bean
     */
    public void registerBean(String key, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(key, beanDefinition);
    }

    /**
     * 获取bean
     */
    public Object getBean(String key) {
        return beanDefinitionMap.get(key).getBean();
    }

}

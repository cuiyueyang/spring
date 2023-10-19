package com.example.day1;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/19 13:54</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class BeanDefinition {

    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }

}

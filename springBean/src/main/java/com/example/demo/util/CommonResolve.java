package com.example.demo.util;



import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/24 11:09</p>
 *
 * @author jin jie
 **/
@FunctionalInterface
public interface CommonResolve<T> {
    /**
     * 分批处理list
     * @param items
     */
    void resolve(List<T> items) ;
}

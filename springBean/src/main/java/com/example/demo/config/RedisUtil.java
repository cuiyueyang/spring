package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>Description: </p>
 * <p>Date: 2021/8/17 10:36 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Slf4j
@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;
    private static final int DATABASE = 1;

    /**
     * 通过key获取储 存在redis中的value
     * 并释放连接
     * @param key
     * @return 成功返回value 失败返回null
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DATABASE);
            value = jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 返还到连接池
     *
     * @param jedisPool
     * @param jedis
     */
    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param indexdb 选择redis库 0-15
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value, int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {

            log.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * <p>
     * 新增key,并将 key 的生存时间 (以秒为单位)
     * </p>
     *
     * @param key
     * @param seconds 生存时间 单位：秒
     * @param value
     * @return 设置成功时返回 OK 。当 seconds 参数不合法时，返回一个错误。
     */
    public String setex(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DATABASE);
            return jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("失败，key:{}", key);
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

}

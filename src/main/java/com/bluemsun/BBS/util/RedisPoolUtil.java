package com.bluemsun.BBS.util;

import com.bluemsun.BBS.common.RedisPool;
import redis.clients.jedis.Jedis;

public class RedisPoolUtil {
    /**
     * 设置key的有效期，单位是秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            e.printStackTrace();
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * exTime的单位是秒
     * 设置key的生存时间
     *
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            e.printStackTrace();
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * set设置key-value
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * get取value值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * del删除key
     *
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

//    测试用
//    public static void main(String[] args) {
//        Jedis jedis = RedisPool.getJedis();
//    }

}

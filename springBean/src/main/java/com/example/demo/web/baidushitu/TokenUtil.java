package com.example.demo.web.baidushitu;



import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: token工具类</p>
 * <p>Date: 2021/7/27 13:22 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public class TokenUtil {
    private static Map<String, String> map = new HashMap<>();

    private final static long EXPIRES_IN = 2592000 * 1000L;

    /**
     * 获取token
     *
     * @return token值
     */
    public static String isExisted() {
        if (!map.containsKey("token")) {
            return null;
        }
        return map.get("token");
    }

    public static boolean isOverdue(String token) {
        String time = map.get(token);
        return System.currentTimeMillis() > Double.parseDouble(time) ;
    }

    public static void put(String token) {
        String tokenFromMap = isExisted();
        if (tokenFromMap == null) {
            // "token": token从百度获取
            map.put("token", token);
            // token(从百度获取):"过期时间"
            map.put(token, "" + System.currentTimeMillis() + EXPIRES_IN);
        } else {
            map.remove(tokenFromMap);
            map.put("token", token);
            map.put(token, "" + System.currentTimeMillis() + EXPIRES_IN);
        }
    }

    public static String get() {
        return map.get("token");
    }
}

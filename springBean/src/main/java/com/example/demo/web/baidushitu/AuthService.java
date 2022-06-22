package com.example.demo.web.baidushitu;

import com.example.demo.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 获取token</p>
 * <p>Date: 2021/7/27 10:36 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Slf4j
public class AuthService {
    /**
     * 获取权限token
     *
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的 linkcld
        String clientId = "A2N3zKBtAjezgBEGjOMSvhNz";
        // 官网获取的 Secret Key 更新为你注册的 linkcld
        String clientSecret = "B2ZARpXIGjUGpO1ulY9uRjdqSL21hQmx";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //推荐使用POST
            connection.setRequestMethod("POST");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            /**
             * 返回结果示例
             */
            Map<String, String> jsonObject = JsonMapper.defaultMapper().getMapFromJson(result, String.class);
            String accessToken = jsonObject.get("access_token");
            //过期时间30天. 2592000秒
            return accessToken;
        } catch (Exception e) {
            log.error("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static String getToken(){
        String token = TokenUtil.isExisted();
        //如果token不存在或者已过期
        if (token == null || TokenUtil.isOverdue(token)) {
            String auth = getAuth();
            TokenUtil.put(auth);
            return auth;
        } else {
            return TokenUtil.get();
        }
    }

    public static void main(String[] args) {
        String auth = getAuth();
        System.out.println(auth);
    }

}
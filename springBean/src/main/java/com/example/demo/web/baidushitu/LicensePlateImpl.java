package com.example.demo.web.baidushitu;

import cn.hutool.json.JSONObject;
import org.junit.Test;

import java.net.URLEncoder;

/**
 * <p>Description: 获得车牌信息</p>
 * <p>Date: 2021/7/27 16:31 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public class LicensePlateImpl {
    public static String licensePlate() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {
            // 本地文件路径
            String filePath = "C:\\Users\\cuiyueyang\\Pictures\\cs\\1.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getToken();

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String licensePlate(String imgStr) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {

            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getToken();

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test1() {
//        String str = licensePlate();
        String str1 = "{\"words_result\":{\"number\":\"豫H9039F\",\"vertexes_location\":[{\"x\":61,\"y\":42},{\"x\":553,\"y\":39},{\"x\":555,\"y\":181},{\"x\":60,\"y\":184}],\"color\":\"blue\",\"probability\":[0.9998950958,0.9999955893,0.9999792576,0.9999523163,0.9999879599,0.9999988079,0.9999742508,0.9999690652]},\"log_id\":1420199041667818812}\n";
        System.out.println(str1);
        JSONObject jsonObject = new JSONObject(str1);
        JSONObject object = jsonObject.getJSONObject("words_result");
        String plateNumber = object.getStr("number");
        String color = object.getStr("color");
        System.out.println(plateNumber);
        System.out.println(color);
    }

}

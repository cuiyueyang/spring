package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.RestConfig;
import com.example.demo.domain.AmapConfigConstants;
import com.example.demo.domain.DirectionResponse;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Description: </p>
 * <p>Date: 2023/12/27 09:43</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Slf4j
@Service
public class AmapUtil {

    private String key = "cs";

    @Autowired
    private RestTemplate restTemplate;


    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装请求调用的url
        StringBuffer urlBuild = new StringBuffer();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin="+depLongitude+","+depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination="+destLongitude+","+destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key="+key);
        String url = urlBuild.toString();
        log.info("url",url);
        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(url, String.class);
        log.info("高德返回信息",directionEntity.getBody());
        DirectionResponse directionResponse = parseDirectionEntity(directionEntity.getBody());
        //解析接口


        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String body) {
        DirectionResponse directionResponse = null;
        try {
            JSONObject result = JSONObject.parseObject(body);

            if (result.containsKey(AmapConfigConstants.STATUS)) {
                if (result.getIntValue(AmapConfigConstants.STATUS)==1) {
                    if (result.containsKey(AmapConfigConstants.ROUTE)) {
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);

                        directionResponse = new DirectionResponse();
                        if (pathObject.containsKey(AmapConfigConstants.DISTANCE)) {
                            int distance = pathObject.getIntValue(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.containsKey(AmapConfigConstants.DURATION)) {
                            int duration = pathObject.getIntValue(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
            return directionResponse;
        } catch (Exception e) {
            return null;
        }
    }


}

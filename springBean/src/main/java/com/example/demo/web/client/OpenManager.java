package com.example.demo.web.client;


import com.example.demo.domain.StudentInfo;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>@date 2022/8/29 10:00</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface OpenManager {

    @GET("/open/test")
    Call<List<StudentInfo>> testFeignClient();

}

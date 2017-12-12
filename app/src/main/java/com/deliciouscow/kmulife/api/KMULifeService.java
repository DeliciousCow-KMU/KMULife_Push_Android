package com.deliciouscow.kmulife.api;

import com.deliciouscow.kmulife.data.LoginObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by gangdongho on 2017. 11. 21..
 */

public interface KMULifeService {
    @POST("/api/login")
    @FormUrlEncoded
    Call<ResponseBody> login(
            @Field("user_id") String user_id,
            @Field("passwd") String passwd
    );
}

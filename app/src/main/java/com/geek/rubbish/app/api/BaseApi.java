package com.geek.rubbish.app.api;

import com.geek.rubbish.storage.BaseResponse;
import com.geek.rubbish.storage.entity.UserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApi {

    /**
     * 登录
     *
     * @param mobile      手机号码
     * @param md5Password MD5加密密码
     */
    @POST(APIs.API.login)
    Observable<BaseResponse<UserBean>> login(@Query("mobile") String mobile, @Query("md5Password") String md5Password);
}
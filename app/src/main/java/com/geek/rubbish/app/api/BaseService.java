package com.geek.rubbish.app.api;

import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.storage.entity.ResultBean;
import com.geek.rubbish.storage.entity.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 服务器接口
 * Created by Administrator on 2018/3/16.
 */

public interface BaseService {
    /**
     * 获取垃圾分类列表
     */
    @GET(APIs.API.member_list)
    Observable<BaseResponse<BaseArrayData<UserInfoBean>>> memberList(@Query("memberRank") int memberRank);
    /**
     * 获取垃圾分类列表
     */
    @GET(APIs.API.rubbish_category)
    Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory();

    @POST(APIs.API.rubbishRecycle_add)
    Observable<BaseResponse<ResultBean>> rubbishRecycleAdd(@Query("memberId")long memberId, @Query("rubbishCategoryId")int rubbishCategoryId, @Query("weight")int weight, @Query("point")int point);
}

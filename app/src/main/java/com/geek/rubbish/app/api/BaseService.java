package com.geek.rubbish.app.api;

import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 服务器接口
 * Created by Administrator on 2018/3/16.
 */

public interface BaseService {
    /**
     * 获取垃圾分类列表
     */
    @GET(APIs.API.rubbish_category)
    Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory();
}

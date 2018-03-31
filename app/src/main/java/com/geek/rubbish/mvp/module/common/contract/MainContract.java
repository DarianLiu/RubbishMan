package com.geek.rubbish.mvp.module.common.contract;

import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.storage.entity.ResultBean;
import com.geek.rubbish.storage.entity.UserInfoBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void updateMemberView(List<UserInfoBean> memberList);
        void updateRubbishCategoryView(List<RubbishCategoryBean> categoryList);
        void showInfo(String msg);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<BaseArrayData<UserInfoBean>>> memberList(int memberRank);

        Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory();

        /**
         * 保存垃圾回心信息
         * @param memberId
         * @param rubbishCategoryId
         * @param weight
         * @param point
         * @return
         */
        Observable<BaseResponse<ResultBean>> rubbishRecycleAdd(long memberId, int rubbishCategoryId, int weight, int point);
    }
}

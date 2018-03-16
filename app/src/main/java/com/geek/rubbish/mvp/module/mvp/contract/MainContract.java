package com.geek.rubbish.mvp.module.mvp.contract;

import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void updateRubbishCategoryView(List<RubbishCategoryBean> categoryList);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory();
    }
}

package com.geek.rubbish.common.utils;

import android.os.Environment;

/**
 * 常用常量
 * Created by LiuLi on 2017/11/30.
 */

public class Constants {

    /**
     * 文件保存地址
     **/
    public static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/rubbish";

    /**
     * ================================================
     * SharePreference Key
     * ================================================
     */
    public static final String SP_USER_INFO = "user_info";

    public static final String SP_TOKEN = "token";

}

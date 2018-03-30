package com.geek.rubbish.app.api;

/**
 * 服务器接口配置
 * Created by Administrator on 2018/2/1.
 */
public interface APIs {

    /**
     * 测试环境与生产环境切换
     **/
    boolean RELEASE_VERSION = false;

    /**
     * 服务器IP地址
     */
    String IP = RELEASE_VERSION ? "www.yanfumall.com" : "192.168.1.42"; //192.168.1.42  47.94.227.16

    /**
     * 端口号
     */
    String PORT = RELEASE_VERSION ? "" : "";

//    /**
//     * 版本号
//     */
    //String VERSION_CODE = RELEASE_VERSION ? "" : "";

    /**
     * 项目名
     */
    String PROJECT = RELEASE_VERSION ? "" : "shopxx";

    /**
     * 客户端类型（区分APP跟网页端）
     */
    String CLIENT_TYPE = "mobile";

    /**
     * 接口基础URL
     */
    String BASE_URL = "http://" + IP + PORT + "/" + PROJECT + "/" + CLIENT_TYPE + "/";

    /**
     * 服务器请求接口
     */
    interface API {

        /**
         * 垃圾分类
         */
        String rubbish_category = "rubbishcategory/listAll.jhtml";
        /**
         * 登录
         */
        String login = "member/rubbishManLogin.jhtml";
    }
}

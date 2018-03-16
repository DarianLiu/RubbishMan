package com.geek.rubbish.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 垃圾分类
 * Created by Administrator on 2018/3/16.
 */

public class RubbishCategoryBean {

    /**
     * id : 1
     * createDate : 1521111270000
     * modifyDate : 1521111274000
     * version : 1
     * categoryName : 厨房垃圾
     * remark : null
     * point : 10
     * isEnabled : false
     * new : false
     */

    private String id;
    private long createDate;
    private long modifyDate;
    private int version;
    private String categoryName;
    private Object remark;
    private int point;
    private boolean isEnabled;
    @SerializedName("new")
    private boolean newX;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }
}

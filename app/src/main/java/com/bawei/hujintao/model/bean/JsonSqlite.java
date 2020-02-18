package com.bawei.hujintao.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 11:23
 */
@Entity
public class JsonSqlite {
    @Id
    long id;
    String json;
    @Generated(hash = 1458089755)
    public JsonSqlite(long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1890786739)
    public JsonSqlite() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}

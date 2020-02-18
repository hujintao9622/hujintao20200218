package com.bawei.hujintao.model.bean;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 11:58
 */
public class PayBean {

    /**
     * message : 该订单已支付
     * status : 1001
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

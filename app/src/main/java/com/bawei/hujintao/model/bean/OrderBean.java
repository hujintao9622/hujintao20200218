package com.bawei.hujintao.model.bean;

import java.util.List;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:23
 */
public class OrderBean {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/5.jpg","commodityPrice":39,"orderDetailId":14738}],"expressCompName":"京东快递","expressSn":"1001","orderId":"2020021513110567527823","orderStatus":2,"orderTime":1581743466000,"payAmount":117,"payMethod":1,"userId":27823},{"detailList":[{"commentStatus":2,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/5.jpg","commodityPrice":39,"orderDetailId":14737}],"expressCompName":"京东快递","expressSn":"1001","orderId":"2020021513041933427823","orderStatus":9,"orderTime":1581743059000,"payAmount":117,"payMethod":1,"userId":27823},{"detailList":[{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/5.jpg","commodityPrice":39,"orderDetailId":14725}],"expressCompName":"京东快递","expressSn":"1001","orderId":"2020021415363669527823","orderStatus":1,"orderTime":1581665797000,"payAmount":117,"payMethod":1,"userId":27823}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/5.jpg","commodityPrice":39,"orderDetailId":14738}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 2020021513110567527823
         * orderStatus : 2
         * orderTime : 1581743466000
         * payAmount : 117
         * payMethod : 1
         * userId : 27823
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private long orderTime;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 3
             * commodityId : 5
             * commodityName : 双头两用修容笔
             * commodityPic : http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/5.jpg
             * commodityPrice : 39
             * orderDetailId : 14738
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}

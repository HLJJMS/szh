package com.diwaves.news.bean;

import java.util.List;

public class WelltaRecordBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"id":3,"userid":40,"tradeType":10,"cointype":1,"coincount":10,"createdatetime":"2020-06-19 10:00:00","isRead":0,"content":"测试交易","objid":-1,"up":1},{"id":2,"userid":40,"tradeType":10,"cointype":1,"coincount":10,"createdatetime":"2020-06-18 10:00:00","isRead":0,"content":"测试交易","objid":-1,"up":1},{"id":1,"userid":40,"tradeType":10,"cointype":1,"coincount":10,"createdatetime":"2020-06-17 10:00:00","isRead":0,"content":"测试交易","objid":-1,"up":1}]
     * timestamp : 1592371543580
     */

    private boolean success;
    private String message;
    private String code;
    private long timestamp;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 3
         * userid : 40
         * tradeType : 10
         * cointype : 1
         * coincount : 10
         * createdatetime : 2020-06-19 10:00:00
         * isRead : 0
         * content : 测试交易
         * objid : -1
         * up : 1
         */

        private int id;
        private int userid;
        private int tradeType;
        private int cointype;
        private int coincount;
        private String createdatetime;
        private int isRead;
        private String content;
        private int objid;
        private int up;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public int getCointype() {
            return cointype;
        }

        public void setCointype(int cointype) {
            this.cointype = cointype;
        }

        public int getCoincount() {
            return coincount;
        }

        public void setCoincount(int coincount) {
            this.coincount = coincount;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getObjid() {
            return objid;
        }

        public void setObjid(int objid) {
            this.objid = objid;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }
    }
}

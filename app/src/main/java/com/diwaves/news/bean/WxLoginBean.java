package com.diwaves.news.bean;

public class WxLoginBean  {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"openId":"oNfe10mIjDXMBiP6ywd_WacqHX-k"}
     * timestamp : 1595569947035
     */

    private boolean success;
    private String message;
    private int code;
    private ResultBean result;
    private long timestamp;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * openId : oNfe10mIjDXMBiP6ywd_WacqHX-k
         */

        private String openId;
        private String user_Id;

        public String getUser_Id() {
            return user_Id;
        }

        public void setUser_Id(String user_Id) {
            this.user_Id = user_Id;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }
    }
}

package com.diwaves.news.bean;

import com.google.gson.annotations.SerializedName;

public class PayBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"package":"Sign=WXPay","appid":"wx5a097702a5ad39e9","sign":"00AA5231C0FBD0A430E85B07DA3738BC28A7D49A9967543250259CEBAFECAE3C","partnerid":"1600733468","prepayid":"wx231624331175749facc4a1331690234900","noncestr":"1595492693493","timestamp":"1595492693"}
     * timestamp : 1595492721113
     */

    private boolean success;
    private String message;
    private String code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * package : Sign=WXPay
         * appid : wx5a097702a5ad39e9
         * sign : 00AA5231C0FBD0A430E85B07DA3738BC28A7D49A9967543250259CEBAFECAE3C
         * partnerid : 1600733468
         * prepayid : wx231624331175749facc4a1331690234900
         * noncestr : 1595492693493
         * timestamp : 1595492693
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}

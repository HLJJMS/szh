package com.diwaves.news.bean;

import java.util.List;

public class MessageBean {

    /**
     * result : [{"read":1,"reportid":null,"avatarUrl":"http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg","touserid":35,"name":"郑泽辉1","shielding":null,"id":null,"createdatetime":null,"type":null,"userid":40,"content":"你好我是郑泽辉"}]
     * code : 200
     * success : true
     * message : 操作成功！
     * timestamp : 1604045247023
     */
    private List<ResultEntity> result;
    private String code;
    private boolean success;
    private String message;
    private long timestamp;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public class ResultEntity {
        /**
         * read : 1
         * reportid : null
         * avatarUrl : http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg
         * touserid : 35
         * name : 郑泽辉1
         * shielding : null
         * id : null
         * createdatetime : null
         * type : null
         * userid : 40
         * content : 你好我是郑泽辉
         */
        private int read;
        private String reportid;
        private String avatarUrl;
        private int touserid;
        private String name;
        private String shielding;
        private String id;
        private String createdatetime;
        private String type;
        private int userid;
        private String content;

        public void setRead(int read) {
            this.read = read;
        }

        public void setReportid(String reportid) {
            this.reportid = reportid;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public void setTouserid(int touserid) {
            this.touserid = touserid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setShielding(String shielding) {
            this.shielding = shielding;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRead() {
            return read;
        }

        public String getReportid() {
            return reportid;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public int getTouserid() {
            return touserid;
        }

        public String getName() {
            return name;
        }

        public String getShielding() {
            return shielding;
        }

        public String getId() {
            return id;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public String getType() {
            return type;
        }

        public int getUserid() {
            return userid;
        }

        public String getContent() {
            return content;
        }
    }
}

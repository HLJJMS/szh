package com.diwaves.news.bean;

import java.util.List;

public class NewFriendBean {

    /**
     * result : [{"readStatus":1,"requestuserid":40,"requestname":"1","avatarUrl":"http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg","name":"1","createdate":"20-11-10 19:53","id":19,"state":0,"requestAvatarUrl":"http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg","userid":40,"content":null}]
     * code : 200
     * success : true
     * message : 操作成功！
     * timestamp : 1605009358629
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
         * readStatus : 1
         * requestuserid : 40
         * requestname : 1
         * avatarUrl : http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg
         * name : 1
         * createdate : 20-11-10 19:53
         * id : 19
         * state : 0
         * requestAvatarUrl : http://116.62.114.248:10019/image/avatar/20200607/ffd368ecb6884cd881f7b1fa336a9a43.jpg
         * userid : 40
         * content : null
         */
        private int readStatus;
        private int requestuserid;
        private String requestname;
        private String avatarUrl;
        private String name;
        private String createdate;
        private int id;
        private int state;
        private String requestAvatarUrl;
        private int userid;
        private String content;

        public void setReadStatus(int readStatus) {
            this.readStatus = readStatus;
        }

        public void setRequestuserid(int requestuserid) {
            this.requestuserid = requestuserid;
        }

        public void setRequestname(String requestname) {
            this.requestname = requestname;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setRequestAvatarUrl(String requestAvatarUrl) {
            this.requestAvatarUrl = requestAvatarUrl;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReadStatus() {
            return readStatus;
        }

        public int getRequestuserid() {
            return requestuserid;
        }

        public String getRequestname() {
            return requestname;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getName() {
            return name;
        }

        public String getCreatedate() {
            return createdate;
        }

        public int getId() {
            return id;
        }

        public int getState() {
            return state;
        }

        public String getRequestAvatarUrl() {
            return requestAvatarUrl;
        }

        public int getUserid() {
            return userid;
        }

        public String getContent() {
            return content;
        }
    }
}

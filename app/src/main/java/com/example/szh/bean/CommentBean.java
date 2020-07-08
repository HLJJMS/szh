package com.example.szh.bean;

import java.util.List;

public class CommentBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"records":[{"id":1,"content":"这是第一条评论","createdatetime":"2020-05-30","userid":1,"replycount":0,"articleid":109,"state":1,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":-1,"onlyauth":0,"replysList":[],"avatarUrl":"user.png","name":"admin","isUp":null,"articles":null},{"id":2,"content":"这是第一条回复","createdatetime":"2020-05-30","userid":1,"replycount":0,"articleid":109,"state":1,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":-1,"onlyauth":0,"replysList":[],"avatarUrl":"user.png","name":"admin","isUp":null,"articles":null},{"id":15,"content":"3","createdatetime":"2020-07-04 16:18:28","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":4,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null},{"id":16,"content":"3","createdatetime":"2020-07-04 16:18:31","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":4,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null},{"id":17,"content":"1","createdatetime":"2020-07-04 16:20:04","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/image/comment/20200704/112b08ed112642bcb4420bdb8fcaa4e5.jpg","up":0,"reward":0,"pushid":2,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null}],"total":5,"size":10,"current":1,"orders":[],"searchCount":true,"pages":1}
     * timestamp : 1593851046146
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
         * records : [{"id":1,"content":"这是第一条评论","createdatetime":"2020-05-30","userid":1,"replycount":0,"articleid":109,"state":1,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":-1,"onlyauth":0,"replysList":[],"avatarUrl":"user.png","name":"admin","isUp":null,"articles":null},{"id":2,"content":"这是第一条回复","createdatetime":"2020-05-30","userid":1,"replycount":0,"articleid":109,"state":1,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":-1,"onlyauth":0,"replysList":[],"avatarUrl":"user.png","name":"admin","isUp":null,"articles":null},{"id":15,"content":"3","createdatetime":"2020-07-04 16:18:28","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":4,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null},{"id":16,"content":"3","createdatetime":"2020-07-04 16:18:31","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/null","up":0,"reward":0,"pushid":4,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null},{"id":17,"content":"1","createdatetime":"2020-07-04 16:20:04","userid":40,"replycount":0,"articleid":109,"state":0,"pic":"http://myzhu.zicp.vip:43280/image/comment/20200704/112b08ed112642bcb4420bdb8fcaa4e5.jpg","up":0,"reward":0,"pushid":2,"onlyauth":0,"replysList":[],"avatarUrl":"","name":"1","isUp":null,"articles":null}]
         * total : 5
         * size : 10
         * current : 1
         * orders : []
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : 1
             * content : 这是第一条评论
             * createdatetime : 2020-05-30
             * userid : 1
             * replycount : 0
             * articleid : 109
             * state : 1
             * pic : http://myzhu.zicp.vip:43280/null
             * up : 0
             * reward : 0
             * pushid : -1
             * onlyauth : 0
             * replysList : []
             * avatarUrl : user.png
             * name : admin
             * isUp : null
             * articles : null
             */

            private int id;
            private String content;
            private String createdatetime;
            private int userid;
            private int replycount;
            private int articleid;
            private int state;
            private String pic;
            private int up;
            private int reward;
            private int pushid;
            private int onlyauth;
            private String avatarUrl;
            private String name;
            private boolean isUp;
            private Object articles;
            private List<?> replysList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedatetime() {
                return createdatetime;
            }

            public void setCreatedatetime(String createdatetime) {
                this.createdatetime = createdatetime;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getReplycount() {
                return replycount;
            }

            public void setReplycount(int replycount) {
                this.replycount = replycount;
            }

            public int getArticleid() {
                return articleid;
            }

            public void setArticleid(int articleid) {
                this.articleid = articleid;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getUp() {
                return up;
            }

            public void setUp(int up) {
                this.up = up;
            }

            public int getReward() {
                return reward;
            }

            public void setReward(int reward) {
                this.reward = reward;
            }

            public int getPushid() {
                return pushid;
            }

            public void setPushid(int pushid) {
                this.pushid = pushid;
            }

            public int getOnlyauth() {
                return onlyauth;
            }

            public void setOnlyauth(int onlyauth) {
                this.onlyauth = onlyauth;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean getIsUp() {
                return isUp;
            }

            public void setIsUp(boolean isUp) {
                this.isUp = isUp;
            }

            public Object getArticles() {
                return articles;
            }

            public void setArticles(Object articles) {
                this.articles = articles;
            }

            public List<?> getReplysList() {
                return replysList;
            }

            public void setReplysList(List<?> replysList) {
                this.replysList = replysList;
            }
        }
    }
}

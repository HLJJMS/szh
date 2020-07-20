package com.example.szh.bean;

import java.util.List;

public class MyArticleBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"avatarUrl":"http://myzhu.zicp.vip:43280/","name":"1","articlesList":[{"id":10012,"title":"杨宜勇","content":"10017","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":40,"up":0,"push":0,"comment":3,"state":0,"createdate":"2020-06-01 21:52:47","remark":null,"reward":0,"pic":null,"category":1,"audiolength":0,"audiopath":"","contenttext":"杨宜勇","view":4,"website":null,"author":null,"issuetime":null,"link":null,"parentdirid":18,"like":1,"dislike":0,"good":1,"bad":0,"tofans":1,"tofriend":1,"tags":null,"top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}]}
     * timestamp : 1595142004349
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
         * avatarUrl : http://myzhu.zicp.vip:43280/
         * name : 1
         * articlesList : [{"id":10012,"title":"杨宜勇","content":"10017","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":40,"up":0,"push":0,"comment":3,"state":0,"createdate":"2020-06-01 21:52:47","remark":null,"reward":0,"pic":null,"category":1,"audiolength":0,"audiopath":"","contenttext":"杨宜勇","view":4,"website":null,"author":null,"issuetime":null,"link":null,"parentdirid":18,"like":1,"dislike":0,"good":1,"bad":0,"tofans":1,"tofriend":1,"tags":null,"top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}]
         */

        private String avatarUrl;
        private String name;
        private List<ArticlesListBean> articlesList;

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

        public List<ArticlesListBean> getArticlesList() {
            return articlesList;
        }

        public void setArticlesList(List<ArticlesListBean> articlesList) {
            this.articlesList = articlesList;
        }

        public static class ArticlesListBean {
            /**
             * id : 10012
             * title : 杨宜勇
             * content : 10017
             * grandpadirid : null
             * dirid : 21
             * dirname : 财经/沪深/上证指数
             * userid : 40
             * up : 0
             * push : 0
             * comment : 3
             * state : 0
             * createdate : 2020-06-01 21:52:47
             * remark : null
             * reward : 0
             * pic : null
             * category : 1
             * audiolength : 0
             * audiopath :
             * contenttext : 杨宜勇
             * view : 4
             * website : null
             * author : null
             * issuetime : null
             * link : null
             * parentdirid : 18
             * like : 1
             * dislike : 0
             * good : 1
             * bad : 0
             * tofans : 1
             * tofriend : 1
             * tags : null
             * top : 0
             * articlecontent : null
             * name : null
             * avatarUrl : null
             * commentCount : null
             * type : null
             * pushid : null
             */

            private int id;
            private String title;
            private String content;
            private Object grandpadirid;
            private int dirid;
            private String dirname;
            private int userid;
            private int up;
            private int push;
            private int comment;
            private int state;
            private String createdate;
            private Object remark;
            private int reward;
            private Object pic;
            private int category;
            private int audiolength;
            private String audiopath;
            private String contenttext;
            private int view;
            private Object website;
            private Object author;
            private Object issuetime;
            private Object link;
            private int parentdirid;
            private int like;
            private int dislike;
            private int good;
            private int bad;
            private int tofans;
            private int tofriend;
            private Object tags;
            private int top;
            private Object articlecontent;
            private String name;
            private Object avatarUrl;
            private Object commentCount;
            private Object type;
            private Object pushid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getGrandpadirid() {
                return grandpadirid;
            }

            public void setGrandpadirid(Object grandpadirid) {
                this.grandpadirid = grandpadirid;
            }

            public int getDirid() {
                return dirid;
            }

            public void setDirid(int dirid) {
                this.dirid = dirid;
            }

            public String getDirname() {
                return dirname;
            }

            public void setDirname(String dirname) {
                this.dirname = dirname;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getUp() {
                return up;
            }

            public void setUp(int up) {
                this.up = up;
            }

            public int getPush() {
                return push;
            }

            public void setPush(int push) {
                this.push = push;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getReward() {
                return reward;
            }

            public void setReward(int reward) {
                this.reward = reward;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public int getAudiolength() {
                return audiolength;
            }

            public void setAudiolength(int audiolength) {
                this.audiolength = audiolength;
            }

            public String getAudiopath() {
                return audiopath;
            }

            public void setAudiopath(String audiopath) {
                this.audiopath = audiopath;
            }

            public String getContenttext() {
                return contenttext;
            }

            public void setContenttext(String contenttext) {
                this.contenttext = contenttext;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public Object getWebsite() {
                return website;
            }

            public void setWebsite(Object website) {
                this.website = website;
            }

            public Object getAuthor() {
                return author;
            }

            public void setAuthor(Object author) {
                this.author = author;
            }

            public Object getIssuetime() {
                return issuetime;
            }

            public void setIssuetime(Object issuetime) {
                this.issuetime = issuetime;
            }

            public Object getLink() {
                return link;
            }

            public void setLink(Object link) {
                this.link = link;
            }

            public int getParentdirid() {
                return parentdirid;
            }

            public void setParentdirid(int parentdirid) {
                this.parentdirid = parentdirid;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getDislike() {
                return dislike;
            }

            public void setDislike(int dislike) {
                this.dislike = dislike;
            }

            public int getGood() {
                return good;
            }

            public void setGood(int good) {
                this.good = good;
            }

            public int getBad() {
                return bad;
            }

            public void setBad(int bad) {
                this.bad = bad;
            }

            public int getTofans() {
                return tofans;
            }

            public void setTofans(int tofans) {
                this.tofans = tofans;
            }

            public int getTofriend() {
                return tofriend;
            }

            public void setTofriend(int tofriend) {
                this.tofriend = tofriend;
            }

            public Object getTags() {
                return tags;
            }

            public void setTags(Object tags) {
                this.tags = tags;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public Object getArticlecontent() {
                return articlecontent;
            }

            public void setArticlecontent(Object articlecontent) {
                this.articlecontent = articlecontent;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(Object avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public Object getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(Object commentCount) {
                this.commentCount = commentCount;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getPushid() {
                return pushid;
            }

            public void setPushid(Object pushid) {
                this.pushid = pushid;
            }
        }
    }
}

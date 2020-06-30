package com.example.szh.bean;

public class ArticleDetailBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"like":0,"notGood":0,"collection":0,"articles":{"id":21175,"title":"第127届广交会将于6月15日至24日在网上举办","content":"21181","grandpadirid":null,"dirid":4,"dirname":"国内焦点","userid":1,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-06-10 19:47:10","remark":null,"reward":0,"pic":null,"category":1,"audiolength":0,"audiopath":"","contenttext":"<p>6月10日，商务部召开网上专题新闻发布会，介绍第127届广交会有关筹备情况。中国对外贸易中心主任李晋奇介绍，为稳住外贸外资基本盘，扩大对外开放，帮助中外企业开拓市场，中国政府决定，第127届广交会将在网上举办，运用先进信息技术，让中外客商足不出户下订单、做生意。<\/p>","view":15,"website":"21CN","author":"自动","issuetime":"2020-06-10 19:35:28","link":"http://news.21cn.com/domestic/yaowen/a/2020/0610/19/33861678.shtml","parentdirid":1,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"['中国', '李晋奇', '扩大', '对外', '开拓', '外贸', '基本盘', '网上', '户下', '中心', '国际', '国内焦点']","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null},"good":0}
     * timestamp : 1593332983835
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
         * like : 0
         * notGood : 0
         * collection : 0
         * articles : {"id":21175,"title":"第127届广交会将于6月15日至24日在网上举办","content":"21181","grandpadirid":null,"dirid":4,"dirname":"国内焦点","userid":1,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-06-10 19:47:10","remark":null,"reward":0,"pic":null,"category":1,"audiolength":0,"audiopath":"","contenttext":"<p>6月10日，商务部召开网上专题新闻发布会，介绍第127届广交会有关筹备情况。中国对外贸易中心主任李晋奇介绍，为稳住外贸外资基本盘，扩大对外开放，帮助中外企业开拓市场，中国政府决定，第127届广交会将在网上举办，运用先进信息技术，让中外客商足不出户下订单、做生意。<\/p>","view":15,"website":"21CN","author":"自动","issuetime":"2020-06-10 19:35:28","link":"http://news.21cn.com/domestic/yaowen/a/2020/0610/19/33861678.shtml","parentdirid":1,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"['中国', '李晋奇', '扩大', '对外', '开拓', '外贸', '基本盘', '网上', '户下', '中心', '国际', '国内焦点']","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}
         * good : 0
         */

        private int like;
        private int notGood;
        private int collection;
        private ArticlesBean articles;
        private int good;

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getNotGood() {
            return notGood;
        }

        public void setNotGood(int notGood) {
            this.notGood = notGood;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public ArticlesBean getArticles() {
            return articles;
        }

        public void setArticles(ArticlesBean articles) {
            this.articles = articles;
        }

        public int getGood() {
            return good;
        }

        public void setGood(int good) {
            this.good = good;
        }

        public static class ArticlesBean {
            /**
             * id : 21175
             * title : 第127届广交会将于6月15日至24日在网上举办
             * content : 21181
             * grandpadirid : null
             * dirid : 4
             * dirname : 国内焦点
             * userid : 1
             * up : 0
             * push : 0
             * comment : 0
             * state : 0
             * createdate : 2020-06-10 19:47:10
             * remark : null
             * reward : 0
             * pic : null
             * category : 1
             * audiolength : 0
             * audiopath :
             * contenttext : <p>6月10日，商务部召开网上专题新闻发布会，介绍第127届广交会有关筹备情况。中国对外贸易中心主任李晋奇介绍，为稳住外贸外资基本盘，扩大对外开放，帮助中外企业开拓市场，中国政府决定，第127届广交会将在网上举办，运用先进信息技术，让中外客商足不出户下订单、做生意。</p>
             * view : 15
             * website : 21CN
             * author : 自动
             * issuetime : 2020-06-10 19:35:28
             * link : http://news.21cn.com/domestic/yaowen/a/2020/0610/19/33861678.shtml
             * parentdirid : 1
             * like : 0
             * dislike : 0
             * good : 0
             * bad : 0
             * tofans : 1
             * tofriend : 1
             * tags : ['中国', '李晋奇', '扩大', '对外', '开拓', '外贸', '基本盘', '网上', '户下', '中心', '国际', '国内焦点']
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
            private String pic="";
            private int category;
            private int audiolength;
            private String audiopath;
            private String contenttext;
            private int view;
            private String website;
            private String author;
            private String issuetime;
            private String link;
            private int parentdirid;
            private int like;
            private int dislike;
            private int good;
            private int bad;
            private int tofans;
            private int tofriend;
            private String tags;
            private int top;
            private Object articlecontent;
            private Object name;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
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

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getIssuetime() {
                return issuetime;
            }

            public void setIssuetime(String issuetime) {
                this.issuetime = issuetime;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
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

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
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

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
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

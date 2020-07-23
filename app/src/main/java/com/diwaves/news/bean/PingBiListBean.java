package com.diwaves.news.bean;

import java.util.List;

public class PingBiListBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"avatarUrl":"","name":"1","list":{"records":[{"id":3,"userid":40,"articleid":107,"title":"看过的内容","articles":{"id":107,"title":"1111111","content":"104","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":2,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-05-30 11:58:01","remark":"","reward":0,"pic":"","category":1,"audiolength":0,"audiopath":"","contenttext":"<p>erfsf<b>dsfsdfa<i>sadf<\/i>asdfsdf<\/b>asfsd<\/p>","view":53,"website":"","author":"","issuetime":"","link":"","parentdirid":18,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}}],"total":1,"size":100,"current":1,"orders":[],"searchCount":true,"pages":1}}
     * timestamp : 1595312876930
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
         * avatarUrl :
         * name : 1
         * list : {"records":[{"id":3,"userid":40,"articleid":107,"title":"看过的内容","articles":{"id":107,"title":"1111111","content":"104","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":2,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-05-30 11:58:01","remark":"","reward":0,"pic":"","category":1,"audiolength":0,"audiopath":"","contenttext":"<p>erfsf<b>dsfsdfa<i>sadf<\/i>asdfsdf<\/b>asfsd<\/p>","view":53,"website":"","author":"","issuetime":"","link":"","parentdirid":18,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}}],"total":1,"size":100,"current":1,"orders":[],"searchCount":true,"pages":1}
         */

        private String avatarUrl;
        private String name;
        private ListBean list;

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

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * records : [{"id":3,"userid":40,"articleid":107,"title":"看过的内容","articles":{"id":107,"title":"1111111","content":"104","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":2,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-05-30 11:58:01","remark":"","reward":0,"pic":"","category":1,"audiolength":0,"audiopath":"","contenttext":"<p>erfsf<b>dsfsdfa<i>sadf<\/i>asdfsdf<\/b>asfsd<\/p>","view":53,"website":"","author":"","issuetime":"","link":"","parentdirid":18,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}}]
             * total : 1
             * size : 100
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
                 * id : 3
                 * userid : 40
                 * articleid : 107
                 * title : 看过的内容
                 * articles : {"id":107,"title":"1111111","content":"104","grandpadirid":null,"dirid":21,"dirname":"财经/沪深/上证指数","userid":2,"up":0,"push":0,"comment":0,"state":0,"createdate":"2020-05-30 11:58:01","remark":"","reward":0,"pic":"","category":1,"audiolength":0,"audiopath":"","contenttext":"<p>erfsf<b>dsfsdfa<i>sadf<\/i>asdfsdf<\/b>asfsd<\/p>","view":53,"website":"","author":"","issuetime":"","link":"","parentdirid":18,"like":0,"dislike":0,"good":0,"bad":0,"tofans":1,"tofriend":1,"tags":"","top":0,"articlecontent":null,"name":null,"avatarUrl":null,"commentCount":null,"type":null,"pushid":null}
                 */

                private int id;
                private int userid;
                private int articleid;
                private String title;
                private ArticlesBean articles;

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

                public int getArticleid() {
                    return articleid;
                }

                public void setArticleid(int articleid) {
                    this.articleid = articleid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public ArticlesBean getArticles() {
                    return articles;
                }

                public void setArticles(ArticlesBean articles) {
                    this.articles = articles;
                }

                public static class ArticlesBean {
                    /**
                     * id : 107
                     * title : 1111111
                     * content : 104
                     * grandpadirid : null
                     * dirid : 21
                     * dirname : 财经/沪深/上证指数
                     * userid : 2
                     * up : 0
                     * push : 0
                     * comment : 0
                     * state : 0
                     * createdate : 2020-05-30 11:58:01
                     * remark :
                     * reward : 0
                     * pic :
                     * category : 1
                     * audiolength : 0
                     * audiopath :
                     * contenttext : <p>erfsf<b>dsfsdfa<i>sadf</i>asdfsdf</b>asfsd</p>
                     * view : 53
                     * website :
                     * author :
                     * issuetime :
                     * link :
                     * parentdirid : 18
                     * like : 0
                     * dislike : 0
                     * good : 0
                     * bad : 0
                     * tofans : 1
                     * tofriend : 1
                     * tags :
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
                    private String remark;
                    private int reward;
                    private String pic;
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

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
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
    }
}

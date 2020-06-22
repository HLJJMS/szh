package com.example.szh.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class RecommendBean implements MultiItemEntity {
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
     * state : 1
     * createdate : 2020-06-10 19:47:10
     * remark : null
     * reward : 0
     * pic : null
     * category : 1
     * audiolength : 0
     * audiopath :
     * contenttext : <p>6月10日，商务部召开网上专题新闻发布会，介绍第127届广交会有关筹备情况。中国对外贸易中心主任李晋奇介绍，为稳住外贸外资基本盘，扩大对外开放，帮助中外企业开拓市场，中国政府决定，第127届广交会将在网上举办，运用先进信息技术，让中外客商足不出户下订单、做生意。</p>
     * view : 5
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
     * articlecontent : <p>6月10日，商务部召开网上专题新闻发布会，介绍第127届广交会有关筹备情况。中国对外贸易中心主任李晋奇介绍，为稳住外贸外资基本盘，扩大对外开放，帮助中外企业开拓市场，中国政府决定，第127届广交会将在网上举办，运用先进信息技术，让中外客商足不出户下订单、做生意。</p>
     * name : null
     * avatarUrl : null
     * commentCount : null
     * type : 0
     * pushid : null
     */

    private String id;
    private String title;
    private String content;
    private String grandpadirid;
    private String dirid;
    private String dirname;
    private String userid;
    private String up;
    private String push;
    private String comment;
    private String state;
    private String createdate;
    private String remark;
    private String reward;
    private String pic;
    private int category;//1:text article(文字型) 2:voice article(视频)
    private String audiolength;
    private String audiopath;
    private String contenttext;
    private String view;
    private String website;
    private String author;
    private String issuetime;
    private String link;
    private String parentdirid;
    private String like;
    private String dislike;
    private String good;
    private String bad;
    private String tofans;
    private String tofriend;
    private String tags;
    private String top;
    private String articlecontent;
    private String name;
    private String avatarUrl;
    private String commentCount;
    private String type;
    private String pushid;

    @Override
    public int getItemType() {
        return category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGrandpadirid() {
        return grandpadirid;
    }

    public void setGrandpadirid(String grandpadirid) {
        this.grandpadirid = grandpadirid;
    }

    public String getDirid() {
        return dirid;
    }

    public void setDirid(String dirid) {
        this.dirid = dirid;
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
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

    public String getAudiolength() {
        return audiolength;
    }

    public void setAudiolength(String audiolength) {
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

    public String getView() {
        return view;
    }

    public void setView(String view) {
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

    public String getParentdirid() {
        return parentdirid;
    }

    public void setParentdirid(String parentdirid) {
        this.parentdirid = parentdirid;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getTofans() {
        return tofans;
    }

    public void setTofans(String tofans) {
        this.tofans = tofans;
    }

    public String getTofriend() {
        return tofriend;
    }

    public void setTofriend(String tofriend) {
        this.tofriend = tofriend;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }
}

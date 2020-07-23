package com.diwaves.news.bean;

import java.util.List;

public class FriendListBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"id":35,"name":"郑泽辉1","phone":"18646596565","unionId":null,"szhnum":"szh_8417044550","wxname":"szh_8417044550","openId":"","avatarUrl":"image/avatar/20200616/97463940515e4016a5c894d26f886647.png","country":"东城区","province":"北京市","city":"北京市","gender":"男","birthday":"2020-06-10","level":1,"fans":0,"focus":0,"friends":0,"password":"123456","gold":0,"silver":196,"createscore":41,"predictscore":3,"predictcount":0,"state":0,"qrcode":null,"expire_ttl":0,"lastlogintime":null,"createlevel":0,"predictlevel":0,"verificaCode":null}]
     * timestamp : 1594791037005
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
         * id : 35
         * name : 郑泽辉1
         * phone : 18646596565
         * unionId : null
         * szhnum : szh_8417044550
         * wxname : szh_8417044550
         * openId :
         * avatarUrl : image/avatar/20200616/97463940515e4016a5c894d26f886647.png
         * country : 东城区
         * province : 北京市
         * city : 北京市
         * gender : 男
         * birthday : 2020-06-10
         * level : 1
         * fans : 0
         * focus : 0
         * friends : 0
         * password : 123456
         * gold : 0
         * silver : 196
         * createscore : 41
         * predictscore : 3
         * predictcount : 0
         * state : 0
         * qrcode : null
         * expire_ttl : 0
         * lastlogintime : null
         * createlevel : 0
         * predictlevel : 0
         * verificaCode : null
         */

        private int id;
        private String name;
        private String phone;
        private Object unionId;
        private String szhnum;
        private String wxname;
        private String openId;
        private String avatarUrl;
        private String country;
        private String province;
        private String city;
        private String gender;
        private String birthday;
        private int level;
        private int fans;
        private int focus;
        private int friends;
        private String password;
        private int gold;
        private int silver;
        private int createscore;
        private int predictscore;
        private int predictcount;
        private int state;
        private Object qrcode;
        private int expire_ttl;
        private Object lastlogintime;
        private int createlevel;
        private int predictlevel;
        private Object verificaCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getUnionId() {
            return unionId;
        }

        public void setUnionId(Object unionId) {
            this.unionId = unionId;
        }

        public String getSzhnum() {
            return szhnum;
        }

        public void setSzhnum(String szhnum) {
            this.szhnum = szhnum;
        }

        public String getWxname() {
            return wxname;
        }

        public void setWxname(String wxname) {
            this.wxname = wxname;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getFocus() {
            return focus;
        }

        public void setFocus(int focus) {
            this.focus = focus;
        }

        public int getFriends() {
            return friends;
        }

        public void setFriends(int friends) {
            this.friends = friends;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getSilver() {
            return silver;
        }

        public void setSilver(int silver) {
            this.silver = silver;
        }

        public int getCreatescore() {
            return createscore;
        }

        public void setCreatescore(int createscore) {
            this.createscore = createscore;
        }

        public int getPredictscore() {
            return predictscore;
        }

        public void setPredictscore(int predictscore) {
            this.predictscore = predictscore;
        }

        public int getPredictcount() {
            return predictcount;
        }

        public void setPredictcount(int predictcount) {
            this.predictcount = predictcount;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getQrcode() {
            return qrcode;
        }

        public void setQrcode(Object qrcode) {
            this.qrcode = qrcode;
        }

        public int getExpire_ttl() {
            return expire_ttl;
        }

        public void setExpire_ttl(int expire_ttl) {
            this.expire_ttl = expire_ttl;
        }

        public Object getLastlogintime() {
            return lastlogintime;
        }

        public void setLastlogintime(Object lastlogintime) {
            this.lastlogintime = lastlogintime;
        }

        public int getCreatelevel() {
            return createlevel;
        }

        public void setCreatelevel(int createlevel) {
            this.createlevel = createlevel;
        }

        public int getPredictlevel() {
            return predictlevel;
        }

        public void setPredictlevel(int predictlevel) {
            this.predictlevel = predictlevel;
        }

        public Object getVerificaCode() {
            return verificaCode;
        }

        public void setVerificaCode(Object verificaCode) {
            this.verificaCode = verificaCode;
        }
    }
}

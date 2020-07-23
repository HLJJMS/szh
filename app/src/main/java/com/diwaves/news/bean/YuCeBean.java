package com.diwaves.news.bean;

import java.util.List;

public class YuCeBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"avatarUrl":"","name":"1","list":{"records":[{"id":19,"userid":35,"predictid":524,"option":1,"state":2,"silver":20,"silverwin":0,"comment":"","createdatetime":"2020-07-11 08:53:26","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月13日上证指数日线预测"},{"id":16,"userid":40,"predictid":521,"option":1,"state":3,"silver":20,"silverwin":120,"comment":null,"createdatetime":"2020-07-09 17:53:39","hide":0,"result":5,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月10日上证指数日线预测"},{"id":15,"userid":41,"predictid":497,"option":3,"state":3,"silver":20,"silverwin":60,"comment":"平了","createdatetime":"2020-07-04 11:06:36","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日至7月10日周线预测"},{"id":14,"userid":41,"predictid":506,"option":3,"state":3,"silver":50,"silverwin":150,"comment":"zzehui的预测","createdatetime":"2020-07-04 10:39:18","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日上证指数日线预测"},{"id":11,"userid":2,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:41:22","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":9,"userid":30,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:34:44","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":3,"userid":2,"predictid":425,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日至6月5日周线预测"},{"id":2,"userid":2,"predictid":429,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日深证指数日线预测"},{"id":1,"userid":30,"predictid":425,"option":3,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"6月1日至6月5日周线预测"}],"total":9,"size":100,"current":1,"orders":[],"searchCount":true,"pages":1}}
     * timestamp : 1595313028917
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
         * list : {"records":[{"id":19,"userid":35,"predictid":524,"option":1,"state":2,"silver":20,"silverwin":0,"comment":"","createdatetime":"2020-07-11 08:53:26","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月13日上证指数日线预测"},{"id":16,"userid":40,"predictid":521,"option":1,"state":3,"silver":20,"silverwin":120,"comment":null,"createdatetime":"2020-07-09 17:53:39","hide":0,"result":5,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月10日上证指数日线预测"},{"id":15,"userid":41,"predictid":497,"option":3,"state":3,"silver":20,"silverwin":60,"comment":"平了","createdatetime":"2020-07-04 11:06:36","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日至7月10日周线预测"},{"id":14,"userid":41,"predictid":506,"option":3,"state":3,"silver":50,"silverwin":150,"comment":"zzehui的预测","createdatetime":"2020-07-04 10:39:18","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日上证指数日线预测"},{"id":11,"userid":2,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:41:22","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":9,"userid":30,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:34:44","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":3,"userid":2,"predictid":425,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日至6月5日周线预测"},{"id":2,"userid":2,"predictid":429,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日深证指数日线预测"},{"id":1,"userid":30,"predictid":425,"option":3,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"6月1日至6月5日周线预测"}],"total":9,"size":100,"current":1,"orders":[],"searchCount":true,"pages":1}
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
             * records : [{"id":19,"userid":35,"predictid":524,"option":1,"state":2,"silver":20,"silverwin":0,"comment":"","createdatetime":"2020-07-11 08:53:26","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月13日上证指数日线预测"},{"id":16,"userid":40,"predictid":521,"option":1,"state":3,"silver":20,"silverwin":120,"comment":null,"createdatetime":"2020-07-09 17:53:39","hide":0,"result":5,"name":null,"avatarUrl":null,"optionvalue":"大涨(>=1.5%)","title":"7月10日上证指数日线预测"},{"id":15,"userid":41,"predictid":497,"option":3,"state":3,"silver":20,"silverwin":60,"comment":"平了","createdatetime":"2020-07-04 11:06:36","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日至7月10日周线预测"},{"id":14,"userid":41,"predictid":506,"option":3,"state":3,"silver":50,"silverwin":150,"comment":"zzehui的预测","createdatetime":"2020-07-04 10:39:18","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"7月6日上证指数日线预测"},{"id":11,"userid":2,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:41:22","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":9,"userid":30,"predictid":488,"option":2,"state":3,"silver":50,"silverwin":200,"comment":"我感觉这次肯定小涨","createdatetime":"2020-06-26 21:34:44","hide":0,"result":4,"name":null,"avatarUrl":null,"optionvalue":"涨(0.5%=<,<1.5%)","title":"6月29日上证指数日线预测"},{"id":3,"userid":2,"predictid":425,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日至6月5日周线预测"},{"id":2,"userid":2,"predictid":429,"option":5,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"大跌(<=-1.5%)","title":"6月1日深证指数日线预测"},{"id":1,"userid":30,"predictid":425,"option":3,"state":3,"silver":10,"silverwin":10,"comment":null,"createdatetime":"2020-05-30","hide":0,"result":1,"name":null,"avatarUrl":null,"optionvalue":"平(-0.5=<,<0.5%)","title":"6月1日至6月5日周线预测"}]
             * total : 9
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
                 * id : 19
                 * userid : 35
                 * predictid : 524
                 * option : 1
                 * state : 2
                 * silver : 20
                 * silverwin : 0
                 * comment :
                 * createdatetime : 2020-07-11 08:53:26
                 * hide : 0
                 * result : 1
                 * name : null
                 * avatarUrl : null
                 * optionvalue : 大涨(>=1.5%)
                 * title : 7月13日上证指数日线预测
                 */

                private int id;
                private int userid;
                private int predictid;
                private int option;
                private int state;
                private int silver;
                private int silverwin;
                private String comment;
                private String createdatetime;
                private int hide;
                private int result;
                private Object name;
                private Object avatarUrl;
                private String optionvalue;
                private String title;

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

                public int getPredictid() {
                    return predictid;
                }

                public void setPredictid(int predictid) {
                    this.predictid = predictid;
                }

                public int getOption() {
                    return option;
                }

                public void setOption(int option) {
                    this.option = option;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public int getSilver() {
                    return silver;
                }

                public void setSilver(int silver) {
                    this.silver = silver;
                }

                public int getSilverwin() {
                    return silverwin;
                }

                public void setSilverwin(int silverwin) {
                    this.silverwin = silverwin;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getCreatedatetime() {
                    return createdatetime;
                }

                public void setCreatedatetime(String createdatetime) {
                    this.createdatetime = createdatetime;
                }

                public int getHide() {
                    return hide;
                }

                public void setHide(int hide) {
                    this.hide = hide;
                }

                public int getResult() {
                    return result;
                }

                public void setResult(int result) {
                    this.result = result;
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

                public String getOptionvalue() {
                    return optionvalue;
                }

                public void setOptionvalue(String optionvalue) {
                    this.optionvalue = optionvalue;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}

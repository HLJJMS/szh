package com.diwaves.news.bean;

import java.util.List;

public class BangdanBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"id":1,"title":"资讯","level":1,"parent":0,"comment":1,"createdate":"2020-01-30","dirsList":[{"id":4,"title":"国内","level":2,"parent":1,"comment":0,"createdate":"2020-01-30","dirsList":[]},{"id":5,"title":"国际","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":6,"title":"财经","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":7,"title":"体育","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":8,"title":"文娱","level":2,"parent":1,"comment":1,"createdate":"2020-04-07","dirsList":[]},{"id":9,"title":"游戏","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":10,"title":"教育","level":2,"parent":1,"comment":1,"createdate":"2020-03-17","dirsList":[]},{"id":11,"title":"房产","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":12,"title":"汽车","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":13,"title":"女人","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":14,"title":"科技","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":15,"title":"其他","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]}]},{"id":2,"title":"财经","level":1,"parent":0,"comment":1,"createdate":"2020-01-30","dirsList":[{"id":18,"title":"沪深","level":2,"parent":2,"comment":2,"createdate":"2020-01-30","dirsList":[{"id":21,"title":"上证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":24,"title":"深证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":28,"title":"创业板指","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]}]}]},{"id":4,"title":"国内","level":2,"parent":1,"comment":0,"createdate":"2020-01-30","dirsList":[]},{"id":5,"title":"国际","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":6,"title":"财经","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":7,"title":"体育","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":8,"title":"文娱","level":2,"parent":1,"comment":1,"createdate":"2020-04-07","dirsList":[]},{"id":9,"title":"游戏","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":10,"title":"教育","level":2,"parent":1,"comment":1,"createdate":"2020-03-17","dirsList":[]},{"id":11,"title":"房产","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":12,"title":"汽车","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":13,"title":"女人","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":14,"title":"科技","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":15,"title":"其他","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":18,"title":"沪深","level":2,"parent":2,"comment":2,"createdate":"2020-01-30","dirsList":[{"id":21,"title":"上证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":24,"title":"深证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":28,"title":"创业板指","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]}]},{"id":21,"title":"上证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":24,"title":"深证指数","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]},{"id":28,"title":"创业板指","level":3,"parent":18,"comment":2,"createdate":"2020-01-30","dirsList":[]}]
     * timestamp : 1593320972273
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
         * id : 1
         * title : 资讯
         * level : 1
         * parent : 0
         * comment : 1
         * createdate : 2020-01-30
         * dirsList : [{"id":4,"title":"国内","level":2,"parent":1,"comment":0,"createdate":"2020-01-30","dirsList":[]},{"id":5,"title":"国际","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":6,"title":"财经","level":2,"parent":1,"comment":1,"createdate":"2020-01-30","dirsList":[]},{"id":7,"title":"体育","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":8,"title":"文娱","level":2,"parent":1,"comment":1,"createdate":"2020-04-07","dirsList":[]},{"id":9,"title":"游戏","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":10,"title":"教育","level":2,"parent":1,"comment":1,"createdate":"2020-03-17","dirsList":[]},{"id":11,"title":"房产","level":2,"parent":1,"comment":1,"createdate":"2020-05-03","dirsList":[]},{"id":12,"title":"汽车","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":13,"title":"女人","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":14,"title":"科技","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]},{"id":15,"title":"其他","level":2,"parent":1,"comment":2,"createdate":"2020-05-03","dirsList":[]}]
         */

        private int id;
        private String title;
        private int level;
        private int parent;
        private int comment;
        private String createdate;
        private List<DirsListBean> dirsList;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public List<DirsListBean> getDirsList() {
            return dirsList;
        }

        public void setDirsList(List<DirsListBean> dirsList) {
            this.dirsList = dirsList;
        }

        public static class DirsListBean {
            /**
             * id : 4
             * title : 国内
             * level : 2
             * parent : 1
             * comment : 0
             * createdate : 2020-01-30
             * dirsList : []
             */

            private int id;
            private String title;
            private int level;
            private int parent;
            private int comment;
            private String createdate;
            private List<DirsListBean> dirsList;

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public List<DirsListBean> getDirsList() {
                return dirsList;
            }

            public void setDirsList(List<DirsListBean> dirsList) {
                this.dirsList = dirsList;
            }
        }
    }
}

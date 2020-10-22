package com.diwaves.news.bean;

import java.util.List;

public class BangdanBean {

    /**
     * result : [{"dirsList":[{"dirsList":[],"parent":1,"level":2,"createdate":"2020-08-17","comment":1,"id":13,"title":"互联网"}],"parent":0,"level":1,"createdate":"2020-01-30","comment":1,"id":1,"title":"时事"},{"dirsList":[{"dirsList":[],"parent":2,"level":2,"createdate":"2020-08-17","comment":1,"id":14,"title":"互联网"},{"dirsList":[],"parent":2,"level":2,"createdate":"2020-08-17","comment":1,"id":25,"title":"原创"},{"dirsList":[],"parent":2,"level":2,"createdate":"2020-08-17","comment":1,"id":31,"title":"沪深股指"}],"parent":0,"level":1,"createdate":"2020-01-30","comment":1,"id":2,"title":"财经"},{"dirsList":[{"dirsList":[],"parent":3,"level":2,"createdate":"2020-08-17","comment":1,"id":15,"title":"互联网"},{"dirsList":[],"parent":3,"level":2,"createdate":"2020-08-17","comment":1,"id":26,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":3,"title":"体育"},{"dirsList":[{"dirsList":[],"parent":4,"level":2,"createdate":"2020-08-17","comment":1,"id":16,"title":"互联网"},{"dirsList":[],"parent":4,"level":2,"createdate":"2020-08-17","comment":1,"id":27,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-04-07","comment":1,"id":4,"title":"文娱"},{"dirsList":[{"dirsList":[],"parent":5,"level":2,"createdate":"2020-08-17","comment":1,"id":17,"title":"互联网"},{"dirsList":[],"parent":5,"level":2,"createdate":"2020-08-17","comment":1,"id":28,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":5,"title":"游戏"},{"dirsList":[{"dirsList":[],"parent":6,"level":2,"createdate":"2020-08-17","comment":1,"id":18,"title":"互联网"},{"dirsList":[],"parent":6,"level":2,"createdate":"2020-08-17","comment":1,"id":29,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-03-17","comment":1,"id":6,"title":"旅游"},{"dirsList":[{"dirsList":[],"parent":7,"level":2,"createdate":"2020-08-17","comment":1,"id":19,"title":"互联网"},{"dirsList":[],"parent":7,"level":2,"createdate":"2020-08-17","comment":1,"id":30,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":7,"title":"情感"},{"dirsList":[{"dirsList":[],"parent":8,"level":2,"createdate":"2020-08-17","comment":1,"id":20,"title":"互联网"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":8,"title":"教育"},{"dirsList":[{"dirsList":[],"parent":9,"level":2,"createdate":"2020-08-17","comment":1,"id":21,"title":"互联网"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":9,"title":"科技"},{"dirsList":[{"dirsList":[],"parent":10,"level":2,"createdate":"2020-08-17","comment":1,"id":22,"title":"互联网"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":10,"title":"IT"},{"dirsList":[{"dirsList":[],"parent":11,"level":2,"createdate":"2020-08-17","comment":1,"id":23,"title":"互联网"}],"parent":0,"level":1,"createdate":"2020-05-03","comment":1,"id":11,"title":"其他"},{"dirsList":[{"dirsList":[],"parent":12,"level":2,"createdate":"2020-08-17","comment":1,"id":24,"title":"互联网"},{"dirsList":[],"parent":12,"level":2,"createdate":"2020-08-17","comment":1,"id":32,"title":"原创"}],"parent":0,"level":1,"createdate":"2020-01-30","comment":1,"id":12,"title":"图片"}]
     * code : 200
     * success : true
     * message : 操作成功！
     * timestamp : 1603369005522
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
         * dirsList : [{"dirsList":[],"parent":1,"level":2,"createdate":"2020-08-17","comment":1,"id":13,"title":"互联网"}]
         * parent : 0
         * level : 1
         * createdate : 2020-01-30
         * comment : 1
         * id : 1
         * title : 时事
         */
        private List<DirsListEntity> dirsList;
        private int parent;
        private int level;
        private String createdate;
        private int comment;
        private int id;
        private String title;

        public void setDirsList(List<DirsListEntity> dirsList) {
            this.dirsList = dirsList;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DirsListEntity> getDirsList() {
            return dirsList;
        }

        public int getParent() {
            return parent;
        }

        public int getLevel() {
            return level;
        }

        public String getCreatedate() {
            return createdate;
        }

        public int getComment() {
            return comment;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public class DirsListEntity {
            /**
             * dirsList : []
             * parent : 1
             * level : 2
             * createdate : 2020-08-17
             * comment : 1
             * id : 13
             * title : 互联网
             */
            private List<?> dirsList;
            private int parent;
            private int level;
            private String createdate;
            private int comment;
            private int id;
            private String title;

            public void setDirsList(List<?> dirsList) {
                this.dirsList = dirsList;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<?> getDirsList() {
                return dirsList;
            }

            public int getParent() {
                return parent;
            }

            public int getLevel() {
                return level;
            }

            public String getCreatedate() {
                return createdate;
            }

            public int getComment() {
                return comment;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}

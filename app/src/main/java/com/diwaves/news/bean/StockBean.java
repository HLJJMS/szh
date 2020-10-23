package com.diwaves.news.bean;

public class StockBean {

    /**
     * result : {"map3":{"change_pct":-7.31,"current":"2666.2832","percentage":"-0.27%","name":"创业板指"},"map2":{"change_pct":-11.09,"current":"13385.0884","percentage":"-0.08%","name":"深证成指"},"map1":{"change_pct":4.97,"current":"3317.4730","percentage":"0.15%","name":"上证指数"}}
     * code : 200
     * success : true
     * message : 操作成功！
     * timestamp : 1603421093069
     */
    private ResultEntity result;
    private String code;
    private boolean success;
    private String message;
    private long timestamp;

    public void setResult(ResultEntity result) {
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

    public ResultEntity getResult() {
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
         * map3 : {"change_pct":-7.31,"current":"2666.2832","percentage":"-0.27%","name":"创业板指"}
         * map2 : {"change_pct":-11.09,"current":"13385.0884","percentage":"-0.08%","name":"深证成指"}
         * map1 : {"change_pct":4.97,"current":"3317.4730","percentage":"0.15%","name":"上证指数"}
         */
        private Map3Entity map3;
        private Map2Entity map2;
        private Map1Entity map1;

        public void setMap3(Map3Entity map3) {
            this.map3 = map3;
        }

        public void setMap2(Map2Entity map2) {
            this.map2 = map2;
        }

        public void setMap1(Map1Entity map1) {
            this.map1 = map1;
        }

        public Map3Entity getMap3() {
            return map3;
        }

        public Map2Entity getMap2() {
            return map2;
        }

        public Map1Entity getMap1() {
            return map1;
        }

        public class Map3Entity {
            /**
             * change_pct : -7.31
             * current : 2666.2832
             * percentage : -0.27%
             * name : 创业板指
             */
            private double change_pct;
            private String current;
            private String percentage;
            private String name;

            public void setChange_pct(double change_pct) {
                this.change_pct = change_pct;
            }

            public void setCurrent(String current) {
                this.current = current;
            }

            public void setPercentage(String percentage) {
                this.percentage = percentage;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getChange_pct() {
                return change_pct;
            }

            public String getCurrent() {
                return current;
            }

            public String getPercentage() {
                return percentage;
            }

            public String getName() {
                return name;
            }
        }

        public class Map2Entity {
            /**
             * change_pct : -11.09
             * current : 13385.0884
             * percentage : -0.08%
             * name : 深证成指
             */
            private double change_pct;
            private String current;
            private String percentage;
            private String name;

            public void setChange_pct(double change_pct) {
                this.change_pct = change_pct;
            }

            public void setCurrent(String current) {
                this.current = current;
            }

            public void setPercentage(String percentage) {
                this.percentage = percentage;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getChange_pct() {
                return change_pct;
            }

            public String getCurrent() {
                return current;
            }

            public String getPercentage() {
                return percentage;
            }

            public String getName() {
                return name;
            }
        }

        public class Map1Entity {
            /**
             * change_pct : 4.97
             * current : 3317.4730
             * percentage : 0.15%
             * name : 上证指数
             */
            private double change_pct;
            private String current;
            private String percentage;
            private String name;

            public void setChange_pct(double change_pct) {
                this.change_pct = change_pct;
            }

            public void setCurrent(String current) {
                this.current = current;
            }

            public void setPercentage(String percentage) {
                this.percentage = percentage;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getChange_pct() {
                return change_pct;
            }

            public String getCurrent() {
                return current;
            }

            public String getPercentage() {
                return percentage;
            }

            public String getName() {
                return name;
            }
        }
    }
}

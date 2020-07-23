package com.diwaves.news.bean;

public class RmbMaketBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"userpredict":null,"predict":{"id":518,"dirid":21,"enddatetime":"2020-07-09 09:15:00","title":"7月9日上证指数日线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-07-09 09:15:00","settleendtime":"2020-07-09 15:00:00","tableid":925,"type":"日线"}}
     * timestamp : 1594272069019
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
         * userpredict : null
         * predict : {"id":518,"dirid":21,"enddatetime":"2020-07-09 09:15:00","title":"7月9日上证指数日线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-07-09 09:15:00","settleendtime":"2020-07-09 15:00:00","tableid":925,"type":"日线"}
         */

        private Object userpredict;
        private PredictBean predict;

        public Object getUserpredict() {
            return userpredict;
        }

        public void setUserpredict(Object userpredict) {
            this.userpredict = userpredict;
        }

        public PredictBean getPredict() {
            return predict;
        }

        public void setPredict(PredictBean predict) {
            this.predict = predict;
        }

        public static class PredictBean {
            /**
             * id : 518
             * dirid : 21
             * enddatetime : 2020-07-09 09:15:00
             * title : 7月9日上证指数日线预测
             * preresulttext :
             * state : 1
             * option1 : 大涨(>=1.5%)
             * option2 : 涨(0.5%=<,<1.5%)
             * option3 : 平(-0.5=<,<0.5%)
             * option4 : 跌(-1.5%=<,<-0.5%)
             * option5 : 大跌(<=-1.5%)
             * result : 0
             * predate :
             * option1value : 0
             * option2value : 0
             * option3value : 0
             * option4value : 0
             * option5value : 0
             * preup : 0
             * settlebegintime : 2020-07-09 09:15:00
             * settleendtime : 2020-07-09 15:00:00
             * tableid : 925
             * type : 日线
             */

            private int id;
            private int dirid;
            private String enddatetime;
            private String title;
            private String preresulttext;
            private int state;
            private String option1;
            private String option2;
            private String option3;
            private String option4;
            private String option5;
            private int result;
            private String predate;
            private int option1value;
            private int option2value;
            private int option3value;
            private int option4value;
            private int option5value;
            private int preup;
            private String settlebegintime;
            private String settleendtime;
            private int tableid;
            private String type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDirid() {
                return dirid;
            }

            public void setDirid(int dirid) {
                this.dirid = dirid;
            }

            public String getEnddatetime() {
                return enddatetime;
            }

            public void setEnddatetime(String enddatetime) {
                this.enddatetime = enddatetime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPreresulttext() {
                return preresulttext;
            }

            public void setPreresulttext(String preresulttext) {
                this.preresulttext = preresulttext;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getOption1() {
                return option1;
            }

            public void setOption1(String option1) {
                this.option1 = option1;
            }

            public String getOption2() {
                return option2;
            }

            public void setOption2(String option2) {
                this.option2 = option2;
            }

            public String getOption3() {
                return option3;
            }

            public void setOption3(String option3) {
                this.option3 = option3;
            }

            public String getOption4() {
                return option4;
            }

            public void setOption4(String option4) {
                this.option4 = option4;
            }

            public String getOption5() {
                return option5;
            }

            public void setOption5(String option5) {
                this.option5 = option5;
            }

            public int getResult() {
                return result;
            }

            public void setResult(int result) {
                this.result = result;
            }

            public String getPredate() {
                return predate;
            }

            public void setPredate(String predate) {
                this.predate = predate;
            }

            public int getOption1value() {
                return option1value;
            }

            public void setOption1value(int option1value) {
                this.option1value = option1value;
            }

            public int getOption2value() {
                return option2value;
            }

            public void setOption2value(int option2value) {
                this.option2value = option2value;
            }

            public int getOption3value() {
                return option3value;
            }

            public void setOption3value(int option3value) {
                this.option3value = option3value;
            }

            public int getOption4value() {
                return option4value;
            }

            public void setOption4value(int option4value) {
                this.option4value = option4value;
            }

            public int getOption5value() {
                return option5value;
            }

            public void setOption5value(int option5value) {
                this.option5value = option5value;
            }

            public int getPreup() {
                return preup;
            }

            public void setPreup(int preup) {
                this.preup = preup;
            }

            public String getSettlebegintime() {
                return settlebegintime;
            }

            public void setSettlebegintime(String settlebegintime) {
                this.settlebegintime = settlebegintime;
            }

            public String getSettleendtime() {
                return settleendtime;
            }

            public void setSettleendtime(String settleendtime) {
                this.settleendtime = settleendtime;
            }

            public int getTableid() {
                return tableid;
            }

            public void setTableid(int tableid) {
                this.tableid = tableid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

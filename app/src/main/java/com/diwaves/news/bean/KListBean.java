package com.diwaves.news.bean;


import java.io.Serializable;

public class KListBean {

    /**
     * shang : {"date":"20-12-23 15:00","change_pct":0,"current":"3356.7800","percentage":"0.0%","name":"上证指数","predict":{"id":12340,"dirid":31,"enddatetime":"2020-12-23 09:15:00","title":"12月23日上证指数日线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-12-23 09:15:00","settleendtime":"2020-12-23 15:00:00","tableid":1038,"type":"日线","zstype":1}}
     * chuang : {"date":"20-12-23 15:00","change_pct":0,"current":"2811.7500","percentage":"0.0%","name":"创业板指","predict":{"id":12247,"dirid":31,"enddatetime":"2020-12-16 09:15:00","title":"12月上证指数月线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-12-01 09:15:00","settleendtime":"2020-12-31 15:00:00","tableid":754,"type":"月线","zstype":1}}
     * sheng : {"date":"20-12-23 15:00","change_pct":0,"current":"13882.3000","percentage":"0.0%","name":"深证成指","predict":{"id":12340,"dirid":31,"enddatetime":"2020-12-23 09:15:00","title":"12月23日上证指数日线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-12-23 09:15:00","settleendtime":"2020-12-23 15:00:00","tableid":1038,"type":"日线","zstype":1}}
     */


    private ShangDTO shang;

    private ChuangDTO chuang;
    private ShengDTO sheng;

    public ShangDTO getShang() {
        return shang;
    }

    public void setShang(ShangDTO shang) {
        this.shang = shang;
    }

    public ChuangDTO getChuang() {
        return chuang;
    }

    public void setChuang(ChuangDTO chuang) {
        this.chuang = chuang;
    }

    public ShengDTO getSheng() {
        return sheng;
    }

    public void setSheng(ShengDTO sheng) {
        this.sheng = sheng;
    }

    public static class ShangDTO implements Serializable {
        /**
         * date : 20-12-23 15:00
         * change_pct : 0
         * current : 3356.7800
         * percentage : 0.0%
         * name : 上证指数
         * predict : {"id":12340,"dirid":31,"enddatetime":"2020-12-23 09:15:00","title":"12月23日上证指数日线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-12-23 09:15:00","settleendtime":"2020-12-23 15:00:00","tableid":1038,"type":"日线","zstype":1}
         */

        private String date;
        private String change_pct;
        private String current;
        private String percentage;
        private String name;
        private PredictDTO predict;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getChangePct() {
            return change_pct;
        }

        public void setChangePct(String changePct) {
            this.change_pct = changePct;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PredictDTO getPredict() {
            return predict;
        }

        public void setPredict(PredictDTO predict) {
            this.predict = predict;
        }

        public static class PredictDTO implements Serializable {
            /**
             * id : 12340
             * dirid : 31
             * enddatetime : 2020-12-23 09:15:00
             * title : 12月23日上证指数日线预测
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
             * settlebegintime : 2020-12-23 09:15:00
             * settleendtime : 2020-12-23 15:00:00
             * tableid : 1038
             * type : 日线
             * zstype : 1
             */

            private Integer id;
            private Integer dirid;
            private String enddatetime;
            private String title;
            private String preresulttext;
            private Integer state;
            private String option1;
            private String option2;
            private String option3;
            private String option4;
            private String option5;
            private Integer result;
            private String predate;
            private Integer option1value;
            private Integer option2value;
            private Integer option3value;
            private Integer option4value;
            private Integer option5value;
            private Integer preup;
            private String settlebegintime;
            private String settleendtime;
            private Integer tableid;
            private String type;
            private Integer zstype;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDirid() {
                return dirid;
            }

            public void setDirid(Integer dirid) {
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

            public Integer getState() {
                return state;
            }

            public void setState(Integer state) {
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

            public Integer getResult() {
                return result;
            }

            public void setResult(Integer result) {
                this.result = result;
            }

            public String getPredate() {
                return predate;
            }

            public void setPredate(String predate) {
                this.predate = predate;
            }

            public Integer getOption1value() {
                return option1value;
            }

            public void setOption1value(Integer option1value) {
                this.option1value = option1value;
            }

            public Integer getOption2value() {
                return option2value;
            }

            public void setOption2value(Integer option2value) {
                this.option2value = option2value;
            }

            public Integer getOption3value() {
                return option3value;
            }

            public void setOption3value(Integer option3value) {
                this.option3value = option3value;
            }

            public Integer getOption4value() {
                return option4value;
            }

            public void setOption4value(Integer option4value) {
                this.option4value = option4value;
            }

            public Integer getOption5value() {
                return option5value;
            }

            public void setOption5value(Integer option5value) {
                this.option5value = option5value;
            }

            public Integer getPreup() {
                return preup;
            }

            public void setPreup(Integer preup) {
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

            public Integer getTableid() {
                return tableid;
            }

            public void setTableid(Integer tableid) {
                this.tableid = tableid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getZstype() {
                return zstype;
            }

            public void setZstype(Integer zstype) {
                this.zstype = zstype;
            }
        }
    }

    public static class ChuangDTO implements Serializable {
        /**
         * date : 20-12-23 15:00
         * change_pct : 0
         * current : 2811.7500
         * percentage : 0.0%
         * name : 创业板指
         * predict : {"id":12247,"dirid":31,"enddatetime":"2020-12-16 09:15:00","title":"12月上证指数月线预测","preresulttext":"","state":1,"option1":"大涨(>=1.5%)","option2":"涨(0.5%=<,<1.5%)","option3":"平(-0.5=<,<0.5%)","option4":"跌(-1.5%=<,<-0.5%)","option5":"大跌(<=-1.5%)","result":0,"predate":"","option1value":0,"option2value":0,"option3value":0,"option4value":0,"option5value":0,"preup":0,"settlebegintime":"2020-12-01 09:15:00","settleendtime":"2020-12-31 15:00:00","tableid":754,"type":"月线","zstype":1}
         */

        private String date;
        private String change_pct;
        private String current;
        private String percentage;
        private String name;
        private PredictDTOX predict;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getChangePct() {
            return change_pct;
        }

        public void setChangePct(String changePct) {
            this.change_pct = changePct;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PredictDTOX getPredict() {
            return predict;
        }

        public void setPredict(PredictDTOX predict) {
            this.predict = predict;
        }

        public static class PredictDTOX implements Serializable {
            /**
             * id : 12247
             * dirid : 31
             * enddatetime : 2020-12-16 09:15:00
             * title : 12月上证指数月线预测
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
             * settlebegintime : 2020-12-01 09:15:00
             * settleendtime : 2020-12-31 15:00:00
             * tableid : 754
             * type : 月线
             * zstype : 1
             */

            private Integer id;
            private Integer dirid;
            private String enddatetime;
            private String title;
            private String preresulttext;
            private Integer state;
            private String option1;
            private String option2;
            private String option3;
            private String option4;
            private String option5;
            private Integer result;
            private String predate;
            private Integer option1value;
            private Integer option2value;
            private Integer option3value;
            private Integer option4value;
            private Integer option5value;
            private Integer preup;
            private String settlebegintime;
            private String settleendtime;
            private Integer tableid;
            private String type;
            private Integer zstype;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDirid() {
                return dirid;
            }

            public void setDirid(Integer dirid) {
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

            public Integer getState() {
                return state;
            }

            public void setState(Integer state) {
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

            public Integer getResult() {
                return result;
            }

            public void setResult(Integer result) {
                this.result = result;
            }

            public String getPredate() {
                return predate;
            }

            public void setPredate(String predate) {
                this.predate = predate;
            }

            public Integer getOption1value() {
                return option1value;
            }

            public void setOption1value(Integer option1value) {
                this.option1value = option1value;
            }

            public Integer getOption2value() {
                return option2value;
            }

            public void setOption2value(Integer option2value) {
                this.option2value = option2value;
            }

            public Integer getOption3value() {
                return option3value;
            }

            public void setOption3value(Integer option3value) {
                this.option3value = option3value;
            }

            public Integer getOption4value() {
                return option4value;
            }

            public void setOption4value(Integer option4value) {
                this.option4value = option4value;
            }

            public Integer getOption5value() {
                return option5value;
            }

            public void setOption5value(Integer option5value) {
                this.option5value = option5value;
            }

            public Integer getPreup() {
                return preup;
            }

            public void setPreup(Integer preup) {
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

            public Integer getTableid() {
                return tableid;
            }

            public void setTableid(Integer tableid) {
                this.tableid = tableid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getZstype() {
                return zstype;
            }

            public void setZstype(Integer zstype) {
                this.zstype = zstype;
            }
        }
    }

    public static class ShengDTO implements Serializable {

        private String date;
        private String change_pct;
        private String current;
        private String percentage;
        private String name;
        private PredictDTOXX predict;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getChangePct() {
            return change_pct;
        }

        public void setChangePct(String changePct) {
            this.change_pct = changePct;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PredictDTOXX getPredict() {
            return predict;
        }

        public void setPredict(PredictDTOXX predict) {
            this.predict = predict;
        }

        public static class PredictDTOXX implements Serializable {
            /**
             * id : 12340
             * dirid : 31
             * enddatetime : 2020-12-23 09:15:00
             * title : 12月23日上证指数日线预测
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
             * settlebegintime : 2020-12-23 09:15:00
             * settleendtime : 2020-12-23 15:00:00
             * tableid : 1038
             * type : 日线
             * zstype : 1
             */

            private Integer id;
            private Integer dirid;
            private String enddatetime;
            private String title;
            private String preresulttext;
            private Integer state;
            private String option1;
            private String option2;
            private String option3;
            private String option4;
            private String option5;
            private Integer result;
            private String predate;
            private Integer option1value;
            private Integer option2value;
            private Integer option3value;
            private Integer option4value;
            private Integer option5value;
            private Integer preup;
            private String settlebegintime;
            private String settleendtime;
            private Integer tableid;
            private String type;
            private Integer zstype;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDirid() {
                return dirid;
            }

            public void setDirid(Integer dirid) {
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

            public Integer getState() {
                return state;
            }

            public void setState(Integer state) {
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

            public Integer getResult() {
                return result;
            }

            public void setResult(Integer result) {
                this.result = result;
            }

            public String getPredate() {
                return predate;
            }

            public void setPredate(String predate) {
                this.predate = predate;
            }

            public Integer getOption1value() {
                return option1value;
            }

            public void setOption1value(Integer option1value) {
                this.option1value = option1value;
            }

            public Integer getOption2value() {
                return option2value;
            }

            public void setOption2value(Integer option2value) {
                this.option2value = option2value;
            }

            public Integer getOption3value() {
                return option3value;
            }

            public void setOption3value(Integer option3value) {
                this.option3value = option3value;
            }

            public Integer getOption4value() {
                return option4value;
            }

            public void setOption4value(Integer option4value) {
                this.option4value = option4value;
            }

            public Integer getOption5value() {
                return option5value;
            }

            public void setOption5value(Integer option5value) {
                this.option5value = option5value;
            }

            public Integer getPreup() {
                return preup;
            }

            public void setPreup(Integer preup) {
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

            public Integer getTableid() {
                return tableid;
            }

            public void setTableid(Integer tableid) {
                this.tableid = tableid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getZstype() {
                return zstype;
            }

            public void setZstype(Integer zstype) {
                this.zstype = zstype;
            }
        }
    }
}

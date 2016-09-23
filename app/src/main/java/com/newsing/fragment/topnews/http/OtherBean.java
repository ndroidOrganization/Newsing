package com.newsing.fragment.topnews.http;

import java.util.List;

/**
 * Created by Angel on 2016/9/23.
 */
public class OtherBean {

    private String reason;
    private List<ResultBean> resultBeen;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return resultBeen;
    }

    public void setResult(List<ResultBean> resultBeen) {
        this.resultBeen = resultBeen;
    }

    public class ResultBean {

        private long stat;
        private List<DataBean> dataBeen;

        public long getStat() {
            return stat;
        }

        public void setStat(long stat) {
            this.stat = stat;
        }

        public List<DataBean> getDataBeen() {
            return dataBeen;
        }

        public void setDataBeen(List<DataBean> dataBeen) {
            this.dataBeen = dataBeen;
        }

        public class DataBean {

            private String title;
            private String date;
            private String author_name;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }
    }
}

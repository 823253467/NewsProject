package com.bwei.zhanghaoqing20170921;

import java.util.List;

/**
 * Created by MK on 2017/9/21.
 */
public class JavaBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-09-21","title":"陈小春应采儿：有种爱情叫一物降一物","description":"剽悍一只猫","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28898894.static/640","url":"https://mp.weixin.qq.com/s?src=16&ver=379&timestamp=1505952028&signature=NUnkbLzssceRxqX8r3lKmi*GRTBhY*UcLiJUcLz3uEIRMID4Z3SoT-OncJAr7wr6ve4I9zXZ8e1JvDkdcjv4y*8PESUNsBIYcZWdiQOVJSo="}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-09-21
         * title : 陈小春应采儿：有种爱情叫一物降一物
         * description : 剽悍一只猫
         * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-28898894.static/640
         * url : https://mp.weixin.qq.com/s?src=16&ver=379&timestamp=1505952028&signature=NUnkbLzssceRxqX8r3lKmi*GRTBhY*UcLiJUcLz3uEIRMID4Z3SoT-OncJAr7wr6ve4I9zXZ8e1JvDkdcjv4y*8PESUNsBIYcZWdiQOVJSo=
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

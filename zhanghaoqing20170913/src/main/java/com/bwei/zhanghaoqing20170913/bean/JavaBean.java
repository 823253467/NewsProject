package com.bwei.zhanghaoqing20170913.bean;

import java.util.List;

/**
 * Created by MK on 2017/9/13.
 */
public class JavaBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-09-13","title":"何冰:它把中国的孩子害惨了！但大部分家长却还在做！","description":"哈佛家训","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-18812496.static/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260832&signature=JslrD*MJk2NQOl3LXxj9FiZ9-ef2Ud3CX8G0ycffJIJ6LgwCF2pZIS5KNUSoZJHRP7k0ylKH1SZi3DBcgwqHUKallmB2U5NTDnYQr8QM0Wo="},{"ctime":"2017-09-13","title":"分手后把水军买到前女友那里去，邓伦是我见过的第一人。","description":"圈内密探","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-45004617.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260832&signature=xyRjGm7EGbiKU2MEvY7fOZFlp0iZXK-0ri59Mt861s4rLdAS4vdAHnG*rpvRHGTeM8Wlu7DHRlie6v0qNu-6W*sfLJdYuT4-Mn8IpS6rIJM="},{"ctime":"2017-09-13","title":"1个好父亲=200个老师，当爹的都好好看看！","description":"掌上育儿","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-33170335.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260831&signature=q-IC7SZiAYKiF28I*GkcFmdwz-VKKZicqwj89kaps35WOIOjvoDlSy35sn8curbUxzn32LLzc*ca*iCQKmV8CCThxTX*w5N7QgEFgxh4Aac="},{"ctime":"2017-09-13","title":"想赶走你所有的不快乐，再给你一个么么哒！","description":"大忘路","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-45059318.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260831&signature=roNSKHEMI*UalW818XfpEGYE44RRCDUbY0qRHKlDTJOWPBJwuWwDlCwzlUj*7W12YseVKtKs0*B9IjBELlJR*Klsu30ZrZ5jxvleMxuibh0="},{"ctime":"2017-09-13","title":"想拉你一把，你的手在哪？ （句句如针）","description":"中外管理杂志","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-45056228.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260831&signature=9SZ21vOiiqKpIPoprPXoWXx0YIfvH-f83NBEXpQb6gy4gjRY2MITUtWZ5n3Dn30lMLa1y6cMj0LJ7bWJezA17iT2uuHSd2i2UdDSQP9Q7Lo="},{"ctime":"2017-09-13","title":"富而不贵是一种痛\u2026\u2026","description":"品味人生","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28219915.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260830&signature=4vqT2u5Kuxixt7gW*GjbCPGkRdv*3UNYr12jR0tVr04xmXYlb7ARDs-GmpxoMQ2QB4gpqRHJIF4sneGrQ1KVVAToa*hRRfnY3vV*mtw7n6Q="}]
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
         * ctime : 2017-09-13
         * title : 何冰:它把中国的孩子害惨了！但大部分家长却还在做！
         * description : 哈佛家训
         * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-18812496.static/640
         * url : https://mp.weixin.qq.com/s?src=16&ver=363&timestamp=1505260832&signature=JslrD*MJk2NQOl3LXxj9FiZ9-ef2Ud3CX8G0ycffJIJ6LgwCF2pZIS5KNUSoZJHRP7k0ylKH1SZi3DBcgwqHUKallmB2U5NTDnYQr8QM0Wo=
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

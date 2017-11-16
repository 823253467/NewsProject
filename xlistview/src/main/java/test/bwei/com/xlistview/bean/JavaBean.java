package test.bwei.com.xlistview.bean;

import java.util.List;

/**
 * Created by MK on 2017/9/9.
 */
public class JavaBean {

    /**
     * reason : 请求成功
     * result : {"list":[{"id":"wechat_20170909017363","title":"这小哥唱得人格都分裂了，就想告诉你古典音乐超有趣的","source":"橄榄古典音乐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43951821.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017363"},{"id":"wechat_20170909017488","title":"江山散文∣年关时节","source":"江山文学","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-37624653.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017488"},{"id":"wechat_20170909017715","title":"散文 | 牛旭斌：风吹我还乡（上）","source":"南部战区","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43952677.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017715"},{"id":"wechat_20170909017770","title":"中国龙是怎么混到今天的地位的？","source":"世界遗产地理","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-32766562.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017770"},{"id":"wechat_20170908089060","title":"她用一把香菜，竟让女儿红遍网络！这位妈妈太会玩儿了！","source":"育儿分享","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-42392090.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170908089060"},{"id":"wechat_20170909014117","title":"田字格里写汉字和数字，这是最标准的格式！（强烈推荐收藏）","source":"教之道","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-39454050.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014117"},{"id":"wechat_20170909014149","title":"同样是杀人无数，为什么关羽死后做了菩萨，而白起却做了猪牛？","source":"命运与信仰","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43329563.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014149"},{"id":"wechat_20170909014845","title":"一年好景君须记，人艰不拆有诗词","source":"报刊文摘","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43946244.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014845"},{"id":"wechat_20170909015140","title":"130万买的翡翠手镯，经鉴定后值200元，气的血压180。","source":"珠宝知识汇","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-31207483.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909015140"},{"id":"wechat_20170909015132","title":"6400元买的翡翠观音只值400块，剩下的钱都买了这张纸","source":"珠宝知识汇","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-31207483.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909015132"}],"totalPage":14231,"ps":10,"pno":1}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * list : [{"id":"wechat_20170909017363","title":"这小哥唱得人格都分裂了，就想告诉你古典音乐超有趣的","source":"橄榄古典音乐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43951821.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017363"},{"id":"wechat_20170909017488","title":"江山散文∣年关时节","source":"江山文学","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-37624653.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017488"},{"id":"wechat_20170909017715","title":"散文 | 牛旭斌：风吹我还乡（上）","source":"南部战区","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43952677.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017715"},{"id":"wechat_20170909017770","title":"中国龙是怎么混到今天的地位的？","source":"世界遗产地理","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-32766562.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017770"},{"id":"wechat_20170908089060","title":"她用一把香菜，竟让女儿红遍网络！这位妈妈太会玩儿了！","source":"育儿分享","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-42392090.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170908089060"},{"id":"wechat_20170909014117","title":"田字格里写汉字和数字，这是最标准的格式！（强烈推荐收藏）","source":"教之道","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-39454050.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014117"},{"id":"wechat_20170909014149","title":"同样是杀人无数，为什么关羽死后做了菩萨，而白起却做了猪牛？","source":"命运与信仰","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43329563.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014149"},{"id":"wechat_20170909014845","title":"一年好景君须记，人艰不拆有诗词","source":"报刊文摘","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-43946244.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909014845"},{"id":"wechat_20170909015140","title":"130万买的翡翠手镯，经鉴定后值200元，气的血压180。","source":"珠宝知识汇","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-31207483.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909015140"},{"id":"wechat_20170909015132","title":"6400元买的翡翠观音只值400块，剩下的钱都买了这张纸","source":"珠宝知识汇","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-31207483.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170909015132"}]
         * totalPage : 14231
         * ps : 10
         * pno : 1
         */

        private int totalPage;
        private int ps;
        private int pno;
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPs() {
            return ps;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }

        public int getPno() {
            return pno;
        }

        public void setPno(int pno) {
            this.pno = pno;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : wechat_20170909017363
             * title : 这小哥唱得人格都分裂了，就想告诉你古典音乐超有趣的
             * source : 橄榄古典音乐
             * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-43951821.jpg/640
             * mark :
             * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170909017363
             */

            private String id;
            private String title;
            private String source;
            private String firstImg;
            private String mark;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
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

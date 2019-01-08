package com.bwie.juan_mao.jingdong_kanglijuan.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/13.
 */

public class NewsBean {

    /**
     * msg : 成功的返回
     * code : 1
     * data : [{"uniquekey":"bae3b9b53490bb98c147fa11df84df44","title":"学生齐动手，校园早干净！珠海中小学18日复课","date":"2018-09-17 21:03","category":"社会","author_name":"珠报融媒","url":"http://mini.eastday.com/mobile/180917210303560.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20180917/20180917210303_412dc19a017b91ddd9570c87585116b9_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20180917/20180917210303_412dc19a017b91ddd9570c87585116b9_1_mwpm_03200403.jpg"},{"uniquekey":"87a6e3e704f729b2fb4c1c0866fedc43","title":"潮汕水灾\u201c被困屋顶的男人\u201d，原来他才是英雄！","date":"2018-09-17 21:02","category":"社会","author_name":"人民网app","url":"http://mini.eastday.com/mobile/180917210253718.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20180917/20180917210253_bb01b6937d9bace952e8e4c186a07fc8_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20180917/20180917210253_bb01b6937d9bace952e8e4c186a07fc8_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20180917/20180917210253_bb01b6937d9bace952e8e4c186a07fc8_4_mwpm_03200403.jpg"},{"uniquekey":"08b512f4bfe885260a473645eaeb0a53","title":"武功山高空玻璃栈道15日开放 最高处海拔超1600米","date":"2018-09-17 21:02","category":"社会","author_name":"井冈山报融媒体","url":"http://mini.eastday.com/mobile/180917210220327.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20180917/20180917210220_9d24b6d7741a979e10b551c1b2ffa7c1_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20180917/20180917210220_9d24b6d7741a979e10b551c1b2ffa7c1_2_mwpm_03200403.jpg"},{"uniquekey":"406976334bb5ebcc5f79d6c8ad1c415f","title":"如何准确地把握好机遇","date":"2018-09-17 21:00","category":"社会","author_name":"囚禁舞台中央","url":"http://mini.eastday.com/mobile/180917210043437.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20180917/20180917210043_339e61ced20c98f450c519ef0f3af461_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20180917/20180917210043_339e61ced20c98f450c519ef0f3af461_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20180917/20180917210043_339e61ced20c98f450c519ef0f3af461_1_mwpm_03200403.jpg"},{"uniquekey":"cde8a13ae021531605eb80966c8b5b96","title":"新华微评：台风来袭，铭记那些逆风而行的身影","date":"2018-09-17 21:00","category":"社会","author_name":"新华社","url":"http://mini.eastday.com/mobile/180917210003949.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20180917/20180917210003_ef9ef250a7d18095cfb47a63078e4d28_1_mwpm_03200403.jpg"},{"uniquekey":"fe7b9216f9c0df668828b6ad42cca869","title":"网络安全威胁\u201c无人能够幸免\u201d，国家、银行、企业各自该做什么？","date":"2018-09-17 20:58","category":"社会","author_name":"文汇网","url":"http://mini.eastday.com/mobile/180917205859990.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20180917/20180917205859_1be3f8a09ebb25ca9860fc67db059750_1_mwpm_03200403.jpg"},{"uniquekey":"5efe65249fb722e660f703a2ee96df48","title":"什么样的跑赛能够风靡全球24年？在迪士尼里跑一圈你就懂了","date":"2018-09-17 20:58","category":"社会","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/180917205832364.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20180917/20180917205832_2d8cbf579edb57396d6d6a372ed70df5_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20180917/20180917205832_2d8cbf579edb57396d6d6a372ed70df5_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20180917/20180917205832_2d8cbf579edb57396d6d6a372ed70df5_5_mwpm_03200403.jpg"},{"uniquekey":"50023421be0ce0ed37432d38f152618f","title":"@嘉定人 星空剧场限时放映 体验360度环幕观影","date":"2018-09-17 20:57","category":"社会","author_name":"上海嘉定","url":"http://mini.eastday.com/mobile/180917205710036.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20180917/20180917205710_1a9406f8f4d936374a3e249c1e9efbb0_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20180917/20180917205710_1a9406f8f4d936374a3e249c1e9efbb0_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20180917/20180917205710_1a9406f8f4d936374a3e249c1e9efbb0_1_mwpm_03200403.jpg"},{"uniquekey":"cd26df62466be814da4652e7a016d13f","title":"台北捷运传歹徒持刀刺人 女子胸口被划伤15厘米","date":"2018-09-17 20:56","category":"社会","author_name":"海外网","url":"http://mini.eastday.com/mobile/180917205635738.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20180917/20180917205635_0fc961882aa959ca4259bce49947576a_1_mwpm_03200403.jpg"},{"uniquekey":"44f9c9e45003016599aea6ed54d1f337","title":"保姆做了一个半月，偷偷拿雇主闲置衣物去卖，案发时偷了一行李箱","date":"2018-09-17 20:56","category":"社会","author_name":"百姓社会热点","url":"http://mini.eastday.com/mobile/180917205626399.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20180917/20180917_39cef343b4ca7fbf9d9d058a063aacf6_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20180917/20180917_da2403f83e77353f39240f7b89a6473c_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20180917/20180917_6f80cf57ac021b603b5027e1569758bf_cover_mwpm_03200403.jpg"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uniquekey : bae3b9b53490bb98c147fa11df84df44
         * title : 学生齐动手，校园早干净！珠海中小学18日复课
         * date : 2018-09-17 21:03
         * category : 社会
         * author_name : 珠报融媒
         * url : http://mini.eastday.com/mobile/180917210303560.html
         * thumbnail_pic_s : http://01imgmini.eastday.com/mobile/20180917/20180917210303_412dc19a017b91ddd9570c87585116b9_2_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://01imgmini.eastday.com/mobile/20180917/20180917210303_412dc19a017b91ddd9570c87585116b9_1_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://07imgmini.eastday.com/mobile/20180917/20180917210253_bb01b6937d9bace952e8e4c186a07fc8_4_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        @SerializedName("author_name")
        private String authorName;
        private String url;
        @SerializedName("thumbnail_pic_s")
        private String thumbnail01;
        @SerializedName("thumbnail_pic_s02")
        private String thumbnail02;
        @SerializedName("thumbnail_pic_s03")
        private String thumbnail03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail01() {
            return thumbnail01;
        }

        public void setThumbnail01(String thumbnail01) {
            this.thumbnail01 = thumbnail01;
        }

        public String getThumbnail02() {
            return thumbnail02;
        }

        public void setThumbnail02(String thumbnail02) {
            this.thumbnail02 = thumbnail02;
        }

        public String getThumbnail03() {
            return thumbnail03;
        }

        public void setThumbnail03(String thumbnail03) {
            this.thumbnail03 = thumbnail03;
        }
    }
}

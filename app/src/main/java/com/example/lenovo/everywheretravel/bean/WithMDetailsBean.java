package com.example.lenovo.everywheretravel.bean;

import java.util.List;

public class WithMDetailsBean {


    /**
     * code : 0
     * desc :
     * result : {"share":{"shareTitle":"我是王自如，看看我的私藏旅行线路！","shareContent":"作为中国电子产品测评第一人，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=25"},"banmi":{"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1500,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg","isFollowed":true,"routesCount":1},"activities":[{"id":120,"content":"大家还有什么好的科技路线推荐或者想看，我们攒一攒，把这个世界好好逛一逛。[haha]","audioURL":"","audioLength":0,"images":[],"firstImageWidth":0,"firstImageHeight":0,"likeCount":7,"replyCount":6,"isLiked":false,"date":"2017-12-09 14:21"},{"id":90,"content":"天台 busking，鼓手是亮点","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511436570151_f0745a40b59677167c098c175ea579eb.jpg"],"firstImageWidth":1920,"firstImageHeight":1080,"likeCount":1,"replyCount":5,"isLiked":false,"date":"2017-11-19 19:29"},{"id":92,"content":"我多希望五年前能遇到今天的自己\n我会问他很多很多的问题\n他会给我讲很多很多的故事\n...","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511437144836_1d8a89961a089f5658c81ab5d41c411a.png"],"firstImageWidth":564,"firstImageHeight":635,"likeCount":2,"replyCount":7,"isLiked":false,"date":"2017-11-01 19:39"},{"id":95,"content":"这也太酷了，满满的回忆啊！","audioURL":"http://cdn.banmi.com/banmiapp/rahdna/1511439927314_9efd2b4c59cf349483223e723e647b4c.mp3","audioLength":92,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511439917710_fa62ef71eb22bba296f08678da26c5f6.jpg"],"firstImageWidth":1200,"firstImageHeight":1799,"likeCount":3,"replyCount":2,"isLiked":false,"date":"2017-10-29 20:26"},{"id":88,"content":"雨中圆觉寺","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511434895393_7c2103a12a133363e9ef08a485441e6c.jpg"],"firstImageWidth":1620,"firstImageHeight":1078,"likeCount":1,"replyCount":3,"isLiked":false,"date":"2017-10-23 19:02"},{"id":87,"content":"闪开，我要开始装逼了！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511431652405_fc040117617535fbbadcc18b5ee4289f.jpg"],"firstImageWidth":958,"firstImageHeight":1280,"likeCount":1,"replyCount":5,"isLiked":false,"date":"2017-10-12 18:07"}],"page":1,"limit":20,"count":6}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * share : {"shareTitle":"我是王自如，看看我的私藏旅行线路！","shareContent":"作为中国电子产品测评第一人，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=25"}
         * banmi : {"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1500,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg","isFollowed":true,"routesCount":1}
         * activities : [{"id":120,"content":"大家还有什么好的科技路线推荐或者想看，我们攒一攒，把这个世界好好逛一逛。[haha]","audioURL":"","audioLength":0,"images":[],"firstImageWidth":0,"firstImageHeight":0,"likeCount":7,"replyCount":6,"isLiked":false,"date":"2017-12-09 14:21"},{"id":90,"content":"天台 busking，鼓手是亮点","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511436570151_f0745a40b59677167c098c175ea579eb.jpg"],"firstImageWidth":1920,"firstImageHeight":1080,"likeCount":1,"replyCount":5,"isLiked":false,"date":"2017-11-19 19:29"},{"id":92,"content":"我多希望五年前能遇到今天的自己\n我会问他很多很多的问题\n他会给我讲很多很多的故事\n...","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511437144836_1d8a89961a089f5658c81ab5d41c411a.png"],"firstImageWidth":564,"firstImageHeight":635,"likeCount":2,"replyCount":7,"isLiked":false,"date":"2017-11-01 19:39"},{"id":95,"content":"这也太酷了，满满的回忆啊！","audioURL":"http://cdn.banmi.com/banmiapp/rahdna/1511439927314_9efd2b4c59cf349483223e723e647b4c.mp3","audioLength":92,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511439917710_fa62ef71eb22bba296f08678da26c5f6.jpg"],"firstImageWidth":1200,"firstImageHeight":1799,"likeCount":3,"replyCount":2,"isLiked":false,"date":"2017-10-29 20:26"},{"id":88,"content":"雨中圆觉寺","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511434895393_7c2103a12a133363e9ef08a485441e6c.jpg"],"firstImageWidth":1620,"firstImageHeight":1078,"likeCount":1,"replyCount":3,"isLiked":false,"date":"2017-10-23 19:02"},{"id":87,"content":"闪开，我要开始装逼了！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1511431652405_fc040117617535fbbadcc18b5ee4289f.jpg"],"firstImageWidth":958,"firstImageHeight":1280,"likeCount":1,"replyCount":5,"isLiked":false,"date":"2017-10-12 18:07"}]
         * page : 1
         * limit : 20
         * count : 6
         */

        private ShareBean share;
        private BanmiBean banmi;
        private int page;
        private int limit;
        private int count;
        private List<ActivitiesBean> activities;

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ActivitiesBean> getActivities() {
            return activities;
        }

        public void setActivities(List<ActivitiesBean> activities) {
            this.activities = activities;
        }

        public static class ShareBean {
            /**
             * shareTitle : 我是王自如，看看我的私藏旅行线路！
             * shareContent : 作为中国电子产品测评第一人，我的生活和旅行路线绝对精彩！
             * shareImage : http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg
             * shareURL : http://banmi.com/app2017/banmi.html?id=25
             */

            private String shareTitle;
            private String shareContent;
            private String shareImage;
            private String shareURL;

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public String getShareImage() {
                return shareImage;
            }

            public void setShareImage(String shareImage) {
                this.shareImage = shareImage;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
        }

        public static class BanmiBean {
            /**
             * id : 25
             * name : 王自如
             * location : 深圳
             * occupation : 中国电子产品测评第一人
             * introduction : Hello你好，我是王自如。借着在日本转机的机会，我来到了东京——这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。
             * following : 1500
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1511430572565_b75a4702424bd6c56540d4ac3ec3754e.jpg
             * isFollowed : true
             * routesCount : 1
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;
            private int routesCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public int getRoutesCount() {
                return routesCount;
            }

            public void setRoutesCount(int routesCount) {
                this.routesCount = routesCount;
            }
        }

        public static class ActivitiesBean {
            /**
             * id : 120
             * content : 大家还有什么好的科技路线推荐或者想看，我们攒一攒，把这个世界好好逛一逛。[haha]
             * audioURL :
             * audioLength : 0
             * images : []
             * firstImageWidth : 0
             * firstImageHeight : 0
             * likeCount : 7
             * replyCount : 6
             * isLiked : false
             * date : 2017-12-09 14:21
             */

            private int id;
            private String content;
            private String audioURL;
            private int audioLength;
            private int firstImageWidth;
            private int firstImageHeight;
            private int likeCount;
            private int replyCount;
            private boolean isLiked;
            private String date;
            private List<?> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAudioURL() {
                return audioURL;
            }

            public void setAudioURL(String audioURL) {
                this.audioURL = audioURL;
            }

            public int getAudioLength() {
                return audioLength;
            }

            public void setAudioLength(int audioLength) {
                this.audioLength = audioLength;
            }

            public int getFirstImageWidth() {
                return firstImageWidth;
            }

            public void setFirstImageWidth(int firstImageWidth) {
                this.firstImageWidth = firstImageWidth;
            }

            public int getFirstImageHeight() {
                return firstImageHeight;
            }

            public void setFirstImageHeight(int firstImageHeight) {
                this.firstImageHeight = firstImageHeight;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public boolean isIsLiked() {
                return isLiked;
            }

            public void setIsLiked(boolean isLiked) {
                this.isLiked = isLiked;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}

package com.example.lenovo.everywheretravel.bean;

import java.util.List;

public class MyCollectBean {

    /**
     * code : 0
     * desc : 处理成功
     * result : {"page":1,"limit":20,"count":1,"collectedRoutes":[{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-09","price":"1.90"}]}
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
         * page : 1
         * limit : 20
         * count : 1
         * collectedRoutes : [{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-09","price":"1.90"}]
         */

        private int page;
        private int limit;
        private int count;
        private List<CollectedRoutesBean> collectedRoutes;

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

        public List<CollectedRoutesBean> getCollectedRoutes() {
            return collectedRoutes;
        }

        public void setCollectedRoutes(List<CollectedRoutesBean> collectedRoutes) {
            this.collectedRoutes = collectedRoutes;
        }

        public static class CollectedRoutesBean {
            /**
             * id : 196
             * cityID : 52
             * title : 濑户内海艺术双岛
             * intro : 直岛·丰岛美术馆巡礼
             * priceInCents : 190
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg
             * isPurchased : false
             * createdAt : 2019-05-09
             * price : 1.90
             */

            private int id;
            private int cityID;
            private String title;
            private String intro;
            private int priceInCents;
            private String cardURL;
            private boolean isPurchased;
            private String createdAt;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}

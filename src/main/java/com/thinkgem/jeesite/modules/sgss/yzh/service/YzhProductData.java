package com.thinkgem.jeesite.modules.sgss.yzh.service;

import java.util.List;

public class YzhProductData {

    /**
     * response_status : true
     * result_data : {"product_data":{"productid":81790,"name":"司顿保温杯","type":"system","thumbnailimage":"http://img.fygift.com//2017/1/7175491920510167570.jpg","brand":"司顿","productcate":89,"productcode":"sty100g","status":"selling","marketprice":218,"retailprice":1,"productplace":"","features":"","hot":false,"createtime":"2016-03-21 18:53:12","is7toreturn":true},"product_image":[{"imageurl":"http://img.fygift.com//2017/1/7175491920510167570.jpg","ordersort":0}],"product_description":"<p><img src=\"http://img.fygift.com/attach/2015/10/1508126865517384259.jpg\" /><\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n","mobile_product_description":""}
     */

    private String response_status;
    private ResultDataBean result_data;

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public ResultDataBean getResult_data() {
        return result_data;
    }

    public void setResult_data(ResultDataBean result_data) {
        this.result_data = result_data;
    }

    public static class ResultDataBean {
        /**
         * product_data : {"productid":81790,"name":"司顿保温杯","type":"system","thumbnailimage":"http://img.fygift.com//2017/1/7175491920510167570.jpg","brand":"司顿","productcate":89,"productcode":"sty100g","status":"selling","marketprice":218,"retailprice":1,"productplace":"","features":"","hot":false,"createtime":"2016-03-21 18:53:12","is7toreturn":true}
         * product_image : [{"imageurl":"http://img.fygift.com//2017/1/7175491920510167570.jpg","ordersort":0}]
         * product_description : <p><img src="http://img.fygift.com/attach/2015/10/1508126865517384259.jpg" /></p>

         <p>&nbsp;</p>
         * mobile_product_description :
         */

        private ProductDataBean product_data;
        private String product_description;
        private String mobile_product_description;
        private List<ProductImageBean> product_image;

        public ProductDataBean getProduct_data() {
            return product_data;
        }

        public void setProduct_data(ProductDataBean product_data) {
            this.product_data = product_data;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public String getMobile_product_description() {
            return mobile_product_description;
        }

        public void setMobile_product_description(String mobile_product_description) {
            this.mobile_product_description = mobile_product_description;
        }

        public List<ProductImageBean> getProduct_image() {
            return product_image;
        }

        public void setProduct_image(List<ProductImageBean> product_image) {
            this.product_image = product_image;
        }

        public static class ProductDataBean {
            /**
             * productid : 81790
             * name : 司顿保温杯
             * type : system
             * thumbnailimage : http://img.fygift.com//2017/1/7175491920510167570.jpg
             * brand : 司顿
             * productcate : 89
             * productcode : sty100g
             * status : selling
             * marketprice : 218.0
             * retailprice : 1.0
             * productplace :
             * features :
             * hot : false
             * createtime : 2016-03-21 18:53:12
             * is7toreturn : true
             */

            private int productid;
            private String name;
            private String type;
            private String thumbnailimage;
            private String brand;
            private int productcate;
            private String productcode;
            private String status;
            private double marketprice;
            private double retailprice;
            private String productplace;
            private String features;
            private boolean hot;
            private String createtime;
            private boolean is7toreturn;

            public int getProductid() {
                return productid;
            }

            public void setProductid(int productid) {
                this.productid = productid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getThumbnailimage() {
                return thumbnailimage;
            }

            public void setThumbnailimage(String thumbnailimage) {
                this.thumbnailimage = thumbnailimage;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getProductcate() {
                return productcate;
            }

            public void setProductcate(int productcate) {
                this.productcate = productcate;
            }

            public String getProductcode() {
                return productcode;
            }

            public void setProductcode(String productcode) {
                this.productcode = productcode;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(double marketprice) {
                this.marketprice = marketprice;
            }

            public double getRetailprice() {
                return retailprice;
            }

            public void setRetailprice(double retailprice) {
                this.retailprice = retailprice;
            }

            public String getProductplace() {
                return productplace;
            }

            public void setProductplace(String productplace) {
                this.productplace = productplace;
            }

            public String getFeatures() {
                return features;
            }

            public void setFeatures(String features) {
                this.features = features;
            }

            public boolean isHot() {
                return hot;
            }

            public void setHot(boolean hot) {
                this.hot = hot;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public boolean isIs7toreturn() {
                return is7toreturn;
            }

            public void setIs7toreturn(boolean is7toreturn) {
                this.is7toreturn = is7toreturn;
            }
        }

        public static class ProductImageBean {
            /**
             * imageurl : http://img.fygift.com//2017/1/7175491920510167570.jpg
             * ordersort : 0
             */

            private String imageurl;
            private int ordersort;

            public String getImageurl() {
                return imageurl;
            }

            public void setImageurl(String imageurl) {
                this.imageurl = imageurl;
            }

            public int getOrdersort() {
                return ordersort;
            }

            public void setOrdersort(int ordersort) {
                this.ordersort = ordersort;
            }
        }
    }
}

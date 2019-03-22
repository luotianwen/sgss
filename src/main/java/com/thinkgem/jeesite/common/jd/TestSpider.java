package com.thinkgem.jeesite.common.jd;

public class TestSpider {
    public static void main(String[] args) {
       jd();
        //tm();
    }
    public static void tm(){
        System.out.println("天猫");
        //tm
        Spider spider = new Spider();
        //给接口注入实现类
        spider.setDownloadable(new DownloadImpl());
        spider.setProcessable(new TMProcessImpl());
        spider.setStoreable(new StoreImple());
        String url="552644285094";//PropertyUtil.getProperty("url");
        String path=PropertyUtil.getProperty("path");
        Page page = spider.download("https://detail.tmall.com/item.htm?id="+url);
        page.setPath(path);
        page.setGoodId(url);
        spider.process(page);
        spider.store(page);
    }
    public static void jd(){
        //jd
        Spider spider = new Spider();
        //给接口注入实现类
        spider.setDownloadable(new DownloadImpl());
        spider.setProcessable(new ProcessImpl());
        spider.setStoreable(new StoreImple());
        String url="27370761933";//PropertyUtil.getProperty("url");
        String path=PropertyUtil.getProperty("path");
        Page page = spider.download("http://item.jd.com/"+url+".html");
        page.setPath(path);
        spider.process(page);
        spider.store(page);
    }
}

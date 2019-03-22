package com.thinkgem.jeesite.common.jd;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessImpl implements Processable {

    public void process(Page page) {

        HtmlCleaner htmlCleaner = new HtmlCleaner();
       // System.out.println(page.getContent());
        TagNode rootNode = htmlCleaner.clean(page.getContent());
        try {
            String goodName = HtmlUtils.getText(rootNode, "//div[@class='item ellipsis']");
            page.setGoodName(goodName);

            List picUrl = HtmlUtils.getListAttributeByName(rootNode, "//div[@class='spec-items']/ul/li/img","data-url","http://img10.360buyimg.com/n1/");
            page.setPicUrl(picUrl);


            String url = page.getDataUrl();
            Pattern compile = Pattern.compile("http://item.jd.com/([0-9]+).html");
            Matcher matcher = compile.matcher(url);
            String goodid = null;
            if (matcher.find()) {
                goodid = matcher.group(1);
                page.setGoodId(goodid);
            }
            String dispicUrl = HtmlUtils.getAttributeByScript(rootNode, "//script","src","http:");
            String pricejson = PageUtil
                    .getContent("http:"+dispicUrl);
            JSONObject j=new JSONObject(pricejson);
            HtmlCleaner disClean = new HtmlCleaner();
            // System.out.println(page.getContent());
            TagNode disNode = disClean.clean(j.getString("content"));

            List disUrl = HtmlUtils.getListAttributeByName(disNode, "//img","data-lazyload","http:");
            page.setDisUrl(disUrl);
           // System.out.println(disUrl);

            //page.setDisUrl(dispicUrl);
          /*
            String pricejson = PageUtil
                    .getContent("http://p.3.cn/prices/get?skuid=J_" + goodid);
            JSONArray jsonArray = new JSONArray(pricejson);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String price = jsonObject.getString("p");
            page.setPrice(price);*/



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

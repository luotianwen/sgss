package com.thinkgem.jeesite.modules.sgss.goods.service;

import com.thinkgem.jeesite.common.jd.HtmlUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncGoods {
    private Cookie acookie;
    private  String www;
    private   String LOGIN_URL="http://m.p-ubit.com/IndexAjax.aspx";
    private Goods goods;
    private   String SaveGood_URL= "http://m.p-ubit.com/Application/YBCustom/ajaxService/CustomHandler.ashx";
    private   String SaveGoodSKU_URL= "http://m.p-ubit.com/Application/FormCustom/AjaxService/ChargingMgrAjax.aspx";
    private CloseableHttpClient httpClient;
    private BasicCookieStore basicCookieStore;
    private String goodId;
    public SyncGoods(Goods goods,String type){
        basicCookieStore=new BasicCookieStore();
        this.goods=goods;
        httpClient= HttpClients
                .custom()
                .setDefaultCookieStore(basicCookieStore)
                .build();
        if(type.equals("1")){
            www="m.p-ubit.com";
        }
        if(type.equals("2")){
            www="byzhgh.p-ubit.com";
        }
        LOGIN_URL="http://"+www+"/IndexAjax.aspx";
        SaveGood_URL= "http://"+www+"/Application/YBCustom/ajaxService/CustomHandler.ashx";
        SaveGoodSKU_URL= "http://"+www+"/Application/FormCustom/AjaxService/ChargingMgrAjax.aspx";
    }

   public String Encrypt(String theText) throws ScriptException, NoSuchMethodException {
       String output="";
       ScriptEngineManager sem = new ScriptEngineManager();
       ScriptEngine engine=sem.getEngineByName("js");
       engine.eval("function Encrypt(theText) {\n" +
               "        output = new String;\n" +
               "        Temp = new Array();\n" +
               "        Temp2 = new Array();\n" +
               "        TextSize = theText.length;\n" +
               "        for (i = 0; i < TextSize; i++) {\n" +
               "            rnd = Math.round(Math.random() * 122) + 68;\n" +
               "            Temp[i] = theText.charCodeAt(i) + rnd;\n" +
               "            Temp2[i] = rnd;\n" +
               "        }\n" +
               "        for (i = 0; i < TextSize; i++) {\n" +
               "            output += String.fromCharCode(Temp[i], Temp2[i]);\n" +
               "        }\n" +
               "        return output;\n" +
               "    }");
       if (engine instanceof Invocable) {
           Invocable in = (Invocable) engine;
           // 执行js函数
           output = (String) in.invokeFunction( "Encrypt",theText);
           //System. out.println("运算结果是：" + output);
       }
       return output;
   }
   public  void saveSku() throws Exception {
      if(StringUtils.isEmpty(goodId)){
          return ;
      }
       HttpPost loginHttpPost=new HttpPost(SaveGoodSKU_URL);
       loginHttpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
       loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
       loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
       loginHttpPost.setHeader("Cache-Control","max-age=0");
       loginHttpPost.setHeader("Connection","keep-alive");
       loginHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
       loginHttpPost.setHeader("Host",www);
       loginHttpPost.setHeader("Origin","http://"+www);
       loginHttpPost.setHeader("Upgrade-Insecure-Requests","1");
       //loginHttpPost.setHeader("Cookie",acookie.getName()+"="+acookie.getValue());
       loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
       List<NameValuePair> loginParams=new ArrayList<NameValuePair>();

       JSONArray jsonArray=new JSONArray();
       for (GoodsSku goodsSku : goods.getGoodsSkuList()) {
               JSONObject jo = new JSONObject();
               jo.put("F_ID", "");
               jo.put("F_GOODSID", goodId);
               jo.put("F_SPECIFICATION1",goodsSku.getSpec1());
               jo.put("F_SPECIFICATION2", goodsSku.getSpec2());
               jo.put("F_SPECIFICATION3", "");
               jo.put("F_ORIGINALPRICE", goodsSku.getMarketPrice());
               jo.put("F_SUPPLIERPRICE", goodsSku.getPrice());
               jo.put("F_INTEGRALPRICE",goodsSku.getMarketPrice());
               jo.put("F_STOCK",goodsSku.getStock());
               jo.put("F_SORT", "1");
               jsonArray.put(jo);
       }
      // System.out.println( jsonArray.toString());
       loginParams.add(new BasicNameValuePair("sid", goodId));
       loginParams.add(new BasicNameValuePair("chargingdetail", jsonArray.toString()));
       loginParams.add(new BasicNameValuePair("rowsdeleteflage", ""));
       loginParams.add(new BasicNameValuePair("name1", goods.getSpec1()));
       loginParams.add(new BasicNameValuePair("name2", goods.getSpec2()));
       loginParams.add(new BasicNameValuePair("name3", ""));
       loginParams.add(new BasicNameValuePair("isSupp", "1"));
       loginParams.add(new BasicNameValuePair("hasPrice", "0"));
       loginParams.add(new BasicNameValuePair("paramType","SaveIntegralCfgData"));

       CloseableHttpResponse loginResponse=null;

           loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
           loginResponse = httpClient.execute(loginHttpPost);

           HttpEntity entity = loginResponse.getEntity();

           System.out.println(EntityUtils.toString(entity));

   }
  public String queryGoods(String artno){
          String id="";

          HttpPost loginHttpPost=new HttpPost("http://m.p-ubit.com/Config/QueryList.aspx?ModuleCode=1071001&brandid=&IsAllRight=1");
          loginHttpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
          loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
          loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
          loginHttpPost.setHeader("Cache-Control","max-age=0");
          loginHttpPost.setHeader("Connection","keep-alive");
          loginHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
          loginHttpPost.setHeader("Host","m.p-ubit.com");
          loginHttpPost.setHeader("Origin","http://m.p-ubit.com");
          loginHttpPost.setHeader("Upgrade-Insecure-Requests","1");
          //loginHttpPost.setHeader("Cookie",acookie.getName()+"="+acookie.getValue());
          loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
          List<NameValuePair> loginParams=new ArrayList<NameValuePair>();
          loginParams.add(new BasicNameValuePair("f_artno",artno));

          CloseableHttpResponse loginResponse=null;
          try {
              loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
              loginResponse = httpClient.execute(loginHttpPost);

              HttpEntity entity = loginResponse.getEntity();
              String content=EntityUtils.toString(entity);
              //System.out.println(content);
              HtmlCleaner htmlCleaner = new HtmlCleaner();
              TagNode rootNode = htmlCleaner.clean(content);
                id = HtmlUtils.getText(rootNode, "//td[@id='Table_List_DataLeft_Td_1_1']");
              System.out.println("id"+id);

          }catch (Exception e){
              e.printStackTrace();
          }
          return id;
      }

  public SyncGoods saveGoods() throws Exception {
      HttpPost loginHttpPost=new HttpPost(SaveGood_URL);
      loginHttpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
      loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
      loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
      loginHttpPost.setHeader("Cache-Control","max-age=0");
      loginHttpPost.setHeader("Connection","keep-alive");
      loginHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
      loginHttpPost.setHeader("Host",www);
      loginHttpPost.setHeader("Origin","http://"+www);
      loginHttpPost.setHeader("Upgrade-Insecure-Requests","1");
      //loginHttpPost.setHeader("Cookie",acookie.getName()+"="+acookie.getValue());
      loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
      List<NameValuePair> loginParams=new ArrayList<NameValuePair>();

      loginParams.add(new BasicNameValuePair("fid", ""));
      loginParams.add(new BasicNameValuePair("brandid",goods.getBrand().getId()));
      loginParams.add(new BasicNameValuePair("supplierid","51"));
      loginParams.add(new BasicNameValuePair("categoryid",goods.getCategoryId()));
      loginParams.add(new BasicNameValuePair("artno",goods.getArtno()));
      loginParams.add(new BasicNameValuePair("sort","1"));
      loginParams.add(new BasicNameValuePair("grounding","0"));
      loginParams.add(new BasicNameValuePair("hasright","1"));
      loginParams.add(new BasicNameValuePair("paramType","IorUGoods"));
      loginParams.add(new BasicNameValuePair("proname",goods.getName()));
      loginParams.add(new BasicNameValuePair("des",goods.getRemarks()));
      loginParams.add(new BasicNameValuePair("picurl1",goods.getLogo()));
      loginParams.add(new BasicNameValuePair("picurl2",goods.getLogo()));
      loginParams.add(new BasicNameValuePair("picurl3",goods.getLogo()));
      loginParams.add(new BasicNameValuePair("picurl4",goods.getDetail().getDetails()));

      //loginParams.add(new BasicNameValuePair("pwd", Encrypt("q1w2e3")));

      CloseableHttpResponse loginResponse=null;

          loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
          loginResponse = httpClient.execute(loginHttpPost);

          HttpEntity entity = loginResponse.getEntity();
          String str=EntityUtils.toString(entity);
          str=str.replaceAll("\\(","").replaceAll("\\)","");
          JSONObject j=new JSONObject(str);
          goodId=j.getString("Data");


      return this;
  }
   public void test(){
      /* File file = new File("D:\\yoyound\\29703812347\\a1.jpg");
       HttpPost post = new HttpPost("http://echo.200please.com");
       FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
       StringBody stringBody1 = new StringBody("Message 1", ContentType.MULTIPART_FORM_DATA);
       StringBody stringBody2 = new StringBody("Message 2", ContentType.MULTIPART_FORM_DATA);
//
       MultipartEntityBuilder builder = MultipartEntityBuilder.create();
       builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
       builder.addPart("upfile", fileBody);
       builder.addPart("text1", stringBody1);
       builder.addPart("text2", stringBody2);
       HttpEntity entity = builder.build();
//
       post.setEntity(entity);

       try {
           HttpResponse response = httpClient.execute(post);
           HttpEntity entity2 = response.getEntity();
           String result = EntityUtils.toString(entity2, "UTF-8");
           //JSON.
           JSONObject j=new JSONObject(result);

           System.out.println("result"+result);
       } catch (IOException e) {
           e.printStackTrace();
       }*/
   }
/*
    public void saveGoodspic(){
        HttpPost loginHttpPost=new HttpPost(SaveGoodPic_URL);

         loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
         loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
         loginHttpPost.setHeader("Cache-Control","private");
         loginHttpPost.setHeader("Connection","keep-alive");
        loginHttpPost.setHeader("Content-Type","multipart/form-data;");
        loginHttpPost.setHeader("Host","m.p-ubit.com");
        loginHttpPost.setHeader("Origin","http://m.p-ubit.com");
        loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
         multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        File file = new File("D:\\yoyound\\29703812347\\a1.jpg");
        FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
        multipartEntityBuilder.addPart("file", fileBody);
        multipartEntityBuilder.addTextBody("IsThumbnail", "1");
        multipartEntityBuilder.addTextBody("ThumbnailMode","HW");
        multipartEntityBuilder.addTextBody("ThumbnailHeight","100");
        multipartEntityBuilder.addTextBody("ThumbnailWidth","100");
        multipartEntityBuilder.addTextBody("paramType","ajaxFileUpload");

        HttpEntity httpEntity = multipartEntityBuilder.build();
        loginHttpPost.setEntity(httpEntity);
        CloseableHttpResponse loginResponse=null;
        try {
            loginResponse = httpClient.execute(loginHttpPost);

           HttpEntity entity = loginResponse.getEntity();
             String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("result"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/



    /**
 * 模拟登录

 * @return
 */
    public SyncGoods login() throws Exception {
        String timestamp=""+new Date().getTime();
        HttpPost loginHttpPost=new HttpPost(LOGIN_URL);
        loginHttpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
        loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
        loginHttpPost.setHeader("Cache-Control","max-age=0");
        loginHttpPost.setHeader("Connection","keep-alive");
        loginHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        loginHttpPost.setHeader("Host",www);
        loginHttpPost.setHeader("Origin","http://"+www);
        loginHttpPost.setHeader("Upgrade-Insecure-Requests","1");
        loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        List<NameValuePair> loginParams=new ArrayList<NameValuePair>();
        loginParams.add(new BasicNameValuePair("name",Encrypt("youyang")));
        loginParams.add(new BasicNameValuePair("r",timestamp));
        loginParams.add(new BasicNameValuePair("txtCheck",""));
        loginParams.add(new BasicNameValuePair("pwd", Encrypt("q1w2e3")));

        CloseableHttpResponse loginResponse=null;
        try {
            loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
            loginResponse = httpClient.execute(loginHttpPost);

            List<Cookie>cookies=basicCookieStore.getCookies();
            if(cookies.isEmpty()){
                System.out.println("The Cookie Is None.");
            }else {
                for(Cookie cookie:cookies){
                    acookie=cookie;
                   // System.out.println(cookie.getName()+"sss"+cookie.getValue());
                   // System.out.println(cookie.toString());
                }
            }
            HttpEntity entity = loginResponse.getEntity();

            System.out.println(EntityUtils.toString(entity));
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

}
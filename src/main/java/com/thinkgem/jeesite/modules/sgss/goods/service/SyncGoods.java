package com.thinkgem.jeesite.modules.sgss.goods.service;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.jd.HtmlUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsSku;
import org.apache.commons.lang3.StringEscapeUtils;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.UriUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

public class SyncGoods {
    private Cookie acookie;
    private  String www;
    private   String LOGIN_URL="http://m.p-ubit.com/IndexAjax.aspx";
    private Goods goods;
    private   String SaveGood_URL= "http://m.p-ubit.com/Application/YBCustom/ajaxService/CustomHandler.ashx";
    private   String SaveGoodSKU_URL= "http://m.p-ubit.com/Application/FormCustom/AjaxService/ChargingMgrAjax.aspx";
    private   String SaveGoodPic_URL = "http://m.p-ubit.com/Application/YBCustom/ajaxService/UploadHandler.ashx";
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
        SaveGoodPic_URL= "http://"+www+"/Application/YBCustom/ajaxService/UploadHandler.ashx";
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


     public   String uploadInputStream( List<InputStream> inputStreams,String ThumbnailMode,String ThumbnailHeight,String ThumbnailWidth){
           StringBuffer sd=new StringBuffer();
            String BOUNDARY = UUID.randomUUID().toString();
            String message="";
            Map<String ,String> params=new HashMap();
            params.put("IsThumbnail", "1");
            params.put("ThumbnailMode", ThumbnailMode);
            params.put("ThumbnailHeight",ThumbnailHeight);
            params.put("ThumbnailWidth", ThumbnailWidth);
            params.put("paramType", "ajaxFileUpload");
            final String NEWLINE = "\r\n";
            final String PREFIX = "--";
            try {
                URL url1=new URL(SaveGoodPic_URL);
                HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod("POST");
                connection.addRequestProperty("Connection","Keep-Alive");
                connection.addRequestProperty("Charset","UTF-8");
                connection.addRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setConnectTimeout(20000);
                DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());

                for(int i = 0; i < inputStreams.size(); i++){
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(NEWLINE);
                    sb.append("Content-Disposition: form-data;  name=\"file[]\"; filename=\"" +i+"\""+NEWLINE);
                    sb.append(NEWLINE);
                    dataOutputStream.write(sb.toString().getBytes());
                    InputStream is = inputStreams.get(i);
                    byte[] bytes = new byte[1024];
                    int len = -1;
                    while((len=is.read(bytes))!=-1) {
                        dataOutputStream.write(bytes, 0, len);
                    }
                    dataOutputStream.write(NEWLINE.getBytes());
                    is.close();
                }
                dataOutputStream.writeBytes("--"+BOUNDARY+"\r\n");
                try {
                    Set<String > keySet=params.keySet();
                    for (String param:keySet){
                        dataOutputStream.writeBytes(PREFIX + BOUNDARY + NEWLINE);
                        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\""
                                +encode(param)+"\"\r\n");
                        dataOutputStream.writeBytes(NEWLINE);
                        String value=params.get(param);
                        dataOutputStream.writeBytes(encode(value)+NEWLINE);
                    }
                }catch (Exception e){

                }
                dataOutputStream.writeBytes(PREFIX+BOUNDARY+NEWLINE);
                InputStream inputStream=connection.getInputStream();
                byte[] data=new byte[1024];
                StringBuffer sb1=new StringBuffer();
                int length=0;
                while ((length=inputStream.read(data))!=-1){
                    String s=new String(data, Charset.forName("utf-8"));
                    sb1.append(s);
                }
                message=sb1.toString();
                //System.out.println(message);
                inputStream.close();
                JSONObject j=new JSONObject(message);

                String serverPath = j.getString("serverPath");
                String[] imgObj = j.getString("msg").split("\\|")[0].split(";");

                for (int i = 0; i < imgObj.length; i++) {
                    sd.append(serverPath + "|" + imgObj[i] + "|" + imgObj[i]+ ";") ;
                }

                //{"isok":"1","msg":"20190618044747474712953922.jpg;201906180447474747562116172.jpg|uploads/201906/18/","serverPath":"uploads/201906/18/"}
                dataOutputStream.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sd.toString();

    }
    public   String uploadFile( List<File> files,String ThumbnailMode,String ThumbnailHeight,String ThumbnailWidth){
        StringBuffer sd=new StringBuffer();
        String BOUNDARY = UUID.randomUUID().toString();
        String message="";
        Map<String ,String> params=new HashMap();
        params.put("IsThumbnail", "1");
        params.put("ThumbnailMode", ThumbnailMode);
        params.put("ThumbnailHeight",ThumbnailHeight);
        params.put("ThumbnailWidth", ThumbnailWidth);
        params.put("paramType", "ajaxFileUpload");
        final String NEWLINE = "\r\n";
        final String PREFIX = "--";
        try {
            URL url1=new URL(SaveGoodPic_URL);
            HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Connection","Keep-Alive");
            connection.addRequestProperty("Charset","UTF-8");
            connection.addRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(20000);
            DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());

            for(int i = 0; i < files.size(); i++){
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(NEWLINE);
                sb.append("Content-Disposition: form-data;  name=\"file[]\"; filename=\"" + URLEncoder.encode(files.get(i).getName(),"UTF-8")+"\""+NEWLINE);
                sb.append(NEWLINE);
                dataOutputStream.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(files.get(i));
                byte[] bytes = new byte[1024];
                int len = -1;
                while((len=is.read(bytes))!=-1) {
                    dataOutputStream.write(bytes, 0, len);
                }
                dataOutputStream.write(NEWLINE.getBytes());
                is.close();
            }
            dataOutputStream.writeBytes("--"+BOUNDARY+"\r\n");
            try {
                Set<String > keySet=params.keySet();
                for (String param:keySet){
                    dataOutputStream.writeBytes(PREFIX + BOUNDARY + NEWLINE);
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\""
                            +encode(param)+"\"\r\n");
                    dataOutputStream.writeBytes(NEWLINE);
                    String value=params.get(param);
                    dataOutputStream.writeBytes(encode(value)+NEWLINE);
                }
            }catch (Exception e){

            }
            dataOutputStream.writeBytes(PREFIX+BOUNDARY+NEWLINE);
            InputStream inputStream=connection.getInputStream();
            byte[] data=new byte[1024];
            StringBuffer sb1=new StringBuffer();
            int length=0;
            while ((length=inputStream.read(data))!=-1){
                String s=new String(data, Charset.forName("utf-8"));
                sb1.append(s);
            }
            message=sb1.toString();
            //System.out.println(message);
            inputStream.close();
            JSONObject j=new JSONObject(message);

            String serverPath = j.getString("serverPath");
            String[] imgObj = j.getString("msg").split("\\|")[0].split(";");

            for (int i = 0; i < imgObj.length; i++) {
                sd.append(serverPath + "|" + imgObj[i] + "|" + imgObj[i]+ ";") ;
            }

            //{"isok":"1","msg":"20190618044747474712953922.jpg;201906180447474747562116172.jpg|uploads/201906/18/","serverPath":"uploads/201906/18/"}
            dataOutputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd.toString();

    }
    private static String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value,"UTF-8");
    }
 private InputStream getInput(String url1) throws Exception {

     URL url = new URL(url1);
     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
     //设置超时间为3秒
     conn.setConnectTimeout(5*1000);
     //防止屏蔽程序抓取而返回403错误
     conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
     //得到输入流
     InputStream is = conn.getInputStream();
     conn.disconnect();

     return is;
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
      String logo=goods.getLogo();
      try {
          logo = UriUtils.decode(logo, "UTF-8");
      } catch (UnsupportedEncodingException e1) {

      }
      File file = new File(Global.getUserfilesBaseDir() + logo);
      List<File>files=new ArrayList<>();
      files.add(file);
      logo=this.uploadFile(files,"HW","100","100");
      loginParams.add(new BasicNameValuePair("picurl1",logo));
      loginParams.add(new BasicNameValuePair("picurl2",logo));
      loginParams.add(new BasicNameValuePair("picurl3",logo));


      String www="http://image.yoyound.com";

      Document containerDoc = Jsoup.parse(StringEscapeUtils.unescapeHtml4(goods.getDetail().getDetails()));
      Elements e=containerDoc.select("img");
      List<File> imgs= Lists.newArrayList();
      List<InputStream>inputStreams=new ArrayList<>();
      for(Element ee:e){
          try {
              logo = UriUtils.decode( ee.attr("src").replaceAll(www,""), "UTF-8");
          } catch (UnsupportedEncodingException e1) {

          }
          if(logo.indexOf("http")<0) {
              File file2 = new File(Global.getUserfilesBaseDir() + logo);
              imgs.add(file2);
          }
          else{
              inputStreams.add(getInput(logo));
          }
      }
      if(imgs.size()>0) {
          logo = this.uploadFile(imgs, "W", "0", "800");
      }
      else {
          logo = this.uploadInputStream(inputStreams, "W", "0", "800");
      }
      loginParams.add(new BasicNameValuePair("picurl4",logo));


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
package com.thinkgem.jeesite.common.jd;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class PageUtil {

    public static String getContent(String url){
        HttpClientBuilder custom = HttpClients.custom();

        CloseableHttpClient build = custom.build();

        HttpGet httpGet = new HttpGet(url);
        String content = null;
        try {

            CloseableHttpResponse response = build.execute(httpGet);

            HttpEntity entity = response.getEntity();

            content = EntityUtils.toString(entity);
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
    public static void getFile(String url,String path){
        url=url.replaceAll("https:","").replaceAll("http:","");
        url="http:"+url.replace("img30.360buyimg.com","img10.360buyimg.com");


        HttpClientBuilder custom = HttpClients.custom();//创建httpclient
        //通过构建器构建一个httpclient对象，可以认为是获取到一个浏览器对象
        CloseableHttpClient build = custom.build();
        //把url封装到get请求中
        HttpGet httpGet = new HttpGet(url);

        try {
            Thread.sleep(1000L);
            //使用client执行get请求,获取请求的结果，请求的结果被封装到response中
            CloseableHttpResponse response = build.execute(httpGet);
            //表示获取返回的内容实体对象
            HttpEntity entity = response.getEntity();

            long contentLength = entity.getContentLength();
            InputStream is = entity.getContent();
            // 根据InputStream 下载文件
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int r = 0;

            while ((r = is.read(buffer)) > 0) {
                output.write(buffer, 0, r);
            }
            FileOutputStream fos = new FileOutputStream(path);
            output.writeTo(fos);
            output.flush();
            output.close();
            fos.close();
            System.out.println("下载"+path+"成功");
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
    /**
     * 模拟请求
     *
     * @param url       资源地址


     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void send(String url,  String path) {
        url=url.replace("https","http").replace("img30.360buyimg.com","img13.360buyimg.com");

        try {
            String body = "";
            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext))
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            HttpClients.custom().setConnectionManager(connManager);

            //创建自定义的httpclient对象
            CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//      CloseableHttpClient client = HttpClients.createDefault();

            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);


            System.out.println("请求地址：" + url);


            //设置header信息
            //指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                long contentLength = entity.getContentLength();
                InputStream is = entity.getContent();

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int r = 0;

                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                }
                FileOutputStream fos = new FileOutputStream(path);
                output.writeTo(fos);
                output.flush();
                output.close();
                fos.close();
                System.out.println("下载" + path + "成功");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
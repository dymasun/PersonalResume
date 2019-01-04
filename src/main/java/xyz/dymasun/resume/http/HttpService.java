package xyz.dymasun.resume.http;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dymasun.resume.rule.RuleMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * httpclient请求的工具包
 */
@Service
public class HttpService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig  requestConfig;
    //直接访问get url
    public String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }
    //带参数发出get请求
    public String get(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        return this.get(uriBuilder.build().toString());
    }
    //通过get下载文件
    public boolean downloadFile(String url,String filename) throws IOException {
//        HttpGet httpGet = new HttpGet(url);
//        httpGet.setConfig(requestConfig);
//        CloseableHttpResponse response = this.httpClient.execute(httpGet);
//        if (response.getStatusLine().getStatusCode() == 200) {
//            byte[] data = EntityUtils.toByteArray(response.getEntity());
//            File file = FileUtils.create(filename);
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(data,0,data.length);
//            fos.flush();
//            fos.close();
//            return true;
//        }
        return false;
    }
    //基础的post请求方法
    private String doPost(String url,HttpEntity entity,String contentType) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", contentType == null ? "application/json; charset=utf-8" : contentType);
        httpPost.addHeader("X-Forwarded-For", "");
        if(entity!=null)
            httpPost.setEntity(entity);
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }
    //表单提交post请求
    public String post(String url, Map<String, Object> map,String contentType) throws Exception {
        UrlEncodedFormEntity urlEncodedFormEntity=null;
        if (map != null) {
           List<NameValuePair> list = new ArrayList<NameValuePair>();
           for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
          urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

        }
        return doPost(url, urlEncodedFormEntity,contentType);
    }

    //表单提交post请求
    public String post(String url, RuleMap rmap,String contentType) throws Exception {
        return post(url,rmap.getMap(),contentType);
    }
    //post传输json
    public String post(String url,String json) throws Exception{
        StringEntity urlEncodedJSONEntity = new StringEntity(json,"UTF-8");
//        se.setContentEncoding("utf-8");
//        urlEncodedJSONEntity.setContentType("application/json;charset=utf-8");
        return doPost(url, urlEncodedJSONEntity,null);
//        return post(url,json.getBytes("UTF-8"));
    }
    //post传输二进制流
    public String post(String url,byte[] data) throws Exception{
        return doPost(url,new InputStreamEntity(new ByteArrayInputStream(data)),null);
    }
    //不带任何参数的post
    public String post(String url) throws Exception {
        return this.doPost(url, null,null);
    }
}


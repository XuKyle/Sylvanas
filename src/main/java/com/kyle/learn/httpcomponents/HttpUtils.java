package com.kyle.learn.httpcomponents;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle on 2016/4/30.
 */
public class HttpUtils {
    public void requestGet(String urlWithParams) throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(urlWithParams);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(50)
                .setConnectionRequestTimeout(50).setSocketTimeout(50).build();

        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println("Status Code ->"+response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity);
        System.out.println("jsonStr = " + jsonStr);

        httpGet.releaseConnection();
    }

    public void requestPost(String url,List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpclient.execute(httppost);
        System.out.println(response.toString());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity, "utf-8");
        System.out.println(jsonStr);

        httppost.releaseConnection();
    }

    public static void main(String[] args) {
        HttpUtils httpUtils = new HttpUtils();
        try {
            ///service/getIpInfo.php?ip=[ip地址字串]
            //115.34.29.53
//            httpUtils.requestGet("http://ip.taobao.com/service/getIpInfo2.php?ip=115.34.29.53");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ip", "115.34.29.53"));

            httpUtils.requestPost("http://ip.taobao.com/service/getIpInfo2.php", params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

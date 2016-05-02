package com.kyle.learn.httpcomponents;

import org.apache.http.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by kyle on 2016/4/29.
 */
public class HttpLearn {
    public static void main(String[] args) throws IOException {

        /*HTTP 请求消息*/
        HttpRequest request = new BasicHttpRequest("GET", "/",HttpVersion.HTTP_1_1);
        System.out.println(request.getRequestLine().getMethod());
        System.out.println(request.getRequestLine().getUri());
        System.out.println(request.getRequestLine().getProtocolVersion());
        System.out.println(request.getProtocolVersion());
        System.out.println(request.getRequestLine().toString());

        /*HTTP 响应消息*/
        System.out.println("响应信息：");
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getProtocolVersion());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());

        response.addHeader("Set-Cookie","c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie","c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);

        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("===iterator!===");
        HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        while (iterator.hasNext()) {
            HeaderElement elem = iterator.nextElement();
            System.out.println(elem.getName() + " = " + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.println("-->" + params[i]);
            }
        }


        System.out.println("StringEntity!");
        StringEntity myEntity = new StringEntity("important message","UTF-8");
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.getContentCharSet(myEntity));
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);














    }





}

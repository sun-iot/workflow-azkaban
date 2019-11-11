package com.ci123.workflow.azkaban.operate.service.http;

import com.ci123.workflow.azkaban.operate.service.http.ssl.HttpClientSSL;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.service.http
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 13:46
 */
public class PostClientSSL {
    private Logger logger = LoggerFactory.getLogger(PostClientSSL.class);

    public String doPost(String url ,List<NameValuePair> content){
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        CloseableHttpResponse response = null ;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        String result = "" ;
        if (content.size() > 0){
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                logger.error("UrlEncodedFormEntity failed {}" , e.getMessage());
                return "\"status\":\"error\",\"message\":"+e.getMessage()+""; // "status":"error","message":e.getMessage
            }

            try {
                response = httpClient.execute(httpPost);
                result = dealResponse(response);
            } catch (IOException e) {
                logger.error("httpClient POST EXECUTOR failed {}" , e.getMessage());
                return "\"status\":\"error\",\"message\":"+e.getMessage()+""; // "status":"error","message":e.getMessage
            }finally {
                httpPost.abort();
                if (response != null) {
                    EntityUtils.consumeQuietly(response.getEntity());
                }
                release(httpClient);
            }
        }
        return result;
    }

    public String doPost(String url , HttpEntity entity ){
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        CloseableHttpResponse response = null ;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        String result ="" ;
        try {
            response= httpClient.execute(httpPost);
            result = dealResponse(response);
        } catch (IOException e) {
            logger.error("httpClient POST executor failed {}" , e.getMessage());
            return "\"status\":\"error\",\"message\":"+e.getMessage()+""; // "status":"error","message":e.getMessage
        }finally {
            httpPost.abort();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            release(httpClient);
        }
        return result ;
    }

    private String dealResponse(CloseableHttpResponse response){
        String result = "" ;
        if (response != null && response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            try {
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                logger.error("entity to string failed {}" , e.getMessage());
                return "\"status\":\"error\",\"message\":"+e.getMessage()+""; // "status":"error","message":e.getMessage
            }
        }
        return result ;
    }

    /**
     * @param url
     * @param contentMap
     * @return
     */
    public String doPost(String url, Map<String, String> contentMap ,String contentType) {
        String result = null;
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> content = new ArrayList<>();

        Iterator iterator = contentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            content.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        CloseableHttpResponse response = null;
        post.addHeader("Content-Type", contentType);
        try {
            if (content.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
                post.setEntity(entity);
            }
            response = httpClient.execute(post);

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
            logger.info(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.abort();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            release(httpClient);
        }
        return null;
    }

    private void release(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("release httpClient failed {}" , e.getMessage());
                e.printStackTrace();
            }
        }
    }


}

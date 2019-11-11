package com.ci123.workflow.azkaban.operate.service.http;

import com.ci123.workflow.azkaban.operate.service.http.ssl.HttpClientSSL;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.service.http
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 17:21
 */
public class GetHttpClientSSL {
    private Logger logger = LoggerFactory.getLogger(GetHttpClientSSL.class);

    public String doGet(String url) {
        String result = null;
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            result = dealResponse(response);
        } catch (Exception e) {
            logger.error("httpClient Get executor failed {}", e.getMessage());
            return "\"status\":\"error\",\"message\":" + e.getMessage() + "";
        } finally {
            httpGet.abort();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            release(httpClient);
        }
        return result;
    }

    /**
     * 释放
     * @param response
     * @return
     */
    private String dealResponse(CloseableHttpResponse response) {
        String result = "";
        if (response != null && response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            try {
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                logger.error("entity to string failed {}", e.getMessage());
                return "\"status\":\"error\",\"message\":" + e.getMessage() + "";
            }
        }
        return result;
    }

    /**
     * 释放
     *
     * @param httpClient
     */
    private void release(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

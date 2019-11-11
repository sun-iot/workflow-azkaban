package com.ci123.workflow.azkaban.operate.service.http.ssl;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.service.http.ssl
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 14:13
 */
public class HttpClientSSL {
    private static Logger logger = LoggerFactory.getLogger(HttpClientSSL.class);

    private static CloseableHttpClient closeableHttpClient = null;

    public HttpClientSSL() {}

    public CloseableHttpClient createHttpClient(){
        return getCloseableHttpClient() ;
    }
    private CloseableHttpClient getCloseableHttpClient() {
        if (this.closeableHttpClient == null) {
            this.closeableHttpClient = getHttpClient();
        }
        return this.closeableHttpClient;
    }
    public static CloseableHttpClient buildSSLCloseableHttpClient()  {
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        //ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    private SSLContext getSSLContext() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(new File("D:\\corp-ci-work\\workflow\\src\\main\\resources\\keystore"), "123456".toCharArray()).build();
        } catch (Exception e) {
            logger.error("Executpr SSL {} failed ", e.getMessage());
            sslContext = null;
        }
        return sslContext;
    }

    /**
     * 默认的域名校验类为 DefaultHostnameVerifier,比对服务器证书的AlternativeName和CN两个属性。
     * 如果服务器证书这两者不合法而我们又必须让其校验通过，怎可以自己实现HostnameVerifier
     *
     * @param sslContext
     * @return
     */
    private SSLConnectionSocketFactory getSSLConnectionSocketFactory(SSLContext sslContext) {
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                // 自定义域名校验逻辑
                return true;
            }
        });
        return socketFactory;
    }

    private PoolingHttpClientConnectionManager getPoolHttpClientConnectionManager(SSLConnectionSocketFactory sslConnectionSocketFactory) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory).build());
        poolingHttpClientConnectionManager.setMaxTotal(32);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(64);

        return poolingHttpClientConnectionManager;
    }

    private CloseableHttpClient getHttpClient() {
        SSLContext sslContext = getSSLContext();
        SSLConnectionSocketFactory socketFactory = getSSLConnectionSocketFactory(sslContext);
        PoolingHttpClientConnectionManager connectionManager = getPoolHttpClientConnectionManager(socketFactory);
        return HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLSocketFactory(socketFactory)
                .setConnectionManager(connectionManager)
                .build();
    }
}

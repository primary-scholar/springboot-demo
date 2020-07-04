package com.mimu.simple.comn.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.*;

/**
 * author: mimu
 * date: 2018/10/28
 */
public class SimpleHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(SimpleHttpClient.class);
    private static volatile CloseableHttpClient httpClient;
    private static int connectionTimeOut = 3000;
    private static int readTimeOut = 3000;
    private static final Object lock = new Object();

    private SimpleHttpClient() {
    }

    static {
        initHttpClient();
    }

    public static String get(String url) {
        return get(url, null);
    }

    public static Future<String> futureGet(String url) {
        return futureGet(url, null);
    }

    public static String get(String url, int connectionTimeOut, int readTimeOut) {
        return get(url, null, connectionTimeOut, readTimeOut);
    }

    public static Future<String> futureGet(String url, int connectionTimeOut, int readTimeOut) {
        return futureGet(url, null, connectionTimeOut, readTimeOut);
    }

    public static String get(String url, Map<String, Object> para) {
        return get(url, para, connectionTimeOut, readTimeOut);
    }

    public static Future<String> futureGet(String url, Map<String, Object> para) {
        return futureGet(url, para, connectionTimeOut, readTimeOut);
    }

    public static String get(String url, Map<String, Object> para, int connectionTimeOut, int readTimeOut) {
        return getMethod(url, para, connectionTimeOut, readTimeOut);
    }

    public static Future<String> futureGet(String url, Map<String, Object> para, int connectionTimeOut, int readTimeOut) {
        return CompletableFuture.supplyAsync(() -> getMethod(url, para, connectionTimeOut, readTimeOut));
    }

    private static String getMethod(String url, Map<String, Object> para, int connectionTimeOut, int readTimeOut) {
        String result = "";
        URIBuilder builder;
        try {
            builder = new URIBuilder(url);
            if (para != null) {
                List<NameValuePair> pairArrayList = new ArrayList<>();
                for (String paraKey : para.keySet()) {
                    Object value = para.get(paraKey);
                    if (value instanceof String) {
                        value = value.toString();
                    } else if (value instanceof Number) {
                        value = String.valueOf(value);
                    }
                    NameValuePair nameValuePair = new BasicNameValuePair(paraKey, value.toString());
                    pairArrayList.add(nameValuePair);
                }
                builder.addParameters(pairArrayList);
            }
            HttpGet httpGet = new HttpGet(builder.toString());
            httpGet.setConfig(getRequestConfig(connectionTimeOut, readTimeOut));
            long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            logger.info("get method url={},para={},cost={}ms", url, para, System.currentTimeMillis() - startTime);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
            }
            EntityUtils.consume(responseEntity);
        } catch (URISyntaxException | IOException e) {
            logger.error("get method url={},para={}", url, para, e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")

    private static void initHttpClient() {
        if (httpClient == null) {
            synchronized (lock) {
                if (httpClient == null) {
                    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
                    connectionManager.setMaxTotal(500);
                    connectionManager.setDefaultMaxPerRoute(50);
                    httpClient = HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler(new StandardHttpRequestRetryHandler(1, false)).build();
                    /**
                     * 服务端 关闭连接 客户端无感知，HttpClient为了缓解这一问题，
                     * 在某个连接使用前会检测这个连接是否过时，如果过时则连接失效，但是这种做法会为每个请求增加一定额外开销，
                     * 因此有一个定时任务专门回收长时间不活动而被判定为失效的连接，可以某种程度上解决这个问题
                     */
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            /**
                             * 关闭失效链接，并已出链接池
                             */
                            connectionManager.closeExpiredConnections();
                            /**
                             * 关闭不活跃链接
                             */
                            connectionManager.closeIdleConnections(20, TimeUnit.SECONDS);
                        }
                    }, 0, 1000 * 10);
                }
            }
        }
    }

    /**
     * @param connectionTime 链接建立时间
     * @param readTime       请求翻出后等待对方相应时间
     * @return
     */
    private static RequestConfig getRequestConfig(int connectionTime, int readTime) {
        if (connectionTimeOut > 0) {
            connectionTimeOut = connectionTime;
        }
        if (readTimeOut > 0) {
            readTimeOut = readTime;
        }
        /**
         * connectionRequestTimeOut 从链接池获取链接的 超时时间
         */
        int connectionRequestTimeOut = 3000;
        return RequestConfig.custom().setConnectTimeout(connectionTimeOut).setConnectionRequestTimeout(connectionRequestTimeOut)
                .setSocketTimeout(readTimeOut).build();
    }
}

package qian.ling.yi.ext.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * HttpClient工具类
 * Created by liuguobin on 2016/9/21.
 */
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static CloseableHttpClient httpClient ;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);//最大并发链接数
        SocketConfig defaultSocketConfig = SocketConfig
            .custom()
            .setSoTimeout(3000)//超时时间
            .build();
        cm.setDefaultSocketConfig(defaultSocketConfig);
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    /**
     * 传送XML字符串
     * @param url 请求地址
     * @param xmlStr XMl字符串
     * @return Map
     */
    public static Map<String, String> postXml(String url, String xmlStr) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity xmlEntity = new StringEntity(xmlStr, "UTF-8");
        httpPost.addHeader("Content-Type", "application/xml");
        httpPost.setEntity(xmlEntity);
        return execRequest(httpPost);
    }

    /**
     * 发送带头部的请求
     * @param url
     * @param headMap
     * @param reqParam
     * @return
     */
    public static Map<String, String> postMapWithHead(String url, Map<String, String> headMap, Map<String, String> reqParam) {
        HttpPost httpPost = new HttpPost(url);
        Map<String, String> result = new HashMap<>();
        setHeads(httpPost, headMap);
        if (!setParams(httpPost, reqParam, result)) return result;
        return execRequest(httpPost);
    }

    /**
     * 发送Map请求
     */
    public static Map<String, String> postMap(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        Map<String, String> result = new HashMap<>();
        if (setParams(httpPost, params, result)) return result;
        return execRequest(httpPost);
    }

    /**
     * 设置头部
     * @param requestBase
     * @param headMap
     */
    private static void setHeads(HttpRequestBase requestBase, Map<String, String> headMap) {
        if (null == headMap || headMap.isEmpty()) {
            return ;
        }

        for (String key : headMap.keySet()) {
            requestBase.setHeader(key, headMap.get(key));
        }
    }

    /**
     * 设置参数
     * @param httpPost 具体请求对象
     * @param params 请求参数
     * @param result 若设置参数异常，返回结果
     * @return boolean
     */
    private static boolean setParams(HttpPost httpPost, Map<String, String> params, Map<String, String> result) {
        if (null == params || params.isEmpty()) {
            return true;
        }
        Set<String> keys = params.keySet();
        List<NameValuePair> nvp = new ArrayList<>(keys.size());
        keys.forEach(key -> nvp.add(new BasicNameValuePair(key, params.get(key))));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvp, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            result.put("code", "444");
            logger.error("[请求][编码][失败]", e);
            return false;
        }
        return true;
    }

    /**
     * 执行具体的请求
     * @param request HttpPost/HttpRequest
     * @return Map
     */
    private static Map<String, String> execRequest(HttpRequestBase request){
        Map<String, String> result = new HashMap<>();
        try (CloseableHttpResponse response = httpClient.execute(request)){
            HttpEntity entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity, "utf-8");
            logger.info("[请求][成功][结果][{}]", responseStr);
            result.put("code", "200");
            result.put("msg", responseStr);
            return result;
        } catch (IOException e) {
            result.put("code", "444");
            logger.error("[请求][失败][IO异常]", e);
            return result;
        } finally {
            request.releaseConnection();
        }
    }

    public static void main(String[] args) {
        logger.info("kaishi");
        Map<String, String> map = new HashMap<>();
        map.put("3","3");
        Map<String, String> resMap = postMap("http://www.baidu.com", map);
        Set<String> key = resMap.keySet();
        key.forEach(str -> logger.info(resMap.get(str)));
    }
}

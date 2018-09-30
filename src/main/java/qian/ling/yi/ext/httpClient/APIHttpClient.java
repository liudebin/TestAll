package qian.ling.yi.ext.httpClient;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class APIHttpClient {

    // 接口地址  
    private static String apiURL = "http://localhost:8080/v1/code/callback/qf/wx";
    private Log logger = LogFactory.getLog(this.getClass());
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private HttpClient httpClient = null;
    private HttpPost method = null;
    private long startTime = 0L;
    private long endTime = 0L;
    private int status = 0;

    /**
     * 接口地址 
     *
     * @param url
     */
    public APIHttpClient(String url) {

        if (url != null) {
            this.apiURL = url;
        }
        if (apiURL != null) {
            httpClient = new DefaultHttpClient();
            method = new HttpPost(apiURL);

        }
    }

    /**
     * 调用 API 
     *
     * @param parameters
     * @return
     */
    public String post(String parameters) {
        String body = null;
        logger.info("parameters:" + parameters);

        if (method != null & parameters != null
                && !"".equals(parameters.trim())) {
            try {

                // 建立一个NameValuePair数组，用于存储欲传送的参数  
//                method.addHeader("Content-type","application/json; charset=utf-8");
                method.setHeader("Accept", "application/json");
                method.setHeader("x-qf-sign","161E1064CD9B4430029774B6A01CA6E1");
                method.setHeader("X-QF-SIGN","161E1064CD9B4430029774B6A01CA6E1");
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                startTime = System.currentTimeMillis();

                HttpResponse response = httpClient.execute(method);

                endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();

                logger.info("statusCode:" + statusCode);
                logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    logger.error("Method failed:" + response.getStatusLine());
                    status = 1;
                }

                // Read the response body  
                body = EntityUtils.toString(response.getEntity());

            } catch (IOException e) {
                // 网络错误  
                status = 3;
            } finally {
                logger.info("调用接口状态：" + status);
            }

        }
        return body;
    }

    public static void main(String[] args) {
        APIHttpClient ac = new APIHttpClient(apiURL);

        JsonObject j = new JsonObject();
        j.addProperty("status", "1");
        j.addProperty("sysdtm", "2016-10-17 23:55:45");
        j.addProperty("goods_name", "");
        j.addProperty("txcurrcd","CNY");
        j.addProperty("cancel","0");
        j.addProperty("pay_type","800201");
        j.addProperty("txdtm","2016-07-06 17:21:36");
        j.addProperty("txamt","37869");
        j.addProperty("out_trade_no","NNPR_3670691");
        j.addProperty("syssn","20161017487008");
        j.addProperty("respcd","0000");
        j.addProperty("notify_type","payment");
//        System.out.println(JSON.toJSONString(j));
        String s = j.toString();
        System.out.println(s);


        System.out.println(ac.post("{\"status\": \"1\", \"sysdtm\": \"2016-10-17 23:55:45\", \"goods_name\": \"\", \"txcurrcd\": \"CNY\", \"cancel\": \"0\", \"pay_type\": \"800201\", \"txdtm\": \"2016-10-17 23:55:45\", \"txamt\": \"37869\", \"out_trade_no\": \"NNPR_3670691\", \"syssn\": \"20161017487008\", \"respcd\": \"0000\", \"notify_type\": \"payment\"}"));
    }

    /**
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误 
     *
     * @return the status 
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set 
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the startTime 
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime 
     */
    public long getEndTime() {
        return endTime;
    }
}  
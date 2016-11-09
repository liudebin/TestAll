package qian.ling.yi.ext.ssl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * Created by liuguobin on 2016/9/14.
 */
public class HttpComponentExample {
    public final static void main(String[] args) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(new File("d:/ssl/client.p12")), "222222".toCharArray());

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
//                .loadKeyMaterial(keyStore, "222222".toCharArray())
                .loadTrustMaterial(new File("d:/ssl/client.truststore"), "222222".toCharArray(),
                        new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {

            HttpPost httppost = new HttpPost("https://localhost:8443/sslServlet");
            System.out.println("Executing request " + httppost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(entity.getContent());
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }
    }
}

package qian.ling.yi.ext.ssl.socket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

/**
 * Created by liuguobin on 2016/9/14.
 */
public class TestSSLSocketClient {
    private static final String KEY_STORE_TYPE_JKS = "jks";
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";
    private static final String SCHEME_HTTPS = "https";
    private static final int HTTPS_PORT = 8443;
    private static final String HTTPS_URL = "https://localhost:8443/SSL/sslServlet";
    private static final String KEY_STORE_CLIENT_PATH = "d:/ssl/client.p12";
    private static final String KEY_STORE_TRUST_PATH = "d:/ssl/client.truststore";
    private static final String KEY_STORE_PASSWORD = "222222";
    private static final String KEY_STORE_TRUST_PASSWORD = "222222";

    Logger logger = LoggerFactory.getLogger(getClass());
    public void post() {
        SSLContext sslContext = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
            KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
            InputStream ksIn = new FileInputStream(KEY_STORE_CLIENT_PATH);
            InputStream tsIn = new FileInputStream(new File(KEY_STORE_TRUST_PATH));
            keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
            trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());
            sslContext = SSLContext.getInstance("TLS");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());

//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(trustStore);
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers() , null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("错误");
            return;
        }
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        try {
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost",8443);
            InputStream is = sslSocket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buf=new byte[1024];
            bis.read(buf);
            System.out.println(new String(buf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new TestSSLSocketClient().testException();
        new TestSSLSocketClient().post();
    }

    public void testException(){
        try {
            logger.info("{}","异常已经抛出");
            throw new Exception("hhe");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{}", "捕捉到异常，需要处理，不然会继续执行");
        }
        logger.info("{}","异常捕捉，但是出现异常，未做相应处理");
    }

}

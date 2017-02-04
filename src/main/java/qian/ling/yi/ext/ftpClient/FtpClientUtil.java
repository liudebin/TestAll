package qian.ling.yi.ext.ftpClient;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by liuguobin on 2016/12/20.
 */
public class FtpClientUtil {
    Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);

    private FTPClient getClient() {
        FTPClient client = new FTPClient();
        try {
            logger.info("connecting {}", System.nanoTime());
            client.connect("localhost");
            logger.info("Connected {}", System.nanoTime());
            client.login("liuguobin", "Ldb20160907");
        } catch (IOException e){
            e.printStackTrace();
        }

        return client;
    }

    public void disconnect(FTPClient client) {
        try {
            client.logout();
            if (client.isConnected()) {
                client.disconnect();
                client = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
//            throw new FTPClientException("Could not returnClient from server.", e);
        }
    }

    public boolean uploadFile(String remoteFile, String localFile, boolean autoClose) {
        FTPClient client = getClient();
        boolean isSuc = false;
        try (InputStream input = new FileInputStream(localFile)) {
            client.storeFile(remoteFile, input);
            logger.debug("put " + localFile);
            isSuc = true;
//        } catch (FileNotFoundException e) {
//            throw new FTPClientException("local file not found.", e);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new FTPClientException("Could not put file to server.", e);
        } finally {
            if (autoClose) {
                disconnect(client); //断开连接
            }
            return isSuc;
        }
    }

    public boolean getFile(String remoteFile, String localFile, boolean autoClose) {
        boolean isSuc = false;
        try (OutputStream outputStream = new FileOutputStream(localFile)) {
            isSuc =  get(remoteFile, outputStream, autoClose);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuc;
    }

    public boolean get(String remoteFile, OutputStream output, boolean autoClose) {
        FTPClient client = getClient();
        boolean isSuc = false;
        try {
            // 处理传输
            isSuc = client.retrieveFile(remoteFile, output);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new FTPClientException("Couldn't get file from server.", e);
        } finally {
            if (autoClose) {
                disconnect(client); //关闭链接
            }
            return isSuc;
        }
    }

    public static void main(String[] args) {
        FtpClientUtil util = new FtpClientUtil();
//        boolean s = util.uploadFile("tmp/3004-EVE0001-20150320-hehe", "/Users/liuguobin/tmp/3004-EVE0001-20150320",true);
//        s = util.getFile("tmp/3004-EVE0001-20150320", "/Users/liuguobin/tmp/3004-EVE0001-20150320-download", true);
        boolean s = util.uploadFile("tmp/3004-APPZX0002-990001-20150213-hehe", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213",true);
        s = util.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download", true);
        System.out.print(s);
    }

}

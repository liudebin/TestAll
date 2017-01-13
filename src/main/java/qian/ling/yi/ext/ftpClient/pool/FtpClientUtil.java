package qian.ling.yi.ext.ftpClient.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.util.StringUtil;

import java.io.*;

/**
 * ftp客户端工具
 * Created by liuguobin on 2016/12/20.
 */
public class FtpClientUtil {
    static Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);
    private static GenericObjectPool<FTPClient> ftpClientPool;

    static {
        GenericObjectPoolConfig poolConf = new GenericObjectPoolConfig();
        poolConf.setMaxTotal(10);
        poolConf.setMaxIdle(1);
//        poolConf.setMaxWaitMillis(180000);
        poolConf.setMaxWaitMillis(10000);//等待分配客户端的最长时间
        ftpClientPool = new GenericObjectPool<FTPClient>(new FTPClientPoolFactory(), poolConf);
    }

    private FtpClientUtil(){};

    private static FTPClient getClient() {
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpClientPool.borrowObject();
        } catch (Exception e) {
//            throw new baserun todo
        } finally {
            return ftpClient;
        }
    }

    /**
     * 返还给连接池
     * @param client
     */
    public static void returnClient(FTPClient client) {
        ftpClientPool.returnObject(client);
    }

    /**
     * 在给定目录创建文件夹
     *
     * 慎重换目录
     * @param workDirectory
     * @param pathName
     * @return
     */
    public static boolean mkdir(String workDirectory, String pathName) {
        boolean isSuc = false;
        FTPClient ftpClient = getClient();
        try {
            if (!StringUtil.isEmpty(workDirectory)) {
                isSuc = ftpClient.changeWorkingDirectory(workDirectory);
                if (!isSuc) {
                    return isSuc;
                }
                if (!StringUtil.isEmpty(pathName)) {
                    isSuc = ftpClient.makeDirectory(pathName);
                }
            } else {
                if (!StringUtil.isEmpty(pathName)) {
                    isSuc = ftpClient.makeDirectory(pathName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();//todo
        } finally {
            returnClient(ftpClient);
            return isSuc;
        }

    }

    /**
     * 上传文件
     * @param remoteFile 服务器文件名称
     * @param localFile 本地文件名称
     * @return
     */
    public static boolean uploadFile(String remoteFile, String localFile) {
        FTPClient client = getClient();
        boolean isSuc = false;
        try (InputStream input = new FileInputStream(localFile)) {
            client.storeFile(remoteFile, input);
            logger.debug("put " + localFile);
            isSuc = true;
        } catch (Exception e) {
            e.printStackTrace();
//to            throw new FTPClientException("Could not put file to server.", e);
        } finally {
            returnClient(client);
            return isSuc;
        }
    }

    public static boolean getFile(String remoteFile, String localFile) {
        boolean isSuc = false;
        try (OutputStream outputStream = new FileOutputStream(localFile)) {
            isSuc =  get(remoteFile, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuc;
    }

    public static boolean get(String remoteFile, OutputStream output) {
        FTPClient client = getClient();
        boolean isSuc = false;
        try {
            // 处理传输
            isSuc = client.retrieveFile(remoteFile, output);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new FTPClientException("Couldn't get file from server.", e);
        } finally {
            returnClient(client);
            return isSuc;
        }
    }

    public static void main(String[] args) {
//        FtpClientUtil util = new FtpClientUtil();
//        boolean s = util.uploadFile("tmp/3004-EVE0001-20150320-hehe", "/Users/liuguobin/tmp/3004-EVE0001-20150320",true);
//        s = util.getFile("tmp/3004-EVE0001-20150320", "/Users/liuguobin/tmp/3004-EVE0001-20150320-download", true);
        boolean s = FtpClientUtil.uploadFile("tmp/3004-APPZX0002-990001-20150213-haha", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213");
//        boolean s = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
        logger.info("{}", s);
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean s1 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
                    logger.info("{}", s);
                }
            }).start();

        }

//        boolean s1 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
//        logger.info("{}", s);
//        boolean s2 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
//        logger.info("{}", s);
//        boolean s3 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
//        logger.info("{}", s);
//        boolean s4 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
//        logger.info("{}", s);
//        boolean s5 = FtpClientUtil.getFile("tmp/3004-APPZX0002-990001-20150213", "/Users/liuguobin/tmp/3004-APPZX0002-990001-20150213-download");
//        logger.info("{}", s);


        logger.info("start {}", System.nanoTime());
//        getClient();
        try {

        Thread.sleep(500000);
        } catch (Exception e) {

        }
//        logger.info("end1 {}", System.nanoTime());
//
//        getClient();
//        logger.info("end2 {}", System.nanoTime());
//
//        getClient();
//        logger.info("end3 {}", System.nanoTime());
//
//        getClient();
//        logger.info("end4 {}", System.nanoTime());
//
//        getClient();
//        logger.info("end5 {}", System.nanoTime());
//        logger.info("开始等待---{}", System.currentTimeMillis());
//
//        getClient();
//        logger.info("end5 {}", System.nanoTime());
    }

}

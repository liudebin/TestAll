package qian.ling.yi.ext.ftpClient.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuguobin on 2016/12/21.
 */
public class FTPClientPoolTest {
    static Logger logger = LoggerFactory.getLogger(FTPClientPoolTest.class);
    public static void main(String[] args) {

//        FTPClientConf clientConf = new FTPClientConf();
//        clientConf.setHost("localhost")
//            .setUsername("liuguobin")
//            .setPassword("Ldb20160907")
//            .setClientTimeout(180000)
//            .setEncoding("GBK");
//
//        GenericObjectPoolConfig poolConf = new GenericObjectPoolConfig();
//        poolConf.setMaxTotal(5);
//        poolConf.setMaxIdle(1);
////        poolConf.setMaxWaitMillis(1000);
//        poolConf.setLifo(false);
//        GenericObjectPool<FTPClient> ftpClientPool = new GenericObjectPool<FTPClient>(new FTPClientPoolFactory(clientConf), poolConf);
//
//        FTPClient ftpClient = null;
//        try {
//            ftpClient = ftpClientPool.borrowObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != ftpClient) {
//                ftpClientPool.returnObject(ftpClient);
//            }
//            try {
//                ftpClient = null;
//                Thread.sleep(4000);
//                logger.info("{}", ftpClientPool.getCreatedCount());
//                logger.info("{}", ftpClientPool.getDestroyedCount());
//                ftpClientPool.borrowObject();
////                logger.info("{}", ftpClient.isConnected());
//
//            } catch (Exception e) {
//
//            }
//        }


//        101.227.69.182: 22
        FTPClientConf clientConf = new FTPClientConf();
        clientConf.setHost("101.227.69.182")
                .setPort(22)
                .setUsername("test16_zh")
                .setPassword("yMb5(z648q*")
                .setClientTimeout(180000)
                .setEncoding("GBK");

        GenericObjectPoolConfig poolConf = new GenericObjectPoolConfig();
        poolConf.setMaxTotal(5);
        poolConf.setMaxIdle(1);
//        poolConf.setMaxWaitMillis(1000);
        poolConf.setLifo(false);
        GenericObjectPool<FTPClient> ftpClientPool = new GenericObjectPool<FTPClient>(new FTPClientPoolFactory(clientConf), poolConf);

        FTPClient ftpClient = null;
        try {
            ftpClient = ftpClientPool.borrowObject();
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                logger.info(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != ftpClient) {
                ftpClientPool.returnObject(ftpClient);
            }
//            try {
//                ftpClient = null;
//                Thread.sleep(4000);
//                logger.info("{}", ftpClientPool.getCreatedCount());
//                logger.info("{}", ftpClientPool.getDestroyedCount());
//                ftpClientPool.borrowObject();
////                logger.info("{}", ftpClient.isConnected());
//
//            } catch (Exception e) {
//
//            }
        }
    }
}

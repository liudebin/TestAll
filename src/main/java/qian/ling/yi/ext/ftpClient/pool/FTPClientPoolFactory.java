package qian.ling.yi.ext.ftpClient.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * FTPClient 配置类
 *
 * 只要实现了ObjectPool 和 PoolableObjectFactory<T>接口就能实现一个自定义的连接池
 *
 * 为了方便实现，继承了 BasePooledObjectFactory ，直接使用了 GenericObjectPool
 * GenericObjectPool使用的是LinkedBlockingDeque
 *
 * sudo -s launchctl load -w /System/Library/LaunchDaemons/ftp.plist
 * sudo -s launchctl unload -w /System/Library/LaunchDaemons/ftp.plist
 * Created by liuguobin on 2016/12/20.
 */
public class FTPClientPoolFactory extends BasePooledObjectFactory<FTPClient> {
    private static Logger logger = LoggerFactory.getLogger(FTPClientPoolFactory.class);

    private FTPClientConf clientConf;//todo 换成properties直接注入

    public FTPClientPoolFactory() {
        this.clientConf = new FTPClientConf()
                .setHost("localhost")
                .setUsername("liuguobin")
                .setPassword("Ldb20160907")
                .setClientTimeout(180000)
                .setEncoding("GBK");
    }

    public FTPClientPoolFactory(FTPClientConf clientConfig) {
        this.clientConf = clientConfig;
    }

    @Override
    public FTPClient create() throws Exception {
        logger.info("新建client");
        FTPClient ftpClient = new FTPSClient();
        ftpClient.setConnectTimeout(clientConf.getClientTimeout());
        try {
            if (clientConf.hasSetPort()) {
                ftpClient.connect(clientConf.getHost(), clientConf.getPort());
            } else {
                ftpClient.connect(clientConf.getHost());
            }

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.warn("FTPServer refused connection");
                return null;
            }
            boolean result = ftpClient.login(clientConf.getUsername(), clientConf.getPassword());
            if (!result) {
                logger.warn("ftpClient login failed... username is {}", clientConf.getUsername());
            }
            ftpClient.setFileType(clientConf.getTransferFileType());
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(clientConf.getEncoding());
            if ("true".equals(clientConf.getPassiveMode())) {
                ftpClient.enterLocalPassiveMode();
            }
        } catch (Exception e) {
            logger.error("create ftp connection failed...{}", e);
            throw e;
        }

        return ftpClient;
    }

    @Override
    public PooledObject<FTPClient> wrap(FTPClient obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public void destroyObject(PooledObject<FTPClient> ftpClient){
        logger.info("destroy");
        if (null != ftpClient && ftpClient.getObject().isConnected()) {
            try {
                ftpClient.getObject().logout();
            } catch (IOException e) {
                e.printStackTrace();//todo
            } finally {
                try {
                    ftpClient.getObject().disconnect();
                } catch (IOException e) {
                    logger.error("FTPCLient 链接断开异常{}",e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

}

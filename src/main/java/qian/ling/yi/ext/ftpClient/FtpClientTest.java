package qian.ling.yi.ext.ftpClient;

import org.apache.commons.net.ftp.*;
import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.ext.ftpClient.pool.FTPClientConf;

import java.io.IOException;

/**
 * sudo -s launchctl load -w /System/Library/LaunchDaemons/ftp.plist
 * Created by liuguobin on 2016/12/20.
 */
public class FtpClientTest extends AbstractTest {
    @Test
    public void test() {
        FTPClient ftp = new FTPClient();

        FTPClientConf config = new FTPClientConf();
//        config.setXXX(YYY); // change required options
        // for example config.setServerTimeZoneId("Pacific/Pitcairn")
//        ftp.configure(config );
        boolean error = false;
        try {
            int reply;
            String server = "localhost";
            ftp.setConnectTimeout(300000);
            logger.info("{}", System.nanoTime());
            ftp.connect(server);

            logger.info("Connected {}", System.nanoTime());
            ftp.login("liuguobin", "Ldb20160907");

            logger.info(ftp.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.error("FTP server {} refused connection.", server);
//                System.exit(1);
            }
            logger.info("hehe");
             logger.info(ftp.getLocalAddress().getHostAddress());
            //绝对路径
            String[] names = ftp.listNames();
            logger.info("{}", null == names);
            for (String name : names) {
//                logger.info(name);
            }

            FTPFile[] ftpDir = ftp.listFiles("store/");
            for (FTPFile ftpFile : ftpDir) {
                logger.info(ftpFile.getName());
            }

            ftpDir = ftp.listDirectories("store/");
            for (FTPFile ftpFile : ftpDir) {
                logger.info(ftpFile.getName());
            }

//            ftp.setRestartOffset();

//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//      ... // transfer files
            ftp.logout();
        } catch(IOException e) {
            error = true;
            e.printStackTrace();
            logger.info("{}", "fuck");
        } finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
//            System.exit(error ? 1 : 0);
        }
    }




}

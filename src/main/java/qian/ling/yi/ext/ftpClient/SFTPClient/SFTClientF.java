package qian.ling.yi.ext.ftpClient.SFTPClient;

/**
 * Created by liuguobin on 2016/12/26.
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * sftp工具。注意：构造方法有两个：分别是基于密码认证、基于秘钥认证。
 *
 * @see http://xliangwu.iteye.com/blog/1499764
 * @author Somnus
 */
public class SFTClientF {
    private static final Logger LOG = LoggerFactory.getLogger(SFTClientF.class);
    private ChannelSftp sftp;
    private String userName; // FTP 登录用户名
    private String password; // FTP 登录密码
    private String keyFilePath;// 私钥文件的路径
    private String host; // FTP 服务器地址IP地址
    private int port; // FTP 端口
    private Session sshSession;

    /**
     * 构造基于密码认证的sftp对象
     *
     * @param userName
     *            用户名
     * @param password
     *            登陆密码
     * @param host
     *            服务器ip
     * @param port
     *            fwq端口
     */
    public SFTClientF(String userName, String password, String host, int port) {
        super();
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     *
     * @param userName
     *            用户名
     * @param host
     *            服务器ip
     * @param port
     *            fwq端口
     * @param keyFilePath
     *            私钥文件路径
     */
    public SFTClientF(String userName, String host, int port, String keyFilePath) {
        super();
        this.userName = userName;
        this.host = host;
        this.port = port;
        this.keyFilePath = keyFilePath;
    }

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    public void connect() throws Exception {
        try {
            JSch jsch = new JSch();
            if (keyFilePath != null) {
                jsch.addIdentity(keyFilePath);// 设置私钥
                LOG.info("连接sftp，私钥文件路径：" + keyFilePath);
            }
            LOG.info("sftp host: " + host + "; userName:" + userName);

            sshSession = jsch.getSession(userName, host, port);
            LOG.debug("Session 已建立.");
            if (password != null) {
                sshSession.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            LOG.debug("Session 已连接.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
            LOG.info("连接到SFTP成功。host: " + host);
        } catch (Exception e) {
            LOG.error("连接sftp失败！", e);
            throw e;
        }
    }

    /**
     * 关闭连接 server
     */
    public void disconnect() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                sshSession.disconnect();
                LOG.info("sftp连接关闭成功！" + sftp);
            } else if (sftp.isClosed()) {
                LOG.warn("sftp 已经关闭,不需要重复关闭！" + sftp);
            }
        }

    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory
     *            上传到该目录
     * @param sftpFileName
     *            sftp端文件名
     * @param in
     *            输入流
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws Exception {
        try {

            try {// 如果cd报异常，说明目录不存在，就创建目录
                sftp.cd(directory);
            } catch (Exception e) {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            sftp.put(input, sftpFileName);
            LOG.info("sftp上传成功！文件名：" + sftpFileName);
        } catch (Exception e) {
            LOG.error("sftp上传失败！文件名" + sftpFileName, e);
            throw e;
        }
    }

    /**
     * 上传单个文件
     *
     * @param directory
     *            上传到sftp目录
     * @param uploadFile
     *            要上传的文件,包括路径
     * @throws Exception
     */
    public void upload(String directory, String uploadFile) throws Exception {
        File file = new File(uploadFile);
        upload(directory, file.getName(), new FileInputStream(file));
    }

    /**
     * 将byte[]上传到sftp，作为文件。注意:从String生成byte[]是，要指定字符集。
     *
     * @param directory
     *            上传到sftp目录
     * @param sftpFileName
     *            文件在sftp端的命名
     * @param byteArr
     *            要上传的字节数组
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, byte[] byteArr) throws Exception {
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));
    }

    /**
     * 将字符串按照指定的字符编码上传到sftp
     *
     * @param directory
     *            上传到sftp目录
     * @param sftpFileName
     *            文件在sftp端的命名
     * @param dataStr
     *            待上传的数据
     * @param charsetName
     *            sftp上的文件，按该字符编码保存
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, String dataStr, String charsetName) throws Exception{
        upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)));

    }

    /**
     * 下载文件
     *
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param saveFile
     *            存在本地的路径
     * @throws Exception
     */
    public void download(String directory, String downloadFile, String saveFile) throws Exception {
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
            LOG.info("sftp下载文件成功！文件名" + downloadFile);
        } catch (Exception e) {

            LOG.error("sftp下载文件失败！文件名：" + downloadFile, e);
            throw e;
        }
    }
    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     * @throws Exception
     */
    public byte[] download(String directory, String downloadFile) throws Exception{
        byte[] fileData = null;
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            InputStream is = sftp.get(downloadFile);

            fileData = new byte[is.available()];
            is.read(fileData);

            LOG.info("sftp下载文件成功！文件名" + downloadFile);
        } catch (SftpException e) {

            LOG.error("sftp下载文件失败！文件名：" + downloadFile, e);
            throw e;
        } catch (IOException e) {
            LOG.error("sftp下载文件读取失败！文件名：" + downloadFile, e);
            throw e;
        }
        return fileData;
    }

    /**
     * 删除文件
     *
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @throws Exception
     */
    public void delete(String directory, String deleteFile) throws Exception {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            LOG.error("sftp删除文件失败" + deleteFile, e);
            throw e;
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory
     *            要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

}

package qian.ling.yi.ext.ftpClient.pool;

/**
 * Created by liuguobin on 2016/12/20.
 */
public class FTPClientConf {
    private String host;

    private int port;

    private String username;

    private String password;

    private String passiveMode;

    private String encoding;

    private int clientTimeout;

    private int bufferSize;

    private int transferFileType;

    private int retryTime;

    public String getHost() {
        return host;
    }

    public FTPClientConf setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public FTPClientConf setPort(int port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public FTPClientConf setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public FTPClientConf setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassiveMode() {
        return passiveMode;
    }

    public FTPClientConf setPassiveMode(String passiveMode) {
        this.passiveMode = passiveMode;
        return this;
    }

    public String getEncoding() {
        return encoding;
    }

    public FTPClientConf setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public int getClientTimeout() {
        return clientTimeout;
    }

    public FTPClientConf setClientTimeout(int clientTimeout) {
        this.clientTimeout = clientTimeout;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public FTPClientConf setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public int getTransferFileType() {
        return transferFileType;
    }

    public FTPClientConf setTransferFileType(int transferFileType) {
        this.transferFileType = transferFileType;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public FTPClientConf setRetryTime(int retryTime) {
        this.retryTime = retryTime;
        return this;
    }

    public boolean hasSetPort() {
        return 0 != this.port;
    }

}
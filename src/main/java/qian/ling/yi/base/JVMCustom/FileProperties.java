package qian.ling.yi.base.JVMCustom;

import qian.ling.yi.util.ArrayUtil;
import qian.ling.yi.util.ByteUtil;
import qian.ling.yi.file.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 文件属性
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class FileProperties {
    String relatePath; //文件路径
    String fileName;// 文件剪短名称
    byte[] fileData, oriFileData; // 处理后的字节，原始字节
    int oriFileDataSize; //原始字节数

    FileProperties(String relatePath) {
        this.relatePath = relatePath;
        this.fileName = FileUtil.getFileNameByPath(relatePath);
        readClassFile();
    }

    @Override
    public String toString() {
        return "FileProperties {" +
                "relatePath='" + relatePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", oriFileData=" + Arrays.toString(oriFileData) +
                ", oriFileDataSize=" + oriFileDataSize +
                ", fileData=" + Arrays.toString(fileData) +
                '}';
    }

    public void readClassFile() {
        try(InputStream ins = FileProperties.class.getResourceAsStream(relatePath)) {
            int byteLength = ins.available();
            setOriFileDataSize(byteLength);
            final byte[] bytes = new byte[byteLength];
            ins.read(bytes);
            setOriFileData(bytes);
            setFileData(bytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        final FileProperties fileProp = new FileProperties("/qian/ling/yi/base/JVMCustom/testFile/SimpleObject.class");
        System.out.println(fileProp);

        System.out.println(fileProp.getHex( 4));

        System.out.println(fileProp.getInt(2));
        System.out.println(fileProp.getInt(2));
        System.out.println(fileProp.getInt(2));

    }

    public String getHex(int length) {
        String s =  ByteUtil.splitBytesToHex(this.getFileData(), 0, length);
        interceptBytes(length, this.getFileData().length - length);
        return s;
    }

    public int getInt(int length) {
        int i = ByteUtil.splitBytesToInt(this.getFileData(), 0, length);
        interceptBytes(length, this.getFileData().length - length);
        return i;
    }
    public double getDouble(int length) {
        double s  = ByteUtil.splitBytesToDouble(this.getFileData(), 0, length);
        interceptBytes(length, this.getFileData().length - length);
        return s;
    }
    public double getFloat(int length) {
        float s  = ByteUtil.splitBytesToFloat(this.getFileData(), 0, length);
        interceptBytes(length, this.getFileData().length - length);
        return s;
    }

    public String getString(int length) {
        String s  = ByteUtil.splitBytesToString(this.getFileData(), 0, length);
        interceptBytes(length, this.getFileData().length - length);
        return s;
    }

    public byte[] getOriBytes(int length) {
        return ArrayUtil.interceptByte(this.getFileData(), 0, length);
    }


    void interceptBytes(int begin, int end) {
        this.setFileData(ArrayUtil.interceptByte(this.getFileData(), begin, end));
    }

    public String getRelatePath() {
        return relatePath;
    }

    public FileProperties setRelatePath(String relatePath) {
        this.relatePath = relatePath;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileProperties setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public FileProperties setFileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }

    public byte[] getOriFileData() {
        return oriFileData;
    }

    public FileProperties setOriFileData(byte[] oriFileData) {
        this.oriFileData = oriFileData;
        return this;
    }

    public int getOriFileDataSize() {
        return oriFileDataSize;
    }

    public FileProperties setOriFileDataSize(int oriFileDataSize) {
        this.oriFileDataSize = oriFileDataSize;
        return this;
    }

}

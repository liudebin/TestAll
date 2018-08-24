package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */

public class ConstantUtf8Info extends ConstantInfo{
    int byteLength;
    String value;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        byteLength = fileProp.getInt(2);

        byte[] tmp = new byte[oriDataLength + byteLength];
        byte[] tmp1 = fileProp.getOriBytes(byteLength);
        System.arraycopy(this.getBytes(), 0, tmp, 0, oriDataLength);
        System.arraycopy(tmp1, 0, tmp, oriDataLength,  byteLength );
        this.bytes = tmp;
        value = fileProp.getString(byteLength);
        chained = true;
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 2;
        return 2;
    }

    public int getByteLength() {
        return byteLength;
    }

    public ConstantUtf8Info setByteLength(int length) {
        this.byteLength = length;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ConstantUtf8Info setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "ConstantUtf8Info{" +
                "   value='" + value + '\'' +
                ", tag=" + tag +
                ", byteLength=" + byteLength +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}

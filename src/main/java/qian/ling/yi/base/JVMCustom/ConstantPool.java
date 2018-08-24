package qian.ling.yi.base.JVMCustom;

import qian.ling.yi.base.JVMCustom.constantInfo.ConstantInfo;

import java.util.Arrays;

/**
 * constantpool
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ConstantPool {
    int length;  // 从 1 开始
    byte[] data;
    ConstantInfo[] constantInfo;

    public int getLength() {
        return length;
    }

    public ConstantPool setLength(int length) {
        this.length = length;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ConstantPool setData(byte[] data) {
        this.data = data;
        return this;
    }

    public ConstantInfo[] getConstantInfo() {
        return constantInfo;
    }

    public ConstantPool setConstantInfo(ConstantInfo[] constantInfo) {
        this.constantInfo = constantInfo;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConstantPool{");
        sb.append("length=").append(length);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append(", constantInfo=");
        for (int i = 0; i < constantInfo.length; i++) {
            sb.append("\n\n [index : ")
                    .append(i).append("\t")
                    .append(constantInfo[i])
                    .append(" ]");
        }


        sb.append('}');
        return sb.toString();
    }
}

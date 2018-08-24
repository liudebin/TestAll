package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 * @author liuguobin
 * @date 2018/4/29
 */

public abstract class ConstantInfo {
    int tag;
    byte[] bytes;

    int oriDataLength;
    boolean chained;
    public void init(FileProperties fileProp) {
        initOriData(fileProp);
        build(fileProp);
    }

    protected void build(FileProperties fileProp) {};

    void initOriData(FileProperties fileProp) {
        bytes = fileProp.getOriBytes(getOriDataLength());
    }

    public abstract int getOriDataLength();

    public void setOriDataLength(int oriDataLength) {
        this.oriDataLength = oriDataLength;
    }

//    ConstantInfo[] constants;


    public int getTag() {
        return tag;
    }

    public ConstantInfo setTag(int tag) {
        this.tag = tag;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ConstantInfo setBytes(byte[] data) {
        this.bytes = data;
        return this;
    }

    public void chain(ConstantInfo[] fields) {
        if (chained) return;

        if (!getIndex(fields)) {
            throw new RuntimeException(this.getClass().getName().concat("索引为空"));
        }
        chainDepends(fields);
        chainSelf();
        chained = true;
    }

    public void chainSelf() {
    }

    protected void chainDepends(ConstantInfo[] fields) {
    }

    public boolean getIndex(ConstantInfo[] fields) {
        return true;
    }
//    public ConstantInfo[] getConstants() {
//        return constants;
//    }
//
//    public ConstantInfo setConstants(ConstantInfo[] constants) {
//        this.constants = constants;
//        return this;
//    }
}

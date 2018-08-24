package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantClassInfo extends ConstantInfo {
    int nameIndex;
    String className;
    ConstantUtf8Info constantUtf8Info;
//
//    public ConstantClassInfo(int nameIndex, String className) {
//        this.nameIndex = nameIndex;
//        this.className = className;
//    }


    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        nameIndex = fileProp.getInt(2);//todo
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 2;
        return 2;
    }

    @Override
    public void chainSelf() {
        className = constantUtf8Info.getValue();
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return null !=(constantUtf8Info = (ConstantUtf8Info) fields[nameIndex]);
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        constantUtf8Info.chain(fields);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public ConstantClassInfo setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ConstantClassInfo setClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public String toString() {
        return "ConstantClassInfo{" +
                "    className='" + className + '\'' +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", nameIndex=" + nameIndex +
//                ", constantUtf8Info=" + constantUtf8Info +
                '}';
    }
}

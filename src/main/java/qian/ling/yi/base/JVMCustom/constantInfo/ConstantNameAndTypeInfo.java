package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {
    int nameIndex;
    int desIndex;
    String name;
    String des; // 参数列表和返回值
    ConstantUtf8Info nameInfo;
    ConstantUtf8Info desInfo;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        nameIndex = fileProp.getInt(2);
        desIndex = fileProp.getInt(2);
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 4;
        return 4;
    }

    @Override
    public void chainSelf() {
        name = nameInfo.getValue();
        des = desInfo.getValue();
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return null != (nameInfo = (ConstantUtf8Info) fields[nameIndex])
                && null != (desInfo = (ConstantUtf8Info) fields[desIndex]);
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        nameInfo.chain(fields);
        desInfo.chain(fields);
    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfo{" +
                "   name='" + name + '\'' +
                "    des='" + des + '\'' +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", nameIndex=" + nameIndex +
                ", desIndex=" + desIndex +
//                ", nameInfo=" + nameInfo +
//                ", desInfo=" + desInfo +
                '}';
    }


    public int getNameIndex() {
        return nameIndex;
    }

    public ConstantNameAndTypeInfo setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public int getDesIndex() {
        return desIndex;
    }

    public ConstantNameAndTypeInfo setDesIndex(int desIndex) {
        this.desIndex = desIndex;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConstantNameAndTypeInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDes() {
        return des;
    }

    public ConstantNameAndTypeInfo setDes(String des) {
        this.des = des;
        return this;
    }

    public ConstantUtf8Info getNameInfo() {
        return nameInfo;
    }

    public ConstantNameAndTypeInfo setNameInfo(ConstantUtf8Info nameInfo) {
        this.nameInfo = nameInfo;
        return this;
    }

    public ConstantUtf8Info getDesInfo() {
        return desInfo;
    }

    public ConstantNameAndTypeInfo setDesInfo(ConstantUtf8Info desInfo) {
        this.desInfo = desInfo;
        return this;
    }
}

package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantMethodTypeInfo extends ConstantInfo {
    int desIndex;
    ConstantUtf8Info desInfo;
    String des;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        desIndex = fileProp.getInt(2);
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 2;
        return 2;
    }

    @Override
    public void chainSelf() {
        des = desInfo.getValue();
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return  (desInfo = (ConstantUtf8Info) fields[desIndex]) != null;
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        desInfo.chain(fields);
    }

    @Override
    public String toString() {
        return "\nConstantMethodTypeInfo{" +
                "   des='" + des + '\'' +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", desIndex=" + desIndex +
//                ", desInfo=" + desInfo +
                '}';
    }
}

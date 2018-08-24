package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantStringInfo extends ConstantInfo {
    int stringIndex;
    ConstantUtf8Info constantUtf8Info;
    String value;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        stringIndex = fileProp.getInt(2);
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 2;
        return 2;
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return null != (constantUtf8Info = (ConstantUtf8Info) fields[stringIndex]);
    }

    @Override
    public void chainSelf() {
        value = constantUtf8Info.getValue();
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        constantUtf8Info.chain(fields);
    }

    @Override
    public String toString() {
        return "ConstantStringInfo{" +
                "    value='" + value + '\'' +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", stringIndex=" + stringIndex +
//                ", constantUtf8Info=" + constantUtf8Info +
                '}';
    }
}

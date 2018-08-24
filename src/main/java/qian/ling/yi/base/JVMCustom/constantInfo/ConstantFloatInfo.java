package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantFloatInfo extends ConstantInfo {
    float value;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        fileProp.getFloat(4);
        chained = true;
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 4;
        return 4;
    }

    @Override
    public String toString() {
        return "\nConstantFloatInfo{" +
                "   value=" + value +
                ", tag=" + tag +
//                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}

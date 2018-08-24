package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantDoubleInfo extends ConstantInfo {
    double value;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        value = fileProp.getDouble(8);
        chained = true;
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 8;
        return 8;
    }

    @Override
    public String toString() {
        return "\nConstantDoubleInfo{" +
                "   value=" + value +
                ", tag=" + tag +
//                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}

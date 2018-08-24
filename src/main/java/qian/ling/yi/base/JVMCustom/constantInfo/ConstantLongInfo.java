package qian.ling.yi.base.JVMCustom.constantInfo;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantLongInfo extends ConstantInfo {
    int value;

    @Override
    public String toString() {
        return "\nConstantLongInfo{" +
                "   value=" + value +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 8;
        return 8;
    }
}

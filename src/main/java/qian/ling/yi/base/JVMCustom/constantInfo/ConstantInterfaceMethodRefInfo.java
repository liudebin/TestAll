package qian.ling.yi.base.JVMCustom.constantInfo;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantInterfaceMethodRefInfo extends ConstantMethodRefInfo {
    @Override
    public String toString() {
        return "ConstantInterfaceMethodRefInfo{" +
                "   className='" + className + '\'' +
                "  methodNameAndDes='" + methodNameAndDes + '\'' +
                "tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
//                ", classInfo=" + classInfo +
//                ", nameAndTypeInfo=" + nameAndTypeInfo +
                '}';
    }
}

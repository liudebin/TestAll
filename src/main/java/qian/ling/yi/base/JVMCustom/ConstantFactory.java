package qian.ling.yi.base.JVMCustom;

import org.apache.commons.lang.ArrayUtils;
import qian.ling.yi.base.JVMCustom.constantInfo.*;

/**
 * fatory
 *
 * @author liuguobin
 * @date 2018/4/29
 */

public class ConstantFactory {
    static Class[] info = new Class[19];
    static {
        info[1] =  ConstantUtf8Info.class;
        info[3] =  ConstantIntegerInfo.class;
        info[4] =  ConstantFloatInfo.class;
        info[5] =  ConstantLongInfo.class;
        info[6] =  ConstantDoubleInfo.class;
        info[7] =  ConstantClassInfo.class;
        info[8] =  ConstantStringInfo.class;
        info[9] =  ConstantFiledRefInfo.class;
        info[10] =  ConstantMethodRefInfo.class;
        info[11] =  ConstantInterfaceMethodRefInfo.class;
        info[12] =  ConstantNameAndTypeInfo.class;
        info[15] =  ConstantMethodHandleInfo.class;
        info[16] =  ConstantMethodTypeInfo.class;
        info[18] =  ConstantInvokeDynamicInfo.class;
    }
    public static ConstantInfo getByTag(int tag) {
        return null;
    }

    public static ConstantInfo createConstant(FileProperties filePro) {
        try {
            byte[] oriData = filePro.getOriBytes(1);
            int tag = filePro.getInt(1);
//            System.out.println(tag);
//            System.out.println(info[tag]);
            ConstantInfo constantInfo = (ConstantInfo) info[tag].newInstance();
            constantInfo.setTag(tag);
            constantInfo.init(filePro);
            constantInfo.setOriDataLength(constantInfo.getOriDataLength() + 1);
            constantInfo.setBytes(ArrayUtils.addAll(oriData, constantInfo.getBytes()));
//            System.out.println(constantInfo);
            return constantInfo;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

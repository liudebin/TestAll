package qian.ling.yi.base.JVMCustom;

import qian.ling.yi.base.JVMCustom.constantInfo.ConstantInfo;

/**
 * ConstantPoolBuilder
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ConstantPoolBuilder {

    public static int getTag(FileProperties fileProp) {
        return fileProp.getInt(1);
    }

    public static void buildConstantPool(ClassFile classFile) {
        ConstantPool pool = new ConstantPool();
        FileProperties filePro = classFile.getFileProp();
        int constantNum = filePro.getInt(2);
        pool.setLength(constantNum);
        ConstantInfo[] fields = new ConstantInfo[constantNum]; //包含第0个，但是16位，0 个不知道是啥？
        pool.setConstantInfo(fields);
        for (int i = 1; i < constantNum ; i++) {
            fields[i]  = ConstantFactory.createConstant(filePro);
//            System.out.println("第几个了“" + i);
        }
        for (int i = 1; i < fields.length; i++) {
            ConstantInfo field = fields[i];
            field.chain(fields);
        }
        classFile.setConstantPool(pool);
//        System.out.println(Arrays.toString(fields));
    }

}

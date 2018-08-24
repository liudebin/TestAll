package qian.ling.yi.base.JVMCustom;

import qian.ling.yi.base.JVMCustom.constantInfo.ConstantInfo;
import qian.ling.yi.base.JVMCustom.constantInfo.ConstantUtf8Info;

/**
 * attributes
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class AttributeBuilder {

    public static Attribute[] buildAttr(int attrNum, ClassFile classFile) {
        FileProperties prop = classFile.getFileProp();
        final ConstantInfo[] constantInfo = classFile.getConstantPool().getConstantInfo();
        Attribute[] attrs = new Attribute[attrNum];
        for (int i = 0; i < attrNum; i++) {
            Attribute attribute = new Attribute();
            int nameIndex = prop.getInt(2);
            attribute.setNameIndex(nameIndex);
            ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfo[nameIndex];
            attribute.setName(utf8Info.getValue());
            final int length = prop.getInt(4);
            attribute.setByteLength(length);
            attribute.setByteData(prop.getOriBytes(length));
            prop.interceptBytes(length, prop.getFileData().length - length);
            attrs[i] = attribute;
        }
        return attrs;
    }

}

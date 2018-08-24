package qian.ling.yi.base.JVMCustom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.base.JVMCustom.constantInfo.ConstantClassInfo;
import qian.ling.yi.base.JVMCustom.constantInfo.ConstantInfo;
import qian.ling.yi.base.JVMCustom.constantInfo.ConstantUtf8Info;

import java.util.Objects;

/**
 * Class 文件格式
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ClassFileBuilder {
    private static Logger logger = LoggerFactory.getLogger(ClassFileBuilder.class);
    private final static String MAGIC_NUM = "CAFEBABE";

    public static ClassFile build(String path) {
        FileProperties fileProp = new FileProperties(path);
        return buildClass(fileProp);
    }

    private static ClassFile buildClass(FileProperties fileProp) {
        ClassFile classFile = new ClassFile();
        classFile.setFileProp(fileProp);
        getCheckMagic(fileProp);
        classFile.setMagic(MAGIC_NUM);
        setVersion(classFile);
        ConstantPoolBuilder.buildConstantPool(classFile);
        setAccFlags(classFile);
        classFile.setClassIndex(fileProp.getInt(2));
        classFile.setSuperClassIndex(fileProp.getInt(2));
        ConstantClassInfo classInfo = (ConstantClassInfo)classFile.getConstantPool().getConstantInfo()[classFile.getClassIndex()];
        ConstantClassInfo superClassInfo = (ConstantClassInfo)classFile.getConstantPool().getConstantInfo()[classFile.getSuperClassIndex()];

        classFile.setClassName(classInfo.getClassName());
        classFile.setSuperClassName(superClassInfo.getClassName());
        int interNum = fileProp.getInt(2);
        int[] interF = new int[interNum];
        for (int i = 0; i < interNum; i++) {
            interF[i] = fileProp.getInt(2);
        }
        classFile.setInterF(interF);

        setClassFields(classFile);
        setMethods(classFile);
        setAttrs(classFile);
        logger.info(classFile.toString());
        return classFile;
    }

    private static void setAttrs(ClassFile classFile) {
        FileProperties prop = classFile.getFileProp();
        int attrNum = prop.getInt(2);
        final Attribute[] attributes = AttributeBuilder.buildAttr(attrNum, classFile);
        classFile.setClassAttrs(attributes);
    }


    private static void setClassFields(ClassFile classFile) {
        FileProperties prop = classFile.getFileProp();
        int fieldNum = prop.getInt(2);
        ClassField[] fields = new ClassField[fieldNum];
        classFile.setFieldNum(fieldNum);

        final ConstantInfo[] constantInfo = classFile.getConstantPool().getConstantInfo();
        for (int i = 0; i < fieldNum; i++) {
            ClassField field = new ClassField();
            fields[i] = field;
            field.setAccFlag(FieldsAccFlagBuilder.getFiledAccFlags(prop.getHex(2)));
            field.setNameIndex(prop.getInt(2));
            field.setDesIndex(prop.getInt(2));
            final ConstantUtf8Info nameInfo = (ConstantUtf8Info) constantInfo[field.getNameIndex()];
            final ConstantUtf8Info desInfo = (ConstantUtf8Info) constantInfo[field.getDesIndex()];
            field.setName(nameInfo.getValue());
            field.setDes(desInfo.getValue());
            int attrNum = prop.getInt(2);
            field.setAttrNum(attrNum);
            final Attribute[] attributes = AttributeBuilder.buildAttr(attrNum, classFile);
            field.setClassAttrs(attributes);
            //还有好多
        }
        classFile.setClassFields(fields);
    }

    private static void setMethods(ClassFile classFile) {
        FileProperties prop = classFile.getFileProp();
        int methodNum = prop.getInt(2);
        ClassMethod[] methods = new ClassMethod[methodNum];
        classFile.setMethodNum(methodNum);
        classFile.setClassMethods(methods);

        final ConstantInfo[] constantInfo = classFile.getConstantPool().getConstantInfo();
        for (int i = 0; i < methodNum; i++) {
            ClassMethod method = new ClassMethod();
            methods[i] = method;
            method.setAccFlag(MethodAccFlagBuilder.getMethodAccFlags(prop.getHex(2)));
            method.setNameIndex(prop.getInt(2));
            method.setDesIndex(prop.getInt(2));
            final ConstantUtf8Info nameInfo = (ConstantUtf8Info) constantInfo[method.getNameIndex()];
            final ConstantUtf8Info desInfo = (ConstantUtf8Info) constantInfo[method.getDesIndex()];
            method.setName(nameInfo.getValue());
            method.setDes(desInfo.getValue());
            int attrNum = prop.getInt(2);
            method.setAttrNum(attrNum);
            final Attribute[] attributes = AttributeBuilder.buildAttr(attrNum, classFile);
            method.setClassAttrs(attributes);
        }
        classFile.setClassMethods(methods);
    }



    private static void setAccFlags(ClassFile classFile) {
        final String flag = classFile.getFileProp().getHex(2);
        final char[] chars = flag.toCharArray();
        classFile.setAccessFlag(AccFlagBuilder.getFlags(chars));
    }

    /**
     * 魔数
     * @param fileProp
     */
    private static void getCheckMagic(FileProperties fileProp) {
        String magic = fileProp.getHex(4);
        if (!Objects.equals(MAGIC_NUM, magic)) {
            throw new RuntimeException("error Class, Magic num is wrong");
        }
    }

    /**
     * 版本号
     */
    private static void setVersion(ClassFile classFile) {
        classFile.setMinorVersion(classFile.getFileProp().getInt(2));
        classFile.setMajorVersion(classFile.getFileProp().getInt(2));
    }

    /**
     * 常量池
     * @param
     */
    private static ConstantPool buildConstantPool(FileProperties fileProp) {
        int i = fileProp.getInt(2);

        return null;
    }

    public static void main(String[] args) {
//        ClassFileBuilder.build("/qian/ling/yi/base/JVMCustom/testFile/SimpleObject.class");
//        ClassFileBuilder.build("/qian/ling/yi/base/ReferenceTest.class");
//        ClassFileBuilder.build("/qian/ling/yi/base/singleton/TestSingleton.class");
        ClassFileBuilder.build("/qian/ling/yi/base/singleton/SimpleClass.class");
    }

}

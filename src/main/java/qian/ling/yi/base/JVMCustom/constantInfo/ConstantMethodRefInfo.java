package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

import java.util.Arrays;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantMethodRefInfo extends ConstantInfo {
    int classIndex;
    int nameAndTypeIndex;
    ConstantClassInfo classInfo;
    ConstantNameAndTypeInfo nameAndTypeInfo;
    String className;
    String methodNameAndDes;



    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        classIndex = fileProp.getInt(2);
        nameAndTypeIndex = fileProp.getInt(2);

    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 4;
        return 4;
    }

    @Override
    public void chainSelf() {
        className = classInfo.getClassName();
        methodNameAndDes = nameAndTypeInfo.getName() + "  " + nameAndTypeInfo.getDes();
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return null != (classInfo = (ConstantClassInfo) fields[classIndex])
                && null != (nameAndTypeInfo = (ConstantNameAndTypeInfo) fields[nameAndTypeIndex]);
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        classInfo.chain(fields);
        nameAndTypeInfo.chain(fields);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public ConstantMethodRefInfo setClassIndex(int classIndex) {
        this.classIndex = classIndex;
        return this;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public ConstantMethodRefInfo setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
        return this;
    }

    public ConstantClassInfo getClassInfo() {
        return classInfo;
    }

    public ConstantMethodRefInfo setClassInfo(ConstantClassInfo classInfo) {
        this.classInfo = classInfo;
        return this;
    }

    public ConstantNameAndTypeInfo getNameAndTypeInfo() {
        return nameAndTypeInfo;
    }

    public ConstantMethodRefInfo setNameAndTypeInfo(ConstantNameAndTypeInfo nameAndTypeInfo) {
        this.nameAndTypeInfo = nameAndTypeInfo;
        return this;
    }


    @Override
    public String toString() {
        return "ConstantMethodRefInfo{" +
                "    className='" + className + '\'' +
                "  methodNameAndDes='" + methodNameAndDes + '\'' +
                ", tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
//                ", classInfo=" + classInfo +
//                ", nameAndTypeInfo=" + nameAndTypeInfo +
                '}';
    }
}

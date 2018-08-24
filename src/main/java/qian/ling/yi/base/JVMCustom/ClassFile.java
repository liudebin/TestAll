package qian.ling.yi.base.JVMCustom;

import com.google.common.base.MoreObjects;

/**
 * Class 文件格式
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ClassFile {
    FileProperties fileProp;
    String magic;
    int majorVersion;
    int minorVersion;
    ConstantPool constantPool;
    String[] accessFlag;
    int classIndex;
    String className;
    int superClassIndex;
    String superClassName;
    int[] interF;

    int fieldNum;
    ClassField[] classFields;
    int methodNum;
    ClassMethod[] classMethods;
    int attrNum;
    Attribute[] classAttrs;

    ClassFile[] interfaces;
    public FileProperties getFileProp() {
        return fileProp;
    }

    public ClassFile setFileProp(FileProperties fileProp) {
        this.fileProp = fileProp;
        return this;
    }

    public String getMagic() {
        return magic;
    }

    public ClassFile setMagic(String magic) {
        this.magic = magic;
        return this;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ClassFile setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
        return this;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public ClassFile setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
        return this;
    }

    public String[] getAccessFlag() {
        return accessFlag;
    }

    public ClassFile setAccessFlag(String[] accessFlag) {
        this.accessFlag = accessFlag;
        return this;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public ClassFile setClassIndex(int classIndex) {
        this.classIndex = classIndex;
        return this;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public ClassFile setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
        return this;
    }

    public ClassFile[] getInterfaces() {
        return interfaces;
    }

    public ClassFile setInterfaces(ClassFile[] interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ClassFile setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
        return this;
    }

    public int[] getInterF() {
        return interF;
    }

    public ClassFile setInterF(int[] interF) {
        this.interF = interF;
        return this;
    }

    public int getFieldNum() {
        return fieldNum;
    }

    public ClassFile setFieldNum(int fieldNum) {
        this.fieldNum = fieldNum;
        return this;
    }

    public ClassField[] getClassFields() {
        return classFields;
    }

    public ClassFile setClassFields(ClassField[] classFields) {
        this.classFields = classFields;
        return this;
    }

    public int getMethodNum() {
        return methodNum;
    }

    public ClassFile setMethodNum(int methodNum) {
        this.methodNum = methodNum;
        return this;
    }

    public ClassMethod[] getClassMethods() {
        return classMethods;
    }

    public ClassFile setClassMethods(ClassMethod[] classMethods) {
        this.classMethods = classMethods;
        return this;
    }

    public int getAttrNum() {
        return attrNum;
    }

    public ClassFile setAttrNum(int attrNum) {
        this.attrNum = attrNum;
        return this;
    }

    public Attribute[] getClassAttrs() {
        return classAttrs;
    }

    public ClassFile setClassAttrs(Attribute[] attributes) {
        this.classAttrs = attributes;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassFile setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public ClassFile setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("\nfileProp", fileProp)
                .add("\nmagic", magic)
                .add("\nmajorVersion", majorVersion)
                .add("\nminorVersion", minorVersion)
                .add("\nconstantPool", constantPool)
                .add("\naccessFlag", accessFlag)
                .add("\nclassIndex", classIndex)
                .add("\nclassName", className)
                .add("\nsuperClassIndex", superClassIndex)
                .add("\nsuperClassName", superClassName)
                .add("\ninterF", interF)
                .add("\nfieldNum", fieldNum)
                .add("\nclassFields", classFields)
                .add("\nmethodNum", methodNum)
                .add("\nclassMethods", classMethods)
                .add("\nattrNum", attrNum)
                .add("\nclassAttrs", classAttrs)
                .add("\ninterfaces", interfaces)
                .toString();
    }
}

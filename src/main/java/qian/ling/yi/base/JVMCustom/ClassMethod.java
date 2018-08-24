package qian.ling.yi.base.JVMCustom;

import java.util.Arrays;

/**
 * fields
 *
 * @author liuguobin
 * @date 2018/5/1
 */

public class ClassMethod {
    String[] accFlag;

    int nameIndex;
    int desIndex;
    String name;
    String des;

    int attrNum;
    Attribute[] classAttrs;

    public String[] getAccFlag() {
        return accFlag;
    }

    public ClassMethod setAccFlag(String[] accFlag) {
        this.accFlag = accFlag;
        return this;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public ClassMethod setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public int getDesIndex() {
        return desIndex;
    }

    public ClassMethod setDesIndex(int desIndex) {
        this.desIndex = desIndex;
        return this;
    }

    public Attribute[] getClassAttrs() {
        return classAttrs;
    }

    public ClassMethod setClassAttrs(Attribute[] classAttrs) {
        this.classAttrs = classAttrs;
        return this;
    }

    public int getAttrNum() {
        return attrNum;
    }

    public ClassMethod setAttrNum(int attrNum) {
        this.attrNum = attrNum;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClassMethod setName(String name) {
        this.name = name;
        return this;
    }

    public String getDes() {
        return des;
    }

    public ClassMethod setDes(String des) {
        this.des = des;
        return this;
    }

    @Override
    public String toString() {
        return "ClassMethod{" +
                "accFlag=" + Arrays.toString(accFlag) +
                ", nameIndex=" + nameIndex +
                ", desIndex=" + desIndex +
                ", name=" + name +
                ", des=" + des +
                ", attrNum=" + attrNum +
                ", classAttrs=" + Arrays.toString(classAttrs) +
                '}';
    }
}

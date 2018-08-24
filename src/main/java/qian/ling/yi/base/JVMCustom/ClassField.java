package qian.ling.yi.base.JVMCustom;

import java.util.Arrays;

/**
 * fields
 *
 * @author liuguobin
 * @date 2018/5/1
 */

public class ClassField {

    String[] accFlag;

    int nameIndex;
    int desIndex;

    int attrNum;
    Attribute[] classAttrs;

    String name;
    String des;

    public String[] getAccFlag() {
        return accFlag;
    }

    public ClassField setAccFlag(String[] accFlag) {
        this.accFlag = accFlag;
        return this;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public ClassField setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public int getDesIndex() {
        return desIndex;
    }

    public ClassField setDesIndex(int desIndex) {
        this.desIndex = desIndex;
        return this;
    }

    public Attribute[] getClassAttrs() {
        return classAttrs;
    }

    public ClassField setClassAttrs(Attribute[] classAttrs) {
        this.classAttrs = classAttrs;
        return this;
    }

    public int getAttrNum() {
        return attrNum;
    }

    public ClassField setAttrNum(int attrNum) {
        this.attrNum = attrNum;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClassField setName(String name) {
        this.name = name;
        return this;
    }

    public String getDes() {
        return des;
    }

    public ClassField setDes(String des) {
        this.des = des;
        return this;
    }

    @Override
    public String toString() {
        return "ClassField{" +
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

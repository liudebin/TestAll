package qian.ling.yi.base.JVMCustom;

import java.util.Arrays;

/**
 * attributes
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class Attribute {
    int nameIndex;
    int byteLength;
    byte[] byteData;

    String name;


    public int getNameIndex() {
        return nameIndex;
    }

    public Attribute setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public int getByteLength() {
        return byteLength;
    }

    public Attribute setByteLength(int byteLength) {
        this.byteLength = byteLength;
        return this;
    }

    public byte[] getByteData() {
        return byteData;
    }

    public Attribute setByteData(byte[] byteData) {
        this.byteData = byteData;
        return this;
    }

    public String getName() {
        return name;
    }

    public Attribute setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name=" + name +
                ", nameIndex=" + nameIndex +
                ", byteLength=" + byteLength +
                ", byteData=" + Arrays.toString(byteData) +
                '}';
    }
}

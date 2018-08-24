package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantInvokeDynamicInfo extends ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    ConstantNameAndTypeInfo nameAndTypeInfo;
    String name ;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        bootstrapMethodAttrIndex = fileProp.getInt(2);
        nameAndTypeIndex = fileProp.getInt(2);
        System.out.println("-----------------------------------------------");
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 4;
        return 4;
    }

    @Override
    public void chainSelf() {
        name = nameAndTypeInfo.getName();
    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return (nameAndTypeInfo = (ConstantNameAndTypeInfo) fields[nameAndTypeIndex])
                != null;
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        nameAndTypeInfo.chain(fields);
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public ConstantInvokeDynamicInfo setBootstrapMethodAttrIndex(int bootstrapMethodAttrIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        return this;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public ConstantInvokeDynamicInfo setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
        return this;
    }

    public ConstantNameAndTypeInfo getNameAndTypeInfo() {
        return nameAndTypeInfo;
    }

    public ConstantInvokeDynamicInfo setNameAndTypeInfo(ConstantNameAndTypeInfo nameAndTypeInfo) {
        this.nameAndTypeInfo = nameAndTypeInfo;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConstantInvokeDynamicInfo setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "ConstantInvokeDynamicInfo{" +
                "   name='" + name + '\'' +
                ", bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
//                ", nameAndTypeInfo=" + nameAndTypeInfo +
                '}';
    }
}

package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 *
 * todo  干嘛用的呢
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantFiledRefInfo extends ConstantInfo {
    int classIndex;
    int nameAndTypeIndex;
    ConstantClassInfo constantClassInfo;
    ConstantNameAndTypeInfo constantNameAndTypeInfo;
    String className;
    String fieldName;

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
    public boolean getIndex(ConstantInfo[] fields) {
        return null != (constantClassInfo =(ConstantClassInfo) fields[classIndex])
                && null != (constantNameAndTypeInfo =(ConstantNameAndTypeInfo) fields[nameAndTypeIndex]);
    }

    @Override
    public void chainSelf() {
        className = constantClassInfo.getClassName();
        fieldName = constantNameAndTypeInfo.getName();
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
        constantClassInfo.chain(fields);
        constantNameAndTypeInfo.chain(fields);
    }
}

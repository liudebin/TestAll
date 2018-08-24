package qian.ling.yi.base.JVMCustom.constantInfo;

import qian.ling.yi.base.JVMCustom.FileProperties;

/**
 * @author liuguobin
 * @date 2018/4/29
 */
public class ConstantMethodHandleInfo extends ConstantInfo {
    int refKind;
    int refIndex;

    @Override
    protected void build(FileProperties fileProp) {
        super.build(fileProp);
        refKind = fileProp.getInt(1);
        refIndex = fileProp.getInt(2);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public int getOriDataLength() {
        oriDataLength = 8;
        return 8;
    }


    @Override
    public void chainSelf() {

    }

    @Override
    public boolean getIndex(ConstantInfo[] fields) {
        return null != fields[refIndex];
    }

    @Override
    protected void chainDepends(ConstantInfo[] fields) {
//        nameAndTypeInfo.chained(fields);
    }

}

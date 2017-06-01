package qian.ling.yi.ext.mockito;

/**
 * 基本类
 *
 * @author liuguobin
 * @date 2017/5/20
 */

public class BaseBean {
    BaseBeanBase baseBeanBase;
    BaseBean() {
        System.out.println("BaseBean construct");
    }

    BaseBean(BaseBeanBase baseBeanBase) {

        System.out.println("BaseBean construct inject baseBeanBase");
    }

//    BaseBean(BaseBeanBase baseBeanBase, BaseBeanBase baseBeanBase1) {
//
//        System.out.println("BaseBean construct inject baseBeanBase 1");
//    }

    public BaseBeanBase getBaseBeanBase() {
        return baseBeanBase;
    }

    public void setBaseBeanBase(BaseBeanBase baseBeanBase) {
        System.out.println("set");
        this.baseBeanBase = baseBeanBase;
    }
}

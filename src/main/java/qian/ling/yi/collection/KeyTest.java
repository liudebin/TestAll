package qian.ling.yi.collection;

/**
 * KeyTest
 *
 * @author liuguobin
 * @date 2018/5/28
 */

public class KeyTest extends Object{
    int i;

    public int getI() {
        return i;
    }

    public KeyTest setI(int i) {
        this.i = i;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals方法");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyTest keyTest = (KeyTest) o;
        return this == o;
//        return i == keyTest.i;
    }

    @Override
    public int hashCode() {
        System.out.println("hashCode");
        return i;
    }

    @Override
    public String toString() {
        return "" + i;
    }
}

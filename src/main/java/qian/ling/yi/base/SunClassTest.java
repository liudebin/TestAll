package qian.ling.yi.base;

import org.junit.Test;

/**
 * 覆盖父类方法
 *
 * @author liuguobin
 * @date 2017/4/7
 */

/**
 * 如果在子类设置了get方法，则获取的子类值，为空
 */
public class SunClassTest extends SuperClass{
    public String str;

    public Long l;

    public String getSuperStr() {
        return super.str;
    }
    
    public Long getSuperL() {
        return super.l;
    }

    public String getStr() {
        return str;
    }

    public Long getL() {
        return l;
    }

    @Override
    public void mul () {
        System.out.println(" sun");
    }

    public void sun() {
        System.out.println("private " );
    }


    @Test
    public void testVar() {
        SunClassTest sunClassTest = new SunClassTest();
        sunClassTest.setStr("12");
        System.out.println("sunClassTest.getStr() = " + sunClassTest.getStr());
        System.out.println("sunClassTest.getSuperStr() = " + sunClassTest.getSuperStr());

        sunClassTest.setL(1L);
        System.out.println("sunClassTest.getL() = " + sunClassTest.getL());
        System.out.println("sunClassTest.getSuperL() = " + sunClassTest.getSuperL());
    }

    @Test
    public void testMethod() {
        SunClassTest sun = new SunClassTest();
        sun.mul();

        SuperClass supers = sun;
        sun.sun();
        supers.mul();
    }
}

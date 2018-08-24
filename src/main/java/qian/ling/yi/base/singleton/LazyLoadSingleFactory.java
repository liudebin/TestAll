package qian.ling.yi.base.singleton;

/**
 * Lazy
 *
 * @author liuguobin
 * @date 2018/5/21
 */

public class LazyLoadSingleFactory {


    static {
        System.out.println(" LazyLoadSingleFactory init");
    }

    public static class SingletonHolder {
        static {
            System.out.println("SingletonHolder static init" );
        }
        public final static BaseClass single = new BaseClass();
    }

    public static BaseClass getSingleton() {
        return SingletonHolder.single;
    }

    static class BaseClass {
        {
            System.out.println("BaseClass init" );
        }

        static {
            System.out.println("BaseClass static init" );
        }

        private BaseClass(){};

        public void test() {
            System.out.println("test");
        }
    }
}

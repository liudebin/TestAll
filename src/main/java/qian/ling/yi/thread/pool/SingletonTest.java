package qian.ling.yi.thread.pool;

public class SingletonTest {

    public static class SingletonHolder {
        public static  SingletonTest baseClass = new SingletonTest();

        public static  SingletonTest getBaseClass() {
            return baseClass;
        }
    }

    public static void main(String[] args) {
        SingletonTest baseClass = SingletonHolder.getBaseClass();
    }



}

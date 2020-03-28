package qian.ling.yi.arithmetic;

import org.junit.Test;

public class IntegerReverseTest {

    public static  int invo(int i ) {
        int mod = 10;
        int target = 0;
        int tmp;
        while (!isEnd(i, mod)) {
            tmp = i%mod;
            target = target *10 + tmp;
            i = i/mod;
        }
        return target;
    }

    public static boolean isEnd(int i , int j) {
        int i1 = i / j;
        int i2 = i%j;
        return i1+i2 == 0;
    }


    public static int reverse(int i ) {
        int mod = 10;
        int target = 0;
        int tmp;
        do {
            tmp = i%mod;
            target = target * 10 + tmp;
            i = i/mod;
        } while (tmp+i !=0);
        return target;
    }


    @Test
    public void test() {
        System.out.println(invo(1000));
        System.out.println(invo(12));
        System.out.println(invo(1222));
        System.out.println(invo(12345));
        int[] a = new int[2];

        System.out.println(reverse(1000));
        System.out.println(reverse(12));
        System.out.println(reverse(1222));
        System.out.println(reverse(12345));
    }





}

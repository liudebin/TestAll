package qian.ling.yi.arithmetic.face;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class MaxCount {
    public static int massage(int[] nums) {
        int length = nums.length;
        int max = 0;
        int tmp = 0;
        int maxTmp = 0;
        for(int i = 0; i< length; i ++ ) {
            tmp = nums[i];
            if (i < length -2) {
                for(int m = i +2; m < length; m ++) {
                    maxTmp = tmp;
                    for(int j = m; j< length; ) {
                        maxTmp = maxTmp + nums[j];
                        if (max< maxTmp) {
                            max=maxTmp;
                        }
                        j = j+2;
                    }
                }
            } else {
                if (max< maxTmp) {
                    max=maxTmp;
                }
            }


        }
        if (max <maxTmp) {
            max = maxTmp;
        }
        return max;
    }

    public static  int maxCount(int[] nums) {
        int length = nums.length;
        int tmp = 0;
        int maxTmp = 0;
        int max = 0 ;
        for(int i = 0; i< length; i ++ ) {
            tmp = nums[i];
            if (i < length - 2) {
                for(int m = i +2; m < length; m ++) {
                    maxTmp = tmp + maxCount(Arrays.copyOfRange(nums, m, length));
                    if (max <maxTmp) {
                        max = maxTmp;
                    }
                }
            } else {
                if (max < tmp) {
                    max = tmp;
                }
            }
        }
        return max;
    }

    public static  int maxCount2(int[] nums) {
        int unchecked = 0;
        int checked = 0;

        for(int num : nums) {
            //当前状态不选的情况，可能前一个选，也可能不选
            int unchecked_temp = Math.max(unchecked, checked);
            //当前状态选的情况，必须是前一个不选
            checked = unchecked + num;
            unchecked = unchecked_temp;
        }

        return Math.max(unchecked, checked);
    }

    @Test
    public void test(){
//        System.out.println(massage(new int []{1,2,3,1}));
//        System.out.println(massage(new int []{2,7,9,3,1}));
//        System.out.println(massage(new int []{2,1,4,5,3,1,1,3}));
        System.out.println(maxCount(new int []{2,3}));
        System.out.println(maxCount(new int []{1,2,3}));
        System.out.println(maxCount(new int []{1,2,3,1}));
        System.out.println(maxCount(new int []{2,7,9,3,1}));
        System.out.println(maxCount(new int []{2,1,4,5,3,1,1,3}));
        System.out.println(maxCount2(new int []{183,219,57,193,94,233,202,154,65,240,97,234,100,249,186,66,90,238,168,128,177,235,50,81,185,165,217,207,88,80,112,78,135,62,228,247,211}));
        System.out.println(maxCount(new int []{183,219,57,193,94,233,202,154,65,240,97}));
        System.out.println(maxCount2(new int []{183,219,57,193,94,233,202,154,65,240,97}));
    }
}

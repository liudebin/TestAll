package qian.ling.yi.arithmetic;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;

/**
 * 归并排序
 *
 * 将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。
 * @author liuguobin
 * @date 2018/6/21
 */

public class MergeSort extends AbstractTest {
    private static int[] aux;
    void sort(int[] a) {
        if (a.length < 2) {
            return ;
        }


        mergeSort(a, 0, a.length - 1);
    }

    /**
     *  传入的数组会复用，所以要传入 起始点。
     *  当 起始点相同的时候，返回，即只有一个元素的时候返回
     *  只有两个元素的时候，也要进行比较。 也会进行递归，但是直接返回。
     * @param a
     * @param low
     * @param high
     */
    void mergeSort(int[] a, int low, int high) {
        if (high == low) {
            return;
        }
        int mid = low + (high -low)/2;
        mergeSort(a, low, mid );
        mergeSort(a, mid +1, high);
        merge(a, low, mid, high);
    }

//    void mergeSort(int[] a, int low, int high) {
//
//        int mid = low + (high -low)/2;
//
//        if (high - low > 2) {
//            mergeSort(a, low, mid );
//            mergeSort(a, mid +1, high);
//        }
//        merge(a, low, mid, high);
//    }

    void merge(int[] a, int low, int mid, int high) {
        int i=low;
        int j=mid+1;
        //做中间值用
        for(int k=low;k<=high;k++){
            aux[k]=a[k];
        }
        System.out.println(JSON.toJSONString(aux) + ", " + low+ ", " + mid+ ", " +high);

        //两列数组比较
        for(int k=low;k<=high;k++){
//            当 数组1 超了之后，就直接拿数组 2 的值即可
            if(i>mid){
                a[k] = aux[j++];
//                当数组超了之后，直接拿数组1的即可
            }else if(j>high){
                a[k] = aux[i++];
//                如果数组1 的 值小于 数组2 的值赋值。
            }else if(aux[i]<aux[j]){
                a[k]=aux[i++];
            }else{
                a[k]=aux[j++];
            }
        }
    }

    @Test
    public void test(){
//        int[] a = {3, 2, 5, 6, 1, 4};
//        int[] a = {3, 2, 4};
        int[] a = {3, 2};
//        int[] a = {3};
        aux = new int[a.length];
        sort(a);
        logger.info("{}", a);
    }
}
package qian.ling.yi.arithmetic;

/**
 * 冒泡排序
 *冒泡排序算法的原理如下：
 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 针对所有的元素重复以上的步骤，除了最后一个。
 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * @author liuguobin
 * @date 2018/6/21
 */

public class BubbleSort {
    private static  void bubbleSort(int[] arr) {
        for(int i =0;i<arr.length-1;i++) {
            for(int j=0;j<arr.length-i-1;j++) {//-1为了防止溢出
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];

                    arr[j]=arr[j+1];

                    arr[j+1]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {99,3,12,22,11,0};
        bubbleSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

}

package qian.ling.yi.arithmetic;

/**
 *
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。

 * @author liuguobin
 * @date 2018/6/14
 */

public class  QuickSort  {

    private void quickSort(int[] targetArr, int start, int end){
            int i = start,
                    j = end;
            int key = targetArr[start];

            while(i < j){
                /*按j--方向遍历目标数组，直到比key小的值为止*/
                while(j > i && targetArr[j] > key){
                    j --;
                }

                if(i < j){
                        /*targetArr[i]已经保存在key中，可将后面的数填入*/
                    targetArr[i] = targetArr[j];
                    i ++;
                }

               /*按i++方向遍历目标数组，直到比key大的值为止*/
                while(i < j && targetArr[i] < key) {
                        /*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
                    i++;
                }
                if(i < j){
                        /*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
                    targetArr[j]=targetArr[i];
                    j--;
                }
            }
            /*此时i==j*/
            targetArr[i]=key;

            /*递归调用，把key前面的完成排序*/
            if (start < i -1)
                this.quickSort(targetArr,start,i-1);


            /*递归调用，把key后面的完成排序*/
            if (j+1 < end)
                this.quickSort(targetArr,j+1,end);

    }


    public static void main(String[] args) {
        QuickSort t = new QuickSort();
//        int[] a = {3,12,1,4,2,8,90,55};
        int[] a = {1, 2, 3 ,4 ,8 ,12, 55 ,90 };
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();

        t.quickSort(a, 0, 7);

        for (int i : a) {
            System.out.print(i + " ");
        }


    }


    void quickSort1(int[] a, int start, int end) {
        int i = start, j = end;
        int key = a[i];
        while (i < j) {
            while (i < j && key > a[i]) {
                i ++;
            }
        }

    }
}
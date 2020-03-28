package qian.ling.yi.arithmetic;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoNum {

    public static  int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int tmp;
        int[] m = new int[2];
        for(int i = 0; i < length; i ++ ) {
            tmp = nums[i];
                for (int j = i+1; j < length ; j ++) {
                    if (tmp + nums[j] == target) {
                        m[0] = i;
                        m[1] = j;
                        return m;
                    }
                }
        }
        return m;
    }

    /**
     *  借助HashMap 这种思路也是新奇，完全想不到这算法
     *  只需要判断有无能组成的即可，拿结果判断是否包含，只要有能组成目标值的 A+B，则查找到B时，A必然已在Map中
     *  而且只有一次遍历，真的是思路清奇
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-2/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    @Test
    public void test(){
        System.out.println(Arrays.toString(twoSum(new int[]{1, 2, 3, 4}, 7)));
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{0,4,3,0}, 0)));

        System.out.println(Arrays.toString(twoSum2(new int[]{1, 2, 3, 4}, 7)));
        System.out.println(Arrays.toString(twoSum2(new int[]{2,7,11,15}, 9)));
        System.out.println(Arrays.toString(twoSum2(new int[]{0,4,3,0}, 0)));
    }
}

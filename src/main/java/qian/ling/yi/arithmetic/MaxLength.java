package qian.ling.yi.arithmetic;

import org.drools.core.rule.SlidingTimeWindow;
import org.junit.Test;

import java.util.*;

public class MaxLength {
    /**
     * 给一个字符串s，一个整数k，请实现算法找出s中含有k个不同字符的最长子串
     * e.g., s = "abcaaabb"，k = 1 => "aaa", k = 2 => "aaabb", k = 3 => "abcaaabb"
     *
     *
     * 要使用队列，先进先出的原则，如果有重复的则要删除之前已排队的，放入队尾，队尾就是离判断点最近的位置
     * @param s
     * @param k
     * @return
     */
    public static String maxLength(String s, int k) {

        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        String max = "";
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char a = chars[i];
            if (arrayDeque.contains(a)) {
                arrayDeque.remove(a);
                arrayDeque.addLast(a);
                tmp.append(a);
            } else {
                arrayDeque.addLast(a);
                if (arrayDeque.size() > k) {
                    if (max.length() < tmp.length()) {
                        max = tmp.toString();
                    }
                    Character character = arrayDeque.pollFirst();
                    tmp.delete(0, tmp.lastIndexOf(String.valueOf(character))+1);
                }
                tmp.append(a);
            }
        }

        if (max.length() <= tmp.length()) {
            max = tmp.toString();
        }
        return max;
    }

    public static String maxLength2(String s, int k) {

        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        String max = "";
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char a = chars[i];
            if (arrayDeque.contains(a)) {
                arrayDeque.remove(a);
                arrayDeque.addLast(a);
                tmp.append(a);
            } else {
                arrayDeque.addLast(a);
                if (arrayDeque.size() > k) {
                    if (max.length() < tmp.length()) {
                        max = tmp.toString();
                    }
                    Character character = arrayDeque.pollFirst();
                    tmp.delete(0, tmp.lastIndexOf(String.valueOf(character))+1);
                }
                tmp.append(a);
            }
        }

        if (max.length() <= tmp.length()) {
            max = tmp.toString();
        }
        return max;
    }

    @Test
    public void test(){
//        System.out.println(maxLength("ab", 1));
//        System.out.println(maxLength("ab", 2));
//        System.out.println(maxLength("aab", 2));
        System.out.println(maxLength("abcaaabb", 1));
        System.out.println(maxLength("abcaaabb", 2));
        System.out.println(maxLength("abcaaabb", 3));
        System.out.println(maxLength("abccccccaaabbdddd", 3));
        System.out.println(maxLength("abcccaaabb", 2));
        System.out.println(maxLength("abcccaaabb", 1));

    }
}

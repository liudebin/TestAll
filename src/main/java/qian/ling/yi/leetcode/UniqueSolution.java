package qian.ling.yi.leetcode;

import org.junit.Assert;
import org.junit.Test;
import sun.management.Sensor;

import java.util.*;

public class UniqueSolution {
    public static boolean isUnique(String astr) {
        if (null == astr|| (astr.length() == 1)) {
            return true;
        }
        char[] chars = astr.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            for (int i1 = i+1; i1 < length; i1++) {
                if (chars[i] == chars[i1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isUnique2(String astr) {
        if (null == astr || (astr.length() == 1)) {
            return true;
        }
        char[] chars = astr.toCharArray();
        int length = chars.length;
        HashSet<Object> hashSet = new HashSet<>(length);
        for (char aChar : chars) {
            if (!hashSet.add(aChar)) {
                return false;
            }
        }
        return true;
    }

     public static boolean CheckPermutation(String astr, String bstr) {
        if (null == astr || null == bstr) {
            return Objects.equals(astr, bstr);
        }
        char[] chars = astr.toCharArray();
        char[] chars2 = bstr.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars2);
        return Arrays.equals(chars, chars2);
    }

    /**
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     *
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     *
     * 回文串不一定是字典当中的单词。
     *
     *
     *
     * 示例1：
     *
     * 输入："tactcoa"
     * 输出：true（排列有"tacocat"、"atcocta"，等等）
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        if (null == s) {
            return false;
        }
        int length = s.length();
        if ( length == 0 ) {
            return false;
        }
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> stringCharacterHashMap = new HashMap<>(length);
        for (char aChar : chars) {
            stringCharacterHashMap.merge(aChar, 1, Integer::sum);
        }

        long count = stringCharacterHashMap.values().stream().filter(v -> 0 != v % 2).count();
        return count <=1;
    }

    public boolean canPermutePalindrome2(String s) {
        if (null == s) {
            return false;
        }
        int length = s.length();
        if (length == 1 || length == 0 ) {
            return false;
        }
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>(length);
        for (char aChar : chars) {
            if (!set.add(aChar)) {
                set.remove(aChar);
            }
        }
        return set.size() <=1;
    }

    @Test
    public void testCan() {
        new UniqueSolution().canPermutePalindrome("code");
    }

    public boolean isPalindrome(String s) {
        if (null == s) {
            return false;
        }
        int length = s.length();
        if (length == 0 ) {
            return false;
        }
        char[] chars = s.toCharArray();
        int seg = (length - 1)/2;
        for (int i = 0; i < seg; i++) {
            if (chars[i] != chars[length-1-i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     *
     *
     * 示例 1:
     *
     * 输入:
     * first = "pale"
     * second = "ple"
     * 输出: True
     *
     *
     *
     * 示例 2:
     *
     * 输入:
     * first = "pales"
     * second = "pal"
     * 输出: False
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/one-away-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param first
     * @param second
     * @return
     */

    public boolean oneEditAway(String first, String second) {
        if (null == first && null == second) {
            return false;
        }
        if (first == null) {
            return second.length() ==1;
        }
        if (second == null) {
            return first.length() ==1;
        }

        if(Math.abs(first.length() - second.length())> 1) {
            return false;
        }
        if (first.length() < second.length()) {
            String tmp = first;
            first = second;
            second = tmp;
        }

        char[] f = first.toCharArray();
        char[] s = second.toCharArray();
        int fl = f.length;
        int sl = s.length;

        int d = 0;
        int i = 0;
        int j = 0;
        for (; i < fl  && j < sl; i++,j++) {
            if (f[i] != s[j]) {
                d++;
                if ((i< fl -1)&&f[i + 1] == s[j]) {
                    i++;
                } else if ((j< sl -1)&&f[i] == s[j + 1]) {
                    j++;
                }
            }
        }
        if (i != fl || j !=sl ) {
            d++;
        }
        return d <=1;
    }

    @Test
    public void test() {
        UniqueSolution uniqueSolution = new UniqueSolution();
        Assert.assertTrue(uniqueSolution.oneEditAway("abc", "ab"));
        Assert.assertTrue(uniqueSolution.oneEditAway("ab", "abc"));
        Assert.assertTrue(uniqueSolution.oneEditAway("abc", "bc"));
        Assert.assertTrue(uniqueSolution.oneEditAway("bc", "abc"));
        Assert.assertTrue(uniqueSolution.oneEditAway("abc", "ac"));
        Assert.assertTrue(uniqueSolution.oneEditAway("ac", "abc"));
        Assert.assertTrue(uniqueSolution.oneEditAway("abc", "abc"));
        Assert.assertTrue(uniqueSolution.oneEditAway("", ""));
        Assert.assertFalse(uniqueSolution.oneEditAway("ab", "bc"));
        Assert.assertFalse(uniqueSolution.oneEditAway("bc", "ab"));
        Assert.assertFalse(uniqueSolution.oneEditAway("abc", "a"));
        Assert.assertFalse(uniqueSolution.oneEditAway("a", "abc"));
        Assert.assertFalse(uniqueSolution.oneEditAway("abcdefge", "abcdxfgx"));
    }


    /*
    字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

示例1:

 输入："aabcccccaaa"
 输出："a2b1c5a3"

示例2:

 输入："abbccd"
 输出："abbccd"
 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。

提示：

    字符串长度在[0, 50000]范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/compress-string-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 双100
     * @param S
     * @return
     */
    public static String compressString(String S) {
        if (null == S|| S.length()<3) {
            return S;
        }
        char[] chars = S.toCharArray();
//        Map<Character, Integer> map = new TreeMap<>();
//        for (char aChar : chars) {
//            map.put(aChar, 1 + map.getOrDefault(aChar, 0));
//        }
//        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
//        if(entries.size() >= (chars.length+1)/2) {
//            return S;
//        }
//        StringBuilder s = new StringBuilder();
//        entries.forEach(n -> s.append(n.getKey()).append(n.getValue()));
//        return s.toString();
        char tmp = 0;
        int i = 0;
        StringBuilder strBl = new StringBuilder();
        for (char aChar : chars) {
            if (0 == tmp) {
                tmp = aChar;
                i =1;
            } else {
                if (tmp==aChar) {
                    i++;
                } else {
                    strBl.append(tmp).append(i);
                    tmp = aChar;
                    i =1;
                }
            }
        }
        if (tmp != 0) {
            strBl.append(tmp).append(i);
        }
        String s = strBl.toString();
        if (s.length() < S.length()) {
            return s;
        }
        return S;
    }

    @Test
    public void testCompressString() {
        Assert.assertEquals("abc", compressString("abc"));
        Assert.assertEquals("aabbcc", compressString("aabbcc"));
        Assert.assertEquals("a2b2c3", compressString("aabbccc"));
    }

}
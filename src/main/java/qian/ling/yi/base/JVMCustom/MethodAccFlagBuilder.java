package qian.ling.yi.base.JVMCustom;

import org.apache.commons.lang.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * fields
 *
 * @author liuguobin
 * @date 2018/5/1
 */

public class MethodAccFlagBuilder {
    static Map<Character, String> flag1 = new HashMap<>(8);
    static Map<Character, String> flag2 = new HashMap<>(8);
    static Map<Character, String> flag3 = new HashMap<>(4);
    static Map<Character, String> flag4 = new HashMap<>(4);

    static  {
        flag1.put('1', "public");
        flag1.put('2', "private");
        flag1.put('4', "protected");
        flag1.put('8', "static");

        flag2.put('1', "final");
        flag2.put('2', "synchronized");
        flag2.put('4', "bridge");
        flag2.put('8', "varargs");

        flag3.put('1', "native");
        flag3.put('4', "abstract");
        flag3.put('8', "stricfp");

        flag4.put('1', "synthetic");
    }

    static  String[] getMethodAccFlags(String str) {
        final char[] chars = str.toCharArray();
        String[] fieldAccFlag = new String[0];
        if ('0' != chars[0]) {
            fieldAccFlag = (String[]) ArrayUtils.add(fieldAccFlag, flag1.get(chars[0]));
        }
        if ('0' != chars[1]) {
            fieldAccFlag = (String[]) ArrayUtils.add(fieldAccFlag, flag2.get(chars[1]));
        }
        if ('0' != chars[2]) {
            fieldAccFlag = (String[]) ArrayUtils.add(fieldAccFlag, flag3.get(chars[2]));
        }
        if ('0' != chars[3]) {
            fieldAccFlag = (String[]) ArrayUtils.add(fieldAccFlag, flag4.get(chars[3]));
        }
        return fieldAccFlag;
    }


}

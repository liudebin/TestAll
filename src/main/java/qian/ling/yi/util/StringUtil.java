package qian.ling.yi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuguobin on 2016/9/22.
 */
public class StringUtil {
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
    /**
     * 是否为null
     * @param str
     * @return
     */
    public static boolean isNull(String str){
        return null == str ? true : false;
    }

    /**
     * 是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (isNull(str) || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串按长度分块追加相应字符串
     * @param str
     * @param gapLength
     * @param end
     * @return
     */
    public static String chunkSplit(String str, int gapLength, String end) {
        if (isEmpty(str)) {
            return null;
        }
        char[] strChar = str.toCharArray();
        StringBuilder chunkString = new StringBuilder();
        int strLength = strChar.length;
        int tmp = strLength / gapLength;
        for (int i = 0;i < tmp;i ++) {
            chunkString.append(strChar, i * gapLength, gapLength)
                    .append(end);
        }
        int mod = strLength % gapLength;
        if (mod != 0) {
            chunkString.append(strChar, strLength - mod, mod)
                    .append(end);
        }
        return chunkString.toString();
    }

    public static String ascii2native(String ascii) {
        int n = ascii.length() / 6;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0, j = 2; i < n; i++, j += 6) {
            String code = ascii.substring(j, j + 4);
            char ch = (char) Integer.parseInt(code, 16);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        logger.info("{}", isNull(""));
        logger.info(ascii2native("\\u4ea4\\u6613\\u6210\\u529f"));
        System.out.println("\\u4ea4\\u6613\\u6210\\u529f");
    }

}

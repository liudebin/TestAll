package qian.ling.yi.util;

/**
 * ByteUtil
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ByteUtil {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String splitBytesToHex (byte[] bytes, int begin, int length) {
        return bytesToHexFun(ArrayUtil.interceptByte(bytes, begin, length));
    }

    public static int splitBytesToInt (byte[] bytes, int begin, int length) {
        return bytes2Int(ArrayUtil.interceptByte(bytes, begin, length));
    }

    public static double splitBytesToDouble (byte[] bytes, int begin, int length) {
        return bytes2Double(ArrayUtil.interceptByte(bytes, begin, length));
    }

    public static float splitBytesToFloat (byte[] bytes, int begin, int length) {
        return bytes2float(ArrayUtil.interceptByte(bytes, begin, length));
    }

    public static String splitBytesToString (byte[] bytes, int begin, int length) {
        return bytesToString(ArrayUtil.interceptByte(bytes, begin, length));
    }

    public static String bytesToHexFun(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 利用位运算进行转换
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    public static String bytesToString(byte[] bytes) {
        return new String(bytes);
    }

//    public static int byte2IntTmp(byte[] b) {
//        return Integer.parseInt(bytesToHexFun(b), 16);
//    }

    public static int bytes2Int(byte[] b) {
        int v = b[b.length -1] & 0xFF;

        for (int i = b.length - 2; i >= 0; i --) {
            v = v | (b[i]& 0xFF) << (8 * (b.length - i - 1)); // (8 * 位数，)
        }
        return v;
    }

    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < arr.length; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    public static float bytes2float(byte[] b) {
        int l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        return Float.intBitsToFloat(l);
    }

}

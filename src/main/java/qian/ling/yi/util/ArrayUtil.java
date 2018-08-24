package qian.ling.yi.util;

/**
 * array
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ArrayUtil {

//    /**
//     * 截取一定的byte
//     * @param begin
//     * @param length
//     * @return
//     */
//    public static byte[] splitByte(byte[] bytes, Integer begin, Integer length) {
//        if (null == begin) {
//            begin = 0;
//        }
//        byte[] bs = new byte[length];
//        System.arraycopy(bytes, begin, bs, 0, length);
//        return bs;
//    }

    /**
     * 从bytes 中 的 begin 坐标开始，截取 length 长度的数组
     * @param bytes
     * @param begin
     * @param length
     * @return
     */
    public static byte[] interceptByte(byte[] bytes, int begin, int length) {
        byte[] tmpData = new byte[length];
//        数组源，拷贝的起始下标，目标数组，填写的起始下标，要拷贝的长度
        System.arraycopy(bytes, begin, tmpData, 0, length);
        return tmpData;
    }



}

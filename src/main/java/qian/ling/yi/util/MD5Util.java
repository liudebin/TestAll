package qian.ling.yi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenzhang on 2016/8/30.
 */
public class MD5Util {
    private static final String HEX_CHARS = "0123456789abcdef";

    private MD5Util() {
    }

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return toHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String data) {
        return toHexString(md5(data));
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param b -
     *          the input byte array
     * @return hex string representation of b.
     */

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     *
     * @param s -
     *          string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }

    /** @param source 需要加密的字符串
     * @return
     */
    public static String getHash2(String source) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(source.getBytes());
            for (byte b : md5.digest()) {
                sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.md5Hex("aaaaa&bbbb"));
        System.out.println(MD5Util.getHash2("{\"status\": \"1\", \"sysdtm\": \"2016-10-17 23:55:45\", \"goods_name\": \"\", \"txcurrcd\": \"CNY\", \"cancel\": \"0\", \"pay_type\": \"800201\", \"txdtm\": \"2016-10-17 23:55:45\", \"txamt\": \"37869\", \"out_trade_no\": \"NNPR_3670691\", \"syssn\": \"20161017487008\", \"respcd\": \"0000\", \"notify_type\": \"payment\"}BD6B5A03769940B2BC40D542DFB5CCC3"));
        System.out.println(MD5Util.md5Hex("{\"status\": \"1\", \"sysdtm\": \"2016-10-17 23:55:45\", \"goods_name\": \"\", \"txcurrcd\": \"CNY\", \"cancel\": \"0\", \"pay_type\": \"800201\", \"txdtm\": \"2016-10-17 23:55:45\", \"txamt\": \"37869\", \"out_trade_no\": \"NNPR_3670691\", \"syssn\": \"20161017487008\", \"respcd\": \"0000\", \"notify_type\": \"payment\"}BD6B5A03769940B2BC40D542DFB5CCC3"));
    }
}

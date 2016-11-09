package qian.ling.yi.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Base64;

/**
 * 【Java使用3DES加密解密的流程】
 ①传入共同约定的密钥（keyBytes）以及算法（Algorithm），来构建SecretKey密钥对象
 SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
 ②根据算法实例化Cipher对象。它负责加密/解密
 Cipher c1 = Cipher.getInstance(Algorithm);
 ③传入加密/解密模式以及SecretKey密钥对象，实例化Cipher对象
 c1.init(Cipher.ENCRYPT_MODE, deskey);
 ④传入字节数组，调用Cipher.doFinal()方法，实现加密/解密，并返回一个byte字节数组
 c1.doFinal(src);
 * Created by liuguobin on 2016/9/22.
 */
public class Des3Util {
    public static final String default_des_pri_key = "2012PinganVitality075522628888ForShenZhenBelter075561869839";
    /**
     * 3DES加密
     * @param key
     * @param data
     * @return
     */
    public static String desEncrypt(String key, String data) {
        try {
            //DESede 即 3DES
            String keyAlgorithm = "DESede";//定义加密算法,可用 DES,DESede,Blowfish
            String cipherAlgorithm = "DESede/ECB/PKCS5Padding";
            //生成密钥
            SecretKey desKey = new SecretKeySpec(key.getBytes(), keyAlgorithm);
            //加密
            Cipher c1 = Cipher.getInstance(cipherAlgorithm);//实例化负责加密/解密的Cipher工具类
            c1.init(Cipher.ENCRYPT_MODE, desKey);//初始化为加密模式
            byte[] desData = c1.doFinal(data.getBytes());//在单一方面的加密或解密
            return new String(desData);
        } catch (java.security.NoSuchAlgorithmException | javax.crypto.NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     * @param key
     * @param data
     * @return
     */
    public static String desDecrypt(String key, String data) {
        try {
            String keyAlgorithm = "DESede";//定义加密算法,可用 DES,DESede,Blowfish
            String cipherAlgorithm = "DESede/ECB/PKCS5Padding";
            //生成密钥
            SecretKey desKey = new SecretKeySpec(key.getBytes(), keyAlgorithm);
            //加密
            Cipher c1 = Cipher.getInstance(cipherAlgorithm);//实例化负责加密/解密的Cipher工具类
            c1.init(Cipher.DECRYPT_MODE, desKey);//初始化为加密模式
            byte[] dataBytes = Base64.getDecoder().decode(data.getBytes());
            byte[] desData = c1.doFinal(dataBytes);//在单一方面的加密或解密
            return new String(desData);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }catch(javax.crypto.NoSuchPaddingException e2){
            e2.printStackTrace();
        }catch(java.lang.Exception e3){
            e3.printStackTrace();
        }
        return null;
    }
}

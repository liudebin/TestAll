package qian.ling.yi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.util.StringUtil;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 公钥加密技术12号标准
 * （Public Key Cryptography Standards #12，PKCS#12）
 * 为存储和传输用户或服务器私钥、公钥和证书指定了一个可移植的格式。
 * 它是一种二进制格式，这些文件也称为PFX文件。开发人员通常需要将PFX文件转换为某些不同的格式，如PEM或JKS，
 * 以便可以为使用SSL通信的独立Java客户端或WebLogic Server使用 是一种Microsoft协议，
 * 使用户可以将机密信息从一个环境或平台传输到另一个环境或平台。使用该协议，
 * 用户就可以安全地将个人信息从一个计算机系统导出到另一个系统中。
 * Created by liuguobin on 2016/9/22.
 */
public class PFXUtil {
    Logger logger = LoggerFactory.getLogger(PFXUtil.class);
    /**
     * 由私钥文件获取公钥私钥
     * @param pfxPath
     * @param password
     * @return
     */
    public Map<String, Key> getKeyFromPfx(String pfxPath, String password){
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(pfxPath);
            char[] nPassword = null;
            if (StringUtil.isEmpty(password)){
                nPassword = null;
            } else {
                nPassword = password.toCharArray();
            }

            fis.close();
            logger.info("keystore type=" + ks.getType());

            Enumeration enums = ks.aliases();
            String keyAlias = null;
            if (enums.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String)enums.nextElement();
                logger.info("alias=[" + keyAlias + "]");
            }
            logger.info("is key entry=" + ks.isKeyEntry(keyAlias));
            PrivateKey priKey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubKey = cert.getPublicKey();
            logger.info("cert class = " + cert.getClass().getName());
            logger.info("cert = " + cert);
            logger.info("public key = " + pubKey);
            logger.info("private key = " + priKey);
            Map<String, Key> result = new HashMap<>();
            result.put("pubKey", pubKey);
            result.put("priKey", priKey);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

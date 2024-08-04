package encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/4 21:12
 */
//@Slf4j
public class RsaUtils {
    private static final Logger log = LoggerFactory.getLogger(RsaUtils.class);

    class AdminConstant {
        public static final String PUBLIC_KEY = "PUBLIC_KEY";
        public static final String PRIVATE_KEY = "PRIVATE_KEY";
    }
    /**
     * @Description:RAS非对称加密，随机生成密钥对
     * @Author: ls
     * @Date: 2024/8/4 21:03
     *  @return 密钥对
     */
    public static Map<String, String> genKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        assert keyPairGen != null;
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        // 将公钥和私钥保存到Map
        Map<String, String> res = new HashMap<String, String>(2) {{
            put(AdminConstant.PUBLIC_KEY, new String(Base64.encodeBase64(publicKey.getEncoded())));
            put(AdminConstant.PRIVATE_KEY, new String(Base64.encodeBase64((privateKey.getEncoded()))));
        }};
        return res;
    }

    /**
     * RAS非对称加密: 公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String str, String publicKey) {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey;
        String outStr = null;

        try {
            pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException |
                 NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //RSA加密
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     */
    public static String decrypt(String str, String privateKey) {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey;
        //RSA解密
        Cipher cipher;
        String outStr = null;

        try {
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return outStr;
    }

    @Test
    public void testRasDemo() {
        JSONObject object = new JSONObject();
        object.put("name", "我是子龙");
        object.put("id", "123456");
        object.put("age", 20);
        // 随机获取秘钥对
        Map<String, String> keyPair = RsaUtils.genKeyPair();
        // 秘钥
        String privateKey = keyPair.get(AdminConstant.PRIVATE_KEY);
        // 公钥
        String publicKey = keyPair.get(AdminConstant.PUBLIC_KEY);
        // 加密后的数据
        String encryptData = null;
        try {
            // 使用公钥加密
            encryptData = RsaUtils.encrypt(JSON.toJSONString(object), publicKey);
        } catch (Exception e) {
            log.error("加密失败", e);
        }
        // 解密后的数据
        String decryptData = null;
        try {
            // 使用私钥解密
            decryptData = RsaUtils.decrypt(encryptData, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("原始明文数据={}", object.toJSONString());
        log.info("公钥={}", publicKey);
        log.info("私钥={}", privateKey);
        log.info("加密后的数据={}", encryptData);
        log.info("解密后的数据={}", decryptData);

        // 记录debug级别的信息
        log.debug("This is debug message.");
        // 记录info级别的信息
        log.info("This is info message.");
        // 记录error级别的信息
        log.error("This is error message.");
    }
}

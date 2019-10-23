package com.github.wenzhencn.cmsseed.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.midi.Soundbank;

/**
 * AES 加密工具类
 */
public class AesUtils {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String KEY_ALGORITHM = "AES";
    /*** 默认的加密算法 */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0, 25, 26, 0, 0, 0, 0, 0, 0 };

    /**
     * 加密数据
     * @param strToEncrypt
     * @param secret
     * @param salt
     * @return
     */
    public static String encrypt(String strToEncrypt, String secret, String salt) throws Exception {
        byte[] data = doCrypt(Cipher.ENCRYPT_MODE, strToEncrypt.getBytes(CHARSET),
                secret.toCharArray(), salt.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * 解密数据
     * @param strToDecrypt
     * @param secret
     * @param salt
     * @return
     */
    public static String decrypt(String strToDecrypt, String secret, String salt) throws Exception {
        byte[] data = doCrypt(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(strToDecrypt),
                secret.toCharArray(), salt.getBytes(CHARSET));
        return new String(data);
    }

    /**
     * 执行加密或者解密
     * @param cryptMode
     * @param content
     * @param secret
     * @param salt
     * @return
     */
    private static byte[] doCrypt(int cryptMode, byte[] content, char[] secret, byte[] salt) throws Exception {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret, salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), KEY_ALGORITHM);

            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(cryptMode, secretKey, ivSpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error when cryption", e);
        }
    }

    /**
     * 随机生成秘钥
     */
    public static String generateKey() {
        return doGenerateKey(256, null);
    }
    /**
     * 使用指定的字符串生成秘钥
     */
    public static String generateKey(String password) {
        return doGenerateKey(256, password);
    }

    private static String doGenerateKey(int length, String seed) {
        try {
            if(length != 128 && length != 192 && length != 256) {
                throw new Exception("Wrong aes key length");
            }
            KeyGenerator kg = kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            if(seed != null) {
                kg.init(length, new SecureRandom(seed.getBytes(CHARSET)));
            } else {
                kg.init(length);
            }
            SecretKey sk = kg.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error when generate key", e);
        }
    }

    public static void main(String[] args) throws Exception {
        String secret = "VoR3ch4Lcbjs1lvBTU87logt4H0fzvR6aFKC5gigv9Y=";
        String salt = "X6a+DGPpNXZfOxjP2GibfQ==";
        String originalString = "test";

        // 加密
        String encryptedString = AesUtils.encrypt(originalString, secret, salt) ;
        // 解密
        String decryptedString = AesUtils.decrypt(encryptedString, secret, salt) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

        ExecutorService exe = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(100);
        for(int i = 0; i < 100; i++) {
            final int index = i;
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程[" + index + "]开始");
                        String original = new RandomStringUtils().random(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-=_+:\";'<>?,./`~") + index;
                        String encryption = encrypt(original, secret, salt);
                        String decryption = decrypt(encryption, secret, salt);
                        System.out.println("线程[" + index + "] original = " + original + " encryption = " + encryption + " decrypt = " + decryption);
                        System.out.println("线程[" + index + "]结束");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
        System.out.println("等待完成");
        latch.await();
        System.out.println("完成");
        System.exit(0);
    }

}

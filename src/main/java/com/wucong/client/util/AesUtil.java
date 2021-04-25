package com.wucong.client.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/**
 * @Author: kerwin
 * @Date: 2020/10/27 14:06
 * @Description:
 */

public class AesUtil {
    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    public static String decryptData(String reqInfo,String appKey) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        byte[] decodeData = decodeData(reqInfo);
        SecretKeySpec key = new SecretKeySpec(getMD5(appKey).toLowerCase().getBytes(), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING,"BC");
        cipher.init(Cipher.DECRYPT_MODE,key);
        return new String(cipher.doFinal(decodeData),"utf-8");
    }

    private static byte[] decodeData(String encodeData) {
        return Base64.getDecoder().decode(encodeData);
    }

    /**
     * 获取解密字符串
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String result = MD5(str, md);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * MD5解密
     * @param strSrc
     * @param md
     * @return
     */
    public static String MD5(String strSrc, MessageDigest md) {
        byte[] bt = strSrc.getBytes();
        md.update(bt);
        String strDes = bytes2Hex(md.digest());
        return strDes;
    }

    /**
     * 字节转16进制
     * @param bts
     * @return
     */
    public static String bytes2Hex(byte[] bts) {
        StringBuffer des = new StringBuffer();
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        // 这里的1代表正数
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * 生成AES KEY
     * @param clientNumber
     * @param serverNumber
     * @return
     */
    public static String genAesKey(String clientNumber,String serverNumber){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientNumber).append(serverNumber);
        String aesKey = ShaUtil.sha1(stringBuilder.toString());
        //取收尾各8位
        return aesKey.substring(0,8)+aesKey.substring(aesKey.length()-8);

    }


    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
}


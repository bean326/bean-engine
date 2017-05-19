package com.bean.util;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
  
/** 
 * 编码工具类 
 * 实现aes加密、解密 
 */  
public class EncryptUtils {  
      
    /** 
     * 密钥 只能为16位
     */  
    private static final String KEY = "douqu2015126.com";
      
    /** 
     * 算法 
     */  
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";  
  
    public static void main(String[] args) throws Exception {  
//        String content = "{\"appId\":\"wxf73c57e00e07bd74\",\"nonceStr\":\"n15d2o0ea2rqyeua510\",\"timeStamp\":\"1488365153\",\"package\":\"prepay_id=wx201703011845529d316b16570835267436\",\"signType\":\"MD5\",\"paySign\":\"2123150C2ADC6DD5F8F8394B7B3B2C21\"}\n";
////        System.out.println("加密前：" + content);
//  
////        System.out.println("加密密钥和解密密钥：" + KEY);
//
//        String encrypt = aesEncrypt(content, KEY);
////        System.out.println("加密后：" + encrypt);
//
//        String a = aesEncrypt(content);
////        System.out.println("a:"+a);
//
//        String b = encrypt(content);
////        System.out.println("b:"+b);
//  
//        String decrypt = aesDecrypt(encrypt, KEY);  
////        System.out.println("解密后：" + decrypt);
    }

    public static String encrypt(String content) throws Exception {

        return aesEncrypt(content, KEY);
    }

    public static String decrypt(String content) throws Exception {

        return aesDecrypt(content, KEY);
    }



      
    /** 
     * aes解密 
     * @param encrypt   内容 
     * @return 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encrypt) throws Exception {
        return aesDecrypt(encrypt, KEY);  
    }  
      
    /** 
     * aes加密 
     * @param content 
     * @return 
     * @throws Exception 
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, KEY);  
    }  
  
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */
    public static String binary(byte[] bytes, int radix)
    {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
  
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */
    private static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);  
    }  
  
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */
    private static byte[] base64Decode(String base64Code) throws Exception{
        return Utils.isNull(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);  
    }  
  
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
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
    private static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
  
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
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
    private static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return Utils.isNull(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    }  
  
}  
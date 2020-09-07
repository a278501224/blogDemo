package cn.cherry.blog.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 密码加解密工具类
 * @author: Cherry
 * @create: 2020-09-07 11:18
 **/
@Slf4j
public class SecurityUtil {
    private final static byte[] IV = "tanganti20200717".getBytes();

    /**
    * @Description: 加密
    * @Param: 
    * @return: 
    */
    public static String encrypt(String str,String key){
        if(str == null || key == null){
            throw new IllegalArgumentException();
        }
        byte[] encrypted = new byte[0];
        try {
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw,"AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,new IvParameterSpec(IV));
            encrypted = cipher.doFinal(str.getBytes());
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException:{}",e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException:{}",e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException:{}",e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException:{}",e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            log.error("InvalidAlgorithmParameterException:{}",e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException:{}",e.getMessage());
        } catch (BadPaddingException e) {
            log.error("BadPaddingException:{}",e.getMessage());
        }
        return HexUtil.byte2hex(encrypted);
    }


    /**
    * @Description: 解密
    * @Param: 
    * @return: 
    */
    public static String decrypt(String str ,String key){
        if(str == null || key == null){
            throw new IllegalArgumentException();
        }
        byte[] raw = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw,"AES");
        byte[] original = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,new IvParameterSpec(IV));
            original = cipher.doFinal(HexUtil.hex2byte(str));
            return new String(original);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException:{}",e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException:{}",e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException:{}",e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            log.error("InvalidAlgorithmParameterException:{}",e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException:{}",e.getMessage());
        } catch (BadPaddingException e) {
            log.error("BadPaddingException:{}",e.getMessage());
        }
        return null;
    }


}

package com.base.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@UtilityClass
public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String secret = "1234567890123456";

    public static String encrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    public static String decrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedValue = Base64.getDecoder().decode(value);
            byte[] decryptedValue = cipher.doFinal(decodedValue);
            return new String(decryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}

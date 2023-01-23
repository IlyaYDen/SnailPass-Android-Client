package com.example.snailpasswordmanager.domain.crypto.AES;


import android.os.Build;

import androidx.annotation.RequiresApi;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;


public class AESUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] encrypt(byte[] plainTextData, byte[] secretKey,byte[] iv2) throws Exception {
        try {
            String iv = new String(iv2).substring(0, 16);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            byte[] dataBytes = plainTextData;
            int plaintextLength = dataBytes.length;
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new String(Base64.getEncoder().encode(encrypted)).getBytes();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] decrypt(byte[] cipherTextData, byte[] secretKey,byte[] iv2) throws Exception {
        try {
            String iv = new String(iv2).substring(0, 16);

            byte[] encrypted = Base64.getDecoder().decode(cipherTextData);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
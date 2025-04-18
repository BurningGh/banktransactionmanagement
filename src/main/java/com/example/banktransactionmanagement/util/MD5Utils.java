package com.example.banktransactionmanagement.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * JsonUtils
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class MD5Utils {

    public static String md5Hex(String input, String charset) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(charset));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Error generating MD5 hash", e);
        }
    }

}

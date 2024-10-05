package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static void main(String[] args) {
        String password = "yourPasswordHere";
        String salt = "yourSaltHere";
        try {
            String hashedPassword = hashPassword(password, salt);
            System.out.println("Hashed Password: " + hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String hashedSalt = MD5(salt);
        String hashedPassword = MD5(password);
        String finalHash = MD5(hashedSalt + hashedPassword);
        return finalHash;
    }

    public static String MD5(String md5) {
        StringBuffer sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            System.out.println("MD5 Hash of '" + md5 + "' is: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("I cannot find the MD5 algorithm in the Java security package.");
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("UTF-8 Encoding not supported!");
        }
        return sb.toString();
    }
}

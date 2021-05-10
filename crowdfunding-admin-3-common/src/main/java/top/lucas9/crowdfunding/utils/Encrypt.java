package top.lucas9.crowdfunding.utils;

import top.lucas9.crowdfunding.constant.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encrypt {
    static final String algorithm = "MD5";
    static final char[] hexadecimalChars = {'0', '1', '2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    /**
     * MD5加密工具方法
     * @param plaintext 明文
     * @return 密文
     */
    public static String md5(String plaintext) {
        if (!CheckEffective.stringEffective(plaintext)) {
            throw new RuntimeException(Constant.MESSAGE_CODE_INVALID);
        }
        StringBuilder ciphertext = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] plaintextBytes = plaintext.getBytes();
            byte[] ciphertextBytes = messageDigest.digest(plaintextBytes);
            int len = ciphertextBytes.length;
            int lowValue, highValue, tempByte;
            for (int i = 0; i < len; i++) {
                tempByte = ciphertextBytes[i];
                lowValue = tempByte & 15;
                highValue = (tempByte >> 4) & 15;
                ciphertext.append(hexadecimalChars[lowValue]).append(hexadecimalChars[highValue]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext.toString();
    }
}

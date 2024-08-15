package com.sparta.schedulemanager.secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements SecureAlgorithm{

    @Override
    public String encrypt(String plainText) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(plainText.getBytes());

        return bytesToHex(digest.digest());
    }

    // 바이트를 hexString으로 변환해주는 함수
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            // 만약 0 ~ 15 : 0 ~ f사이의 수일 경우 앞에 0을 추가해준다.
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

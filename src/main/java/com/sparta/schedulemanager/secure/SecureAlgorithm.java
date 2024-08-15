package com.sparta.schedulemanager.secure;

import java.security.NoSuchAlgorithmException;

public interface SecureAlgorithm {
    String encrypt(String plainText) throws NoSuchAlgorithmException;
}

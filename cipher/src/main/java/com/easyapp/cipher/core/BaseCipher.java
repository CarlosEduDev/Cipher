package com.easyapp.cipher.core;

import com.easyapp.cipher.util.Hash;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class BaseCipher {

    protected static final String DEFAULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    protected static final String DEFAULT_ALGORITHM = "AES";

    protected final String transformation, algorithm;
    protected final byte[] keyBytes;

    protected BaseCipher(byte[] keyBytes) {
        this(DEFAULT_TRANSFORMATION, DEFAULT_ALGORITHM, keyBytes);
    }

    protected BaseCipher(String transformation, String algorithm, byte[] keyBytes) {
        if (transformation == null) {
            throw new IllegalArgumentException("transformation cannot be null.");
        }
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm cannot be null.");
        }
        if (keyBytes == null) {
            throw new IllegalArgumentException("keyBytes cannot be null.");
        }
        this.transformation = transformation;
        this.algorithm = algorithm;
        this.keyBytes = keyBytes;
    }

    protected final Cipher newCipher(int mode) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(
                mode,
                new SecretKeySpec(
                        Hash.md5(keyBytes),
                        algorithm
                ),
                new IvParameterSpec(Hash.md5(keyBytes))
        );

        return cipher;
    }

    protected final Cipher newCipherDecryptMode() throws InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return newCipher(Cipher.DECRYPT_MODE);
    }

    protected final Cipher newCipherEncryptMode() throws InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return newCipher(Cipher.ENCRYPT_MODE);
    }
}

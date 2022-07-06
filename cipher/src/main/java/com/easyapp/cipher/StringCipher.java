package com.easyapp.cipher;

import androidx.annotation.NonNull;

import com.easyapp.cipher.core.BaseCipher;
import com.easyapp.cipher.core.CipherResult;

@SuppressWarnings("unused")
public final class StringCipher extends BaseCipher {

    protected StringCipher(@NonNull String key) {
        super(key.getBytes());
    }

    protected StringCipher(
            @NonNull String transformation,
            @NonNull String algorithm,
            @NonNull String key
    ) {
        super(transformation, algorithm, key.getBytes());
    }

    @NonNull
    public CipherResult<byte[]> encrypt(@NonNull String str) {
        try {
            return new CipherResult<>(
                    newCipherEncryptMode().doFinal(str.getBytes())
            );
        } catch (Throwable e) {
            return new CipherResult<>(e);
        }
    }

    @NonNull
    public static CipherResult<byte[]> encrypt(
            @NonNull String key,
            @NonNull String str
    ) {
        return new StringCipher(key).encrypt(str);
    }

    @NonNull
    public CipherResult<String> decrypt(@NonNull byte[] encrypted) {
        try {
            return new CipherResult<>(
                    new String(newCipherDecryptMode().doFinal(encrypted))
            );
        } catch (Throwable e) {
            return new CipherResult<>(e);
        }
    }

    @NonNull
    public static CipherResult<String> decrypt(
            @NonNull String key,
            @NonNull byte[] encrypted
    ) {
        return new StringCipher(key).decrypt(encrypted);
    }
}
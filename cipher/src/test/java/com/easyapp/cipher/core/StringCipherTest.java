package com.easyapp.cipher.core;

import com.easyapp.cipher.StringCipher;

import junit.framework.TestCase;

public class StringCipherTest extends TestCase {

    public void testSuccess() {

        String str = "testing";
        String key = "key_test";

        CipherResult<byte[]> encrypted = StringCipher.encrypt(key, str);

        assertTrue(encrypted.isSuccess());

        CipherResult<String> decrypted = StringCipher.decrypt(key, encrypted.getData());

        assertTrue(decrypted.isSuccess());

        assertEquals(str, decrypted.getData());
    }

    public void testError() {
        String str = "testing";

        CipherResult<byte[]> encrypted = StringCipher.encrypt("key_test1", str);

        assertTrue(encrypted.isSuccess());

        CipherResult<String> decrypted = StringCipher.decrypt("key_test2", encrypted.getData());

        assertTrue(decrypted.isError());
    }
}
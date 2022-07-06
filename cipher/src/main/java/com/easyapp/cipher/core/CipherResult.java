package com.easyapp.cipher.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CipherResult<T> {

    @Nullable
    private final Throwable error;

    @Nullable
    private final T data;

    public CipherResult(@NonNull Throwable error) {
        this.data = null;
        this.error = error;
    }

    public CipherResult(@NonNull T data) {
        this.error = null;
        this.data = data;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public boolean isError() {
        return error != null;
    }

    public boolean isSuccess() {
        return data != null;
    }
}

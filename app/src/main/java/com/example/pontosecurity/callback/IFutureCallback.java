package com.example.pontosecurity.callback;

public interface IFutureCallback {
    void onSuccess();

    void onError(Exception exception);
}

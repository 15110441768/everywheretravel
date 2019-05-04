package com.example.lenovo.everywheretravel.base;

public interface CallBack<K> {
    void onSuccess(K k);
    void onFail(String string);
}

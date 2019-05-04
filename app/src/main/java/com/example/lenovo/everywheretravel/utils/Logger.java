package com.example.lenovo.everywheretravel.utils;

import android.util.Log;

import com.example.lenovo.everywheretravel.base.Constants;

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}

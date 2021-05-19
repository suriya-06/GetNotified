package com.example.getnotified.Util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {
    public static void hideKeyBoard(Context context, View view){
        InputMethodManager inputMethodManager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}

package com.ferhatiltas.sharedpreference_basit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    static final String PREF_NAME = "Dosya";
    static final String PREF_KEY = "Key";

    public void save(Context context, String text) {//String değer kayıt etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_KEY, text);
        editor.commit();
    }

    public String getValue(Context context) {//KAyıt ettiği string değeri elde etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String text = settings.getString(PREF_KEY, null);
        return text;
    }

    public void clear(Context context) {//Kayıt ettiği değerleri silmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void remove(Context context) {// O yapıyı kaldırmak için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(PREF_KEY);
        editor.commit();
    }
}

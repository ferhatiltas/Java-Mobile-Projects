package com.ferhatiltas.sharedpreference_benihatirla;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    static final String PREF_NAME = "Login";

    public void save(Context context, String key,String value) {//String değer kayıt etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void saveBoolean(Context context, String key,Boolean value) {//String değer kayıt etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getValue(Context context, String key) {//KAyıt ettiği string değeri elde etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String text = settings.getString(key, null);
        return text;
    }
    public Boolean getValueBoolean(Context context, String key) {//KAyıt ettiği string değeri elde etmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Boolean text = settings.getBoolean(key, false);
        return text;
    }

    public void clear(Context context) {//Kayıt ettiği değerleri silmek için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void remove(Context context, String key) {// O yapıyı kaldırmak için
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }
}

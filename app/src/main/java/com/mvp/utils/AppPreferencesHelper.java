package com.mvp.utils;

import android.content.Context;
import com.google.gson.Gson;

public class AppPreferencesHelper implements PreferencesHelper {

    private final SecuredPreference securedPreference;
    private final Gson gson = new Gson();

    public AppPreferencesHelper(Context context, String prefFileName) {
        securedPreference = new SecuredPreference(context, prefFileName);
    }

}
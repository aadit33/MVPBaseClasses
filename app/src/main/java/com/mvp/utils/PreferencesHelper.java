package com.mvp.utils;

import com.mvp.model.User;

public interface PreferencesHelper {
    String getAccessToken();

    void setToken(String token);

    void saveUserData(User data);
}

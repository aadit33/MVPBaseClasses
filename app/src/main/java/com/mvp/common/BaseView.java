package com.mvp.common;


import com.mvp.permission.PermissionHelper;
import com.mvp.utils.PreferencesHelper;

public interface BaseView {

    void hideLoading();

    void showLoading();

    void showLoading(int progress);

    void setLoadingProgress(int progress);

    boolean isNetworkAvailable();

    PreferencesHelper getPreferences();

    String getNoInternetMessage();

    String getServerErrorMessage();

    PermissionHelper getPermission();

    NetworkHelper getNetworkHelper();

    void hidePendingTask();

    void runOnUi(Runnable runnable);

    String getDeviceId();

    void showNetworkError();

    void hideNetworkError();

}
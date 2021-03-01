package com.mvp.common;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleRegistry;

import com.mvp.permission.PermissionHelper;
import com.mvp.permission.PermissionHelperImpl;
import com.mvp.utils.PreferencesHelper;

public class BaseAlertDialog extends DialogFragment implements BaseView {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private PermissionHelper permissionHelper;

    public LifecycleRegistry getLifecycleRegistry() {
        return lifecycleRegistry;
    }

    @Override
    public void hideLoading() {
        getBaseActivity().hideLoading();
    }

    @Override
    public void showLoading() {
        getBaseActivity().showLoading();
    }

    @Override
    public void showLoading(int progress) {
        getBaseActivity().showLoading(progress);
    }

    @Override
    public void setLoadingProgress(int progress) {
        getBaseActivity().setLoadingProgress(progress);
    }

    @Override
    public String getNoInternetMessage() {
        return getBaseActivity().getNoInternetMessage();
    }

    @Override
    public String getServerErrorMessage() {
        return getBaseActivity().getServerErrorMessage();
    }

    @Override
    public PermissionHelper getPermission() {
        if (permissionHelper == null) {
            permissionHelper = new PermissionHelperImpl(this);
        }
        return permissionHelper;
    }

    @Override
    public NetworkHelper getNetworkHelper() {
        return getBaseActivity();
    }

    @Override
    public void hidePendingTask() {
        getBaseActivity().hidePendingTask();
    }

    @Override
    public void runOnUi(Runnable runnable) {
        getBaseActivity().runOnUi(runnable);
    }

    @Override
    public PreferencesHelper getPreferences() {
        return getBaseActivity().getPreferences();
    }

    @Override
    public boolean isNetworkAvailable() {
        return getBaseActivity().isNetworkAvailable();
    }

    public void showToast(String message) {
        getBaseActivity().showToast(message);
    }

    public BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }

    public void setToolbarTitle(Toolbar toolbar, String title) {
        getBaseActivity().setToolbar(toolbar, title);
    }

    public String getString(EditText editText) {
        return getBaseActivity().getString(editText);
    }

    public boolean onBackPressed() {
        return false;
    }

    public void showViews(View... views) {
        for (View v :
                views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public void hideViews(View... views) {
        for (View v :
                views) {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public String getDeviceId() {
        return getBaseActivity().getDeviceId();
    }

    @Override
    public void showNetworkError() {
        getBaseActivity().showNetworkError();
    }

    @Override
    public void hideNetworkError() {
        getBaseActivity().hideNetworkError();
    }

}

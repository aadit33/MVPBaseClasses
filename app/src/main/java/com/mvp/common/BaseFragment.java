package com.mvp.common;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleRegistry;

import com.mvp.permission.PermissionHelper;
import com.mvp.permission.PermissionHelperImpl;
import com.mvp.utils.PreferencesHelper;

import java.util.List;


public class BaseFragment extends Fragment implements BaseView {

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


    public void popOrReplace(@IdRes int container, Fragment fragment, String tag, boolean addToBackStack) {
        if (getChildFragmentManager().findFragmentByTag(tag) != null) {
            getChildFragmentManager().popBackStack(tag, 0);
        } else {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction().replace(container, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }
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
        return getBaseActivity().getBaseApp().getPreferences();
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
        List fragmentList = getChildFragmentManager().getFragments();
        boolean handled = false;
        for (Object f : fragmentList) {
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPressed();
                if (handled) {
                    break;
                }
            }
        }
        return handled;
    }

    public void showViews(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public void hideViews(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    public void enableViews(View... views) {
        for (View v : views) {
            v.setEnabled(true);
            v.setAlpha(1F);
        }
    }

    public void disableViews(View... views) {
        for (View v : views) {
            v.setEnabled(false);
            v.setAlpha(0.5F);
        }
    }

    @Override
    public String getDeviceId() {
        return getBaseActivity().getDeviceId();
    }

    public void internetConnectivityChanged(Boolean isConnected) {
    }

    @Override
    public void showNetworkError() {
        getBaseActivity().showNetworkError();
    }

    @Override
    public void hideNetworkError() {
        getBaseActivity().hideNetworkError();
    }

    public void onRetryClicked() {

    }

}
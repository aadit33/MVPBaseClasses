package com.mvp.common;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mvp.R;
import com.mvp.permission.PermissionHelper;
import com.mvp.permission.PermissionHelperImpl;
import com.mvp.utils.PreferencesHelper;
import com.mvp.utils.Utilities;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements BaseView, NetworkHelper, AnalyticsHelper {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private PermissionHelper permissionHelper;
    private RecyclerView rvPendingTask;
    private RelativeLayout rlPendingTask;
    private RelativeLayout container;
    private ConstraintLayout clNetworkContainer;
    private Button retry;
    private ProgressBar pgLoading, pgProgressDeterminate;
    private TextView txtProgress, txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initViews();
    }

    public void setLayoutView(int layoutResID) {
        super.setContentView(layoutResID);
        initNetworkContainer();
    }

    private void initViews() {
        container = findViewById(R.id.rl_progress_container);
        pgLoading = findViewById(R.id.pg_progress);
        pgProgressDeterminate = findViewById(R.id.pg_progress_determinate);
        txtProgress = findViewById(R.id.txt_progress);
        txtTitle = findViewById(R.id.txt_title);
    }

    private void initNetworkContainer() {
        clNetworkContainer = findViewById(R.id.cl_network_container);
        retry = findViewById(R.id.btn_retry);
        retry.setOnClickListener(v -> onRetryClicked());
    }

    public void onRetryClicked() {
        List fragmentList = getSupportFragmentManager().getFragments();
        for (Object f : fragmentList) {
            if (f instanceof BaseFragment) {
                ((BaseFragment) f).onRetryClicked();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void initPendingTaskView() {
//        rvPendingTask = findViewById(R.id.rv_pending_tasks);
//        rlPendingTask = findViewById(R.id.rl_pending_items);
//        if (rvPendingTask != null) {
//            adapter = new PendingTaskAdapter();
//            rvPendingTask.setLayoutManager(new LinearLayoutManager(this));
//            rvPendingTask.setAdapter(adapter);
//        }
    }


    public void popOrReplace(@IdRes int container, Fragment fragment, String tag, boolean addToBackStack) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, 0);
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(container, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    public LifecycleRegistry getLifecycleRegistry() {
        return lifecycleRegistry;
    }

    BaseApplication getBaseApp() {
        return ((BaseApplication) getApplication());
    }


    public String getString(AppCompatEditText editText) {
        String text = "";
        if (editText != null && editText.getText() != null &&
                editText.getText().toString().trim().length() > 0) {
            text = editText.getText().toString();
        }
        return text;
    }

    public String getString(EditText editText) {
        String text = "";
        if (editText != null && editText.getText() != null &&
                editText.getText().toString().trim().length() > 0) {
            text = editText.getText().toString();
        }
        return text;
    }

    public void setToolbar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
//            if (BuildConfig.BUILD_TYPE.contentEquals("develop")) {
//                toolbar.setOnClickListener(v -> {
//                    Intent intent = new Intent(BaseActivity.this, DbViewerActivity.class);
//                    startActivity(intent);
//                });
//            }
        }
    }

    public void setToolbar(Toolbar toolbar, String title, boolean isHome) {
        if (toolbar != null) {
            setToolbar(toolbar,title);
            if (isHome) {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            }
        }
    }

    public void overrideToolbarBackIcon(@DrawableRes int resource) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(resource);
        }
    }

    @Override
    public void hideLoading() {
        if (container != null) {
            container.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoading() {
        showSimpleLoading();
    }

    private void showSimpleLoading() {
        if (container != null) {
            container.setVisibility(View.VISIBLE);
            pgLoading.setVisibility(View.VISIBLE);
            pgLoading.setIndeterminate(true);
            pgProgressDeterminate.setVisibility(View.GONE);
            txtProgress.setText(getString(R.string.loading));
            txtProgress.setVisibility(View.VISIBLE);
            txtTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading(int progress) {
        showLoadingWithProgress(progress);
    }

    private void showLoadingWithProgress(int progress) {
        if (container != null) {
            container.setVisibility(View.VISIBLE);
            pgLoading.setVisibility(View.GONE);
            pgProgressDeterminate.setVisibility(View.VISIBLE);
            pgProgressDeterminate.setMax(progress);
            pgProgressDeterminate.setIndeterminate(false);
            txtProgress.setVisibility(View.VISIBLE);
            txtProgress.setText(progress + "of " + pgProgressDeterminate.getMax());
            txtTitle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLoadingProgress(int progress) {
        if (container != null) {
            container.setVisibility(View.VISIBLE);
            pgProgressDeterminate.setProgress(progress);
            txtProgress.setVisibility(View.VISIBLE);
            txtProgress.setText(progress + " of " + pgProgressDeterminate.getMax());
            txtTitle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean isNetworkAvailable() {
        return Utilities.Companion.isNetworkAvailable(this);
    }

    @Override
    public PreferencesHelper getPreferences() {
        return getBaseApp().getPreferences();
    }

    @Override
    public String getNoInternetMessage() {
        return getString(R.string.no_internet_connection);
    }


    @Override
    public String getServerErrorMessage() {
        return getString(R.string.server_error);
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
        return this;
    }

    @Override
    public void hidePendingTask() {
        if (rlPendingTask != null) {
            rlPendingTask.setVisibility(View.GONE);
        }
    }

    @Override
    public void runOnUi(Runnable runnable) {
        runOnUiThread(runnable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        List fragmentList = getSupportFragmentManager().getFragments();
        boolean handled = false;
        for (Object f : fragmentList) {
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPressed();
                if (handled) {
                    break;
                }
            }
        }
        if (!handled) {
            super.onBackPressed();
        }
    }

    @Override
    public Boolean hasInternet() {
        return isNetworkAvailable();
    }

    @Override
    public String getDeviceId() {
        return Utilities.Companion.getDeviceId(this);
    }

    @Override
    public void showNetworkError() {
        if (clNetworkContainer != null) {
            clNetworkContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideNetworkError() {
        if (clNetworkContainer != null) {
            clNetworkContainer.setVisibility(View.GONE);
        }
    }

    public CharSequence setErrorMessage(int resId, View errorView) {
        errorView.requestFocus();
        return getString(resId);
    }

    @Override
    public void logError(String message) {
        logError(new Exception(message));

    }

    @Override
    public void logError(Throwable throwable) {
     //log crashlytics here
    }

    @Override
    public void logEvent(String message) {
        logError(new Exception(message));
    }

    @Override
    public void setScreenName(String screenName) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    @Override
    public void setUserDetails() {

    }

    private FirebaseAnalytics getFirebaseAnalytics() {
        return getBaseApp().getFirebaseAnalytics();
    }

    @Override
    public AnalyticsHelper getAnalyticsHelper() {
        return this;
    }
}
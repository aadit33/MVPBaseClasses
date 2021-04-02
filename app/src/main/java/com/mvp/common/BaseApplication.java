package com.mvp.common;

import android.app.Application;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.facebook.soloader.SoLoader;
import com.facebook.stetho.Stetho;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mvp.utils.AppConstants;
import com.mvp.utils.AppPreferencesHelper;
import com.mvp.utils.PreferencesHelper;
import com.mvp.utils.Utilities;
import com.transerve.locationservices.manager.CoordinateManager;
import com.transerve.locationservices.manager.models.TTNewLocation;
import io.reactivex.observers.DisposableObserver;

public class BaseApplication extends Application implements LifecycleObserver, LocationProvider {

    private PreferencesHelper preferences;
    private NetworkHelper networkHelper;
    private CoordinateManager coordinateManager;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppModules();
        initLocationManagers();
        initStetho();
        initNetworkHelper();
        initConcealHelper();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initAppModules() {
        preferences = new AppPreferencesHelper(this, AppConstants.PREF_FILE_NAME);
    }

    private void initLocationManagers() {
        initCoordinateManager();
    }

    private void initNetworkHelper() {
        networkHelper = () -> Utilities.Companion.isNetworkAvailable(getApplicationContext());
    }

    private void initCoordinateManager() {
        coordinateManager = new CoordinateManager(this);
        coordinateManager.addObserver(new DisposableObserver<TTNewLocation>() {

            @Override
            public void onNext(TTNewLocation ttNewLocation) {
                if (ttNewLocation != null) {
                 //   preferences.saveLocation(ttNewLocation);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public CoordinateManager getLocationManager() {
        return coordinateManager;
    }

    public PreferencesHelper getPreferences() {
        return preferences;
    }

    public NetworkHelper getNetworkHelper() {
        return networkHelper;
    }

    private void initConcealHelper() {
        SoLoader.init(this, false);
    }

    public FirebaseAnalytics getFirebaseAnalytics() {
        if (firebaseAnalytics == null)
        {
            firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        return firebaseAnalytics;
    }

}
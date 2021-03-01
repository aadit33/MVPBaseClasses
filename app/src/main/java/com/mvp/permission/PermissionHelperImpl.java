package com.mvp.permission;

import android.Manifest;

import com.mvp.common.BaseActivity;
import com.mvp.common.BaseAlertDialog;
import com.mvp.common.BaseFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class PermissionHelperImpl implements PermissionHelper {

    private RxPermissions rxPermissions;

    public PermissionHelperImpl(BaseActivity context) {
        rxPermissions = new RxPermissions(context);
    }

    public PermissionHelperImpl(BaseFragment context) {
        rxPermissions = new RxPermissions(context);
    }

    public PermissionHelperImpl(BaseAlertDialog context) {
        rxPermissions = new RxPermissions(context);
    }

    @Override
    public void checkCameraPermission(PermissionObserver observer) {
        rxPermissions
                .requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(observer);
    }

    @Override
    public void checkStoragePermission(PermissionObserver observer) {
        rxPermissions
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(observer);
    }

    @Override
    public void checkLocationPermission(PermissionObserver observer) {
        rxPermissions
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(observer);
    }

    @Override
    public void checkAudioRecordPermission(PermissionObserver observer) {
        rxPermissions
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .subscribe(observer);
    }

    @Override
    public void checkVideoPermission(PermissionObserver permissionObserver) {
        rxPermissions
                .requestEach(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permissionObserver);
    }

    @Override
    public void checkCatalystRelatedPermission(PermissionObserver observer) {
        rxPermissions.requestEach(Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(observer);
    }
}

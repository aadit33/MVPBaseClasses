package com.mvp.permission;

import com.tbruyelle.rxpermissions2.Permission;

import io.reactivex.observers.DisposableObserver;

public abstract class PermissionObserver extends DisposableObserver<Permission> {

    private Boolean isAnyConditionDenied;
    private Boolean isAnyConditionNeverAskedAgain;

    public PermissionObserver() {
        isAnyConditionDenied = false;
        isAnyConditionNeverAskedAgain = false;
    }

    @Override
    public void onNext(Permission result) {
        if (result.granted) {
        } else if (result.shouldShowRequestPermissionRationale) {
            isAnyConditionDenied = true;
        } else {
            isAnyConditionNeverAskedAgain = true;
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        if (isAnyConditionNeverAskedAgain) {
            onPermissionNeverAskAgain();
        }
        else if(isAnyConditionDenied) {
            onPermissionDenied();
        }
        else {
            onPermissionGranted();
        }

    }

    public abstract void onPermissionGranted();

    public void onPermissionDenied() {

    }

    public void onPermissionNeverAskAgain() {

    }
}

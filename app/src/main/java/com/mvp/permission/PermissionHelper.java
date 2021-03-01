package com.mvp.permission;


public interface PermissionHelper {

    void checkCameraPermission(PermissionObserver observer);

    void checkStoragePermission(PermissionObserver observer);

    void checkLocationPermission(PermissionObserver observer);

    void checkAudioRecordPermission(PermissionObserver observer);

    void checkVideoPermission(PermissionObserver permissionObserver);

    void checkCatalystRelatedPermission(PermissionObserver observer);
}

package com.mvp.common;

public class BaseManager {

    private NetworkHelper networkHelper;

    public BaseManager(NetworkHelper networkHelper) {
        this.networkHelper = networkHelper;
    }

    public NetworkHelper getNetworkHelper() {
        return networkHelper;
    }
}

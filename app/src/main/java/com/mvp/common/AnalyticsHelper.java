package com.mvp.common;

/**
 * Created by jaidev on 4/10/18.
 */

public interface AnalyticsHelper {

    void logError(String message);

    void logError(Throwable throwable);

    void logEvent(String message);

    void setScreenName(String screenName);

    void setUserDetails();
}

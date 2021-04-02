package com.mvp.common;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.mvp.utils.PreferencesHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter implements LifecycleObserver {

    private BaseView view;

    private final CompositeDisposable disposeBag;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAttach() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onDetach() {
        disposeBag.clear();
    }

    public <T> void executeAsync(Observable<T> observable, BaseObserver<T> observer) {
        executeAsync(observable, observer, false);
    }

    public <T> void executeAsyncWithProgress(Observable<T> observable, BaseObserver<T> observer) {
        executeAsync(observable, observer, true);
    }

    public <T, R> void executeAsync(Observable<T> observable, Function<? super T, ? extends R> mapper, BaseObserver<R> observer) {
        executeAsync(observable, observer, false, mapper);
    }

    public <T, R> void executeAsyncWithProgress(Observable<T> observable, Function<? super T, ? extends R> mapper, BaseObserver<R> observer) {
        executeAsync(observable, observer, true, mapper);
    }

    private <T> void executeAsync(Observable<T> observable, BaseObserver<T> observer, final boolean isProgressShow) {
        Function<T, T> fakeMapper = new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return t;
            }
        };
        executeAsync(observable, observer, isProgressShow, fakeMapper);
    }

    private <T, R> void executeAsync(Observable<T> observable, BaseObserver<R> observer, final boolean isProgressShow, Function<? super T, ? extends R> mapper) {
        if (isProgressShow) {
            view.showLoading();
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isProgressShow) {
                            view.hideLoading();
                        }
                    }
                })
                .map(mapper)
                .subscribe(observer);
        disposeBag.add(observer);
    }


    public BasePresenter(BaseView view) {
        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        this.view = view;
        view.getAnalyticsHelper().setScreenName(getScreenName());
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
        disposeBag = new CompositeDisposable();
    }

    public PreferencesHelper getPreferencesHelper() {
        return view.getPreferences();
    }

    public NetworkHelper getNetworkHelper() {
        return view.getNetworkHelper();
    }

    public abstract String getScreenName();

}
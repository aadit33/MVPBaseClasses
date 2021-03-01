package com.mvp.common;

import com.google.gson.Gson;
import com.mvp.model.responses.ErrorResponse;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    BaseView view;

    public BaseObserver() {
    }

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    public void onNext(T t) {
        onOperationCompleted(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            try {
                if (exception.code() == 404 || exception.code() == 500) {
                    sendGenericError(exception);
                } else {
                    String body = exception.response().errorBody().string();
                    ErrorResponse errorResponse = new Gson().fromJson(body, ErrorResponse.class);
                    if (exception.code() == 426) {
                        sendAppUpdateError(errorResponse);
                    } else {
                        onError(errorResponse.getError());
                    }
                }
            } catch (Exception e1) {
                sendGenericError(e1);
            }
        } else if (e instanceof SocketTimeoutException) {
            sendGenericError(e);
        } else {
            sendGenericError(e);
        }
    }

    private void sendAppUpdateError(ErrorResponse exception) {
        if (view != null) {
         //   view.showAppUpdateDialog(exception);
        }
    }

    private void sendPasswordExpiredError() {
       // view.showPasswordExpiredDialog();
    }

    private void sendGenericError(Throwable e) {
        ErrorResponse.Error error = new ErrorResponse.Error();
        String message = e.getMessage();
        if (view != null) {
            message = view.getServerErrorMessage();
        }
        error.setMessage(message);
        onError(Arrays.asList(error));
    }

    public void onError(List<ErrorResponse.Error> error) {

    }


    @Override
    public void onComplete() {

    }

    public abstract void onOperationCompleted(T data);
}

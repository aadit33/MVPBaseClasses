package com.mvp.ui.login;

import com.mvp.common.BaseObserver;
import com.mvp.manager.SignInManager;
import com.mvp.model.User;
import com.mvp.model.responses.ErrorResponse;
import com.mvp.utils.AnalyticsConstants;
import com.mvp.utils.Utilities;

import java.util.List;

public class SignInPresenter extends SignInContract.Presenter {

    private SignInContract.View view;
    private SignInManager manager;

    SignInPresenter(SignInContract.View view) {
        super(view);
        this.view = view;
        manager = new SignInManager();
    }

    @Override
    public void loginClicked(String email, String password) {
        if (validate(email, password)) {
            processLogin(email, password);
        }
    }

    private boolean validate(String email, String password) {
        boolean status = true;
        if (!Utilities.Companion.isStringValid(email)) {
            //Validate Username
            view.showEmailEmptyError();
            status = false;
        } else if (!Utilities.Companion.isValidEmail(email)) {
            view.showValidEmailError();
            status = false;
        } else {
            //Validate password
            if (!Utilities.Companion.isStringValid(password)) {
                view.showPasswordEmptyError();
                status = false;
            } else {
                if (password.length() < 4) {
                    view.showPassWordInValid();
                    status = false;
                }
            }
        }
        return status;
    }

    private void processLogin(String email, String password) {
        view.showLoading();
        executeAsync(manager.logInUser(email, password), new BaseObserver<User>() {
            @Override
            public void onOperationCompleted(User data) {
                view.hideLoading();
                view.getPreferences().setToken(data.getToken());
                view.goToHomeScreen();
            }

            @Override
            public void onError(List<ErrorResponse.Error> error) {
                if (Utilities.Companion.isNotEmpty(error)) {
                    view.hideLoading();
                    view.clearPassword();
                    view.showError(error.get(0).getMessage());
                    view.getAnalyticsHelper().logError(error.get(0).getMessage());
                }
            }
        });
    }

    @Override
    public String getScreenName() {
        return AnalyticsConstants.SIGN_IN_SCREEN;
    }
}

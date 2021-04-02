package com.mvp.ui.login;


import com.mvp.common.BasePresenter;
import com.mvp.common.BaseView;

public interface SignInContract {

    interface View extends BaseView {

        void showEmailEmptyError();

        void showPasswordEmptyError();

        void showPassWordInValid();

        void goToHomeScreen();

        void clearPassword();

        void showError(String message);

        void showValidEmailError();
    }

    abstract class Presenter extends BasePresenter {

        protected Presenter(BaseView view) {
            super(view);
        }

        public abstract void loginClicked(String phone, String password);
    }
}

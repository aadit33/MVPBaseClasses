package com.mvp.manager;


import com.mvp.common.BaseManager;
import com.mvp.interaction.SignInInteraction;
import com.mvp.model.User;
import com.mvp.network.SignInApiProvider;

import io.reactivex.Observable;

public class SignInManager extends BaseManager {
    private SignInInteraction loginApi;

    public SignInManager() {
        loginApi = new SignInApiProvider();
    }

    public Observable<User> logInUser(String email, String password) {
        return loginApi.loginUser(email, password);
    }
}

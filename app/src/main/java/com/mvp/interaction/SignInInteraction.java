package com.mvp.interaction;

import com.mvp.model.User;

import io.reactivex.Observable;

public interface SignInInteraction {

    Observable<User> loginUser(String email, String password);

}

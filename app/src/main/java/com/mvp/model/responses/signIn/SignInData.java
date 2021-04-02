
package com.mvp.model.responses.signIn;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SignInData {

    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}

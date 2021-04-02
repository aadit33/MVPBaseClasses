package com.mvp.network;

import com.google.gson.annotations.SerializedName;
import com.mvp.interaction.SignInInteraction;
import com.mvp.model.User;
import com.mvp.model.responses.signIn.SignInResponse;
import com.mvp.utils.HttpConstants;
import com.mvp.utils.Utilities;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class SignInApiProvider extends NetworkProvider implements SignInInteraction {

    private SignInApiService signInApiService;

    public SignInApiProvider() {
        signInApiService = create(SignInApiService.class);
    }

    @Override
    public Observable<User> loginUser(String email, String password) {
        return signInApiService.loginUser(new SignInRequest(email, password)).map(new Function<SignInResponse, User>() {
            @Override
            public User apply(SignInResponse loginResponse) throws Exception {
                User user = new User();
                if (Utilities.Companion.isNotEmpty(loginResponse)) {
                    if (Utilities.Companion.isStringValid(loginResponse.getData().getToken())) {
                        user.setToken(loginResponse.getData().getToken());
                    }
                }
                return user;
            }
        });
    }

    public interface SignInApiService {
        @POST(HttpConstants.SIGNIN_API)
        Observable<SignInResponse> loginUser(@Body SignInRequest user);
    }

    private static class SignInRequest {
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;

        SignInRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}

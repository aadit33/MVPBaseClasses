package com.mvp.network;

import com.mvp.utils.HttpConstants;
import com.mvp.utils.PreferencesHelper;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private PreferencesHelper preferenceHelper;

    public NetworkProvider(PreferencesHelper preferenceHelper) {
        this.preferenceHelper = preferenceHelper;
        initOkHTTP();
        initRetrofit();
    }

    public NetworkProvider() {
        initOkHTTP();
        initRetrofit();
    }

    private void initOkHTTP() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES).writeTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES).connectTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES);
//        if (BuildConfig.BUILD_TYPE.contentEquals("develop")) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addNetworkInterceptor(new StethoInterceptor());
//            // builder.addNetworkInterceptor(new ChuckInterceptor(AppConstants.context));
//            builder.addInterceptor(interceptor);
//        }
        builder.addNetworkInterceptor(new AuthenticationInterceptors());
        okHttpClient = builder.build();
    }

    private void initRetrofit() {
        String basePath = HttpConstants.SERVER_URL;
        retrofit = new Retrofit.Builder()
                .baseUrl(basePath)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public class AuthenticationInterceptors implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest;
            if (preferenceHelper != null) {
                newRequest = request.newBuilder()
                       // .addHeader("Authorization", AppConstants.TOKEN_BEARER + preferenceHelper.getAccessToken())
                     //   .addHeader("Accept-Language", preferenceHelper.getLocaleLanguage())
                        .build();
                return chain.proceed(newRequest);
            }
            return chain.proceed(request);
        }
    }
}

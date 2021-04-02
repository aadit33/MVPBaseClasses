
package com.mvp.model.responses.signIn;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SignInResponse {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("data")
    private SignInData mData;
    @SerializedName("error")
    private List<Object> mError;
    @SerializedName("success")
    private Boolean mSuccess;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public SignInData getData() {
        return mData;
    }

    public void setData(SignInData data) {
        mData = data;
    }

    public List<Object> getError() {
        return mError;
    }

    public void setError(List<Object> error) {
        mError = error;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}

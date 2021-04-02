package com.mvp.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mvp.R;
import com.mvp.common.BaseActivity;
import com.mvp.utils.Alerts;
import com.mvp.utils.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity implements SignInContract.View {

    @BindView(R.id.btn_sign_in)
    Button btnSignIn;
    @BindView(R.id.pg_sign_in)
    ProgressBar pgSignIn;
    @BindView(R.id.txt_edt_email)
    TextInputEditText txtEdtEmail;
    @BindView(R.id.txt_input_email)
    TextInputLayout txtInputEmail;
    @BindView(R.id.txt_edt_password)
    TextInputEditText txtEdtPassword;
    @BindView(R.id.txt_input_password)
    TextInputLayout txtInputPassword;

    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        presenter = new SignInPresenter(this);
    }

    @OnClick({R.id.btn_sign_in, R.id.txt_sign_up, R.id.txt_forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                onSigInClicked();
                break;
        }
    }

    private void onSigInClicked() {
        txtInputEmail.setError(null);
        txtInputPassword.setError(null);
        presenter.loginClicked(getString(txtEdtEmail), getString(txtEdtPassword));
        Utilities.Companion.hideKeyboard(SignInActivity.this);
    }

    @Override
    public void showEmailEmptyError() {
        txtInputEmail.setError((setErrorMessage(R.string.email_empty, txtEdtEmail)));
    }

    @Override
    public void showPasswordEmptyError() {
        txtInputPassword.setError((setErrorMessage(R.string.pass_empty, txtEdtPassword)));
    }

    @Override
    public void showPassWordInValid() {
        txtInputPassword.setError((setErrorMessage(R.string.valid_pass, txtEdtPassword)));
    }

    @Override
    public void showValidEmailError() {
        txtInputEmail.setError((setErrorMessage(R.string.email_valid, txtEdtEmail)));
    }

    @Override
    public void goToHomeScreen() {
        System.out.println("LOGGED IN");
    }

    @Override
    public void clearPassword() {
        txtEdtPassword.setText("");
    }

    @Override
    public void showError(String message) {
        Alerts.showMessage(this, message);
    }

    @Override
    public void showLoading() {
        pgSignIn.setVisibility(View.VISIBLE);
        btnSignIn.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pgSignIn.setVisibility(View.GONE);
        btnSignIn.setVisibility(View.VISIBLE);
    }
}

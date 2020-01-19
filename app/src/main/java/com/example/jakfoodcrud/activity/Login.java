package com.example.jakfoodcrud.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jakfoodcrud.MainActivity;
import com.example.jakfoodcrud.R;
import com.example.jakfoodcrud.helper.SessionManager;
import com.example.jakfoodcrud.model.ResponseUser;
import com.example.jakfoodcrud.network.ConfigRetrofit;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends SessionManager {

    @BindView(R.id.login_username)
    TextInputEditText loginUsername;
    @BindView(R.id.login_password)
    TextInputEditText loginPassword;
    @BindView(R.id.rg_user_admin_sign)
    RadioButton rgUserAdminSign;
    @BindView(R.id.rg_user_biasa_sign)
    RadioButton rgUserBiasaSign;
    @BindView(R.id.sign_in)
    Button signIn;
    @BindView(R.id.register)
    TextView register;

    String username, password, levelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);

        setUpStartView();
    }

    private void setUpStartView() {
        if (rgUserAdminSign.isChecked()) levelUser = "Admin";
        else levelUser = "User Biasa";

    }


    @OnClick({R.id.rg_user_admin_sign, R.id.rg_user_biasa_sign, R.id.sign_in, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rg_user_admin_sign:
                levelUser = "Admin";
                break;
            case R.id.rg_user_biasa_sign:
                levelUser = "User Biasa";
                break;
            case R.id.sign_in:
                login();
                break;
            case R.id.register:
                intent(Register.class);
                break;
        }
    }

    private void login() {
        username = loginUsername.getText().toString().trim();
        password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            loginUsername.setError(getString(R.string.isEmpyField));
        } else if (TextUtils.isEmpty(password)){
            loginPassword.setError(getString(R.string.isEmpyField));
        } else {
            fetchLogin();
        }
    }

    private void fetchLogin() {
        showProgressDialog("Fetch Login....");
        ConfigRetrofit.getInstance().requestLogin(username, password, levelUser).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String result = response.body().getResult();
                String message = response.body().getMsg();
                String idUser = response.body().getUser().getIdUser();

                if (result.equals("1")){
                    sessionManager.createSession(username);
                    sessionManager.setIdUser(idUser);
                    intent(MainActivity.class);
                    finish();
                } else {
                    shortToast(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
            longToast(t.getMessage());
            }
        });
    }
}

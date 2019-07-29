package com.hemantax.musica.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.utils.PrefManager;
import com.hemantax.musica.utils.RetrofitUtils;
import com.hemantax.musica.utils.RetrofitUtils.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etUser)
    EditText etUser;
    @BindView(R.id.etPass)
    EditText etPass;
    RetrofitService service;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch() && prefManager.isLoggedIn()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);


        service = RetrofitUtils.getInstance();
        setStatusBarTranslucent(true);




    }
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @OnClick({R.id.btnLogin, R.id.btnReg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                register();
                break;
        }
    }

    private void register() {
        Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }

    private void login() {
        final String name = etUser.getText().toString();
        String pass = etPass.getText().toString();

        service.login(name,pass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if (response.body().equals("1")){
                        prefManager.setFirstTimeLaunch(false);
                        prefManager.setLoggedIn(true);
                        prefManager.setUserame(name);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        etPass.setText("");
                        Toast.makeText(LoginActivity.this, "Username or password is Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error",t.toString());
                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}

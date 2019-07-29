package com.hemantax.musica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.utils.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etEmail)
    EditText etEmail;
    RetrofitUtils.RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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

    @OnClick(R.id.btnSignUp)
    public void onViewClicked() {
        service.register(etName.getText().toString(),etUsername.getText().toString(),etPassword.getText().toString(),etEmail.getText().toString())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String success=response.body().toString();
                        if(success.equals("1")){
                            Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        }else{
                            etName.setText("");
                            etUsername.setText("");
                            etPassword.setText("");
                            etEmail.setText("");
                            Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Error",t.toString());
                        Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}

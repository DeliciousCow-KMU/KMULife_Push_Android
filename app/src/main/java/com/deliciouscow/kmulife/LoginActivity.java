package com.deliciouscow.kmulife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deliciouscow.kmulife.api.KMULifeService;
import com.deliciouscow.kmulife.api.ServiceGenerator;
import com.deliciouscow.kmulife.data.LoginData;
import com.deliciouscow.kmulife.data.LoginObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    KMULifeService kmulifeAPI;

    EditText universityNumber, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kmulifeAPI = ServiceGenerator.createService(KMULifeService.class);
        universityNumber = (EditText)findViewById(R.id.university_number);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
//                kmulifeAPI.login(universityNumber.getText().toString(), password.getText().toString());
                Call<ResponseBody> data = kmulifeAPI.login(universityNumber.getText().toString(), password.getText().toString());
                data.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("전송", response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        LoginData data = response.body().getData();

//                        Toast.makeText(LoginActivity.this, data.getUser_id() + ", " + data.getCollege() + ", " + data.getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("전송", "실패");
                        Toast.makeText(LoginActivity.this, "전송 실패", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}

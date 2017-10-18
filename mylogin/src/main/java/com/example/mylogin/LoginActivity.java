package com.example.mylogin;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button signinButton;
    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bmob.initialize(this, "e01990f453876ec8667dbc94fb0ff9ef", "bmob");

        accountEdit = (EditText) findViewById(R.id.email_edittext);
        passwordEdit = (EditText) findViewById(R.id.password_edittext);
        signinButton = (Button) findViewById(R.id.custom_signin_button);
        signupButton = (Button) findViewById(R.id.custom_signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    @SuppressLint("UseValueOf")
    private void signup() {
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        final ModelUser modelUser = new ModelUser();
        modelUser.setUsername(account);
        modelUser.setPassword(password);

        modelUser.signUp(new SaveListener<ModelUser>() {
            @Override
            public void done(ModelUser modelUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    public void signin() {
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        ModelUser modelUser = new ModelUser();
        modelUser.setUsername(account);
        modelUser.setPassword(password);
    }




}


package com.example.mylogin;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button signinButton;
    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bmob.initialize(this, "e01990f453876ec8667dbc94fb0ff9ef", "bmob");

        initialize();

    }
    // 初始化控件
    private void initialize() {
        accountEdit = (EditText) findViewById(R.id.account_edittext);
        passwordEdit = (EditText) findViewById(R.id.password_edittext);
        signinButton = (Button) findViewById(R.id.custom_signin_button);
        signupButton = (Button) findViewById(R.id.custom_signup_button);

        signupButton.setOnClickListener(this);
        signinButton.setOnClickListener(this);

//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signup();
//            }
//        });
//
//        signinButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signin();
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_signin_button:
                signin();
                break;
            case R.id.custom_signup_button:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void signin() {
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        final ModelUser modelUser = new ModelUser();
        modelUser.setUsername(account);
        modelUser.setPassword(password);
        modelUser.login(new SaveListener<ModelUser>() {

            @Override
            public void done(ModelUser modelUser, BmobException e) {
                if(e==null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}


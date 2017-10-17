package com.example.mylogin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 17-10-17.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText repeatpasswordEdit;
    private Button signupButton;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Bmob.initialize(this, "e01990f453876ec8667dbc94fb0ff9ef", "bmob");
        initialize();

}

    private void initialize() {
        accountEdit = (EditText) findViewById(R.id.email_edittext);
        passwordEdit = (EditText) findViewById(R.id.password_edittext);
        repeatpasswordEdit = (EditText) findViewById(R.id.repeat_password_edittext);
        signupButton = (Button) findViewById(R.id.custom_signup_button);

        signupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_signup_button:
                signup();
                break;

        }
    }

    private void signup() {
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String repeatpassword = repeatpasswordEdit.getText().toString();

        final ModelUser modelUser = new ModelUser();
        modelUser.setUsername(account);
        modelUser.setPassword(password);

        modelUser.signUp(new SaveListener<ModelUser>() {
            @Override
            public void done(ModelUser modelUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(SignupActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}

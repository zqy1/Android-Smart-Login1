package com.example.mylogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView accountTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "e01990f453876ec8667dbc94fb0ff9ef", "bmob");

        logoutButton = (Button) findViewById(R.id.logout_button);
        accountTextView = (TextView) findViewById(R.id.user_account_textview);
        logoutButton.setOnClickListener(this);


        ModelUser userInfo = BmobUser.getCurrentUser(ModelUser.class);
        accountTextView.setText(userInfo.getUsername());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                ModelUser.logOut();
                Toast.makeText(MainActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
                refresh();
                break;
            default:

                break;
        }
    }

    public void refresh() {
//        onCreate(null);
        setContentView(R.layout.activity_main);
    }
}

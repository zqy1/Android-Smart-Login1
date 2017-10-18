package com.example.mylogin;

import cn.bmob.v3.BmobUser;

/**
 * Created by zqy on 17-10-17.
 */

public class ModelUser extends BmobUser {
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

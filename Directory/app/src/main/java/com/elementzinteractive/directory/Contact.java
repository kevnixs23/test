package com.elementzinteractive.directory;

import android.graphics.Bitmap;

/**
 * Created by Elementz on 2/4/2016.
 */
public class Contact {
    private int mAvatar;
    private String mName;
    private String mContactNumber;
    private String mEmail;
    public Contact(int avatar, String name, String phone, String email) {
        mAvatar = avatar;
        mName = name;
        mContactNumber = phone;
        mEmail = email;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }
    public int getAvatar() {
        return mAvatar;
    }

    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }

    public void setPhone(String phone) {
        mContactNumber = phone;
    }
    public String getPhone() {
        return mContactNumber;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
    public String getEmail() {
        return mEmail;
    }
}

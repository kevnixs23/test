package com.elementzinteractive.personalinfo.model;

/**
 * Created by Elementz on 2/15/2016.
 */
public class Person {
    private String mFirstName;
    private String mLastName;
    private String mBirthDate;
    private String mGender;
    private String mPhone;
    private String mEmail;
    private int mAvatar;
    private String mAvatarURL;

    public Person(int mAvatar, String mLastName, String mFirstName, String mGender, String mBirthDate, String mPhone, String mEmail) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.mAvatar = mAvatar;
        this.mGender = mGender;
        this.mBirthDate = mBirthDate;
    }
    public Person(String mAvatar, String mLastName, String mFirstName, String mGender, String mBirthDate, String mPhone, String mEmail) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.mAvatarURL = mAvatar;
        this.mGender = mGender;
        this.mBirthDate = mBirthDate;
    }
    public Person() {

    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String mBirthDate) {
        this.mBirthDate = mBirthDate;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getAvatarURL() {
        return mAvatarURL;
    }

    public void setAvatarURL(String mAvatar) {
        this.mAvatarURL = mAvatar;
    }
}

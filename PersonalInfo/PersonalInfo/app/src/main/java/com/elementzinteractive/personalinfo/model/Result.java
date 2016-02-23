package com.elementzinteractive.personalinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("user")
    @Expose
    private User user;

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

}

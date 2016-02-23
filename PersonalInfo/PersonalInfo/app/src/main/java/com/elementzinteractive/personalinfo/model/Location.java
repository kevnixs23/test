package com.elementzinteractive.personalinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private Integer zip;

    /**
     * @return The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The zip
     */
    public Integer getZip() {
        return zip;
    }

    /**
     * @param zip The zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

}

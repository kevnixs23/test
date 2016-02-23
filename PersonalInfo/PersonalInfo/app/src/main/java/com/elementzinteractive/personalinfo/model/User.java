
package com.elementzinteractive.personalinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("md5")
    @Expose
    private String md5;
    @SerializedName("sha1")
    @Expose
    private String sha1;
    @SerializedName("sha256")
    @Expose
    private String sha256;
    @SerializedName("registered")
    @Expose
    private Integer registered;
    @SerializedName("dob")
    @Expose
    private Integer dob;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cell")
    @Expose
    private String cell;
    @SerializedName("HETU")
    @Expose
    private String HETU;
    @SerializedName("picture")
    @Expose
    private Picture picture;

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The name
     */
    public Name getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     *
     * @param salt
     * The salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     * The md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     *
     * @param md5
     * The md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     *
     * @return
     * The sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     *
     * @param sha1
     * The sha1
     */
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    /**
     *
     * @return
     * The sha256
     */
    public String getSha256() {
        return sha256;
    }

    /**
     *
     * @param sha256
     * The sha256
     */
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    /**
     *
     * @return
     * The registered
     */
    public Integer getRegistered() {
        return registered;
    }

    /**
     *
     * @param registered
     * The registered
     */
    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    /**
     *
     * @return
     * The dob
     */
    public Integer getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(Integer dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The cell
     */
    public String getCell() {
        return cell;
    }

    /**
     *
     * @param cell
     * The cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     *
     * @return
     * The HETU
     */
    public String getHETU() {
        return HETU;
    }

    /**
     *
     * @param HETU
     * The HETU
     */
    public void setHETU(String HETU) {
        this.HETU = HETU;
    }

    /**
     *
     * @return
     * The picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}
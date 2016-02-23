package com.elementzinteractive.personalinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class JsonObj {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("seed")
    @Expose
    private String seed;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality The nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return The seed
     */
    public String getSeed() {
        return seed;
    }

    /**
     * @param seed The seed
     */
    public void setSeed(String seed) {
        this.seed = seed;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

}



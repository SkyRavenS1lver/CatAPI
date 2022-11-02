package com.example.pertemuan8api;

import com.google.gson.annotations.SerializedName;


public class Cat {
    @SerializedName("fact")
    private String facts;

    public String getFacts() {
        return facts;
    }

    public Cat(String facts) {
        this.facts = facts;
    }


}

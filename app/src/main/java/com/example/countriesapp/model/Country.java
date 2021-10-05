package com.example.countriesapp.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    /*
    *   @SerializeName("name") is gson annotation used to tell retrofit that this attribute correspond to the attribute "name" in the server.
    *   So the "name" inside the parentheses correspond to the server attribute that we want our Country attribute name to be.
    *
    *   Notice that if the name you give to the attribute of your object is the same in the server, you don't have to add that annotation,
    *   but if it's not the case you have to!
    * */

    @SerializedName("name")
    private String name;

    @SerializedName("capital")
    private String capital;

    @SerializedName("flagPNG")
    private String flag;

    public Country(String name, String capital, String flag) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

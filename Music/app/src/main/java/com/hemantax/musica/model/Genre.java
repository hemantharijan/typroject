package com.hemantax.musica.model;

import com.google.gson.annotations.SerializedName;



public class Genre {
    @SerializedName("id") int id;
    @SerializedName("genre_name") String name;
    @SerializedName("Img_url") String url;

    public Genre(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

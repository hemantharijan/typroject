package com.hemantax.musica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sabrish on 18/01/18.
 */

public class Song {
    @SerializedName("id") int id;
    @SerializedName("name")String name;
    @SerializedName("cover")String imageUrl;
    @SerializedName("artist_name")String artist;
    @SerializedName("uploaded")String uploadedBy;
    @SerializedName("url")String url;

    public Song(int id, String name, String imageUrl, String artist, String uploadedBy,String url) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.artist = artist;
        this.uploadedBy = uploadedBy;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

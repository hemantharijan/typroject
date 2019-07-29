package com.hemantax.musica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hemant on 25-01-2018.
 */

public class upload {
    @SerializedName("username") String username;
    @SerializedName("Musicname")String Musicname;
    @SerializedName("artist_name")String artist_name;
    @SerializedName("genre")String genre;


    public upload(String userid, String Musicname, String artist_name, String genre) {
        this.username = userid;
        this.Musicname = Musicname;
        this.artist_name = artist_name;
        this.genre = genre;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userid) {
        this.username = userid;
    }

    public String getMusicname() {
        return Musicname;
    }

    public void setMusicname(String musicname) {
        Musicname = musicname;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}



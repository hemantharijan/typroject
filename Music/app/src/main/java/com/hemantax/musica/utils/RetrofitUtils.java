package com.hemantax.musica.utils;

import com.hemantax.musica.model.Genre;
import com.hemantax.musica.model.Song;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by sabrish on 17/01/18.
 */

public class RetrofitUtils {

    public static RetrofitService instance;
    public static String BASE_URL = "http://192.168.0.2:8088/Music/";
    public static RetrofitService getInstance(){
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService.class);
        }
        return instance;
    }


    public interface RetrofitService{

        @POST("insertData.jsp")
        @FormUrlEncoded
        Call<String> register(
                @Field("name")String name,
                @Field("username")String username,
                @Field("password")String password,
                @Field("email")String email
        );

        @POST("Login.jsp")
        @FormUrlEncoded
        Call<String>login(@Field("username")String username,
                          @Field("password")String password);

        @POST("upload.jsp")
        @FormUrlEncoded
        Call<String>UploadMusicDetails(@Field("User_name")String User_name,
                                       @Field("Music_Name")String Music_Name,
                                       @Field("Artist_Name")String Artist_Name,
                                       @Field("Genre")String Genre
        );


        @GET("getgenre.jsp")
        Call<List<Genre>>getGenres();

        @GET("getmusic.jsp")
        Call<List<Song>>getMusic(@Query("genre_id")int id);


        @GET
        @Streaming
        Call<ResponseBody> getFile(@Url String url);


        @Multipart
        @POST("UploadServlet")
        Call<ResponseBody>uploadMusic(
                @Part MultipartBody.Part music,
                @Part MultipartBody.Part username,
                @Part MultipartBody.Part musicname,
                @Part MultipartBody.Part artisname,
                @Part MultipartBody.Part genre

        );

    }

   }


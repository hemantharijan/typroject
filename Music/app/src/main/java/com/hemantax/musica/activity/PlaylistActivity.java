package com.hemantax.musica.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.adapter.SongsRVAdapter;
import com.hemantax.musica.model.Song;
import com.hemantax.musica.services.MusicService;
import com.hemantax.musica.utils.RetrofitUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity implements SongsRVAdapter.OnClickListener {

    @BindView(R.id.songRv)
    RecyclerView songRv;

    RetrofitUtils.RetrofitService service;
    List<Song> songList = new ArrayList<Song>();
    SongsRVAdapter adapter;

    private MusicService musicService;
    private Intent playIntent;
    private boolean musicBound = false;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(getIntent().getStringExtra("genre_name"));

        service = RetrofitUtils.getInstance();
        int genre_id = getIntent().getIntExtra("genre_id", 1);

        service.getMusic(genre_id).enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body();
                setList(songList);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(PlaylistActivity.this, "No Network", Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new SongsRVAdapter(songList, this);
        songRv.setLayoutManager(new LinearLayoutManager(PlaylistActivity.this));
        songRv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            Log.e("if", "entered");
            playIntent = new Intent(this, MusicService.class);
            startService(playIntent);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        if (musicBound) {
            unbindService(musicConnection);
            musicBound = false;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        musicService = null;
        super.onDestroy();
    }

    public void setList(List<Song> songList) {
        adapter = new SongsRVAdapter(songList, this);
        songRv.setLayoutManager(new LinearLayoutManager(PlaylistActivity.this));
        songRv.setAdapter(adapter);
        musicService.setSongsList(songList);
    }


    @Override
    public void onPlayPauseClicked(View v, int position, Song s) {
        Log.e("PlaylistActivity", s.getUrl());
        ImageView playPauseButton = (ImageView) v;
        if (musicService.isPng()) {
            playPauseButton.setImageResource(R.drawable.ic_play);
            musicService.pausePlayer();
        } else {
            playPauseButton.setImageResource(R.drawable.ic_pause);
            musicService.setSongPos(position);
            musicService.playSong();
        }

    }

    @Override
    public void onDownloadClicked(View v, int position, Song s) {
        service.getFile(RetrofitUtils.BASE_URL + s.getUrl()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, response.message());
                if(!response.isSuccessful()){
                    Log.e(TAG, "Something's gone wrong");
                    return;
                }
                DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask();
                downloadFileAsyncTask.execute(response.body().byteStream());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public List<Song> getSongsList() {
        return songList;
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            Log.e("MusicService", "Instantiated");
            musicService = binder.getService();
            musicService.setSongsList(songList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    private class DownloadFileAsyncTask extends AsyncTask<InputStream, Void, Boolean> {

        final String appDirectoryName = getString(R.string.app_name);
        final File musicRoot = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), appDirectoryName);
        final String filename = "music.mp3";

        @Override
        protected Boolean doInBackground(InputStream... params) {
            musicRoot.mkdirs();
            InputStream inputStream = params[0];
            File file = new File(musicRoot, filename);
            OutputStream output = null;
            try {
                file.createNewFile();
                output = new FileOutputStream(file);

                byte[] buffer = new byte[1024]; // or other buffer size
                int read;

                Log.d(TAG, "Attempting to write to: " + musicRoot + "/" + filename);
                while ((read = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                    Log.v(TAG, "Writing to buffer to output stream.");
                }
                Log.d(TAG, "Flushing output stream.");
                output.flush();
                Log.d(TAG, "Output flushed.");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception: " + e.getMessage());
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (output != null) {
                        output.close();
                        Log.d(TAG, "Output stream closed sucessfully.");
                    }
                    else{
                        Log.d(TAG, "Output stream is null");
                    }
                } catch (IOException e){
                    Log.e(TAG, "Couldn't close output stream: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG, "Download success: " + result);
            Toast.makeText(PlaylistActivity.this, "Download "+result, Toast.LENGTH_SHORT).show();
        }
    }


}

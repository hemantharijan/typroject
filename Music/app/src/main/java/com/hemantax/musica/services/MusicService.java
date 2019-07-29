package com.hemantax.musica.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;

import com.hemantax.musica.model.Song;
import com.hemantax.musica.utils.RetrofitUtils;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {

    private final IBinder musicBind = new MusicBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    private MediaPlayer player;
    private List<Song> songsList;
    private int songPos;
    private static MusicService mInstance = null;
    Song playSong;
    //public static final String BROADCAT_ACTION = "musicChanged";
    //Intent broadcastIntent;
    public class MusicBinder extends Binder{
        public MusicService getService(){
            return MusicService.this;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        songPos = 0;
        player = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                //playNext();
                //sendBroadcast(broadcastIntent);
            }
        });
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                mediaPlayer.reset();
                return false;
            }
        });
    }

    public void setSongsList(List<Song> songsList) {
        this.songsList = songsList;
    }

    public void setSongPos(int songPos) {
        this.songPos = songPos;
    }

    @Override
    public boolean onUnbind(Intent intent) {
//        player.stop();
//        player.release();
        return false;
    }


    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    public void playSong(){
        player.reset();
        playSong = songsList.get(songPos);
        String url = RetrofitUtils.BASE_URL + playSong.getUrl();
        try {
            player.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.prepareAsync();
    }

    public int getPosn(){
        return player.getCurrentPosition();
    }

    public int getDur(){
        return player.getDuration();
    }

    public boolean isPng(){
        return player.isPlaying();
    }

    public void pausePlayer(){
        player.pause();
    }

    public void go(){
        player.start();
    }

    public static MusicService getInstance(){
        return mInstance;
    }

    public Song getPlaySong() {
        return playSong;
    }
}

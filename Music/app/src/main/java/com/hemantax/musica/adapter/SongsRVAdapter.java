package com.hemantax.musica.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.activity.PlaylistActivity;
import com.hemantax.musica.model.Song;
import com.hemantax.musica.utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sabrish on 18/01/18.
 */

public class SongsRVAdapter extends RecyclerView.Adapter<SongsRVAdapter.MyViewHolder> {
    RetrofitUtils.RetrofitService service;



    List<Song> songList;
    View itemView;
    Context context;

    private OnClickListener listener = null;

    public SongsRVAdapter(List<Song> songList, OnClickListener listener) {
        this.songList = songList;
        this.listener = null;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.tvSongName.setText(song.getName());
        holder.tvArtist.setText(song.getArtist());
        holder.tvUploaded.setText("Uploaded By " + song.getUploadedBy());
        Picasso.with(holder.ctx).load(RetrofitUtils.BASE_URL + song.getImageUrl()).into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = null;
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgCover)
        ImageView imgCover;
        @BindView(R.id.btnDownload)
        ImageView btnDownload;
        @BindView(R.id.btnPlayPause)
        ImageView btnPlayPause;
        @BindView(R.id.tvSongName)
        TextView tvSongName;
        @BindView(R.id.tvArtist)
        TextView tvArtist;
        @BindView(R.id.tvUploaded)
        TextView tvUploaded;
        Context ctx;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ctx = itemView.getContext();
        }

        @OnClick({R.id.btnDownload, R.id.btnPlayPause})
        public void onViewClicked(View view) {
            int position = getAdapterPosition();
            Song s = songList.get(position);
            switch (view.getId()) {
                case R.id.btnDownload:
                    listener.onDownloadClicked(view,position,s);
                    break;
                case R.id.btnPlayPause:
                    listener.onPlayPauseClicked(view,position,s);
                    break;
            }
        }


    }
    public interface OnClickListener {

        void onPlayPauseClicked(View v, int position, Song s);

        void onDownloadClicked(View v, int position, Song s);

    }

}

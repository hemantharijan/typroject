package com.hemantax.musica.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemantax.musica.R;
import com.hemantax.musica.activity.PlaylistActivity;
import com.hemantax.musica.model.Genre;
import com.hemantax.musica.utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabrish on 18/01/18.
 */

public class GenreRVAdapter extends RecyclerView.Adapter<GenreRVAdapter.MyViewHolder> {

    List<Genre> genreList;

    public GenreRVAdapter(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        String url = RetrofitUtils.BASE_URL + genre.getUrl();
        holder.tvGenreName.setText(genre.getName());
        Picasso.with(holder.ctx)
                .load(url)
                .into(holder.imgGenre);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgGenre)
        ImageView imgGenre;
        @BindView(R.id.tvGenreName)
        TextView tvGenreName;
        Context ctx;
        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ctx = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Genre genre = genreList.get(position);
                    Intent i = new Intent(ctx, PlaylistActivity.class);
                    i.putExtra("genre_name",genre.getName());
                    i.putExtra("genre_id",genre.getId());
                    ctx.startActivity(i);
                }
            });
        }
    }
}

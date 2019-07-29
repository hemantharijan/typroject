package com.hemantax.musica.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.activity.MainActivity;
import com.hemantax.musica.adapter.GenreRVAdapter;
import com.hemantax.musica.model.Genre;
import com.hemantax.musica.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {


    @BindView(R.id.rvGenre)
    RecyclerView rvGenre;
    Unbinder unbinder;
    Context ctx;

    RetrofitUtils.RetrofitService service;
    GenreRVAdapter adapter;
    List<Genre> genreList = new ArrayList<Genre>();

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ctx = (AppCompatActivity)getActivity();
        service = RetrofitUtils.getInstance();
        service.getGenres().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                Log.e("MainActivity","OnResponse");
                genreList = response.body();
                adapter = new GenreRVAdapter(genreList);
                rvGenre.setLayoutManager(new LinearLayoutManager(ctx));
                rvGenre.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Log.e("MainActivity","OnFailure",t);
                Toast.makeText(ctx, "No Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

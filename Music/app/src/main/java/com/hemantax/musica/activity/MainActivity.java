package com.hemantax.musica.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hemantax.musica.R;
import com.hemantax.musica.fragment.FeedbackFragment;
import com.hemantax.musica.fragment.LibraryFragment;
import com.hemantax.musica.fragment.UploadFragment;
import com.hemantax.musica.utils.PrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    TextView txtusername;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(this);
        txtusername = navigationView.getHeaderView(0).findViewById(R.id.usernamedisplay);
        txtusername.setText(prefManager.getUsername());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new LibraryFragment());
        ft.commit();
        getSupportActionBar().setTitle("Musica Library");
       if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
       ){
           requestPermission();
        }
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
           requestPermission();
        }


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Library) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new LibraryFragment());
            ft.commit();
            getSupportActionBar().setTitle("Musica Library");

        } else if (id == R.id.upload) {
            FragmentTransaction ftu =getSupportFragmentManager().beginTransaction();
            ftu.replace(R.id.fragment_container, new UploadFragment());
            ftu.commit();
            getSupportActionBar().setTitle("Upload My Musica");

        } else if (id == R.id.Feedback) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new FeedbackFragment());
            ft.commit();
            getSupportActionBar().setTitle("Feedback");

        }else if(id == R.id.Logout) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            prefManager.setLoggedIn(false);
            startActivity(intent);


        }



        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }


}

package com.hemantax.musica.fragment;


import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.hemantax.musica.R;
import com.hemantax.musica.activity.MainActivity;
import com.hemantax.musica.activity.RegistrationActivity;
import com.hemantax.musica.utils.PrefManager;
import com.hemantax.musica.utils.RetrofitUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {
    PrefManager prefManager;

    @BindView(R.id.Mname)
    EditText etMname;
    @BindView(R.id.MArtist)
    EditText etMartist;
    @BindView(R.id.MGenre)
    Spinner sMgenre;
    Unbinder unbinder;
    String Username;
    RetrofitUtils.RetrofitService service;
    Uri fileuri;

    private ProgressDialog progress;

    private String TAG = this.getClass().getSimpleName();


    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prefManager=new PrefManager(getActivity());
        Username = prefManager.getUsername();

        service = RetrofitUtils.getInstance();

    }

    @OnClick({R.id.btnuploadmusic,R.id.Mpicker})
    public void onViewClicked(final View view){

        switch (view.getId()){
            case R.id.btnuploadmusic:
                String path = getAudioPath(fileuri);
                File musicFile = new File(path);


                Log.e(TAG,musicFile.toString());
                MultipartBody.Part musicPart = MultipartBody.Part.createFormData("file",musicFile.getName(),RequestBody.create(MediaType.parse("audio/*"),musicFile));
                MultipartBody.Part username = MultipartBody.Part.createFormData("username",Username,RequestBody.create(MediaType.parse("text/plain"),Username));
                MultipartBody.Part musicname = MultipartBody.Part.createFormData("musicname",etMname.getText().toString(),RequestBody.create(MediaType.parse("text/plain"),etMname.getText().toString()));
                MultipartBody.Part artistname = MultipartBody.Part.createFormData("artistname",etMartist.getText().toString(),RequestBody.create(MediaType.parse("text/plain"),etMartist.getText().toString()));
                MultipartBody.Part genre = MultipartBody.Part.createFormData("genre",sMgenre.getSelectedItem().toString(),RequestBody.create(MediaType.parse("text/plain"),sMgenre.getSelectedItem().toString()));
                service.uploadMusic(musicPart,username,musicname,artistname,genre).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_SHORT).show();
                        Log.e(TAG,response.body().toString());
                        UploadProgress(view);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG,"Failure",t);
                    }
                });



                break;
            case R.id.Mpicker:
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);
        }




        }

    private String getAudioPath(Uri uri) {
        String[] data = {MediaStore.Audio.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), uri, data, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void UploadProgress(View view){
        progress=new ProgressDialog(getActivity());
        progress.setMessage("Uploading Music");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread(){
            @Override
            public void run() {
                int jumpTime =0;

                while (jumpTime < totalProgressTime){
                    try{
                        sleep(200);
                        jumpTime+=5;
                        progress.setProgress(jumpTime);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        };
        t.start();

    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){

            if(resultCode == RESULT_OK){
                //the selected audio.
                fileuri = data.getData();
            }
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}

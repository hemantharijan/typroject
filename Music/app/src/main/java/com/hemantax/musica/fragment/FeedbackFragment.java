package com.hemantax.musica.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hemantax.musica.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FeedbackFragment extends Fragment {
    @BindView(R.id.etfeedback)
    EditText txtfeedback;
    Unbinder unbinder;


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.btnfeedbacksend})
    public void onViewClicked(final View view) {
        String feedback = txtfeedback.getText().toString();


        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hemantharijan92@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "musica feedback");
        intent.putExtra(Intent.EXTRA_TEXT, feedback);
        startActivity(Intent.createChooser(intent, "Select Application"));
        Toast.makeText(getActivity(), "Feedback Send", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}



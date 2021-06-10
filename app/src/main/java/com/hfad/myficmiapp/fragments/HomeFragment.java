package com.hfad.myficmiapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.activities.BranchActivity;
import com.hfad.myficmiapp.activities.DevotionActivity;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CardView locate = root.findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BranchActivity.class));
            }
        });

        CardView dev = root.findViewById(R.id.devt);
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DevotionActivity.class));
            }
        });

        return root;    }
}
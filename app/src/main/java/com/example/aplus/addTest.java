package com.example.aplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class addTest extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        View view= inflater.inflate(R.layout.fragment_add_test, container, false);
        ImageButton sub1=(ImageButton) view.findViewById(R.id.sub1);
        ImageButton sub2=(ImageButton)view.findViewById(R.id.sub2);
        ImageButton sub3=(ImageButton)view.findViewById(R.id.sub3);
        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MAD_ques.class);
                startActivity(intent);
            }
        });
        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),PWP_Ques.class);
                startActivity(intent);

            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),php_ques.class);
                startActivity(intent);

            }
        });

        return view;

    }

}
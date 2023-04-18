package com.example.aplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class teacher_home_frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_teacher_home_frag, container, false);
        ImageButton sub3=(ImageButton) view.findViewById(R.id.ques);
       ImageButton sub2=(ImageButton)view.findViewById(R.id.exam);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),view_ques.class);
                startActivity(intent);

            }
        });
   sub2.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               Intent intent=new Intent(getContext(),teacher_response.class);
                startActivity(intent);

           }
       });
        return view;
    }

}
package com.example.aplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class studTest extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        View view= inflater.inflate(R.layout.fragment_stud_test, container, false);
        ImageButton sub1=(ImageButton) view.findViewById(R.id.sub1);
        ImageButton sub2=(ImageButton)view.findViewById(R.id.sub2);
        ImageButton sub3=(ImageButton)view.findViewById(R.id.sub3);
        Bundle bundle =getArguments();
        String username=bundle.getString("message_key1");

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MAD_quiz.class);
                intent.putExtra("message_key2", username);
                startActivity(intent);
            }
        });
        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),PWP_quiz.class);
                intent.putExtra("message_key2", username);
                startActivity(intent);

            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),php_quiz.class);
                intent.putExtra("message_key2", username);
                startActivity(intent);

            }
        });

        return view;

    }

}
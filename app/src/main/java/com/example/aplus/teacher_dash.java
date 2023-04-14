package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class teacher_dash extends AppCompatActivity {
    ImageButton b1, b2, b3, b4;
    addTest test;
    teacher_home_frag frag = new teacher_home_frag();
    result fragre=new result();
    profile_user pro_fag=new profile_user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dash);
        test = new addTest();
        Intent intent = getIntent();
        b1 = (ImageButton) findViewById(R.id.bt1);
        b2 = (ImageButton) findViewById(R.id.bt2);
        b3 = (ImageButton) findViewById(R.id.bt3);
        b4 = (ImageButton) findViewById(R.id.bt4);
        test.setArguments(intent.getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.frag1, frag).commit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, frag).commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, test).commit();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, fragre).commit();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, pro_fag).commit();
            }
        });
    }
}
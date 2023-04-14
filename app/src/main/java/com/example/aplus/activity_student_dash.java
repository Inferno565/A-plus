package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_student_dash extends AppCompatActivity {
    stud_profile pro_fag=new stud_profile();
    ImageButton b4;
    Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);
        b4 = (ImageButton) findViewById(R.id.bt4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, pro_fag).commit();
            }
        });
    }
}
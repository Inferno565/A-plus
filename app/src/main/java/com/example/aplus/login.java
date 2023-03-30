package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class login extends AppCompatActivity {
    ImageButton visi;
    EditText ed1;
    Button btn1;
    int visiblity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        visi = (ImageButton) findViewById(R.id.visi);
        ed1 = (EditText) findViewById(R.id.pass);
        btn1=(Button)findViewById(R.id.login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),activity_student_dash.class);
                startActivity(intent);
            }
        });
        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (visiblity) {
                    case 0:
                        visi.setImageResource(R.drawable.eye);
                        ed1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        visiblity = 1;
                        break;

                    case 1:
                        visi.setImageResource(R.drawable.view);
                        ed1.setTransformationMethod(new PasswordTransformationMethod());
                        visiblity=0;
                }
            }
        });

    }
}
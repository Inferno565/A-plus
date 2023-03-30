package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class teacher_signup extends AppCompatActivity {
    ImageButton visi;
    EditText ed1;
    int visiblity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);
        visi = (ImageButton) findViewById(R.id.visi);
        ed1 = (EditText) findViewById(R.id.pass);
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
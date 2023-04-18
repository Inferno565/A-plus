package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_student_dash extends AppCompatActivity {
    stud_profile pro_fag = new stud_profile();
    student_home home = new student_home();
    studTest test = new studTest();
    stu_result fag_re = new stu_result();

    ImageButton b1, b2, b3, b4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);
        b1 = (ImageButton) findViewById(R.id.bt1);
        b2 = (ImageButton) findViewById(R.id.bt2);
     b3 = (ImageButton) findViewById(R.id.bt3);
        b4 = (ImageButton) findViewById(R.id.bt4);
        Intent intent = getIntent();
        String username = intent.getStringExtra("message_key");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, home).commit();
                intent.putExtra("message_key1", username);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("message_key1", username); // Put anything what you want
                getSupportFragmentManager().beginTransaction().replace(R.id.frag1,test).commit();
                test.setArguments(bundle);
//                Intent intent = new Intent(getActivity().getBaseContext(),
//                        studTest.class);
//                intent.putExtra("message_key1", username);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("message_key1", username); // Put anything what you want
                getSupportFragmentManager().beginTransaction().replace(R.id.frag1,fag_re).commit();
                fag_re.setArguments(bundle);
            }
       });
        b4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("message_key1", username); // Put anything what you want
                getSupportFragmentManager().beginTransaction().replace(R.id.frag1,pro_fag).commit();
                pro_fag.setArguments(bundle);
            }
        });
    }
}
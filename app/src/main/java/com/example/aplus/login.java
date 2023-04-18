package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class login extends AppCompatActivity {
    ImageButton visi;
    EditText user, pass1;
    Button btn1;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    int visiblity;
    private Cursor cursor1, cursor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        visi = (ImageButton) findViewById(R.id.visi);
        pass1 = (EditText) findViewById(R.id.pass);
        user = (EditText) findViewById(R.id.username);
        btn1 = (Button) findViewById(R.id.login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass1.getText().toString().trim();
                String username = user.getText().toString().trim();

                if (password.isEmpty() || username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter your Email , UserName and Password to login", Toast.LENGTH_SHORT).show();
                } else {
                    cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_2 + " WHERE " + "Email" + "=? AND " + "Password" + "=?", new String[]{username, password});
                    cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_3 + " WHERE " + "Email" + "=? AND " + "Password" + "=?", new String[]{username, password});
                    if (cursor1 != null || cursor2 != null) {
                        if (cursor1.getCount() > 0) {
                            Intent intent = new Intent(getApplicationContext(), activity_student_dash.class);
                       intent.putExtra("message_key", username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Sign In Successful", Toast.LENGTH_SHORT).show();

                        } else if (cursor2.getCount() > 0) {
                            Intent intent = new Intent(getApplicationContext(), teacher_dash.class);
                            intent.putExtra("message_key", username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Sign In Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (visiblity) {
                    case 0:
                        visi.setImageResource(R.drawable.eye);
                        pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        visiblity = 1;
                        break;

                    case 1:
                        visi.setImageResource(R.drawable.view);
                        pass1.setTransformationMethod(new PasswordTransformationMethod());
                        visiblity = 0;
                }
            }
        });

    }
}
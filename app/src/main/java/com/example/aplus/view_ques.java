package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class view_ques extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    //    String[] str;
    ArrayAdapter adp;
    ArrayList str = new ArrayList<>();

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ques);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        EditText ed = (EditText) findViewById(R.id.subject);
        Button bt = (Button) findViewById(R.id.view);
        ListView l1 = (ListView) findViewById(R.id.list);
        adp = new ArrayAdapter<String>(this, R.layout.activity_list, str);
        String sub = ed.getText().toString();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed.getText().toString().equals("php") || ed.getText().toString().equals("PHP") || ed.getText().toString().equals("Php")) {
                    Cursor cursor = sqLiteDatabase.rawQuery("Select Question from php ", null);
                    if (cursor.moveToFirst()) {
                        str.add(cursor.getString(0));
                        l1.setAdapter(adp);
                    }
                } else if (ed.getText().toString().equals("mad") || ed.getText().toString().equals("MAD") || ed.getText().toString().equals("Mad")) {
                    Cursor cursor = sqLiteDatabase.rawQuery("Select Question from mad ", null);
                    if (cursor.moveToFirst()) {
                        str.add(cursor.getString(0));
                        l1.setAdapter(adp);
                    }
                } else {
                    Cursor cursor = sqLiteDatabase.rawQuery("Select Question from pwp ", null);
                    if (cursor.moveToFirst()) {
                        str.add(cursor.getString(0));
                        l1.setAdapter(adp);
                    }
                }

            }
        });
    }
}


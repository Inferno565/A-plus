package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class php_quiz extends AppCompatActivity {
    public static final String TABLE_7 = "Response";

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    TextView quest, subj,sub1;
    RadioButton op1, op2, op3;
    RadioGroup radiogroup;
    Button sub, next;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_quiz);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        quest = findViewById(R.id.ques);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        sub = findViewById(R.id.submit);
        sub1 = findViewById(R.id.sub1);
        radiogroup = findViewById(R.id.radioGroup);
        next = findViewById(R.id.nex);
        subj = findViewById(R.id.sub);
        Intent intent = getIntent();
        String username = intent.getStringExtra("message_key2");
        Cursor abc1 = sqLiteDatabase.rawQuery("Select FirstName from Student where Email='" + username + "'", null);
        if (abc1.moveToFirst()) {
            sub1.setText(abc1.getString(0));
        }
        Cursor abc = sqLiteDatabase.rawQuery("Select Question,Option1,Option2,Option3 from php", null);
        //while(cursor.moveToNext())
        if (abc.moveToFirst()) {
            quest.setText(abc.getString(0));
            op1.setText(abc.getString(1));
            op2.setText(abc.getString(2));
            op3.setText(abc.getString(3));
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while (abc.moveToNext()) {
                    quest.setText(abc.getString(0));
                    op1.setText(abc.getString(1));
                    op2.setText(abc.getString(2));
                    op3.setText(abc.getString(3));
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = quest.getText().toString();
                String uname=sub1.getText().toString();
                String sub = subj.getText().toString();
                String ans = ((RadioButton) findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();
                Cursor cursor = sqLiteDatabase.rawQuery("Select Correctans from mad where Question='"+quest.getText().toString()+"'", null);
                while (cursor.moveToNext()) {
                    if (cursor.getString(0).equals(ans)) {
                        score = 1;
                    } else {
                        score = 0;
                    }
                }
                String str = "CREATE TABLE IF NOT EXISTS " + TABLE_7 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Subject TEXT,Question TEXT,Answer TEXT,Score INTEGER)";
                sqLiteDatabase.execSQL(str);
                insertData(uname, sub, ques, ans,score);
                Toast.makeText(getApplicationContext(), "Response submitted Successful", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void insertData (String uname, String sub, String ques, String ans,Integer score) {
        ContentValues values20 = new ContentValues();

        values20.put("Username", uname);
        values20.put("Subject", sub);
        values20.put("Question", ques);
        values20.put("Answer", ans);
        values20.put("Score", score);



        sqLiteDatabase.insert(TABLE_7, null, values20);
    }
}
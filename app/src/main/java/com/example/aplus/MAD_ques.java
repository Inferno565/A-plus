package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MAD_ques extends AppCompatActivity {
    public static final String TABLE_5 = "mad";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button add;
    private Cursor cursor;
    private EditText ques, op_1, op_2, op_3, corr_op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_ques);
        openHelper = new DatabaseHelper(this);
        add = findViewById(R.id.add);
        ques = findViewById(R.id.ques);
        op_1 = findViewById(R.id.op_1);
        op_2 = findViewById(R.id.op_2);
        op_3 = findViewById(R.id.op_3);
        corr_op = findViewById(R.id.corr_op);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String t2sciq = ques.getText().toString();
                String t2scio1 = op_1.getText().toString();
                String t2scio2 = op_2.getText().toString();
                String t2scio3 = op_3.getText().toString();
                String t2scicop = corr_op.getText().toString();
                String str = "CREATE TABLE IF NOT EXISTS " + TABLE_5 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if (!validateempty() | !validateans()) {
                    return;
                } else {
                    insertData(t2sciq, t2scio1, t2scio2, t2scio3, t2scicop);
                    Toast.makeText(getApplicationContext(), "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateempty() {
        String t2sciq = ques.getText().toString();
        String t2scio1 = op_1.getText().toString();
        String t2scio2 = op_2.getText().toString();
        String t2scio3 = op_3.getText().toString();
        String t2scicop = corr_op.getText().toString();
        if (t2sciq.isEmpty() || t2scio1.isEmpty() || t2scio2.isEmpty() || t2scio3.isEmpty() || t2scicop.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private Boolean validateans() {
        String t2scio1 = op_1.getText().toString();
        String t2scio2 = op_2.getText().toString();
        String t2scio3 = op_3.getText().toString();
        String t2scicop = corr_op.getText().toString();

        if (!(t2scicop.equals(t2scio1) || t2scicop.equals(t2scio2) || t2scicop.equals(t2scio3))) {
            corr_op.setError("Answer does not match the given options");
            return false;
        } else {
            corr_op.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void insertData(String t2sciq, String t2scio1, String t2scio2, String t2scio3, String t2scicop) {
        ContentValues values5 = new ContentValues();

        values5.put("Question", t2sciq);
        values5.put("Option1", t2scio1);
        values5.put("Option2", t2scio2);
        values5.put("Option3", t2scio3);
        values5.put("Correctans", t2scicop);


        sqLiteDatabase.insert(TABLE_5, null, values5);
        ques.setText("");
        op_1.setText("");
        op_2.setText("");
        op_3.setText("");
        corr_op.setText("");
    }
}
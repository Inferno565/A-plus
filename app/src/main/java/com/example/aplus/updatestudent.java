package com.example.aplus;

import static com.example.aplus.DatabaseHelper.TABLE_2;
import static com.example.aplus.DatabaseHelper.TABLE_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updatestudent extends AppCompatActivity {
    Button del;
    EditText email;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    Intent intent;
    TextView txt;
    private EditText f_name, l_name, mob_no, par_mob, pass, pass_re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatestudent);
        openHelper = new DatabaseHelper(this);
        mob_no = (EditText) findViewById(R.id.mob_no);
        email = (EditText) findViewById(R.id.email);
        Button submit = (Button) findViewById(R.id.submit);
        pass = (EditText) findViewById(R.id.pass);
        pass_re = (EditText) findViewById(R.id.pass_re);
        sqLiteDatabase = openHelper.getWritableDatabase();
        del = findViewById(R.id.del);
        txt = findViewById(R.id.txt);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Message_key2");
        email = (EditText) findViewById(R.id.email);
        email.setText(username);
        Cursor abc1 = sqLiteDatabase.rawQuery("Select FirstName from student where Email='" + email.getText().toString() + "'", null);
        while (abc1.moveToNext()) {
            txt.setText(abc1.getString(0));
        }
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor abc = sqLiteDatabase.rawQuery("Delete from student where Email='" + email.getText().toString() + "'", null);
                if (abc.getCount() == -1) {
                    Toast.makeText(getApplicationContext(), "Failed to Delete.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Successfully Deleted.", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String mob = mob_no.getText().toString();
                String passw = pass.getText().toString();
                String mail = email.getText().toString();
                String str1 = "Update " + TABLE_2 + " set Email='" + mail + "', Phone='" + mob + "', Password='" + passw + "' where FirstName='" + txt.getText().toString() + "' ";
                sqLiteDatabase.execSQL(str1);
                Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
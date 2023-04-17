package com.example.aplus;

import static com.example.aplus.DatabaseHelper.TABLE_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class teacher_signup extends AppCompatActivity {
    ImageButton visi;
    EditText f_name, l_name, email, mob_no, pass, pass_re;
    Button submit;
    int visiblity;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);
        openHelper = new DatabaseHelper(this);
        f_name = findViewById(R.id.f_name);
        l_name = findViewById(R.id.l_name);
        email = findViewById(R.id.email);
        mob_no = findViewById(R.id.mob_no);
        pass = findViewById(R.id.pass);
        pass_re = findViewById(R.id.pass_re);
        submit = findViewById(R.id.submit);
        visi = (ImageButton) findViewById(R.id.visi);
        //*********TOGGLE PASSWORD VISIBLITY*********
        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (visiblity) {
                    case 0:
                        visi.setImageResource(R.drawable.eye);
                        pass.setTransformationMethod(new PasswordTransformationMethod());
                        visiblity = 1;
                        break;

                    case 1:
                        visi.setImageResource(R.drawable.view);
                        pass.setTransformationMethod(null);
                        visiblity = 0;
                        break;
                }
            }
        });
        //*********TOGGLE PASSWORD VISIBLITY*********

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String fname = f_name.getText().toString();
                String lname = l_name.getText().toString().trim();
                String phone = mob_no.getText().toString();
                String femail = email.getText().toString();
                String val = pass.getText().toString();
                String cpass = pass_re.getText().toString();
                if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateConfirmpassword()) {
                    return;
                } else {
                    insertData(fname, lname, femail, phone, val);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }
            }
        });


    }


    private Boolean validateUsername() {
        String uname = email.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (uname.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (uname.length() >= 15) {
            email.setError("Username too long");
            return false;
        } else if (!uname.matches(noWhiteSpace)) {
            email.setError("White Spaces are not allowed");
            return false;
        } else {
            email.setError(null);
            //email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateName() {
        String fname = f_name.getText().toString();
        String lname = l_name.getText().toString().trim();

        String val = pass.getText().toString();
        if (fname.isEmpty() || lname.isEmpty()) {
//            Toast.makeText(teacher_signup.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            f_name.setError("First Name cannot be empty");
            return false;
        } else {
            f_name.setError(null);
            //firstname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String femail = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (femail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!femail.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            //email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String phone = mob_no.getText().toString();
        if (phone.isEmpty()) {
            mob_no.setError("Field cannot be empty");
            return false;
        }
        if (phone.length() < 10) {
            mob_no.setError("Enter Correct Contact Number");
            return false;
        } else {
            mob_no.setError(null);
            //regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = pass.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            pass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass.setError("Password is too weak");
            return false;
        } else {
            pass.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmpassword() {
        String cpass = pass_re.getText().toString();
        String val = pass.getText().toString();
        if (!cpass.equals(val)) {
            pass_re.setError("Password did not match");
            return false;
        } else {
            pass_re.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void insertData(String fname, String lname, String femail, String phone, String val) {
        ContentValues values3 = new ContentValues();

        values3.put("FirstName", fname);
        values3.put("LastName", lname);
        values3.put("Phone", phone);
        values3.put("Email", femail);
        values3.put("Password", val);

        sqLiteDatabase.insert(TABLE_3, null, values3);
    }

}
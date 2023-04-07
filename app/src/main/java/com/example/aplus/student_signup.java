package com.example.aplus;

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

public class student_signup extends AppCompatActivity {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    int visiblity ;
    ImageButton visi;
    private EditText f_name, l_name, class2, email, mob_no, pass, pass_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
//        openHelper = new DatabaseHelper(this);
        Button login = findViewById(R.id.login);
        f_name = findViewById(R.id.f_name);
        l_name = findViewById(R.id.l_name);
//        class2 = findViewById(R.id.class2);
        mob_no = findViewById(R.id.mob_no);
        email = findViewById(R.id.email);
//        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        pass_re = findViewById(R.id.pass_re);

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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String uname = email.getText().toString();
                String fname = f_name.getText().toString();
                String lname = l_name.getText().toString().trim();
                String std = class2.getText().toString().trim();
                String phone = mob_no.getText().toString();
                String femail = email.getText().toString();
                String val = pass.getText().toString();
                String cpass = pass_re.getText().toString();
                if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateConfirmpassword()) {
                    return;
                }
//                else
//                {
////                    insertData(fname,lname,std,phone,femail,uname,val);
////                    Toast.makeText(RegisterStudent.this, "Registration Successful", Toast.LENGTH_SHORT).show();
////                    Intent intent= new Intent(RegisterStudent.this, Login.class);
////                    startActivity(intent);
//                }
            }
        });

    }
//    private Boolean validateUsername()
//    {
//        String uname = email.getText().toString();
//        String noWhiteSpace = "\\A\\w{4,20}\\z";
//        if (uname.isEmpty())
//        {
//            email.setError("Field cannot be empty");
//            return false;
//        }
//        else if (uname.length() >= 15)
//        {
//            email.setError("Username too long");
//            return false;
//        }
//        else if (!uname.matches(noWhiteSpace))
//        {
//            email.setError("White Spaces are not allowed");
//            return false;
//        }
//        else
//        {
//            email.setError(null);
//            //username.setErrorEnabled(false);
//            return true;
//        }
//    }

    private Boolean validateName() {
        String fname = f_name.getText().toString();
        String lname = l_name.getText().toString().trim();

        String val = pass.getText().toString();
        if (fname.isEmpty() || lname.isEmpty()) {
            Toast.makeText(student_signup.this, "Please fill all the details", Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
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
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
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


    public void insertData(String fname, String lname, String std, String phone, String femail, String uname, String val) {
        //SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //sqLiteDatabase=SQLiteOpenHelper.g
        //ContentValues values1 = new ContentValues();
//        ContentValues values2 = new ContentValues();
//
//        values2.put("Firstname",fname);
//        values2.put("LastName",lname);
//        values2.put("Class",std);
//        values2.put("Phone",phone);
//        values2.put("Email",femail);
//        values2.put("Username",uname);
//        values2.put("Password",val);
//        sqLiteDatabase.insert(TABLE_2,null,values2);

    }
}
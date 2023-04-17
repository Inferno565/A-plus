package com.example.aplus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class profile_user extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private TextView email, contact, pass;
    TextView user;
    private Button logout, change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        View rootview = inflater.inflate(R.layout.fragment_profile_user, container, false);
        email = (TextView) rootview.findViewById(R.id.View_email);
        contact = (TextView) rootview.findViewById(R.id.view_contact);
        pass = (TextView) rootview.findViewById(R.id.view_pass);
        user = (TextView) rootview.findViewById(R.id.useText);
        openHelper = new DatabaseHelper(getContext());
        sqLiteDatabase = openHelper.getReadableDatabase();
        Cursor abc = sqLiteDatabase.rawQuery("Select * from teacher where LastName='" + user.getText().toString() + "'", null);
        if (abc.moveToFirst()) {
            do {
                email.setText("Email:" + abc.getString(2));
                contact.setText("Phone no:" + abc.getString(3));
                pass.setText("Password:" + abc.getString(4));
            }while (abc.moveToNext());
        }
        return inflater.inflate(R.layout.fragment_profile_user, container, false);

    }
}
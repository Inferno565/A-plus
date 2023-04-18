package com.example.aplus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class stud_profile extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private TextView email, contact, pass;
    TextView user;
    private Button logout, edit;
    private ImageButton change;

    ImageView dp;
    private static final int rqcode = 123;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        View rootview = inflater.inflate(R.layout.fragment_stud_profile, container, false);
        email = (TextView) rootview.findViewById(R.id.View_email);
        contact = (TextView) rootview.findViewById(R.id.view_contact);
        pass = (TextView) rootview.findViewById(R.id.view_pass);
        user = (TextView) rootview.findViewById(R.id.useText);
        logout = (Button) rootview.findViewById(R.id.signout);
        dp = (ImageView) rootview.findViewById(R.id.imagebg);
        change = (ImageButton) rootview.findViewById(R.id.i1);
        edit=(Button)rootview.findViewById(R.id.edit);
        openHelper = new DatabaseHelper(getContext());
        sqLiteDatabase = openHelper.getReadableDatabase();
        Bundle bundle = getArguments();
        String username = bundle.getString("message_key1");
        email.setText(username);
        Cursor abc = sqLiteDatabase.rawQuery("Select * from student where Email='" + email.getText().toString() + "'", null);
        if (abc.moveToFirst()) {
            do {
                user.setText(abc.getString(1));
                user.append(" " + abc.getString(2));
                contact.setText(abc.getString(4));
                pass.setText(abc.getString(5));
            } while (abc.moveToNext());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, rqcode);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), updatestudent.class);
                intent.putExtra("Message_key2",username);
                startActivity(intent);
            }
        });
        return rootview;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == rqcode) {
            Bitmap pp = (Bitmap) data.getExtras().get("data");
            dp.setImageBitmap(pp);
        }

    }
}

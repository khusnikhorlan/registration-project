package com.example.a6lesson.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a6lesson.R;
import com.example.a6lesson.database.StoreDatabaase;

import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_CITY;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_EMAIL;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_NAME;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_PASSWORD;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_PHONE_NUMBER;
import static com.example.a6lesson.database.StoreDatabaase.TABLE_USERS;

public class user_details extends AppCompatActivity {
TextView name,email,number,city;

    private StoreDatabaase storeDb;
    private SQLiteDatabase sqdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name=findViewById(R.id.username);
        email=findViewById(R.id.useremail);
        number=findViewById(R.id.usernumber);
        city=findViewById(R.id.usercity);

        storeDb = new StoreDatabaase(this);
        sqdb = storeDb.getWritableDatabase();

        Bundle bundle= getIntent().getExtras();


        String emaill = bundle.getString("et_email");

        Cursor userCursor = sqdb.rawQuery("SELECT * FROM " + TABLE_USERS +
                        " WHERE " + COLOUMN_USER_EMAIL+ " =?  ", new String[]{emaill} );

        if (((userCursor != null) && (userCursor.getCount() > 0))) {
            userCursor.moveToFirst();
            String userName = userCursor.getString(userCursor.getColumnIndex(COLOUMN_USER_NAME));
            String numberr = userCursor.getString(userCursor.getColumnIndex(COLOUMN_USER_PHONE_NUMBER));
            String cityy = userCursor.getString(userCursor.getColumnIndex(COLOUMN_USER_CITY));

            Toast.makeText(this, "User tabyldy! Welcome"+userName, Toast.LENGTH_SHORT).show();

            number.setText(numberr);
            email.setText(emaill);
            city.setText(cityy);
        }else{
            Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
        }



    }
}
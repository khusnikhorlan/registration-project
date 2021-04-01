package com.example.a6lesson.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a6lesson.R;
import com.example.a6lesson.database.StoreDatabaase;

import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_EMAIL;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_NAME;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_PASSWORD;
import static com.example.a6lesson.database.StoreDatabaase.TABLE_USERS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private StoreDatabaase storeDb;
    private SQLiteDatabase sqdb;

    EditText et_email;
    EditText et_password;
    Button btn_submit;
    Button btn_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        storeDb = new StoreDatabaase(this);
        sqdb = storeDb.getWritableDatabase();

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);
        btn_password = findViewById(R.id.btn_password);

        btn_submit.setOnClickListener(this);
        btn_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(et_email.getText())){
            et_email.setError("Fill again");
            return;
        }

        if (TextUtils.isEmpty(et_password.getText())){
            et_password.setError("Fill again");
            return;
        }



        Cursor userCursor = sqdb.rawQuery("SELECT * FROM " + TABLE_USERS +
                  " WHERE " + COLOUMN_USER_EMAIL+ " =? AND " +COLOUMN_USER_PASSWORD + " =? ",

                  new String[]{et_email.getText().toString(), et_password.getText().toString()} );



        if (((userCursor != null) && (userCursor.getCount() > 0))) {
            userCursor.moveToFirst();
            String userName = userCursor.getString(userCursor.getColumnIndex(COLOUMN_USER_NAME));
            Toast.makeText(this, "User tabyldy! Welcome"+userName, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(LoginActivity.this, user_details.class);
            i.putExtra("et_email", et_email.getText().toString());

            startActivity(i);

        }else{
            Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
        }
    }
}
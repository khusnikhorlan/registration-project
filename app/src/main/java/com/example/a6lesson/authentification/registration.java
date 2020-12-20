package com.example.a6lesson.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a6lesson.R;
import com.example.a6lesson.database.StoreDatabaase;

import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_CITY;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_EMAIL;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_NAME;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_PASSWORD;
import static com.example.a6lesson.database.StoreDatabaase.COLOUMN_USER_PHONE_NUMBER;
import static com.example.a6lesson.database.StoreDatabaase.TABLE_USERS;

public class registration extends AppCompatActivity implements View.OnClickListener {

    private StoreDatabaase storeDb;
    private SQLiteDatabase sqdb;

    EditText et_username;
    EditText et_email;
    EditText et_password;
    EditText et_city;
    EditText et_number;
    Button btn_submit;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        storeDb = new StoreDatabaase(this);
        sqdb = storeDb.getWritableDatabase();

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_number = findViewById(R.id.et_number);
        et_city = findViewById(R.id.et_city);
        btn_submit = findViewById(R.id.btn_submit);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_username.getText().toString();
                String email=et_email.getText().toString();
                String num=et_number.getText().toString();
                String city=et_city.getText().toString();


                Intent  i = new Intent(registration.this,user_details.class);
                i.putExtra("et_username",name);
                i.putExtra("et_email",email);
                i.putExtra("et_number",num);
                i.putExtra("et_city",city);

                startActivity(i);
            }
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:

                if (TextUtils.isEmpty(et_username.getText())){
                    et_username.setError("Fill again");
                    return;
                }

                if (TextUtils.isEmpty(et_email.getText())){
                    et_email.setError("Fill again");
                    return;
                }

                if (TextUtils.isEmpty(et_password.getText())){
                    et_password.setError("Fill again");
                    return;
                }

                if (TextUtils.isEmpty(et_number.getText())){
                    et_number.setError("Fill again");
                    return;
                }

                if (TextUtils.isEmpty(et_city.getText())){
                    et_city.setError("Fill again");
                    return;
                }

                ContentValues versionValues = new ContentValues();
                versionValues.put(COLOUMN_USER_NAME, et_username.getText().toString());
                versionValues.put(COLOUMN_USER_EMAIL, et_email.getText().toString());
                versionValues.put(COLOUMN_USER_PASSWORD, et_password.getText().toString());
                versionValues.put(COLOUMN_USER_PHONE_NUMBER, et_number.getText().toString());
                versionValues.put(COLOUMN_USER_CITY, et_city.getText().toString());

                sqdb.insert(TABLE_USERS,null,versionValues);
                Toast.makeText(this,"Database user engizildi",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case  R.id.btn_login:

                Intent intent2 = new Intent(this,LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
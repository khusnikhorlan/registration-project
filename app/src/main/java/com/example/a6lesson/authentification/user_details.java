package com.example.a6lesson.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a6lesson.R;

public class user_details extends AppCompatActivity {
TextView name,email,number,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name=findViewById(R.id.username);
        email=findViewById(R.id.useremail);
        number=findViewById(R.id.usernumber);
        city=findViewById(R.id.usercity);


        Bundle bundle= getIntent().getExtras();

        String namee = bundle.getString("et_username");
        String emaill = bundle.getString("et_email");
        String numberr = bundle.getString("et_number");
        String cityy = bundle.getString("et_city");

        number.setText(numberr);
        email.setText(emaill);
        name.setText(namee);
        city.setText(cityy);
    }
}
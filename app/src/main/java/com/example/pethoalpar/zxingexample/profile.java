package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    String MyPREFERENCES="MyPrefs";
    SharedPreferences sharedPreferences;
    TextView username_display;
    TextView useremail_display;
    TextView userid_display;

    String username[],email,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username_display=(TextView)findViewById(R.id.textView3);
        useremail_display=(TextView)findViewById(R.id.textView4);
        userid_display=(TextView)findViewById(R.id.textView5);
        email=sharedPreferences.getString("email",null);
      //  username=sharedPreferences.getString("username",null);
        userid=sharedPreferences.getString("userid",null);
        username = email.split("@");
         username_display.setText("UserName :"+username[0]);
        useremail_display.setText("Email ID :"+email);
           userid_display.setText("User ID  :"+userid);

    }
}

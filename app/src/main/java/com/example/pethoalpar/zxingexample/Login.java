package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Login extends AppCompatActivity {
    private Button button,button_signUp;
    private TextView ed,pa;
    String uname,pass,msg,status,userid;
    private TextView username_display;
    private TextView useremail_display;
    String MyPREFERENCES = "MyPrefs";
    SharedPreferences.Editor editor;
    ProgressView circularProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress2);
        circularProgressBar.setVisibility(View.INVISIBLE);
        button = (Button) this.findViewById(R.id.button_login);
        button_signUp = (Button) this.findViewById(R.id.button_signUp);
        username_display=(TextView)findViewById(R.id.username_Display);
        useremail_display=(TextView)findViewById(R.id.userEmail_Display);
        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
         editor=sharedPreferences.edit();
    }
    public void login(View v){
         ed = (EditText) findViewById(R.id.email);
         pa = (EditText) findViewById(R.id.password);
        uname = ed.getText().toString();
        pass = pa.getText().toString();
        ed.setVisibility(View.INVISIBLE);
        pa.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        button_signUp.setVisibility(View.INVISIBLE);
        circularProgressBar.setVisibility(View.VISIBLE);

        (new conn()).execute();
    }
    public  void signup(View v){
        Intent intent=new Intent(Login.this,SignUp.class);
        startActivity(intent);

    }
    private class conn extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... strings) {
            String url = "http://qresponse.comlu.com/auth.php?email="+uname+"&pass="+pass;
            JSONParser j = new JSONParser();
            JSONObject jobj = j.getJSONFromUrl(url);
            try {
                Iterator<String> it = jobj.keys();
                if(it.hasNext()) {
                    userid = it.next().toString();
                    JSONObject ms = jobj.getJSONObject(userid);
                    status = ms.getString("status");
                    if(status.equals("1")) {
                        msg = ms.getString("email");
                        Intent intent=new Intent(Login.this,NavigationDrawer.class);
                       // intent.putExtra("email",msg);
                        editor.putString("email",msg);
                        editor.putString("userid",userid);
                        editor.commit();
                        startActivity(intent);
                    } else {
                        msg = ms.getString("msg");
                    }
                }
            } catch (JSONException e) {
                //e.printStackTrace();
                Toast.makeText(Login.this,userid+" "+msg,Toast.LENGTH_LONG).show();
            }
            return msg;
        }
        @Override
        protected void onPostExecute(String strFromDoInBg) {
            if("Wrong Detail.".equalsIgnoreCase(msg)){
                ed.setVisibility(View.VISIBLE);
                pa.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button_signUp.setVisibility(View.VISIBLE);
                circularProgressBar.setVisibility(View.INVISIBLE);
            }
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
}

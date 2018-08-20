package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Show_QR_Data extends AppCompatActivity {
    private String qr;
    TextView textView;
    String json,userid;
    StringBuilder sb;
    String MyPREFERENCES="MyPrefs";
    SharedPreferences sharedPreferences;
    ProgressView circularProgressBar;
    static InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__qr__data);
        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress2);
        circularProgressBar.setVisibility(View.VISIBLE);
       textView=(TextView)findViewById(R.id.textView);
        Bundle bundle=getIntent().getExtras();
         qr=bundle.getString("QR_Result");
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userid=sharedPreferences.getString("userid",null);
        (new conn()).execute();

    }
    private class conn extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... strings) {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://qresponse.comlu.com/validateQR.php");
                    List<NameValuePair> pairs = new ArrayList<>();
                    pairs.add(new BasicNameValuePair("codeS",qr));
                    pairs.add(new BasicNameValuePair("id",userid));
                    httpPost.setEntity(new UrlEncodedFormEntity(pairs));

                    HttpResponse httpResponse = null;
                    httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    //    Log.i("ss", line);
                    }
                    is.close();
                    json = sb.toString();
                } catch (Exception e) {
                }
                return json;
            }
        @Override
        protected void onPostExecute(String strFromDoInBg) {
           // Toast.makeText(getBaseContext(),"Message:"+qr, Toast.LENGTH_LONG).show();
          //  textView.setText(json);
            Toast.makeText(getBaseContext(),"Message:"+json, Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Show_QR_Data.this,NavigationDrawer.class);
            startActivity(intent);
        }
    }
}

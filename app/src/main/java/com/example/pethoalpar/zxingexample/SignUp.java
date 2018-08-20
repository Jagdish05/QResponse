package com.example.pethoalpar.zxingexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class SignUp extends AppCompatActivity {
    String username,contact,add,email,pass;
    String json;
    static InputStream is = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //public void toLogIn(View v)
    {
      //  Intent i = new Intent(SignUp.this,Login.class);
        //startActivity(i);
    }
    public void register1(View v){
        EditText euser = (EditText) findViewById(R.id.input_email);
        EditText user_name = (EditText) findViewById(R.id.input_Usernam);
        EditText epass = (EditText) findViewById(R.id.input_password);
        EditText econtact = (EditText) findViewById(R.id.contact);
        email=euser.getText().toString();
        contact=econtact.getText().toString();
        username=user_name.getText().toString();
        pass=epass.getText().toString();
       (new conn()).execute();
    }
   private class conn extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... strings) {
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://qresponse.comlu.com/insert.php");
                List<NameValuePair> pairs = new ArrayList<>();
                pairs.add(new BasicNameValuePair("name",username));
                pairs.add(new BasicNameValuePair("pass",pass));
                pairs.add(new BasicNameValuePair("contact",contact));
                pairs.add(new BasicNameValuePair("email",email));
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
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                    Log.i("ss", line);
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
            }
            return json;
        }
        @Override
        protected void onPostExecute(String strFromDoInBg) {
            Toast.makeText(SignUp.this,json,Toast.LENGTH_LONG).show();

        }

    }
}

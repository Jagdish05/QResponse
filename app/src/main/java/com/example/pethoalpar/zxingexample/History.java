package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class History extends AppCompatActivity {
    String msg,status,userid;
    String[] foods={"Bacon","Ham","Tuna","Candy","Meatball","Potato"};
    List<String> list = new ArrayList<String>();
    //private static String[] NAMES = new String[]{"Abusment", "Corruption", "Environment", "General Crime", "Govt. Service"};
    String MyPREFERENCES="MyPrefs";
    ProgressView circularProgressBar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress2);
        circularProgressBar.setVisibility(View.VISIBLE);
//=======================//ListView//===============================//
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userid=sharedPreferences.getString("userid",null);
        (new conn()).execute();

    }
    private class conn extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... strings) {
            String url = "http://qresponse.comlu.com/history.php?id="+userid;
            String temp = "";
            JSONParser j = new JSONParser();
            JSONObject jobj = j.getJSONFromUrl(url);
            try {
                Iterator<String> it = jobj.keys();
                //temp = it.next().toString();
                if(it.hasNext()) {
                    JSONObject in = jobj.getJSONObject(userid);
                    Iterator<String> it2 = in.keys();

                    String date,entry,exit,hour;

                    while(it2.hasNext()) {
                        JSONObject in2 = in.getJSONObject(it2.next().toString());
                        status = in2.getString("status");

                        if (status.equals("1")) {
                            msg = "All record fetch";
                            //list.add(in2.getString("date")+"_"+ in2.getString("entry")+"_"+ in2.getString("exit")+"_"+ in2.getString("hour"));
                            date = in2.getString("date");
                            entry = in2.getString("entry");
                            exit = in2.getString("exit");
                            hour = in2.getString("hour");
                            list.add(in2.getString("date")+"_"+ in2.getString("entry")+"_"+ in2.getString("exit")+"_"+ in2.getString("hour"));
                        } else {
                            msg = in2.getString("msg");
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(History.this,userid+" "+msg,Toast.LENGTH_LONG).show();

            }
            return msg;
        }
        @Override
        protected void onPostExecute(String strFromDoInBg) {
            circularProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
            if(!msg.equalsIgnoreCase("No Information.")) {
                ListAdapter Jagdishadapter = new CustomAdapter(History.this, list);
                ListView JagdishlistView = (ListView) findViewById(R.id.listView);
                JagdishlistView.setAdapter(Jagdishadapter);

            }
            /*
            JagdishlistView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String food=String.valueOf(parent.getItemAtPosition(position));
                            Toast.makeText(History.this,msg,Toast.LENGTH_SHORT).show();
                        }
                    }
            );
  */
        }

    }
}

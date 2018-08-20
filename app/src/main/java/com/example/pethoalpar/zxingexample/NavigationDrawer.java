package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences.Editor editor;
    String MyPREFERENCES="MyPrefs";
    SharedPreferences sharedPreferences;
    TextView username_Display;
    TextView useremail_Display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
         sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        String email=sharedPreferences.getString("email",null);
        String username[] = email.split("@");
        char Char=email.charAt(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//================//For User Name Display//=============//
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        useremail_Display=(TextView)header.findViewById(R.id.userEmail_Display);
        useremail_Display.setText(email);
        username_Display=(TextView)header.findViewById(R.id.username_Display);
        username_Display.setText(username[0]);
     //   String a = "#00A650";
       // ColorDrawable colorDrawable  = new ColorDrawable(Color.parseColor(a));
       // getSupportActionBar().setBackgroundDrawable(colorDrawable);
        TextDrawable drawable1 = TextDrawable.builder().buildRound(String.valueOf(Character.toUpperCase(Char)),Color.CYAN);
        ImageView image1 = (ImageView)header.findViewById(R.id.user_profile);
        image1.setImageDrawable(drawable1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
            Intent intent=new Intent(NavigationDrawer.this,profile.class);
            startActivity(intent);
        } else if (id == R.id.Entry_Exit) {
            Intent intent=new Intent(NavigationDrawer.this,QR_Generator.class);
            startActivity(intent);
        } else if (id == R.id.History) {
            Intent intent=new Intent(NavigationDrawer.this,History.class);
            startActivity(intent);

        } else if (id == R.id.Logout) {
            Intent intent=new Intent(NavigationDrawer.this,Logout.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.dhiraj.dreamyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FourthActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DatabaseHelper myDb;
    TableControllerCategory tDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        myDb = new DatabaseHelper(this);
        tDb = new TableControllerCategory(this);

        Button buttonCreateCategory = (Button) findViewById(R.id.buttonCreateCategory);
        buttonCreateCategory.setOnClickListener(new OnClickListenerCreateCategory());

       // Toast.makeText(FourthActivity.this, "before countrecord", Toast.LENGTH_SHORT).show();
        countRecords();
        readRecords();
  //      countentrytemp();
    }


    public void countRecords() {
        int recordCount = new DatabaseHelper(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        //Toast.makeText(FourthActivity.this, "in countrecord", Toast.LENGTH_SHORT).show();
        textViewRecordCount.setText(recordCount + " Categories found.");
    }
/*
    public void countentrytemp() {
        int recordCount = new DatabaseHelper(this).countentrytemp();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        //Toast.makeText(FourthActivity.this, "in countrecord", Toast.LENGTH_SHORT).show();
        textViewRecordCount.setText(recordCount + " Categories found.");
    }*/

    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectCategory> category = new TableControllerCategory(this).read();

        if (category.size() > 0) {

            for (ObjectCategory obj : category) {

                int id = obj.id;
                String categoryName = obj.cat_name;
                int categoryType = obj.cat_type;
                int categoryPoint = obj.cat_point;

                //String textViewContents = categoryName + "("+categoryPoint+") - " + categoryType;
                String textViewContents = id+")"+categoryName+ "("+categoryPoint+") -"+categoryType;
                TextView textViewCategoryItem= new TextView(this);
                textViewCategoryItem.setPadding(0, 10, 0, 10);
                textViewCategoryItem.setText(textViewContents);
                textViewCategoryItem.setTag(Integer.toString(id));

                textViewCategoryItem.setOnLongClickListener(new OnLongClickListenerCategoryRecord());

                linearLayoutRecords.addView(textViewCategoryItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

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
        getMenuInflater().inflate(R.menu.fourth, menu);
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

        if (id == R.id.nav_camera) {
            Intent intent = new Intent("com.dhiraj.dreamyou.ThirdActivity");
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent("com.dhiraj.dreamyou.FifthActivity");
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent("com.dhiraj.dreamyou.SixthActivity");
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent("com.dhiraj.dreamyou.FourthActivity");
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

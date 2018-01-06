package com.dhiraj.dreamyou;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

import java.util.List;

public class SixthActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
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


       /* Button buttonCreateTodaycat = (Button) findViewById(R.id.buttonCreateTodaycat);
        buttonCreateTodaycat.setOnClickListener(new OnClickListenerCreateTodaycat());*/

         myDb = new DatabaseHelper(this);
        Cursor res = myDb.getListSummaryTableData();
        StringBuffer buffer = new StringBuffer();
        if(res.getCount() == 0) {
            //show msg
            showMessage("Error", "No data found!");
            return;
        } else {
            //  countRecords;
            String recordCount = null;
            while (res.moveToNext()) {
                //buffer.append("highest final score ever :"+ res.getString(0)+"\n");
                recordCount = res.getString(0);
            }

            TextView textViewRecordCount = (TextView) findViewById(R.id.textViewSummaryRecordCount);
            textViewRecordCount.setText(recordCount + " records found.");
        }


        readSummaryRecords();
    }

    public void readSummaryRecords() {

        LinearLayout linearLayoutSummaryRecords = (LinearLayout) findViewById(R.id.linearLayoutSummaryRecords);
        linearLayoutSummaryRecords.removeAllViews();

        List<ObjectSummary> summary = new DatabaseHelper(this).readSummary();

        if (summary.size() > 0) {

            for (ObjectSummary obj : summary) {

                int id = obj.id;
                long summaryDate = obj.date;
                int summaryTodayscore = obj.today_score;
                int summaryFinalscore = obj.final_score;
                int summaryDateonly = obj.date_only;

                String textViewContents = "("+ summaryDateonly + ") - ("+ summaryTodayscore + ") - (" + summaryFinalscore + ")["+summaryDate+"]";

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                //textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                linearLayoutSummaryRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutSummaryRecords.addView(locationItem);
        }

    }



    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
        getMenuInflater().inflate(R.menu.sixth, menu);
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

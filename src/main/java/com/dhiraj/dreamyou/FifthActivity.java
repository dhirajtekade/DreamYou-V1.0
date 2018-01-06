package com.dhiraj.dreamyou;

import android.database.Cursor;
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
import android.content.Intent;
import android.graphics.Color;
//import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.DefaultLabelFormatter;
//import android.icu.util.Calendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.Date;

public class FifthActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    // SimpleDateFormat displaysdf = new SimpleDateFormat("yyyy-M-Y");
    SimpleDateFormat displaysdfd = new SimpleDateFormat("dd");
    SimpleDateFormat displaysdfm = new SimpleDateFormat("MMM");
    SimpleDateFormat displaysdfy = new SimpleDateFormat("yy");

    private static Button button_todayscore;
    private static Button button_list;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
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



        GraphView graphView = (GraphView) findViewById(R.id.sgraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getSummaryDataPoint());
        graphView.addSeries(series);
        Integer v = getSummaryDataPoint().length;
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
        series.setColor(Color.rgb(226,91,36));
        series.setThickness(8);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(40,95,226,156));
        series.setDrawDataPoints(true);

/*bound*/
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1514745000);//1514745000//1514658600
        graphView.getViewport().setMaxX(1515004200);//20171022
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(-50000);
        graphView.getViewport().setMaxY(5000);


        graphView.getViewport().setScrollable(true); //for scrolling
        graphView.getViewport().setScrollableY(true);

        graphView.getViewport().setScalable(true); //for zooming
        graphView.getViewport().setScalableY(true);



        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    long valueAslong = (long) ((value+3600)*1000);
                    String valueAsString =String.valueOf(valueAslong);
                    Date valueDate;
                    cal.setTimeInMillis(valueAslong);
                        String newDateStringdmy = DateFormat.format("\ndd\nMMM\nyyyy", cal).toString();

                    //String test = String.valueOf(value);

                    return newDateStringdmy;
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        onClickButtonListener2();
        summaryInfo();
    }


    public void summaryInfo() {
       TextView summaryDataInfo = (TextView) findViewById(R.id.summaryInfo);
       // summaryDataInfo.addTextChangedListener("fff");
        summaryDataInfo.setText("*Summary Data*");

        Cursor res = new DatabaseHelper(this).getSummaryTableData();
        if(res.getCount() == 0) {
            summaryDataInfo.append("\nNo data found");
            return;
        }

        while(res.moveToNext()) {
            //badge:
            String Badge;
            int finalval = Integer.parseInt(res.getString(3));
            if(finalval < -300000) { Badge = "7th NARAK \n";}
            else if(finalval < -200000) {Badge = " 6th NARAK \n";}
            else if(finalval < -150000) {Badge = " 5th NARAK \n";}
            else if(finalval < -100000) {Badge = " 4th NARAK \n";}
            else if(finalval < -90000) {Badge = " 3rd NARAK \n";}
            else if(finalval < -70000) {Badge = " 2nd NARAK \n";}
            else if(finalval < -60000) {Badge = " 1st NARAK \n";}
            else if(finalval < -50000) {Badge = " RAKSHASH \n";}
            else if(finalval < -10000) {Badge = " DANAV\n";}
            else if(finalval < -5000) {Badge = " GHOR PAPI\n";}
            else if(finalval < -1000) {Badge = " SINNER\n";}
            else if(finalval < -5) {Badge = " ANIMAL\n";}
            else if(finalval < 1000) {Badge = " IGNORANT\n";}
            else if(finalval < 5000) {Badge = " NORMAL\n";}
            else if(finalval < 9000) {Badge = " DHARMIK\n";}
            else if(finalval < 10000) {Badge = " SEEKER\n";}
            else if(finalval < 15000) {Badge = " ENTHUSIASTIC\n";}
            else if(finalval < 20000) {Badge = " PUNYAWAN\n";}
            else if(finalval < 30000) {Badge = " NEW BEE\n";}
            else if(finalval < 35000) {Badge = " MUMUKSH\n";}
            else if(finalval < 50000) {Badge = " MAHATMA\n";}
            else if(finalval < 90000) {Badge = " MBA BHAI\n";}
            else if(finalval < 120000) {Badge = " APTAKUMAR\n";}
            else if(finalval < 150000) {Badge = " APTAPUTRA\n";}
            else if(finalval < 300000) {Badge = " UPADHYAY\n";}
            else if(finalval < 400000) {Badge = " ACHARYA\n";}
            else if(finalval < 500000) {Badge = " DADA\n";}
            else if(finalval < 600000) {Badge = " SWAMI\n";}
            else if(finalval < 700000) {Badge = " ARIHANT\n";}
            else {Badge = " MOKSHA\n";}
            //  buffer.append("Id :"+ res.getString(0)+"\n");
            //  buffer.append("todaycatid :"+ res.getString(1)+"\n");
            summaryDataInfo.append("\nBadge : "+Badge);

            /*ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat simpledateformat = new SimpleDateFormat("ddMMMyyyy");
            Date stringDate = simpledateformat.parse(res.getString(4), pos);*/

            String stringDate = DateFormat.format("dd MMM yyyy", cal).toString();

            summaryDataInfo.append("Last RecordDate : "+ stringDate);
            summaryDataInfo.append("\nLast TodayScore : "+res.getString(2)+"");
            summaryDataInfo.append("\nFinal Score : "+res.getString(3)+"");
        }

        Cursor res2 = new DatabaseHelper(this).getHighestFinalScore();
        if(res2.getCount() == 0) { summaryDataInfo.append("\nNo finalscore found!"); }
        while(res2.moveToNext()) { summaryDataInfo.append("\nHighest Finalscore ever : "+ res2.getString(0)+""); }

        Cursor res3 = new DatabaseHelper(this).getHighestTodayScore();
        if(res3.getCount() == 0) { summaryDataInfo.append("\nNo highest Todayscore found!"); }
        while(res3.moveToNext()) { summaryDataInfo.append("\nHighest Todayscore ever : "+ res3.getString(0)+""); }

        Cursor res4 = new DatabaseHelper(this).getLowestFinalScore();
        while(res4.moveToNext()) { summaryDataInfo.append("\nLowest Finalscore ever : "+ res4.getString(0)+""); }

    }



    public void onClickButtonListener2() {
        button_todayscore = (Button)findViewById(R.id.button_add);
        button_todayscore.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Toast.makeText(SecondActivity.this, "2nd", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent("com.dhiraj.dreamyou.ThirdActivity");
                        startActivity(intent);
                    }
                }
        );
        button_list = (Button) findViewById(R.id.list);
        button_list.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.dhiraj.dreamyou.SixthActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    private DataPoint[] getDataPoint() {
//just for testing
        DataPoint[] values = new DataPoint[] {
                new DataPoint(1514745000,-10002),
                new DataPoint(1514831400,-502),
                new DataPoint(1514917800,-11),
                new DataPoint(1515004200,88)

        };
        return values;
    }
    private DataPoint[] getSummaryDataPoint() {
       // Cursor res = myDb.getAllSummaryDayWise();
        Cursor res = new DatabaseHelper(this).getAllSummaryDayWise();
        DataPoint[] dp = new DataPoint[res.getCount()];
        for(int i=0; i<res.getCount(); i++) {
            res.moveToNext();
            dp[i] = new DataPoint(res.getLong(0), res.getInt(1));
        }
        return dp;
/*

        DataPoint[] values = new DataPoint[] {
                new DataPoint(1511977210,-10002),
                new DataPoint(1512063610,-502),
                new DataPoint(1512150010,-11),
                new DataPoint(1512236410,88)

        };
        return values;*/
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
        getMenuInflater().inflate(R.menu.fifth, menu);
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

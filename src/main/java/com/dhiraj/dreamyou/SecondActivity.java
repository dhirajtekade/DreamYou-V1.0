package com.dhiraj.dreamyou;


import android.content.Intent;
import android.graphics.Color;
//import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.DefaultLabelFormatter;
//import android.icu.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.Date;


/**
 * Created by Dhiraj on 12/19/2017.
 */

public class SecondActivity extends AppCompatActivity {

/*
    Calendar calendar = Calendar.getInstance();
    Date d1 = calendar.getTime();
    calendar.add(Calendar.DATE, 1);
    Date d2 = calendar.getTime();
    calendar.add(Calendar.DATE, 1);
    Date d3 = calendar.getTime();*/
Calendar cal = Calendar.getInstance();
   // Date d1 = calendar.getTime();


SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    // SimpleDateFormat displaysdf = new SimpleDateFormat("yyyy-M-Y");
    SimpleDateFormat displaysdfd = new SimpleDateFormat("dd");
    SimpleDateFormat displaysdfm = new SimpleDateFormat("MMM");
    SimpleDateFormat displaysdfy = new SimpleDateFormat("yy");

    private static Button button_todayscore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        GraphView graphView = (GraphView) findViewById(R.id.sgraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        series.setColor(Color.rgb(226,91,36));
        series.setThickness(8);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(40,95,226,156));
        series.setDrawDataPoints(true);


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    long valueAslong = (long) (value);
                    String valueAsString =String.valueOf(valueAslong);
                    Date valueDate;
                    /* valueDate = sdf.parse(valueAsString);
                     //  String newDateString = sdf.format(valueDate);
                     String newDateStringd = displaysdfd.format(valueDate);
                     String newDateStringm = displaysdfm.format(valueDate);
                     String newDateStringy = displaysdfy.format(valueDate);
                     String newDateStringdmy = newDateStringd+"-"+newDateStringm+"\n"+newDateStringy;*/
                    // calendar.add(Calendar.DATE, 1);
                    cal.setTimeInMillis(valueAslong* 1000L);
                    String newDateStringdmy = DateFormat.format("dd-MM-yyyy", cal).toString();

                    return newDateStringdmy;
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        onClickButtonListener2();
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
    }

    private DataPoint[] getDataPoint() {
//just for testing
        DataPoint[] values = new DataPoint[] {
                new DataPoint(1511977210,-10002),
                new DataPoint(1512063610,-502),
                new DataPoint(1512150010,-11),
                new DataPoint(1512236410,88)

        };
        return values;
    }
}



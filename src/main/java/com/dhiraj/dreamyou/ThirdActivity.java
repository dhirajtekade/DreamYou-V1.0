package com.dhiraj.dreamyou;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.icu.text.DateFormat;
//import android.icu.text.SimpleDateFormat;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.jjoe64.graphview.series.DataPoint;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThirdActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper myDb;
    TableControllerCategory tDb;

    LinearLayout linearMain;
    CheckBox checkBox;
    Button btnAddData;
    Array checkboxlista;
    ArrayList checkboxlistb;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Integer finalscoreValue;
    String lasttotalscore;

    private static final String TAG = "ThirdActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
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


        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                //int month = (m < 10 ? "0" : "") + m;


                DatePickerDialog dialog = new DatePickerDialog(
                        ThirdActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,m,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String ZeroM; if(month < 10) { ZeroM = "0"; } else {ZeroM = "";}
                String ZeroD; if(day < 10) { ZeroD = "0"; } else {ZeroD = "";}


                String date = year+"" +ZeroM+  month+""+ZeroD+day;// + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };







        //checkboxList();
        linearMain = (LinearLayout) findViewById(R.id.linear_main);
        List<ObjectCategory> category = new TableControllerCategory(this).read();
        if (category.size() > 0) {
            for (ObjectCategory obj : category) {
                int id = obj.id;
                String categoryName = obj.cat_name;
                checkBox = new CheckBox(this);
                checkBox.setId(id);



                checkBox.setText(categoryName);



                checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
                linearMain.addView(checkBox);
            }
        }



      //  AddData (checkBox);
        btnAddData = (Button)findViewById(R.id.button_add);
      //  Button buttonCreateAddData = (Button) findViewById(R.id.button_add);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getRootView().getContext();
                try {
                    AddData (checkBox, context);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

//        getResults();
      //  private ArrayList<objectname> objectList  = getResults();
      //  readCheckboxRecords();
    }
    /*public void getResults() {

    }*/

    public void AddData (CheckBox checkBoxValue, Context context) throws ParseException {
        /*btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {*/
/*
        String str_date="20180101";
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date datev = (Date)formatter.parse(str_date);
        //System.out.println("Today is " +date.getTime());

        Long datetimestamp = datev.getTime();
        Integer Todaycat_tableTimestamp =   Integer.parseInt(datetimestamp/1000);
        Toast.makeText(ThirdActivity.this, "Today is2 " +datetimestamp, Toast.LENGTH_LONG).show();
*/


                        //get date as timestamp
                       TextView sdate = (TextView) findViewById(R.id.tvDate);
                        String  currentDateString = sdate.getText().toString();
                       // Integer selecteddateInt = Integer.parseInt(scurrentDateString)/1000;
                        //String  currentDateString = selecteddateInt.toString();
                        //if no date is selected use todaydate
                        if(currentDateString == "")  {
                            currentDateString = getCurrentDate();
                        }
                        String str_date= currentDateString;
                        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                        Long dTodaycat_tableTimestamp =   (long) 0;
                        Long Todaycat_tableTimestamp =   (long) 0;
                        try {
                            Date date = (Date)formatter.parse(str_date);
                             dTodaycat_tableTimestamp =    date.getTime();

                             Todaycat_tableTimestamp = Long.parseLong(dTodaycat_tableTimestamp.toString())/1000;

                            Toast.makeText(ThirdActivity.this, "timelong="+Todaycat_tableTimestamp, Toast.LENGTH_LONG).show();
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }
       // Toast.makeText(ThirdActivity.this, "time="+Todaycat_tableTimestamp, Toast.LENGTH_LONG).show();

                        boolean allCatInserted = true;
                        Integer todayscore = 0;
                        for (int i = 0; i < mUserItems.size(); i++) {
                            //showMessage("Notice", "-"+Todaycat_tableTimestamp+"--"+ mUserItems.get(i)+".");
                          //  Toast.makeText(ThirdActivity.this, "-"+Todaycat_tableTimestamp+"--"+ mUserItems.get(i)+".", Toast.LENGTH_LONG).show();
                           Integer catidvalue = mUserItems.get(i);
                            //boolean isInserted = myDb.insertDataTodaycat_table(111, 22222);

//



                            boolean isInserted = new TableControllerCategory(context).addTodaycat(Todaycat_tableTimestamp,catidvalue);

                            //Cursor Cat_Info = myDb.getCat_InfoFromCat_id(mUserItems.get(i));
                            Cursor Cat_Info = new TableControllerCategory(context).getCat_InfoFromCat_id(mUserItems.get(i));
                            while(Cat_Info.moveToNext()) {
                                //Toast.makeText(ThirdActivity.this, "Data Inserted for cat no="+Cat_Info.getString(5), Toast.LENGTH_SHORT).show();
                                todayscore = todayscore + Integer.parseInt(Cat_Info.getString(5));
                            }

                            //todayscore = todayscore +
                            if(isInserted = true) {
                               // Toast.makeText(ThirdActivity.this, "Data Inserted for cat ="+Cat_Info.getString(1), Toast.LENGTH_SHORT).show();
                            } else {
                                allCatInserted = false;
                                Toast.makeText(ThirdActivity.this, "Data failed to Add for cat no="+mUserItems.get(i), Toast.LENGTH_SHORT).show();
                            }
                        }

                        if(allCatInserted != true) {
                            Toast.makeText(ThirdActivity.this, "Data failed to add into Todaycat_table", Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(ThirdActivity.this, "todayscore="+todayscore, Toast.LENGTH_LONG).show();

                        //get last record from the summary_table//get last finalscore value

                        Cursor res2 =  new TableControllerCategory(context).getSummaryTableData();
                        if(res2.getCount() == 0) {
                            //show msg
                            showMessage("Notice", "This may be your first record OR No data found in Summary Table!");
                            finalscoreValue = todayscore;
                        }
                        while(res2.moveToNext()) {
                            //TODO - add only those previous finalscore of which date is smaller than this date
                            //and update larger date's finalscore if exists.
                            lasttotalscore = res2.getString(3); //finalscore
                            finalscoreValue = Integer.parseInt(lasttotalscore) + todayscore;
                        }

                        Integer currentDateInt = Integer.parseInt(currentDateString);
                        //add these data (date, todayscore,finalscore) to summary_table
                        boolean isSummaryInserted = new TableControllerCategory(context).summaryInsertData(Todaycat_tableTimestamp, todayscore, finalscoreValue, currentDateInt);
                        if(isSummaryInserted = true) {
                            Toast.makeText(ThirdActivity.this, "Data Inserted summarywise!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Data not Inserted summarywise!", Toast.LENGTH_SHORT).show();
                        }







                        //Toast.makeText(ThirdActivity.this, "sadate="+mUserItems.size(), Toast.LENGTH_LONG).show();
                   /* }});*/
    }


    public void checkboxList() {
        linearMain = (LinearLayout) findViewById(R.id.linear_main);
//         ArrayList<String> al = new ArrayList<String>();
        //ArrayList<objectname> al = new ArrayList<>(getCheckboxData());

        List<ObjectCategory> category = new TableControllerCategory(this).read();


        if (category.size() > 0) {
            for (ObjectCategory obj : category) {
                int id = obj.id;
                String categoryName = obj.cat_name;


                checkBox = new CheckBox(this);
                checkBox.setId(id);
                checkBox.setText(categoryName);

                checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
                linearMain.addView(checkBox);


                //Toast.makeText(ThirdActivity.this, "rescount="+categoryName, Toast.LENGTH_LONG).show();
            }

        }
    }

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserItems.add(button.getId());//todo remove when unchecked
               // Toast.makeText(ThirdActivity.this, "Checkoboxid="+button.getId()+" Text="+button.getText().toString(), Toast.LENGTH_SHORT).show();
                //checkboxlistb.add(button.getId());
                //checkboxlistb.set();
            }
        };
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
        //display(strDate);
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
        getMenuInflater().inflate(R.menu.third, menu);
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

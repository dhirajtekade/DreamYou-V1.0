package com.dhiraj.dreamyou;

/**
 * Created by Dhiraj on 12/21/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class OnClickListenerCreateCategory implements View.OnClickListener {
    DatabaseHelper myDb;
    Context context;
    String id;

    @Override
    public void onClick(View view) {



        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);

        final EditText editTextCategoryName = (EditText) formElementsView.findViewById(R.id.editTextCategoryName);
        final EditText editTextCategoryType = (EditText) formElementsView.findViewById(R.id.editTextCategoryType);
        final EditText editTextCategoryPoint = (EditText) formElementsView.findViewById(R.id.editTextCategoryPoint);
       final EditText editTextCategoryDesc = (EditText) formElementsView.findViewById(R.id.editTextCategoryDesc);
/*
        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date currentDayTime = Calendar.getInstance().getTime();
        final Integer CategoryCreated =  Integer.parseInt(df.format(currentDayTime));
        //myDb = new DatabaseHelper(context);

        Toast.makeText(context, "Creaate="+CategoryCreated, Toast.LENGTH_LONG).show();
*/
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Category")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                String CategoryName = editTextCategoryName.getText().toString();
                                Integer CategoryType = Integer.parseInt(editTextCategoryType.getText().toString());
                                Integer CategoryPoint = Integer.parseInt(editTextCategoryPoint.getText().toString());
                                String CategoryDesc = editTextCategoryDesc.getText().toString();



                              /*  Toast.makeText(context, "name="+CategoryName, Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "type="+CategoryType, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "CategoryPoint="+CategoryPoint, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "CategoryDesc="+CategoryDesc, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "CategoryCreated="+CategoryCreated, Toast.LENGTH_LONG).show();
*//*
                                Toast.makeText(context, "CategoryName="+CategoryName, Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "CategoryType="+CategoryType, Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "CategoryPoint="+CategoryPoint, Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "CategoryDesc="+CategoryDesc, Toast.LENGTH_LONG).show();
*/

                                ObjectCategory objectCategory = new ObjectCategory();
                                objectCategory.cat_name = CategoryName;
                                objectCategory.cat_type = CategoryType;
                                objectCategory.cat_point = CategoryPoint;
                                objectCategory.cat_desc = CategoryDesc;
  //                              objectCategory.cat_created = CategoryCreated;



                                boolean createSuccessful = new TableControllerCategory(context).create(objectCategory);
                                if(createSuccessful){
                                    Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                }
/*


                                objectCategory.cat_created = CategoryCreated;*/
/*
                               boolean createSuccessful = new DatabaseHelper(context).create(objectCategory);
                                if(createSuccessful){
                                    Toast.makeText(context, "Category information was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save Category information.", Toast.LENGTH_SHORT).show();
                                }*/


                                //boolean isInserted =    myDb.insertNewCategory(CategoryName,CategoryType,CategoryCreated,CategoryPoint,CategoryDesc);
                           //     boolean isInserted =    myDb.insertNewCategory(CategoryName);
/*                                if(isInserted = true) {
                                    Toast.makeText(context, "Category Added!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Failed to create category!", Toast.LENGTH_SHORT).show();
                                }*/
//todo- need to fix the page refresh issue
                                //countRecords();
                                //((FourthActivity) context).countRecords();
                                //((FourthActivity) context).readRecords();
                                dialog.cancel();
//countRecords();

                            }




                        }).show();
    }
}

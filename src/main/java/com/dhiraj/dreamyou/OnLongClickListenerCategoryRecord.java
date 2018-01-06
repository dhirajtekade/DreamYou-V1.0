package com.dhiraj.dreamyou;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dhiraj on 12/23/2017.
 */

public class OnLongClickListenerCategoryRecord implements View.OnLongClickListener {

    Context context;
    String id;
    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Category Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerCategory(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Category record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete category record.", Toast.LENGTH_SHORT).show();
                            }

                            ((FourthActivity) context).countRecords();
                            ((FourthActivity) context).readRecords();

                        }


                        dialog.dismiss();

                    }
                }).show();

        return false;
    }


    public void editRecord(final int categoryId) {
        final TableControllerCategory tableControllerCategory = new TableControllerCategory(context);
        ObjectCategory objectCategory = tableControllerCategory.readSingleRecord(categoryId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);

        final EditText editTextCategoryName = (EditText) formElementsView.findViewById(R.id.editTextCategoryName);
       // final EditText editTextCategoryType = (EditText) formElementsView.findViewById(R.id.editTextCategoryType);

        editTextCategoryName.setText(objectCategory.cat_name);
      //  editTextCategoryType.setText(objectCategory.cat_type);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectCategory objectCategory = new ObjectCategory();
                                objectCategory.id = categoryId;
                                objectCategory.cat_name = editTextCategoryName.getText().toString();
                                //objectCategory.cat_type = editTextCategoryName.getText().toString();


                                boolean updateSuccessful = tableControllerCategory.update(objectCategory);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Category record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }

                                ((FourthActivity) context).countRecords();
                                ((FourthActivity) context).readRecords();

                                dialog.cancel();
                            }

                        }).show();

    }

}

package com.dhiraj.dreamyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Button button_viewrecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickButtonListener();
    }


    public void onClickButtonListener() {
        button_viewrecord = (Button)findViewById(R.id.startjourney);
        button_viewrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.dhiraj.dreamyou.FifthActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}

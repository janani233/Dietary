package com.smart.www.dietaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashBoardDietitian extends AppCompatActivity {

    LinearLayout takeDietary,viewDietary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_dietitian);
        takeDietary = (LinearLayout)findViewById(R.id.take_dietary);
        viewDietary = (LinearLayout)findViewById(R.id.view_dietary);

        takeDietary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardDietitian.this,CreatePatientDietary.class));

            }
        });
       viewDietary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardDietitian.this,ViewDietary.class));

            }
        });

    }
}

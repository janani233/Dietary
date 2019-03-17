package com.smart.www.dietaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashBoardPatient extends AppCompatActivity {

    LinearLayout patientOrder,attender_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_patient);

        patientOrder =(LinearLayout)findViewById(R.id.patient_order);
        attender_order =(LinearLayout)findViewById(R.id.attender_order);
        patientOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardPatient.this,PatientOrder.class));

            }
        });

        attender_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardPatient.this,AttenderOrder.class));

            }
        });
    }
}

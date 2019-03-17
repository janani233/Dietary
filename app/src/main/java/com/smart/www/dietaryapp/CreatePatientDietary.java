package com.smart.www.dietaryapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatePatientDietary extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final Context context = this;
    private Button button;
    Button canteen_items,patient_details;
    Spinner schecdule,diet_type;
    EditText patient_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_dietary);
        canteen_items =(Button)findViewById(R.id.canteen_items);
        patient_details =(Button)findViewById(R.id.patient_details);
        diet_type =(Spinner)findViewById(R.id.diet_type);
        schecdule =(Spinner)findViewById(R.id.schedule);
        patient_id =(EditText)findViewById(R.id.patient_id);

        canteen_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        patient_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myRef = database.getReference("Patients");

                Integer patientId = Integer.parseInt(patient_id.getText().toString());
                String  Schedule = getResources().getStringArray(R.array.schedule)[schecdule.getSelectedItemPosition()];
                String  dietType = getResources().getStringArray(R.array.diet_type)[diet_type.getSelectedItemPosition()];

                //  Toast.makeText(getApplicationContext(),""+patientId +Schedule +dietType,Toast.LENGTH_SHORT).show();
                CanteenAction(patientId,Schedule,dietType);

            }
        });
    }

    public void CanteenAction(final Integer patientId, final String Schedule,final String dietType) {
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

int check =0;
int key = 0;
for(int i=0;i<dataSnapshot.getChildrenCount();i++){

    if(dataSnapshot.child(""+i+"").child("Id").getValue().toString().equals(patientId.toString())){
        check=1;
        key = i;
    }

}

                if(check == 1){

                   Toast.makeText(getApplicationContext(),""+dataSnapshot.child(""+key+"").getValue().toString(),Toast.LENGTH_SHORT).show();
                     check =0;

                }else{
                    Toast.makeText(getApplicationContext(),"Data absent",Toast.LENGTH_SHORT).show();
                 }






                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Patient details");


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.show();



















            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });

    }
}

package com.smart.www.dietaryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button login;
    EditText username,password;
    Spinner role;
    DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "Login";
    String roletype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        role  =(Spinner)findViewById(R.id.role);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = username.getText().toString();
                String pword = password.getText().toString();

                 roletype = getResources().getStringArray(R.array.role)[role.getSelectedItemPosition()];



                if (TextUtils.isEmpty(uname))
                    username.setError("* Enter the username");

                if (TextUtils.isEmpty(pword))
                    password.setError("* Enter the password");

                if(!uname.isEmpty() && !pword.isEmpty()){
 if(roletype.equals("Dietitian")){

     myRef = database.getReference("Dietitian");
     loginAction(uname,pword);
 }else if(roletype.equals("Patient")){

     myRef = database.getReference("Patient");
     loginAction(uname,pword);

 }else if(roletype.equals("Select Role")){
     Toast.makeText(getApplicationContext(),"Select Role",Toast.LENGTH_SHORT).show();
 }



                }



            }
        });

    }

    public void loginAction(final String ur, final String pwrd) {
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String usernameDB = dataSnapshot.child("UserName").getValue().toString();
                String passwordDB = dataSnapshot.child("Password").getValue().toString();

                    if (ur.equals(usernameDB) && pwrd.equals(passwordDB))
                    {

                        if(roletype.equals("Dietitian")){

                            startActivity(new Intent(Login.this,DashBoardDietitian.class));
                        }else if(roletype.equals("Patient")){

                            startActivity(new Intent(Login.this,DashBoardPatient.class));

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
  }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();

    }

}

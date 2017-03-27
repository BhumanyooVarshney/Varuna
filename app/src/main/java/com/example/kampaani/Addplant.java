
package com.example.kampaani;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addplant extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextNumber;
    private TextView a;
    private Button buttonSave;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plant);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        Firebase.setAndroidContext(this);
        mFirebaseAuth= FirebaseAuth.getInstance();
        mFirebaseUser= mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserID=mFirebaseUser.getUid();

        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.addlog);
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                loadLogInView();
            }
        });


        FloatingActionButton fab6 = (FloatingActionButton) findViewById(R.id.addhome);
        fab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });



        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating firebase object
                Firebase ref = new Firebase("https://kampaani-3294b.firebaseio.com/");
                editTextName.setText("");
                editTextNumber.setText("");
                //Getting values to store
                String name = editTextName.getText().toString().trim();
                String address = editTextNumber.getText().toString().trim();

                //Creating Person object
                Plant plant = new Plant();

                //Adding values
                plant.setName(name);
                plant.setNumber(address);

                //Storing values to firebase
                mDatabase.child("Users").child(mUserID).push().setValue(plant);
            }
        });


    }

    private void loadLogInView() {
        Intent intent = new Intent(this, startup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
package com.example.kampaani;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jains on 01-03-2017.
 */

public class WaterPlants extends AppCompatActivity{

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private TextView a;
    private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_plants);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final int[] water_txtviewid = {R.id.wat_text1, R.id.wat_text2, R.id.wat_text3, R.id.wat_text4, R.id.wat_text5};

        final int[] button_id = {R.id.water1, R.id.water2, R.id.water3, R.id.water4, R.id.water5};


        FloatingActionButton fab7 = (FloatingActionButton) findViewById(R.id.waterlog);
        fab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                loadLogInView();
            }
        });


        FloatingActionButton fab8 = (FloatingActionButton) findViewById(R.id.waterhome);
        fab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int k = 0;
                    for(DataSnapshot d : dataSnapshot.child("Users").child(mUserId).getChildren())
                    {
                        Plant c=d.getValue(Plant.class);
                        a=(TextView) findViewById(water_txtviewid[k]);
                        but = (Button) findViewById(button_id[k]);
                        a.setText(c.getName().concat("  ").concat(c.getNumber()));
                        but.setVisibility(View.VISIBLE);
                        k++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }



    }


    private void loadLogInView() {
        Intent intent = new Intent(this, startup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}





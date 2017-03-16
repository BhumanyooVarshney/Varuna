package com.example.kampaani;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by jains on 01-03-2017.
 */

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        myDb=new DatabaseHelper(this);
        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        AddData("Rice","0.43");

        Button water_plants=(Button)findViewById(R.id.button1);
        Button add_plant=(Button)findViewById(R.id.button2);
        Button details=(Button)findViewById(R.id.button3);
        Button about=(Button)findViewById(R.id.button4);

        TextView txtviw = (TextView)findViewById(R.id.textView4);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ama.ttf");

        txtviw.setTypeface(custom_font);

        water_plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WaterPlants.class);
                startActivityForResult(myIntent, 0);
            }
        });

        add_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Addplant.class);
                startActivityForResult(myIntent, 0);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Detail.class);
                startActivityForResult(myIntent, 0);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), About.class);
                startActivityForResult(myIntent, 0);
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                loadLogInView();
            }
        });

    }


    private void loadLogInView() {
        Intent intent = new Intent(this, startup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        //return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
  //          mFirebaseAuth.signOut();
    //        loadLogInView();
      //  }

        //return super.onOptionsItemSelected(item);
   // }

    void AddData(String a,String b)
    {
        myDb.insertData(a,b);
    }



    }





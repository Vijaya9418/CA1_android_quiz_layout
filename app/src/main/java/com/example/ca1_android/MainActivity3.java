package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ratingBar=findViewById(R.id.ratingBar);
    }
    public void back(View v)
    {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void back2(View v)
    {
        ratingBar.setVisibility(View.VISIBLE);


        float a=ratingBar.getRating();
        Toast.makeText(getApplicationContext(),"rating is "+a,Toast.LENGTH_SHORT).show();

        int b=ratingBar.getNumStars();
        Toast.makeText(getApplicationContext(),"number of stars are "+b,Toast.LENGTH_SHORT).show();
    }
}
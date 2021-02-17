package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {
    RatingBar ratingBar;

    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ratingBar=findViewById(R.id.ratingBar);

    //Custom taost


//        LayoutInflater inflater=getLayoutInflater();
//
//       View l=inflater.inflate(R. ,findViewById(R.id.im2));
//
//        TextView tv1=l.findViewById(R.id.tv1);
//        tv1.setText("i am custom toast");
//
//        Toast t=new Toast(getApplicationContext());
//        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
//        t.setDuration(Toast.LENGTH_LONG);
//        t.setView(l);
//        t.show();




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
        Toast.makeText(getApplicationContext(),"You have given " +a+ "much rating out of "+b,Toast.LENGTH_SHORT).show();
    }
}
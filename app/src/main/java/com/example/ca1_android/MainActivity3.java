package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {
    RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ratingBar=findViewById(R.id.ratingBar);
        Intent i=getIntent();

        TextView mar=findViewById(R.id.marks);
        mar.setText(i.getStringExtra("marks"));
        ImageView imgv=findViewById(R.id.im2);
        String reg=i.getStringExtra("reg");


        Toast toa=new Toast(this);
        toa.setDuration(Toast.LENGTH_LONG);
        toa.setGravity(Gravity.CENTER_VERTICAL,0,0);

        LayoutInflater layoutInflater = null;
        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layoutt =layoutInflater.inflate(R.layout.customtoast,null);
        TextView tt=layoutt.findViewById(R.id.toasttext);
        tt.setText("Marks :"+i.getStringExtra("marks"));
        ImageView imgtoast=layoutt.findViewById(R.id.toastimg);



        if(reg.equals("11813692")) {
            imgv.setImageResource(R.drawable.ian);imgtoast.setImageResource(R.drawable.ian);
        }
        else if(reg.equals("11806769")){
            imgv.setImageResource(R.drawable.paul);imgtoast.setImageResource(R.drawable.paul);}
        else if(reg.equals("11915692")){
            imgv.setImageResource(R.drawable.hrithik);imgtoast.setImageResource(R.drawable.hrithik);}
        else if(reg.equals("125690")){
            imgv.setImageResource(R.drawable.salman);imgtoast.setImageResource(R.drawable.salman);}
        else if(reg.equals("16568959")){
            imgv.setImageResource(R.drawable.shahrukh);imgtoast.setImageResource(R.drawable.shahrukh);}
        else
            imgv.setImageResource(R.drawable.person);imgtoast.setImageResource(R.drawable.person);



        toa.setView(layoutt);
        toa.show();





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

package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    RelativeLayout rv1,rv2,rv3,rv4;
    ProgressBar pg1,pg2,pg3;
    String name;
   TextView head,foot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent i=getIntent();
        name=i.getStringExtra("name");

        head=findViewById(R.id.head);
        foot=findViewById(R.id.foot);

        head.setText("Name"+name);
        foot.setText("Registration"+name);






        rv1=findViewById(R.id.rv1);
        rv2=findViewById(R.id.rv2);
        rv3=findViewById(R.id.rv3);
        rv4=findViewById(R.id.rv4);


        pg1=findViewById(R.id.pg1);





        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.GONE);
    }

    public void button1(View v)
    {
        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.VISIBLE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.GONE);



        new Handler().postAtTime(new Runnable() {
                                      @Override
                                      public void run() {
                                          pg1.setVisibility(View.VISIBLE);
                                      }
                                  },2
        );


    }

    public void button2(View v)
    {

        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.VISIBLE);
        rv4.setVisibility(View.GONE);



        new Handler().postAtTime(new Runnable() {
                                      @Override
                                      public void run() {
                                          pg1.setVisibility(View.VISIBLE);
                                      }
                                  },2
        );
    }

    public void button3(View v)
    {
        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.VISIBLE);



        new Handler().postAtTime (new Runnable() {
                                      @Override
                                      public void run() {
                                          pg1.setVisibility(View.VISIBLE);
                                      }
                                  },2
        );


    }

    public void button4(View v)
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
        startActivity(intent);
    }
}
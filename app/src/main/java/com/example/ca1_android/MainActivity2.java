package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    RelativeLayout rv1,rv2,rv3,rv4;
    ProgressBar pg1,pg2,pg3;
    String name,registration;
    TextView head,foot;
    int marks=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent i=getIntent();
        name=i.getStringExtra("name");
        registration=i.getStringExtra("registration");

        head=findViewById(R.id.head);
        foot=findViewById(R.id.foot);

        head.setText(name);
        foot.setText(registration);
        Toast.makeText(getApplicationContext(),"Attempt all the quiz",Toast.LENGTH_SHORT).show();

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
        RadioGroup vi=findViewById(R.id.rgq1);
        if(vi.getCheckedRadioButtonId()==R.id.rbq2){
            marks+=1;
        }
        progressbar();
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
        RadioGroup vi=findViewById(R.id.rgq2);
        if(vi.getCheckedRadioButtonId()==R.id.rcq2){
            marks+=1;
        }
        progressbar();
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
        RadioGroup vi=findViewById(R.id.rgq3);
        if(vi.getCheckedRadioButtonId()==R.id.rdq1){
            marks+=1;
        }
        progressbar();
        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.VISIBLE);

        Toast.makeText(getApplicationContext(),"this is the end of a quiz",Toast.LENGTH_SHORT).show();

        new Handler().postAtTime (new Runnable() {
                                      @Override
                                      public void run() {
                                          pg1.setVisibility(View.VISIBLE);
                                      }
                                  },2
        );


    }
    private Handler progressBarHandler = new Handler();
    public void button4(View v)
    {
        RadioGroup vi=findViewById(R.id.rgq4);
        if(vi.getCheckedRadioButtonId()==R.id.req4){
            marks+=1;
        }
        progressbar();
        Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("marks",String.valueOf(marks));
        intent.putExtra("reg",name);
        startActivity(intent);
    }
    public void progressbar(){
        RelativeLayout por=findViewById(R.id.progress);
        por.setVisibility(View.VISIBLE);
        ProgressBar pr=findViewById(R.id.pg1);
        pr.setProgress(0);
        pr.setMax(100);

        final int[] i = {0};
        new Thread(new Runnable() {
            public void run() {
                while (i[0] < 100) {

                    i[0] = doOperation(i[0]);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            pr.setProgress(i[0]);
//                             message.setText(Integer.toString(i[0])+"%");
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        por.setVisibility(View.INVISIBLE);
                    }});
            }
        }).start();





    }
    int doOperation(int i){
        return i+1;
    }
}

/*
 new Thread(new Runnable() {
            public void run() {

            }
        }).start();
 */
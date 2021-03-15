package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RelativeLayout rv1,rv2,rv3,rv4;
    ProgressBar pg1,pg2,pg3;
    String name,registration;
    TextView head,foot;
    TextView question;
    RadioButton ra,rb,rc,rd;
    Button bt1,bt2;
    int marks=0;
    ArrayList<String> ques=new ArrayList<>();
    ArrayList<String> A=new ArrayList<>();
    ArrayList<String> B=new ArrayList<>();
    ArrayList<String> C=new ArrayList<>();
    ArrayList<String> D=new ArrayList<>();
    ArrayList<String> and=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent old=getIntent();
        String quizName=old.getStringExtra("quizname");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        System.out.println(quizName+".............................name");
        DatabaseReference myRef = database.getReference("quiz").child(quizName);

        bt1=findViewById(R.id.nextbtn);
        bt2=findViewById(R.id.submitbtn);
        ra=findViewById(R.id.rbq1);
        rb=findViewById(R.id.rbq2);
        rc=findViewById(R.id.rbq3);
        rd=findViewById(R.id.rbq4);
        question=findViewById(R.id.q1t);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot i:dataSnapshot.getChildren()){
                    if(i.getChildrenCount()>2) {
                        System.out.println(i.child("ques").getValue(String.class) + "....................................");
                        ques.add(i.child("ques").getValue(String.class));
                        A.add(i.child("A").getValue(String.class));
                        B.add(i.child("B").getValue(String.class));
                        C.add(i.child("C").getValue(String.class));
                        D.add(i.child("D").getValue(String.class));
                        and.add(i.child("ans").getValue(String.class));
                    }
                }
                View temp=null;
                bt1.setVisibility(View.VISIBLE);
                button1(temp);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println(error+".........................................");
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MainActivity2.this,"Error check ur Network",Toast.LENGTH_LONG).show();

            }
        });




        Intent i=getIntent();
        name=i.getStringExtra("name");
        registration=i.getStringExtra("registration");

        head=findViewById(R.id.head);
        foot=findViewById(R.id.foot);

        head.setText(name);
        foot.setText(registration);
        Toast.makeText(getApplicationContext(),"Attempt all the quiz",Toast.LENGTH_SHORT).show();


        rv1=findViewById(R.id.rv1);




        pg1=findViewById(R.id.pg1);


        RadioGroup vi=findViewById(R.id.rgq1);
        vi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbq1=findViewById(R.id.rbq1);
                rbq1.setBackground(getDrawable(R.drawable.back5));
                rbq1.setTextColor(Color.parseColor("#000000"));

                RadioButton rbq2=findViewById(R.id.rbq2);
                rbq2.setBackground(getDrawable(R.drawable.back5));
                rbq2.setTextColor(Color.parseColor("#000000"));

                RadioButton rbq3=findViewById(R.id.rbq3);
                rbq3.setBackground(getDrawable(R.drawable.back5));
                rbq3.setTextColor(Color.parseColor("#000000"));

                RadioButton rbq4=findViewById(R.id.rbq4);
                rbq4.setBackground(getDrawable(R.drawable.back5));
                rbq4.setTextColor(Color.parseColor("#000000"));
                RadioButton anyName=findViewById(i);
                if(anyName!=null) {
                    anyName.setBackground(getDrawable(R.drawable.back6));
                    anyName.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        });



//        rv2.setVisibility(View.GONE);
        //     rv3.setVisibility(View.GONE);
        // rv4.setVisibility(View.GONE);
    }
    static int count=0;
    public void button1(View v)
    {
        RadioGroup vi=findViewById(R.id.rgq1);
        if(count!=0){


            if(vi.getCheckedRadioButtonId()==R.id.rbq1){



                if(and.get(count - 1).equals("A"))
                    marks+=1;
            }
            else if(vi.getCheckedRadioButtonId()==R.id.rbq2){


                if(and.get(count - 1).equals("B"))
                    marks+=1;
            }
            else if(vi.getCheckedRadioButtonId()==R.id.rbq3){


                if(and.get(count - 1).equals("C"))
                    marks+=1;
            }
            else if(vi.getCheckedRadioButtonId()==R.id.rbq4){


                if(and.get(count - 1).equals("D"))
                    marks+=1;
            }

        }
        vi.clearCheck();
        if(count<=ques.size()) {
            question.setText(ques.get(count));
            ra.setText(A.get(count));
            rb.setText(B.get(count));
            rc.setText(C.get(count));
            rd.setText(D.get(count));
            count++;
            progressbar();
        }

        //   rv2.setVisibility(View.VISIBLE);
        // rv3.setVisibility(View.GONE);


        if(count==ques.size()){

            bt1.setVisibility(View.GONE);
            bt2.setVisibility(View.VISIBLE);
        }


    }



    private Handler progressBarHandler = new Handler();
    public void button4(View v)
    {
        if(count==ques.size()) {
            RadioGroup vi = findViewById(R.id.rgq1);

            if (vi.getCheckedRadioButtonId() == R.id.rbq1) {
                if (and.get(count - 1).equals("A"))
                    marks += 1;
            } else if (vi.getCheckedRadioButtonId() == R.id.rbq2) {
                if (and.get(count - 1).equals("B"))
                    marks += 1;
            } else if (vi.getCheckedRadioButtonId() == R.id.rbq3) {
                if (and.get(count - 1).equals("C"))
                    marks += 1;
            } else if (vi.getCheckedRadioButtonId() == R.id.rbq4) {
                if (and.get(count - 1).equals("D"))
                    marks += 1;
            }

            progressbar();
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            intent.putExtra("marks", String.valueOf(marks));
            intent.putExtra("reg", name);
            count=0;
            marks=0;
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Error Sorry Close app and try again.",Toast.LENGTH_LONG).show();
        }
    }
    public void progressbar(){
        RelativeLayout por=findViewById(R.id.progress);
        por.setVisibility(View.VISIBLE);
        ProgressBar pr=findViewById(R.id.pg1);
        pr.setProgress(0);
        pr.setMax(100);

        // message=findViewById(R.id.message);
        //runningPBar();
        final int[] i = {0};
        new Thread(new Runnable() {
            public void run() {
                while (i[0] < 100) {
                    // performing operation
                    i[0] = doOperation(i[0]);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            pr.setProgress(i[0]);
                            // message.setText(Integer.toString(i[0])+"%");
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

        // performing operation if file is downloaded,



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
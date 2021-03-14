package com.example.ca1_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class choosequiz extends AppCompatActivity {

    ArrayList<String> quiz=new ArrayList<>();
    String name,reg;
    ArrayAdapter ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosequiz);
        ListView listView=findViewById(R.id.quizlist);
        Intent old=getIntent();
        name=old.getStringExtra("name");
        reg=old.getStringExtra("registration");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("quiz");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot i:dataSnapshot.getChildren()){

                    quiz.add(i.child("name").getValue(String.class));
                }
                ar= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, quiz);
                listView.setAdapter(ar);
                ar.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println(error+".........................................");
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(choosequiz.this,"Error check ur Network",Toast.LENGTH_LONG).show();
            }
        });


         ar = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, quiz);
        listView.setAdapter(ar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(MainActivity.this,"Select any Student to Attempt quiz",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(choosequiz.this);
                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                                System.out.println(quiz.get(position)+"....................name");
                                i.putExtra("quizname",quiz.get(position));
                                i.putExtra("name",name);
                                i.putExtra("registration",reg);

                                startActivity(i);
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(choosequiz.this,"Select any Student to Attempt quiz",Toast.LENGTH_LONG).show();
                        //k  finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                //end
            }
        });
    }
}
package com.example.ca1_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class choosequiz extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"Geography", "Political", "Sports"};
    String mDescription[] = {"geography", "political", "sports"};
    ArrayList<String> quiz=new ArrayList<>();
    int images[] = {R.drawable.gg, R.drawable.p2, R.drawable.s3};
   MyAdapterr adapter;

    String name,reg;
    ArrayAdapter ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosequiz);
        listView=findViewById(R.id.quizlist);
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
                adapter = new MyAdapterr(getApplicationContext(), mTitle, mDescription, images);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println(error+".........................................");
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(choosequiz.this,"Error check ur Network",Toast.LENGTH_LONG).show();
            }
        });


        adapter = new MyAdapterr(getApplicationContext(), mTitle, mDescription, images);
        listView.setAdapter(adapter);

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
    class MyAdapterr extends ArrayAdapter<String> {

        Context context;
        String rTitle[],filtitle[],fildec[];
        String rDescription[];
        int rImgs[],filimg[];

        MyAdapterr (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.filtitle=title;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;
            this.fildec=description;this.filimg=imgs;
        }

        public void filterc(String s){
            ArrayList<String> ind=new ArrayList<>();
            for(String i:filtitle){
                ind.add(i);
            }
            ArrayList<String> iv=new ArrayList<>();
            for(String i:filtitle){
                if(i.startsWith(s)){
                    iv.add(i);
                }
            }

            this.rTitle=new String[iv.size()];
            this.rDescription=new String[iv.size()];
            this.rImgs=new int[iv.size()];
            int count=0;
            for(String i:iv){
                this.rTitle[count]=i;
                this.rImgs[count]=filimg[ind.indexOf(i)];
                this.rDescription[count]=fildec[ind.indexOf(i)];
                count++;
            }
            notifyDataSetChanged();

        }

        @Override
        public int getCount() {
            return rTitle.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            try{
                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.row, parent, false);
                ImageView images = row.findViewById(R.id.image);
                TextView myTitle = row.findViewById(R.id.textView1);
                TextView myDescription = row.findViewById(R.id.textView2);

                images.setImageResource(rImgs[position]);
                myTitle.setText(rTitle[position]);
                myDescription.setText(rDescription[position]);

                return row;}
            catch(Exception e) {
                System.out.println("Error");
                return null;
            }
        }
    }
}
package com.example.ca1_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter ar;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb1 =findViewById(R.id.tb1);
        setSupportActionBar(tb1);

        ListView lv=findViewById(R.id.lv);



        ArrayList al=new ArrayList();
        al.add("                 Vijaya - 11813692");
        al.add("                 Golu - 11834568");
        al.add("                 Rinku - 11803591");
        al.add("                 Ranu - 11813402");
        al.add("                 Khushbu - 11806682");


        ar=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(ar);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,"Quiz",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);



                String element= (String) al.get(position);

                i.putExtra("name",element);
                startActivity(i);








            }
        });

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
//                        alertDialogBuilder.setPositiveButton("yes",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface arg0, int arg1) {
//                                        Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
//                                        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
//                                        startActivity(i);
//                                    }
//                                });
//
//                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                    }
//                });
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            }
//        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem mSearch=menu.findItem(R.id.search_bar);
        SearchView msv=(SearchView)mSearch.getActionView();

    if(msv!=null) {
    msv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;

            //If you wanna send any data to nextActicity.class you can use
//                i.putExtra(String key, value.get(position));



        }

        @Override
        public boolean onQueryTextChange(String s) {
            ar.getFilter().filter(s);
            return true;
        }
    });
}
        return super.onCreateOptionsMenu(menu);
    }
}
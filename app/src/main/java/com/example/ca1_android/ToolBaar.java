package com.example.ca1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class ToolBaar extends AppCompatActivity {
    ArrayAdapter ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbaar);
        androidx.appcompat.widget.Toolbar tb1 =findViewById(R.id.tb1);
        setSupportActionBar(tb1);

       ListView lv=findViewById(R.id.lv);

        ArrayList al=new ArrayList();
        al.add("11813692");
        al.add("11834568");
        al.add("11803591");
        al.add("11813402");
        al.add("11806682");

        ar=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(ar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem mSearch=menu.findItem(R.id.search_bar);

        SearchView msv=(SearchView)mSearch.getActionView();
        msv.setQueryHint("Enter registration number");
        msv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               ar.getFilter().filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
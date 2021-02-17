package com.example.ca1_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"11813692", "11813693", "11813694", "11813695", "11813696"};
    String mDescription[] = {"ian somehaldar", "paul wesley", "hrithik roshan", "salman khan", "shahrukh khan"};
    int images[] = {R.drawable.ian, R.drawable.paul, R.drawable.hrithik, R.drawable.salman, R.drawable.shahrukh};

//    ArrayAdapter ar;
//    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb1 =findViewById(R.id.tb1);
        setSupportActionBar(tb1);

         listView=findViewById(R.id.listview);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,"Select any Student to Attempt quiz",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);





                i.putExtra("name",mTitle[position]);
                i.putExtra("registration",mDescription[position]);

                startActivity(i);








            }
        });
//
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);


            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);




            return row;
        }
    }
}



//        ArrayList al=new ArrayList();
//        al.add("                 Vijaya - 11813692");
//        al.add("                 Golu - 11834568");
//        al.add("                 Rinku - 11803591");
//        al.add("                 Ranu - 11813402");
//        al.add("                 Khushbu - 11806682");
//
//
//        ar=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);
//        lv.setAdapter(ar);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(MainActivity.this,"Quiz",Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
//
//
//
//                String element= (String) al.get(position);
//
//                i.putExtra("name",element);
//                startActivity(i);
//
//
//
//
//
//
//
//
//            }
//        });
//
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
////
////                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
////                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
////                        alertDialogBuilder.setPositiveButton("yes",
////                                new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface arg0, int arg1) {
////                                        Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
////                                        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
////                                        startActivity(i);
////                                    }
////                                });
////
////                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
////                           @Override
////                           public void onClick(DialogInterface dialog, int which) {
////                            finish();
////                    }
////                });
////                AlertDialog alertDialog = alertDialogBuilder.create();
////                alertDialog.show();
////            }
////        });
//
//
//
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_menu,menu);
//        MenuItem mSearch=menu.findItem(R.id.search_bar);
//        SearchView msv=(SearchView)mSearch.getActionView();
//
//    if(msv!=null) {
//    msv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String s) {
//            return false;
//
//            //If you wanna send any data to nextActicity.class you can use
////                i.putExtra(String key, value.get(position));
//
//
//
//        }
//
//        @Override
//        public boolean onQueryTextChange(String s) {
//            ar.getFilter().filter(s);
//            return true;
//        }
//    });
//}
//        return super.onCreateOptionsMenu(menu);
//    }
//}
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"11813692", "11806769", "11915692", "125690", "16568959"};
    String mDescription[] = {"ian somehaldar", "paul wesley", "hrithik roshan", "salman khan", "shahrukh khan"};
    ArrayList<String> names=new ArrayList<>();
    int images[] = {R.drawable.ian, R.drawable.paul, R.drawable.hrithik, R.drawable.salman, R.drawable.shahrukh};
    MyAdapter adapter;
//    ArrayAdapter ar;
//    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb1 =findViewById(R.id.tb1);
        setSupportActionBar(tb1);

        listView=findViewById(R.id.listview);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("regusers");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot i:dataSnapshot.getChildren()){
                    System.out.println(i.child("name").getValue(String.class)+"....................................");
                    names.add(i.child("name").getValue(String.class));
                }
                update();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println(error+".........................................");
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MainActivity.this,"Error check ur Network",Toast.LENGTH_LONG).show();

            }
        });

        adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(MainActivity.this,"Select any Student to Attempt quiz",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(getApplicationContext(), MainActivity2.class);



                                i.putExtra("name",mTitle[position]);
                                i.putExtra("registration",mDescription[position]);

                                startActivity(i);
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Select any Student to Attempt quiz",Toast.LENGTH_LONG).show();
                        //k  finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                //end









            }
        });
//

    }

    private void update(){
        String[] newarr=new String[mTitle.length+names.size()];
        String[] newarrd=new String[mTitle.length+names.size()];
        int[] newarri=new int[mTitle.length+names.size()];
        int cat=0;
        for(int i=0;i<mTitle.length;i++){
            newarr[cat]=mTitle[cat];
            newarrd[cat]=mDescription[cat];
            newarri[cat]=images[cat];
            cat++;
        }
        for(int i=0;i<names.size();i++){
            newarr[cat]=names.get(i);
            newarrd[cat]=names.get(i);
            newarri[cat]=R.drawable.p1;
            cat++;
        }
        mTitle=newarr;
        mDescription=newarrd;
        images=newarri;
        adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
                    // ar.getFilter().filter(s);
                    adapter.filterc(s);
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }



    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[],filtitle[],fildec[];
        String rDescription[];
        int rImgs[],filimg[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
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

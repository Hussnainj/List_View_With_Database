package com.example.wasim.listviewwithdatabase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {


   /* int[] imageIDs = {

            R.drawable.down,
            R.drawable.fish,
            R.drawable.heart,
            R.drawable.lightning,
            R.drawable.star,
            R.drawable.up
    };
    int nextImageIndex = 0;*/
    //List<String> data;
    ListView lv2;
    ArrayAdapter<String> adapter2;
    ArrayList<String> player2 = new ArrayList<String>();
    final DBAdapter db2 = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Toast.makeText(getApplicationContext(),"testing",Toast.LENGTH_SHORT).show();
        db2.openDB();

          Cursor c = db2.getAllNames();

            while (c.moveToNext())
            {

                String TextData=c.getString(1);
                String TextData1=c.getString(2);
//                Toast.makeText(getApplicationContext(),TextData,Toast.LENGTH_SHORT).show();
//                textView33.setText(TextData);
//                textView44.setText(TextData1);
                player2.add(TextData);
             //   player2.add(TextData1);
}

        ArrayAdapter<String> courseArrayAdapter = new CustomeArrayAdapter(this,0,player2);
        lv2= (ListView) findViewById(R.id.listView2);
    //    ListView listView = (ListView) findViewById(android.R.id.list);
        lv2.setAdapter(courseArrayAdapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee = data.get(i);
                displayDetails(employee);
            }
        });*/

    }

    class CustomeArrayAdapter extends ArrayAdapter<String>
    {
        Context context;
        List<String> objects;

        public CustomeArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            this.objects=objects;
            this.context=context;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String course = objects.get(position);
          ////  String course1 = objects1.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
          //  LayoutInflater inflater1 = (LayoutInflater) context1.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.limagelist, null);
            TextView textView33 = (TextView) view.findViewById(R.id.textView3);
          //  TextView textView44 = (TextView) view.findViewById(R.id.textView4);

           ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
          //  player2.clear();
      /*      db2.openDB();

            Cursor c = db2.getAllNames();

           while (c.moveToNext())
            {

                String TextData=c.getString(1);
                String TextData1=c.getString(2);
                Toast.makeText(getApplicationContext(),TextData,Toast.LENGTH_SHORT).show();
             textView33.setText(TextData);
                textView44.setText(TextData1);
                player2.add(TextData);

          }

            lv2.setAdapter(adapter2);
            db2.close();*/
          //  int imageId = imageIDs[nextImageIndex];
          //  nextImageIndex = (nextImageIndex + 1) % imageIDs.length;
            final Class drawableClass = R.drawable.class;
            final Field[] fields = drawableClass.getFields();

            final Random rand = new Random();
            int rndInt = rand.nextInt(fields.length);
            try {
                int resID = fields[rndInt].getInt(drawableClass);
                imageView.setImageResource(resID);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add it to the DB and re-draw the ListView
       //   adapter2.insertRow(et.getText().toString() + nextImageIndex, imageId, "BROWN");
     //    int res = context.getResources().getIdentifier(course,"drawable",context.getPackageName());
        //   imageView.setImageResource(res);


            textView33.setText(course);

            return view;


        }
    }
    //public class
}

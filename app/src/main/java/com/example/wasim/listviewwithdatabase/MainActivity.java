package com.example.wasim.listviewwithdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText nameTx,posTx;
    Button saveBtn,retriveBtn;
    ArrayAdapter<String> adapter;
    ArrayList<String> player = new ArrayList<String>();
    final DBAdapter db = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        nameTx = (EditText) findViewById(R.id.editText);
        posTx = (EditText) findViewById(R.id.editText2);
        lv= (ListView) findViewById(R.id.listView);
      //  lv.setBackgroundColor(Color.LTGRAY);

        saveBtn = (Button) findViewById(R.id.button);
        retriveBtn= (Button) findViewById(R.id.button2);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,player);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveData(View view) {

       db.openDB();

        long result = db.add(nameTx.getText().toString(),posTx.getText().toString());
        Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();

        if (result > 0 )
        {
            nameTx.setText("");
            posTx.setText("");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
        }
       db.close();
    }

    public void retriveData(View view) {

            player.clear();
        db.openDB();

        Cursor c = db.getAllNames();

        while (c.moveToNext())
        {

            String TextData=c.getString(1);
            player.add(TextData);

        }

        lv.setAdapter(adapter);
        Toast.makeText(getApplicationContext(),"Fetched Data successfully",Toast.LENGTH_SHORT).show();
        db.close();
 lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         Toast.makeText(getApplicationContext(),player.get(i),Toast.LENGTH_SHORT).show();
     }
 });
    }

    public void newactivity(View view) {

        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}

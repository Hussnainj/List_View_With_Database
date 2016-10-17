package com.example.wasim.listviewwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by wasim on 10/4/2016.
 */
public class DBAdapter {

    //Coloumns
    static final String ROWID= "id";
    static final String NAME= "name";
    static final String POSITION= "position";

    //DB propertise
    static final String DBNAME= "m_DB";
    static final String TBNAME= "m_TB";
    static final int DBVERSION= '1';

    static final String CREATE_TB= "CREATE TABLE m_TB(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,position TEXT NOT NULL);";


    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx) {
        this.c = ctx;
        helper = new DBHelper(c);
    }

    // / Inner helper DB Class

    private static  class DBHelper extends SQLiteOpenHelper

    {


        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try
            {
            db.execSQL(CREATE_TB);
            }
            catch (Exception e)
            {

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

            Log.w("DBAdapter","Updating DB");
            db.execSQL("DROP TABLE IF EXISTS m_TB");
            onCreate(db);
        }
    }


 // Open DB

    public DBAdapter openDB()
    {


        try
        {
            db = helper.getWritableDatabase();

        }
        catch (Exception e)
        {

        }

    return this ;
    }

    //CLose DB

    public  void close()
    {

        helper.close();
    }

    // Insert into DB

    public  long add (String name, String position)
    {


        try
        {
            ContentValues cv = new ContentValues();

            cv.put(NAME,name);
            cv.put(POSITION,position);

            return  db.insert(TBNAME,ROWID,cv);
        }
        catch (Exception e)
        {


        }
        return  0;
    }



    //Get ALL Values

    public Cursor getAllNames()
    {
        String [] column = {ROWID,NAME,POSITION};

        return  db.query(TBNAME,column,null,null,null,null,null);
    }
}




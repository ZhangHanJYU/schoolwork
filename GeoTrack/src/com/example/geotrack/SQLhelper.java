package com.example.geotrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

/* use this class to help store the locations */
public class SQLhelper{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_latitude  = "latitude";
	public static final String KEY_longitude = "longitude";
	          Cursor cur=null;

	private static final String DATABASE_NAME = "MyDB.sqlite";
	private static final String DATABASE_TABLE = "gpsCordinates";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = 
	        "create table gpsCordinates(_id integer primary key autoincrement,"
	+ "latitude text not null, longitude text not null);";
	

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public SQLhelper(Context context){
	    this.context = context;
	    DBHelper = new DatabaseHelper(context);
	}
      /*create SQL database*/
	private static class DatabaseHelper extends SQLiteOpenHelper{
	    DatabaseHelper (Context context)
	    {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        // TODO Auto-generated method stub
	        try{
	            db.execSQL(DATABASE_CREATE);
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	        }

	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),"Upgrading database from version" 
	        +oldVersion + " to " +newVersion + ", This will remove all previous data");
	        db.execSQL("Drop TABLE IF EXISTS");
	        onCreate(db);                            /* called when upgrade the database*/
	    }
	}

	public void open() throws SQLException {
	    db = DBHelper.getWritableDatabase();   /* called when open database*/
	    

	}

	public void close(){
	    DBHelper.close();
	}
	/* inserts a new contact into database */
	public double insertRow(double latitudE, double longitudE){
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_latitude, latitudE);
	    initialValues.put(KEY_longitude, longitudE);
	    return db.insert(DATABASE_TABLE, null, initialValues); /*store latitude and longitude to table*/

	}
	/*called when read the data*/
	public Cursor retrieveRecord(){
	  cur= db.query(DATABASE_TABLE,  new String[]{KEY_latitude,KEY_longitude}, null, null, null, null, null);
	    return cur;
	}

	
}

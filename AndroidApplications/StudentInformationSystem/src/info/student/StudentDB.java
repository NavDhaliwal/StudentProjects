package info.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentDB 
{
	public static final String KEY_id = "id";
    public static final String KEY_FirstName = "fn";
    public static final String KEY_MiddleName = "mn";
    public static final String KEY_LastName = "ls";
    public static final String KEY_FatherName = "fa";
    public static final String KEY_MotherName = "m_n";  
    public static final String KEY_DOB_day = "day";
    public static final String KEY_DOB_month = "month";
    public static final String KEY_DOB_year = "year";
    public static final String KEY_Email = "email";
    public static final String KEY_Degree = "degree";
    public static final String KEY_Spec = "spec"; 
    public static final String KEY_Contact_no = "contactno";
    private static final String TAG = "StudentDB";   
    private static final String DATABASE_NAME = "StudentInfo";
    private static final String DATABASE_TABLE = "Info";
    private static final int DATABASE_VERSION = 1;
   
    private static final String DATABASE_CREATE =
        "create table Info (id integer primary key autoincrement,fn text not null, mn text,ls text not null, fa text not null, " 
        + "m_n text not null,day integer not null,month integer not null,year integer not null,email text not null,contactno long DEFAULT 0,degree text not null,spec text not null);";
        
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public StudentDB(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Info");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public StudentDB open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertInfo(String fn, String mn, String ls,String fa,String mm,long day,
    		long month,long year,String email,long contact_no,String degree,String spec) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FirstName, fn);
        initialValues.put(KEY_MiddleName, mn);
        initialValues.put(KEY_LastName, ls);
        initialValues.put(KEY_FatherName, fa);
        initialValues.put(KEY_MotherName, mm);
        initialValues.put(KEY_DOB_day, day);
        initialValues.put(KEY_DOB_month,month);
        initialValues.put(KEY_DOB_year,year);
        initialValues.put(KEY_Email, email);
        initialValues.put(KEY_Contact_no, contact_no);
        initialValues.put(KEY_Degree,degree);
        initialValues.put(KEY_Spec,spec);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular title---
    public boolean deleteInfo(long id) 
    {
        return db.delete(DATABASE_TABLE, KEY_id + 
        		"="+id, null) > 0;
    }

    //---retrieves all the titles---
    public Cursor getAllInfo() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_id,
        		KEY_FirstName, 
        		KEY_MiddleName,
        		KEY_LastName,
        		KEY_FatherName,
        		KEY_MotherName,
        		KEY_DOB_day,KEY_DOB_month,KEY_DOB_year,KEY_Email,
        		KEY_Contact_no,KEY_Degree,KEY_Spec}, 
                null, 
                null, 
                null, 
                null, 
                KEY_FirstName+" Asc");
    }

    //---retrieves a particular title---
    public Cursor getInfo(long id) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_id,
                		KEY_FirstName, 
                		KEY_MiddleName,
                		KEY_LastName,
                		KEY_FatherName,
                		KEY_MotherName,
                		KEY_DOB_day,KEY_DOB_month,KEY_DOB_year,
                		KEY_Email,KEY_Contact_no,KEY_Degree,KEY_Spec
                		}, 
                		KEY_id+"="+id, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        //if (mCursor != null) {
            //mCursor.moveToFirst();
       // }
        return mCursor;
    }

    //---updates a title---
    public boolean updateInfo(int id,String fn, String mn, 
    String ls, String fa,String mm,long day,long month,long year,
    String email,long contact_no,String degree,String spec) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_FirstName, fn);
        args.put(KEY_MiddleName, mn);
        args.put(KEY_LastName, ls);
        args.put(KEY_FatherName, fa);
        args.put(KEY_MotherName, mm);
        args.put(KEY_DOB_day, day);
        args.put(KEY_DOB_month, month);
        args.put(KEY_DOB_year, year);
        args.put(KEY_Email,email);
        args.put(KEY_Contact_no,contact_no);
        args.put(KEY_Degree,degree);
        args.put(KEY_Spec,spec);
        Log.i("ID =",String.valueOf(id));
        return db.update(DATABASE_TABLE, args,KEY_id+"="+(id),null) > 0;
    }
}




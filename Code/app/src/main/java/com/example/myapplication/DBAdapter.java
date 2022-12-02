package com.example.myapplication;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;


public class DBAdapter {
    private static final boolean DEBUG = true;

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SIZE = "size";
    public static final String KEY_TOPPING_ONE = "topping_one";
    public static final String KEY_TOPPING_TWO = "topping_two";
    public static final String KEY_TOPPING_THREE = "topping_three";
    public static final String KEY_ORDER_DATE = "order_date";
    public static final String TAG = "DBAdapter";
    public static final String DATABASE_NAME = "CruddyPizzaDB";
    public static final String DATABASE_TABLE = "orders";
    public static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
            "create table orders (_id integer primary key autoincrement, "
                    + "name text not null, size text not null, topping_one text, topping_two text, topping_three text, order_date text not null);";
    //seed database with some data
    private static final String DATABASE_SEED =
            "INSERT INTO orders (name, size, topping_one, topping_two, topping_three, order_date) VALUES ('John Smith', 'Large', 'Pepperoni', 'Sausage', 'Mushrooms', 'date'), "
            + "('Jane Doe', 'Medium', 'Pepperoni', 'Sausage', 'Mushrooms', 'date'), "
            + "('Jack MacKinnon', 'Small', 'Pepperoni', 'Sausage', 'Mushrooms', 'date'), "
            + "('Jill MacKinnon', 'Large', 'Pepperoni', 'Sausage', 'Mushrooms', 'date'), "
            + "('John MacKinnon', 'Medium', 'Pepperoni', 'Sausage', 'Mushrooms', 'date');";


    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
                //seed database with some data if debug is true
                if (DEBUG) {
                    db.execSQL(DATABASE_SEED);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS orders");
            onCreate(db);
        }
    }// end of DatabaseHelper class

    //open the connection
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }// end of open method

    //close the connection
    public void close() {
        DBHelper.close();
    }// end of close method

    //insert a record into the database
    public long insertRecord(String name, String size, String topping_one, String topping_two, String topping_three, String order_date) {
        //sanitize the data
        name = name.trim();


        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SIZE, size);
        initialValues.put(KEY_TOPPING_ONE, topping_one);
        initialValues.put(KEY_TOPPING_TWO, topping_two);
        initialValues.put(KEY_TOPPING_THREE, topping_three);
        initialValues.put(KEY_ORDER_DATE, order_date);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }// end of insertRecord method

    //delete a record from the database
    public boolean deleteRecord(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }// end of deleteRecord method


    //retrieve all records from the database
    public Cursor getAllRecords() {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_SIZE, KEY_TOPPING_ONE, KEY_TOPPING_TWO, KEY_TOPPING_THREE, KEY_ORDER_DATE},
                KEY_ROWID+"="+KEY_ROWID, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }// end of getAllRecords method

    //update a single contact
    public boolean updateOrder(long rowId, String name, String size, String topping_one, String topping_two, String topping_three, String order_date) {
        //sanitize the data
        name = name.trim();

        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SIZE, size);
        args.put(KEY_TOPPING_ONE, topping_one);
        args.put(KEY_TOPPING_TWO, topping_two);
        args.put(KEY_TOPPING_THREE, topping_three);
        args.put(KEY_ORDER_DATE, order_date);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }// end of updateOrder method

    //retrieve a single record from the database
    public Cursor getRecord(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_SIZE, KEY_TOPPING_ONE, KEY_TOPPING_TWO, KEY_TOPPING_THREE, KEY_ORDER_DATE},
                KEY_ROWID+"="+rowId, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }// end of getRecord method



}//end class DBAdapter

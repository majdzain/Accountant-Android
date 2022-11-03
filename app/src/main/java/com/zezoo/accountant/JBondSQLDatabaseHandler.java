package com.zezoo.accountant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JBondSQLDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JBonds_Database.db";
    private static final String TABLE_NAME = "JBonds";
    private static final String KEY_COLUMN = "colum";
    private static final String KEY_DEBCRE = "deb_cre";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_CAMOUNT = "c_amount";
    private static final String KEY_DAMOUNT = "d_amount";
    private static final String KEY_SPEC = "spec";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_COLUMN, KEY_CAMOUNT,KEY_DAMOUNT, KEY_DEBCRE, KEY_PHONE, KEY_SPEC, KEY_CURRENCY, KEY_TIME, KEY_DATE};

    SQLiteDatabase db;
    Context context;

    String CREATION_TABLE = "CREATE TABLE JBonds ( "
            + "colum INTEGER PRIMARY KEY AUTOINCREMENT, " + "c_amount INTEGER, "+ "d_amount INTEGER, " + "deb_cre TEXT, " + "phone TEXT, "
            + "spec TEXT, " + "currency TEXT, " + "time TEXT," + "date TEXT )";

    public JBondSQLDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
// you can implement here migration process
        db.execSQL(CREATION_TABLE);
        this.onCreate(db);
    }

    public void deleteJBond(JBondListChildItem j_bond) {
        // Get reference to writable DB
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.delete(TABLE_NAME, "colum = ?", new String[]{String.valueOf(j_bond.getColumn())});
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "j_bonds.db");
        } catch (Exception e) {

        }
    }

    public JBondListChildItem getJBond(int Column) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " colum = ?", // c. selections
                new String[]{String.valueOf(Column)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        JBondListChildItem j_bond = new JBondListChildItem();
        j_bond.setColumn(cursor.getInt(0));
        j_bond.setCAmount(cursor.getInt(1));
        j_bond.setDAmount(cursor.getInt(2));
        j_bond.setDebCre(cursor.getString(3));
        j_bond.setPhone(cursor.getString(4));
        j_bond.setSpec(cursor.getString(5));
        j_bond.setCurrency(cursor.getString(6));
        j_bond.setTime(cursor.getString(7));
        j_bond.setDate(cursor.getString(8));
        return j_bond;
    }

    public List<JBondListChildItem> allJBonds() {

        List<JBondListChildItem> j_bonds = new ArrayList<JBondListChildItem>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        JBondListChildItem j_bond = null;

        if (cursor.moveToFirst()) {
            do {
                j_bond = new JBondListChildItem();
                j_bond.setColumn(cursor.getInt(0));
                j_bond.setCAmount(cursor.getInt(1));
                j_bond.setDAmount(cursor.getInt(2));
                j_bond.setDebCre(cursor.getString(3));
                j_bond.setPhone(cursor.getString(4));
                j_bond.setSpec(cursor.getString(5));
                j_bond.setCurrency(cursor.getString(6));
                j_bond.setTime(cursor.getString(7));
                j_bond.setDate(cursor.getString(8));
                j_bonds.add(j_bond);
            } while (cursor.moveToNext());
        }
        return j_bonds;
    }

    public List<String> allDebCres() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, JBondListChildItem> j_bondsWithoutGroup = new HashMap<Integer, JBondListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        JBondListChildItem j_bond = null;
        String folder = null;
        if (cursor.moveToFirst()) {
            do {
                folder = cursor.getString(3);
                if (!folders.contains(folder)) {
                    folders.add(folder);
                }
            } while (cursor.moveToNext());
        }
        return folders;
    }


    public void addJBond(JBondListChildItem j_bond) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, j_bond.getColumn());
        values.put(KEY_CAMOUNT, j_bond.getCAmount());
        values.put(KEY_DAMOUNT, j_bond.getDAmount());
        values.put(KEY_DEBCRE, j_bond.getDebCre());
        values.put(KEY_PHONE, j_bond.getPhone());
        values.put(KEY_SPEC, j_bond.getSpec());
        values.put(KEY_CURRENCY, j_bond.getCurrency());
        values.put(KEY_TIME, j_bond.getTime());
        values.put(KEY_DATE, j_bond.getDate());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "j_bonds.db");
        } catch (Exception e) {

        }
    }

    public int updateJBond(JBondListChildItem j_bond) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, j_bond.getColumn());
        values.put(KEY_CAMOUNT, j_bond.getCAmount());
        values.put(KEY_DAMOUNT, j_bond.getDAmount());
        values.put(KEY_DEBCRE, j_bond.getDebCre());
        values.put(KEY_PHONE, j_bond.getPhone());
        values.put(KEY_SPEC, j_bond.getSpec());
        values.put(KEY_CURRENCY, j_bond.getCurrency());
        values.put(KEY_TIME, j_bond.getTime());
        values.put(KEY_DATE, j_bond.getDate());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "colum = ?", // selections
                new String[]{String.valueOf(j_bond.getColumn())});

        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "j_bonds.db");
        } catch (Exception e) {

        }
        return i;
    }

    public void backup(String outFileName) throws IOException {
        //database path
        final String inFileName = context.getDatabasePath(DATABASE_NAME).toString();
        File dbFile = new File(inFileName);
        FileInputStream fis = new FileInputStream(dbFile);
        // Open the empty db as the output stream
        OutputStream output = new FileOutputStream(outFileName);
        // Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        // Close the streams
        output.flush();
        output.close();
        fis.close();
    }

    public void importDB(String inFileName) throws IOException {
        final String outFileName = context.getDatabasePath(DATABASE_NAME).toString();
        File dbFile = new File(inFileName);
        FileInputStream fis = new FileInputStream(dbFile);
        // Open the empty db as the output stream
        OutputStream output = new FileOutputStream(outFileName);
        // Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        // Close the streams
        output.flush();
        output.close();
        fis.close();
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }
    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
}

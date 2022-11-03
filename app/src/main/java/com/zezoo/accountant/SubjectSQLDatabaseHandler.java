package com.zezoo.accountant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectSQLDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Products_Database.db";
    private static String DATABASE_PATH = "/data/data/com.zezoo.accountant/databases/";
    private static final String DATABASE_EX_PATH = "/Accountant/Databases/";
    private static final String TABLE_NAME = "Subjects";
    private static final String KEY_COLUMN = "colum";
    private static final String KEY_ID = "id";
    private static final String KEY_FOLDER = "folder";
    private static final String KEY_NAME = "name";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_AMOUNTLAST = "amount_last";
    private static final String KEY_AMOUNTLOCK = "amount_lock";
    private static final String KEY_COST = "cost";
    private static final String KEY_LAST = "last";
    private static final String KEY_LOCK = "lock";
    private static final String KEY_SPEC = "spec";
    private static final String KEY_CODE = "code";
    private static final String KEY_BUYER = "buyer";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_COLUMN, KEY_ID, KEY_BUYER, KEY_FOLDER, KEY_NAME, KEY_AMOUNT, KEY_AMOUNTLAST, KEY_AMOUNTLOCK, KEY_COST, KEY_LAST, KEY_LOCK, KEY_SPEC, KEY_CODE, KEY_TIME, KEY_DATE};

    SQLiteDatabase db;
    Context context;

    String CREATION_TABLE = "CREATE TABLE Subjects ( "
            + "colum INTEGER PRIMARY KEY AUTOINCREMENT, " + "id INTEGER, " + "buyer TEXT, " + "folder TEXT, " + "name TEXT, " + "amount REAL, " + "amount_last REAL, " + "amount_lock REAL, "
            + "cost REAL, " + "last REAL, " + "lock REAL, " + "spec TEXT, " + "code TEXT, " + "time TEXT," + "date TEXT )";

    public SubjectSQLDatabaseHandler(Context context) {
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

    public void deleteSubject(SubjectListChildItem subject) {
        // Get reference to writable DB
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.delete(TABLE_NAME, "colum = ?", new String[]{String.valueOf(subject.getColumn())});
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases" + File.separator + "products.db");
        } catch (Exception e) {

        }
    }

    public SubjectListChildItem getSubject(int Column) {
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

        SubjectListChildItem subject = new SubjectListChildItem();
        subject.setColumn(cursor.getInt(0));
        subject.setId(cursor.getInt(1));
        subject.setBuyer(cursor.getString(2));
        subject.setFolder(cursor.getString(3));
        subject.setName(cursor.getString(4));
        subject.setAmount(cursor.getDouble(5));
        subject.setAmountLast(cursor.getDouble(6));
        subject.setAmountLock(cursor.getDouble(7));
        subject.setCost(cursor.getDouble(8));
        subject.setLast(cursor.getDouble(9));
        subject.setLock(cursor.getDouble(10));
        subject.setSpec(cursor.getString(11));
        subject.setCode(cursor.getString(12));
        subject.setTime(cursor.getString(13));
        subject.setDate(cursor.getString(14));

        return subject;
    }

    public SubjectListChildItem getSubjectById(int Id) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(Id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        SubjectListChildItem subject = new SubjectListChildItem();
        subject.setColumn(cursor.getInt(0));
        subject.setId(cursor.getInt(1));
        subject.setBuyer(cursor.getString(2));
        subject.setFolder(cursor.getString(3));
        subject.setName(cursor.getString(4));
        subject.setAmount(cursor.getDouble(5));
        subject.setAmountLast(cursor.getDouble(6));
        subject.setAmountLock(cursor.getDouble(7));
        subject.setCost(cursor.getDouble(8));
        subject.setLast(cursor.getDouble(9));
        subject.setLock(cursor.getDouble(10));
        subject.setSpec(cursor.getString(11));
        subject.setCode(cursor.getString(12));
        subject.setTime(cursor.getString(13));
        subject.setDate(cursor.getString(14));

        return subject;
    }

    public SubjectListChildItem getSubjectByName(String Name) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " name = ?", // c. selections
                new String[]{Name}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        SubjectListChildItem subject = new SubjectListChildItem();
        subject.setColumn(cursor.getInt(0));
        subject.setId(cursor.getInt(1));
        subject.setBuyer(cursor.getString(2));
        subject.setFolder(cursor.getString(3));
        subject.setName(cursor.getString(4));
        subject.setAmount(cursor.getDouble(5));
        subject.setAmountLast(cursor.getDouble(6));
        subject.setAmountLock(cursor.getDouble(7));
        subject.setCost(cursor.getDouble(8));
        subject.setLast(cursor.getDouble(9));
        subject.setLock(cursor.getDouble(10));
        subject.setSpec(cursor.getString(11));
        subject.setCode(cursor.getString(12));
        subject.setTime(cursor.getString(13));
        subject.setDate(cursor.getString(14));

        return subject;
    }

    public List<SubjectListChildItem> allSubjects() {

        List<SubjectListChildItem> subjects = new ArrayList<SubjectListChildItem>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SubjectListChildItem subject = null;

        if (cursor.moveToFirst()) {
            do {
                subject = new SubjectListChildItem();
                subject.setColumn(cursor.getInt(0));
                subject.setId(cursor.getInt(1));
                subject.setBuyer(cursor.getString(2));
                subject.setFolder(cursor.getString(3));
                subject.setName(cursor.getString(4));
                subject.setAmount(cursor.getDouble(5));
                subject.setAmountLast(cursor.getDouble(6));
                subject.setAmountLock(cursor.getDouble(7));
                subject.setCost(cursor.getDouble(8));
                subject.setLast(cursor.getDouble(9));
                subject.setLock(cursor.getDouble(10));
                subject.setSpec(cursor.getString(11));
                subject.setCode(cursor.getString(12));
                subject.setTime(cursor.getString(13));
                subject.setDate(cursor.getString(14));
                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        return subjects;
    }

    public List<String> allFolders() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, SubjectListChildItem> subjectsWithoutGroup = new HashMap<Integer, SubjectListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        SubjectListChildItem subject = null;
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

    public List<String> allSubjectsNames() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, SubjectListChildItem> subjectsWithoutGroup = new HashMap<Integer, SubjectListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        SubjectListChildItem subject = null;
        String folder = null;
        if (cursor.moveToFirst()) {
            do {
                folder = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        return folders;
    }

    public void addSubject(SubjectListChildItem subject) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, subject.getColumn());
        values.put(KEY_ID, subject.getId());
        values.put(KEY_BUYER, subject.getBuyer());
        values.put(KEY_FOLDER, subject.getFolder());
        values.put(KEY_NAME, subject.getName());
        values.put(KEY_AMOUNT, subject.getAmount());
        values.put(KEY_AMOUNTLAST, subject.getAmountLast());
        values.put(KEY_AMOUNTLOCK, subject.getAmountLock());
        values.put(KEY_COST, subject.getCost());
        values.put(KEY_LAST, subject.getLast());
        values.put(KEY_LOCK, subject.getLock());
        values.put(KEY_SPEC, subject.getSpec());
        values.put(KEY_CODE, subject.getCode());
        values.put(KEY_TIME, subject.getTime());
        values.put(KEY_DATE, subject.getDate());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases" + File.separator + "products.db");
        } catch (Exception e) {

        }
    }

    public int updateSubject(SubjectListChildItem subject) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, subject.getColumn());
        values.put(KEY_ID, subject.getId());
        values.put(KEY_BUYER, subject.getBuyer());
        values.put(KEY_FOLDER, subject.getFolder());
        values.put(KEY_NAME, subject.getName());
        values.put(KEY_AMOUNT, subject.getAmount());
        values.put(KEY_AMOUNTLAST, subject.getAmountLast());
        values.put(KEY_AMOUNTLOCK, subject.getAmountLock());
        values.put(KEY_COST, subject.getCost());
        values.put(KEY_LAST, subject.getLast());
        values.put(KEY_LOCK, subject.getLock());
        values.put(KEY_SPEC, subject.getSpec());
        values.put(KEY_CODE, subject.getCode());
        values.put(KEY_TIME, subject.getTime());
        values.put(KEY_DATE, subject.getDate());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "colum = ?", // selections
                new String[]{String.valueOf(subject.getColumn())});

        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "Databases" + File.separator + "products.db");
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

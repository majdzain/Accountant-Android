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

public class ExpenseSQLDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Expenses_Database.db";
    private static final String TABLE_NAME = "Expenses";
    private static final String KEY_COLUMN = "colum";
    private static final String KEY_CONSUMER = "consumer";
    private static final String KEY_NAME = "name";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_SPEC = "spec";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_COLUMN, KEY_AMOUNT, KEY_CONSUMER, KEY_NAME, KEY_CATEGORY, KEY_SPEC, KEY_CURRENCY, KEY_TIME, KEY_DATE};

    SQLiteDatabase db;
    Context context;

    String CREATION_TABLE = "CREATE TABLE Expenses ( "
            + "colum INTEGER PRIMARY KEY AUTOINCREMENT, " + "amount INTEGER, " + "consumer TEXT, " + "name TEXT, "
            + "category TEXT, " + "spec TEXT, " + "currency TEXT, " + "time TEXT," + "date TEXT )";

    public ExpenseSQLDatabaseHandler(Context context) {
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

    public void deleteExpense(ExpenseListChildItem expense) {
        // Get reference to writable DB
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.delete(TABLE_NAME, "colum = ?", new String[]{String.valueOf(expense.getColumn())});
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "expenses.db");
        } catch (Exception e) {

        }
    }

    public ExpenseListChildItem getExpense(int Column) {
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

        ExpenseListChildItem expense = new ExpenseListChildItem();
        expense.setColumn(cursor.getInt(0));
        expense.setAmount(cursor.getInt(1));
        expense.setConsumer(cursor.getString(2));
        expense.setExpense(cursor.getString(3));
        expense.setCategory(cursor.getString(4));
        expense.setSpec(cursor.getString(5));
        expense.setCurrency(cursor.getString(6));
        expense.setTime(cursor.getString(7));
        expense.setDate(cursor.getString(8));
        return expense;
    }

    public List<ExpenseListChildItem> allExpenses() {

        List<ExpenseListChildItem> expenses = new ArrayList<ExpenseListChildItem>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ExpenseListChildItem expense = null;

        if (cursor.moveToFirst()) {
            do {
                expense = new ExpenseListChildItem();
                expense.setColumn(cursor.getInt(0));
                expense.setAmount(cursor.getInt(1));
                expense.setConsumer(cursor.getString(2));
                expense.setExpense(cursor.getString(3));
                expense.setCategory(cursor.getString(4));
                expense.setSpec(cursor.getString(5));
                expense.setCurrency(cursor.getString(6));
                expense.setTime(cursor.getString(7));
                expense.setDate(cursor.getString(8));
                expenses.add(expense);
            } while (cursor.moveToNext());
        }
        return expenses;
    }

    public List<String> allCategories() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, ExpenseListChildItem> expensesWithoutGroup = new HashMap<Integer, ExpenseListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        ExpenseListChildItem expense = null;
        String folder = null;
        if (cursor.moveToFirst()) {
            do {
                folder = cursor.getString(4);
                if (!folders.contains(folder)) {
                    folders.add(folder);
                }
            } while (cursor.moveToNext());
        }
        return folders;
    }


    public void addExpense(ExpenseListChildItem expense) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, expense.getColumn());
        values.put(KEY_AMOUNT, expense.getAmount());
        values.put(KEY_CONSUMER, expense.getConsumer());
        values.put(KEY_NAME, expense.getExpense());
        values.put(KEY_CATEGORY, expense.getCategory());
        values.put(KEY_SPEC, expense.getSpec());
        values.put(KEY_CURRENCY, expense.getCurrency());
        values.put(KEY_TIME, expense.getTime());
        values.put(KEY_DATE, expense.getDate());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "expenses.db");
        } catch (Exception e) {

        }
    }

    public int updateExpense(ExpenseListChildItem expense) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, expense.getColumn());
        values.put(KEY_AMOUNT, expense.getAmount());
        values.put(KEY_CONSUMER, expense.getConsumer());
        values.put(KEY_NAME, expense.getExpense());
        values.put(KEY_CATEGORY, expense.getCategory());
        values.put(KEY_SPEC, expense.getSpec());
        values.put(KEY_CURRENCY, expense.getCurrency());
        values.put(KEY_TIME, expense.getTime());
        values.put(KEY_DATE, expense.getDate());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "colum = ?", // selections
                new String[]{String.valueOf(expense.getColumn())});

        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "expenses.db");
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

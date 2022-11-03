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

public class SupplierSQLDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Suppliers_Database.db";
    private static String DATABASE_PATH = "/data/data/com.zezoo.accountant/databases/";
    private static final String DATABASE_EX_PATH = "/Accountant/Databases/";
    private static final String TABLE_NAME = "Suppliers";
    private static final String KEY_COLUMN = "colum";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_NAME = "name";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_COMPANY_PH = "company_ph";
    private static final String KEY_NAME_PH = "name_ph";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CASH = "cash";
    private static final String KEY_SPEC = "spec";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_COLUMN, KEY_ACCOUNT, KEY_COMPANY, KEY_NAME, KEY_COMPANY_PH, KEY_NAME_PH, KEY_EMAIL, KEY_SPEC, KEY_CASH, KEY_CURRENCY, KEY_TIME, KEY_DATE};

    SQLiteDatabase db;
    Context context;

    String CREATION_TABLE = "CREATE TABLE Suppliers ( "
            + "colum INTEGER PRIMARY KEY AUTOINCREMENT, " + "account INTEGER, " + "company TEXT, " + "name TEXT, " + "company_ph TEXT, " + "name_ph TEXT, "
            + "email TEXT, " + "spec TEXT, " + "cash TEXT, " + "currency TEXT, " + "time TEXT," + "date TEXT )";

    public SupplierSQLDatabaseHandler(Context context) {
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

    public void deleteSupplier(SupplierListChildItem supplier) {
        // Get reference to writable DB
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.delete(TABLE_NAME, "colum = ?", new String[]{String.valueOf(supplier.getColumn())});
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "suppliers.db");
        } catch (Exception e) {

        }
    }

    public SupplierListChildItem getSupplier(int Column) {
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

        SupplierListChildItem supplier = new SupplierListChildItem();
        supplier.setColumn(cursor.getInt(0));
        supplier.setAccount(cursor.getInt(1));
        supplier.setCompany(cursor.getString(2));
        supplier.setSupplier(cursor.getString(3));
        supplier.setCompanyPho(cursor.getString(4));
        supplier.setSupplierPho(cursor.getString(5));
        supplier.setEmail(cursor.getString(6));
        supplier.setSpec(cursor.getString(7));
        supplier.setCash(cursor.getString(8));
        supplier.setCurrency(cursor.getString(9));
        supplier.setTime(cursor.getString(10));
        supplier.setDate(cursor.getString(11));
        return supplier;
    }

    public List<SupplierListChildItem> allSuppliers() {

        List<SupplierListChildItem> suppliers = new ArrayList<SupplierListChildItem>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SupplierListChildItem supplier = null;

        if (cursor.moveToFirst()) {
            do {
                supplier = new SupplierListChildItem();
                supplier.setColumn(cursor.getInt(0));
                supplier.setAccount(cursor.getInt(1));
                supplier.setCompany(cursor.getString(2));
                supplier.setSupplier(cursor.getString(3));
                supplier.setCompanyPho(cursor.getString(4));
                supplier.setSupplierPho(cursor.getString(5));
                supplier.setEmail(cursor.getString(6));
                supplier.setSpec(cursor.getString(7));
                supplier.setCash(cursor.getString(8));
                supplier.setCurrency(cursor.getString(9));
                supplier.setTime(cursor.getString(10));
                supplier.setDate(cursor.getString(11));
                suppliers.add(supplier);
            } while (cursor.moveToNext());
        }
        return suppliers;
    }

    public List<String> allCompanies() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, CustomerListChildItem> customersWithoutGroup = new HashMap<Integer, CustomerListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        CustomerListChildItem customer = null;
        String folder = null;
        if (cursor.moveToFirst()) {
            do {
                folder = cursor.getString(2);
                if (!folders.contains(folder)) {
                    folders.add(folder);
                }
            } while (cursor.moveToNext());
        }
        return folders;
    }

    public List<String> allSuppliersNames() {
        List<String> folders = new ArrayList<String>();
        HashMap<Integer, CustomerListChildItem> customersWithoutGroup = new HashMap<Integer, CustomerListChildItem>();
        String query1 = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        CustomerListChildItem customer = null;
        String folder = null;
        if (cursor.moveToFirst()) {
            do {
                folder = cursor.getString(3);
                folders.add(folder);
            } while (cursor.moveToNext());
        }
        return folders;
    }

    public void addSupplier(SupplierListChildItem supplier) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, supplier.getColumn());
        values.put(KEY_ACCOUNT, supplier.getAccount());
        values.put(KEY_COMPANY, supplier.getCompany());
        values.put(KEY_NAME, supplier.getSupplier());
        values.put(KEY_COMPANY_PH, supplier.getCompanyPho());
        values.put(KEY_NAME_PH, supplier.getSupplierPho());
        values.put(KEY_EMAIL, supplier.getEmail());
        values.put(KEY_SPEC, supplier.getSpec());
        values.put(KEY_CASH, supplier.getCash());
        values.put(KEY_CURRENCY, supplier.getCurrency());
        values.put(KEY_TIME, supplier.getTime());
        values.put(KEY_DATE, supplier.getDate());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "suppliers.db");
        } catch (Exception e) {

        }
    }

    public int updateSupplier(SupplierListChildItem supplier) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, supplier.getColumn());
        values.put(KEY_ACCOUNT, supplier.getAccount());
        values.put(KEY_COMPANY, supplier.getCompany());
        values.put(KEY_NAME, supplier.getSupplier());
        values.put(KEY_COMPANY_PH, supplier.getCompanyPho());
        values.put(KEY_NAME_PH, supplier.getSupplierPho());
        values.put(KEY_EMAIL, supplier.getEmail());
        values.put(KEY_SPEC, supplier.getSpec());
        values.put(KEY_CASH, supplier.getCash());
        values.put(KEY_CURRENCY, supplier.getCurrency());
        values.put(KEY_TIME, supplier.getTime());
        values.put(KEY_DATE, supplier.getDate());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "colum = ?", // selections
                new String[]{String.valueOf(supplier.getColumn())});

        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "suppliers.db");
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

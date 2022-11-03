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

public class CustomerSQLDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Customers_Database.db";
    private static String DATABASE_PATH = "/data/data/com.zezoo.accountant/databases/";
    private static final String DATABASE_EX_PATH = "/Accountant/Databases/";
    private static final String TABLE_NAME = "Customers";
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

    String CREATION_TABLE = "CREATE TABLE Customers ( "
            + "colum INTEGER PRIMARY KEY AUTOINCREMENT, " + "account INTEGER, " + "company TEXT, " + "name TEXT, " + "company_ph TEXT, " + "name_ph TEXT, "
            + "email TEXT, " + "spec TEXT, " + "cash TEXT, " + "currency TEXT, " + "time TEXT," + "date TEXT )";

    public CustomerSQLDatabaseHandler(Context context) {
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

    public void deleteCustomer(CustomerListChildItem customer) {
        // Get reference to writable DB
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.delete(TABLE_NAME, "colum = ?", new String[]{String.valueOf(customer.getColumn())});
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "customers.db");
        } catch (Exception e) {

        }
    }

    public CustomerListChildItem getCustomer(int Column) {
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

        CustomerListChildItem customer = new CustomerListChildItem();
        customer.setColumn(cursor.getInt(0));
        customer.setAccount(cursor.getInt(1));
        customer.setCompany(cursor.getString(2));
        customer.setCustomer(cursor.getString(3));
        customer.setCompanyPho(cursor.getString(4));
        customer.setCustomerPho(cursor.getString(5));
        customer.setEmail(cursor.getString(6));
        customer.setSpec(cursor.getString(7));
        customer.setCash(cursor.getString(8));
        customer.setCurrency(cursor.getString(9));
        customer.setTime(cursor.getString(10));
        customer.setDate(cursor.getString(11));
        return customer;
    }

    public List<CustomerListChildItem> allCustomers() {

        List<CustomerListChildItem> customers = new ArrayList<CustomerListChildItem>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CustomerListChildItem customer = null;

        if (cursor.moveToFirst()) {
            do {
                customer = new CustomerListChildItem();
                customer.setColumn(cursor.getInt(0));
                customer.setAccount(cursor.getInt(1));
                customer.setCompany(cursor.getString(2));
                customer.setCustomer(cursor.getString(3));
                customer.setCompanyPho(cursor.getString(4));
                customer.setCustomerPho(cursor.getString(5));
                customer.setEmail(cursor.getString(6));
                customer.setSpec(cursor.getString(7));
                customer.setCash(cursor.getString(8));
                customer.setCurrency(cursor.getString(9));
                customer.setTime(cursor.getString(10));
                customer.setDate(cursor.getString(11));
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        return customers;
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

    public List<String> allCustomersNames() {
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

    public void addCustomer(CustomerListChildItem customer) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, customer.getColumn());
        values.put(KEY_ACCOUNT, customer.getAccount());
        values.put(KEY_COMPANY, customer.getCompany());
        values.put(KEY_NAME, customer.getCustomer());
        values.put(KEY_COMPANY_PH, customer.getCompanyPho());
        values.put(KEY_NAME_PH, customer.getCustomerPho());
        values.put(KEY_EMAIL, customer.getEmail());
        values.put(KEY_SPEC, customer.getSpec());
        values.put(KEY_CASH, customer.getCash());
        values.put(KEY_CURRENCY, customer.getCurrency());
        values.put(KEY_TIME, customer.getTime());
        values.put(KEY_DATE, customer.getDate());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "customers.db");
        } catch (Exception e) {

        }
    }

    public int updateCustomer(CustomerListChildItem customer) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN, customer.getColumn());
        values.put(KEY_ACCOUNT, customer.getAccount());
        values.put(KEY_COMPANY, customer.getCompany());
        values.put(KEY_NAME, customer.getCustomer());
        values.put(KEY_COMPANY_PH, customer.getCompanyPho());
        values.put(KEY_NAME_PH, customer.getCustomerPho());
        values.put(KEY_EMAIL, customer.getEmail());
        values.put(KEY_SPEC, customer.getSpec());
        values.put(KEY_CASH, customer.getCash());
        values.put(KEY_CURRENCY, customer.getCurrency());
        values.put(KEY_TIME, customer.getTime());
        values.put(KEY_DATE, customer.getDate());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "colum = ?", // selections
                new String[]{String.valueOf(customer.getColumn())});

        db.close();
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "Accountant");
            if (!f.exists())
                f.mkdirs();
            backup(Environment.getExternalStorageDirectory() + File.separator + "Accountant" + File.separator + "customers.db");
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

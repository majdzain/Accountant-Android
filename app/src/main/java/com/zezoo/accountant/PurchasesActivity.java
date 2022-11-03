package com.zezoo.accountant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;

public class PurchasesActivity extends Fragment implements SearchView.OnQueryTextListener {
    Context context;
    FloatingActionButton fab;
    Resources res;
    BillSQLDatabaseHandler db;
    ExpandableListView expListView;
    CustomBillExpandableListAdapter expandableListAdapter;
    ArrayList<String> listGroupTitles;
    HashMap<String, List<BillListChildItem>> listChildData;
    ArrayList<BillListChildItem> listAllBills;
    CustomSearchBillsAdapter customSearchBillsAdapter;
    View view;
    ListView searchList;
    SearchView searchView;
    private int REQUEST_FORM = 1;
    LinearLayout linear_info;
    boolean isCheckEnabled = false;
    ArrayList<Boolean> groupCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childCheckStates;
    TextView date, type, code, name, from, to, cash, currency, total, final_, txt_from, txt_to;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_bills, container, false);
        context = view.getContext();
        setHasOptionsMenu(true);
        res = context.getResources();
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionBill);
        fab.setColorNormal(res.getColor(R.color.blue));
        fab.setColorPressed(res.getColor(R.color.blue));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 1);
                startActivityForResult(i, REQUEST_FORM);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        return view;
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new BillSQLDatabaseHandler(context);
    }

    void createListFromSQL() {
        // list all bills
        List<BillListChildItem> bills = db.allBills();
        List<String> folders = db.allSupplierFolders();
        if (bills != null) {
            createList(folders, bills);
        }
    }

    private void createListVaribales(){
        List<BillListChildItem> bills = db.allBills();
        List<String> folders = db.allSupplierFolders();
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
        }
        int[] itemsBillsColumns = new int[bills.size()];
        String[] itemsBillsDates = new String[bills.size()];
        String[] itemsBillsTimes = new String[bills.size()];
        String[] itemsBillsFroms = new String[bills.size()];
        String[] itemsBillsTos = new String[bills.size()];
        String[] itemsBillsCashes = new String[bills.size()];
        String[] itemsBillsCurrencies = new String[bills.size()];
        double[] itemsBillsDiscounts = new double[bills.size()];
        double[] itemsBillsAdditions = new double[bills.size()];
        double[] itemsBillsTotals = new double[bills.size()];
        double[] itemsBillsFinals = new double[bills.size()];
        int[] itemsBillsTypes = new int[bills.size()];


        String[] itemsBillP1s = new String[bills.size()];
        String[] itemsBillP2s = new String[bills.size()];
        String[] itemsBillP3s = new String[bills.size()];
        String[] itemsBillP4s = new String[bills.size()];
        String[] itemsBillP5s = new String[bills.size()];
        String[] itemsBillP6s = new String[bills.size()];
        String[] itemsBillP7s = new String[bills.size()];
        String[] itemsBillP8s = new String[bills.size()];
        String[] itemsBillP9s = new String[bills.size()];
        String[] itemsBillP10s = new String[bills.size()];
        String[] itemsBillP11s = new String[bills.size()];
        String[] itemsBillP12s = new String[bills.size()];
        String[] itemsBillP13s = new String[bills.size()];
        String[] itemsBillP14s = new String[bills.size()];
        String[] itemsBillP15s = new String[bills.size()];
        String[] itemsBillP16s = new String[bills.size()];
        String[] itemsBillP17s = new String[bills.size()];
        String[] itemsBillP18s = new String[bills.size()];
        String[] itemsBillP19s = new String[bills.size()];
        String[] itemsBillP20s = new String[bills.size()];
        String[] itemsBillP21s = new String[bills.size()];
        String[] itemsBillP22s = new String[bills.size()];
        String[] itemsBillP23s = new String[bills.size()];
        String[] itemsBillP24s = new String[bills.size()];
        String[] itemsBillP25s = new String[bills.size()];
        String[] itemsBillP26s = new String[bills.size()];
        String[] itemsBillP27s = new String[bills.size()];
        String[] itemsBillP28s = new String[bills.size()];
        String[] itemsBillP29s = new String[bills.size()];
        String[] itemsBillP30s = new String[bills.size()];
        String[] itemsBillP31s = new String[bills.size()];
        String[] itemsBillP32s = new String[bills.size()];
        String[] itemsBillP33s = new String[bills.size()];
        String[] itemsBillP34s = new String[bills.size()];
        String[] itemsBillP35s = new String[bills.size()];
        String[] itemsBillP36s = new String[bills.size()];
        String[] itemsBillP37s = new String[bills.size()];
        String[] itemsBillP38s = new String[bills.size()];
        String[] itemsBillP39s = new String[bills.size()];
        String[] itemsBillP40s = new String[bills.size()];
        String[] itemsBillP41s = new String[bills.size()];
        String[] itemsBillP42s = new String[bills.size()];
        String[] itemsBillP43s = new String[bills.size()];
        String[] itemsBillP44s = new String[bills.size()];
        String[] itemsBillP45s = new String[bills.size()];
        String[] itemsBillP46s = new String[bills.size()];
        String[] itemsBillP47s = new String[bills.size()];
        String[] itemsBillP48s = new String[bills.size()];
        String[] itemsBillP49s = new String[bills.size()];
        String[] itemsBillP50s = new String[bills.size()];

        double[] itemsBillA1s = new double[bills.size()];
        double[] itemsBillA2s = new double[bills.size()];
        double[] itemsBillA3s = new double[bills.size()];
        double[] itemsBillA4s = new double[bills.size()];
        double[] itemsBillA5s = new double[bills.size()];
        double[] itemsBillA6s = new double[bills.size()];
        double[] itemsBillA7s = new double[bills.size()];
        double[] itemsBillA8s = new double[bills.size()];
        double[] itemsBillA9s = new double[bills.size()];
        double[] itemsBillA10s = new double[bills.size()];
        double[] itemsBillA11s = new double[bills.size()];
        double[] itemsBillA12s = new double[bills.size()];
        double[] itemsBillA13s = new double[bills.size()];
        double[] itemsBillA14s = new double[bills.size()];
        double[] itemsBillA15s = new double[bills.size()];
        double[] itemsBillA16s = new double[bills.size()];
        double[] itemsBillA17s = new double[bills.size()];
        double[] itemsBillA18s = new double[bills.size()];
        double[] itemsBillA19s = new double[bills.size()];
        double[] itemsBillA20s = new double[bills.size()];
        double[] itemsBillA21s = new double[bills.size()];
        double[] itemsBillA22s = new double[bills.size()];
        double[] itemsBillA23s = new double[bills.size()];
        double[] itemsBillA24s = new double[bills.size()];
        double[] itemsBillA25s = new double[bills.size()];
        double[] itemsBillA26s = new double[bills.size()];
        double[] itemsBillA27s = new double[bills.size()];
        double[] itemsBillA28s = new double[bills.size()];
        double[] itemsBillA29s = new double[bills.size()];
        double[] itemsBillA30s = new double[bills.size()];
        double[] itemsBillA31s = new double[bills.size()];
        double[] itemsBillA32s = new double[bills.size()];
        double[] itemsBillA33s = new double[bills.size()];
        double[] itemsBillA34s = new double[bills.size()];
        double[] itemsBillA35s = new double[bills.size()];
        double[] itemsBillA36s = new double[bills.size()];
        double[] itemsBillA37s = new double[bills.size()];
        double[] itemsBillA38s = new double[bills.size()];
        double[] itemsBillA39s = new double[bills.size()];
        double[] itemsBillA40s = new double[bills.size()];
        double[] itemsBillA41s = new double[bills.size()];
        double[] itemsBillA42s = new double[bills.size()];
        double[] itemsBillA43s = new double[bills.size()];
        double[] itemsBillA44s = new double[bills.size()];
        double[] itemsBillA45s = new double[bills.size()];
        double[] itemsBillA46s = new double[bills.size()];
        double[] itemsBillA47s = new double[bills.size()];
        double[] itemsBillA48s = new double[bills.size()];
        double[] itemsBillA49s = new double[bills.size()];
        double[] itemsBillA50s = new double[bills.size()];

        double[] itemsBillO1s = new double[bills.size()];
        double[] itemsBillO2s = new double[bills.size()];
        double[] itemsBillO3s = new double[bills.size()];
        double[] itemsBillO4s = new double[bills.size()];
        double[] itemsBillO5s = new double[bills.size()];
        double[] itemsBillO6s = new double[bills.size()];
        double[] itemsBillO7s = new double[bills.size()];
        double[] itemsBillO8s = new double[bills.size()];
        double[] itemsBillO9s = new double[bills.size()];
        double[] itemsBillO10s = new double[bills.size()];
        double[] itemsBillO11s = new double[bills.size()];
        double[] itemsBillO12s = new double[bills.size()];
        double[] itemsBillO13s = new double[bills.size()];
        double[] itemsBillO14s = new double[bills.size()];
        double[] itemsBillO15s = new double[bills.size()];
        double[] itemsBillO16s = new double[bills.size()];
        double[] itemsBillO17s = new double[bills.size()];
        double[] itemsBillO18s = new double[bills.size()];
        double[] itemsBillO19s = new double[bills.size()];
        double[] itemsBillO20s = new double[bills.size()];
        double[] itemsBillO21s = new double[bills.size()];
        double[] itemsBillO22s = new double[bills.size()];
        double[] itemsBillO23s = new double[bills.size()];
        double[] itemsBillO24s = new double[bills.size()];
        double[] itemsBillO25s = new double[bills.size()];
        double[] itemsBillO26s = new double[bills.size()];
        double[] itemsBillO27s = new double[bills.size()];
        double[] itemsBillO28s = new double[bills.size()];
        double[] itemsBillO29s = new double[bills.size()];
        double[] itemsBillO30s = new double[bills.size()];
        double[] itemsBillO31s = new double[bills.size()];
        double[] itemsBillO32s = new double[bills.size()];
        double[] itemsBillO33s = new double[bills.size()];
        double[] itemsBillO34s = new double[bills.size()];
        double[] itemsBillO35s = new double[bills.size()];
        double[] itemsBillO36s = new double[bills.size()];
        double[] itemsBillO37s = new double[bills.size()];
        double[] itemsBillO38s = new double[bills.size()];
        double[] itemsBillO39s = new double[bills.size()];
        double[] itemsBillO40s = new double[bills.size()];
        double[] itemsBillO41s = new double[bills.size()];
        double[] itemsBillO42s = new double[bills.size()];
        double[] itemsBillO43s = new double[bills.size()];
        double[] itemsBillO44s = new double[bills.size()];
        double[] itemsBillO45s = new double[bills.size()];
        double[] itemsBillO46s = new double[bills.size()];
        double[] itemsBillO47s = new double[bills.size()];
        double[] itemsBillO48s = new double[bills.size()];
        double[] itemsBillO49s = new double[bills.size()];
        double[] itemsBillO50s = new double[bills.size()];

        double[] itemsBillU1s = new double[bills.size()];
        double[] itemsBillU2s = new double[bills.size()];
        double[] itemsBillU3s = new double[bills.size()];
        double[] itemsBillU4s = new double[bills.size()];
        double[] itemsBillU5s = new double[bills.size()];
        double[] itemsBillU6s = new double[bills.size()];
        double[] itemsBillU7s = new double[bills.size()];
        double[] itemsBillU8s = new double[bills.size()];
        double[] itemsBillU9s = new double[bills.size()];
        double[] itemsBillU10s = new double[bills.size()];
        double[] itemsBillU11s = new double[bills.size()];
        double[] itemsBillU12s = new double[bills.size()];
        double[] itemsBillU13s = new double[bills.size()];
        double[] itemsBillU14s = new double[bills.size()];
        double[] itemsBillU15s = new double[bills.size()];
        double[] itemsBillU16s = new double[bills.size()];
        double[] itemsBillU17s = new double[bills.size()];
        double[] itemsBillU18s = new double[bills.size()];
        double[] itemsBillU19s = new double[bills.size()];
        double[] itemsBillU20s = new double[bills.size()];
        double[] itemsBillU21s = new double[bills.size()];
        double[] itemsBillU22s = new double[bills.size()];
        double[] itemsBillU23s = new double[bills.size()];
        double[] itemsBillU24s = new double[bills.size()];
        double[] itemsBillU25s = new double[bills.size()];
        double[] itemsBillU26s = new double[bills.size()];
        double[] itemsBillU27s = new double[bills.size()];
        double[] itemsBillU28s = new double[bills.size()];
        double[] itemsBillU29s = new double[bills.size()];
        double[] itemsBillU30s = new double[bills.size()];
        double[] itemsBillU31s = new double[bills.size()];
        double[] itemsBillU32s = new double[bills.size()];
        double[] itemsBillU33s = new double[bills.size()];
        double[] itemsBillU34s = new double[bills.size()];
        double[] itemsBillU35s = new double[bills.size()];
        double[] itemsBillU36s = new double[bills.size()];
        double[] itemsBillU37s = new double[bills.size()];
        double[] itemsBillU38s = new double[bills.size()];
        double[] itemsBillU39s = new double[bills.size()];
        double[] itemsBillU40s = new double[bills.size()];
        double[] itemsBillU41s = new double[bills.size()];
        double[] itemsBillU42s = new double[bills.size()];
        double[] itemsBillU43s = new double[bills.size()];
        double[] itemsBillU44s = new double[bills.size()];
        double[] itemsBillU45s = new double[bills.size()];
        double[] itemsBillU46s = new double[bills.size()];
        double[] itemsBillU47s = new double[bills.size()];
        double[] itemsBillU48s = new double[bills.size()];
        double[] itemsBillU49s = new double[bills.size()];
        double[] itemsBillU50s = new double[bills.size()];

        double[] itemsBillT1s = new double[bills.size()];
        double[] itemsBillT2s = new double[bills.size()];
        double[] itemsBillT3s = new double[bills.size()];
        double[] itemsBillT4s = new double[bills.size()];
        double[] itemsBillT5s = new double[bills.size()];
        double[] itemsBillT6s = new double[bills.size()];
        double[] itemsBillT7s = new double[bills.size()];
        double[] itemsBillT8s = new double[bills.size()];
        double[] itemsBillT9s = new double[bills.size()];
        double[] itemsBillT10s = new double[bills.size()];
        double[] itemsBillT11s = new double[bills.size()];
        double[] itemsBillT12s = new double[bills.size()];
        double[] itemsBillT13s = new double[bills.size()];
        double[] itemsBillT14s = new double[bills.size()];
        double[] itemsBillT15s = new double[bills.size()];
        double[] itemsBillT16s = new double[bills.size()];
        double[] itemsBillT17s = new double[bills.size()];
        double[] itemsBillT18s = new double[bills.size()];
        double[] itemsBillT19s = new double[bills.size()];
        double[] itemsBillT20s = new double[bills.size()];
        double[] itemsBillT21s = new double[bills.size()];
        double[] itemsBillT22s = new double[bills.size()];
        double[] itemsBillT23s = new double[bills.size()];
        double[] itemsBillT24s = new double[bills.size()];
        double[] itemsBillT25s = new double[bills.size()];
        double[] itemsBillT26s = new double[bills.size()];
        double[] itemsBillT27s = new double[bills.size()];
        double[] itemsBillT28s = new double[bills.size()];
        double[] itemsBillT29s = new double[bills.size()];
        double[] itemsBillT30s = new double[bills.size()];
        double[] itemsBillT31s = new double[bills.size()];
        double[] itemsBillT32s = new double[bills.size()];
        double[] itemsBillT33s = new double[bills.size()];
        double[] itemsBillT34s = new double[bills.size()];
        double[] itemsBillT35s = new double[bills.size()];
        double[] itemsBillT36s = new double[bills.size()];
        double[] itemsBillT37s = new double[bills.size()];
        double[] itemsBillT38s = new double[bills.size()];
        double[] itemsBillT39s = new double[bills.size()];
        double[] itemsBillT40s = new double[bills.size()];
        double[] itemsBillT41s = new double[bills.size()];
        double[] itemsBillT42s = new double[bills.size()];
        double[] itemsBillT43s = new double[bills.size()];
        double[] itemsBillT44s = new double[bills.size()];
        double[] itemsBillT45s = new double[bills.size()];
        double[] itemsBillT46s = new double[bills.size()];
        double[] itemsBillT47s = new double[bills.size()];
        double[] itemsBillT48s = new double[bills.size()];
        double[] itemsBillT49s = new double[bills.size()];
        double[] itemsBillT50s = new double[bills.size()];

        String[] itemsBillS1s = new String[bills.size()];
        String[] itemsBillS2s = new String[bills.size()];
        String[] itemsBillS3s = new String[bills.size()];
        String[] itemsBillS4s = new String[bills.size()];
        String[] itemsBillS5s = new String[bills.size()];
        String[] itemsBillS6s = new String[bills.size()];
        String[] itemsBillS7s = new String[bills.size()];
        String[] itemsBillS8s = new String[bills.size()];
        String[] itemsBillS9s = new String[bills.size()];
        String[] itemsBillS10s = new String[bills.size()];
        String[] itemsBillS11s = new String[bills.size()];
        String[] itemsBillS12s = new String[bills.size()];
        String[] itemsBillS13s = new String[bills.size()];
        String[] itemsBillS14s = new String[bills.size()];
        String[] itemsBillS15s = new String[bills.size()];
        String[] itemsBillS16s = new String[bills.size()];
        String[] itemsBillS17s = new String[bills.size()];
        String[] itemsBillS18s = new String[bills.size()];
        String[] itemsBillS19s = new String[bills.size()];
        String[] itemsBillS20s = new String[bills.size()];
        String[] itemsBillS21s = new String[bills.size()];
        String[] itemsBillS22s = new String[bills.size()];
        String[] itemsBillS23s = new String[bills.size()];
        String[] itemsBillS24s = new String[bills.size()];
        String[] itemsBillS25s = new String[bills.size()];
        String[] itemsBillS26s = new String[bills.size()];
        String[] itemsBillS27s = new String[bills.size()];
        String[] itemsBillS28s = new String[bills.size()];
        String[] itemsBillS29s = new String[bills.size()];
        String[] itemsBillS30s = new String[bills.size()];
        String[] itemsBillS31s = new String[bills.size()];
        String[] itemsBillS32s = new String[bills.size()];
        String[] itemsBillS33s = new String[bills.size()];
        String[] itemsBillS34s = new String[bills.size()];
        String[] itemsBillS35s = new String[bills.size()];
        String[] itemsBillS36s = new String[bills.size()];
        String[] itemsBillS37s = new String[bills.size()];
        String[] itemsBillS38s = new String[bills.size()];
        String[] itemsBillS39s = new String[bills.size()];
        String[] itemsBillS40s = new String[bills.size()];
        String[] itemsBillS41s = new String[bills.size()];
        String[] itemsBillS42s = new String[bills.size()];
        String[] itemsBillS43s = new String[bills.size()];
        String[] itemsBillS44s = new String[bills.size()];
        String[] itemsBillS45s = new String[bills.size()];
        String[] itemsBillS46s = new String[bills.size()];
        String[] itemsBillS47s = new String[bills.size()];
        String[] itemsBillS48s = new String[bills.size()];
        String[] itemsBillS49s = new String[bills.size()];
        String[] itemsBillS50s = new String[bills.size()];

        int[] itemsBillCodes = new int[bills.size()];
        String[] itemsBillNames = new String[bills.size()];
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getType() == 1) {
                itemsBillsColumns[i] = bills.get(i).getCOLUMN();
                itemsBillsDates[i] = bills.get(i).getDate();
                itemsBillsTimes[i] = bills.get(i).getTime();
                itemsBillsFroms[i] = bills.get(i).getFrom();
                itemsBillsTos[i] = bills.get(i).getTo();
                itemsBillsCashes[i] = bills.get(i).getCash();
                itemsBillsCurrencies[i] = bills.get(i).getCurrency();
                itemsBillsDiscounts[i] = bills.get(i).getDiscount();
                itemsBillsAdditions[i] = bills.get(i).getAddition();
                itemsBillsTotals[i] = bills.get(i).getTotal();
                itemsBillsFinals[i] = bills.get(i).getFinal();
                itemsBillsTypes[i] = bills.get(i).getType();

                itemsBillP1s[i] = bills.get(i).getP1();
                itemsBillP2s[i] = bills.get(i).getP2();
                itemsBillP3s[i] = bills.get(i).getP3();
                itemsBillP4s[i] = bills.get(i).getP4();
                itemsBillP5s[i] = bills.get(i).getP5();
                itemsBillP6s[i] = bills.get(i).getP6();
                itemsBillP7s[i] = bills.get(i).getP7();
                itemsBillP8s[i] = bills.get(i).getP8();
                itemsBillP9s[i] = bills.get(i).getP9();
                itemsBillP10s[i] = bills.get(i).getP10();
                itemsBillP11s[i] = bills.get(i).getP11();
                itemsBillP12s[i] = bills.get(i).getP12();
                itemsBillP13s[i] = bills.get(i).getP13();
                itemsBillP14s[i] = bills.get(i).getP14();
                itemsBillP15s[i] = bills.get(i).getP15();
                itemsBillP16s[i] = bills.get(i).getP16();
                itemsBillP17s[i] = bills.get(i).getP17();
                itemsBillP18s[i] = bills.get(i).getP18();
                itemsBillP19s[i] = bills.get(i).getP19();
                itemsBillP20s[i] = bills.get(i).getP20();
                itemsBillP21s[i] = bills.get(i).getP21();
                itemsBillP22s[i] = bills.get(i).getP22();
                itemsBillP23s[i] = bills.get(i).getP23();
                itemsBillP24s[i] = bills.get(i).getP24();
                itemsBillP25s[i] = bills.get(i).getP25();
                itemsBillP26s[i] = bills.get(i).getP26();
                itemsBillP27s[i] = bills.get(i).getP27();
                itemsBillP28s[i] = bills.get(i).getP28();
                itemsBillP29s[i] = bills.get(i).getP29();
                itemsBillP30s[i] = bills.get(i).getP30();
                itemsBillP31s[i] = bills.get(i).getP31();
                itemsBillP32s[i] = bills.get(i).getP32();
                itemsBillP33s[i] = bills.get(i).getP33();
                itemsBillP34s[i] = bills.get(i).getP34();
                itemsBillP35s[i] = bills.get(i).getP35();
                itemsBillP36s[i] = bills.get(i).getP36();
                itemsBillP37s[i] = bills.get(i).getP37();
                itemsBillP38s[i] = bills.get(i).getP38();
                itemsBillP39s[i] = bills.get(i).getP39();
                itemsBillP40s[i] = bills.get(i).getP40();
                itemsBillP41s[i] = bills.get(i).getP41();
                itemsBillP42s[i] = bills.get(i).getP42();
                itemsBillP43s[i] = bills.get(i).getP43();
                itemsBillP44s[i] = bills.get(i).getP44();
                itemsBillP45s[i] = bills.get(i).getP45();
                itemsBillP46s[i] = bills.get(i).getP46();
                itemsBillP47s[i] = bills.get(i).getP47();
                itemsBillP48s[i] = bills.get(i).getP48();
                itemsBillP49s[i] = bills.get(i).getP49();
                itemsBillP50s[i] = bills.get(i).getP50();

                itemsBillA1s[i] = bills.get(i).getA1();
                itemsBillA2s[i] = bills.get(i).getA2();
                itemsBillA3s[i] = bills.get(i).getA3();
                itemsBillA4s[i] = bills.get(i).getA4();
                itemsBillA5s[i] = bills.get(i).getA5();
                itemsBillA6s[i] = bills.get(i).getA6();
                itemsBillA7s[i] = bills.get(i).getA7();
                itemsBillA8s[i] = bills.get(i).getA8();
                itemsBillA9s[i] = bills.get(i).getA9();
                itemsBillA10s[i] = bills.get(i).getA10();
                itemsBillA11s[i] = bills.get(i).getA11();
                itemsBillA12s[i] = bills.get(i).getA12();
                itemsBillA13s[i] = bills.get(i).getA13();
                itemsBillA14s[i] = bills.get(i).getA14();
                itemsBillA15s[i] = bills.get(i).getA15();
                itemsBillA16s[i] = bills.get(i).getA16();
                itemsBillA17s[i] = bills.get(i).getA17();
                itemsBillA18s[i] = bills.get(i).getA18();
                itemsBillA19s[i] = bills.get(i).getA19();
                itemsBillA20s[i] = bills.get(i).getA20();
                itemsBillA21s[i] = bills.get(i).getA21();
                itemsBillA22s[i] = bills.get(i).getA22();
                itemsBillA23s[i] = bills.get(i).getA23();
                itemsBillA24s[i] = bills.get(i).getA24();
                itemsBillA25s[i] = bills.get(i).getA25();
                itemsBillA26s[i] = bills.get(i).getA26();
                itemsBillA27s[i] = bills.get(i).getA27();
                itemsBillA28s[i] = bills.get(i).getA28();
                itemsBillA29s[i] = bills.get(i).getA29();
                itemsBillA30s[i] = bills.get(i).getA30();
                itemsBillA31s[i] = bills.get(i).getA31();
                itemsBillA32s[i] = bills.get(i).getA32();
                itemsBillA33s[i] = bills.get(i).getA33();
                itemsBillA34s[i] = bills.get(i).getA34();
                itemsBillA35s[i] = bills.get(i).getA35();
                itemsBillA36s[i] = bills.get(i).getA36();
                itemsBillA37s[i] = bills.get(i).getA37();
                itemsBillA38s[i] = bills.get(i).getA38();
                itemsBillA39s[i] = bills.get(i).getA39();
                itemsBillA40s[i] = bills.get(i).getA40();
                itemsBillA41s[i] = bills.get(i).getA41();
                itemsBillA42s[i] = bills.get(i).getA42();
                itemsBillA43s[i] = bills.get(i).getA43();
                itemsBillA44s[i] = bills.get(i).getA44();
                itemsBillA45s[i] = bills.get(i).getA45();
                itemsBillA46s[i] = bills.get(i).getA46();
                itemsBillA47s[i] = bills.get(i).getA47();
                itemsBillA48s[i] = bills.get(i).getA48();
                itemsBillA49s[i] = bills.get(i).getA49();
                itemsBillA50s[i] = bills.get(i).getA50();

                itemsBillO1s[i] = bills.get(i).getO1();
                itemsBillO2s[i] = bills.get(i).getO2();
                itemsBillO3s[i] = bills.get(i).getO3();
                itemsBillO4s[i] = bills.get(i).getO4();
                itemsBillO5s[i] = bills.get(i).getO5();
                itemsBillO6s[i] = bills.get(i).getO6();
                itemsBillO7s[i] = bills.get(i).getO7();
                itemsBillO8s[i] = bills.get(i).getO8();
                itemsBillO9s[i] = bills.get(i).getO9();
                itemsBillO10s[i] = bills.get(i).getO10();
                itemsBillO11s[i] = bills.get(i).getO11();
                itemsBillO12s[i] = bills.get(i).getO12();
                itemsBillO13s[i] = bills.get(i).getO13();
                itemsBillO14s[i] = bills.get(i).getO14();
                itemsBillO15s[i] = bills.get(i).getO15();
                itemsBillO16s[i] = bills.get(i).getO16();
                itemsBillO17s[i] = bills.get(i).getO17();
                itemsBillO18s[i] = bills.get(i).getO18();
                itemsBillO19s[i] = bills.get(i).getO19();
                itemsBillO20s[i] = bills.get(i).getO20();
                itemsBillO21s[i] = bills.get(i).getO21();
                itemsBillO22s[i] = bills.get(i).getO22();
                itemsBillO23s[i] = bills.get(i).getO23();
                itemsBillO24s[i] = bills.get(i).getO24();
                itemsBillO25s[i] = bills.get(i).getO25();
                itemsBillO26s[i] = bills.get(i).getO26();
                itemsBillO27s[i] = bills.get(i).getO27();
                itemsBillO28s[i] = bills.get(i).getO28();
                itemsBillO29s[i] = bills.get(i).getO29();
                itemsBillO30s[i] = bills.get(i).getO30();
                itemsBillO31s[i] = bills.get(i).getO31();
                itemsBillO32s[i] = bills.get(i).getO32();
                itemsBillO33s[i] = bills.get(i).getO33();
                itemsBillO34s[i] = bills.get(i).getO34();
                itemsBillO35s[i] = bills.get(i).getO35();
                itemsBillO36s[i] = bills.get(i).getO36();
                itemsBillO37s[i] = bills.get(i).getO37();
                itemsBillO38s[i] = bills.get(i).getO38();
                itemsBillO39s[i] = bills.get(i).getO39();
                itemsBillO40s[i] = bills.get(i).getO40();
                itemsBillO41s[i] = bills.get(i).getO41();
                itemsBillO42s[i] = bills.get(i).getO42();
                itemsBillO43s[i] = bills.get(i).getO43();
                itemsBillO44s[i] = bills.get(i).getO44();
                itemsBillO45s[i] = bills.get(i).getO45();
                itemsBillO46s[i] = bills.get(i).getO46();
                itemsBillO47s[i] = bills.get(i).getO47();
                itemsBillO48s[i] = bills.get(i).getO48();
                itemsBillO49s[i] = bills.get(i).getO49();
                itemsBillO50s[i] = bills.get(i).getO50();

                itemsBillU1s[i] = bills.get(i).getU1();
                itemsBillU2s[i] = bills.get(i).getU2();
                itemsBillU3s[i] = bills.get(i).getU3();
                itemsBillU4s[i] = bills.get(i).getU4();
                itemsBillU5s[i] = bills.get(i).getU5();
                itemsBillU6s[i] = bills.get(i).getU6();
                itemsBillU7s[i] = bills.get(i).getU7();
                itemsBillU8s[i] = bills.get(i).getU8();
                itemsBillU9s[i] = bills.get(i).getU9();
                itemsBillU10s[i] = bills.get(i).getU10();
                itemsBillU11s[i] = bills.get(i).getU11();
                itemsBillU12s[i] = bills.get(i).getU12();
                itemsBillU13s[i] = bills.get(i).getU13();
                itemsBillU14s[i] = bills.get(i).getU14();
                itemsBillU15s[i] = bills.get(i).getU15();
                itemsBillU16s[i] = bills.get(i).getU16();
                itemsBillU17s[i] = bills.get(i).getU17();
                itemsBillU18s[i] = bills.get(i).getU18();
                itemsBillU19s[i] = bills.get(i).getU19();
                itemsBillU20s[i] = bills.get(i).getU20();
                itemsBillU21s[i] = bills.get(i).getU21();
                itemsBillU22s[i] = bills.get(i).getU22();
                itemsBillU23s[i] = bills.get(i).getU23();
                itemsBillU24s[i] = bills.get(i).getU24();
                itemsBillU25s[i] = bills.get(i).getU25();
                itemsBillU26s[i] = bills.get(i).getU26();
                itemsBillU27s[i] = bills.get(i).getU27();
                itemsBillU28s[i] = bills.get(i).getU28();
                itemsBillU29s[i] = bills.get(i).getU29();
                itemsBillU30s[i] = bills.get(i).getU30();
                itemsBillU31s[i] = bills.get(i).getU31();
                itemsBillU32s[i] = bills.get(i).getU32();
                itemsBillU33s[i] = bills.get(i).getU33();
                itemsBillU34s[i] = bills.get(i).getU34();
                itemsBillU35s[i] = bills.get(i).getU35();
                itemsBillU36s[i] = bills.get(i).getU36();
                itemsBillU37s[i] = bills.get(i).getU37();
                itemsBillU38s[i] = bills.get(i).getU38();
                itemsBillU39s[i] = bills.get(i).getU39();
                itemsBillU40s[i] = bills.get(i).getU40();
                itemsBillU41s[i] = bills.get(i).getU41();
                itemsBillU42s[i] = bills.get(i).getU42();
                itemsBillU43s[i] = bills.get(i).getU43();
                itemsBillU44s[i] = bills.get(i).getU44();
                itemsBillU45s[i] = bills.get(i).getU45();
                itemsBillU46s[i] = bills.get(i).getU46();
                itemsBillU47s[i] = bills.get(i).getU47();
                itemsBillU48s[i] = bills.get(i).getU48();
                itemsBillU49s[i] = bills.get(i).getU49();
                itemsBillU50s[i] = bills.get(i).getU50();

                itemsBillT1s[i] = bills.get(i).getT1();
                itemsBillT2s[i] = bills.get(i).getT2();
                itemsBillT3s[i] = bills.get(i).getT3();
                itemsBillT4s[i] = bills.get(i).getT4();
                itemsBillT5s[i] = bills.get(i).getT5();
                itemsBillT6s[i] = bills.get(i).getT6();
                itemsBillT7s[i] = bills.get(i).getT7();
                itemsBillT8s[i] = bills.get(i).getT8();
                itemsBillT9s[i] = bills.get(i).getT9();
                itemsBillT10s[i] = bills.get(i).getT10();
                itemsBillT11s[i] = bills.get(i).getT11();
                itemsBillT12s[i] = bills.get(i).getT12();
                itemsBillT13s[i] = bills.get(i).getT13();
                itemsBillT14s[i] = bills.get(i).getT14();
                itemsBillT15s[i] = bills.get(i).getT15();
                itemsBillT16s[i] = bills.get(i).getT16();
                itemsBillT17s[i] = bills.get(i).getT17();
                itemsBillT18s[i] = bills.get(i).getT18();
                itemsBillT19s[i] = bills.get(i).getT19();
                itemsBillT20s[i] = bills.get(i).getT20();
                itemsBillT21s[i] = bills.get(i).getT21();
                itemsBillT22s[i] = bills.get(i).getT22();
                itemsBillT23s[i] = bills.get(i).getT23();
                itemsBillT24s[i] = bills.get(i).getT24();
                itemsBillT25s[i] = bills.get(i).getT25();
                itemsBillT26s[i] = bills.get(i).getT26();
                itemsBillT27s[i] = bills.get(i).getT27();
                itemsBillT28s[i] = bills.get(i).getT28();
                itemsBillT29s[i] = bills.get(i).getT29();
                itemsBillT30s[i] = bills.get(i).getT30();
                itemsBillT31s[i] = bills.get(i).getT31();
                itemsBillT32s[i] = bills.get(i).getT32();
                itemsBillT33s[i] = bills.get(i).getT33();
                itemsBillT34s[i] = bills.get(i).getT34();
                itemsBillT35s[i] = bills.get(i).getT35();
                itemsBillT36s[i] = bills.get(i).getT36();
                itemsBillT37s[i] = bills.get(i).getT37();
                itemsBillT38s[i] = bills.get(i).getT38();
                itemsBillT39s[i] = bills.get(i).getT39();
                itemsBillT40s[i] = bills.get(i).getT40();
                itemsBillT41s[i] = bills.get(i).getT41();
                itemsBillT42s[i] = bills.get(i).getT42();
                itemsBillT43s[i] = bills.get(i).getT43();
                itemsBillT44s[i] = bills.get(i).getT44();
                itemsBillT45s[i] = bills.get(i).getT45();
                itemsBillT46s[i] = bills.get(i).getT46();
                itemsBillT47s[i] = bills.get(i).getT47();
                itemsBillT48s[i] = bills.get(i).getT48();
                itemsBillT49s[i] = bills.get(i).getT49();
                itemsBillT50s[i] = bills.get(i).getT50();

                itemsBillS1s[i] = bills.get(i).getS1();
                itemsBillS2s[i] = bills.get(i).getS2();
                itemsBillS3s[i] = bills.get(i).getS3();
                itemsBillS4s[i] = bills.get(i).getS4();
                itemsBillS5s[i] = bills.get(i).getS5();
                itemsBillS6s[i] = bills.get(i).getS6();
                itemsBillS7s[i] = bills.get(i).getS7();
                itemsBillS8s[i] = bills.get(i).getS8();
                itemsBillS9s[i] = bills.get(i).getS9();
                itemsBillS10s[i] = bills.get(i).getS10();
                itemsBillS11s[i] = bills.get(i).getS11();
                itemsBillS12s[i] = bills.get(i).getS12();
                itemsBillS13s[i] = bills.get(i).getS13();
                itemsBillS14s[i] = bills.get(i).getS14();
                itemsBillS15s[i] = bills.get(i).getS15();
                itemsBillS16s[i] = bills.get(i).getS16();
                itemsBillS17s[i] = bills.get(i).getS17();
                itemsBillS18s[i] = bills.get(i).getS18();
                itemsBillS19s[i] = bills.get(i).getS19();
                itemsBillS20s[i] = bills.get(i).getS20();
                itemsBillS21s[i] = bills.get(i).getS21();
                itemsBillS22s[i] = bills.get(i).getS22();
                itemsBillS23s[i] = bills.get(i).getS23();
                itemsBillS24s[i] = bills.get(i).getS24();
                itemsBillS25s[i] = bills.get(i).getS25();
                itemsBillS26s[i] = bills.get(i).getS26();
                itemsBillS27s[i] = bills.get(i).getS27();
                itemsBillS28s[i] = bills.get(i).getS28();
                itemsBillS29s[i] = bills.get(i).getS29();
                itemsBillS30s[i] = bills.get(i).getS30();
                itemsBillS31s[i] = bills.get(i).getS31();
                itemsBillS32s[i] = bills.get(i).getS32();
                itemsBillS33s[i] = bills.get(i).getS33();
                itemsBillS34s[i] = bills.get(i).getS34();
                itemsBillS35s[i] = bills.get(i).getS35();
                itemsBillS36s[i] = bills.get(i).getS36();
                itemsBillS37s[i] = bills.get(i).getS37();
                itemsBillS38s[i] = bills.get(i).getS38();
                itemsBillS39s[i] = bills.get(i).getS39();
                itemsBillS40s[i] = bills.get(i).getS40();
                itemsBillS41s[i] = bills.get(i).getS41();
                itemsBillS42s[i] = bills.get(i).getS42();
                itemsBillS43s[i] = bills.get(i).getS43();
                itemsBillS44s[i] = bills.get(i).getS44();
                itemsBillS45s[i] = bills.get(i).getS45();
                itemsBillS46s[i] = bills.get(i).getS46();
                itemsBillS47s[i] = bills.get(i).getS47();
                itemsBillS48s[i] = bills.get(i).getS48();
                itemsBillS49s[i] = bills.get(i).getS49();
                itemsBillS50s[i] = bills.get(i).getS50();

                itemsBillCodes[i] = bills.get(i).getCode();
                itemsBillNames[i] = bills.get(i).getName();
            }
        }

        // Setting up list
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<BillListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<BillListChildItem> pDistricts = pDistricts = new ArrayList<BillListChildItem>();
            for (int i = 0; i < bills.size(); i++) {
                if (bills.get(i).getFrom().matches(folder)) {
                    if (bills.get(i).getType() == 1) {
                        BillListChildItem b = new BillListChildItem(itemsBillsColumns[i], itemsBillCodes[i], itemsBillNames[i], itemsBillsTypes[i], itemsBillsDates[i], itemsBillsTimes[i], itemsBillsFroms[i], itemsBillsTos[i]
                                , itemsBillsCashes[i], itemsBillsCurrencies[i], itemsBillsDiscounts[i], itemsBillsAdditions[i], itemsBillsTotals[i], itemsBillsFinals[i]);
                        b.SETTABLE1(itemsBillP1s[i], itemsBillP2s[i], itemsBillP3s[i], itemsBillP4s[i], itemsBillP5s[i], itemsBillP6s[i], itemsBillP7s[i], itemsBillP8s[i], itemsBillP9s[i], itemsBillP10s[i]
                                , itemsBillP11s[i], itemsBillP12s[i], itemsBillP13s[i], itemsBillP14s[i], itemsBillP15s[i], itemsBillP16s[i], itemsBillP17s[i], itemsBillP18s[i], itemsBillP19s[i], itemsBillP20s[i]
                                , itemsBillP21s[i], itemsBillP22s[i], itemsBillP23s[i], itemsBillP24s[i], itemsBillP25s[i], itemsBillP26s[i], itemsBillP27s[i], itemsBillP28s[i], itemsBillP29s[i], itemsBillP30s[i]
                                , itemsBillP31s[i], itemsBillP32s[i], itemsBillP33s[i], itemsBillP34s[i], itemsBillP35s[i], itemsBillP36s[i], itemsBillP37s[i], itemsBillP38s[i], itemsBillP39s[i], itemsBillP40s[i]
                                , itemsBillP41s[i], itemsBillP32s[i], itemsBillP43s[i], itemsBillP44s[i], itemsBillP45s[i], itemsBillP46s[i], itemsBillP47s[i], itemsBillP48s[i], itemsBillP49s[i], itemsBillP50s[i]
                                , itemsBillA1s[i], itemsBillA2s[i], itemsBillA3s[i], itemsBillA4s[i], itemsBillA5s[i], itemsBillA6s[i], itemsBillA7s[i], itemsBillA8s[i], itemsBillA9s[i], itemsBillA10s[i]
                                , itemsBillA11s[i], itemsBillA12s[i], itemsBillA13s[i], itemsBillA14s[i], itemsBillA15s[i], itemsBillA16s[i], itemsBillA17s[i], itemsBillA18s[i], itemsBillA19s[i], itemsBillA20s[i]
                                , itemsBillA21s[i], itemsBillA22s[i], itemsBillA23s[i], itemsBillA24s[i], itemsBillA25s[i], itemsBillA26s[i], itemsBillA27s[i], itemsBillA28s[i], itemsBillA29s[i], itemsBillA30s[i]
                                , itemsBillA31s[i], itemsBillA32s[i], itemsBillA33s[i], itemsBillA34s[i], itemsBillA35s[i], itemsBillA36s[i], itemsBillA37s[i], itemsBillA38s[i], itemsBillA39s[i], itemsBillA40s[i]
                                , itemsBillA41s[i], itemsBillA32s[i], itemsBillA43s[i], itemsBillA44s[i], itemsBillA45s[i], itemsBillA46s[i], itemsBillA47s[i], itemsBillA48s[i], itemsBillA49s[i], itemsBillA50s[i]
                                , itemsBillO1s[i], itemsBillO2s[i], itemsBillO3s[i], itemsBillO4s[i], itemsBillO5s[i], itemsBillO6s[i], itemsBillO7s[i], itemsBillO8s[i], itemsBillO9s[i], itemsBillO10s[i]
                                , itemsBillO11s[i], itemsBillO12s[i], itemsBillO13s[i], itemsBillO14s[i], itemsBillO15s[i], itemsBillO16s[i], itemsBillO17s[i], itemsBillO18s[i], itemsBillO19s[i], itemsBillO20s[i]
                                , itemsBillO21s[i], itemsBillO22s[i], itemsBillO23s[i], itemsBillO24s[i], itemsBillO25s[i], itemsBillO26s[i], itemsBillO27s[i], itemsBillO28s[i], itemsBillO29s[i], itemsBillO30s[i]
                                , itemsBillO31s[i], itemsBillO32s[i], itemsBillO33s[i], itemsBillO34s[i], itemsBillO35s[i], itemsBillO36s[i], itemsBillO37s[i], itemsBillO38s[i], itemsBillO39s[i], itemsBillO40s[i]
                                , itemsBillO41s[i], itemsBillO32s[i], itemsBillO43s[i], itemsBillO44s[i], itemsBillO45s[i], itemsBillO46s[i], itemsBillO47s[i], itemsBillO48s[i], itemsBillO49s[i], itemsBillO50s[i]);

                        b.SETTABLE2(itemsBillU1s[i], itemsBillU2s[i], itemsBillU3s[i], itemsBillU4s[i], itemsBillU5s[i], itemsBillU6s[i], itemsBillU7s[i], itemsBillU8s[i], itemsBillU9s[i], itemsBillU10s[i]
                                , itemsBillU11s[i], itemsBillU12s[i], itemsBillU13s[i], itemsBillU14s[i], itemsBillU15s[i], itemsBillU16s[i], itemsBillU17s[i], itemsBillU18s[i], itemsBillU19s[i], itemsBillU20s[i]
                                , itemsBillU21s[i], itemsBillU22s[i], itemsBillU23s[i], itemsBillU24s[i], itemsBillU25s[i], itemsBillU26s[i], itemsBillU27s[i], itemsBillU28s[i], itemsBillU29s[i], itemsBillU30s[i]
                                , itemsBillU31s[i], itemsBillU32s[i], itemsBillU33s[i], itemsBillU34s[i], itemsBillU35s[i], itemsBillU36s[i], itemsBillU37s[i], itemsBillU38s[i], itemsBillU39s[i], itemsBillU40s[i]
                                , itemsBillU41s[i], itemsBillU32s[i], itemsBillU43s[i], itemsBillU44s[i], itemsBillU45s[i], itemsBillU46s[i], itemsBillU47s[i], itemsBillU48s[i], itemsBillU49s[i], itemsBillU50s[i]
                                , itemsBillT1s[i], itemsBillT2s[i], itemsBillT3s[i], itemsBillT4s[i], itemsBillT5s[i], itemsBillT6s[i], itemsBillT7s[i], itemsBillT8s[i], itemsBillT9s[i], itemsBillT10s[i]
                                , itemsBillT11s[i], itemsBillT12s[i], itemsBillT13s[i], itemsBillT14s[i], itemsBillT15s[i], itemsBillT16s[i], itemsBillT17s[i], itemsBillT18s[i], itemsBillT19s[i], itemsBillT20s[i]
                                , itemsBillT21s[i], itemsBillT22s[i], itemsBillT23s[i], itemsBillT24s[i], itemsBillT25s[i], itemsBillT26s[i], itemsBillT27s[i], itemsBillT28s[i], itemsBillT29s[i], itemsBillT30s[i]
                                , itemsBillT31s[i], itemsBillT32s[i], itemsBillT33s[i], itemsBillT34s[i], itemsBillT35s[i], itemsBillT36s[i], itemsBillT37s[i], itemsBillT38s[i], itemsBillT39s[i], itemsBillT40s[i]
                                , itemsBillT41s[i], itemsBillT32s[i], itemsBillT43s[i], itemsBillT44s[i], itemsBillT45s[i], itemsBillT46s[i], itemsBillT47s[i], itemsBillT48s[i], itemsBillT49s[i], itemsBillT50s[i]
                                , itemsBillS1s[i], itemsBillS2s[i], itemsBillS3s[i], itemsBillS4s[i], itemsBillS5s[i], itemsBillS6s[i], itemsBillS7s[i], itemsBillS8s[i], itemsBillS9s[i], itemsBillS10s[i]
                                , itemsBillS11s[i], itemsBillS12s[i], itemsBillS13s[i], itemsBillS14s[i], itemsBillS15s[i], itemsBillS16s[i], itemsBillS17s[i], itemsBillS18s[i], itemsBillS19s[i], itemsBillS20s[i]
                                , itemsBillS21s[i], itemsBillS22s[i], itemsBillS23s[i], itemsBillS24s[i], itemsBillS25s[i], itemsBillS26s[i], itemsBillS27s[i], itemsBillS28s[i], itemsBillS29s[i], itemsBillS30s[i]
                                , itemsBillS31s[i], itemsBillS32s[i], itemsBillS33s[i], itemsBillS34s[i], itemsBillS35s[i], itemsBillS36s[i], itemsBillS37s[i], itemsBillS38s[i], itemsBillS39s[i], itemsBillS40s[i]
                                , itemsBillS41s[i], itemsBillS32s[i], itemsBillS43s[i], itemsBillS44s[i], itemsBillS45s[i], itemsBillS46s[i], itemsBillS47s[i], itemsBillS48s[i], itemsBillS49s[i], itemsBillS50s[i]);
                        pDistricts.add(b);
                    }
                }
            }
            listChildData.put(folder, pDistricts);
        }
    }

    private void createList(final List<String> folders,final List<BillListChildItem> bills) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.billsList);
        createListVaribales();
        expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                if(isCheckEnabled){
                    List<BillListChildItem> bills = db.allBills();
                    List<String> folders = db.allSupplierFolders();
                    boolean x[] = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        x[i] = expListView.isGroupExpanded(i);
                    }
                    CheckBox check = (CheckBox) view.findViewById(R.id.billCheckP);
                    ArrayList<Boolean> a = childCheckStates.get(groupPosition);
                    a.set(childPosition, !check.isChecked());
                    childCheckStates.put(groupPosition, a);
                    int index = expListView.getFirstVisiblePosition();
                    View v = expListView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
                    int numberChecked = 0;
                    for(int i = 0;i < groupCheckStates.size();i++) {
                        for (int j = 0; j < childCheckStates.get(i).size(); j++) {
                                if(childCheckStates.get(i).get(j))
                                    numberChecked++;
                        }
                    }
                    if(numberChecked > 1)
                        menuInfoItem.setVisible(false);
                    else if(numberChecked == 1)
                        menuInfoItem.setVisible(true);
                    for (int i = 0; i < folders.size(); i++) {
                        boolean isAll = true;
                        for (int j = 0; j < listChildData.get(folders.get(i)).size(); j++) {
                            if (!childCheckStates.get(i).get(j)) {
                                isAll = false;
                                break;
                            }
                        }
                        if (isAll)
                            groupCheckStates.set(i, true);
                        else
                            groupCheckStates.set(i, false);
                    }
                    createListVaribales();
                    expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1,childCheckStates,groupCheckStates);
                    // Setting list adapter
                    expListView.setAdapter(expandableListAdapter);
                    for (int i = 0; i < folders.size(); i++) {
                        if (x[i])
                            expListView.expandGroup(i);
                    }
                    expListView.setSelectionFromTop(index, top);
                } else {
                    Intent i = new Intent(context, BillActivity.class);
                    i.putExtra("sub", 1);
                    i.putExtra("isView", true);
                    i.putExtra("column", listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCOLUMN());
                    startActivityForResult(i, REQUEST_FORM);
                }
                return true;
            }
        });
        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ExpandableListView listView = (ExpandableListView) parent;
                long pos = listView.getExpandableListPosition(position);

                // get type and correct positions
                int itemType = ExpandableListView.getPackedPositionType(pos);
                int groupPos = ExpandableListView.getPackedPositionGroup(pos);
                int childPos = ExpandableListView.getPackedPositionChild(pos);

                // if child is long-clicked
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    if (!isCheckEnabled) {
                        isCheckEnabled = true;
                        menuRecordItem.setVisible(false);
                        menuInfoItem.setVisible(true);
                        menuDeleteItem.setVisible(true);
                        menuSelectaItem.setVisible(true);
                        menuDeselectaItem.setVisible(true);
                        menuDeleteaItem.setVisible(false);
                        menuSearchItem.setVisible(false);
                        menuNewItem.setVisible(false);
                        List<BillListChildItem> bills = db.allBills();
                        List<String> folders = db.allSupplierFolders();
                        int index = expListView.getFirstVisiblePosition();
                        View v = expListView.getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
                        int max = 0;
                        for (int i = 0; i < folders.size(); i++) {
                            int a = 0;
                            for (int b = 0; b < bills.size(); b++) {
                                if (bills.get(b).getType() == 1 && bills.get(b).getFrom().matches(folders.get(i)))
                                    a++;
                            }
                            if (a > max)
                                max = a;
                        }
                        childCheckStates = new HashMap<>();
                        groupCheckStates = new ArrayList<Boolean>();
                        boolean x[] = new boolean[folders.size()];
                        for (int i = 0; i < folders.size(); i++) {
                            x[i] = expListView.isGroupExpanded(i);
                        }
                        for (int i = 0; i < folders.size(); i++) {
                            ArrayList<Boolean> a = new ArrayList<>();
                            for (int b = 0; b < max; b++) {
                                if (b == childPos && i == groupPos)
                                    a.add(true);
                                else
                                    a.add(false);
                            }
                            childCheckStates.put(i, a);
                        }
                        for (int k = 0; k < folders.size(); k++) {
                            groupCheckStates.add(false);
                        }
                        for (int i = 0; i < folders.size(); i++) {
                            boolean isAll = true;
                            for (int j = 0; j < listChildData.get(folders.get(i)).size(); j++) {
                                if (!childCheckStates.get(i).get(j)) {
                                    isAll = false;
                                    break;
                                }
                            }
                            if (isAll)
                                groupCheckStates.set(i, true);
                            else
                                groupCheckStates.set(i, false);
                        }
                        createListVaribales();
                        expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1, childCheckStates, groupCheckStates);
                        // Setting list adapter
                        expListView.setAdapter(expandableListAdapter);
                        for (int i = 0; i < folders.size(); i++) {
                            if (x[i])
                                expListView.expandGroup(i);
                        }
                        expListView.setSelectionFromTop(index, top);
                    }
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    if (isCheckEnabled) {
                        int index = expListView.getFirstVisiblePosition();
                        View v = expListView.getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
                        boolean x[] = new boolean[folders.size()];
                        for (int i = 0; i < folders.size(); i++) {
                            x[i] = expListView.isGroupExpanded(i);
                        }
                        CheckBox check = (CheckBox) view.findViewById(R.id.groupPurCheck);
                        for (int i = 0; i < childCheckStates.get(groupPos).size(); i++) {
                            ArrayList<Boolean> a = childCheckStates.get(groupPos);
                            a.set(i, !check.isChecked());
                            childCheckStates.put(groupPos, a);
                        }
                        groupCheckStates.set(groupPos, !check.isChecked());
                        createListVaribales();
                        expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1, childCheckStates, groupCheckStates);
                        // Setting list adapter
                        expListView.setAdapter(expandableListAdapter);
                        for (int i = 0; i < folders.size(); i++) {
                            if (x[i])
                                expListView.expandGroup(i);
                        }
                        expListView.setSelectionFromTop(index, top);
                    }else{
                        createPopupGroupItemMenu(view, groupPos);
                    }
                }
                return true;
            }
        });

    }

    private void createPopupGroupItemMenu(View view, final int groupPos) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_sub_edit_item:
                        createFolderDialog(groupPos);
                        return true;
                    case R.id.pop_sub_delete_item:
                        createAlertDeleteFolderDialog(groupPos);

                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_subject);
        popup.show();
    }

    private void createAlertDeleteFolderDialog(final int groupPos) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(res.getString(R.string.alertTitle));
        alertDialog.setMessage(res.getString(R.string.alertDeleteMessage));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        List<BillListChildItem> bills = db.allBills();
                        for (int i = 0; i < db.allBills().size(); i++) {
                            BillListChildItem bill = bills.get(i);
                            if (bill.getType() == 1 && bill.getFrom().matches(listGroupTitles.get(groupPos))) {
                                deleteFromSQL(bill);
                                Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
                            }
                        }
                        createListFromSQL();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void createFolderDialog(final int groupPos) {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_edit_folder);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final EditText editFolder = (EditText) d.findViewById(R.id.edit_folder);
        Button save_new_edit = (Button) d.findViewById(R.id.save_edit_folder);
        Button cancel_new_edit = (Button) d.findViewById(R.id.cancel_edit_folder);
        editFolder.setText(listGroupTitles.get(groupPos));

        save_new_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editFolder.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    List<BillListChildItem> bills = db.allBills();
                    for (int i = 0; i < db.allBills().size(); i++) {
                        BillListChildItem bill = bills.get(i);
                        if (bill.getType() == 1 && bill.getFrom().matches(listGroupTitles.get(groupPos))) {
                            editFromSQL(bill, editFolder.getText().toString(), bill.getTo());
                        }
                    }
                    createListFromSQL();
                    d.cancel();
                } else {
                    if (context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic"))
                        editFolder.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                    else
                        editFolder.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                }
            }
        });
        cancel_new_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.show();
    }

    private void createPopupChildItemMenu(View view, final BillListChildItem blci) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_bill_edit_item:
                        Intent i = new Intent(context, BillActivity.class);
                        i.putExtra("sub", 1);
                        i.putExtra("isView", true);
                        i.putExtra("column", blci.getCOLUMN());
                        startActivityForResult(i, REQUEST_FORM);
                        return true;
                    case R.id.pop_bill_info_item:
                        createInfoDialog(blci);
                        return true;
                    case R.id.pop_bill_delete_item:
                        deleteFromSQL(blci);
                        createListFromSQL();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_bill);
        popup.show();
    }

    private void createInfoDialog(BillListChildItem blci) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_bill_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        date = (TextView) dialog.findViewById(R.id.info_date);
        type = (TextView) dialog.findViewById(R.id.info_type);
        code = (TextView) dialog.findViewById(R.id.info_code);
        name = (TextView) dialog.findViewById(R.id.info_name);
        from = (TextView) dialog.findViewById(R.id.info_from);
        to = (TextView) dialog.findViewById(R.id.info_to);
        cash = (TextView) dialog.findViewById(R.id.info_cash);
        currency = (TextView) dialog.findViewById(R.id.info_currency);
        total = (TextView) dialog.findViewById(R.id.info_total);
        final_ = (TextView) dialog.findViewById(R.id.info_final);
        txt_from = (TextView) dialog.findViewById(R.id.info_from_txt);
        txt_to = (TextView) dialog.findViewById(R.id.info_to_txt);
        linear_info = (LinearLayout) dialog.findViewById(R.id.linear_info);
        linear_info.setBackground(res.getDrawable(R.drawable.dialog_info_pur_background));
        date.setText(blci.getDate() + "  " + blci.getTime());
        type.setText(context.getResources().getString(R.string.drawer_purchases));
        type.setTextColor(context.getResources().getColor(R.color.blue));
        txt_from.setText(context.getResources().getString(R.string.txt_from));
        txt_to.setText(context.getResources().getString(R.string.txt_to_));
        from.setText(blci.getFrom());
        to.setText(blci.getTo());
        code.setText(String.valueOf(blci.getCode()));
        name.setText(blci.getName());
        cash.setText(blci.getCash());
        currency.setText(blci.getCurrency());
        total.setText(String.valueOf(blci.getTotal()));
        final_.setText(String.valueOf(blci.getFinal()));
        dialog.show();
    }

    private void deleteFromSQL(BillListChildItem bill) {
        db.deleteBill(bill);
    }

    private void editFromSQL(BillListChildItem bill, String from, String to) {
        db.updateBill(bill, from, to);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        createListFromSQL();
    }

    MenuItem menuRecordItem, menuInfoItem, menuDeleteItem, menuSelectaItem, menuDeselectaItem, menuSearchItem, menuNewItem, menuDeleteaItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bills, menu);
        menu.findItem(R.id.menu_new).setTitle(R.string.menu_bill_add);
        menuRecordItem = menu.getItem(0);
        menuInfoItem = menu.getItem(1);
        menuDeleteItem = menu.getItem(2);
        menuSearchItem = menu.getItem(3);
        menuSelectaItem = menu.getItem(4).getSubMenu().getItem(0);
        menuDeselectaItem = menu.getItem(4).getSubMenu().getItem(1);
        menuNewItem = menu.getItem(4).getSubMenu().getItem(4);
        menuDeleteaItem = menu.getItem(4).getSubMenu().getItem(5);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_record:

                return true;
            case R.id.menu_search:
                createSearchDialog();
                return true;
            case R.id.menu_info:
                createListVaribales();
                List<String> folders5 = db.allSupplierFolders();
                for(int i = 0;i <groupCheckStates.size();i++){
                    for(int j = 0;j < childCheckStates.get(i).size(); j++){
                        if(childCheckStates.get(i).get(j)){
                            createInfoDialog(listChildData.get(folders5.get(i)).get(j));
                            break;
                        }
                    }
                }
                return true;
            case R.id.menu_delete:
                List<BillListChildItem> bills = db.allBills();
                List<String> folders = db.allSupplierFolders();
                listChildData = new HashMap<String, List<BillListChildItem>>();
                createListFromSQL();
                boolean x[] = new boolean[folders.size()];
                for (int i = 0; i < folders.size(); i++) {
                    x[i] = expListView.isGroupExpanded(i);
                }
                for (int i = 0; i < groupCheckStates.size(); i++) {
                    for (int j = 0; j < childCheckStates.get(i).size(); j++) {
                        if (childCheckStates.get(i).get(j)) {
                            db.deleteBill(listChildData.get(folders.get(i)).get(j));
                        }
                    }
                }
                isCheckEnabled = false;
                menuInfoItem.setVisible(false);
                menuDeleteItem.setVisible(false);
                menuSelectaItem.setVisible(false);
                menuDeselectaItem.setVisible(false);
                menuDeleteaItem.setVisible(true);
                menuSearchItem.setVisible(true);
                menuNewItem.setVisible(true);
                for (int i = 0; i < folders.size(); i++) {
                    if (x[i])
                        expListView.expandGroup(i);
                }
                return true;
            case R.id.menu_new:
                Intent in = new Intent(context, BillActivity.class);
                in.putExtra("sub", 1);
                startActivityForResult(in, REQUEST_FORM);
                return true;
            case R.id.menu_selectAll:
                List<BillListChildItem> bills1 = db.allBills();
                List<String> folders1 = db.allSupplierFolders();
                boolean x1[] = new boolean[folders1.size()];
                for (int i = 0; i < folders1.size(); i++) {
                    x1[i] = expListView.isGroupExpanded(i);
                }
                int index = expListView.getFirstVisiblePosition();
                View v = expListView.getChildAt(0);
                int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
                createListVaribales();
                groupCheckStates = new ArrayList<>();
                childCheckStates = new HashMap<>();
                for (int i = 0; i < listGroupTitles.size(); i++) {
                    groupCheckStates.add(true);
                    ArrayList<Boolean> a = new ArrayList<>();
                    for (int j = 0; j < listChildData.get(listGroupTitles.get(i)).size(); j++) {
                        a.add(true);
                    }
                    childCheckStates.put(i, a);
                }
                int numberChecked = 0;
                for(int i = 0;i < groupCheckStates.size();i++) {
                    for (int j = 0; j < childCheckStates.get(i).size(); j++) {
                        if(childCheckStates.get(i).get(j))
                            numberChecked++;
                    }
                }
                if(numberChecked > 1)
                    menuInfoItem.setVisible(false);
                else if(numberChecked == 1)
                    menuInfoItem.setVisible(true);
                expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1, childCheckStates, groupCheckStates);
                // Setting list adapter
                expListView.setAdapter(expandableListAdapter);
                //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                for (int i = 0; i < folders1.size(); i++) {
                    if (x1[i])
                        expListView.expandGroup(i);
                }
                expListView.setSelectionFromTop(index, top);
                return true;
            case R.id.menu_deselectAll:
                List<BillListChildItem> bills2 = db.allBills();
                List<String> folders2 = db.allSupplierFolders();
                boolean x2[] = new boolean[folders2.size()];
                for (int i = 0; i < folders2.size(); i++) {
                    x2[i] = expListView.isGroupExpanded(i);
                }
                int index1 = expListView.getFirstVisiblePosition();
                View v1 = expListView.getChildAt(0);
                int top1 = (v1 == null) ? 0 : (v1.getTop() - expListView.getPaddingTop());
                for (int i = 0; i < groupCheckStates.size(); i++) {
                    groupCheckStates.set(i, false);
                    ArrayList<Boolean> a = childCheckStates.get(i);
                    for (int j = 0; j < childCheckStates.get(i).size(); j++) {
                        a.set(j, false);
                    }
                    childCheckStates.put(i, a);
                }
                createListVaribales();
                menuInfoItem.setVisible(false);
                expandableListAdapter = new CustomBillExpandableListAdapter(context, listGroupTitles, listChildData, 1, childCheckStates, groupCheckStates);
                // Setting list adapter
                expListView.setAdapter(expandableListAdapter);
                //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                for (int i = 0; i < folders2.size(); i++) {
                    if (x2[i])
                        expListView.expandGroup(i);
                }
                expListView.setSelectionFromTop(index1, top1);
                return true;
            case R.id.menu_expAll:
                for (int i = 0; i < db.allSupplierFolders().size(); i++) {
                    if (!expListView.isGroupExpanded(i))
                        expListView.expandGroup(i, true);
                }
                return true;
            case R.id.menu_colAll:
                for (int i = 0; i < db.allSupplierFolders().size(); i++) {
                    if (expListView.isGroupExpanded(i))
                        expListView.collapseGroup(i);
                }
                return true;
            case R.id.menu_deleteAll:
                createAlertDeleteAllDialog();
                return true;
            case R.id.menu_pref:

                return true;
            case R.id.menu_exit:
                getActivity().finish();
                getActivity().finishAffinity();
                getActivity().moveTaskToBack(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createAlertDeleteAllDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(res.getString(R.string.alertTitle));
        alertDialog.setMessage(res.getString(R.string.alertDeleteAllMessage));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        List<BillListChildItem> bills = db.allBills();
                        for (int i = 0; i < bills.size(); i++) {
                            BillListChildItem BLCI = bills.get(i);
                            if (bills.get(i).getType() == 1)
                                deleteFromSQL(BLCI);
                        }
                        createListFromSQL();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void createSearchDialog() {
        final Dialog d1 = new Dialog(context);
        d1.setContentView(R.layout.dialog_search_bill);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_bills_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_bills);
        listAllBills = new ArrayList<BillListChildItem>();
        listAllBills.addAll(db.allBills());
        customSearchBillsAdapter = new CustomSearchBillsAdapter(context, R.layout.list_bills_item, listAllBills);
        searchList.setAdapter(customSearchBillsAdapter);
        searchView.setOnQueryTextListener(this);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_bills_item, null);
                }
                TextView billName = (TextView) convertView.findViewById(R.id.billName);
                TextView billDate = (TextView) convertView.findViewById(R.id.billDate);
                TextView billFinal = (TextView) convertView.findViewById(R.id.billFinal);
                TextView billSupCus = (TextView) convertView.findViewById(R.id.billSupCus);
                d1.cancel();
                BillListChildItem BLCI = db.getBillByName(billName.getText().toString());
                Intent j = new Intent(context, BillActivity.class);
                j.putExtra("sub", BLCI.getType());
                j.putExtra("isView", true);
                j.putExtra("column", BLCI.getCOLUMN());
                startActivityForResult(j, REQUEST_FORM);
            }
        });
        searchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_bills_item, null);
                }
                TextView billName = (TextView) convertView.findViewById(R.id.billName);
                TextView billDate = (TextView) convertView.findViewById(R.id.billDate);
                TextView billFinal = (TextView) convertView.findViewById(R.id.billFinal);
                TextView billSupCus = (TextView) convertView.findViewById(R.id.billSupCus);
                BillListChildItem BLCI = db.getBillByName(billName.getText().toString());
                createPopupChildItemMenu(convertView, BLCI);
                return true;
            }
        });
        cancel_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1.cancel();
            }
        });
        d1.setCancelable(false);
        d1.setCanceledOnTouchOutside(false);
        d1.show();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        customSearchBillsAdapter.filter(text);
        return false;
    }
}

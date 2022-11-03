package com.zezoo.accountant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;

public class ListSuppliersActivity extends Fragment implements SearchView.OnQueryTextListener {

    EditText edit_new_company, edit_new_supplier, edit_new_num1, edit_new_num2, edit_new_email, edit_new_balance, edit_new_spec1;
    Spinner spin_new_currency, spin_new_type;
    Button btn_save_supplier, btn_cancel_supplier;
    TextInputLayout til_supplier, til_num;
    TextView txt_date, txt_time;
    ExpandableListView expListView;
    CustomSupplierExpandableListAdapter expandableListAdapter;
    CustomSearchSuppliersAdapter customSearchSuppliersAdapter;
    ArrayList<String> listGroupTitles;
    HashMap<String, List<SupplierListChildItem>> listChildData;
    ListView searchList;
    SearchView searchView;
    ArrayList<SupplierListChildItem> listAllSuppliers;
    Resources res;
    FloatingActionButton fab;
    String code = "SYP";
    int cash = 0, debt = 0;
    Context context;
    View view;
    private SupplierSQLDatabaseHandler db;
    int currentColumn;
    int currentAccount;
    String currentCompany;
    String currentSupplier;
    String currentCompanyPh;
    String currentSupplierPh;
    String currentEmail;
    String currentSpec;
    String currentCash;
    String currentCurrency;
    String currentTime;
    String currentDate;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_suppliers, container, false);
        context = view.getContext();
        setHasOptionsMenu(true);
        res = getResources();
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionSupplier);
        expListView = (ExpandableListView) view.findViewById(R.id.suppliersList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSupplierDialog(false);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        return view;
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new SupplierSQLDatabaseHandler(context);
    }

    private void addToSQL(int Column, int Account, String Company, String Supplier, String CompanyPh, String SupplierPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        SupplierListChildItem supplier = new SupplierListChildItem(Column, Account, Company, Supplier, CompanyPh, SupplierPh, Email, Spec, Cash, Currency, Time, Date);
        db.addSupplier(supplier);
    }

    private void deleteFromSQL(int Column, int Account, String Company, String Supplier, String CompanyPh, String SupplierPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        SupplierListChildItem supplier = new SupplierListChildItem(Column, Account, Company, Supplier, CompanyPh, SupplierPh, Email, Spec, Cash, Currency, Time, Date);
        db.deleteSupplier(supplier);
    }

    private void editFromSQL(int Column, int Account, String Company, String Supplier, String CompanyPh, String SupplierPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        SupplierListChildItem supplier = new SupplierListChildItem(Column, Account, Company, Supplier, CompanyPh, SupplierPh, Email, Spec, Cash, Currency, Time, Date);
        db.updateSupplier(supplier);
    }

    private void createListFromSQL() {
        // list all suppliers
        List<SupplierListChildItem> suppliers = db.allSuppliers();
        List<String> folders = db.allCompanies();
        if (suppliers != null) {
            createList(folders, suppliers);
        }
    }

    private void createList(List<String> folders, List<SupplierListChildItem> suppliers) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.suppliersList);
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
        }
        int[] itemsSuppliersColumns = new int[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersColumns[i] = suppliers.get(i).getColumn();
        }
        int[] itemsSuppliersAccounts = new int[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersAccounts[i] = suppliers.get(i).getAccount();
        }
        String[] itemsSuppliersCompanies = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersCompanies[i] = suppliers.get(i).getCompany();
        }
        String[] itemsSuppliersSuppliers = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersSuppliers[i] = suppliers.get(i).getSupplier();
        }
        String[] itemsSuppliersCompaniesPhs = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersCompaniesPhs[i] = suppliers.get(i).getCompanyPho();
        }
        String[] itemsSuppliersSuppliersPhs = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersSuppliersPhs[i] = suppliers.get(i).getSupplierPho();
        }
        String[] itemsSuppliersEmails = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersEmails[i] = suppliers.get(i).getEmail();
        }
        String[] itemsSuppliersSpecs = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersSpecs[i] = suppliers.get(i).getSpec();
        }
        String[] itemsSuppliersCashs = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersCashs[i] = suppliers.get(i).getCash();
        }
        String[] itemsSuppliersCurrencies = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersCurrencies[i] = suppliers.get(i).getCurrency();
        }
        String[] itemsSuppliersTimes = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersTimes[i] = suppliers.get(i).getTime();
        }
        String[] itemsSuppliersDates = new String[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            itemsSuppliersDates[i] = suppliers.get(i).getDate();
        }

        // Setting up list
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<SupplierListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<SupplierListChildItem> pDistricts = pDistricts = new ArrayList<SupplierListChildItem>();
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i).getCompany().matches(folder)) {
                    pDistricts.add(new SupplierListChildItem(itemsSuppliersColumns[i], itemsSuppliersAccounts[i], folder, itemsSuppliersSuppliers[i], itemsSuppliersCompaniesPhs[i], itemsSuppliersSuppliersPhs[i], itemsSuppliersEmails[i], itemsSuppliersSpecs[i], itemsSuppliersCashs[i], itemsSuppliersCurrencies[i], itemsSuppliersTimes[i], itemsSuppliersDates[i]));
                }
            }
            listChildData.put(folder, pDistricts);
        }
        expandableListAdapter = new CustomSupplierExpandableListAdapter(context, listGroupTitles, listChildData);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                currentColumn = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getColumn();
                currentAccount = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAccount();
                currentCompany = listGroupTitles.get(groupPosition);
                currentSupplier = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSupplier();
                currentCompanyPh = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCompanyPho();
                currentSupplierPh = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSupplierPho();
                currentEmail = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getEmail();
                currentSpec = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSpec();
                currentCash = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCash();
                currentCurrency = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCurrency();
                currentTime = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getTime();
                currentDate = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getDate();
                createSupplierDialog(true);
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
                    SupplierListChildItem SLCI = (SupplierListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                    createPopupChildItemMenu(view, SLCI);
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    createPopupGroupItemMenu(view, groupPos);
                }
                return true;
            }
        });

    }

    private void createSupplierDialog(final boolean isEdit) {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_new_supplier);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_new_company = (EditText) d.findViewById(R.id.edit_new_company);
        edit_new_supplier = (EditText) d.findViewById(R.id.edit_new_supplier);
        edit_new_num1 = (EditText) d.findViewById(R.id.edit_new_num1);
        edit_new_num2 = (EditText) d.findViewById(R.id.edit_new_num2);
        edit_new_email = (EditText) d.findViewById(R.id.edit_new_email);
        edit_new_spec1 = (EditText) d.findViewById(R.id.edit_new_spec1);
        edit_new_balance = (EditText) d.findViewById(R.id.edit_new_balance);
        spin_new_currency = (Spinner) d.findViewById(R.id.spin_new_currency_c);
        spin_new_type = (Spinner) d.findViewById(R.id.spin_new_type);
        btn_save_supplier = (Button) d.findViewById(R.id.save_new_supplier);
        btn_cancel_supplier = (Button) d.findViewById(R.id.cancel_new_supplier);
        til_supplier = (TextInputLayout) d.findViewById(R.id.til_supplier);
        til_num = (TextInputLayout) d.findViewById(R.id.til_num);
        txt_time = (TextView) d.findViewById(R.id.txt_time);
        txt_date = (TextView) d.findViewById(R.id.txt_date);

        String[] currency = res.getStringArray(R.array.dola_sy);
        String[] type = res.getStringArray(R.array.debt_cash);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        ArrayAdapter<CharSequence> spinnerTypeArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, type);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerTypeArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_new_currency.setAdapter(spinnerCurrencyArrayAdapter);
        spin_new_type.setAdapter(spinnerTypeArrayAdapter);

        spin_new_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index == 0)
                    code = "SYP";
                else
                    code = "$";
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btn_save_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_new_supplier.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_num2.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    if(!edit_new_balance.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_new_balance.setText("0");
                    if (!edit_new_company.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_new_company.setText(res.getString(R.string.without_folder));
                    if (isEdit) {
                        editFromSQL(currentColumn
                                , Integer.valueOf(edit_new_balance.getText().toString())
                                , edit_new_company.getText().toString()
                                , edit_new_supplier.getText().toString()
                                , edit_new_num1.getText().toString()
                                , edit_new_num2.getText().toString()
                                , edit_new_email.getText().toString()
                                , edit_new_spec1.getText().toString()
                                , spin_new_type.getSelectedItem().toString()
                                , spin_new_currency.getSelectedItem().toString()
                                , getTime()
                                , getDate());
                        createListFromSQL();
                        d.cancel();
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSupplierColumn", 0);
                        addToSQL(column + 1
                                , Integer.valueOf(edit_new_balance.getText().toString())
                                , edit_new_company.getText().toString()
                                , edit_new_supplier.getText().toString()
                                , edit_new_num1.getText().toString()
                                , edit_new_num2.getText().toString()
                                , edit_new_email.getText().toString()
                                , edit_new_spec1.getText().toString()
                                , spin_new_type.getSelectedItem().toString()
                                , spin_new_currency.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSupplierColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    d.cancel();
                } else {
                    if (!edit_new_supplier.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_supplier.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_supplier.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_num2.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_num2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_num2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                }
            }
        });
        btn_cancel_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
        if (isEdit) {
            edit_new_company.setText(currentCompany);
            edit_new_supplier.setText(String.valueOf(currentSupplier));
            edit_new_num1.setText(currentCompanyPh);
            edit_new_num2.setText(currentSupplierPh);
            edit_new_email.setText(currentEmail);
            edit_new_spec1.setText(currentSpec);
            edit_new_balance.setText(String.valueOf(currentAccount));
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCurrency.matches("SYP"))
                spin_new_currency.setSelection(0);
            else
                spin_new_currency.setSelection(1);
        } else {
            spin_new_currency.setSelection(0);
            spin_new_type.setSelection(0);
            txt_date.setText(getDate());
            txt_time.setText(getTime());
        }
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.show();
        edit_new_supplier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_supplier.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_new_num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_num2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void createPopupChildItemMenu(View v, final SupplierListChildItem SLCI) {
        PopupMenu popup = new PopupMenu(context, v);
        currentColumn = SLCI.getColumn();
        currentAccount = SLCI.getAccount();
        currentCompany = SLCI.getCompany();
        currentSupplier = SLCI.getSupplier();
        currentCompanyPh = SLCI.getCompanyPho();
        currentSupplierPh = SLCI.getSupplierPho();
        currentEmail = SLCI.getEmail();
        currentCash = SLCI.getCash();
        currentSpec = SLCI.getSpec();
        currentCurrency = SLCI.getCurrency();
        currentTime = SLCI.getTime();
        currentDate = SLCI.getTime();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_sup_add_item:
                        /////////////////////////////
                        return true;
                    case R.id.pop_sup_edit_item:
                        createSupplierDialog(true);
                        return true;
                    case R.id.pop_sup_delete_item:
                        deleteFromSQL(SLCI.getColumn(), SLCI.getAccount(), SLCI.getCompany(), SLCI.getSupplier(), SLCI.getCompanyPho(), SLCI.getSupplierPho(), SLCI.getEmail(), SLCI.getSpec(), SLCI.getCash(), SLCI.getCurrency(), SLCI.getTime(), SLCI.getDate());
                        createListFromSQL();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_supplier);
        popup.show();
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
                        List<SupplierListChildItem> suppliers = db.allSuppliers();
                        for (int i = 0; i < listChildData.size(); i++) {
                            SupplierListChildItem SLCI = suppliers.get(i);
                            if (SLCI.getCompany().matches(listGroupTitles.get(groupPos))) {
                                deleteFromSQL(SLCI.getColumn(), SLCI.getAccount(), SLCI.getCompany(), SLCI.getSupplier(), SLCI.getCompanyPho(), SLCI.getSupplierPho(), SLCI.getEmail(), SLCI.getSpec(), SLCI.getCash(), SLCI.getCurrency(), SLCI.getTime(), SLCI.getDate());
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
                List<SupplierListChildItem> suppliers = db.allSuppliers();
                for (int i = 0; i < listChildData.size(); i++) {
                    SupplierListChildItem SLCI = suppliers.get(i);
                    if (SLCI.getCompany().matches(listGroupTitles.get(groupPos))) {
                        editFromSQL(SLCI.getColumn(), SLCI.getAccount(), SLCI.getCompany(), SLCI.getSupplier(), SLCI.getCompanyPho(), SLCI.getSupplierPho(), SLCI.getEmail(), SLCI.getSpec(), SLCI.getCash(), SLCI.getCurrency(), SLCI.getTime(), SLCI.getDate());
                    }
                }
                createListFromSQL();
                d.cancel();
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

    Menu men;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subjects, menu);
        men = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                createSearchDialog();
                return true;
            case R.id.menu_new:
                createSupplierDialog(false);
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
            case android.R.id.home:
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
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
                        List<SupplierListChildItem> suppliers = db.allSuppliers();
                        for (int i = 0; i < suppliers.size(); i++) {
                            SupplierListChildItem SLCI = suppliers.get(i);
                            deleteFromSQL(SLCI.getColumn(), SLCI.getAccount(), SLCI.getCompany(), SLCI.getSupplier(), SLCI.getCompanyPho(), SLCI.getSupplierPho(), SLCI.getEmail(), SLCI.getSpec(), SLCI.getCash(), SLCI.getCurrency(), SLCI.getTime(), SLCI.getDate());
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
        d1.setContentView(R.layout.dialog_search_supplier);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_suppliers_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_suppliers);
        createSearchList();
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

    private void createSearchList() {
        listAllSuppliers = new ArrayList<SupplierListChildItem>();
        listAllSuppliers.addAll(db.allSuppliers());
        customSearchSuppliersAdapter = new CustomSearchSuppliersAdapter(context, R.layout.list_suppliers_item, listAllSuppliers);
        searchList.setAdapter(customSearchSuppliersAdapter);
        searchView.setOnQueryTextListener(this);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private String getDate() {
        String date = "";
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        date = new SimpleDateFormat("yyyy/MM/dd").format(now);
        return date;
    }

    private String getTime() {
        String time = "";
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm");
        time = simpledateformat.format(calander.getTime());
        return time;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        customSearchSuppliersAdapter.filter(text);
        return false;
    }
}

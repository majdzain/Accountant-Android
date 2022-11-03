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

public class ListCustomersActivity extends Fragment implements SearchView.OnQueryTextListener {

    EditText edit_new_company, edit_new_customer, edit_new_num1, edit_new_num2, edit_new_email, edit_new_balance, edit_new_spec1;
    Spinner spin_new_currency, spin_new_type;
    Button btn_save_customer, btn_cancel_customer;
    TextInputLayout til_customer, til_num;
    TextView txt_date, txt_time;
    ExpandableListView expListView;
    CustomCustomerExpandableListAdapter expandableListAdapter;
    CustomSearchCustomersAdapter customSearchCustomersAdapter;
    ArrayList<String> listGroupTitles;
    HashMap<String, List<CustomerListChildItem>> listChildData;
    ListView searchList;
    SearchView searchView;
    ArrayList<CustomerListChildItem> listAllCustomers;
    Resources res;
    FloatingActionButton fab;
    String code = "SYP";
    int cash = 0, debt = 0;
    Context context;
    View view;
    private CustomerSQLDatabaseHandler db;
    int currentColumn;
    int currentAccount;
    String currentCompany;
    String currentCustomer;
    String currentCompanyPh;
    String currentCustomerPh;
    String currentEmail;
    String currentSpec;
    String currentCash;
    String currentCurrency;
    String currentTime;
    String currentDate;
    SharedPreferences pref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_customers, container, false);
        context = view.getContext();
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        setHasOptionsMenu(true);
        res = getResources();
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonC);
        expListView = (ExpandableListView) view.findViewById(R.id.customersList); 
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomerDialog(false);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        return view;
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new CustomerSQLDatabaseHandler(context);
    }

    private void addToSQL(int Column, int Account, String Company, String Customer, String CompanyPh, String CustomerPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        CustomerListChildItem customer = new CustomerListChildItem(Column, Account, Company, Customer, CompanyPh, CustomerPh, Email, Spec, Cash, Currency, Time, Date);
        db.addCustomer(customer);
    }

    private void deleteFromSQL(int Column, int Account, String Company, String Customer, String CompanyPh, String CustomerPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        CustomerListChildItem customer = new CustomerListChildItem(Column, Account, Company, Customer, CompanyPh, CustomerPh, Email, Spec, Cash, Currency, Time, Date);
        db.deleteCustomer(customer);
    }

    private void editFromSQL(int Column, int Account, String Company, String Customer, String CompanyPh, String CustomerPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        CustomerListChildItem customer = new CustomerListChildItem(Column, Account, Company, Customer, CompanyPh, CustomerPh, Email, Spec, Cash, Currency, Time, Date);
        db.updateCustomer(customer);
    }

    private void createListFromSQL() {
        // list all customers
        List<CustomerListChildItem> customers = db.allCustomers();
        List<String> folders = db.allCompanies();
        if (customers != null) {
            createList(folders, customers);
        }
    }

    private void createList(List<String> folders, List<CustomerListChildItem> customers) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.customersList);
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
        }
        int[] itemsCustomersColumns = new int[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersColumns[i] = customers.get(i).getColumn();
        }
        int[] itemsCustomersAccounts = new int[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersAccounts[i] = customers.get(i).getAccount();
        }
        String[] itemsCustomersCompanies = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCompanies[i] = customers.get(i).getCompany();
        }
        String[] itemsCustomersCustomers = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCustomers[i] = customers.get(i).getCustomer();
        }
        String[] itemsCustomersCompaniesPhs = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCompaniesPhs[i] = customers.get(i).getCompanyPho();
        }
        String[] itemsCustomersCustomersPhs = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCustomersPhs[i] = customers.get(i).getCustomerPho();
        }
        String[] itemsCustomersEmails = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersEmails[i] = customers.get(i).getEmail();
        }
        String[] itemsCustomersSpecs = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersSpecs[i] = customers.get(i).getSpec();
        }
        String[] itemsCustomersCashs = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCashs[i] = customers.get(i).getCash();
        }
        String[] itemsCustomersCurrencies = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersCurrencies[i] = customers.get(i).getCurrency();
        }
        String[] itemsCustomersTimes = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersTimes[i] = customers.get(i).getTime();
        }
        String[] itemsCustomersDates = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            itemsCustomersDates[i] = customers.get(i).getDate();
        }

        // Setting up list
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<CustomerListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<CustomerListChildItem> pDistricts = pDistricts = new ArrayList<CustomerListChildItem>();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCompany().matches(folder)) {
                    pDistricts.add(new CustomerListChildItem(itemsCustomersColumns[i], itemsCustomersAccounts[i], folder, itemsCustomersCustomers[i], itemsCustomersCompaniesPhs[i], itemsCustomersCustomersPhs[i], itemsCustomersEmails[i], itemsCustomersSpecs[i], itemsCustomersCashs[i], itemsCustomersCurrencies[i], itemsCustomersTimes[i], itemsCustomersDates[i]));
                }
            }
            listChildData.put(folder, pDistricts);
        }
        expandableListAdapter = new CustomCustomerExpandableListAdapter(context, listGroupTitles, listChildData);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                currentColumn = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getColumn();
                currentAccount = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAccount();
                currentCompany = listGroupTitles.get(groupPosition);
                currentCustomer = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCustomer();
                currentCompanyPh = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCompanyPho();
                currentCustomerPh = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCustomerPho();
                currentEmail = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getEmail();
                currentSpec = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSpec();
                currentCash = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCash();
                currentCurrency = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCurrency();
                currentTime = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getTime();
                currentDate = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getDate();
                createCustomerDialog(true);
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
                    CustomerListChildItem CLCI = (CustomerListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                    createPopupChildItemMenu(view, CLCI);
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    createPopupGroupItemMenu(view, groupPos);
                }
                return true;
            }
        });

    }
    
    private void createCustomerDialog(final boolean isEdit) {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_new_customer);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_new_company = (EditText) d.findViewById(R.id.edit_new_company);
        edit_new_customer = (EditText) d.findViewById(R.id.edit_new_customer);
        edit_new_num1 = (EditText) d.findViewById(R.id.edit_new_num1);
        edit_new_num2 = (EditText) d.findViewById(R.id.edit_new_num2);
        edit_new_email = (EditText) d.findViewById(R.id.edit_new_email);
        edit_new_spec1 = (EditText) d.findViewById(R.id.edit_new_spec1);
        edit_new_balance = (EditText) d.findViewById(R.id.edit_new_balance);
        spin_new_currency = (Spinner) d.findViewById(R.id.spin_new_currency_c);
        spin_new_type = (Spinner) d.findViewById(R.id.spin_new_type);
        btn_save_customer = (Button) d.findViewById(R.id.save_new_customer);
        btn_cancel_customer = (Button) d.findViewById(R.id.cancel_new_customer);
        til_customer = (TextInputLayout) d.findViewById(R.id.til_customer);
        til_num = (TextInputLayout) d.findViewById(R.id.til_num);
        txt_time = (TextView)d.findViewById(R.id.txt_time);
        txt_date = (TextView)d.findViewById(R.id.txt_date);

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
        btn_save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_new_customer.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_num2.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    if(!edit_new_balance.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_new_balance.setText("0");
                    if (!edit_new_company.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_new_company.setText(res.getString(R.string.without_folder));
                    if (isEdit) {
                        editFromSQL(currentColumn
                                , Integer.valueOf(edit_new_balance.getText().toString())
                                , edit_new_company.getText().toString()
                                , edit_new_customer.getText().toString()
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
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentCustomerColumn", 0);
                        addToSQL(column + 1
                                , Integer.valueOf(edit_new_balance.getText().toString())
                                , edit_new_company.getText().toString()
                                , edit_new_customer.getText().toString()
                                , edit_new_num1.getText().toString()
                                , edit_new_num2.getText().toString()
                                , edit_new_email.getText().toString()
                                , edit_new_spec1.getText().toString()
                                , spin_new_type.getSelectedItem().toString()
                                , spin_new_currency.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentCustomerColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    d.cancel();
                } else {
                    if (!edit_new_customer.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_customer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_customer.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_num2.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_num2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_num2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                }
            }
        });
        btn_cancel_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
        if (isEdit) {
            edit_new_company.setText(currentCompany);
            edit_new_customer.setText(String.valueOf(currentCustomer));
            edit_new_num1.setText(currentCompanyPh);
            edit_new_num2.setText(currentCustomerPh);
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
        edit_new_customer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_customer.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.show();
    }

    private void createPopupChildItemMenu(View v, final CustomerListChildItem CLCI) {
        PopupMenu popup = new PopupMenu(context, v);
        currentColumn = CLCI.getColumn();
        currentAccount = CLCI.getAccount();
        currentCompany = CLCI.getCompany();
        currentCustomer = CLCI.getCustomer();
        currentCompanyPh = CLCI.getCompanyPho();
        currentCustomerPh = CLCI.getCustomerPho();
        currentEmail = CLCI.getEmail();
        currentCash = CLCI.getCash();
        currentSpec = CLCI.getSpec();
        currentCurrency = CLCI.getCurrency();
        currentTime = CLCI.getTime();
        currentDate = CLCI.getTime();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_cus_add_item:
                        /////////////////////////////
                        return true;
                    case R.id.pop_cus_edit_item:
                        createCustomerDialog(true);
                        return true;
                    case R.id.pop_cus_delete_item:
                        deleteFromSQL(CLCI.getColumn(), CLCI.getAccount(), CLCI.getCompany(), CLCI.getCustomer(), CLCI.getCompanyPho(), CLCI.getCustomerPho(), CLCI.getEmail(), CLCI.getSpec(), CLCI.getCash(), CLCI.getCurrency(), CLCI.getTime(), CLCI.getDate());
                        createListFromSQL();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_customer);
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
                        List<CustomerListChildItem> customers = db.allCustomers();
                        for (int i = 0; i < listChildData.size(); i++) {
                            CustomerListChildItem CLCI = customers.get(i);
                            if (CLCI.getCompany().matches(listGroupTitles.get(groupPos))) {
                                deleteFromSQL(CLCI.getColumn(), CLCI.getAccount(), CLCI.getCompany(), CLCI.getCustomer(), CLCI.getCompanyPho(), CLCI.getCustomerPho(), CLCI.getEmail(), CLCI.getSpec(), CLCI.getCash(), CLCI.getCurrency(), CLCI.getTime(), CLCI.getDate());
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
                List<CustomerListChildItem> customers = db.allCustomers();
                for (int i = 0; i < listChildData.size(); i++) {
                    CustomerListChildItem CLCI = customers.get(i);
                    if (CLCI.getCompany().matches(listGroupTitles.get(groupPos))) {
                        editFromSQL(CLCI.getColumn(), CLCI.getAccount(), CLCI.getCompany(), CLCI.getCustomer(), CLCI.getCompanyPho(), CLCI.getCustomerPho(), CLCI.getEmail(), CLCI.getSpec(), CLCI.getCash(), CLCI.getCurrency(), CLCI.getTime(), CLCI.getDate());
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
                createCustomerDialog(false);
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
                        List<CustomerListChildItem> customers = db.allCustomers();
                        for (int i = 0; i < customers.size(); i++) {
                            CustomerListChildItem CLCI = customers.get(i);
                            deleteFromSQL(CLCI.getColumn(), CLCI.getAccount(), CLCI.getCompany(), CLCI.getCustomer(), CLCI.getCompanyPho(), CLCI.getCustomerPho(), CLCI.getEmail(), CLCI.getSpec(), CLCI.getCash(), CLCI.getCurrency(), CLCI.getTime(), CLCI.getDate());
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
        d1.setContentView(R.layout.dialog_search_customer);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_customers_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_customers);
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
        listAllCustomers = new ArrayList<CustomerListChildItem>();
        listAllCustomers.addAll(db.allCustomers());
        customSearchCustomersAdapter = new CustomSearchCustomersAdapter(context, R.layout.list_customers_item, listAllCustomers);
        searchList.setAdapter(customSearchCustomersAdapter);
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
        customSearchCustomersAdapter.filter(text);
        return false;
    }
}

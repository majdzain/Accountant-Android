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

public class ListExpensesActivity extends Fragment implements SearchView.OnQueryTextListener {
    Context context;
    EditText edit_expense, edit_expense_c, edit_expense_cat, edit_expense_state, edit_expense_amount;
    Button btn_save, btn_cancel;
    TextInputLayout til_expense, til_expense_c, til_amount;
    Spinner spin;
    Resources res;
    FloatingActionButton fab;
    String code = "SYP";
    ExpandableListView expListView;
    CustomExpenseExpandableListAdapter expandableListAdapter;
    CustomSearchExpensesAdapter customSearchExpensesAdapter;
    ArrayList<String> listGroupTitles;
    HashMap<String, List<ExpenseListChildItem>> listChildData;
    ListView searchList;
    SearchView searchView;
    ArrayList<ExpenseListChildItem> listAllExpenses;
    ExpenseSQLDatabaseHandler db;
    int currentColumn;
    int currentAmount;
    String currentCategory;
    String currentExpense;
    String currentSpec;
    String currentConsumer;
    String currentCurrency;
    String currentTime;
    String currentDate;
    View view;
    SharedPreferences pref;
    TextView txt_date, txt_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_expenses, container, false);
        context = view.getContext();
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        setHasOptionsMenu(true);
        res = context.getResources();
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionExpense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createExpenseDialog(false);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        return view;
    }

    private void createExpenseDialog(final boolean isEdit) {
        final Dialog dp = new Dialog(context);
        dp.setContentView(R.layout.dialog_new_expense);
        dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_expense = (EditText) dp.findViewById(R.id.edit_new_expense);
        edit_expense_c = (EditText) dp.findViewById(R.id.edit_new_expense_c);
        edit_expense_cat = (EditText) dp.findViewById(R.id.edit_new_expense_cat);
        edit_expense_state = (EditText) dp.findViewById(R.id.edit_new_expense_state);
        edit_expense_amount = (EditText) dp.findViewById(R.id.edit_new_expense_amount);
        btn_cancel = (Button) dp.findViewById(R.id.cancel_new_expense);
        btn_save = (Button) dp.findViewById(R.id.save_new_expense);
        spin = (Spinner) dp.findViewById(R.id.spin_new_currency_e);
        til_expense = (TextInputLayout) dp.findViewById(R.id.til_expense);
        til_expense_c = (TextInputLayout) dp.findViewById(R.id.til_expense_c);
        til_amount = (TextInputLayout) dp.findViewById(R.id.til_expense_amount);
        txt_time = (TextView) dp.findViewById(R.id.txt_time);
        txt_date = (TextView) dp.findViewById(R.id.txt_date);
        String[] currency = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin.setAdapter(spinnerCurrencyArrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_expense.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_expense_c.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_expense_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    if (!edit_expense_cat.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_expense_cat.setText(res.getString(R.string.without_folder));
                    if (isEdit) {
                        editFromSQL(currentColumn
                                , Integer.valueOf(edit_expense_amount.getText().toString())
                                , edit_expense_c.getText().toString()
                                , edit_expense.getText().toString()
                                , edit_expense_cat.getText().toString()
                                , edit_expense_state.getText().toString()
                                , spin.getSelectedItem().toString()
                                , getTime()
                                , getDate());
                        dp.cancel();
                        createListFromSQL();
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentExpenseColumn", 0);
                        addToSQL(column + 1
                                , Integer.valueOf(edit_expense_amount.getText().toString())
                                , edit_expense_c.getText().toString()
                                , edit_expense.getText().toString()
                                , edit_expense_cat.getText().toString()
                                , edit_expense_state.getText().toString()
                                , spin.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentExpenseColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    dp.cancel();
                } else {
                    if (!edit_expense.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_expense.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_expense.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if (!edit_expense_c.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_expense_c.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_expense_c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if (!edit_expense_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_expense_amount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_expense_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.cancel();
            }
        });
        if (isEdit) {
            edit_expense_cat.setText(currentCategory);
            edit_expense.setText(currentExpense);
            edit_expense_c.setText(currentConsumer);
            edit_expense_state.setText(currentSpec);
            edit_expense_amount.setText(String.valueOf(currentAmount));
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCurrency.matches("SYP"))
                spin.setSelection(0);
            else
                spin.setSelection(1);
        } else {
            spin.setSelection(0);
            txt_date.setText(getDate());
            txt_time.setText(getTime());
        }
        edit_expense.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_expense.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_expense_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_expense_c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_expense_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_expense_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dp.setCancelable(false);
        dp.setCanceledOnTouchOutside(false);
        dp.show();
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new ExpenseSQLDatabaseHandler(context);
    }

    private void addToSQL(int Column, int Amount, String Consumer, String Expense, String Category, String Spec, String Currency, String Time, String Date) {
        ExpenseListChildItem expense = new ExpenseListChildItem(Column, Amount, Consumer, Expense, Category, Spec, Currency, Time, Date);
        db.addExpense(expense);
    }

    private void deleteFromSQL(int Column, int Amount, String Consumer, String Expense, String Category, String Spec, String Currency, String Time, String Date) {
        ExpenseListChildItem expense = new ExpenseListChildItem(Column, Amount, Consumer, Expense, Category, Spec, Currency, Time, Date);
        db.deleteExpense(expense);
    }

    private void editFromSQL(int Column, int Amount, String Consumer, String Expense, String Category, String Spec, String Currency, String Time, String Date) {
        ExpenseListChildItem expense = new ExpenseListChildItem(Column, Amount, Consumer, Expense, Category, Spec, Currency, Time, Date);
        db.updateExpense(expense);
    }

    private void createListFromSQL() {
        // list all expenses
        List<ExpenseListChildItem> expenses = db.allExpenses();
        List<String> folders = db.allCategories();
        if (expenses != null) {
            createList(folders, expenses);
        }
    }

    private void createList(List<String> folders, List<ExpenseListChildItem> expenses) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.expensesList);
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
        }
        int[] itemsExpensesColumns = new int[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesColumns[i] = expenses.get(i).getColumn();
        }
        int[] itemsExpensesAmounts = new int[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesAmounts[i] = expenses.get(i).getAmount();
        }
        String[] itemsExpensesCategories = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesCategories[i] = expenses.get(i).getCategory();
        }
        String[] itemsExpensesExpenses = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesExpenses[i] = expenses.get(i).getExpense();
        }
        String[] itemsConsumersExpenses = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsConsumersExpenses[i] = expenses.get(i).getConsumer();
        }
        String[] itemsExpensesSpecs = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesSpecs[i] = expenses.get(i).getSpec();
        }
        String[] itemsExpensesCurrencies = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesCurrencies[i] = expenses.get(i).getCurrency();
        }
        String[] itemsExpensesTimes = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesTimes[i] = expenses.get(i).getTime();
        }
        String[] itemsExpensesDates = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            itemsExpensesDates[i] = expenses.get(i).getDate();
        }

        // Setting up list
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<ExpenseListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<ExpenseListChildItem> pDistricts = pDistricts = new ArrayList<ExpenseListChildItem>();
            for (int i = 0; i < expenses.size(); i++) {
                if (expenses.get(i).getCategory().matches(folder)) {
                    pDistricts.add(new ExpenseListChildItem(itemsExpensesColumns[i], itemsExpensesAmounts[i], itemsConsumersExpenses[i], itemsExpensesExpenses[i], folder, itemsExpensesSpecs[i], itemsExpensesCurrencies[i], itemsExpensesTimes[i], itemsExpensesDates[i]));
                }
            }
            listChildData.put(folder, pDistricts);
        }
        expandableListAdapter = new CustomExpenseExpandableListAdapter(context, listGroupTitles, listChildData);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                currentColumn = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getColumn();
                currentAmount = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAmount();
                currentCategory = listGroupTitles.get(groupPosition);
                currentExpense = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getExpense();
                currentSpec = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSpec();
                currentConsumer = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getConsumer();
                currentCurrency = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCurrency();
                currentTime = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getTime();
                currentDate = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getDate();
                createExpenseDialog(true);
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
                    ExpenseListChildItem ELCI = (ExpenseListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                    createPopupChildItemMenu(view, ELCI);
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    createPopupGroupItemMenu(view, groupPos);
                }
                return true;
            }
        });

    }

    private void createPopupChildItemMenu(View v, final ExpenseListChildItem ELCI) {
        PopupMenu popup = new PopupMenu(context, v);
        currentColumn = ELCI.getColumn();
        currentAmount = ELCI.getAmount();
        currentCategory = ELCI.getCategory();
        currentExpense = ELCI.getExpense();
        currentConsumer = ELCI.getConsumer();
        currentSpec = ELCI.getSpec();
        currentCurrency = ELCI.getCurrency();
        currentTime = ELCI.getTime();
        currentDate = ELCI.getTime();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_sub_edit_item:
                        createExpenseDialog(true);
                        return true;
                    case R.id.pop_sub_delete_item:
                        deleteFromSQL(ELCI.getColumn(), ELCI.getAmount(), ELCI.getConsumer(), ELCI.getExpense(), ELCI.getCategory(), ELCI.getSpec(), ELCI.getCurrency(), ELCI.getTime(), ELCI.getDate());
                        createListFromSQL();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_subject);
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
                        List<ExpenseListChildItem> expenses = db.allExpenses();
                        for (int i = 0; i < listChildData.size(); i++) {
                            ExpenseListChildItem ELCI = expenses.get(i);
                            if (ELCI.getCategory().matches(listGroupTitles.get(groupPos))) {
                                deleteFromSQL(ELCI.getColumn(), ELCI.getAmount(), ELCI.getConsumer(), ELCI.getExpense(), ELCI.getCategory(), ELCI.getSpec(), ELCI.getCurrency(), ELCI.getTime(), ELCI.getDate());
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
                List<ExpenseListChildItem> expenses = db.allExpenses();
                for (int i = 0; i < listChildData.size(); i++) {
                    ExpenseListChildItem ELCI = expenses.get(i);
                    if (ELCI.getCategory().matches(listGroupTitles.get(groupPos))) {
                        editFromSQL(ELCI.getColumn(), ELCI.getAmount(), ELCI.getConsumer(), ELCI.getExpense(), ELCI.getCategory(), ELCI.getSpec(), ELCI.getCurrency(), ELCI.getTime(), ELCI.getDate());
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
                createExpenseDialog(false);
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
                        List<ExpenseListChildItem> expenses = db.allExpenses();
                        for (int i = 0; i < expenses.size(); i++) {
                            ExpenseListChildItem ELCI = expenses.get(i);
                            deleteFromSQL(ELCI.getColumn(), ELCI.getAmount(), ELCI.getConsumer(), ELCI.getExpense(), ELCI.getCategory(), ELCI.getSpec(), ELCI.getCurrency(), ELCI.getTime(), ELCI.getDate());
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
        d1.setContentView(R.layout.dialog_search_expense);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_expenses_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_expenses);
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
        listAllExpenses = new ArrayList<ExpenseListChildItem>();
        listAllExpenses.addAll(db.allExpenses());
        customSearchExpensesAdapter = new CustomSearchExpensesAdapter(context, R.layout.list_expense_item, listAllExpenses);
        searchList.setAdapter(customSearchExpensesAdapter);
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
        customSearchExpensesAdapter.filter(text);
        return false;
    }
}

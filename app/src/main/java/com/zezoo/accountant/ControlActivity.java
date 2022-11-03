package com.zezoo.accountant;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;


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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;


public class ControlActivity extends Fragment {
    Context context;
    LinearLayout addPurchases, addSales, addTemporary, addProduct, addCustomer, addSupplier, addExpense, addPBond, addRBond;
    View view;
    Resources res;
    protected BarChart mChart;

    AutoCompleteTextView edit_new_folder, edit_new_buyer;
    EditText edit_new_code, edit_new_subject, edit_new_amount, edit_new_lock, edit_new_spec, edit_new_unit, edit_new_last, edit_new_a_lock, edit_new_a_last;
    Button btn_save_subject, btn_cancel_subject;
    TextInputLayout til_subject, til_amount, til_unit, til_code, til_buyer;
    TextView txt_date, txt_time;
    Spinner spin_currency;
    String code = "SYP";
    private SubjectSQLDatabaseHandler dbs;
    private SupplierSQLDatabaseHandler dbsu;
    private CustomerSQLDatabaseHandler dbc;
    private ExpenseSQLDatabaseHandler dbe;
    private PBondSQLDatabaseHandler dbpb;
    private RBondSQLDatabaseHandler dbrb;
    private JBondSQLDatabaseHandler dbjb;

    EditText edit_new_company, edit_new_customer, edit_new_num1, edit_new_num2, edit_new_email, edit_new_balance, edit_new_spec1;
    Spinner spin_new_currency, spin_new_type;
    Button btn_save_customer, btn_cancel_customer;
    TextInputLayout til_customer, til_num;

    EditText edit_p_debtor, edit_p_num, edit_p_state, edit_p_amount, edit_t_creditor, edit_t_num, edit_t_state, edit_t_amount, edit_j_name, edit_j_num, edit_j_state, edit_j_amount_t, edit_j_amount_p;
    Button btn_p_save, btn_p_cancel, btn_t_save, btn_t_cancel, btn_j_save, btn_j_cancel;
    Spinner spin_p, spin_t, spin_j;
    TextView txt_j_final;
    TextInputLayout til_debtor, til_creditor, til_cre_deb, til_p_amount, til_t_amount, til_j_amountT, til_j_amountP;
    String codeP, codeT;

    EditText edit_new_company_s, edit_new_supplier_s, edit_new_num1_s, edit_new_num2_s, edit_new_email_s, edit_new_balance_s, edit_new_spec1_s;
    Spinner spin_new_currency_s, spin_new_type_s;
    Button btn_save_supplier, btn_cancel_supplier;
    TextInputLayout til_supplier_s, til_num_s;

    EditText edit_expense, edit_expense_c, edit_expense_cat, edit_expense_state, edit_expense_amount;
    Button btn_save_e, btn_cancel_e;
    TextInputLayout til_expense, til_expense_c, til_amount_e;
    Spinner spin_expense;
    SharedPreferences pref;

    String FIRST_BASIC_WORDS[], SECOND_BASIC_WORDS[];
    boolean isVoiceControlingEnabled = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_control, container, false);
        context = view.getContext();
        res = context.getResources();
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        setHasOptionsMenu(true);
        setVaribales();
        setClicks();
        createChart();
        return view;
    }

    private void setVaribales() {
        addPurchases = (LinearLayout) view.findViewById(R.id.addPurBill);
        addSales = (LinearLayout) view.findViewById(R.id.addSalesBill);
        addTemporary = (LinearLayout) view.findViewById(R.id.addTemporaryBill);
        addProduct = (LinearLayout) view.findViewById(R.id.addProduct);
        addCustomer = (LinearLayout) view.findViewById(R.id.addCustomer);
        addSupplier = (LinearLayout) view.findViewById(R.id.addSupplier);
        addExpense = (LinearLayout) view.findViewById(R.id.addExpense);
        addPBond = (LinearLayout) view.findViewById(R.id.addPBond);
        addRBond = (LinearLayout) view.findViewById(R.id.addRBond);
        mChart = (BarChart) view.findViewById(R.id.chart);
        dbs = new SubjectSQLDatabaseHandler(context);
        dbsu = new SupplierSQLDatabaseHandler(context);
        dbc = new CustomerSQLDatabaseHandler(context);
        dbe = new ExpenseSQLDatabaseHandler(context);
        dbpb = new PBondSQLDatabaseHandler(context);
        dbrb = new RBondSQLDatabaseHandler(context);
        dbjb = new JBondSQLDatabaseHandler(context);
        FIRST_BASIC_WORDS = res.getStringArray(R.array.FIRST_BASIC_WORDS);
        SECOND_BASIC_WORDS = res.getStringArray(R.array.SECOND_BASIC_WORDS);
    }

    private void setClicks() {
        addPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 1);
                startActivity(i);
            }
        });
        addSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 2);
                startActivity(i);
            }
        });
        addTemporary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 3);
                startActivity(i);
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTypeDialog(false);
            }
        });
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomerDialog(false);
            }
        });
        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSupplierDialog();
            }
        });
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createExpenseDialog();
            }
        });
        addPBond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPaymentDialog();
            }
        });
        addRBond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTakenDialog();
            }
        });
    }

    private void createChart() {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getXAxis().setEnabled(false);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(false);

        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.5f; // x4 DataSet
        int groupCount = 4;
        int startYear = 2018;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, Float.parseFloat("9000")));
        yVals2.add(new BarEntry(0, Float.parseFloat("25000")));
        yVals3.add(new BarEntry(0, Float.parseFloat("18000")));
        yVals4.add(new BarEntry(0, Float.parseFloat("30000")));
        BarDataSet set1, set2, set3, set4;
        // create 4 DataSets
        set1 = new BarDataSet(yVals1, res.getString(R.string.control_balance));
        set1.setColor(res.getColor(R.color.orange));
        set2 = new BarDataSet(yVals2, res.getString(R.string.control_profits));
        set2.setColor(res.getColor(R.color.colorAccent));
        set3 = new BarDataSet(yVals3, res.getString(R.string.control_sales));
        set3.setColor(res.getColor(R.color.purple));
        set4 = new BarDataSet(yVals4, res.getString(R.string.control_debt));
        set4.setColor(res.getColor(R.color.darker_orange));

        BarData data = new BarData(set1, set2, set3, set4);
        data.setValueFormatter(new CustomValueFormatter("$"));
        mChart.setData(data);
        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);
        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(startYear);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        mChart.getXAxis().setAxisMaximum(startYear + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mChart.groupBars(startYear, groupSpace, barSpace);
        mChart.animateY(2000);
        mChart.invalidate();
        mChart.zoom(3.98f, 0f, 0f, 0f);
    }

    private void createTypeDialog(final boolean isEdit) {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_new_subject);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_new_folder = (AutoCompleteTextView) d.findViewById(R.id.edit_new_folder);
        edit_new_buyer = (AutoCompleteTextView) d.findViewById(R.id.edit_new_buyer);
        edit_new_code = (EditText) d.findViewById(R.id.edit_new_code);
        edit_new_subject = (EditText) d.findViewById(R.id.edit_new_subject);
        edit_new_amount = (EditText) d.findViewById(R.id.edit_new_amount);
        edit_new_a_lock = (EditText) d.findViewById(R.id.edit_new_a_lock);
        edit_new_a_last = (EditText) d.findViewById(R.id.edit_new_a_last);
        edit_new_lock = (EditText) d.findViewById(R.id.edit_new_lock);
        edit_new_spec = (EditText) d.findViewById(R.id.edit_new_spec);
        edit_new_unit = (EditText) d.findViewById(R.id.edit_new_unit);
        edit_new_last = (EditText) d.findViewById(R.id.edit_new_last);
        btn_save_subject = (Button) d.findViewById(R.id.save_new_subject);
        btn_cancel_subject = (Button) d.findViewById(R.id.cancel_new_subject);
        til_subject = (TextInputLayout) d.findViewById(R.id.til_subject);
        til_amount = (TextInputLayout) d.findViewById(R.id.til_amount);
        til_unit = (TextInputLayout) d.findViewById(R.id.til_unit);
        til_code = (TextInputLayout) d.findViewById(R.id.til_code);
        til_buyer = (TextInputLayout) d.findViewById(R.id.til_buyer);
        txt_date = (TextView) d.findViewById(R.id.txt_date);
        txt_time = (TextView) d.findViewById(R.id.txt_time);
        spin_currency = (Spinner) d.findViewById(R.id.spin_new_currency_s);
        String[] currency = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_currency.setAdapter(spinnerCurrencyArrayAdapter);
        ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dbs.allFolders());
        edit_new_folder.setAdapter(folderSuggestionAdapter);
        String allSuppliers[] = new String[dbsu.allSuppliers().size()];
        for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
            allSuppliers[k] = dbsu.allSuppliers().get(k).getSupplier();
        }
        ArrayAdapter<String> buyerSuggestionAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, allSuppliers);
        edit_new_buyer.setAdapter(buyerSuggestionAdapter);
        spin_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (isEdit)
                    btn_save_subject.setVisibility(View.VISIBLE);
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
        btn_save_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edit_new_last.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    edit_new_last.setText(String.valueOf(0));
                if (!edit_new_lock.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    edit_new_lock.setText(String.valueOf(0));
                if (!edit_new_a_last.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    edit_new_a_last.setText(String.valueOf(0));
                if (!edit_new_a_lock.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    edit_new_a_lock.setText(String.valueOf(0));
                if (!edit_new_folder.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    edit_new_folder.setText(res.getString(R.string.without_folder));
                if (edit_new_code.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_subject.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_unit.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_buyer.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    boolean isCodeExist = false;
                    boolean isNameExist = false;
                    for (int u = 0; u < dbs.allSubjects().size(); u++) {
                        if (Integer.valueOf(edit_new_code.getText().toString()) == dbs.allSubjects().get(u).getId()) {
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_new_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_new_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(context, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
                            isCodeExist = true;
                            break;
                        }
                    }
                    for (int v = 0; v < dbs.allSubjects().size(); v++) {
                        if (edit_new_subject.getText().toString().matches(dbs.allSubjects().get(v).getName())) {
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(context, res.getString(R.string.error_product), Toast.LENGTH_LONG).show();
                            isNameExist = true;
                            break;
                        }
                    }
                    if (!isCodeExist && !isNameExist) {
                        if (isEdit) {
                        } else {
                            int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectColumn", 0);
                            addProductToSQL(column + 1
                                    , Integer.valueOf(edit_new_code.getText().toString())
                                    , edit_new_buyer.getText().toString()
                                    , edit_new_folder.getText().toString()
                                    , edit_new_subject.getText().toString()
                                    , Double.valueOf(edit_new_amount.getText().toString())
                                    , Double.valueOf(edit_new_a_last.getText().toString())
                                    , Double.valueOf(edit_new_a_lock.getText().toString())
                                    , Double.valueOf(edit_new_unit.getText().toString())
                                    , Double.valueOf(edit_new_last.getText().toString())
                                    , Double.valueOf(edit_new_lock.getText().toString())
                                    , edit_new_spec.getText().toString()
                                    , code
                                    , txt_time.getText().toString()
                                    , txt_date.getText().toString());
                            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectColumn", column + 1).commit();
                            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId", Integer.valueOf(edit_new_code.getText().toString()) + 1).commit();
                        }
                        d.cancel();
                    }
                } else {
                    if (!edit_new_code.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_subject.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_amount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_unit.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_unit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_unit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    if (!edit_new_buyer.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_buyer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_buyer.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                }
            }
        });


        edit_new_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_new_unit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_unit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_new_buyer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_buyer.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_cancel_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
        if (isEdit) {
        } else {
            spin_currency.setSelection(0);
            int id = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001);
            edit_new_code.setText(String.valueOf(id));
            txt_date.setText(getDate());
            txt_time.setText(getTime());
        }
        edit_new_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                for (int u = 0; u < dbs.allSubjects().size(); u++) {
                    if (Integer.valueOf(edit_new_code.getText().toString()) == dbs.allSubjects().get(u).getId()) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        Toast.makeText(context, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_new_subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                for (int v = 0; v < dbs.allSubjects().size(); v++) {
                    if (edit_new_subject.getText().toString().matches(dbs.allSubjects().get(v).getName())) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_new_subject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        Toast.makeText(context, res.getString(R.string.error_product), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.show();
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
        btn_save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_new_customer.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_num2.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    if (!edit_new_balance.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        edit_new_balance.setText("0");
                    if (isEdit) {
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentCustomerColumn", 0);
                        addCustomerToSQL(column + 1
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

    private void createTakenDialog() {
        final Dialog dt = new Dialog(context);
        dt.setContentView(R.layout.dialog_new_bond_taken);
        dt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_t_creditor = (EditText) dt.findViewById(R.id.edit_new_taken);
        edit_t_num = (EditText) dt.findViewById(R.id.edit_new_taken_num);
        edit_t_state = (EditText) dt.findViewById(R.id.edit_new_taken_state);
        edit_t_amount = (EditText) dt.findViewById(R.id.edit_new_taken_amount);
        btn_t_cancel = (Button) dt.findViewById(R.id.cancel_new_taken);
        btn_t_save = (Button) dt.findViewById(R.id.save_new_taken);
        spin_t = (Spinner) dt.findViewById(R.id.spin_new_currency_t);
        til_creditor = (TextInputLayout) dt.findViewById(R.id.til_creditor);
        til_t_amount = (TextInputLayout) dt.findViewById(R.id.til_t_amount);

        String[] currencyT = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapterT = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currencyT);
        spinnerCurrencyArrayAdapterT.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_t.setAdapter(spinnerCurrencyArrayAdapterT);
        spin_t.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index == 0)
                    codeT = "SYP";
                else
                    codeT = "$";
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btn_t_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_t_creditor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_t_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    dt.cancel();
                } else {
                    //edit
                    if (!edit_t_creditor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_creditor.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                    if (!edit_t_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_t_amount.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                }
            }
        });
        btn_t_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dt.cancel();
            }
        });
        spin_t.setSelection(0);
        dt.setCancelable(false);
        dt.setCanceledOnTouchOutside(false);
        dt.show();
    }

    private void createPaymentDialog() {
        final Dialog dp = new Dialog(context);
        dp.setContentView(R.layout.dialog_new_bond_payment);
        dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_p_debtor = (EditText) dp.findViewById(R.id.edit_new_payment);
        edit_p_num = (EditText) dp.findViewById(R.id.edit_new_payment_num);
        edit_p_state = (EditText) dp.findViewById(R.id.edit_new_payment_state);
        edit_p_amount = (EditText) dp.findViewById(R.id.edit_new_payment_amount);
        btn_p_cancel = (Button) dp.findViewById(R.id.cancel_new_payment);
        btn_p_save = (Button) dp.findViewById(R.id.save_new_payment);
        spin_p = (Spinner) dp.findViewById(R.id.spin_new_currency_p);
        til_debtor = (TextInputLayout) dp.findViewById(R.id.til_debtor);
        til_p_amount = (TextInputLayout) dp.findViewById(R.id.til_p_amount);

        String[] currencyP = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapterP = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currencyP);
        spinnerCurrencyArrayAdapterP.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_p.setAdapter(spinnerCurrencyArrayAdapterP);
        spin_p.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index == 0)
                    codeP = "SYP";
                else
                    codeP = "$";
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btn_p_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_p_debtor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_p_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    dp.cancel();
                } else {
                    if (!edit_p_debtor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_debtor.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                    if (!edit_p_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_p_amount.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                }
            }
        });
        btn_p_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.cancel();
            }
        });
        spin_p.setSelection(0);
        dp.setCancelable(false);
        dp.setCanceledOnTouchOutside(false);
        dp.show();
    }

    private void createSupplierDialog() {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_new_supplier);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_new_company_s = (EditText) d.findViewById(R.id.edit_new_company);
        edit_new_supplier_s = (EditText) d.findViewById(R.id.edit_new_supplier);
        edit_new_num1_s = (EditText) d.findViewById(R.id.edit_new_num1);
        edit_new_num2_s = (EditText) d.findViewById(R.id.edit_new_num2);
        edit_new_email_s = (EditText) d.findViewById(R.id.edit_new_email);
        edit_new_spec1_s = (EditText) d.findViewById(R.id.edit_new_spec1);
        edit_new_balance_s = (EditText) d.findViewById(R.id.edit_new_balance);
        spin_new_currency_s = (Spinner) d.findViewById(R.id.spin_new_currency_c);
        spin_new_type_s = (Spinner) d.findViewById(R.id.spin_new_type);
        btn_save_supplier = (Button) d.findViewById(R.id.save_new_supplier);
        btn_cancel_supplier = (Button) d.findViewById(R.id.cancel_new_supplier);
        til_supplier_s = (TextInputLayout) d.findViewById(R.id.til_supplier);
        til_num_s = (TextInputLayout) d.findViewById(R.id.til_num);

        String[] currency = res.getStringArray(R.array.dola_sy);
        String[] type = res.getStringArray(R.array.debt_cash);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        ArrayAdapter<CharSequence> spinnerTypeArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, type);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerTypeArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_new_currency_s.setAdapter(spinnerCurrencyArrayAdapter);
        spin_new_type_s.setAdapter(spinnerTypeArrayAdapter);

        spin_new_currency_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spin_new_currency_s.setSelection(0);
        spin_new_type_s.setSelection(0);
        btn_save_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_new_supplier_s.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_new_num2_s.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    d.cancel();
                } else {
                    if (!edit_new_supplier_s.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_supplier_s.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                    if (!edit_new_num2_s.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_num_s.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                }
            }
        });
        btn_cancel_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.show();
    }

    private void createExpenseDialog() {
        final Dialog dp = new Dialog(context);
        dp.setContentView(R.layout.dialog_new_expense);
        dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_expense = (EditText) dp.findViewById(R.id.edit_new_expense);
        edit_expense_c = (EditText) dp.findViewById(R.id.edit_new_expense_c);
        edit_expense_cat = (EditText) dp.findViewById(R.id.edit_new_expense_cat);
        edit_expense_state = (EditText) dp.findViewById(R.id.edit_new_expense_state);
        edit_expense_amount = (EditText) dp.findViewById(R.id.edit_new_expense_amount);
        btn_cancel_e = (Button) dp.findViewById(R.id.cancel_new_expense);
        btn_save_e = (Button) dp.findViewById(R.id.save_new_expense);
        spin_expense = (Spinner) dp.findViewById(R.id.spin_new_currency_e);
        til_expense = (TextInputLayout) dp.findViewById(R.id.til_expense);
        til_expense_c = (TextInputLayout) dp.findViewById(R.id.til_expense_c);
        til_amount_e = (TextInputLayout) dp.findViewById(R.id.til_expense_amount);

        String[] currency = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_expense.setAdapter(spinnerCurrencyArrayAdapter);
        spin_expense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        btn_save_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_expense.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_expense_c.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && edit_expense_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    dp.cancel();
                } else {
                    if (!edit_expense.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_expense.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                    if (!edit_expense_c.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_expense_c.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                    if (!edit_expense_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                        til_amount_e.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.red)));
                }
            }
        });
        btn_cancel_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.cancel();
            }
        });
        spin_expense.setSelection(0);
        dp.setCancelable(false);
        dp.setCanceledOnTouchOutside(false);
        dp.show();
    }

    private void addProductToSQL(int Column, int Id, String Buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        SubjectListChildItem subject = new SubjectListChildItem(Column, Id, Buyer, Folder, Name, Amount, AmountLast, AmountLock, Cost, Last, Lock, Spec, Code, Time, Date);
        dbs.addSubject(subject);
    }

    private void addCustomerToSQL(int Column, int Account, String Company, String Customer, String CompanyPh, String CustomerPh, String Email, String Spec, String Cash, String Currency, String Time, String Date) {
        CustomerListChildItem customer = new CustomerListChildItem(Column, Account, Company, Customer, CompanyPh, CustomerPh, Email, Spec, Cash, Currency, Time, Date);
        dbc.addCustomer(customer);
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

    MenuItem menuVoiceItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_control, menu);
        menuVoiceItem = menu.getItem(0);
        if (pref.getBoolean("isMainVoiceControlEnable", false)) {
            menuVoiceItem.setIcon(res.getDrawable(R.drawable.ic_action_record));
            mainVoice();
        } else
            menuVoiceItem.setIcon(res.getDrawable(R.drawable.ic_action_record_f));
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_record:
                if (pref.getBoolean("isMainVoiceControlEnable", false)) {
                    isVoiceControlingEnabled = true;
                    item.setIcon(res.getDrawable(R.drawable.ic_action_record_f));
                    pref.edit().putBoolean("isMainVoiceControlEnable", false).commit();
                    isVoiceControlingEnabled = false;
                } else {
                    isVoiceControlingEnabled = false;
                    item.setIcon(res.getDrawable(R.drawable.ic_action_record));
                    pref.edit().putBoolean("isMainVoiceControlEnable", true).commit();
                    isVoiceControlingEnabled = true;
                    mainVoice();
                }

                return true;
            case R.id.menu_pref:

                return true;
            case R.id.menu_about:
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

    private void mainVoice() {
        if (isVoiceControlingEnabled) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    "com.domain.app");

            SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(context);
            RecognitionListener listener = new RecognitionListener() {
                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> voiceResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (voiceResults == null) {
                        Toast.makeText(context, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                    } else {
                        boolean isWordsCheckWork = false;
                        for (int k = 0; k < voiceResults.size(); k++) {
                            boolean isFirstWork = false;
                            boolean isSecondWork = false;
                            for (int m = 0; m < FIRST_BASIC_WORDS.length; m++) {
                                if (countWords(voiceResults.get(k)) == 1 && voiceResults.get(k).contains(FIRST_BASIC_WORDS[m]))
                                    isFirstWork = checkBasicLengthOne(voiceResults.get(k));
                                else if (countWords(voiceResults.get(k)) > 1 && firstWord(voiceResults.get(k)).contains(FIRST_BASIC_WORDS[m]))
                                    isSecondWork = checkBasicLengthMoreOne(voiceResults.get(k));
                            }
                            if (isFirstWork || isSecondWork) {
                                isWordsCheckWork = true;
                                break;
                            }
                        }
                        if (!isWordsCheckWork)
                            if (!checkBasicSentences(voiceResults)) {
                                Toast.makeText(context, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                                mainVoice();
                            }
                    }
                }

                @Override
                public void onReadyForSpeech(Bundle params) {
                }

                @Override
                public void onError(int error) {
                    Toast.makeText(context, "Error listening for speech: " + String.valueOf(error), Toast.LENGTH_LONG).show();
                    if (error != 8)
                        mainVoice();
                }

                @Override
                public void onBeginningOfSpeech() {
                }

                @Override
                public void onBufferReceived(byte[] buffer) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onEndOfSpeech() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onEvent(int eventType, Bundle params) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onRmsChanged(float rmsdB) {
                    // TODO Auto-generated method stub

                }
            };
            recognizer.setRecognitionListener(listener);
            recognizer.startListening(intent);
        }
    }

    private boolean checkBasicLengthMoreOne(String s) {
        boolean isSecondWork = false;
        boolean isSecondWordExist = false;
        for (int j = 0; j < SECOND_BASIC_WORDS.length; j++) {
            if (secondWord(s).contains(SECOND_BASIC_WORDS[j])) {
                isSecondWordExist = true;
                isSecondWork = true;
            }
        }
        if (isSecondWordExist) {
            if ((firstWord(s).contains(FIRST_BASIC_WORDS[0]) || firstWord(s).contains(FIRST_BASIC_WORDS[58]) || firstWord(s).contains(FIRST_BASIC_WORDS[3]) || firstWord(s).contains(FIRST_BASIC_WORDS[5]) || firstWord(s).contains(FIRST_BASIC_WORDS[6]))) {
                if (secondWord(s).contains(SECOND_BASIC_WORDS[4]) || secondWord(s).contains(SECOND_BASIC_WORDS[5])) {
                    Intent i3 = new Intent(context, BillActivity.class);
                    i3.putExtra("sub", 2);
                    startActivity(i3);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[6]) || secondWord(s).contains(SECOND_BASIC_WORDS[7])) {
                    Intent i1 = new Intent(context, BillActivity.class);
                    i1.putExtra("sub", 1);
                    startActivity(i1);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[8]) || secondWord(s).contains(SECOND_BASIC_WORDS[9])) {
                    Intent i2 = new Intent(context, BillActivity.class);
                    i2.putExtra("sub", 3);
                    startActivity(i2);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[10]) || secondWord(s).contains(SECOND_BASIC_WORDS[11])) {
                    createCustomerDialog(false);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[12]) || secondWord(s).contains(SECOND_BASIC_WORDS[13])) {
                    createSupplierDialog();
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[14]) || secondWord(s).contains(SECOND_BASIC_WORDS[15])) {
                    createExpenseDialog();
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[16]) || secondWord(s).contains(SECOND_BASIC_WORDS[17])) {
                    createTypeDialog(false);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[18]) || secondWord(s).contains(SECOND_BASIC_WORDS[19]) || secondWord(s).contains(SECOND_BASIC_WORDS[20]) || secondWord(s).contains(SECOND_BASIC_WORDS[21])) {
                    createPaymentDialog();
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[22]) || secondWord(s).contains(SECOND_BASIC_WORDS[23])) {
                    createTakenDialog();
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[24]) || secondWord(s).contains(SECOND_BASIC_WORDS[25])) {


                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[39]) || secondWord(s).contains(SECOND_BASIC_WORDS[40]) || secondWord(s).contains(SECOND_BASIC_WORDS[41]) || secondWord(s).contains(SECOND_BASIC_WORDS[42]) || secondWord(s).contains(SECOND_BASIC_WORDS[43]) || secondWord(s).contains(SECOND_BASIC_WORDS[44])) {


                }
            } else if (firstWord(s).contains(FIRST_BASIC_WORDS[14])) {
                if (secondWord(s).contains(SECOND_BASIC_WORDS[0]) || secondWord(s).contains(SECOND_BASIC_WORDS[1])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(13);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[2])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(12);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[3])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(11);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[4]) || secondWord(s).contains(SECOND_BASIC_WORDS[5])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(3);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[6]) || secondWord(s).contains(SECOND_BASIC_WORDS[7])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(2);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[8]) || secondWord(s).contains(SECOND_BASIC_WORDS[9])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(4);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[10]) || secondWord(s).contains(SECOND_BASIC_WORDS[11])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(6);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[12]) || secondWord(s).contains(SECOND_BASIC_WORDS[13])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(7);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[14]) || secondWord(s).contains(SECOND_BASIC_WORDS[15])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(8);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[16]) || secondWord(s).contains(SECOND_BASIC_WORDS[17])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(5);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[18]) || secondWord(s).contains(SECOND_BASIC_WORDS[19]) || secondWord(s).contains(SECOND_BASIC_WORDS[20]) || secondWord(s).contains(SECOND_BASIC_WORDS[21]) || secondWord(s).contains(SECOND_BASIC_WORDS[22]) || secondWord(s).contains(SECOND_BASIC_WORDS[23]) || secondWord(s).contains(SECOND_BASIC_WORDS[24]) || secondWord(s).contains(SECOND_BASIC_WORDS[25])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(9);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[26]) || secondWord(s).contains(SECOND_BASIC_WORDS[27]) || secondWord(s).contains(SECOND_BASIC_WORDS[28])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(10);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[29]) || secondWord(s).contains(SECOND_BASIC_WORDS[30]) || secondWord(s).contains(SECOND_BASIC_WORDS[31])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(14);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[32]) || secondWord(s).contains(SECOND_BASIC_WORDS[33]) || secondWord(s).contains(SECOND_BASIC_WORDS[34])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(15);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[35]) || secondWord(s).contains(SECOND_BASIC_WORDS[36]) || secondWord(s).contains(SECOND_BASIC_WORDS[37]) || secondWord(s).contains(SECOND_BASIC_WORDS[38])) {
                    MainActivity m = (MainActivity) getActivity();
                    m.result.setSelection(1);
                } else if (secondWord(s).contains(SECOND_BASIC_WORDS[39]) || secondWord(s).contains(SECOND_BASIC_WORDS[40]) || secondWord(s).contains(SECOND_BASIC_WORDS[41]) || secondWord(s).contains(SECOND_BASIC_WORDS[42]) || secondWord(s).contains(SECOND_BASIC_WORDS[43]) || secondWord(s).contains(SECOND_BASIC_WORDS[44])) {


                }
            }
        }
        return isSecondWork;
    }

    private boolean checkBasicLengthOne(String s) {
        boolean isFirstWork = false;
        if (s.contains(FIRST_BASIC_WORDS[4])) {
            closeApplication();
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[7]) || s.contains(FIRST_BASIC_WORDS[8]) || s.contains(FIRST_BASIC_WORDS[9]) || s.contains(FIRST_BASIC_WORDS[11])) {

            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[12]) || s.contains(FIRST_BASIC_WORDS[13])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(13);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[15])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(12);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[16])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(11);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[17]) || s.contains(FIRST_BASIC_WORDS[18])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(3);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[19]) || s.contains(FIRST_BASIC_WORDS[20])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(2);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[21]) || s.contains(FIRST_BASIC_WORDS[22])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(4);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[23]) || s.contains(FIRST_BASIC_WORDS[24])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(6);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[25]) || s.contains(FIRST_BASIC_WORDS[26])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(7);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[27]) || s.contains(FIRST_BASIC_WORDS[28])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(8);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[29]) || s.contains(FIRST_BASIC_WORDS[30])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(5);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[31]) || s.contains(FIRST_BASIC_WORDS[32]) || s.contains(FIRST_BASIC_WORDS[33]) || s.contains(FIRST_BASIC_WORDS[34]) || s.contains(FIRST_BASIC_WORDS[35]) || s.contains(FIRST_BASIC_WORDS[36]) || s.contains(FIRST_BASIC_WORDS[37]) || s.contains(FIRST_BASIC_WORDS[38])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(9);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[39]) || s.contains(FIRST_BASIC_WORDS[40]) || s.contains(FIRST_BASIC_WORDS[41])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(10);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[42]) || s.contains(FIRST_BASIC_WORDS[43]) || s.contains(FIRST_BASIC_WORDS[44])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(14);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[45]) || s.contains(FIRST_BASIC_WORDS[46]) || s.contains(FIRST_BASIC_WORDS[47])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(15);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[48]) || s.contains(FIRST_BASIC_WORDS[49]) || s.contains(FIRST_BASIC_WORDS[50]) || s.contains(FIRST_BASIC_WORDS[51])) {
            MainActivity m = (MainActivity) getActivity();
            m.result.setSelection(1);
            isFirstWork = true;
        } else if (s.contains(FIRST_BASIC_WORDS[52]) || s.contains(FIRST_BASIC_WORDS[53]) || s.contains(FIRST_BASIC_WORDS[54]) || s.contains(FIRST_BASIC_WORDS[55]) || s.contains(FIRST_BASIC_WORDS[56]) || s.contains(FIRST_BASIC_WORDS[57])) {

            isFirstWork = true;
        } else {
            isFirstWork = false;
        }
        return isFirstWork;
    }

    private boolean checkBasicSentences(ArrayList<String> voiceResults) {
        boolean isResultOk = false;
        for (int i = 0; i < voiceResults.size(); i++) {
            if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[4])) {
                closeApplication();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[4]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[4]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[4]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[4]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[4])) {
                Intent i3 = new Intent(context, BillActivity.class);
                i3.putExtra("sub", 2);
                startActivity(i3);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[6]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[6]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[6]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[6]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[6])) {
                Intent i3 = new Intent(context, BillActivity.class);
                i3.putExtra("sub", 1);
                startActivity(i3);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[8]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[8]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[8]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[8]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[8])) {
                Intent i3 = new Intent(context, BillActivity.class);
                i3.putExtra("sub", 3);
                startActivity(i3);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[10]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[10]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[10]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[10]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[10])) {
                createCustomerDialog(false);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[12]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[12]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[12]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[12]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[12])) {
                createSupplierDialog();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[14]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[14]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[14]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[14]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[14])) {
                createExpenseDialog();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[16]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[16]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[16]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[16]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[16])) {
                createTypeDialog(false);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[18]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[18]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[18]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[18]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[18])) {
                createPaymentDialog();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[20]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[20]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[20]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[20]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[20])) {
                createPaymentDialog();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[22]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[22]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[22]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[22]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[22])) {
                createTakenDialog();
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[24]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[24]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[24]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[24]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[24])) {


                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[39]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[39]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[39]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[39]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[39])) {


                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[41]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[41]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[41]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[41]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[41])) {


                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[0] + " " + SECOND_BASIC_WORDS[43]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[3] + " " + SECOND_BASIC_WORDS[43]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[5] + " " + SECOND_BASIC_WORDS[43]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[6] + " " + SECOND_BASIC_WORDS[43]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[58] + " " + SECOND_BASIC_WORDS[43])) {


                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[0])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(13);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[2])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(12);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[3])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(11);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[4])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(3);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[6])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(2);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[8])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(4);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[10])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(6);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[12])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(7);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[14])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(8);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[16])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(5);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[18]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[20]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[22]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[24])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(9);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[26]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[28])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(10);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[29]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[31])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(14);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[32]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[33]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[34])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(15);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[35]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[36]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[37]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[38])) {
                MainActivity m = (MainActivity) getActivity();
                m.result.setSelection(1);
                isResultOk = true;
                break;
            } else if (voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[39]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[41]) || voiceResults.get(i).contains(FIRST_BASIC_WORDS[14] + " " + SECOND_BASIC_WORDS[43])) {


                isResultOk = true;
                break;
            }
        }
        return isResultOk;
    }

    public static int countWords(String s) {

        int wordCount = 0;

        boolean word = false;
        int endOfLine = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public String firstWord(String s) {
        return s.substring(0, s.indexOf(" ")).replaceAll(" ", "");
    }

    public String secondWord(String s) {
        String s_ = s;
        if (countWords(s) > 2) {
            s_ = s.substring(s.indexOf(" "), s.indexOf(" ", s.indexOf(" ") + 1)).replaceAll(" ", "");
        } else if (countWords(s) == 2) {
            s_ = s.substring(s.indexOf(" ")).replaceAll(" ", "");
        }
        return s_;
    }

    public void closeApplication() {
        getActivity().finish();
        getActivity().finishAffinity();
        getActivity().moveTaskToBack(true);
    }

    @Override
    public void onResume() {
        if (pref.getBoolean("isMainVoiceControlEnable", false)) {
            isVoiceControlingEnabled = true;
            mainVoice();
        }
        super.onResume();
    }
}

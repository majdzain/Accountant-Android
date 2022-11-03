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
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;

public class ListBondsActivity extends Fragment implements SearchView.OnQueryTextListener {
    FloatingActionButton fabTaken;
    FloatingActionButton fabPayment;
    FloatingActionButton fabJournal;
    FloatingActionMenu fam;

    EditText edit_p_debtor, edit_p_num, edit_p_state, edit_p_amount, edit_t_creditor, edit_t_num, edit_t_state, edit_t_amount, edit_j_name, edit_j_num, edit_j_state, edit_j_amount_t, edit_j_amount_p;
    Button btn_p_save, btn_p_cancel, btn_t_save, btn_t_cancel, btn_j_save, btn_j_cancel;
    Spinner spin_p, spin_t, spin_j;
    TextView txt_j_final;
    TextInputLayout til_debtor, til_creditor, til_cre_deb, til_p_amount, til_t_amount, til_j_amountT, til_j_amountP;

    Resources res;

    String codeP, codeT, codeJ;
    int journal_final = 0, journal_taken = 0, journal_payment = 0;
    Context context;
    ExpandableListView expListView;
    CustomBondExpandableListAdapter expandableListAdapter;
    CustomSearchPBondsAdapter customSearchPBondsAdapter;
    CustomSearchRBondsAdapter customSearchRBondsAdapter;
    CustomSearchJBondsAdapter customSearchJBondsAdapter;
    ArrayList<String> listGroupTitles;
    private List<PBondListChildItem> listChildDataP;
    private List<RBondListChildItem> listChildDataR;
    private List<JBondListChildItem> listChildDataJ;
    ListView searchList;
    SearchView searchView;
    PBondSQLDatabaseHandler dbp;
    RBondSQLDatabaseHandler dbr;
    JBondSQLDatabaseHandler dbj;
    ArrayList<PBondListChildItem> listAllPBonds;
    ArrayList<RBondListChildItem> listAllRBonds;
    ArrayList<JBondListChildItem> listAllJBonds;
    int currentColumn;
    int currentAmount;
    int currentCAmount;
    int currentDAmount;
    String currentDebCre;
    String currentPhone;
    String currentSpec;
    String currentCurrency;
    String currentTime;
    String currentDate;
    View view;
    SharedPreferences pref;
    TextView txt_date, txt_time;
    Spinner spin_type;
    int type_ = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_bonds, container, false);
        context = view.getContext();
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        fabTaken = (FloatingActionButton) view.findViewById(R.id.floatingActionTaken);
        fabPayment = (FloatingActionButton) view.findViewById(R.id.floatingActionPayment);
        fabJournal = (FloatingActionButton) view.findViewById(R.id.floatingActionJournal);
        fam = (FloatingActionMenu) view.findViewById(R.id.floatingActionMenuB);
        res = getResources();
        setHasOptionsMenu(true);
        fabTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTakenDialog(false);
            }
        });
        fabPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPaymentDialog(false);
            }
        });
        fabJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createJournalDialog(false);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        return view;
    }

    private void createJournalDialog(final boolean isEdit) {
        fam.close(true);
        final Dialog dj = new Dialog(context);
        dj.setContentView(R.layout.dialog_new_bond_journal);
        dj.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_j_name = (EditText) dj.findViewById(R.id.edit_new_journal);
        edit_j_num = (EditText) dj.findViewById(R.id.edit_new_journal_num);
        edit_j_state = (EditText) dj.findViewById(R.id.edit_new_journal_state);
        edit_j_amount_t = (EditText) dj.findViewById(R.id.edit_new_journal_amount_t);
        edit_j_amount_p = (EditText) dj.findViewById(R.id.edit_new_journal_amount_p);
        btn_j_cancel = (Button) dj.findViewById(R.id.cancel_new_journal);
        btn_j_save = (Button) dj.findViewById(R.id.save_new_journal);
        spin_j = (Spinner) dj.findViewById(R.id.spin_new_currency_j);
        txt_j_final = (TextView) dj.findViewById(R.id.txt_new_journal_final);
        til_cre_deb = (TextInputLayout) dj.findViewById(R.id.til_cre_deb);
        til_j_amountT = (TextInputLayout) dj.findViewById(R.id.til_j_amountT);
        til_j_amountP = (TextInputLayout) dj.findViewById(R.id.til_j_amountP);
        txt_time = (TextView) dj.findViewById(R.id.txt_time);
        txt_date = (TextView) dj.findViewById(R.id.txt_date);

        String[] currencyJ = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapterJ = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currencyJ);
        spinnerCurrencyArrayAdapterJ.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_j.setAdapter(spinnerCurrencyArrayAdapterJ);
        spin_j.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index == 0)
                    codeJ = "SYP";
                else
                    codeJ = "$";
                txt_j_final.setText(String.valueOf(journal_final) + " " + codeJ);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btn_j_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_j_name.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") && (edit_j_amount_p.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*") || edit_j_amount_t.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))) {
                    if (isEdit) {
                        editJFromSQL(currentColumn
                                , Integer.valueOf(edit_j_amount_t.getText().toString())
                                , Integer.valueOf(edit_j_amount_p.getText().toString())
                                , edit_j_name.getText().toString()
                                , edit_j_num.getText().toString()
                                , edit_j_state.getText().toString()
                                , spin_j.getSelectedItem().toString()
                                , getTime()
                                , getDate());
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentPBondColumn", 0);
                        addJToSQL(column + 1
                                , Integer.valueOf(edit_j_amount_t.getText().toString())
                                , Integer.valueOf(edit_j_amount_p.getText().toString())
                                , edit_j_name.getText().toString()
                                , edit_j_num.getText().toString()
                                , edit_j_state.getText().toString()
                                , spin_j.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentPBondColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    dj.cancel();
                } else {
                    if (!edit_j_name.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_j_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_j_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if (!edit_j_amount_p.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_j_amount_p.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_j_amount_p.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if(!edit_j_amount_t.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")){
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_j_amount_t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_j_amount_t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                }
            }
        });
        if (isEdit) {
            edit_j_name.setText(currentDebCre);
            edit_j_num.setText(currentPhone);
            edit_j_state.setText(currentSpec);
            edit_j_amount_t.setText(String.valueOf(currentCAmount));
            edit_j_amount_p.setText(String.valueOf(currentDAmount));
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCurrency.matches("SYP"))
                spin_j.setSelection(0);
            else
                spin_j.setSelection(1);
            if (edit_j_amount_t.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                journal_taken = Integer.valueOf(edit_j_amount_t.getText().toString());
            else
                journal_taken = 0;
            if (edit_j_amount_p.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                journal_payment = Integer.valueOf(edit_j_amount_p.getText().toString());
            else
                journal_payment = 0;
            journal_final = journal_taken - journal_payment;
            txt_j_final.setText(String.valueOf(journal_final) + " " + codeJ);
        } else {
            spin_j.setSelection(0);
            txt_date.setText(getDate());
            txt_time.setText(getTime());
            txt_j_final.setText(String.valueOf(journal_final) + " " + codeJ);
        }
        btn_j_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dj.cancel();
            }
        });
        edit_j_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_j_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_j_amount_t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_j_amount_t.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                if (edit_j_amount_t.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    journal_taken = Integer.valueOf(edit_j_amount_t.getText().toString());
                else
                    journal_taken = 0;
                if (edit_j_amount_p.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    journal_payment = Integer.valueOf(edit_j_amount_p.getText().toString());
                else
                    journal_payment = 0;
                journal_final = journal_taken - journal_payment;
                txt_j_final.setText(String.valueOf(journal_final) + " " + codeJ);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_j_amount_p.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_j_amount_p.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                if (edit_j_amount_t.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    journal_taken = Integer.valueOf(edit_j_amount_t.getText().toString());
                else
                    journal_taken = 0;
                if (edit_j_amount_p.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                    journal_payment = Integer.valueOf(edit_j_amount_p.getText().toString());
                else
                    journal_payment = 0;
                journal_final = journal_taken - journal_payment;
                txt_j_final.setText(String.valueOf(journal_final) + " " + codeJ);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dj.setCancelable(false);
        dj.setCanceledOnTouchOutside(false);
        dj.show();
    }

    private void createTakenDialog(final boolean isEdit) {
        fam.close(true);
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
        txt_time = (TextView) dt.findViewById(R.id.txt_time);
        txt_date = (TextView) dt.findViewById(R.id.txt_date);

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
                    if (isEdit) {
                        editRFromSQL(currentColumn
                                , Integer.valueOf(edit_t_amount.getText().toString())
                                , edit_t_creditor.getText().toString()
                                , edit_t_num.getText().toString()
                                , edit_t_state.getText().toString()
                                , spin_t.getSelectedItem().toString()
                                , getTime()
                                , getDate());
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentRBondColumn", 0);
                        addRToSQL(column + 1
                                , Integer.valueOf(edit_t_amount.getText().toString())
                                , edit_t_creditor.getText().toString()
                                , edit_t_num.getText().toString()
                                , edit_t_state.getText().toString()
                                , spin_t.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentRBondColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    dt.cancel();
                } else {
                    if (!edit_t_creditor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_t_creditor.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_t_creditor.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if (!edit_t_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_t_amount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_t_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                }
            }
        });
        if (isEdit) {
            edit_t_creditor.setText(currentDebCre);
            edit_t_num.setText(currentPhone);
            edit_t_state.setText(currentSpec);
            edit_t_amount.setText(String.valueOf(currentAmount));
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCurrency.matches("SYP"))
                spin_t.setSelection(0);
            else
                spin_t.setSelection(1);
        } else {
            spin_t.setSelection(0);
            txt_date.setText(getDate());
            txt_time.setText(getTime());
        }
        edit_t_creditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_t_creditor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_t_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_t_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_t_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dt.cancel();
            }
        });
        dt.setCancelable(false);
        dt.setCanceledOnTouchOutside(false);
        dt.show();
    }

    private void createPaymentDialog(final boolean isEdit) {
        fam.close(true);
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
        txt_time = (TextView) dp.findViewById(R.id.txt_time);
        txt_date = (TextView) dp.findViewById(R.id.txt_date);

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
                    if (isEdit) {
                        editPFromSQL(currentColumn
                                , Integer.valueOf(edit_p_amount.getText().toString())
                                , edit_p_debtor.getText().toString()
                                , edit_p_num.getText().toString()
                                , edit_p_state.getText().toString()
                                , spin_p.getSelectedItem().toString()
                                , getTime()
                                , getDate());
                    } else {
                        int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentPBondColumn", 0);
                        addPToSQL(column + 1
                                , Integer.valueOf(edit_p_amount.getText().toString())
                                , edit_p_debtor.getText().toString()
                                , edit_p_num.getText().toString()
                                , edit_p_state.getText().toString()
                                , spin_p.getSelectedItem().toString()
                                , txt_time.getText().toString()
                                , txt_date.getText().toString());
                        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentPBondColumn", column + 1).commit();
                    }
                    createListFromSQL();
                    dp.cancel();
                } else {
                    if (!edit_p_debtor.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_p_debtor.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_p_debtor.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                    if (!edit_p_amount.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_p_amount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_p_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                }
            }
        });
        if (isEdit) {
            edit_p_debtor.setText(currentDebCre);
            edit_p_num.setText(currentPhone);
            edit_p_state.setText(currentSpec);
            edit_p_amount.setText(String.valueOf(currentAmount));
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCurrency.matches("SYP"))
                spin_p.setSelection(0);
            else
                spin_p.setSelection(1);
        } else {
            spin_p.setSelection(0);
            txt_date.setText(getDate());
            txt_time.setText(getTime());
        }
        edit_p_debtor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_p_debtor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_p_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_p_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_p_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.cancel();
            }
        });
        dp.setCancelable(false);
        dp.setCanceledOnTouchOutside(false);
        dp.show();
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        dbp = new PBondSQLDatabaseHandler(context);
        dbr = new RBondSQLDatabaseHandler(context);
        dbj = new JBondSQLDatabaseHandler(context);
    }

    private void addPToSQL(int Column, int Amount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        PBondListChildItem bond = new PBondListChildItem(Column, Amount, Debtor, Phone, Spec, Currency, Time, Date);
        dbp.addPBond(bond);
    }

    private void deletePFromSQL(int Column, int Amount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        PBondListChildItem bond = new PBondListChildItem(Column, Amount, Debtor, Phone, Spec, Currency, Time, Date);
        dbp.deletePBond(bond);
    }

    private void editPFromSQL(int Column, int Amount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        PBondListChildItem bond = new PBondListChildItem(Column, Amount, Debtor, Phone, Spec, Currency, Time, Date);
        dbp.updatePBond(bond);
    }

    private void addRToSQL(int Column, int Amount, String Creditor, String Phone, String Spec, String Currency, String Time, String Date) {
        RBondListChildItem bond = new RBondListChildItem(Column, Amount, Creditor, Phone, Spec, Currency, Time, Date);
        dbr.addRBond(bond);
    }

    private void deleteRFromSQL(int Column, int Amount, String Creditor, String Phone, String Spec, String Currency, String Time, String Date) {
        RBondListChildItem bond = new RBondListChildItem(Column, Amount, Creditor, Phone, Spec, Currency, Time, Date);
        dbr.deleteRBond(bond);
    }

    private void editRFromSQL(int Column, int Amount, String Creditor, String Phone, String Spec, String Currency, String Time, String Date) {
        RBondListChildItem bond = new RBondListChildItem(Column, Amount, Creditor, Phone, Spec, Currency, Time, Date);
        dbr.updateRBond(bond);
    }

    private void addJToSQL(int Column, int CAmount, int DAmount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        JBondListChildItem bond = new JBondListChildItem(Column, CAmount, DAmount, Debtor, Phone, Spec, Currency, Time, Date);
        dbj.addJBond(bond);
    }

    private void deleteJFromSQL(int Column, int CAmount, int DAmount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        JBondListChildItem bond = new JBondListChildItem(Column, CAmount, DAmount, Debtor, Phone, Spec, Currency, Time, Date);
        dbj.deleteJBond(bond);
    }

    private void editJFromSQL(int Column, int CAmount, int DAmount, String Debtor, String Phone, String Spec, String Currency, String Time, String Date) {
        JBondListChildItem bond = new JBondListChildItem(Column, CAmount, DAmount, Debtor, Phone, Spec, Currency, Time, Date);
        dbj.updateJBond(bond);
    }

    private void createListFromSQL() {
        // list all bonds
        int bondsNumber = dbr.allRBonds().size() + dbp.allPBonds().size() + dbj.allJBonds().size();
        if (dbp.allPBonds() != null || dbr.allRBonds() != null || dbj.allJBonds() != null) {
            createList(bondsNumber);
        }
    }

    private void createList(int bondsNumber) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.bondsList);
        // Setting up list
        listGroupTitles = new ArrayList<String>();
        if (dbp.allPBonds().size() != 0)
            listGroupTitles.add("Payment Bonds");
        if (dbr.allRBonds().size() != 0)
            listGroupTitles.add("Receipt Bonds");
        if (dbj.allJBonds().size() != 0)
            listGroupTitles.add("Journal Bonds");
        listChildDataP = dbp.allPBonds();
        listChildDataR = dbr.allRBonds();
        listChildDataJ = dbj.allJBonds();
        expandableListAdapter = new CustomBondExpandableListAdapter(context, listGroupTitles, listChildDataP, listChildDataR, listChildDataJ);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                if (listGroupTitles.get(groupPosition).matches("Payment Bonds")) {
                    currentColumn = listChildDataP.get(childPosition).getColumn();
                    currentDebCre = listChildDataP.get(childPosition).getDebtor();
                    currentAmount = listChildDataP.get(childPosition).getAmount();
                    currentPhone = listChildDataP.get(childPosition).getPhone();
                    currentSpec = listChildDataP.get(childPosition).getSpec();
                    currentCurrency = listChildDataP.get(childPosition).getCurrency();
                    currentTime = listChildDataP.get(childPosition).getTime();
                    currentDate = listChildDataP.get(childPosition).getDate();
                    createPaymentDialog(true);
                } else if (listGroupTitles.get(groupPosition).matches("Receipt Bonds")) {
                    currentColumn = listChildDataR.get(childPosition).getColumn();
                    currentAmount = listChildDataR.get(childPosition).getAmount();
                    currentDebCre = listChildDataR.get(childPosition).getCreditor();
                    currentPhone = listChildDataR.get(childPosition).getPhone();
                    currentSpec = listChildDataR.get(childPosition).getSpec();
                    currentCurrency = listChildDataR.get(childPosition).getCurrency();
                    currentTime = listChildDataR.get(childPosition).getTime();
                    currentDate = listChildDataR.get(childPosition).getDate();
                    createTakenDialog(true);
                } else {
                    currentColumn = listChildDataJ.get(childPosition).getColumn();
                    currentCAmount = listChildDataJ.get(childPosition).getCAmount();
                    currentDAmount = listChildDataJ.get(childPosition).getDAmount();
                    currentDebCre = listChildDataJ.get(childPosition).getDebCre();
                    currentPhone = listChildDataJ.get(childPosition).getPhone();
                    currentSpec = listChildDataJ.get(childPosition).getSpec();
                    currentCurrency = listChildDataJ.get(childPosition).getCurrency();
                    currentTime = listChildDataJ.get(childPosition).getTime();
                    currentDate = listChildDataJ.get(childPosition).getDate();
                    createJournalDialog(true);
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
                    if (listGroupTitles.get(groupPos).matches("Payment Bonds")) {
                        PBondListChildItem PBLCI = (PBondListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                        createPopupChildItemMenu(view, PBLCI, 1);
                    } else if (listGroupTitles.get(groupPos).matches("Receipt Bonds")) {
                        RBondListChildItem RBLCI = (RBondListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                        createPopupChildItemMenu(view, RBLCI, 2);
                    } else {
                        JBondListChildItem JBLCI = (JBondListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                        createPopupChildItemMenu(view, JBLCI, 3);
                    }
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    createPopupGroupItemMenu(view, groupPos);
                }
                return true;
            }
        });
    }

    private void createPopupChildItemMenu(View v, final Object O, final int type) {
        PopupMenu popup = new PopupMenu(context, v);
        if (type == 1) {
            currentColumn = ((PBondListChildItem) O).getColumn();
            currentAmount = ((PBondListChildItem) O).getAmount();
            currentDebCre = ((PBondListChildItem) O).getDebtor();
            currentPhone = ((PBondListChildItem) O).getPhone();
            currentSpec = ((PBondListChildItem) O).getSpec();
            currentCurrency = ((PBondListChildItem) O).getCurrency();
            currentTime = ((PBondListChildItem) O).getTime();
            currentDate = ((PBondListChildItem) O).getDate();
        } else if (type == 2) {
            currentColumn = ((RBondListChildItem) O).getColumn();
            currentAmount = ((RBondListChildItem) O).getAmount();
            currentDebCre = ((RBondListChildItem) O).getCreditor();
            currentPhone = ((RBondListChildItem) O).getPhone();
            currentSpec = ((RBondListChildItem) O).getSpec();
            currentCurrency = ((RBondListChildItem) O).getCurrency();
            currentTime = ((RBondListChildItem) O).getTime();
            currentDate = ((RBondListChildItem) O).getDate();
        } else {
            currentColumn = ((JBondListChildItem) O).getColumn();
            currentCAmount = ((JBondListChildItem) O).getCAmount();
            currentDAmount = ((JBondListChildItem) O).getDAmount();
            currentDebCre = ((JBondListChildItem) O).getDebCre();
            currentPhone = ((JBondListChildItem) O).getPhone();
            currentSpec = ((JBondListChildItem) O).getSpec();
            currentCurrency = ((JBondListChildItem) O).getCurrency();
            currentTime = ((JBondListChildItem) O).getTime();
            currentDate = ((JBondListChildItem) O).getDate();
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_sub_edit_item:
                        if (type == 1)
                            createPaymentDialog(true);
                        else if (type == 2)
                            createTakenDialog(true);
                        else
                            createJournalDialog(true);
                        return true;
                    case R.id.pop_sub_delete_item:
                        if (type == 1)
                            deletePFromSQL(((PBondListChildItem) O).getColumn(), ((PBondListChildItem) O).getAmount(), ((PBondListChildItem) O).getDebtor(), ((PBondListChildItem) O).getPhone(), ((PBondListChildItem) O).getSpec(), ((PBondListChildItem) O).getCurrency(), ((PBondListChildItem) O).getTime(), ((PBondListChildItem) O).getDate());
                        else if (type == 2)
                            deleteRFromSQL(((RBondListChildItem) O).getColumn(), ((RBondListChildItem) O).getAmount(), ((RBondListChildItem) O).getCreditor(), ((RBondListChildItem) O).getPhone(), ((RBondListChildItem) O).getSpec(), ((RBondListChildItem) O).getCurrency(), ((RBondListChildItem) O).getTime(), ((RBondListChildItem) O).getDate());
                        else
                            deleteJFromSQL(((JBondListChildItem) O).getColumn(), ((JBondListChildItem) O).getCAmount(), ((JBondListChildItem) O).getDAmount(), ((JBondListChildItem) O).getDebCre(), ((JBondListChildItem) O).getPhone(), ((JBondListChildItem) O).getSpec(), ((JBondListChildItem) O).getCurrency(), ((JBondListChildItem) O).getTime(), ((JBondListChildItem) O).getDate());
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
                    case R.id.pop_delete_item:
                        createAlertDeleteFolderDialog(groupPos);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.popupmenu_list_group);
        popup.show();
    }

    private void createAlertDeleteFolderDialog(final int groupPos) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(res.getString(R.string.alertTitle));
        alertDialog.setMessage(res.getString(R.string.alertDeleteMessage));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listGroupTitles.get(groupPos).matches("Payment Bonds")) {
                            for (int i = 0; i < listChildDataP.size(); i++) {
                                PBondListChildItem PBLCI = listChildDataP.get(i);
                                deletePFromSQL(PBLCI.getColumn(), PBLCI.getAmount(), PBLCI.getDebtor(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
                            }
                        } else if (listGroupTitles.get(groupPos).matches("Receipt Bonds")) {
                            for (int i = 0; i < listChildDataR.size(); i++) {
                                RBondListChildItem PBLCI = listChildDataR.get(i);
                                deleteRFromSQL(PBLCI.getColumn(), PBLCI.getAmount(), PBLCI.getCreditor(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
                            }
                        } else {
                            for (int i = 0; i < listChildDataJ.size(); i++) {
                                JBondListChildItem PBLCI = listChildDataJ.get(i);
                                deleteJFromSQL(PBLCI.getColumn(), PBLCI.getCAmount(), PBLCI.getDAmount(), PBLCI.getDebCre(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
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

    Menu men;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bonds, menu);
        men = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                createSearchDialog();
                return true;
            case R.id.menu_new_p:
                createPaymentDialog(false);
                return true;
            case R.id.menu_new_r:
                createTakenDialog(false);
                return true;
            case R.id.menu_new_j:
                createJournalDialog(false);
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
                        for (int i = 0; i < listChildDataP.size(); i++) {
                            PBondListChildItem PBLCI = listChildDataP.get(i);
                            deletePFromSQL(PBLCI.getColumn(), PBLCI.getAmount(), PBLCI.getDebtor(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
                        }
                        for (int i = 0; i < listChildDataR.size(); i++) {
                            RBondListChildItem PBLCI = listChildDataR.get(i);
                            deleteRFromSQL(PBLCI.getColumn(), PBLCI.getAmount(), PBLCI.getCreditor(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
                        }
                        for (int i = 0; i < listChildDataJ.size(); i++) {
                            JBondListChildItem PBLCI = listChildDataJ.get(i);
                            deleteJFromSQL(PBLCI.getColumn(), PBLCI.getCAmount(), PBLCI.getDAmount(), PBLCI.getDebCre(), PBLCI.getPhone(), PBLCI.getSpec(), PBLCI.getCurrency(), PBLCI.getTime(), PBLCI.getDate());
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
        d1.setContentView(R.layout.dialog_search_bond);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_bonds_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_bonds);
        spin_type = (Spinner) d1.findViewById(R.id.spin_new_type);
        String[] type = res.getStringArray(R.array.bond_type);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.big_spinner_item, type);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.big_simple_spinner_dropdown);
        spin_type.setAdapter(spinnerCurrencyArrayAdapter);
        spin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                type_ = index + 1;
                createSearchList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
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
        if(type_ == 1){
            listAllPBonds = new ArrayList<PBondListChildItem>();
            listAllPBonds.addAll(dbp.allPBonds());
            customSearchPBondsAdapter = new CustomSearchPBondsAdapter(context, R.layout.list_bonds_item, listAllPBonds);
            searchList.setAdapter(customSearchPBondsAdapter);
        }else if(type_ == 2){
            listAllRBonds = new ArrayList<RBondListChildItem>();
            listAllRBonds.addAll(dbr.allRBonds());
            customSearchRBondsAdapter = new CustomSearchRBondsAdapter(context, R.layout.list_bonds_item, listAllRBonds);
            searchList.setAdapter(customSearchRBondsAdapter);
        }else{
            listAllJBonds = new ArrayList<JBondListChildItem>();
            listAllJBonds.addAll(dbj.allJBonds());
            customSearchJBondsAdapter = new CustomSearchJBondsAdapter(context, R.layout.list_bonds_item, listAllJBonds);
            searchList.setAdapter(customSearchJBondsAdapter);
        }
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
        if(type_ == 1){
            customSearchPBondsAdapter.filter(text);
        }else if(type_ == 2){
            customSearchRBondsAdapter.filter(text);
        }else{
            customSearchJBondsAdapter.filter(text);
        }
        return false;
    }
}

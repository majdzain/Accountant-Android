package com.zezoo.accountant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.PendingIntent.getActivity;
import static android.view.View.VISIBLE;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.google.android.material.textfield.TextInputLayout;

public class BillActivity extends Activity implements View.OnClickListener {
    TableLayout billTable;
    LinearLayout dialogAddEdit;
    MyParcelable myClass;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putParcelable("obj", myClass);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myClass = outState.getParcelable("obj");
    }

    Resources res;
    Spinner spin_currency, spin_type, spin_basic, spin_point, spin_point_;

    ImageView spin_img_point, spin_img_point_, spin_img_basic, spin_img_type, spin_img_currency;
    TableRow row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19, row20, row21, row22, row23, row24, row25, row26, row27, row28, row29, row30, row31, row32, row33, row34, row35, row36, row37, row38, row39, row40, row41, row42, row43, row44, row45, row46, row47, row48, row49, row50;
    Button save;
    Bundle bun;
    CustomAutoCompleteTextView dialogEditSubject;
    TextInputLayout dialogEditId_, dialogEditSubject_, dialogEditAmount_, dialogEditOffer_, dialogEditUnit_, dialogEditTotal_, dialogEditStatement_;
    EditText dialogEditId, dialogEditAmount, dialogEditOffer, dialogEditUnit, dialogEditTotal, dialogEditStatement, edit_discount, edit_addition;
    AutoCompleteTextView edit_from, edit_to, edit_from_t, edit_to_t;
    Button dialogBtnAddEdit, dialogBtnCancel;
    TextView txt_total, txt_final, dialogNumber, txt_date, txt_spinner;
    TextView sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11, sub12, sub13, sub14, sub15, sub16, sub17, sub18, sub19, sub20, sub21, sub22, sub23, sub24, sub25, sub26, sub27, sub28, sub29, sub30, sub31, sub32, sub33, sub34, sub35, sub36, sub37, sub38, sub39, sub40, sub41, sub42, sub43, sub44, sub45, sub46, sub47, sub48, sub49, sub50;
    TextView amo1, amo2, amo3, amo4, amo5, amo6, amo7, amo8, amo9, amo10, amo11, amo12, amo13, amo14, amo15, amo16, amo17, amo18, amo19, amo20, amo21, amo22, amo23, amo24, amo25, amo26, amo27, amo28, amo29, amo30, amo31, amo32, amo33, amo34, amo35, amo36, amo37, amo38, amo39, amo40, amo41, amo42, amo43, amo44, amo45, amo46, amo47, amo48, amo49, amo50;
    TextView off1, off2, off3, off4, off5, off6, off7, off8, off9, off10, off11, off12, off13, off14, off15, off16, off17, off18, off19, off20, off21, off22, off23, off24, off25, off26, off27, off28, off29, off30, off31, off32, off33, off34, off35, off36, off37, off38, off39, off40, off41, off42, off43, off44, off45, off46, off47, off48, off49, off50;
    TextView uni1, uni2, uni3, uni4, uni5, uni6, uni7, uni8, uni9, uni10, uni11, uni12, uni13, uni14, uni15, uni16, uni17, uni18, uni19, uni20, uni21, uni22, uni23, uni24, uni25, uni26, uni27, uni28, uni29, uni30, uni31, uni32, uni33, uni34, uni35, uni36, uni37, uni38, uni39, uni40, uni41, uni42, uni43, uni44, uni45, uni46, uni47, uni48, uni49, uni50;
    TextView tot1, tot2, tot3, tot4, tot5, tot6, tot7, tot8, tot9, tot10, tot11, tot12, tot13, tot14, tot15, tot16, tot17, tot18, tot19, tot20, tot21, tot22, tot23, tot24, tot25, tot26, tot27, tot28, tot29, tot30, tot31, tot32, tot33, tot34, tot35, tot36, tot37, tot38, tot39, tot40, tot41, tot42, tot43, tot44, tot45, tot46, tot47, tot48, tot49, tot50;
    TextView sta1, sta2, sta3, sta4, sta5, sta6, sta7, sta8, sta9, sta10, sta11, sta12, sta13, sta14, sta15, sta16, sta17, sta18, sta19, sta20, sta21, sta22, sta23, sta24, sta25, sta26, sta27, sta28, sta29, sta30, sta31, sta32, sta33, sta34, sta35, sta36, sta37, sta38, sta39, sta40, sta41, sta42, sta43, sta44, sta45, sta46, sta47, sta48, sta49, sta50;
    RelativeLayout rel_spin_point, rel_spin_point_;
    ImageView voiceImage, imgDeleteRow, productImage;

    String curSubject, curAmount, curOffer, curUnit, curTotal, curStatement;
    double curAmount_, curOffer_, curUnit_, curTotal_;
    String date, code;
    int spinBasicSelected, spinCurrencySelected, spinTypeSelected;
    boolean isSetInitialTotalEditText = false;
    boolean isSetIntialBasicSpinner = false;
    double tp = 0;
    double ep = 0;

    Calendar calendar;
    int day, month, year;
    private BillSQLDatabaseHandler db;
    private SubjectSQLDatabaseHandler dbs;
    private CustomerSQLDatabaseHandler dbc;
    private SupplierSQLDatabaseHandler dbsu;

    double Discount = 0, Addition = 0, Total = 0, Final = 0;
    String viewDate;

    BillListChildItem bill;
    int basic;
    SharedPreferences pref;
    ArrayList<SubjectListChildItem> listAllSubjects;
    CustomSubjectArrayAdapter customSubjectArrayAdapter;
    AutoCompleteTextView edit_new_folder, edit_new_buyer;
    EditText edit_new_code, edit_new_subject, edit_new_amount, edit_new_lock, edit_new_spec, edit_new_unit, edit_new_last, edit_new_a_last, edit_new_a_lock;
    Button btn_save_subject, btn_cancel_subject;
    TextInputLayout til_subject, til_amount, til_unit, til_code, til_buyer;
    TextView txt_date_, txt_time;
    boolean isChanged = false;
    ArrayList<String> productNames = new ArrayList<String>();
    ArrayList<Double> productAmounts = new ArrayList<Double>();
    ArrayList<Double> productOffers = new ArrayList<Double>();
    ArrayList<Double> productUnits = new ArrayList<Double>();
    ArrayList<Double> productTotals = new ArrayList<Double>();
    ArrayList<String> productStates = new ArrayList<String>();
    boolean isUnitRegistered = false;
    boolean isUnitRegistered_ = false;
    boolean isNes = false;
    boolean isVoiceControlingEnabled = true;
    boolean isSuggVoiceControlingEnabled = true;
    String[] FIRST_BILL_WORDS;
    boolean isWordsNotCompleted = false;
    String[] point;
    AutoCompleteTextView category;
    ListView searchPList;
    SearchView searchPView;
    TextView enterProducts;
    ExpandableListView expPListView;
    ArrayList<String> listPGroupTitles;
    HashMap<String, List<SubjectListChildItem>> listPChildData;
    ArrayList<Boolean> groupPCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childPCheckStates;
    CustomSubjectExpandableListAdapter expandablePListAdapter;
    CustomSearchSubjectsCheckAdapter customSearchSubjectsAdapter;
    int currentPColumn;
    String currentPFolder;
    int currentPId;
    String currentPName;
    double currentPAmount;
    double currentPAmountLast;
    double currentPAmountLock;
    double currentPCost;
    double currentPLast;
    double currentPLock;
    String currentPSpec;
    String currentPCode;
    String currentPBuyer;
    String currentPTime;
    String currentPDate;
    AppCompatCheckBox checkSellectAll;
    ImageView imgPBack;
    int indexPExp;
    View vPExp;
    int topPExp;
    boolean xPExp[];
    ArrayList<String> productsListP;
    ArrayList<Double> amountsListP;
    ArrayList<Double> offersListP;
    ArrayList<Double> unitsListP;
    ArrayList<Double> totalsListP;
    ArrayList<String> statesListP;
    boolean isPAllCheckedProg = false;
    boolean isLayoutsHided = false;
    ScrollView scrollView;
    LinearLayout linearLayoutOfItemsDidden;
    TextView titleSugg;
    ListView listSugg;
    CustomVoiceSuggListAdapter customVoiceSuggListAdapter;
    ArrayList<VoiceSuggestionListItem> voiceSuggestionListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        setVariabales();
        setBillColor();
        setDate();
        setSpinners();
        setOnClickListenerMethods();
        createSQLDatabase();
        if (bun.getBoolean("isView", false)) {
            viewDataInBill(bun.getInt("column"));
        }
        setTextWatchers();
        if (basic == 1)
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001)).commit();
        if (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setHidesActions();
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();
        ArrayList<String> a3 = new ArrayList<>();
        ArrayList<String> a4 = new ArrayList<>();
        ArrayList<String> a5 = new ArrayList<>();
        ArrayList<String> a6 = new ArrayList<>();
        a.add("تفاح عدد 10");
        a1.add("قلم السعر 50");
        a2.add("اضافه موز عدد 100");
        a3.add("نان 1 عدد 50");
        a4.add("خيار عدد 50 سعر 150");
        a5.add("ممحاة عدد 50 سعر 111");
        a6.add("اضافه قلم رصاص عدد 50");
        checkSAddRow(a);
        checkSAddRow(a1);
        checkSAddRow(a2);
        checkSAddRow(a3);
        checkSAddRow(a4);
        checkSAddRow(a5);
        checkSAddRow(a6);
    }

    private void setHidesActions() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isLayoutsHided) {
                    if (scrollView.getScrollY() != 0 && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        // Prepare the View for the animation
                        linearLayoutOfItemsDidden.setAlpha(1.0f);

// Start the animation
                        linearLayoutOfItemsDidden.animate()
                                .translationY(-1 * linearLayoutOfItemsDidden.getHeight())
                                .alpha(0.0f)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        linearLayoutOfItemsDidden.setVisibility(View.GONE);
                                    }
                                });
                        isLayoutsHided = true;
                    }
                } else {
                    if (scrollView.getScrollY() == 0) {
                        isLayoutsHided = false;
                        // Prepare the View for the animation
                        linearLayoutOfItemsDidden.setVisibility(View.VISIBLE);
                        linearLayoutOfItemsDidden.setAlpha(0.0f);
                        linearLayoutOfItemsDidden.animate()
                                .translationY(0)
                                .alpha(1.0f).setListener(null);
                    }
                }
                return false;
            }
        });
    }

    private void setTextWatchers() {
        sub1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub18.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub23.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub24.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub25.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub26.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub27.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub28.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub29.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub30.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub32.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub33.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub34.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub35.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub36.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub37.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub38.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub39.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub40.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub42.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub43.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub44.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub45.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub46.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub47.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub48.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub49.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sub50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edit_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_from.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit_to.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void viewDataInBill(int column) {
        if (bun.getBoolean("isExtractItems", false)) {
            bill = new BillListChildItem();
            bill.setCOLUMN(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillColumn", 0));
            bill.setType(bun.getInt("sub", 1));
            bill.setFrom("");
            bill.setTo("");
            bill.setTime(getTime());
            bill.setDate(getDate());
            bill.setCurrency("SYP");
            bill.setCash("Cash");
            bill.setAddition(0.0);
            bill.setDiscount(0.0);
            bill.setCode(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillId", 10001));
            if (bun.getInt("sub", 1) == 1)
                bill.setName("Bill_" + String.valueOf(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillNum", 1)));
            else if (bun.getInt("sub", 1) == 2)
                bill.setName("Invoice_" + String.valueOf(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentInvoiceNum", 1)));
            else
                bill.setName("Draft_" + String.valueOf(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentDraftNum", 1)));
            bill.setFinal(bun.getDouble("total", 0.0));
            bill.setTotal(bun.getDouble("total", 0.0));
            String[] products = bun.getStringArray("products");
            double[] amounts = bun.getDoubleArray("amounts");
            double[] offers = bun.getDoubleArray("offers");
            double[] units = bun.getDoubleArray("units");
            double[] totals = bun.getDoubleArray("totals");
            String[] states = bun.getStringArray("states");

            for (int i = 0; i < 50; i++) {
                bill.setRowByNumber(i + 1, products[i], amounts[i], offers[i], units[i], totals[i], states[i]);
            }
        } else {
            bill = db.getBill(column);
        }
        //set date
        viewDate = bill.getDate();
        setDate();
        //set from,to,cash and currency
        ArrayAdapter<String> array_spinner = (ArrayAdapter<String>) spin_point.getAdapter();
        ArrayAdapter<String> array_spinner_ = (ArrayAdapter<String>) spin_point_.getAdapter();
        ArrayAdapter<String> array_spinner_1 = (ArrayAdapter<String>) spin_type.getAdapter();
        ArrayAdapter<String> array_spinner_2 = (ArrayAdapter<String>) spin_currency.getAdapter();
        if (basic == 1) {
            edit_from.setText(bill.getFrom());
            spin_point_.setSelection(array_spinner_.getPosition(bill.getTo()));
        } else if (basic == 2) {
            edit_to.setText(bill.getTo());
            spin_point.setSelection(array_spinner.getPosition(bill.getFrom()));
        } else {
            edit_from_t.setText(bill.getFrom());
            if (bill.getTo().matches(res.getString(R.string.without_folder))) {
                edit_to_t.setText("");
            } else {
                edit_to_t.setText(bill.getTo());
            }
        }
        if (basic == 1) {
            if (getSubjectByRow(1).matches(".*[0-9a-zA-Zأ-ي]+.*") && !dbs.getSubjectById(Integer.valueOf(getSubjectByRow(1).substring(0, getSubjectByRow(1).indexOf("-") + 1).replaceAll("-", "").replaceAll(" ", ""))).getFolder().matches(res.getString(R.string.without_folder)))
                category.setText(dbs.getSubjectById(Integer.valueOf(getSubjectByRow(1).substring(0, getSubjectByRow(1).indexOf("-") + 1).replaceAll("-", "").replaceAll(" ", ""))).getFolder());
        }
        spin_type.setSelection(array_spinner_1.getPosition(bill.getCash()));
        spin_currency.setSelection(array_spinner_2.getPosition(bill.getCurrency()));
        String A1 = null, A2 = null, A3 = null, A4 = null, A5 = null, A6 = null, A7 = null, A8 = null, A9 = null, A10 = null, A11 = null, A12 = null, A13 = null, A14 = null, A15 = null, A16 = null, A17 = null, A18 = null, A19 = null, A20 = null, A21 = null, A22 = null, A23 = null, A24 = null, A25 = null, A26 = null, A27 = null, A28 = null, A29 = null, A30 = null, A31 = null, A32 = null, A33 = null, A34 = null, A35 = null, A36 = null, A37 = null, A38 = null, A39 = null, A40 = null, A41 = null, A42 = null, A43 = null, A44 = null, A45 = null, A46 = null, A47 = null, A48 = null, A49 = null, A50 = null;
        String O1 = null, O2 = null, O3 = null, O4 = null, O5 = null, O6 = null, O7 = null, O8 = null, O9 = null, O10 = null, O11 = null, O12 = null, O13 = null, O14 = null, O15 = null, O16 = null, O17 = null, O18 = null, O19 = null, O20 = null, O21 = null, O22 = null, O23 = null, O24 = null, O25 = null, O26 = null, O27 = null, O28 = null, O29 = null, O30 = null, O31 = null, O32 = null, O33 = null, O34 = null, O35 = null, O36 = null, O37 = null, O38 = null, O39 = null, O40 = null, O41 = null, O42 = null, O43 = null, O44 = null, O45 = null, O46 = null, O47 = null, O48 = null, O49 = null, O50 = null;
        String U1 = null, U2 = null, U3 = null, U4 = null, U5 = null, U6 = null, U7 = null, U8 = null, U9 = null, U10 = null, U11 = null, U12 = null, U13 = null, U14 = null, U15 = null, U16 = null, U17 = null, U18 = null, U19 = null, U20 = null, U21 = null, U22 = null, U23 = null, U24 = null, U25 = null, U26 = null, U27 = null, U28 = null, U29 = null, U30 = null, U31 = null, U32 = null, U33 = null, U34 = null, U35 = null, U36 = null, U37 = null, U38 = null, U39 = null, U40 = null, U41 = null, U42 = null, U43 = null, U44 = null, U45 = null, U46 = null, U47 = null, U48 = null, U49 = null, U50 = null;
        String T1 = null, T2 = null, T3 = null, T4 = null, T5 = null, T6 = null, T7 = null, T8 = null, T9 = null, T10 = null, T11 = null, T12 = null, T13 = null, T14 = null, T15 = null, T16 = null, T17 = null, T18 = null, T19 = null, T20 = null, T21 = null, T22 = null, T23 = null, T24 = null, T25 = null, T26 = null, T27 = null, T28 = null, T29 = null, T30 = null, T31 = null, T32 = null, T33 = null, T34 = null, T35 = null, T36 = null, T37 = null, T38 = null, T39 = null, T40 = null, T41 = null, T42 = null, T43 = null, T44 = null, T45 = null, T46 = null, T47 = null, T48 = null, T49 = null, T50 = null;
        if (bill.getA1() != (int) 0)
            A1 = String.valueOf(bill.getA1());
        if (bill.getA2() != (int) 0)
            A2 = String.valueOf(bill.getA2());
        if (bill.getA3() != (int) 0)
            A3 = String.valueOf(bill.getA3());
        if (bill.getA4() != (int) 0)
            A4 = String.valueOf(bill.getA4());
        if (bill.getA5() != (int) 0)
            A5 = String.valueOf(bill.getA5());
        if (bill.getA6() != (int) 0)
            A6 = String.valueOf(bill.getA6());
        if (bill.getA7() != (int) 0)
            A7 = String.valueOf(bill.getA7());
        if (bill.getA8() != (int) 0)
            A8 = String.valueOf(bill.getA8());
        if (bill.getA9() != (int) 0)
            A9 = String.valueOf(bill.getA9());
        if (bill.getA10() != (int) 0)
            A10 = String.valueOf(bill.getA10());
        if (bill.getA11() != (int) 0)
            A11 = String.valueOf(bill.getA11());
        if (bill.getA12() != (int) 0)
            A12 = String.valueOf(bill.getA12());
        if (bill.getA13() != (int) 0)
            A13 = String.valueOf(bill.getA13());
        if (bill.getA14() != (int) 0)
            A14 = String.valueOf(bill.getA14());
        if (bill.getA15() != (int) 0)
            A15 = String.valueOf(bill.getA15());
        if (bill.getA16() != (int) 0)
            A16 = String.valueOf(bill.getA16());
        if (bill.getA17() != (int) 0)
            A17 = String.valueOf(bill.getA17());
        if (bill.getA18() != (int) 0)
            A18 = String.valueOf(bill.getA18());
        if (bill.getA19() != (int) 0)
            A19 = String.valueOf(bill.getA19());
        if (bill.getA20() != (int) 0)
            A20 = String.valueOf(bill.getA20());
        if (bill.getA21() != (int) 0)
            A21 = String.valueOf(bill.getA21());
        if (bill.getA22() != (int) 0)
            A22 = String.valueOf(bill.getA22());
        if (bill.getA23() != (int) 0)
            A23 = String.valueOf(bill.getA23());
        if (bill.getA24() != (int) 0)
            A24 = String.valueOf(bill.getA24());
        if (bill.getA25() != (int) 0)
            A25 = String.valueOf(bill.getA25());
        if (bill.getA26() != (int) 0)
            A26 = String.valueOf(bill.getA26());
        if (bill.getA27() != (int) 0)
            A7 = String.valueOf(bill.getA27());
        if (bill.getA28() != (int) 0)
            A28 = String.valueOf(bill.getA28());
        if (bill.getA29() != (int) 0)
            A29 = String.valueOf(bill.getA29());
        if (bill.getA30() != (int) 0)
            A30 = String.valueOf(bill.getA30());
        if (bill.getA31() != (int) 0)
            A31 = String.valueOf(bill.getA31());
        if (bill.getA32() != (int) 0)
            A32 = String.valueOf(bill.getA32());
        if (bill.getA33() != (int) 0)
            A33 = String.valueOf(bill.getA33());
        if (bill.getA34() != (int) 0)
            A34 = String.valueOf(bill.getA34());
        if (bill.getA35() != (int) 0)
            A35 = String.valueOf(bill.getA35());
        if (bill.getA36() != (int) 0)
            A36 = String.valueOf(bill.getA36());
        if (bill.getA37() != (int) 0)
            A37 = String.valueOf(bill.getA37());
        if (bill.getA38() != (int) 0)
            A38 = String.valueOf(bill.getA38());
        if (bill.getA39() != (int) 0)
            A39 = String.valueOf(bill.getA39());
        if (bill.getA40() != (int) 0)
            A40 = String.valueOf(bill.getA40());
        if (bill.getA41() != (int) 0)
            A41 = String.valueOf(bill.getA41());
        if (bill.getA42() != (int) 0)
            A42 = String.valueOf(bill.getA42());
        if (bill.getA43() != (int) 0)
            A43 = String.valueOf(bill.getA43());
        if (bill.getA44() != (int) 0)
            A44 = String.valueOf(bill.getA44());
        if (bill.getA45() != (int) 0)
            A45 = String.valueOf(bill.getA45());
        if (bill.getA46() != (int) 0)
            A46 = String.valueOf(bill.getA46());
        if (bill.getA47() != (int) 0)
            A47 = String.valueOf(bill.getA47());
        if (bill.getA48() != (int) 0)
            A48 = String.valueOf(bill.getA48());
        if (bill.getA49() != (int) 0)
            A49 = String.valueOf(bill.getA49());
        if (bill.getA50() != (int) 0)
            A50 = String.valueOf(bill.getA50());
        if (bill.getA1() != (int) 0)
            O1 = String.valueOf(bill.getO1());
        if (bill.getA2() != (int) 0)
            O2 = String.valueOf(bill.getO2());
        if (bill.getA3() != (int) 0)
            O3 = String.valueOf(bill.getO3());
        if (bill.getA4() != (int) 0)
            O4 = String.valueOf(bill.getO4());
        if (bill.getA5() != (int) 0)
            O5 = String.valueOf(bill.getO5());
        if (bill.getA6() != (int) 0)
            O6 = String.valueOf(bill.getO6());
        if (bill.getA7() != (int) 0)
            O7 = String.valueOf(bill.getO7());
        if (bill.getA8() != (int) 0)
            O8 = String.valueOf(bill.getO8());
        if (bill.getA9() != (int) 0)
            O9 = String.valueOf(bill.getO9());
        if (bill.getA10() != (int) 0)
            O10 = String.valueOf(bill.getO10());
        if (bill.getA11() != (int) 0)
            O11 = String.valueOf(bill.getO11());
        if (bill.getA12() != (int) 0)
            O12 = String.valueOf(bill.getO12());
        if (bill.getA13() != (int) 0)
            O13 = String.valueOf(bill.getO13());
        if (bill.getA14() != (int) 0)
            O14 = String.valueOf(bill.getO14());
        if (bill.getA15() != (int) 0)
            O15 = String.valueOf(bill.getO15());
        if (bill.getA16() != (int) 0)
            O16 = String.valueOf(bill.getO16());
        if (bill.getA17() != (int) 0)
            O17 = String.valueOf(bill.getO17());
        if (bill.getA18() != (int) 0)
            O18 = String.valueOf(bill.getO18());
        if (bill.getA19() != (int) 0)
            O19 = String.valueOf(bill.getO19());
        if (bill.getA20() != (int) 0)
            O20 = String.valueOf(bill.getO20());
        if (bill.getA21() != (int) 0)
            O21 = String.valueOf(bill.getO21());
        if (bill.getA22() != (int) 0)
            O22 = String.valueOf(bill.getO22());
        if (bill.getA23() != (int) 0)
            O23 = String.valueOf(bill.getO23());
        if (bill.getA24() != (int) 0)
            O24 = String.valueOf(bill.getO24());
        if (bill.getA25() != (int) 0)
            O25 = String.valueOf(bill.getO25());
        if (bill.getA26() != (int) 0)
            O26 = String.valueOf(bill.getO26());
        if (bill.getA27() != (int) 0)
            O7 = String.valueOf(bill.getO27());
        if (bill.getA28() != (int) 0)
            O28 = String.valueOf(bill.getO28());
        if (bill.getA29() != (int) 0)
            O29 = String.valueOf(bill.getO29());
        if (bill.getA30() != (int) 0)
            O30 = String.valueOf(bill.getO30());
        if (bill.getA31() != (int) 0)
            O31 = String.valueOf(bill.getO31());
        if (bill.getA32() != (int) 0)
            O32 = String.valueOf(bill.getO32());
        if (bill.getA33() != (int) 0)
            O33 = String.valueOf(bill.getO33());
        if (bill.getA34() != (int) 0)
            O34 = String.valueOf(bill.getO34());
        if (bill.getA35() != (int) 0)
            O35 = String.valueOf(bill.getO35());
        if (bill.getA36() != (int) 0)
            O36 = String.valueOf(bill.getO36());
        if (bill.getA37() != (int) 0)
            O37 = String.valueOf(bill.getO37());
        if (bill.getA38() != (int) 0)
            O38 = String.valueOf(bill.getO38());
        if (bill.getA39() != (int) 0)
            O39 = String.valueOf(bill.getO39());
        if (bill.getA40() != (int) 0)
            O40 = String.valueOf(bill.getO40());
        if (bill.getA41() != (int) 0)
            O41 = String.valueOf(bill.getO41());
        if (bill.getA42() != (int) 0)
            O42 = String.valueOf(bill.getO42());
        if (bill.getA43() != (int) 0)
            O43 = String.valueOf(bill.getO43());
        if (bill.getA44() != (int) 0)
            O44 = String.valueOf(bill.getO44());
        if (bill.getA45() != (int) 0)
            O45 = String.valueOf(bill.getO45());
        if (bill.getA46() != (int) 0)
            O46 = String.valueOf(bill.getO46());
        if (bill.getA47() != (int) 0)
            O47 = String.valueOf(bill.getO47());
        if (bill.getA48() != (int) 0)
            O48 = String.valueOf(bill.getO48());
        if (bill.getA49() != (int) 0)
            O49 = String.valueOf(bill.getO49());
        if (bill.getA50() != (int) 0)
            O50 = String.valueOf(bill.getO50());
        if (bill.getU1() != (int) 0)
            U1 = String.valueOf(bill.getU1());
        if (bill.getU2() != (int) 0)
            U2 = String.valueOf(bill.getU2());
        if (bill.getU3() != (int) 0)
            U3 = String.valueOf(bill.getU3());
        if (bill.getU4() != (int) 0)
            U4 = String.valueOf(bill.getU4());
        if (bill.getU5() != (int) 0)
            U5 = String.valueOf(bill.getU5());
        if (bill.getU6() != (int) 0)
            U6 = String.valueOf(bill.getU6());
        if (bill.getU7() != (int) 0)
            U7 = String.valueOf(bill.getU7());
        if (bill.getU8() != (int) 0)
            U8 = String.valueOf(bill.getU8());
        if (bill.getU9() != (int) 0)
            U9 = String.valueOf(bill.getU9());
        if (bill.getU10() != (int) 0)
            U10 = String.valueOf(bill.getU10());
        if (bill.getU11() != (int) 0)
            U11 = String.valueOf(bill.getU11());
        if (bill.getU12() != (int) 0)
            U12 = String.valueOf(bill.getU12());
        if (bill.getU13() != (int) 0)
            U13 = String.valueOf(bill.getU13());
        if (bill.getU14() != (int) 0)
            U14 = String.valueOf(bill.getU14());
        if (bill.getU15() != (int) 0)
            U15 = String.valueOf(bill.getU15());
        if (bill.getU16() != (int) 0)
            U16 = String.valueOf(bill.getU16());
        if (bill.getU17() != (int) 0)
            U17 = String.valueOf(bill.getU17());
        if (bill.getU18() != (int) 0)
            U18 = String.valueOf(bill.getU18());
        if (bill.getU19() != (int) 0)
            U19 = String.valueOf(bill.getU19());
        if (bill.getU20() != (int) 0)
            U20 = String.valueOf(bill.getU20());
        if (bill.getU21() != (int) 0)
            U21 = String.valueOf(bill.getU21());
        if (bill.getU22() != (int) 0)
            U22 = String.valueOf(bill.getU22());
        if (bill.getU23() != (int) 0)
            U23 = String.valueOf(bill.getU23());
        if (bill.getU24() != (int) 0)
            U24 = String.valueOf(bill.getU24());
        if (bill.getU25() != (int) 0)
            U25 = String.valueOf(bill.getU25());
        if (bill.getU26() != (int) 0)
            U26 = String.valueOf(bill.getU26());
        if (bill.getU27() != (int) 0)
            U7 = String.valueOf(bill.getU27());
        if (bill.getU28() != (int) 0)
            U28 = String.valueOf(bill.getU28());
        if (bill.getU29() != (int) 0)
            U29 = String.valueOf(bill.getU29());
        if (bill.getU30() != (int) 0)
            U30 = String.valueOf(bill.getU30());
        if (bill.getU31() != (int) 0)
            U31 = String.valueOf(bill.getU31());
        if (bill.getU32() != (int) 0)
            U32 = String.valueOf(bill.getU32());
        if (bill.getU33() != (int) 0)
            U33 = String.valueOf(bill.getU33());
        if (bill.getU34() != (int) 0)
            U34 = String.valueOf(bill.getU34());
        if (bill.getU35() != (int) 0)
            U35 = String.valueOf(bill.getU35());
        if (bill.getU36() != (int) 0)
            U36 = String.valueOf(bill.getU36());
        if (bill.getU37() != (int) 0)
            U37 = String.valueOf(bill.getU37());
        if (bill.getU38() != (int) 0)
            U38 = String.valueOf(bill.getU38());
        if (bill.getU9() != (int) 0)
            U39 = String.valueOf(bill.getU39());
        if (bill.getU40() != (int) 0)
            U40 = String.valueOf(bill.getU40());
        if (bill.getU41() != (int) 0)
            U41 = String.valueOf(bill.getU41());
        if (bill.getU42() != (int) 0)
            U42 = String.valueOf(bill.getU42());
        if (bill.getU43() != (int) 0)
            U43 = String.valueOf(bill.getU43());
        if (bill.getU44() != (int) 0)
            U44 = String.valueOf(bill.getU44());
        if (bill.getU45() != (int) 0)
            U45 = String.valueOf(bill.getU45());
        if (bill.getU46() != (int) 0)
            U46 = String.valueOf(bill.getU46());
        if (bill.getU47() != (int) 0)
            U47 = String.valueOf(bill.getU47());
        if (bill.getU48() != (int) 0)
            U48 = String.valueOf(bill.getU48());
        if (bill.getU49() != (int) 0)
            U49 = String.valueOf(bill.getU49());
        if (bill.getU50() != (int) 0)
            U50 = String.valueOf(bill.getU50());
        if (bill.getT1() != (int) 0)
            T1 = String.valueOf(bill.getT1());
        if (bill.getT2() != (int) 0)
            T2 = String.valueOf(bill.getT2());
        if (bill.getT3() != (int) 0)
            T3 = String.valueOf(bill.getT3());
        if (bill.getT4() != (int) 0)
            T4 = String.valueOf(bill.getT4());
        if (bill.getT5() != (int) 0)
            T5 = String.valueOf(bill.getT5());
        if (bill.getT6() != (int) 0)
            T6 = String.valueOf(bill.getT6());
        if (bill.getT7() != (int) 0)
            T7 = String.valueOf(bill.getT7());
        if (bill.getT8() != (int) 0)
            T8 = String.valueOf(bill.getT8());
        if (bill.getT9() != (int) 0)
            T9 = String.valueOf(bill.getT9());
        if (bill.getT10() != (int) 0)
            T10 = String.valueOf(bill.getT10());
        if (bill.getT11() != (int) 0)
            T11 = String.valueOf(bill.getT11());
        if (bill.getT12() != (int) 0)
            T12 = String.valueOf(bill.getT12());
        if (bill.getT13() != (int) 0)
            T13 = String.valueOf(bill.getT13());
        if (bill.getT14() != (int) 0)
            T14 = String.valueOf(bill.getT14());
        if (bill.getT15() != (int) 0)
            T15 = String.valueOf(bill.getT15());
        if (bill.getT16() != (int) 0)
            T16 = String.valueOf(bill.getT16());
        if (bill.getT17() != (int) 0)
            T17 = String.valueOf(bill.getT17());
        if (bill.getT18() != (int) 0)
            T18 = String.valueOf(bill.getT18());
        if (bill.getT19() != (int) 0)
            T19 = String.valueOf(bill.getT19());
        if (bill.getT20() != (int) 0)
            T20 = String.valueOf(bill.getT20());
        if (bill.getT21() != (int) 0)
            T21 = String.valueOf(bill.getT21());
        if (bill.getT22() != (int) 0)
            T22 = String.valueOf(bill.getT22());
        if (bill.getT23() != (int) 0)
            T23 = String.valueOf(bill.getT23());
        if (bill.getT24() != (int) 0)
            T24 = String.valueOf(bill.getT24());
        if (bill.getT25() != (int) 0)
            T25 = String.valueOf(bill.getT25());
        if (bill.getT26() != (int) 0)
            T26 = String.valueOf(bill.getT26());
        if (bill.getT27() != (int) 0)
            T7 = String.valueOf(bill.getT27());
        if (bill.getT28() != (int) 0)
            T28 = String.valueOf(bill.getT28());
        if (bill.getT29() != (int) 0)
            T29 = String.valueOf(bill.getT29());
        if (bill.getT30() != (int) 0)
            T30 = String.valueOf(bill.getT30());
        if (bill.getT31() != (int) 0)
            T31 = String.valueOf(bill.getT31());
        if (bill.getT32() != (int) 0)
            T32 = String.valueOf(bill.getT32());
        if (bill.getT33() != (int) 0)
            T33 = String.valueOf(bill.getT33());
        if (bill.getT34() != (int) 0)
            T34 = String.valueOf(bill.getT34());
        if (bill.getT35() != (int) 0)
            T35 = String.valueOf(bill.getT35());
        if (bill.getT36() != (int) 0)
            T36 = String.valueOf(bill.getT36());
        if (bill.getT37() != (int) 0)
            T37 = String.valueOf(bill.getT37());
        if (bill.getT38() != (int) 0)
            T38 = String.valueOf(bill.getT38());
        if (bill.getT9() != (int) 0)
            T39 = String.valueOf(bill.getT39());
        if (bill.getT40() != (int) 0)
            T40 = String.valueOf(bill.getT40());
        if (bill.getT41() != (int) 0)
            T41 = String.valueOf(bill.getT41());
        if (bill.getT42() != (int) 0)
            T42 = String.valueOf(bill.getT42());
        if (bill.getT43() != (int) 0)
            T43 = String.valueOf(bill.getT43());
        if (bill.getT44() != (int) 0)
            T44 = String.valueOf(bill.getT44());
        if (bill.getT45() != (int) 0)
            T45 = String.valueOf(bill.getT45());
        if (bill.getT46() != (int) 0)
            T46 = String.valueOf(bill.getT46());
        if (bill.getT47() != (int) 0)
            T47 = String.valueOf(bill.getT47());
        if (bill.getT48() != (int) 0)
            T48 = String.valueOf(bill.getT48());
        if (bill.getT49() != (int) 0)
            T49 = String.valueOf(bill.getT49());
        if (bill.getT50() != (int) 0)
            T50 = String.valueOf(bill.getT50());
        //set body of bill
        sub1.setText(bill.getP1());
        sub2.setText(bill.getP2());
        sub3.setText(bill.getP3());
        sub4.setText(bill.getP4());
        sub5.setText(bill.getP5());
        sub6.setText(bill.getP6());
        sub7.setText(bill.getP7());
        sub8.setText(bill.getP8());
        sub9.setText(bill.getP9());
        sub10.setText(bill.getP10());
        sub11.setText(bill.getP11());
        sub12.setText(bill.getP12());
        sub13.setText(bill.getP13());
        sub14.setText(bill.getP14());
        sub15.setText(bill.getP15());
        sub16.setText(bill.getP16());
        sub17.setText(bill.getP17());
        sub18.setText(bill.getP18());
        sub19.setText(bill.getP19());
        sub20.setText(bill.getP20());
        sub21.setText(bill.getP21());
        sub22.setText(bill.getP22());
        sub23.setText(bill.getP23());
        sub24.setText(bill.getP24());
        sub25.setText(bill.getP25());
        sub26.setText(bill.getP26());
        sub27.setText(bill.getP27());
        sub28.setText(bill.getP28());
        sub29.setText(bill.getP29());
        sub30.setText(bill.getP30());
        sub31.setText(bill.getP31());
        sub32.setText(bill.getP32());
        sub33.setText(bill.getP33());
        sub34.setText(bill.getP34());
        sub35.setText(bill.getP35());
        sub36.setText(bill.getP36());
        sub37.setText(bill.getP37());
        sub38.setText(bill.getP38());
        sub39.setText(bill.getP39());
        sub40.setText(bill.getP40());
        sub41.setText(bill.getP41());
        sub42.setText(bill.getP42());
        sub43.setText(bill.getP43());
        sub44.setText(bill.getP44());
        sub45.setText(bill.getP45());
        sub46.setText(bill.getP46());
        sub47.setText(bill.getP47());
        sub48.setText(bill.getP48());
        sub49.setText(bill.getP49());
        sub50.setText(bill.getP50());

        amo1.setText(A1);
        amo2.setText(A2);
        amo3.setText(A3);
        amo4.setText(A4);
        amo5.setText(A5);
        amo6.setText(A6);
        amo7.setText(A7);
        amo8.setText(A8);
        amo9.setText(A9);
        amo10.setText(A10);
        amo11.setText(A11);
        amo12.setText(A12);
        amo13.setText(A13);
        amo14.setText(A14);
        amo15.setText(A15);
        amo16.setText(A16);
        amo17.setText(A17);
        amo18.setText(A18);
        amo19.setText(A19);
        amo20.setText(A20);
        amo21.setText(A21);
        amo22.setText(A22);
        amo23.setText(A23);
        amo24.setText(A24);
        amo25.setText(A25);
        amo26.setText(A26);
        amo27.setText(A27);
        amo28.setText(A28);
        amo29.setText(A29);
        amo30.setText(A30);
        amo31.setText(A31);
        amo32.setText(A32);
        amo33.setText(A33);
        amo34.setText(A34);
        amo35.setText(A35);
        amo36.setText(A36);
        amo37.setText(A37);
        amo38.setText(A38);
        amo39.setText(A39);
        amo40.setText(A40);
        amo41.setText(A41);
        amo42.setText(A42);
        amo43.setText(A43);
        amo44.setText(A44);
        amo45.setText(A45);
        amo46.setText(A46);
        amo47.setText(A47);
        amo48.setText(A48);
        amo49.setText(A49);
        amo50.setText(A50);

        off1.setText(O1);
        off2.setText(O2);
        off3.setText(O3);
        off4.setText(O4);
        off5.setText(O5);
        off6.setText(O6);
        off7.setText(O7);
        off8.setText(O8);
        off9.setText(O9);
        off10.setText(O10);
        off11.setText(O11);
        off12.setText(O12);
        off13.setText(O13);
        off14.setText(O14);
        off15.setText(O15);
        off16.setText(O16);
        off17.setText(O17);
        off18.setText(O18);
        off19.setText(O19);
        off20.setText(O20);
        off21.setText(O21);
        off22.setText(O22);
        off23.setText(O23);
        off24.setText(O24);
        off25.setText(O25);
        off26.setText(O26);
        off27.setText(O27);
        off28.setText(O28);
        off29.setText(O29);
        off30.setText(O30);
        off31.setText(O31);
        off32.setText(O32);
        off33.setText(O33);
        off34.setText(O34);
        off35.setText(O35);
        off36.setText(O36);
        off37.setText(O37);
        off38.setText(O38);
        off39.setText(O39);
        off40.setText(O40);
        off41.setText(O41);
        off42.setText(O42);
        off43.setText(O43);
        off44.setText(O44);
        off45.setText(O45);
        off46.setText(O46);
        off47.setText(O47);
        off48.setText(O48);
        off49.setText(O49);
        off50.setText(O50);

        uni1.setText(U1);
        uni2.setText(U2);
        uni3.setText(U3);
        uni4.setText(U4);
        uni5.setText(U5);
        uni6.setText(U6);
        uni7.setText(U7);
        uni8.setText(U8);
        uni9.setText(U9);
        uni10.setText(U10);
        uni11.setText(U11);
        uni12.setText(U12);
        uni13.setText(U13);
        uni14.setText(U14);
        uni15.setText(U15);
        uni16.setText(U16);
        uni17.setText(U17);
        uni18.setText(U18);
        uni19.setText(U19);
        uni20.setText(U20);
        uni21.setText(U21);
        uni22.setText(U22);
        uni23.setText(U23);
        uni24.setText(U24);
        uni25.setText(U25);
        uni26.setText(U26);
        uni27.setText(U27);
        uni28.setText(U28);
        uni29.setText(U29);
        uni30.setText(U30);
        uni31.setText(U31);
        uni32.setText(U32);
        uni33.setText(U33);
        uni34.setText(U34);
        uni35.setText(U35);
        uni36.setText(U36);
        uni37.setText(U37);
        uni38.setText(U38);
        uni39.setText(U39);
        uni40.setText(U40);
        uni41.setText(U41);
        uni42.setText(U42);
        uni43.setText(U43);
        uni44.setText(U44);
        uni45.setText(U45);
        uni46.setText(U46);
        uni47.setText(U47);
        uni48.setText(U48);
        uni49.setText(U49);
        uni50.setText(U50);

        tot1.setText(T1);
        tot2.setText(T2);
        tot3.setText(T3);
        tot4.setText(T4);
        tot5.setText(T5);
        tot6.setText(T6);
        tot7.setText(T7);
        tot8.setText(T8);
        tot9.setText(T9);
        tot10.setText(T10);
        tot11.setText(T11);
        tot12.setText(T12);
        tot13.setText(T13);
        tot14.setText(T14);
        tot15.setText(T15);
        tot16.setText(T16);
        tot17.setText(T17);
        tot18.setText(T18);
        tot19.setText(T19);
        tot20.setText(T20);
        tot21.setText(T21);
        tot22.setText(T22);
        tot23.setText(T23);
        tot24.setText(T24);
        tot25.setText(T25);
        tot26.setText(T26);
        tot27.setText(T27);
        tot28.setText(T28);
        tot29.setText(T29);
        tot30.setText(T30);
        tot31.setText(T31);
        tot32.setText(T32);
        tot33.setText(T33);
        tot34.setText(T34);
        tot35.setText(T35);
        tot36.setText(T36);
        tot37.setText(T37);
        tot38.setText(T38);
        tot39.setText(T39);
        tot40.setText(T40);
        tot41.setText(T41);
        tot42.setText(T42);
        tot43.setText(T43);
        tot44.setText(T44);
        tot45.setText(T45);
        tot46.setText(T46);
        tot47.setText(T47);
        tot48.setText(T48);
        tot49.setText(T49);
        tot50.setText(T50);

        sta1.setText(bill.getS1());
        sta2.setText(bill.getS2());
        sta3.setText(bill.getS3());
        sta4.setText(bill.getS4());
        sta5.setText(bill.getS5());
        sta6.setText(bill.getS6());
        sta7.setText(bill.getS7());
        sta8.setText(bill.getS8());
        sta9.setText(bill.getS9());
        sta10.setText(bill.getS10());
        sta11.setText(bill.getS11());
        sta12.setText(bill.getS12());
        sta13.setText(bill.getS13());
        sta14.setText(bill.getS14());
        sta15.setText(bill.getS15());
        sta16.setText(bill.getS16());
        sta17.setText(bill.getS17());
        sta18.setText(bill.getS18());
        sta19.setText(bill.getS19());
        sta20.setText(bill.getS20());
        sta21.setText(bill.getS21());
        sta22.setText(bill.getS22());
        sta23.setText(bill.getS23());
        sta24.setText(bill.getS24());
        sta25.setText(bill.getS25());
        sta26.setText(bill.getS26());
        sta27.setText(bill.getS27());
        sta28.setText(bill.getS28());
        sta29.setText(bill.getS29());
        sta30.setText(bill.getS30());
        sta31.setText(bill.getS31());
        sta32.setText(bill.getS32());
        sta33.setText(bill.getS33());
        sta34.setText(bill.getS34());
        sta35.setText(bill.getS35());
        sta36.setText(bill.getS36());
        sta37.setText(bill.getS37());
        sta38.setText(bill.getS38());
        sta39.setText(bill.getS39());
        sta40.setText(bill.getS40());
        sta41.setText(bill.getS41());
        sta42.setText(bill.getS42());
        sta43.setText(bill.getS43());
        sta44.setText(bill.getS44());
        sta45.setText(bill.getS45());
        sta46.setText(bill.getS46());
        sta47.setText(bill.getS47());
        sta48.setText(bill.getS48());
        sta49.setText(bill.getS49());
        sta50.setText(bill.getS50());
        //set discount,addition,total and total
        edit_discount.setText(String.valueOf(bill.getDiscount()));
        edit_addition.setText(String.valueOf(bill.getAddition()));
        txt_total.setText(String.valueOf(bill.getTotal()));
        txt_final.setText(String.valueOf(bill.getFinal()));
        setTotal();
    }

    private void setBillColor() {
        if (basic == 1) {
            billTable.setBackgroundColor(getResources().getColor(R.color.blue));
            save.setTextColor(getResources().getColor(R.color.blue));
            txt_total.setBackgroundColor(getResources().getColor(R.color.blue));
            txt_final.setBackgroundColor(getResources().getColor(R.color.blue));
            rel_spin_point.setVisibility(View.GONE);
            edit_from.setVisibility(View.VISIBLE);
            rel_spin_point_.setVisibility(View.VISIBLE);
            edit_to.setVisibility(View.GONE);
            edit_from_t.setVisibility(View.GONE);
            edit_to_t.setVisibility(View.GONE);
            category.setVisibility(VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110, ViewGroup.LayoutParams.WRAP_CONTENT, 2.5f);
            voiceImage.setLayoutParams(params);
            productImage.setLayoutParams(params);

        } else if (basic == 2) {
            billTable.setBackgroundColor(getResources().getColor(R.color.purple));
            save.setTextColor(getResources().getColor(R.color.purple));
            txt_total.setBackgroundColor(getResources().getColor(R.color.purple));
            txt_final.setBackgroundColor(getResources().getColor(R.color.purple));
            rel_spin_point.setVisibility(View.VISIBLE);
            edit_from.setVisibility(View.GONE);
            rel_spin_point_.setVisibility(View.GONE);
            edit_to.setVisibility(View.VISIBLE);
            edit_from_t.setVisibility(View.GONE);
            edit_to_t.setVisibility(View.GONE);
            category.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 5.0f);
            voiceImage.setLayoutParams(params);
            productImage.setLayoutParams(params);
        } else {
            billTable.setBackgroundColor(getResources().getColor(R.color.green));
            save.setTextColor(getResources().getColor(R.color.green));
            txt_total.setBackgroundColor(getResources().getColor(R.color.green));
            txt_final.setBackgroundColor(getResources().getColor(R.color.green));
            rel_spin_point.setVisibility(View.GONE);
            edit_from.setVisibility(View.GONE);
            rel_spin_point_.setVisibility(View.GONE);
            edit_to.setVisibility(View.GONE);
            edit_from.setVisibility(View.GONE);
            edit_from_t.setVisibility(View.VISIBLE);
            edit_to_t.setVisibility(View.VISIBLE);
            category.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 5.0f);
            voiceImage.setLayoutParams(params);
            productImage.setLayoutParams(params);
        }
    }

    private boolean checkIsNull() {
        boolean b;
        if (basic != 1)
            b = dialogEditSubject.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditTotal.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*");
        else
            b = dialogEditSubject.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditTotal.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditId.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*");
        if (!b) {
            if (!dialogEditSubject.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                if (pref.getString("Language", "arabic").matches("arabic"))
                    dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                else
                    dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
            if (!dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                if (pref.getString("Language", "arabic").matches("arabic"))
                    dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                else
                    dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
            if (!dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                if (pref.getString("Language", "arabic").matches("arabic"))
                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                else
                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
            if (!dialogEditTotal.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                if (pref.getString("Language", "arabic").matches("arabic"))
                    dialogEditTotal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                else
                    dialogEditTotal.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
            if (basic == 1)
                if (!dialogEditId.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                    if (pref.getString("Language", "arabic").matches("arabic"))
                        dialogEditId.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                    else
                        dialogEditId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
        }
        return b;
    }

    private boolean checkUnitPriceErrors(final boolean add, final int rowNumber) {
        boolean b = false;
        if (basic != 1) {
            if (dialogEditSubject.getText().toString().contains("-") && dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                for (int k = 0; k < dbs.allSubjects().size(); k++) {
                    if (dbs.allSubjects().get(k).getName().matches(dialogEditSubject.getText().toString().substring(dialogEditSubject.getText().toString().indexOf("-")).replace(" ", "").replace("-", ""))) {
                        if (dbs.allSubjects().get(k).getLock() != 0) {
                            if (Double.valueOf(dialogEditUnit.getText().toString()) < dbs.allSubjects().get(k).getLock()) {
                                if (pref.getString("Language", "arabic").matches("arabic"))
                                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                else
                                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                                Toast.makeText(BillActivity.this, res.getString(R.string.error_lower_lock), Toast.LENGTH_LONG).show();
                                b = true;
                            }
                        } else {
                            if (Double.valueOf(dialogEditUnit.getText().toString()) < dbs.allSubjects().get(k).getCost()) {
                                if (pref.getString("Language", "arabic").matches("arabic"))
                                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                else
                                    dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                                Toast.makeText(BillActivity.this, res.getString(R.string.error_lower_net), Toast.LENGTH_LONG).show();
                                b = true;
                            }
                        }
                    }
                }
            }
        } else {
            for (int k = 0; k < dbs.allSubjects().size(); k++) {
                if (dbs.allSubjects().get(k).getName().matches(dialogEditSubject.getText().toString()) && dbs.allSubjects().get(k).getCost() != Double.valueOf(dialogEditUnit.getText().toString()) && !isUnitRegistered_) {
                    isUnitRegistered = false;
                    final int n = k;
                    final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                    alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                    alertDialog.setMessage(res.getString(R.string.alertCalcNewValue));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (add)
                                        productUnits.add((Double.valueOf(dialogEditUnit.getText().toString()) + dbs.allSubjects().get(n).getCost()) / 2);
                                    else
                                        productUnits.set(rowNumber - 1, (Double.valueOf(dialogEditUnit.getText().toString()) + dbs.allSubjects().get(n).getCost()) / 2);
                                    isUnitRegistered = true;
                                    isUnitRegistered_ = true;
                                    isNes = true;
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, res.getString(R.string.alertSaveAsNewPrice),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (add)
                                        productUnits.add(Double.valueOf(dialogEditUnit.getText().toString()));
                                    else
                                        productUnits.set(rowNumber - 1, Double.valueOf(dialogEditUnit.getText().toString()));
                                    isUnitRegistered = true;
                                    isUnitRegistered_ = true;
                                    isNes = true;
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                            isUnitRegistered = false;
                            isUnitRegistered_ = false;
                        }
                    });
                    alertDialog.show();
                    b = true;
                }
            }
        }
        return b;
    }

    private boolean checkAmountErrors() {
        boolean b = false;
        if (basic != 1) {
            if (dialogEditSubject.getText().toString().contains("-") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                for (int k = 0; k < dbs.allSubjects().size(); k++) {
                    if (dbs.allSubjects().get(k).getName().matches(dialogEditSubject.getText().toString().substring(dialogEditSubject.getText().toString().indexOf("-")).replace(" ", "").replace("-", ""))) {
                        if (dbs.allSubjects().get(k).getAmount() < Double.valueOf(dialogEditAmount.getText().toString())) {
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_lower_amount), Toast.LENGTH_LONG).show();
                            b = true;
                        } else if (dbs.allSubjects().get(k).getAmountLock() != 0 && dbs.allSubjects().get(k).getAmountLock() > Double.valueOf(dialogEditAmount.getText().toString())) {
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_lower_amount_lock), Toast.LENGTH_LONG).show();
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }

    private boolean checkIsThereProduct(final boolean add, final int rowNumber, final Dialog dialog) {
        boolean b = false;
        if (basic == 1)
            b = true;
        else {
            for (int i = 0; i < dbs.allSubjects().size(); i++) {
                if (dialogEditSubject.getText().toString().contains("-")) {
                    if (dbs.allSubjects().get(i).getName().matches(dialogEditSubject.getText().toString().substring(dialogEditSubject.getText().toString().indexOf("-")).replace("-", "")))
                        b = true;
                } else {
                    if (dbs.allSubjects().get(i).getName().matches(dialogEditSubject.getText().toString()))
                        b = true;
                }
            }
        }
        if (!b) {
            final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
            alertDialog.setTitle(res.getString(R.string.alertTitleNote));
            alertDialog.setMessage(res.getString(R.string.alertNewProductMessage));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            addNewProduct();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                    if (add)
                        productNames.add(dialogEditSubject.getText().toString());
                    else
                        productNames.set(rowNumber - 1, dialogEditSubject.getText().toString());
                    curSubject = dialogEditSubject.getText().toString();
                    curAmount_ = Double.valueOf(dialogEditAmount.getText().toString());
                    if (dialogEditOffer.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                        curOffer_ = Double.valueOf(dialogEditOffer.getText().toString());
                    else
                        curOffer_ = 0;
                    curUnit_ = Double.valueOf(dialogEditUnit.getText().toString());
                    curTotal_ = Double.valueOf(dialogEditTotal.getText().toString());
                    curStatement = dialogEditStatement.getText().toString();
                    curAmount = String.valueOf(curAmount_);
                    curOffer = String.valueOf(curOffer_);
                    curUnit = String.valueOf(curUnit_);
                    curTotal = String.valueOf(curTotal_);
                    if (add) {
                        addRowNext();
                    } else {
                        editCurrentRow(rowNumber);
                    }
                    setTotal();
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
        return b;
    }

    private boolean editAndCheckIsIdAndNameExept(final boolean add, final int rowNumber) {
        boolean b = true;
        if (basic != 1) {
            for (int i = 0; i < dbs.allSubjects().size(); i++) {
                if (!dialogEditSubject.getText().toString().contains("-") && (dbs.allSubjects().get(i).getName().matches(dialogEditSubject.getText().toString()) || String.valueOf(dbs.allSubjects().get(i).getId()).matches(dialogEditSubject.getText().toString()))) {
                    dialogEditSubject.setText(String.valueOf(dbs.allSubjects().get(i).getId()) + "-" + dbs.allSubjects().get(i).getName());
                }
            }
            curSubject = dialogEditSubject.getText().toString();
        } else {
            curSubject = dialogEditId.getText().toString() + "-" + dialogEditSubject.getText().toString();
        }
        for (int u = 0; u < dbs.allSubjects().size(); u++) {
            if (basic != 1) {
                if (dbs.allSubjects().get(u).getName().matches(dialogEditSubject.getText().toString().substring(dialogEditSubject.getText().toString().indexOf("-") + 1)) && Integer.valueOf(dialogEditSubject.getText().toString().substring(0, dialogEditSubject.getText().toString().indexOf("-"))) != dbs.allSubjects().get(u).getId()) {
                    if (pref.getString("Language", "arabic").matches("arabic"))
                        dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                    else
                        dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_code_exept), Toast.LENGTH_LONG).show();
                    b = false;
                    break;
                }
            } else {
                if (dbs.allSubjects().get(u).getName().matches(dialogEditSubject.getText().toString()) && Integer.valueOf(dialogEditId.getText().toString()) != dbs.allSubjects().get(u).getId()) {
                    if (pref.getString("Language", "arabic").matches("arabic"))
                        dialogEditId.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                    else
                        dialogEditId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_code_exept), Toast.LENGTH_LONG).show();
                    b = false;
                    break;
                }
            }
        }
        return b;
    }

    private boolean checkIsThereRow() {
        boolean b = false;
        for (int k = 0; k < productNames.size(); k++) {
            if (productNames.get(k).matches(dialogEditSubject.getText().toString())) {
                if (pref.getString("Language", "arabic").matches("arabic"))
                    dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                else
                    dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                b = true;
            }
            if (basic == 1) {
                if (productNames.get(k).matches(dialogEditId.getText().toString() + "-" + dialogEditSubject.getText().toString())) {
                    if (pref.getString("Language", "arabic").matches("arabic"))
                        dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                    else
                        dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                    b = true;
                }
            }
        }
        return b;
    }

    private void createDialog(final int rowNumber, boolean add) {
        final boolean add_ = add;
        final int rowNumber_ = rowNumber;
        //Make new Dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_edit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNumber = (TextView) dialog.findViewById(R.id.dialogNum);
        dialogEditSubject = (CustomAutoCompleteTextView) dialog.findViewById(R.id.edit_add_subject);
        dialogEditAmount = (EditText) dialog.findViewById(R.id.edit_add_amount);
        dialogEditId = (EditText) dialog.findViewById(R.id.edit_add_id);
        dialogEditOffer = (EditText) dialog.findViewById(R.id.edit_add_offer);
        dialogEditUnit = (EditText) dialog.findViewById(R.id.edit_add_unit);
        dialogEditTotal = (EditText) dialog.findViewById(R.id.edit_add_total);
        dialogEditStatement = (EditText) dialog.findViewById(R.id.edit_add_state);
        dialogBtnAddEdit = (Button) dialog.findViewById(R.id.dialog_btn_add_edit);
        dialogBtnCancel = (Button) dialog.findViewById(R.id.dialog_btn_cancel);
        dialogEditSubject_ = (TextInputLayout) dialog.findViewById(R.id.til_add_subject);
        dialogEditAmount_ = (TextInputLayout) dialog.findViewById(R.id.til_add_amount);
        dialogEditUnit_ = (TextInputLayout) dialog.findViewById(R.id.til_add_unit);
        dialogEditTotal_ = (TextInputLayout) dialog.findViewById(R.id.til_add_total);
        dialogEditId_ = (TextInputLayout) dialog.findViewById(R.id.til_add_id);
        imgDeleteRow = (ImageView) dialog.findViewById(R.id.imgDeleteRow);
        listAllSubjects = new ArrayList<>();
        customSubjectArrayAdapter = new CustomSubjectArrayAdapter(this, R.layout.list_subjects_item, listAllSubjects, basic);
        dialogEditSubject.setAdapter(customSubjectArrayAdapter);
        dialogEditSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout listItem = (LinearLayout) view;
                TextView name = (TextView) listItem.findViewById(R.id.subjectName);
                TextView amount = (TextView) listItem.findViewById(R.id.subjectAmount);
                TextView cost = (TextView) listItem.findViewById(R.id.subjectCost);
                TextView last = (TextView) listItem.findViewById(R.id.subjectLast);
                SubjectListChildItem SLCI = new SubjectListChildItem();
                SLCI = dbs.getSubjectById(Integer.valueOf(name.getText().toString().substring(0, 5).replace(" ", "")));
                if (basic == 1) {
                    dialogEditSubject.setText(SLCI.getName());
                    dialogEditId.setText(String.valueOf(SLCI.getId()));
                    dialogEditUnit.setText(String.valueOf(SLCI.getCost()));
                } else {
                    dialogEditSubject.setText(String.valueOf(SLCI.getId()) + "-" + SLCI.getName());
                    if (SLCI.getLast() != 0)
                        dialogEditUnit.setText(String.valueOf(SLCI.getLast()));
                    else if (SLCI.getLock() != 0)
                        dialogEditUnit.setText(String.valueOf(SLCI.getLock()));
                    if (SLCI.getAmountLast() != 0)
                        dialogEditAmount.setText(String.valueOf(SLCI.getAmountLast()));
                    else if (SLCI.getAmountLock() != 0)
                        dialogEditAmount.setText(String.valueOf(SLCI.getAmountLock()));
                }
            }
        });
        dialogNumber.setText(String.valueOf(rowNumber).toString());
        if (add) {
            dialog.setTitle(res.getString(R.string.dialogAddTitle));
            dialogBtnAddEdit.setText(res.getString(R.string.dialogPositiveAdd));
            imgDeleteRow.setVisibility(View.GONE);
        } else {
            dialog.setTitle(res.getString(R.string.dialogEditTitle));
            dialogBtnAddEdit.setText(res.getString(R.string.dialogPositiveEdit));
            fillEditTextByCurrentRow(rowNumber);
            imgDeleteRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                    alertDialog.setTitle(res.getString(R.string.alertTitle));
                    alertDialog.setMessage(res.getString(R.string.alertDeleteRowMessage));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteRow(rowNumber_);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                    dialog.cancel();
                }
            });
        }
        isUnitRegistered = true;
        if (basic == 1) {
            dialogNumber.setBackground(res.getDrawable(R.drawable.cir_blue));
            dialogBtnAddEdit.setTextColor(res.getColor(R.color.blue));
            dialogBtnCancel.setTextColor(res.getColor(R.color.blue));
            dialogEditId_.setVisibility(VISIBLE);
            int id = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
            dialogEditId.setText(String.valueOf(id));
            for (int k = 0; k < dbs.allSubjects().size(); k++) {
                if (dbs.allSubjects().get(k).getName().matches(dialogEditSubject.getText().toString()) && dbs.allSubjects().get(k).getCost() != Double.valueOf(dialogEditUnit.getText().toString()) && !isUnitRegistered_) {
                    isUnitRegistered = false;
                }
            }
        } else if (basic == 2) {
            dialogNumber.setBackground(res.getDrawable(R.drawable.cir_purple));
            dialogBtnAddEdit.setTextColor(res.getColor(R.color.purple));
            dialogBtnCancel.setTextColor(res.getColor(R.color.purple));

        } else {
            dialogNumber.setBackground(res.getDrawable(R.drawable.cir_green));
            dialogBtnAddEdit.setTextColor(res.getColor(R.color.green));
            dialogBtnCancel.setTextColor(res.getColor(R.color.green));
        }
        dialogEditSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    dialogEditSubject.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    customSubjectArrayAdapter.notifyDataSetChanged();
                    listAllSubjects = new ArrayList<>();
                    for (int k = 0; k < dbs.allSubjects().size(); k++) {
                        if (dbs.allSubjects().get(k).getName().toLowerCase(Locale.getDefault()).contains(charSequence.toString()))
                            listAllSubjects.add(dbs.allSubjects().get(k));
                    }
                    for (int k = 0; k < dbs.allSubjects().size(); k++) {
                        if (String.valueOf(dbs.allSubjects().get(k).getId()).contains(charSequence.toString()))
                            listAllSubjects.add(dbs.allSubjects().get(k));
                    }
                    customSubjectArrayAdapter = new CustomSubjectArrayAdapter(BillActivity.this, R.layout.list_subjects_item, listAllSubjects, basic);
                    dialogEditSubject.setAdapter(customSubjectArrayAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialogEditUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dialogEditUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                if (dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    double a = Double.valueOf(dialogEditUnit.getText().toString());
                    double b = Double.valueOf(dialogEditAmount.getText().toString());
                    isSetInitialTotalEditText = true;
                    dialogEditTotal.setText(String.valueOf(a * b));
                } else {
                    dialogEditTotal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialogEditAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dialogEditAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                if (dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    double a = Double.valueOf(dialogEditUnit.getText().toString());
                    double b = Double.valueOf(dialogEditAmount.getText().toString());
                    isSetInitialTotalEditText = true;
                    dialogEditTotal.setText(String.valueOf(a * b));
                } else {
                    dialogEditTotal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialogEditTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dialogEditTotal.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                if (isSetInitialTotalEditText) {
                    isSetInitialTotalEditText = false;
                } else {
                    if (dialogEditUnit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditAmount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && dialogEditTotal.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                        double a = Double.valueOf(dialogEditTotal.getText().toString());
                        double b = Double.valueOf(dialogEditAmount.getText().toString());
                        dialogEditUnit.setText(String.valueOf(a / b));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialogBtnAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIsNull() && (!add_ || !checkIsThereRow()) && checkIsThereProduct(add_, rowNumber_, dialog) && editAndCheckIsIdAndNameExept(add_, rowNumber_) && !checkAmountErrors() && !checkUnitPriceErrors(add_, rowNumber_) && isUnitRegistered) {
                    if (basic != 1) {
                        if (add_)
                            productNames.add(dialogEditSubject.getText().toString());
                        else
                            productNames.set(rowNumber_ - 1, dialogEditSubject.getText().toString());
                    } else {
                        if (add_) {
                            productNames.add(dialogEditId.getText().toString() + "-" + dialogEditSubject.getText().toString());
                            if (!isUnitRegistered_)
                                productUnits.add(Double.valueOf(dialogEditUnit.getText().toString()));
                        } else {
                            productNames.set(rowNumber_ - 1, dialogEditId.getText().toString() + "-" + dialogEditSubject.getText().toString());
                            if (!isUnitRegistered_)
                                productUnits.set(rowNumber_ - 1, Double.valueOf(dialogEditUnit.getText().toString()));
                        }
                    }
                    curAmount_ = Double.valueOf(dialogEditAmount.getText().toString());
                    if (dialogEditOffer.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                        curOffer_ = Double.valueOf(dialogEditOffer.getText().toString());
                    else
                        curOffer_ = 0;
                    curUnit_ = Double.valueOf(dialogEditUnit.getText().toString());
                    curTotal_ = Double.valueOf(dialogEditTotal.getText().toString());
                    curStatement = dialogEditStatement.getText().toString();
                    curAmount = String.valueOf(curAmount_);
                    curOffer = String.valueOf(curOffer_);
                    curUnit = String.valueOf(curUnit_);
                    curTotal = String.valueOf(curTotal_);
                    if (add_) {
                        addRowNext();
                    } else {
                        editCurrentRow(rowNumber_);
                    }
                    setTotal();
                    if (basic == 1) {
                        boolean isExist = false;
                        for (int d = 0; d < dbs.allSubjects().size(); d++) {
                            if (Integer.valueOf(dialogEditId.getText().toString()) == dbs.allSubjects().get(d).getId())
                                isExist = true;
                        }
                        if (!isExist)
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", Integer.valueOf(dialogEditId.getText().toString()) + 1).commit();
                    }
                    dialog.cancel();
                    isNes = false;
                }
            }
        });
        dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNes) {
                    setTotal();
                    dialog.cancel();
                } else
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_ness), Toast.LENGTH_LONG).show();

            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void addNewProduct() {
        final boolean isEdit = false;
        final Dialog d = new Dialog(BillActivity.this);
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
        txt_date_ = (TextView) d.findViewById(R.id.txt_date);
        txt_time = (TextView) d.findViewById(R.id.txt_time);
        spin_currency = (Spinner) d.findViewById(R.id.spin_new_currency_s);
        String[] currency = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(BillActivity.this, R.layout.spinner_item, currency);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_currency.setAdapter(spinnerCurrencyArrayAdapter);
        ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, dbs.allFolders());
        edit_new_folder.setAdapter(folderSuggestionAdapter);
        String allSuppliers[] = new String[dbsu.allSuppliers().size()];
        for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
            allSuppliers[k] = dbsu.allSuppliers().get(k).getSupplier();
        }
        ArrayAdapter<String> buyerSuggestionAdapter = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, allSuppliers);
        edit_new_buyer.setAdapter(buyerSuggestionAdapter);
        spin_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (isEdit)
                    btn_save_subject.setVisibility(View.VISIBLE);
                int index = arg0.getSelectedItemPosition();
                if (index == 0)
                    code = res.getString(R.string.sypCode);
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
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
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
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_product), Toast.LENGTH_LONG).show();
                            isNameExist = true;
                            break;
                        }
                    }
                    if (!isCodeExist && !isNameExist) {
                        if (isEdit) {
                        } else {
                            int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectColumn", 0);
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
                            dialogEditSubject.setText(edit_new_code.getText().toString() + "-" + edit_new_subject.getText().toString());
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectColumn", column + 1).commit();
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId", Integer.valueOf(edit_new_code.getText().toString()) + 1).commit();
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
            int id = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001);
            edit_new_code.setText(String.valueOf(id));
            txt_date.setText(getDate());
            txt_time.setText(getTime());
            edit_new_subject.setText(dialogEditSubject.getText().toString());
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
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_product), Toast.LENGTH_LONG).show();
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

    private void addProductToSQL(int Column, int Id, String Buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        SubjectListChildItem subject = new SubjectListChildItem(Column, Id, Buyer, Folder, Name, Amount, AmountLast, AmountLock, Cost, Last, Lock, Spec, Code, Time, Date);
        dbs.addSubject(subject);
    }

    private void setTotal() {
        tp = 0;
        if (tot1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot1.getText().toString());
        if (tot2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot2.getText().toString());
        if (tot3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot3.getText().toString());
        if (tot4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot4.getText().toString());
        if (tot5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot5.getText().toString());
        if (tot6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot6.getText().toString());
        if (tot7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot7.getText().toString());
        if (tot8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot8.getText().toString());
        if (tot9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot9.getText().toString());
        if (tot10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot10.getText().toString());
        if (tot11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot11.getText().toString());
        if (tot12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot12.getText().toString());
        if (tot13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot13.getText().toString());
        if (tot14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot14.getText().toString());
        if (tot15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot15.getText().toString());
        if (tot16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot16.getText().toString());
        if (tot17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot17.getText().toString());
        if (tot18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot18.getText().toString());
        if (tot19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot19.getText().toString());
        if (tot20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot20.getText().toString());
        if (tot21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot21.getText().toString());
        if (tot22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot22.getText().toString());
        if (tot23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot23.getText().toString());
        if (tot24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot24.getText().toString());
        if (tot25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot25.getText().toString());
        if (tot26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot26.getText().toString());
        if (tot27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot27.getText().toString());
        if (tot28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot28.getText().toString());
        if (tot29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot29.getText().toString());
        if (tot30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot30.getText().toString());
        if (tot31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot31.getText().toString());
        if (tot32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot32.getText().toString());
        if (tot33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot33.getText().toString());
        if (tot34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot34.getText().toString());
        if (tot35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot35.getText().toString());
        if (tot36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot36.getText().toString());
        if (tot37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot37.getText().toString());
        if (tot38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot38.getText().toString());
        if (tot39.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot39.getText().toString());
        if (tot40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot40.getText().toString());
        if (tot41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot41.getText().toString());
        if (tot42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot42.getText().toString());
        if (tot43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot43.getText().toString());
        if (tot44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot44.getText().toString());
        if (tot45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot45.getText().toString());
        if (tot46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot46.getText().toString());
        if (tot47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot47.getText().toString());
        if (tot48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot48.getText().toString());
        if (tot49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot49.getText().toString());
        if (tot50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            tp = tp + Double.valueOf(tot50.getText().toString());
        txt_total.setText(String.valueOf(tp) + " " + code);
        setFinal(tp, code);
    }

    private void setFinal(double tp, String code) {
        double dis = 0;
        double add = 0;
        if (edit_discount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            dis = Double.valueOf(edit_discount.getText().toString());
        if (edit_addition.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
            add = Double.valueOf(edit_discount.getText().toString());
        ep = add - dis;
        txt_final.setText(String.valueOf(tp + ep) + " " + code);
    }

    private void setDate() {
        //Date now = new Date();
        //Date alsoNow = Calendar.getInstance().getTime();
        //date = new SimpleDateFormat("yyyy/MM/dd").format(now);
        if (bun.getBoolean("isView", false)) {
            txt_date.setText(viewDate);
        } else {
            txt_date.setText(day + "/" + month + "/" + year);
        }
        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BillActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        txt_date.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void editCurrentRow(int curRow) {
        switch (curRow) {
            case 1:
                sub1.setText(curSubject);
                amo1.setText(curAmount);
                off1.setText(curOffer);
                uni1.setText(curUnit);
                tot1.setText(curTotal);
                sta1.setText(curStatement);
                break;
            case 2:
                sub2.setText(curSubject);
                amo2.setText(curAmount);
                off2.setText(curOffer);
                uni2.setText(curUnit);
                tot2.setText(curTotal);
                sta2.setText(curStatement);
                break;
            case 3:
                sub3.setText(curSubject);
                amo3.setText(curAmount);
                off3.setText(curOffer);
                uni3.setText(curUnit);
                tot3.setText(curTotal);
                sta3.setText(curStatement);
                break;
            case 4:
                sub4.setText(curSubject);
                amo4.setText(curAmount);
                off4.setText(curOffer);
                uni4.setText(curUnit);
                tot4.setText(curTotal);
                sta4.setText(curStatement);
                break;
            case 5:
                sub5.setText(curSubject);
                amo5.setText(curAmount);
                off5.setText(curOffer);
                uni5.setText(curUnit);
                tot5.setText(curTotal);
                sta5.setText(curStatement);
                break;
            case 6:
                sub6.setText(curSubject);
                amo6.setText(curAmount);
                off6.setText(curOffer);
                uni6.setText(curUnit);
                tot6.setText(curTotal);
                sta6.setText(curStatement);
                break;
            case 7:
                sub7.setText(curSubject);
                amo7.setText(curAmount);
                off7.setText(curOffer);
                uni7.setText(curUnit);
                tot7.setText(curTotal);
                sta7.setText(curStatement);
                break;
            case 8:
                sub8.setText(curSubject);
                amo8.setText(curAmount);
                off8.setText(curOffer);
                uni8.setText(curUnit);
                tot8.setText(curTotal);
                sta8.setText(curStatement);
                break;
            case 9:
                sub9.setText(curSubject);
                amo9.setText(curAmount);
                off9.setText(curOffer);
                uni9.setText(curUnit);
                tot9.setText(curTotal);
                sta9.setText(curStatement);
                break;
            case 10:
                sub10.setText(curSubject);
                amo10.setText(curAmount);
                off10.setText(curOffer);
                uni10.setText(curUnit);
                tot10.setText(curTotal);
                sta10.setText(curStatement);
                break;
            case 11:
                sub11.setText(curSubject);
                amo11.setText(curAmount);
                off11.setText(curOffer);
                uni11.setText(curUnit);
                tot11.setText(curTotal);
                sta11.setText(curStatement);
                break;
            case 12:
                sub12.setText(curSubject);
                amo12.setText(curAmount);
                off12.setText(curOffer);
                uni12.setText(curUnit);
                tot12.setText(curTotal);
                sta12.setText(curStatement);
                break;
            case 13:
                sub13.setText(curSubject);
                amo13.setText(curAmount);
                off13.setText(curOffer);
                uni13.setText(curUnit);
                tot13.setText(curTotal);
                sta13.setText(curStatement);
                break;
            case 14:
                sub14.setText(curSubject);
                amo14.setText(curAmount);
                off14.setText(curOffer);
                uni14.setText(curUnit);
                tot14.setText(curTotal);
                sta14.setText(curStatement);
                break;
            case 15:
                sub15.setText(curSubject);
                amo15.setText(curAmount);
                off15.setText(curOffer);
                uni15.setText(curUnit);
                tot15.setText(curTotal);
                sta15.setText(curStatement);
                break;
            case 16:
                sub16.setText(curSubject);
                amo16.setText(curAmount);
                off16.setText(curOffer);
                uni16.setText(curUnit);
                tot16.setText(curTotal);
                sta16.setText(curStatement);
                break;
            case 17:
                sub17.setText(curSubject);
                amo17.setText(curAmount);
                off17.setText(curOffer);
                uni17.setText(curUnit);
                tot17.setText(curTotal);
                sta17.setText(curStatement);
                break;
            case 18:
                sub18.setText(curSubject);
                amo18.setText(curAmount);
                off18.setText(curOffer);
                uni18.setText(curUnit);
                tot18.setText(curTotal);
                sta18.setText(curStatement);
                break;
            case 19:
                sub19.setText(curSubject);
                amo19.setText(curAmount);
                off19.setText(curOffer);
                uni19.setText(curUnit);
                tot19.setText(curTotal);
                sta19.setText(curStatement);
                break;
            case 20:
                sub20.setText(curSubject);
                amo20.setText(curAmount);
                off20.setText(curOffer);
                uni20.setText(curUnit);
                tot20.setText(curTotal);
                sta20.setText(curStatement);
                break;
            case 21:
                sub21.setText(curSubject);
                amo21.setText(curAmount);
                off21.setText(curOffer);
                uni21.setText(curUnit);
                tot21.setText(curTotal);
                sta21.setText(curStatement);
                break;
            case 22:
                sub22.setText(curSubject);
                amo22.setText(curAmount);
                off22.setText(curOffer);
                uni22.setText(curUnit);
                tot22.setText(curTotal);
                sta22.setText(curStatement);
                break;
            case 23:
                sub23.setText(curSubject);
                amo23.setText(curAmount);
                off23.setText(curOffer);
                uni23.setText(curUnit);
                tot23.setText(curTotal);
                sta23.setText(curStatement);
                break;
            case 24:
                sub24.setText(curSubject);
                amo24.setText(curAmount);
                off24.setText(curOffer);
                uni24.setText(curUnit);
                tot24.setText(curTotal);
                sta24.setText(curStatement);
                break;
            case 25:
                sub25.setText(curSubject);
                amo25.setText(curAmount);
                off25.setText(curOffer);
                uni25.setText(curUnit);
                tot25.setText(curTotal);
                sta25.setText(curStatement);
                break;
            case 26:
                sub26.setText(curSubject);
                amo26.setText(curAmount);
                off26.setText(curOffer);
                uni26.setText(curUnit);
                tot26.setText(curTotal);
                sta26.setText(curStatement);
                break;
            case 27:
                sub27.setText(curSubject);
                amo27.setText(curAmount);
                off27.setText(curOffer);
                uni27.setText(curUnit);
                tot27.setText(curTotal);
                sta27.setText(curStatement);
                break;
            case 28:
                sub28.setText(curSubject);
                amo28.setText(curAmount);
                off28.setText(curOffer);
                uni28.setText(curUnit);
                tot28.setText(curTotal);
                sta28.setText(curStatement);
                break;
            case 29:
                sub29.setText(curSubject);
                amo29.setText(curAmount);
                off29.setText(curOffer);
                uni29.setText(curUnit);
                tot29.setText(curTotal);
                sta29.setText(curStatement);
                break;
            case 30:
                sub30.setText(curSubject);
                amo30.setText(curAmount);
                off30.setText(curOffer);
                uni30.setText(curUnit);
                tot30.setText(curTotal);
                sta30.setText(curStatement);
                break;
            case 31:
                sub31.setText(curSubject);
                amo31.setText(curAmount);
                off31.setText(curOffer);
                uni31.setText(curUnit);
                tot31.setText(curTotal);
                sta31.setText(curStatement);
                break;
            case 32:
                sub32.setText(curSubject);
                amo32.setText(curAmount);
                off32.setText(curOffer);
                uni32.setText(curUnit);
                tot32.setText(curTotal);
                sta32.setText(curStatement);
                break;
            case 33:
                sub33.setText(curSubject);
                amo33.setText(curAmount);
                off33.setText(curOffer);
                uni33.setText(curUnit);
                tot33.setText(curTotal);
                sta33.setText(curStatement);
                break;
            case 34:
                sub34.setText(curSubject);
                amo34.setText(curAmount);
                off34.setText(curOffer);
                uni34.setText(curUnit);
                tot34.setText(curTotal);
                sta34.setText(curStatement);
                break;
            case 35:
                sub35.setText(curSubject);
                amo35.setText(curAmount);
                off35.setText(curOffer);
                uni35.setText(curUnit);
                tot35.setText(curTotal);
                sta35.setText(curStatement);
                break;
            case 36:
                sub36.setText(curSubject);
                amo36.setText(curAmount);
                off36.setText(curOffer);
                uni36.setText(curUnit);
                tot36.setText(curTotal);
                sta36.setText(curStatement);
                break;
            case 37:
                sub37.setText(curSubject);
                amo37.setText(curAmount);
                off37.setText(curOffer);
                uni37.setText(curUnit);
                tot37.setText(curTotal);
                sta37.setText(curStatement);
                break;
            case 38:
                sub38.setText(curSubject);
                amo38.setText(curAmount);
                off38.setText(curOffer);
                uni38.setText(curUnit);
                tot38.setText(curTotal);
                sta38.setText(curStatement);
                break;
            case 39:
                sub39.setText(curSubject);
                amo39.setText(curAmount);
                off39.setText(curOffer);
                uni39.setText(curUnit);
                tot39.setText(curTotal);
                sta39.setText(curStatement);
                break;
            case 40:
                sub40.setText(curSubject);
                amo40.setText(curAmount);
                off40.setText(curOffer);
                uni40.setText(curUnit);
                tot40.setText(curTotal);
                sta40.setText(curStatement);
                break;
            case 41:
                sub41.setText(curSubject);
                amo41.setText(curAmount);
                off41.setText(curOffer);
                uni41.setText(curUnit);
                tot41.setText(curTotal);
                sta41.setText(curStatement);
                break;
            case 42:
                sub42.setText(curSubject);
                amo42.setText(curAmount);
                off42.setText(curOffer);
                uni42.setText(curUnit);
                tot42.setText(curTotal);
                sta42.setText(curStatement);
                break;
            case 43:
                sub43.setText(curSubject);
                amo43.setText(curAmount);
                off43.setText(curOffer);
                uni43.setText(curUnit);
                tot43.setText(curTotal);
                sta43.setText(curStatement);
                break;
            case 44:
                sub44.setText(curSubject);
                amo44.setText(curAmount);
                off44.setText(curOffer);
                uni44.setText(curUnit);
                tot44.setText(curTotal);
                sta44.setText(curStatement);
                break;
            case 45:
                sub45.setText(curSubject);
                amo45.setText(curAmount);
                off45.setText(curOffer);
                uni45.setText(curUnit);
                tot45.setText(curTotal);
                sta45.setText(curStatement);
                break;
            case 46:
                sub46.setText(curSubject);
                amo46.setText(curAmount);
                off46.setText(curOffer);
                uni46.setText(curUnit);
                tot46.setText(curTotal);
                sta46.setText(curStatement);
                break;
            case 47:
                sub47.setText(curSubject);
                amo47.setText(curAmount);
                off47.setText(curOffer);
                uni47.setText(curUnit);
                tot47.setText(curTotal);
                sta47.setText(curStatement);
                break;
            case 48:
                sub48.setText(curSubject);
                amo48.setText(curAmount);
                off48.setText(curOffer);
                uni48.setText(curUnit);
                tot48.setText(curTotal);
                sta48.setText(curStatement);
                break;
            case 49:
                sub49.setText(curSubject);
                amo49.setText(curAmount);
                off49.setText(curOffer);
                uni49.setText(curUnit);
                tot49.setText(curTotal);
                sta49.setText(curStatement);
                break;
            case 50:
                sub50.setText(curSubject);
                amo50.setText(curAmount);
                off50.setText(curOffer);
                uni50.setText(curUnit);
                tot50.setText(curTotal);
                sta50.setText(curStatement);
                break;
        }
    }

    private void fillEditTextByCurrentRow(int rowNumber) {
        switch (rowNumber) {
            case 1:
                dialogEditSubject.setText(sub1.getText().toString());
                dialogEditAmount.setText(amo1.getText().toString());
                dialogEditOffer.setText(off1.getText().toString());
                dialogEditUnit.setText(uni1.getText().toString());
                dialogEditTotal.setText(tot1.getText().toString());
                dialogEditStatement.setText(sta1.getText().toString());
                break;
            case 2:
                dialogEditSubject.setText(sub2.getText().toString());
                dialogEditAmount.setText(amo2.getText().toString());
                dialogEditOffer.setText(off2.getText().toString());
                dialogEditUnit.setText(uni2.getText().toString());
                dialogEditTotal.setText(tot2.getText().toString());
                dialogEditStatement.setText(sta2.getText().toString());
                break;
            case 3:
                dialogEditSubject.setText(sub3.getText().toString());
                dialogEditAmount.setText(amo3.getText().toString());
                dialogEditOffer.setText(off3.getText().toString());
                dialogEditUnit.setText(uni3.getText().toString());
                dialogEditTotal.setText(tot3.getText().toString());
                dialogEditStatement.setText(sta3.getText().toString());
                break;
            case 4:
                dialogEditSubject.setText(sub4.getText().toString());
                dialogEditAmount.setText(amo4.getText().toString());
                dialogEditOffer.setText(off4.getText().toString());
                dialogEditUnit.setText(uni4.getText().toString());
                dialogEditTotal.setText(tot4.getText().toString());
                dialogEditStatement.setText(sta4.getText().toString());
                break;
            case 5:
                dialogEditSubject.setText(sub5.getText().toString());
                dialogEditAmount.setText(amo5.getText().toString());
                dialogEditOffer.setText(off5.getText().toString());
                dialogEditUnit.setText(uni5.getText().toString());
                dialogEditTotal.setText(tot5.getText().toString());
                dialogEditStatement.setText(sta5.getText().toString());
                break;
            case 6:
                dialogEditSubject.setText(sub6.getText().toString());
                dialogEditAmount.setText(amo6.getText().toString());
                dialogEditOffer.setText(off6.getText().toString());
                dialogEditUnit.setText(uni6.getText().toString());
                dialogEditTotal.setText(tot6.getText().toString());
                dialogEditStatement.setText(sta6.getText().toString());
                break;
            case 7:
                dialogEditSubject.setText(sub7.getText().toString());
                dialogEditAmount.setText(amo7.getText().toString());
                dialogEditOffer.setText(off7.getText().toString());
                dialogEditUnit.setText(uni7.getText().toString());
                dialogEditTotal.setText(tot7.getText().toString());
                dialogEditStatement.setText(sta7.getText().toString());
                break;
            case 8:
                dialogEditSubject.setText(sub8.getText().toString());
                dialogEditAmount.setText(amo8.getText().toString());
                dialogEditOffer.setText(off8.getText().toString());
                dialogEditUnit.setText(uni8.getText().toString());
                dialogEditTotal.setText(tot8.getText().toString());
                dialogEditStatement.setText(sta8.getText().toString());
                break;
            case 9:
                dialogEditSubject.setText(sub9.getText().toString());
                dialogEditAmount.setText(amo9.getText().toString());
                dialogEditOffer.setText(off9.getText().toString());
                dialogEditUnit.setText(uni9.getText().toString());
                dialogEditTotal.setText(tot9.getText().toString());
                dialogEditStatement.setText(sta9.getText().toString());
                break;
            case 10:
                dialogEditSubject.setText(sub10.getText().toString());
                dialogEditAmount.setText(amo10.getText().toString());
                dialogEditOffer.setText(off10.getText().toString());
                dialogEditUnit.setText(uni10.getText().toString());
                dialogEditTotal.setText(tot10.getText().toString());
                dialogEditStatement.setText(sta10.getText().toString());
                break;
            case 11:
                dialogEditSubject.setText(sub11.getText().toString());
                dialogEditAmount.setText(amo11.getText().toString());
                dialogEditOffer.setText(off11.getText().toString());
                dialogEditUnit.setText(uni11.getText().toString());
                dialogEditTotal.setText(tot11.getText().toString());
                dialogEditStatement.setText(sta11.getText().toString());
                break;
            case 12:
                dialogEditSubject.setText(sub12.getText().toString());
                dialogEditAmount.setText(amo12.getText().toString());
                dialogEditOffer.setText(off12.getText().toString());
                dialogEditUnit.setText(uni12.getText().toString());
                dialogEditTotal.setText(tot12.getText().toString());
                dialogEditStatement.setText(sta12.getText().toString());
                break;
            case 13:
                dialogEditSubject.setText(sub13.getText().toString());
                dialogEditAmount.setText(amo13.getText().toString());
                dialogEditOffer.setText(off13.getText().toString());
                dialogEditUnit.setText(uni13.getText().toString());
                dialogEditTotal.setText(tot13.getText().toString());
                dialogEditStatement.setText(sta13.getText().toString());
                break;
            case 14:
                dialogEditSubject.setText(sub14.getText().toString());
                dialogEditAmount.setText(amo14.getText().toString());
                dialogEditOffer.setText(off14.getText().toString());
                dialogEditUnit.setText(uni14.getText().toString());
                dialogEditTotal.setText(tot14.getText().toString());
                dialogEditStatement.setText(sta14.getText().toString());
                break;
            case 15:
                dialogEditSubject.setText(sub15.getText().toString());
                dialogEditAmount.setText(amo15.getText().toString());
                dialogEditOffer.setText(off15.getText().toString());
                dialogEditUnit.setText(uni15.getText().toString());
                dialogEditTotal.setText(tot15.getText().toString());
                dialogEditStatement.setText(sta15.getText().toString());
                break;
            case 16:
                dialogEditSubject.setText(sub16.getText().toString());
                dialogEditAmount.setText(amo16.getText().toString());
                dialogEditOffer.setText(off16.getText().toString());
                dialogEditUnit.setText(uni16.getText().toString());
                dialogEditTotal.setText(tot16.getText().toString());
                dialogEditStatement.setText(sta16.getText().toString());
                break;
            case 17:
                dialogEditSubject.setText(sub17.getText().toString());
                dialogEditAmount.setText(amo17.getText().toString());
                dialogEditOffer.setText(off17.getText().toString());
                dialogEditUnit.setText(uni17.getText().toString());
                dialogEditTotal.setText(tot17.getText().toString());
                dialogEditStatement.setText(sta17.getText().toString());
                break;
            case 18:
                dialogEditSubject.setText(sub18.getText().toString());
                dialogEditAmount.setText(amo18.getText().toString());
                dialogEditOffer.setText(off18.getText().toString());
                dialogEditUnit.setText(uni18.getText().toString());
                dialogEditTotal.setText(tot18.getText().toString());
                dialogEditStatement.setText(sta18.getText().toString());
                break;
            case 19:
                dialogEditSubject.setText(sub19.getText().toString());
                dialogEditAmount.setText(amo19.getText().toString());
                dialogEditOffer.setText(off19.getText().toString());
                dialogEditUnit.setText(uni19.getText().toString());
                dialogEditTotal.setText(tot19.getText().toString());
                dialogEditStatement.setText(sta19.getText().toString());
                break;
            case 20:
                dialogEditSubject.setText(sub20.getText().toString());
                dialogEditAmount.setText(amo20.getText().toString());
                dialogEditOffer.setText(off20.getText().toString());
                dialogEditUnit.setText(uni20.getText().toString());
                dialogEditTotal.setText(tot20.getText().toString());
                dialogEditStatement.setText(sta20.getText().toString());
                break;
            case 21:
                dialogEditSubject.setText(sub21.getText().toString());
                dialogEditAmount.setText(amo21.getText().toString());
                dialogEditOffer.setText(off21.getText().toString());
                dialogEditUnit.setText(uni21.getText().toString());
                dialogEditTotal.setText(tot21.getText().toString());
                dialogEditStatement.setText(sta21.getText().toString());
                break;
            case 22:
                dialogEditSubject.setText(sub22.getText().toString());
                dialogEditAmount.setText(amo22.getText().toString());
                dialogEditOffer.setText(off22.getText().toString());
                dialogEditUnit.setText(uni22.getText().toString());
                dialogEditTotal.setText(tot22.getText().toString());
                dialogEditStatement.setText(sta22.getText().toString());
                break;
            case 23:
                dialogEditSubject.setText(sub23.getText().toString());
                dialogEditAmount.setText(amo23.getText().toString());
                dialogEditOffer.setText(off23.getText().toString());
                dialogEditUnit.setText(uni23.getText().toString());
                dialogEditTotal.setText(tot23.getText().toString());
                dialogEditStatement.setText(sta23.getText().toString());
                break;
            case 24:
                dialogEditSubject.setText(sub24.getText().toString());
                dialogEditAmount.setText(amo24.getText().toString());
                dialogEditOffer.setText(off24.getText().toString());
                dialogEditUnit.setText(uni24.getText().toString());
                dialogEditTotal.setText(tot24.getText().toString());
                dialogEditStatement.setText(sta24.getText().toString());
                break;
            case 25:
                dialogEditSubject.setText(sub25.getText().toString());
                dialogEditAmount.setText(amo25.getText().toString());
                dialogEditOffer.setText(off25.getText().toString());
                dialogEditUnit.setText(uni25.getText().toString());
                dialogEditTotal.setText(tot25.getText().toString());
                dialogEditStatement.setText(sta25.getText().toString());
                break;
            case 26:
                dialogEditSubject.setText(sub26.getText().toString());
                dialogEditAmount.setText(amo26.getText().toString());
                dialogEditOffer.setText(off26.getText().toString());
                dialogEditUnit.setText(uni26.getText().toString());
                dialogEditTotal.setText(tot26.getText().toString());
                dialogEditStatement.setText(sta26.getText().toString());
                break;
            case 27:
                dialogEditSubject.setText(sub27.getText().toString());
                dialogEditAmount.setText(amo27.getText().toString());
                dialogEditOffer.setText(off27.getText().toString());
                dialogEditUnit.setText(uni27.getText().toString());
                dialogEditTotal.setText(tot27.getText().toString());
                dialogEditStatement.setText(sta27.getText().toString());
                break;
            case 28:
                dialogEditSubject.setText(sub28.getText().toString());
                dialogEditAmount.setText(amo28.getText().toString());
                dialogEditOffer.setText(off28.getText().toString());
                dialogEditUnit.setText(uni28.getText().toString());
                dialogEditTotal.setText(tot28.getText().toString());
                dialogEditStatement.setText(sta28.getText().toString());
                break;
            case 29:
                dialogEditSubject.setText(sub29.getText().toString());
                dialogEditAmount.setText(amo29.getText().toString());
                dialogEditOffer.setText(off29.getText().toString());
                dialogEditUnit.setText(uni29.getText().toString());
                dialogEditTotal.setText(tot29.getText().toString());
                dialogEditStatement.setText(sta29.getText().toString());
                break;
            case 30:
                dialogEditSubject.setText(sub30.getText().toString());
                dialogEditAmount.setText(amo30.getText().toString());
                dialogEditOffer.setText(off30.getText().toString());
                dialogEditUnit.setText(uni30.getText().toString());
                dialogEditTotal.setText(tot30.getText().toString());
                dialogEditStatement.setText(sta30.getText().toString());
                break;
            case 31:
                dialogEditSubject.setText(sub31.getText().toString());
                dialogEditAmount.setText(amo31.getText().toString());
                dialogEditOffer.setText(off31.getText().toString());
                dialogEditUnit.setText(uni31.getText().toString());
                dialogEditTotal.setText(tot31.getText().toString());
                dialogEditStatement.setText(sta31.getText().toString());
                break;
            case 32:
                dialogEditSubject.setText(sub32.getText().toString());
                dialogEditAmount.setText(amo32.getText().toString());
                dialogEditOffer.setText(off32.getText().toString());
                dialogEditUnit.setText(uni32.getText().toString());
                dialogEditTotal.setText(tot32.getText().toString());
                dialogEditStatement.setText(sta32.getText().toString());
                break;
            case 33:
                dialogEditSubject.setText(sub33.getText().toString());
                dialogEditAmount.setText(amo33.getText().toString());
                dialogEditOffer.setText(off33.getText().toString());
                dialogEditUnit.setText(uni33.getText().toString());
                dialogEditTotal.setText(tot33.getText().toString());
                dialogEditStatement.setText(sta33.getText().toString());
                break;
            case 34:
                dialogEditSubject.setText(sub34.getText().toString());
                dialogEditAmount.setText(amo34.getText().toString());
                dialogEditOffer.setText(off34.getText().toString());
                dialogEditUnit.setText(uni34.getText().toString());
                dialogEditTotal.setText(tot34.getText().toString());
                dialogEditStatement.setText(sta34.getText().toString());
                break;
            case 35:
                dialogEditSubject.setText(sub35.getText().toString());
                dialogEditAmount.setText(amo35.getText().toString());
                dialogEditOffer.setText(off35.getText().toString());
                dialogEditUnit.setText(uni35.getText().toString());
                dialogEditTotal.setText(tot35.getText().toString());
                dialogEditStatement.setText(sta35.getText().toString());
                break;
            case 36:
                dialogEditSubject.setText(sub36.getText().toString());
                dialogEditAmount.setText(amo36.getText().toString());
                dialogEditOffer.setText(off36.getText().toString());
                dialogEditUnit.setText(uni36.getText().toString());
                dialogEditTotal.setText(tot36.getText().toString());
                dialogEditStatement.setText(sta36.getText().toString());
                break;
            case 37:
                dialogEditSubject.setText(sub37.getText().toString());
                dialogEditAmount.setText(amo37.getText().toString());
                dialogEditOffer.setText(off37.getText().toString());
                dialogEditUnit.setText(uni37.getText().toString());
                dialogEditTotal.setText(tot37.getText().toString());
                dialogEditStatement.setText(sta37.getText().toString());
                break;
            case 38:
                dialogEditSubject.setText(sub38.getText().toString());
                dialogEditAmount.setText(amo38.getText().toString());
                dialogEditOffer.setText(off38.getText().toString());
                dialogEditUnit.setText(uni38.getText().toString());
                dialogEditTotal.setText(tot38.getText().toString());
                dialogEditStatement.setText(sta38.getText().toString());
                break;
            case 39:
                dialogEditSubject.setText(sub39.getText().toString());
                dialogEditAmount.setText(amo39.getText().toString());
                dialogEditOffer.setText(off39.getText().toString());
                dialogEditUnit.setText(uni39.getText().toString());
                dialogEditTotal.setText(tot39.getText().toString());
                dialogEditStatement.setText(sta39.getText().toString());
                break;
            case 40:
                dialogEditSubject.setText(sub40.getText().toString());
                dialogEditAmount.setText(amo40.getText().toString());
                dialogEditOffer.setText(off40.getText().toString());
                dialogEditUnit.setText(uni40.getText().toString());
                dialogEditTotal.setText(tot40.getText().toString());
                dialogEditStatement.setText(sta40.getText().toString());
                break;
            case 41:
                dialogEditSubject.setText(sub41.getText().toString());
                dialogEditAmount.setText(amo41.getText().toString());
                dialogEditOffer.setText(off41.getText().toString());
                dialogEditUnit.setText(uni41.getText().toString());
                dialogEditTotal.setText(tot41.getText().toString());
                dialogEditStatement.setText(sta41.getText().toString());
                break;
            case 42:
                dialogEditSubject.setText(sub42.getText().toString());
                dialogEditAmount.setText(amo42.getText().toString());
                dialogEditOffer.setText(off42.getText().toString());
                dialogEditUnit.setText(uni42.getText().toString());
                dialogEditTotal.setText(tot42.getText().toString());
                dialogEditStatement.setText(sta42.getText().toString());
                break;
            case 43:
                dialogEditSubject.setText(sub43.getText().toString());
                dialogEditAmount.setText(amo43.getText().toString());
                dialogEditOffer.setText(off43.getText().toString());
                dialogEditUnit.setText(uni43.getText().toString());
                dialogEditTotal.setText(tot43.getText().toString());
                dialogEditStatement.setText(sta43.getText().toString());
                break;
            case 44:
                dialogEditSubject.setText(sub44.getText().toString());
                dialogEditAmount.setText(amo44.getText().toString());
                dialogEditOffer.setText(off44.getText().toString());
                dialogEditUnit.setText(uni44.getText().toString());
                dialogEditTotal.setText(tot44.getText().toString());
                dialogEditStatement.setText(sta44.getText().toString());
                break;
            case 45:
                dialogEditSubject.setText(sub45.getText().toString());
                dialogEditAmount.setText(amo45.getText().toString());
                dialogEditOffer.setText(off45.getText().toString());
                dialogEditUnit.setText(uni45.getText().toString());
                dialogEditTotal.setText(tot45.getText().toString());
                dialogEditStatement.setText(sta45.getText().toString());
                break;
            case 46:
                dialogEditSubject.setText(sub46.getText().toString());
                dialogEditAmount.setText(amo46.getText().toString());
                dialogEditOffer.setText(off46.getText().toString());
                dialogEditUnit.setText(uni46.getText().toString());
                dialogEditTotal.setText(tot46.getText().toString());
                dialogEditStatement.setText(sta46.getText().toString());
                break;
            case 47:
                dialogEditSubject.setText(sub47.getText().toString());
                dialogEditAmount.setText(amo47.getText().toString());
                dialogEditOffer.setText(off47.getText().toString());
                dialogEditUnit.setText(uni47.getText().toString());
                dialogEditTotal.setText(tot47.getText().toString());
                dialogEditStatement.setText(sta47.getText().toString());
                break;
            case 48:
                dialogEditSubject.setText(sub48.getText().toString());
                dialogEditAmount.setText(amo48.getText().toString());
                dialogEditOffer.setText(off48.getText().toString());
                dialogEditUnit.setText(uni48.getText().toString());
                dialogEditTotal.setText(tot48.getText().toString());
                dialogEditStatement.setText(sta48.getText().toString());
                break;
            case 49:
                dialogEditSubject.setText(sub49.getText().toString());
                dialogEditAmount.setText(amo49.getText().toString());
                dialogEditOffer.setText(off49.getText().toString());
                dialogEditUnit.setText(uni49.getText().toString());
                dialogEditTotal.setText(tot49.getText().toString());
                dialogEditStatement.setText(sta49.getText().toString());
                break;
            case 50:
                dialogEditSubject.setText(sub50.getText().toString());
                dialogEditAmount.setText(amo50.getText().toString());
                dialogEditOffer.setText(off50.getText().toString());
                dialogEditUnit.setText(uni50.getText().toString());
                dialogEditTotal.setText(tot50.getText().toString());
                dialogEditStatement.setText(sta50.getText().toString());
                break;
        }
    }

    private void setSpinners() {
        String[] basic_ = res.getStringArray(R.array.pur_sal_tem);
        String[] currency = res.getStringArray(R.array.dola_sy);
        String[] type = res.getStringArray(R.array.debt_cash);
        point = res.getStringArray(R.array.pointsOfSale);

        ArrayAdapter<CharSequence> spinnerBasicArrayAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, basic_);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, currency);
        ArrayAdapter<CharSequence> spinnerTypeArrayAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, type);
        ArrayAdapter<CharSequence> spinnerPointArrayAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, point);

        spinnerBasicArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerTypeArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerPointArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);

        spin_basic.setAdapter(spinnerBasicArrayAdapter);
        spin_currency.setAdapter(spinnerCurrencyArrayAdapter);
        spin_type.setAdapter(spinnerTypeArrayAdapter);
        if (rel_spin_point.getVisibility() == View.VISIBLE)
            spin_point.setAdapter(spinnerPointArrayAdapter);
        if (rel_spin_point_.getVisibility() == View.VISIBLE)
            spin_point_.setAdapter(spinnerPointArrayAdapter);

        isSetIntialBasicSpinner = true;

        spin_basic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                spinBasicSelected = index;
                if (isSetIntialBasicSpinner) {
                    isSetIntialBasicSpinner = false;
                } else {
                    basic = index + 1;
                    setBillColor();
                    setSpinners();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spin_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                spinCurrencySelected = index;
                if (index == 0)
                    code = res.getString(R.string.sypCode);
                else
                    code = "$";
                txt_total.setText(String.valueOf(tp) + " " + code);
                txt_final.setText(String.valueOf(tp + ep) + " " + code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                spinTypeSelected = index;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spin_basic.setSelection(basic - 1);
        spin_currency.setSelection(0);
        spin_type.setSelection(0);
    }

    private void setVariabales() {
        billTable = (TableLayout) findViewById(R.id.bill_table);
        dialogAddEdit = (LinearLayout) findViewById(R.id.dialog_add_edit);
        save = (Button) findViewById(R.id.btn_save);
        txt_total = (TextView) findViewById(R.id.txt_total);
        txt_final = (TextView) findViewById(R.id.txt_final);
        txt_date = (TextView) findViewById(R.id.txt_date);
        spin_currency = (Spinner) findViewById(R.id.spin_currency);
        spin_type = (Spinner) findViewById(R.id.spin_type);
        spin_basic = (Spinner) findViewById(R.id.spin_basic);
        edit_addition = (EditText) findViewById(R.id.edit_addition);
        edit_discount = (EditText) findViewById(R.id.edit_discount);
        edit_from = (AutoCompleteTextView) findViewById(R.id.edit_from);
        edit_to = (AutoCompleteTextView) findViewById(R.id.edit_to);
        edit_from_t = (AutoCompleteTextView) findViewById(R.id.edit_from_t);
        edit_to_t = (AutoCompleteTextView) findViewById(R.id.edit_to_t);
        spin_point = (Spinner) findViewById(R.id.spin_point);
        spin_point_ = (Spinner) findViewById(R.id.spin_point_);
        rel_spin_point = (RelativeLayout) findViewById(R.id.rel_spin_point);
        rel_spin_point_ = (RelativeLayout) findViewById(R.id.rel_spin_point_);
        voiceImage = (ImageView) findViewById(R.id.voiceImage);
        productImage = (ImageView) findViewById(R.id.productImage);
        spin_img_basic = (ImageView) findViewById(R.id.spin_img_basic);
        spin_img_point = (ImageView) findViewById(R.id.spin_img_point);
        spin_img_point_ = (ImageView) findViewById(R.id.spin_img_point_);
        spin_img_currency = (ImageView) findViewById(R.id.spin_img_currency);
        spin_img_type = (ImageView) findViewById(R.id.spin_img_type);
        category = (AutoCompleteTextView) findViewById(R.id.categ);
        scrollView = (ScrollView) findViewById(R.id.scroll_view1);
        linearLayoutOfItemsDidden = (LinearLayout) findViewById(R.id.linear_layout0);
        productUnits = new ArrayList<Double>();
        bun = getIntent().getExtras();
        res = this.getResources();
        basic = bun.getInt("sub");
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        edit_discount.setText("0.0");
        edit_addition.setText("0.0");
        db = new BillSQLDatabaseHandler(this);
        dbs = new SubjectSQLDatabaseHandler(this);
        dbc = new CustomerSQLDatabaseHandler(this);
        dbsu = new SupplierSQLDatabaseHandler(this);
        FIRST_BILL_WORDS = res.getStringArray(R.array.FIRST_BILL_WORDS);
        if (basic == 1) {
            ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, dbsu.allSuppliersNames());
            edit_from.setAdapter(folderSuggestionAdapter);
        } else if (basic == 2) {
            ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, dbc.allCustomersNames());
            edit_to.setAdapter(folderSuggestionAdapter);
        } else {
            ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, dbsu.allSuppliersNames());
            edit_from_t.setAdapter(folderSuggestionAdapter);
            ArrayAdapter<String> folderSuggestionAdapter1 = new ArrayAdapter<String>(BillActivity.this, android.R.layout.simple_list_item_1, dbc.allCustomersNames());
            edit_to_t.setAdapter(folderSuggestionAdapter1);
        }
        if (pref.getBoolean("isBillVoiceControlEnable", false)) {
            voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_));
            billVoice();
        } else
            voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_f_));

        row1 = (TableRow) findViewById(R.id.row_1);
        row2 = (TableRow) findViewById(R.id.row_2);
        row3 = (TableRow) findViewById(R.id.row_3);
        row4 = (TableRow) findViewById(R.id.row_4);
        row5 = (TableRow) findViewById(R.id.row_5);
        row6 = (TableRow) findViewById(R.id.row_6);
        row7 = (TableRow) findViewById(R.id.row_7);
        row8 = (TableRow) findViewById(R.id.row_8);
        row9 = (TableRow) findViewById(R.id.row_9);
        row10 = (TableRow) findViewById(R.id.row_10);
        row11 = (TableRow) findViewById(R.id.row_11);
        row12 = (TableRow) findViewById(R.id.row_12);
        row13 = (TableRow) findViewById(R.id.row_13);
        row14 = (TableRow) findViewById(R.id.row_14);
        row15 = (TableRow) findViewById(R.id.row_15);
        row16 = (TableRow) findViewById(R.id.row_16);
        row17 = (TableRow) findViewById(R.id.row_17);
        row18 = (TableRow) findViewById(R.id.row_18);
        row19 = (TableRow) findViewById(R.id.row_19);
        row20 = (TableRow) findViewById(R.id.row_20);
        row21 = (TableRow) findViewById(R.id.row_21);
        row22 = (TableRow) findViewById(R.id.row_22);
        row23 = (TableRow) findViewById(R.id.row_23);
        row24 = (TableRow) findViewById(R.id.row_24);
        row25 = (TableRow) findViewById(R.id.row_25);
        row26 = (TableRow) findViewById(R.id.row_26);
        row27 = (TableRow) findViewById(R.id.row_27);
        row28 = (TableRow) findViewById(R.id.row_28);
        row29 = (TableRow) findViewById(R.id.row_29);
        row30 = (TableRow) findViewById(R.id.row_30);
        row31 = (TableRow) findViewById(R.id.row_31);
        row32 = (TableRow) findViewById(R.id.row_32);
        row33 = (TableRow) findViewById(R.id.row_33);
        row34 = (TableRow) findViewById(R.id.row_34);
        row35 = (TableRow) findViewById(R.id.row_35);
        row36 = (TableRow) findViewById(R.id.row_36);
        row37 = (TableRow) findViewById(R.id.row_37);
        row38 = (TableRow) findViewById(R.id.row_38);
        row39 = (TableRow) findViewById(R.id.row_39);
        row40 = (TableRow) findViewById(R.id.row_40);
        row41 = (TableRow) findViewById(R.id.row_41);
        row42 = (TableRow) findViewById(R.id.row_42);
        row43 = (TableRow) findViewById(R.id.row_43);
        row44 = (TableRow) findViewById(R.id.row_44);
        row45 = (TableRow) findViewById(R.id.row_45);
        row46 = (TableRow) findViewById(R.id.row_46);
        row47 = (TableRow) findViewById(R.id.row_47);
        row48 = (TableRow) findViewById(R.id.row_48);
        row49 = (TableRow) findViewById(R.id.row_49);
        row50 = (TableRow) findViewById(R.id.row_50);

        sub1 = (TextView) findViewById(R.id.subject1);
        sub2 = (TextView) findViewById(R.id.subject2);
        sub3 = (TextView) findViewById(R.id.subject3);
        sub4 = (TextView) findViewById(R.id.subject4);
        sub5 = (TextView) findViewById(R.id.subject5);
        sub6 = (TextView) findViewById(R.id.subject6);
        sub7 = (TextView) findViewById(R.id.subject7);
        sub8 = (TextView) findViewById(R.id.subject8);
        sub9 = (TextView) findViewById(R.id.subject9);
        sub10 = (TextView) findViewById(R.id.subject10);
        sub11 = (TextView) findViewById(R.id.subject11);
        sub12 = (TextView) findViewById(R.id.subject12);
        sub13 = (TextView) findViewById(R.id.subject13);
        sub14 = (TextView) findViewById(R.id.subject14);
        sub15 = (TextView) findViewById(R.id.subject15);
        sub16 = (TextView) findViewById(R.id.subject16);
        sub17 = (TextView) findViewById(R.id.subject17);
        sub18 = (TextView) findViewById(R.id.subject18);
        sub19 = (TextView) findViewById(R.id.subject19);
        sub20 = (TextView) findViewById(R.id.subject20);
        sub21 = (TextView) findViewById(R.id.subject21);
        sub22 = (TextView) findViewById(R.id.subject22);
        sub23 = (TextView) findViewById(R.id.subject23);
        sub24 = (TextView) findViewById(R.id.subject24);
        sub25 = (TextView) findViewById(R.id.subject25);
        sub26 = (TextView) findViewById(R.id.subject26);
        sub27 = (TextView) findViewById(R.id.subject27);
        sub28 = (TextView) findViewById(R.id.subject28);
        sub29 = (TextView) findViewById(R.id.subject29);
        sub30 = (TextView) findViewById(R.id.subject30);
        sub31 = (TextView) findViewById(R.id.subject31);
        sub32 = (TextView) findViewById(R.id.subject32);
        sub33 = (TextView) findViewById(R.id.subject33);
        sub34 = (TextView) findViewById(R.id.subject34);
        sub35 = (TextView) findViewById(R.id.subject35);
        sub36 = (TextView) findViewById(R.id.subject36);
        sub37 = (TextView) findViewById(R.id.subject37);
        sub38 = (TextView) findViewById(R.id.subject38);
        sub39 = (TextView) findViewById(R.id.subject39);
        sub40 = (TextView) findViewById(R.id.subject40);
        sub41 = (TextView) findViewById(R.id.subject41);
        sub42 = (TextView) findViewById(R.id.subject42);
        sub43 = (TextView) findViewById(R.id.subject43);
        sub44 = (TextView) findViewById(R.id.subject44);
        sub45 = (TextView) findViewById(R.id.subject45);
        sub46 = (TextView) findViewById(R.id.subject46);
        sub47 = (TextView) findViewById(R.id.subject47);
        sub48 = (TextView) findViewById(R.id.subject48);
        sub49 = (TextView) findViewById(R.id.subject49);
        sub50 = (TextView) findViewById(R.id.subject50);


        amo1 = (TextView) findViewById(R.id.amount1);
        amo2 = (TextView) findViewById(R.id.amount2);
        amo3 = (TextView) findViewById(R.id.amount3);
        amo4 = (TextView) findViewById(R.id.amount4);
        amo5 = (TextView) findViewById(R.id.amount5);
        amo6 = (TextView) findViewById(R.id.amount6);
        amo7 = (TextView) findViewById(R.id.amount7);
        amo8 = (TextView) findViewById(R.id.amount8);
        amo9 = (TextView) findViewById(R.id.amount9);
        amo10 = (TextView) findViewById(R.id.amount10);
        amo11 = (TextView) findViewById(R.id.amount11);
        amo12 = (TextView) findViewById(R.id.amount12);
        amo13 = (TextView) findViewById(R.id.amount13);
        amo14 = (TextView) findViewById(R.id.amount14);
        amo15 = (TextView) findViewById(R.id.amount15);
        amo16 = (TextView) findViewById(R.id.amount16);
        amo17 = (TextView) findViewById(R.id.amount17);
        amo18 = (TextView) findViewById(R.id.amount18);
        amo19 = (TextView) findViewById(R.id.amount19);
        amo20 = (TextView) findViewById(R.id.amount20);
        amo21 = (TextView) findViewById(R.id.amount21);
        amo22 = (TextView) findViewById(R.id.amount22);
        amo23 = (TextView) findViewById(R.id.amount23);
        amo24 = (TextView) findViewById(R.id.amount24);
        amo25 = (TextView) findViewById(R.id.amount25);
        amo26 = (TextView) findViewById(R.id.amount26);
        amo27 = (TextView) findViewById(R.id.amount27);
        amo28 = (TextView) findViewById(R.id.amount28);
        amo29 = (TextView) findViewById(R.id.amount29);
        amo30 = (TextView) findViewById(R.id.amount30);
        amo31 = (TextView) findViewById(R.id.amount31);
        amo32 = (TextView) findViewById(R.id.amount32);
        amo33 = (TextView) findViewById(R.id.amount33);
        amo34 = (TextView) findViewById(R.id.amount34);
        amo35 = (TextView) findViewById(R.id.amount35);
        amo36 = (TextView) findViewById(R.id.amount36);
        amo37 = (TextView) findViewById(R.id.amount37);
        amo38 = (TextView) findViewById(R.id.amount38);
        amo39 = (TextView) findViewById(R.id.amount39);
        amo40 = (TextView) findViewById(R.id.amount40);
        amo41 = (TextView) findViewById(R.id.amount41);
        amo42 = (TextView) findViewById(R.id.amount42);
        amo43 = (TextView) findViewById(R.id.amount43);
        amo44 = (TextView) findViewById(R.id.amount44);
        amo45 = (TextView) findViewById(R.id.amount45);
        amo46 = (TextView) findViewById(R.id.amount46);
        amo47 = (TextView) findViewById(R.id.amount47);
        amo48 = (TextView) findViewById(R.id.amount48);
        amo49 = (TextView) findViewById(R.id.amount49);
        amo50 = (TextView) findViewById(R.id.amount50);


        off1 = (TextView) findViewById(R.id.offer1);
        off2 = (TextView) findViewById(R.id.offer2);
        off3 = (TextView) findViewById(R.id.offer3);
        off4 = (TextView) findViewById(R.id.offer4);
        off5 = (TextView) findViewById(R.id.offer5);
        off6 = (TextView) findViewById(R.id.offer6);
        off7 = (TextView) findViewById(R.id.offer7);
        off8 = (TextView) findViewById(R.id.offer8);
        off9 = (TextView) findViewById(R.id.offer9);
        off10 = (TextView) findViewById(R.id.offer10);
        off11 = (TextView) findViewById(R.id.offer11);
        off12 = (TextView) findViewById(R.id.offer12);
        off13 = (TextView) findViewById(R.id.offer13);
        off14 = (TextView) findViewById(R.id.offer14);
        off15 = (TextView) findViewById(R.id.offer15);
        off16 = (TextView) findViewById(R.id.offer16);
        off17 = (TextView) findViewById(R.id.offer17);
        off18 = (TextView) findViewById(R.id.offer18);
        off19 = (TextView) findViewById(R.id.offer19);
        off20 = (TextView) findViewById(R.id.offer20);
        off21 = (TextView) findViewById(R.id.offer21);
        off22 = (TextView) findViewById(R.id.offer22);
        off23 = (TextView) findViewById(R.id.offer23);
        off24 = (TextView) findViewById(R.id.offer24);
        off25 = (TextView) findViewById(R.id.offer25);
        off26 = (TextView) findViewById(R.id.offer26);
        off27 = (TextView) findViewById(R.id.offer27);
        off28 = (TextView) findViewById(R.id.offer28);
        off29 = (TextView) findViewById(R.id.offer29);
        off30 = (TextView) findViewById(R.id.offer30);
        off31 = (TextView) findViewById(R.id.offer31);
        off32 = (TextView) findViewById(R.id.offer32);
        off33 = (TextView) findViewById(R.id.offer33);
        off34 = (TextView) findViewById(R.id.offer34);
        off35 = (TextView) findViewById(R.id.offer35);
        off36 = (TextView) findViewById(R.id.offer36);
        off37 = (TextView) findViewById(R.id.offer37);
        off38 = (TextView) findViewById(R.id.offer38);
        off39 = (TextView) findViewById(R.id.offer39);
        off40 = (TextView) findViewById(R.id.offer40);
        off41 = (TextView) findViewById(R.id.offer41);
        off42 = (TextView) findViewById(R.id.offer42);
        off43 = (TextView) findViewById(R.id.offer43);
        off44 = (TextView) findViewById(R.id.offer44);
        off45 = (TextView) findViewById(R.id.offer45);
        off46 = (TextView) findViewById(R.id.offer46);
        off47 = (TextView) findViewById(R.id.offer47);
        off48 = (TextView) findViewById(R.id.offer48);
        off49 = (TextView) findViewById(R.id.offer49);
        off50 = (TextView) findViewById(R.id.offer50);


        uni1 = (TextView) findViewById(R.id.unit1);
        uni2 = (TextView) findViewById(R.id.unit2);
        uni3 = (TextView) findViewById(R.id.unit3);
        uni4 = (TextView) findViewById(R.id.unit4);
        uni5 = (TextView) findViewById(R.id.unit5);
        uni6 = (TextView) findViewById(R.id.unit6);
        uni7 = (TextView) findViewById(R.id.unit7);
        uni8 = (TextView) findViewById(R.id.unit8);
        uni9 = (TextView) findViewById(R.id.unit9);
        uni10 = (TextView) findViewById(R.id.unit10);
        uni11 = (TextView) findViewById(R.id.unit11);
        uni12 = (TextView) findViewById(R.id.unit12);
        uni13 = (TextView) findViewById(R.id.unit13);
        uni14 = (TextView) findViewById(R.id.unit14);
        uni15 = (TextView) findViewById(R.id.unit15);
        uni16 = (TextView) findViewById(R.id.unit16);
        uni17 = (TextView) findViewById(R.id.unit17);
        uni18 = (TextView) findViewById(R.id.unit18);
        uni19 = (TextView) findViewById(R.id.unit19);
        uni20 = (TextView) findViewById(R.id.unit20);
        uni21 = (TextView) findViewById(R.id.unit21);
        uni22 = (TextView) findViewById(R.id.unit22);
        uni23 = (TextView) findViewById(R.id.unit23);
        uni24 = (TextView) findViewById(R.id.unit24);
        uni25 = (TextView) findViewById(R.id.unit25);
        uni26 = (TextView) findViewById(R.id.unit26);
        uni27 = (TextView) findViewById(R.id.unit27);
        uni28 = (TextView) findViewById(R.id.unit28);
        uni29 = (TextView) findViewById(R.id.unit29);
        uni30 = (TextView) findViewById(R.id.unit30);
        uni31 = (TextView) findViewById(R.id.unit31);
        uni32 = (TextView) findViewById(R.id.unit32);
        uni33 = (TextView) findViewById(R.id.unit33);
        uni34 = (TextView) findViewById(R.id.unit34);
        uni35 = (TextView) findViewById(R.id.unit35);
        uni36 = (TextView) findViewById(R.id.unit36);
        uni37 = (TextView) findViewById(R.id.unit37);
        uni38 = (TextView) findViewById(R.id.unit38);
        uni39 = (TextView) findViewById(R.id.unit39);
        uni40 = (TextView) findViewById(R.id.unit40);
        uni41 = (TextView) findViewById(R.id.unit41);
        uni42 = (TextView) findViewById(R.id.unit42);
        uni43 = (TextView) findViewById(R.id.unit43);
        uni44 = (TextView) findViewById(R.id.unit44);
        uni45 = (TextView) findViewById(R.id.unit45);
        uni46 = (TextView) findViewById(R.id.unit46);
        uni47 = (TextView) findViewById(R.id.unit47);
        uni48 = (TextView) findViewById(R.id.unit48);
        uni49 = (TextView) findViewById(R.id.unit49);
        uni50 = (TextView) findViewById(R.id.unit50);


        tot1 = (TextView) findViewById(R.id.total1);
        tot2 = (TextView) findViewById(R.id.total2);
        tot3 = (TextView) findViewById(R.id.total3);
        tot4 = (TextView) findViewById(R.id.total4);
        tot5 = (TextView) findViewById(R.id.total5);
        tot6 = (TextView) findViewById(R.id.total6);
        tot7 = (TextView) findViewById(R.id.total7);
        tot8 = (TextView) findViewById(R.id.total8);
        tot9 = (TextView) findViewById(R.id.total9);
        tot10 = (TextView) findViewById(R.id.total10);
        tot11 = (TextView) findViewById(R.id.total11);
        tot12 = (TextView) findViewById(R.id.total12);
        tot13 = (TextView) findViewById(R.id.total13);
        tot14 = (TextView) findViewById(R.id.total14);
        tot15 = (TextView) findViewById(R.id.total15);
        tot16 = (TextView) findViewById(R.id.total16);
        tot17 = (TextView) findViewById(R.id.total17);
        tot18 = (TextView) findViewById(R.id.total18);
        tot19 = (TextView) findViewById(R.id.total19);
        tot20 = (TextView) findViewById(R.id.total20);
        tot21 = (TextView) findViewById(R.id.total21);
        tot22 = (TextView) findViewById(R.id.total22);
        tot23 = (TextView) findViewById(R.id.total23);
        tot24 = (TextView) findViewById(R.id.total24);
        tot25 = (TextView) findViewById(R.id.total25);
        tot26 = (TextView) findViewById(R.id.total26);
        tot27 = (TextView) findViewById(R.id.total27);
        tot28 = (TextView) findViewById(R.id.total28);
        tot29 = (TextView) findViewById(R.id.total29);
        tot30 = (TextView) findViewById(R.id.total30);
        tot31 = (TextView) findViewById(R.id.total31);
        tot32 = (TextView) findViewById(R.id.total32);
        tot33 = (TextView) findViewById(R.id.total33);
        tot34 = (TextView) findViewById(R.id.total34);
        tot35 = (TextView) findViewById(R.id.total35);
        tot36 = (TextView) findViewById(R.id.total36);
        tot37 = (TextView) findViewById(R.id.total37);
        tot38 = (TextView) findViewById(R.id.total38);
        tot39 = (TextView) findViewById(R.id.total39);
        tot40 = (TextView) findViewById(R.id.total40);
        tot41 = (TextView) findViewById(R.id.total41);
        tot42 = (TextView) findViewById(R.id.total42);
        tot43 = (TextView) findViewById(R.id.total43);
        tot44 = (TextView) findViewById(R.id.total44);
        tot45 = (TextView) findViewById(R.id.total45);
        tot46 = (TextView) findViewById(R.id.total46);
        tot47 = (TextView) findViewById(R.id.total47);
        tot48 = (TextView) findViewById(R.id.total48);
        tot49 = (TextView) findViewById(R.id.total49);
        tot50 = (TextView) findViewById(R.id.total50);


        sta1 = (TextView) findViewById(R.id.state1);
        sta2 = (TextView) findViewById(R.id.state2);
        sta3 = (TextView) findViewById(R.id.state3);
        sta4 = (TextView) findViewById(R.id.state4);
        sta5 = (TextView) findViewById(R.id.state5);
        sta6 = (TextView) findViewById(R.id.state6);
        sta7 = (TextView) findViewById(R.id.state7);
        sta8 = (TextView) findViewById(R.id.state8);
        sta9 = (TextView) findViewById(R.id.state9);
        sta10 = (TextView) findViewById(R.id.state10);
        sta11 = (TextView) findViewById(R.id.state11);
        sta12 = (TextView) findViewById(R.id.state12);
        sta13 = (TextView) findViewById(R.id.state13);
        sta14 = (TextView) findViewById(R.id.state14);
        sta15 = (TextView) findViewById(R.id.state15);
        sta16 = (TextView) findViewById(R.id.state16);
        sta17 = (TextView) findViewById(R.id.state17);
        sta18 = (TextView) findViewById(R.id.state18);
        sta19 = (TextView) findViewById(R.id.state19);
        sta20 = (TextView) findViewById(R.id.state20);
        sta21 = (TextView) findViewById(R.id.state21);
        sta22 = (TextView) findViewById(R.id.state22);
        sta23 = (TextView) findViewById(R.id.state23);
        sta24 = (TextView) findViewById(R.id.state24);
        sta25 = (TextView) findViewById(R.id.state25);
        sta26 = (TextView) findViewById(R.id.state26);
        sta27 = (TextView) findViewById(R.id.state27);
        sta28 = (TextView) findViewById(R.id.state28);
        sta29 = (TextView) findViewById(R.id.state29);
        sta30 = (TextView) findViewById(R.id.state30);
        sta31 = (TextView) findViewById(R.id.state31);
        sta32 = (TextView) findViewById(R.id.state32);
        sta33 = (TextView) findViewById(R.id.state33);
        sta34 = (TextView) findViewById(R.id.state34);
        sta35 = (TextView) findViewById(R.id.state35);
        sta36 = (TextView) findViewById(R.id.state36);
        sta37 = (TextView) findViewById(R.id.state37);
        sta38 = (TextView) findViewById(R.id.state38);
        sta39 = (TextView) findViewById(R.id.state39);
        sta40 = (TextView) findViewById(R.id.state40);
        sta41 = (TextView) findViewById(R.id.state41);
        sta42 = (TextView) findViewById(R.id.state42);
        sta43 = (TextView) findViewById(R.id.state43);
        sta44 = (TextView) findViewById(R.id.state44);
        sta45 = (TextView) findViewById(R.id.state45);
        sta46 = (TextView) findViewById(R.id.state46);
        sta47 = (TextView) findViewById(R.id.state47);
        sta48 = (TextView) findViewById(R.id.state48);
        sta49 = (TextView) findViewById(R.id.state49);
        sta50 = (TextView) findViewById(R.id.state50);
        if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic")) {
            spin_img_basic.setScaleX(-1);
            spin_img_point.setScaleX(-1);
            spin_img_point_.setScaleX(-1);
            spin_img_type.setScaleX(-1);
            spin_img_currency.setScaleX(-1);
        } else {

        }
    }

    ArrayList<String> voice_results;

    private void billVoice() {
        if (isVoiceControlingEnabled) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    "com.domain.app");

            SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(BillActivity.this);
            RecognitionListener listener = new RecognitionListener() {
                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> voiceResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (voiceResults == null) {
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                    } else {
                        voice_results = voiceResults;
                        if (checkBasicLengthOne(voiceResults))
                            billVoice();
                        else if (checkSAddRow(voiceResults))
                            billVoice();
                        else if (checkSChangeRow(voiceResults))
                            billVoice();
                        else if (checkSDeleteRow(voiceResults))
                            billVoice();
                        else if (checkSFrom(voiceResults))
                            billVoice();
                        else if (checkSTo(voiceResults))
                            billVoice();
                        else if (checkSDate(voiceResults))
                            billVoice();
                        else if (checkSDiscount(voiceResults))
                            billVoice();
                        else if (checkSAddition(voiceResults))
                            billVoice();
                        else if (checkSCategory(voiceResults))
                            billVoice();
                        else if (checkSBillType(voiceResults))
                            billVoice();
                        else if (checkSCurrency(voiceResults))
                            billVoice();
                        else if (checkSExitBill(voiceResults)) {

                        } else if (checkSStopVoice(voiceResults)) {

                        } else if (checkSSave(voiceResults)) {

                        } else {
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                            billVoice();
                        }
                        /**boolean isWordsCheckWork = false;
                         boolean isWordsCheckSmartWork = false;
                         for (int k = 0; k < voiceResults.size(); k++) {
                         Toast.makeText(BillActivity.this, voiceResults.get(k), Toast.LENGTH_LONG).show();
                         boolean isFirstWork = false;
                         boolean isSecondWork = false;
                         for (int m = 0; m < FIRST_BILL_WORDS.length; m++) {
                         if (countWords(voiceResults.get(k)) == 1 && voiceResults.get(k).contains(FIRST_BILL_WORDS[m]))
                         isFirstWork = checkBasicLengthOne(voiceResults.get(k));
                         else if (countWords(voiceResults.get(k)) > 1 && firstWord(voiceResults.get(k)).contains(FIRST_BILL_WORDS[m]))
                         isSecondWork = checkBasicLengthMoreOne(voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 "));
                         if (isFirstWork || isSecondWork) {
                         isWordsCheckWork = true;
                         break;
                         }
                         }
                         if (isFirstWork || isSecondWork) {
                         isWordsCheckWork = true;
                         break;
                         }
                         }
                         if ((basic == 1 || basic == 3) && !isWordsCheckWork) {
                         isWordsCheckSmartWork = checkSmartSentences(voiceResults);
                         }
                         if (!isWordsCheckWork && !isWordsCheckSmartWork) {
                         if (isWordsNotCompleted)
                         billVoice();
                         else {
                         if (!checkBasicSentences(voiceResults)) {
                         Toast.makeText(BillActivity.this, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                         billVoice();
                         }
                         }
                         } else {
                         billVoice();
                         }**/

                    }
                }

                @Override
                public void onReadyForSpeech(Bundle params) {
                }

                @Override
                public void onError(int error) {
                    if (error != 8)
                        billVoice();
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

    private boolean checkSDeleteRow(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if (countWords(s) >= 2) {
                if (s.contains(FIRST_BILL_WORDS[15]) && isNumeric(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[15]) + 1)))) {
                    deleteRow(Integer.valueOf(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[15]) + 1))));
                    if (Integer.valueOf(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[15]) + 1))) < 1 || Integer.valueOf(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[15]) + 1))) > 50)
                        Toast.makeText(this, res.getString(R.string.error_no_number_exist), Toast.LENGTH_LONG).show();
                    else {
                        deleteRow(Integer.valueOf(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[15]) + 1))));
                        isOk = true;
                        break outerloop;
                    }
                } else if (firstWord(s).matches(FIRST_BILL_WORDS[15])) {
                    if (!isErrorVoiceExist) {
                        isErrorVoiceExist = true;
                        Toast.makeText(this, res.getString(R.string.error_voice_delete_row), Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, res.getString(R.string.error_voice_delete_row_), Toast.LENGTH_LONG).show();
                    }
                    isOk = true;
                }
            }

        }
        isErrorVoiceExist = false;
        return isOk;
    }

    boolean isErrorVoiceExist = false;

    private boolean checkSAddRow(ArrayList<String> voiceResults) {
        boolean isOk = false;
        boolean isSmartDetection = false;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> amounts = new ArrayList<>();
        ArrayList<Double> offers = new ArrayList<>();
        ArrayList<Double> units = new ArrayList<>();
        ArrayList<String> states = new ArrayList<>();
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            Toast.makeText(BillActivity.this, s, Toast.LENGTH_LONG).show();
            if (countWords(s) >= 3 && !(firstWord(s).matches(FIRST_BILL_WORDS[6]) || firstWord(s).matches(FIRST_BILL_WORDS[7]))) {
                if (countWords(s) == 3) {
                    if (isNumeric(nthWord(3, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (nthWord(1, s).toLowerCase(Locale.getDefault()).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(2, s).contains(FIRST_BILL_WORDS[8]) || nthWord(2, s).contains(FIRST_BILL_WORDS[9]) || nthWord(2, s).contains(FIRST_BILL_WORDS[10]))) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(3, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(3, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(3, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(2, s).contains(FIRST_BILL_WORDS[32]) || nthWord(2, s).contains(FIRST_BILL_WORDS[33]))) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(3, s)) * SLCI.getAmountLast(), "");
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(3, s)) * SLCI.getAmountLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(3, s)) * SLCI.getAmount(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(3, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(3, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(3, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 4) {
                    if (isNumeric(nthWord(4, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault()) + " " + nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10]))) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(3, s).contains(FIRST_BILL_WORDS[32]) || nthWord(3, s).contains(FIRST_BILL_WORDS[33]))) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLast(), "");
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmount(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 5) {
                    if (isNumeric(nthWord(3, s)) && isNumeric(nthWord(5, s)) && !(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(1, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(2, s).contains(FIRST_BILL_WORDS[8]) || nthWord(2, s).contains(FIRST_BILL_WORDS[9]) || nthWord(2, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(4, s).contains(FIRST_BILL_WORDS[32]) || nthWord(4, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0.0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), "");
                                isOk = true;
                            } else if ((nthWord(2, s).contains(FIRST_BILL_WORDS[8]) || nthWord(2, s).contains(FIRST_BILL_WORDS[9]) || nthWord(2, s).contains(FIRST_BILL_WORDS[10])) && nthWord(4, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(5, s)), SLCI.getLast(), Double.valueOf(nthWord(3, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(5, s)), SLCI.getLock(), Double.valueOf(nthWord(3, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(5, s)), SLCI.getCost(), Double.valueOf(nthWord(3, s)) * SLCI.getCost(), "");
                                isOk = true;
                            } else {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0.0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), "");
                            }
                            break outerloop;
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(1, s).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add(nthWord(1, s));
                                amounts.add(Double.valueOf(nthWord(3, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(5, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(5, s)) && (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(2, s) + " " + nthWord(3, s)).toLowerCase(Locale.getDefault()).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10]))) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[32]) || nthWord(4, s).contains(FIRST_BILL_WORDS[33]))) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLast(), "");
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmount(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 6) {
                    if (isNumeric(nthWord(4, s)) && isNumeric(nthWord(6, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault()) + " " + nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10])) && nthWord(5, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(3, s).contains(FIRST_BILL_WORDS[32]) || nthWord(3, s).contains(FIRST_BILL_WORDS[33])) && nthWord(5, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLast(), "");
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmount(), "");
                                isOk = true;
                                break outerloop;
                            } else {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                            if (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))
                                addRowVoice(false, isExist, id_, nthWord(2, s), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                            else
                                addRowVoice(false, isExist, id_, nthWord(1, s) + " " + nthWord(2, s), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(2, s).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;
                                } else {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(1, s) + " " + nthWord(2, s)).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;
                                }
                            }
                            if (!isExistInNames) {
                                if (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))
                                    names.add(nthWord(2, s));
                                else
                                    names.add(nthWord(1, s) + " " + nthWord(2, s));
                                amounts.add(Double.valueOf(nthWord(4, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(6, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(4, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault()) + " " + nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10])) && nthWord(5, s).contains(FIRST_BILL_WORDS[35])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), nthWord(6, s));
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), nthWord(6, s));
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), nthWord(6, s));
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(3, s).contains(FIRST_BILL_WORDS[32]) || nthWord(3, s).contains(FIRST_BILL_WORDS[33])) && nthWord(5, s).contains(FIRST_BILL_WORDS[35])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0.0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLast(), nthWord(6, s));
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0.0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLock(), nthWord(6, s));
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0.0, Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmount(), nthWord(6, s));
                                isOk = true;
                                break outerloop;
                            } else {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), nthWord(6, s));
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), nthWord(6, s));
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), nthWord(6, s));
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 7) {
                    if (isNumeric(nthWord(3, s)) && isNumeric(nthWord(5, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if (isNumeric(nthWord(7, s)))
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0.0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), nthWord(7, s));
                            isOk = true;
                            break outerloop;
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(1, s).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add(nthWord(1, s));
                                amounts.add(Double.valueOf(nthWord(3, s)));
                                units.add(Double.valueOf(nthWord(5, s)));
                                if (isNumeric(nthWord(7, s))) {
                                    offers.add(Double.valueOf(nthWord(7, s)));
                                    states.add("");
                                } else {
                                    offers.add(0.0);
                                    states.add(nthWord(7, s));
                                }
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(5, s)) && isNumeric(nthWord(7, s)) && (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(2, s).toLowerCase(Locale.getDefault()) + " " + nthWord(3, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(6, s).contains(FIRST_BILL_WORDS[32]) || nthWord(6, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0.0, Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)) * Double.valueOf(nthWord(7, s)), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(6, s).contains(FIRST_BILL_WORDS[34]))) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(4, s).contains(FIRST_BILL_WORDS[32]) || nthWord(4, s).contains(FIRST_BILL_WORDS[33])) && (nthWord(6, s).contains(FIRST_BILL_WORDS[34]))) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLast(), "");
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmount(), "");
                                isOk = true;
                                break outerloop;
                            }
                            if (isNumeric(nthWord(7, s)))
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), "");
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(3, s)), 0.0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(3, s)) * Double.valueOf(nthWord(5, s)), nthWord(7, s));
                            isOk = true;
                            break outerloop;
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(2, s).toLowerCase(Locale.getDefault()) + " " + nthWord(3, s).toLowerCase(Locale.getDefault()))))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add((nthWord(2, s).toLowerCase(Locale.getDefault()) + " " + nthWord(3, s).toLowerCase(Locale.getDefault())));
                                amounts.add(Double.valueOf(nthWord(5, s)));
                                units.add(Double.valueOf(nthWord(7, s)));
                                offers.add(0.0);
                                states.add("");
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(5, s)) && (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(2, s) + " " + nthWord(3, s)).toLowerCase(Locale.getDefault()).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10])) && nthWord(6, s).contains(FIRST_BILL_WORDS[35])) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), nthWord(7, s));
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), nthWord(7, s));
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), nthWord(7, s));
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[32]) || nthWord(4, s).contains(FIRST_BILL_WORDS[33])) && nthWord(6, s).contains(FIRST_BILL_WORDS[35])) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLast(), nthWord(7, s));
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLock(), nthWord(7, s));
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmount(), nthWord(7, s));
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), nthWord(7, s));
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), nthWord(7, s));
                            else
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), nthWord(7, s));
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(4, s)) && isNumeric(nthWord(6, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault()) + " " + nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(3, s).contains(FIRST_BILL_WORDS[8]) || nthWord(3, s).contains(FIRST_BILL_WORDS[9]) || nthWord(3, s).contains(FIRST_BILL_WORDS[10])) && nthWord(5, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getLast(), Double.valueOf(nthWord(4, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getLock(), Double.valueOf(nthWord(4, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(6, s)), SLCI.getCost(), Double.valueOf(nthWord(4, s)) * SLCI.getCost(), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(3, s).contains(FIRST_BILL_WORDS[32]) || nthWord(3, s).contains(FIRST_BILL_WORDS[33])) && nthWord(5, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLast(), "");
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmountLock(), "");
                                else
                                    addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)), Double.valueOf(nthWord(4, s)) * SLCI.getAmount(), "");
                                isOk = true;
                                break outerloop;
                            } else {
                                addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(2, s).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;
                                } else {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(1, s) + " " + nthWord(2, s)).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;
                                }
                            }
                            if (!isExistInNames) {
                                if (firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))
                                    names.add(nthWord(2, s));
                                else
                                    names.add(nthWord(1, s) + " " + nthWord(2, s));
                                amounts.add(Double.valueOf(nthWord(4, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(6, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) > 7) {
                    boolean isExist = false;
                    SubjectListChildItem SLCI = new SubjectListChildItem();
                    outer1loop:
                    for (int i = 0; i < dbs.allSubjects().size(); i++) {
                        if (nthWord(2, s).matches(FIRST_BILL_WORDS[8]) || nthWord(2, s).matches(FIRST_BILL_WORDS[9]) || nthWord(2, s).matches(FIRST_BILL_WORDS[10]) || nthWord(2, s).matches(FIRST_BILL_WORDS[32]) || nthWord(2, s).matches(FIRST_BILL_WORDS[33]) || nthWord(2, s).matches(FIRST_BILL_WORDS[34]) || nthWord(2, s).matches(FIRST_BILL_WORDS[35])) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        } else if (nthWord(3, s).matches(FIRST_BILL_WORDS[8]) || nthWord(3, s).matches(FIRST_BILL_WORDS[9]) || nthWord(3, s).matches(FIRST_BILL_WORDS[10]) || nthWord(3, s).matches(FIRST_BILL_WORDS[32]) || nthWord(3, s).matches(FIRST_BILL_WORDS[33]) || nthWord(3, s).matches(FIRST_BILL_WORDS[34]) || nthWord(3, s).matches(FIRST_BILL_WORDS[35])) {
                            if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(1, s).toLowerCase(Locale.getDefault()) + " " + nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5])) && (nthWord(2, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }

                    }
                    double amount = 0.0;
                    double offer = 0.0;
                    double unit = 0.0;
                    String state = "";
                    if (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]) + 1))))
                        unit = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]) + 1))))
                        unit = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]) + 1))))
                        offer = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[35]) && s.substring(s.indexOf(FIRST_BILL_WORDS[35])).length() != FIRST_BILL_WORDS[35].length())
                        state = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]) + 1));
                    if (amount != 0.0 && unit != 0.0) {
                        if (isExist) {
                            addRowVoice(false, isExist, SLCI.getId(), SLCI.getName(), amount, offer, unit, unit * amount, state);
                            isOk = true;
                            break outerloop;
                        } else if ((s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) && (s.contains(FIRST_BILL_WORDS[32]) || s.contains(FIRST_BILL_WORDS[33]))) {
                            isOk = true;
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (nthWord(2, s).matches(FIRST_BILL_WORDS[8]) || nthWord(2, s).matches(FIRST_BILL_WORDS[9]) || nthWord(2, s).matches(FIRST_BILL_WORDS[10]) || nthWord(2, s).matches(FIRST_BILL_WORDS[32]) || nthWord(2, s).matches(FIRST_BILL_WORDS[33]) || nthWord(2, s).matches(FIRST_BILL_WORDS[34]) || nthWord(2, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(1, s)).toLowerCase(Locale.getDefault())))
                                            isExistInNames = true;
                                    }
                                } else if (nthWord(3, s).matches(FIRST_BILL_WORDS[8]) || nthWord(3, s).matches(FIRST_BILL_WORDS[9]) || nthWord(3, s).matches(FIRST_BILL_WORDS[10]) || nthWord(3, s).matches(FIRST_BILL_WORDS[32]) || nthWord(3, s).matches(FIRST_BILL_WORDS[33]) || nthWord(3, s).matches(FIRST_BILL_WORDS[34]) || nthWord(3, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(1, s) + " " + nthWord(2, s)).toLowerCase(Locale.getDefault())))
                                            isExistInNames = true;
                                    } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(2, s)).toLowerCase(Locale.getDefault())))
                                            isExistInNames = true;
                                    }
                                }
                            }
                            if (!isExistInNames) {
                                if (nthWord(2, s).matches(FIRST_BILL_WORDS[8]) || nthWord(2, s).matches(FIRST_BILL_WORDS[9]) || nthWord(2, s).matches(FIRST_BILL_WORDS[10]) || nthWord(2, s).matches(FIRST_BILL_WORDS[32]) || nthWord(2, s).matches(FIRST_BILL_WORDS[33]) || nthWord(2, s).matches(FIRST_BILL_WORDS[34]) || nthWord(2, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        names.add(nthWord(1, s));
                                    }
                                } else if (nthWord(3, s).matches(FIRST_BILL_WORDS[8]) || nthWord(3, s).matches(FIRST_BILL_WORDS[9]) || nthWord(3, s).matches(FIRST_BILL_WORDS[10]) || nthWord(3, s).matches(FIRST_BILL_WORDS[32]) || nthWord(3, s).matches(FIRST_BILL_WORDS[33]) || nthWord(3, s).matches(FIRST_BILL_WORDS[34]) || nthWord(3, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        names.add(nthWord(1, s) + " " + nthWord(2, s));
                                    } else if ((firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        names.add(nthWord(2, s));
                                    }
                                }
                                amounts.add(amount);
                                offers.add(offer);
                                units.add(unit);
                                states.add(state);
                            }
                        }
                    } else {
                        if (!isErrorVoiceExist) {
                            isErrorVoiceExist = true;
                            Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
                        }
                        isOk = true;
                    }
                }
            }
        }
        if (isSmartDetection) {
            if (names.size() == 1) {
                productUnits.add(units.get(0));
                int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                curSubject = String.valueOf(id_) + "-" + names.get(0);
                curAmount = String.valueOf(amounts.get(0));
                curOffer = String.valueOf(offers.get(0));
                curUnit = String.valueOf(units.get(0));
                curTotal = String.valueOf(amounts.get(0) * units.get(0));
                curStatement = String.valueOf(states.get(0));
                addRowNext();
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id_ + 1).commit();
                setTotal();
                productNames.add(curSubject);
                isOk = true;
            } else if (names.size() > 1) {
                createSuggestionsDialog(false, 1, 1, names, amounts, offers, units, states);
                isOk = true;
                isVoiceControlingEnabled = false;
            }
        }
        isErrorVoiceExist = false;
        return isOk;
    }

    private void addRowVoice(final boolean isChangeRow, final boolean isExist, final int id, final String name, final double amount, final double offer, final double unit, final double total, final String state) {
        boolean isOk = true;
        boolean isThereRow = false;
        if (!isChangeRow) {
            outer1loop:
            for (int k = 0; k < productNames.size(); k++) {
                if (productNames.get(k).toLowerCase(Locale.getDefault()).matches(String.valueOf(id) + "-" + name.toLowerCase(Locale.getDefault()))) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                    isThereRow = true;
                    break outer1loop;
                }
            }
        }
        if (!isThereRow) {
            if (isExist) {
                SubjectListChildItem SLCI = new SubjectListChildItem();
                outerloop:
                for (int i = 0; i < dbs.allSubjects().size(); i++) {
                    if (dbs.allSubjects().get(i).getId() == id) {
                        SLCI = dbs.allSubjects().get(i);
                        break outerloop;
                    }
                }
                if (basic != 1 && SLCI.getAmount() < amount) {
                    Toast.makeText(this, res.getString(R.string.error_lower_amount), Toast.LENGTH_LONG).show();
                    isOk = false;
                } else if (basic != 1 && SLCI.getAmountLock() > amount) {
                    Toast.makeText(this, res.getString(R.string.error_lower_amount_lock), Toast.LENGTH_LONG).show();
                    isOk = false;
                }
                if (basic != 1 && SLCI.getCost() > unit) {
                    Toast.makeText(this, res.getString(R.string.error_lower_net), Toast.LENGTH_LONG).show();
                } else if (basic != 1 && SLCI.getLock() > unit) {
                    Toast.makeText(this, res.getString(R.string.error_lower_lock), Toast.LENGTH_LONG).show();
                    isOk = false;
                }
                if (isOk && basic == 1 && SLCI.getCost() != unit) {
                    isOk = false;
                    final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                    alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                    alertDialog.setMessage(res.getString(R.string.alertCalcNewValue));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!isChangeRow)
                                        productUnits.add((Double.valueOf(unit) + dbs.allSubjects().get(b).getCost()) / 2);
                                    else
                                        productUnits.set(row - 1, (Double.valueOf(unit) + dbs.allSubjects().get(b).getCost()) / 2);
                                    curSubject = String.valueOf(id) + "-" + name;
                                    curAmount_ = amount;
                                    curOffer_ = offer;
                                    curUnit_ = unit;
                                    curTotal_ = total;
                                    curStatement = state;
                                    curAmount = String.valueOf(curAmount_);
                                    curOffer = String.valueOf(curOffer_);
                                    curUnit = String.valueOf(curUnit_);
                                    curTotal = String.valueOf(curTotal_);
                                    if (!isChangeRow)
                                        productNames.add(String.valueOf(id) + "-" + name);
                                    else
                                        productNames.set(row - 1, String.valueOf(id) + "-" + name);
                                    addRowNext();
                                    setTotal();
                                    if (basic == 1 && !isExist)
                                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id + 1).commit();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, res.getString(R.string.alertSaveAsNewPrice),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!isChangeRow)
                                        productUnits.add(Double.valueOf(unit));
                                    else
                                        productUnits.set(row - 1, Double.valueOf(unit));
                                    curSubject = String.valueOf(id) + "-" + name;
                                    curAmount_ = amount;
                                    curOffer_ = offer;
                                    curUnit_ = unit;
                                    curTotal_ = total;
                                    curStatement = state;
                                    curAmount = String.valueOf(curAmount_);
                                    curOffer = String.valueOf(curOffer_);
                                    curUnit = String.valueOf(curUnit_);
                                    curTotal = String.valueOf(curTotal_);
                                    if (!isChangeRow)
                                        productNames.add(String.valueOf(id) + "-" + name);
                                    else
                                        productNames.set(row - 1, String.valueOf(id) + "-" + name);
                                    addRowNext();
                                    setTotal();
                                    if (basic == 1 && !isExist)
                                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id + 1).commit();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                } else if (isOk) {
                    if (!isChangeRow)
                        productUnits.add(Double.valueOf(unit));
                    else
                        productUnits.set(row - 1, Double.valueOf(unit));
                    curSubject = String.valueOf(id) + "-" + name;
                    curAmount_ = amount;
                    curOffer_ = offer;
                    curUnit_ = unit;
                    curTotal_ = total;
                    curStatement = state;
                    curAmount = String.valueOf(curAmount_);
                    curOffer = String.valueOf(curOffer_);
                    curUnit = String.valueOf(curUnit_);
                    curTotal = String.valueOf(curTotal_);
                    if (!isChangeRow)
                        productNames.add(String.valueOf(id) + "-" + name);
                    else
                        productNames.set(row - 1, String.valueOf(id) + "-" + name);
                    addRowNext();
                    setTotal();
                    if (basic == 1 && !isExist)
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id + 1).commit();
                }
            } else {
                if (!isChangeRow)
                    productUnits.add(Double.valueOf(unit));
                else
                    productUnits.set(row - 1, Double.valueOf(unit));
                curSubject = String.valueOf(id) + "-" + name;
                curAmount_ = amount;
                curOffer_ = offer;
                curUnit_ = unit;
                curTotal_ = total;
                curStatement = state;
                curAmount = String.valueOf(curAmount_);
                curOffer = String.valueOf(curOffer_);
                curUnit = String.valueOf(curUnit_);
                curTotal = String.valueOf(curTotal_);
                if (!isChangeRow)
                    productNames.add(String.valueOf(id) + "-" + name);
                else
                    productNames.set(row - 1, String.valueOf(id) + "-" + name);
                addRowNext();
                setTotal();
                if (basic == 1 && !isExist)
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id + 1).commit();
            }
        }
    }

    private boolean checkSChangeRow(ArrayList<String> voiceResults) {
        boolean isOk = false;
        boolean isSmartDetection = false;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> amounts = new ArrayList<>();
        ArrayList<Double> offers = new ArrayList<>();
        ArrayList<Double> units = new ArrayList<>();
        ArrayList<String> states = new ArrayList<>();
        boolean isChange = true;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            Toast.makeText(BillActivity.this, s, Toast.LENGTH_LONG).show();
            if (countWords(s) >= 5 && (firstWord(s).matches(FIRST_BILL_WORDS[6]) || firstWord(s).matches(FIRST_BILL_WORDS[7])) && isNumeric(nthWord(2, s))) {
                row = Integer.valueOf(nthWord(2, s));
                isChange = true;
                if (countWords(s) == 5) {
                    if (isNumeric(nthWord(5, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if (nthWord(3, s).toLowerCase(Locale.getDefault()).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10]))) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(4, s).contains(FIRST_BILL_WORDS[32]) || nthWord(4, s).contains(FIRST_BILL_WORDS[33]))) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLast(), "");
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmountLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(5, s)) * SLCI.getAmount(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 6) {
                    if (isNumeric(nthWord(6, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault()) + " " + nthWord(4, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist && (nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10]))) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist && (nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33]))) {
                            if (basic != 1 && SLCI.getAmountLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLast(), "");
                            else if (basic != 1 && SLCI.getAmountLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmount(), "");
                            isOk = true;
                            break outerloop;
                        } else if (isExist) {
                            if (basic != 1 && SLCI.getLast() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), "");
                            else if (basic != 1 && SLCI.getLock() != 0)
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0, SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), "");
                            isOk = true;
                            break outerloop;
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 7) {
                    if (isNumeric(nthWord(5, s)) && isNumeric(nthWord(7, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(6, s).contains(FIRST_BILL_WORDS[32]) || nthWord(6, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0.0, Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)) * Double.valueOf(nthWord(7, s)), "");
                                isOk = true;
                            } else if ((nthWord(4, s).contains(FIRST_BILL_WORDS[8]) || nthWord(4, s).contains(FIRST_BILL_WORDS[9]) || nthWord(4, s).contains(FIRST_BILL_WORDS[10])) && nthWord(6, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getLast(), Double.valueOf(nthWord(5, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getLock(), Double.valueOf(nthWord(5, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(7, s)), SLCI.getCost(), Double.valueOf(nthWord(5, s)) * SLCI.getCost(), "");
                                isOk = true;
                            } else {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0.0, Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)) * Double.valueOf(nthWord(7, s)), "");
                            }
                            break outerloop;
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(3, s).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add(nthWord(3, s));
                                amounts.add(Double.valueOf(nthWord(5, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(7, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    }

                } else if (countWords(s) == 8) {
                    if (isNumeric(nthWord(6, s)) && isNumeric(nthWord(8, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault()) + " " + nthWord(4, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(7, s).contains(FIRST_BILL_WORDS[32]) || nthWord(7, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)) * Double.valueOf(nthWord(8, s)), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10])) && nthWord(7, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33])) && nthWord(7, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLast(), "");
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLock(), "");
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmount(), "");
                                isOk = true;
                                break outerloop;
                            } else {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(4, s)), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(4, s)) * Double.valueOf(nthWord(6, s)), "");
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                            addRowVoice(true, isExist, id_, nthWord(3, s) + " " + nthWord(4, s), Double.valueOf(nthWord(6, s)), 0.0, Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)) * Double.valueOf(nthWord(8, s)), "");
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(3, s) + " " + nthWord(4, s)).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {

                                names.add(nthWord(3, s) + " " + nthWord(4, s));
                                amounts.add(Double.valueOf(nthWord(6, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(8, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(6, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault()) + " " + nthWord(4, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10])) && nthWord(7, s).contains(FIRST_BILL_WORDS[35])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), nthWord(8, s));
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), nthWord(8, s));
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), nthWord(8, s));
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33])) && nthWord(7, s).contains(FIRST_BILL_WORDS[35])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLast(), nthWord(8, s));
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLock(), nthWord(8, s));
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), 0.0, Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmount(), nthWord(8, s));
                                isOk = true;
                                break outerloop;
                            } else {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), nthWord(8, s));
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), nthWord(8, s));
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), nthWord(8, s));
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            if (!isErrorVoiceExist) {
                                isErrorVoiceExist = true;
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) == 9) {
                    if (isNumeric(nthWord(5, s)) && isNumeric(nthWord(7, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if (isNumeric(nthWord(9, s)))
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), Double.valueOf(nthWord(9, s)), Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)) * Double.valueOf(nthWord(7, s)), "");
                            else
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(5, s)), 0.0, Double.valueOf(nthWord(7, s)), Double.valueOf(nthWord(5, s)) * Double.valueOf(nthWord(7, s)), nthWord(9, s));
                            isOk = true;
                            break outerloop;
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches(nthWord(3, s).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add(nthWord(3, s));
                                amounts.add(Double.valueOf(nthWord(5, s)));
                                units.add(Double.valueOf(nthWord(7, s)));
                                if (isNumeric(nthWord(9, s))) {
                                    offers.add(Double.valueOf(nthWord(9, s)));
                                    states.add("");
                                } else {
                                    offers.add(0.0);
                                    states.add(nthWord(9, s));
                                }
                            }
                            isOk = true;
                        }
                    } else if (isNumeric(nthWord(6, s)) && isNumeric(nthWord(8, s))) {
                        boolean isExist = false;
                        SubjectListChildItem SLCI = new SubjectListChildItem();
                        outer1loop:
                        for (int i = 0; i < dbs.allSubjects().size(); i++) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault()) + " " + nthWord(4, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }
                        if (isExist) {
                            if ((nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10])) && (nthWord(7, s).contains(FIRST_BILL_WORDS[32]) || nthWord(7, s).contains(FIRST_BILL_WORDS[33]))) {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)) * Double.valueOf(nthWord(8, s)), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(5, s).contains(FIRST_BILL_WORDS[8]) || nthWord(5, s).contains(FIRST_BILL_WORDS[9]) || nthWord(5, s).contains(FIRST_BILL_WORDS[10])) && nthWord(7, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getLast(), Double.valueOf(nthWord(6, s)) * SLCI.getLast(), "");
                                else if (basic != 1 && SLCI.getLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getLock(), Double.valueOf(nthWord(6, s)) * SLCI.getLock(), "");
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(8, s)), SLCI.getCost(), Double.valueOf(nthWord(6, s)) * SLCI.getCost(), "");
                                isOk = true;
                                break outerloop;
                            } else if ((nthWord(5, s).contains(FIRST_BILL_WORDS[32]) || nthWord(5, s).contains(FIRST_BILL_WORDS[33])) && nthWord(7, s).contains(FIRST_BILL_WORDS[34])) {
                                if (basic != 1 && SLCI.getAmountLast() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLast(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLast(), "");
                                else if (basic != 1 && SLCI.getAmountLock() != 0)
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmountLock(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmountLock(), "");
                                else
                                    addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), SLCI.getAmount(), Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)), Double.valueOf(nthWord(6, s)) * SLCI.getAmount(), "");
                                isOk = true;
                                break outerloop;
                            } else {
                                addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), Double.valueOf(nthWord(6, s)), 0.0, Double.valueOf(nthWord(8, s)), Double.valueOf(nthWord(6, s)) * Double.valueOf(nthWord(8, s)), "");
                                isOk = true;
                                break outerloop;
                            }
                        } else {
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(3, s) + " " + nthWord(4, s)).toLowerCase(Locale.getDefault())))
                                    isExistInNames = true;
                            }
                            if (!isExistInNames) {
                                names.add(nthWord(3, s) + " " + nthWord(4, s));
                                amounts.add(Double.valueOf(nthWord(6, s)));
                                offers.add(0.0);
                                units.add(Double.valueOf(nthWord(8, s)));
                                states.add("");
                            }
                            isOk = true;
                        }
                    }
                } else if (countWords(s) > 9) {
                    boolean isExist = false;
                    SubjectListChildItem SLCI = new SubjectListChildItem();
                    outer1loop:
                    for (int i = 0; i < dbs.allSubjects().size(); i++) {
                        if (nthWord(4, s).matches(FIRST_BILL_WORDS[8]) || nthWord(4, s).matches(FIRST_BILL_WORDS[9]) || nthWord(4, s).matches(FIRST_BILL_WORDS[10]) || nthWord(4, s).matches(FIRST_BILL_WORDS[32]) || nthWord(4, s).matches(FIRST_BILL_WORDS[33]) || nthWord(4, s).matches(FIRST_BILL_WORDS[34]) || nthWord(4, s).matches(FIRST_BILL_WORDS[35])) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        } else if (nthWord(5, s).matches(FIRST_BILL_WORDS[8]) || nthWord(5, s).matches(FIRST_BILL_WORDS[9]) || nthWord(5, s).matches(FIRST_BILL_WORDS[10]) || nthWord(5, s).matches(FIRST_BILL_WORDS[32]) || nthWord(5, s).matches(FIRST_BILL_WORDS[33]) || nthWord(5, s).matches(FIRST_BILL_WORDS[34]) || nthWord(5, s).matches(FIRST_BILL_WORDS[35])) {
                            if ((nthWord(3, s).toLowerCase(Locale.getDefault()) + " " + nthWord(4, s).toLowerCase(Locale.getDefault())).matches(dbs.allSubjects().get(i).getName().toLowerCase(Locale.getDefault()))) {
                                isExist = true;
                                SLCI = dbs.allSubjects().get(i);
                                break outer1loop;
                            }
                        }

                    }
                    double amount = 0.0;
                    double offer = 0.0;
                    double unit = 0.0;
                    String state = "";
                    if (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1))))
                        amount = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]) + 1))))
                        unit = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]) + 1)));
                    else if (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]) + 1))))
                        unit = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]) + 1))))
                        offer = Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]) + 1)));
                    if (s.contains(FIRST_BILL_WORDS[35]) && s.substring(s.indexOf(FIRST_BILL_WORDS[35])).length() != FIRST_BILL_WORDS[35].length())
                        state = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]) + 1));
                    if (amount != 0.0 && unit != 0.0) {
                        if (isExist) {
                            addRowVoice(true, isExist, SLCI.getId(), SLCI.getName(), amount, offer, unit, unit * amount, state);
                            isOk = true;
                            break outerloop;
                        } else if ((s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) && (s.contains(FIRST_BILL_WORDS[32]) || s.contains(FIRST_BILL_WORDS[33]))) {
                            isOk = true;
                            isSmartDetection = true;
                            boolean isExistInNames = false;
                            for (int p = 0; p < names.size(); p++) {
                                if (nthWord(4, s).matches(FIRST_BILL_WORDS[8]) || nthWord(4, s).matches(FIRST_BILL_WORDS[9]) || nthWord(4, s).matches(FIRST_BILL_WORDS[10]) || nthWord(4, s).matches(FIRST_BILL_WORDS[32]) || nthWord(4, s).matches(FIRST_BILL_WORDS[33]) || nthWord(4, s).matches(FIRST_BILL_WORDS[34]) || nthWord(4, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(3, s)).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;

                                } else if (nthWord(5, s).matches(FIRST_BILL_WORDS[8]) || nthWord(5, s).matches(FIRST_BILL_WORDS[9]) || nthWord(5, s).matches(FIRST_BILL_WORDS[10]) || nthWord(5, s).matches(FIRST_BILL_WORDS[32]) || nthWord(5, s).matches(FIRST_BILL_WORDS[33]) || nthWord(5, s).matches(FIRST_BILL_WORDS[34]) || nthWord(5, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (names.get(p).toLowerCase(Locale.getDefault()).matches((nthWord(3, s) + " " + nthWord(4, s)).toLowerCase(Locale.getDefault())))
                                        isExistInNames = true;
                                }
                            }
                            if (!isExistInNames) {
                                if (nthWord(4, s).matches(FIRST_BILL_WORDS[8]) || nthWord(4, s).matches(FIRST_BILL_WORDS[9]) || nthWord(4, s).matches(FIRST_BILL_WORDS[10]) || nthWord(4, s).matches(FIRST_BILL_WORDS[32]) || nthWord(4, s).matches(FIRST_BILL_WORDS[33]) || nthWord(4, s).matches(FIRST_BILL_WORDS[34]) || nthWord(4, s).matches(FIRST_BILL_WORDS[35])) {
                                    if (!(firstWord(s).matches(FIRST_BILL_WORDS[4]) || firstWord(s).matches(FIRST_BILL_WORDS[5]))) {
                                        names.add(nthWord(3, s));
                                    }
                                } else if (nthWord(5, s).matches(FIRST_BILL_WORDS[8]) || nthWord(5, s).matches(FIRST_BILL_WORDS[9]) || nthWord(5, s).matches(FIRST_BILL_WORDS[10]) || nthWord(5, s).matches(FIRST_BILL_WORDS[32]) || nthWord(5, s).matches(FIRST_BILL_WORDS[33]) || nthWord(5, s).matches(FIRST_BILL_WORDS[34]) || nthWord(5, s).matches(FIRST_BILL_WORDS[35])) {
                                    names.add(nthWord(3, s) + " " + nthWord(4, s));
                                }
                                amounts.add(amount);
                                offers.add(offer);
                                units.add(unit);
                                states.add(state);
                            }
                        }
                    } else {
                        if (!isErrorVoiceExist) {
                            isErrorVoiceExist = true;
                            Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
                        }
                        isOk = true;
                    }
                }
            } else if (countWords(s) >= 5 && (firstWord(s).matches(FIRST_BILL_WORDS[6]) || firstWord(s).matches(FIRST_BILL_WORDS[7]))) {
                isChange = false;
            }
        }
        if (!isChange) {
            Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
            isOk = true;
        }
        if (isSmartDetection) {
            if (names.size() == 1) {
                productUnits.set(row - 1, units.get(0));
                int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                curSubject = String.valueOf(id_) + "-" + names.get(0);
                curAmount = String.valueOf(amounts.get(0));
                curOffer = String.valueOf(offers.get(0));
                curUnit = String.valueOf(units.get(0));
                curTotal = String.valueOf(amounts.get(0) * units.get(0));
                curStatement = String.valueOf(states.get(0));
                addRowNext();
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id_ + 1).commit();
                setTotal();
                productNames.set(row - 1, curSubject);
                isOk = true;
            } else if (names.size() > 1) {
                createSuggestionsDialog(true, row, 1, names, amounts, offers, units, states);
                isOk = true;
                isVoiceControlingEnabled = false;
            }
        }
        isErrorVoiceExist = false;
        return isOk;
    }

    private boolean checkSFrom(ArrayList<String> voiceResults) {
        boolean isOk = false;
        boolean isSmartDetection = false;
        ArrayList<String> names = new ArrayList<>();
        outerloop:
        for (int p = 0; p < voiceResults.size(); p++) {
            String s = voiceResults.get(p).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            String supp = "";
            if (countWords(s) >= 2 && (s.contains(" " + FIRST_BILL_WORDS[0] + " ") || s.contains(" " + FIRST_BILL_WORDS[1] + " "))) {
                if (s.contains(FIRST_BILL_WORDS[0]))
                    supp = FIRST_BILL_WORDS[0];
                else
                    supp = FIRST_BILL_WORDS[1];
                if (basic == 1) {
                    boolean isExist = false;
                    outerloop1:
                    for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
                        if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).contains(dbsu.allSuppliers().get(k).getSupplier().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault()))) {
                            edit_from.setText(secondWord(s.substring(s.indexOf(supp))));
                            isExist = true;
                            isOk = true;
                            break outerloop;
                        }
                    }
                    if (!isExist) {
                        boolean isItemEx = false;
                        for (int j = 0; j < names.size(); j++) {
                            if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
                                isItemEx = true;
                        }
                        if (!isItemEx)
                            names.add(secondWord(s.substring(s.indexOf(supp))));
                    }
                    isOk = true;
                    isSmartDetection = true;
                    break outerloop;
                } else if (basic == 2) {
                    boolean isPointExist = false;
                    for (int k = 0; k < point.length; k++) {
                        if (secondWord(s.substring(s.indexOf(supp))).contains(point[k])) {
                            spin_point.setSelection(k);
                            isPointExist = true;
                        }
                    }
                    if (!isPointExist) {
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_no_exist_point), Toast.LENGTH_LONG).show();
                        isWordsNotCompleted = true;
                        isOk = true;
                    } else {
                        isOk = true;
                        break outerloop;
                    }
                } else {
                    boolean isEx = false;
                    outerloop1:
                    for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
                        if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).contains(dbsu.allSuppliers().get(k).getSupplier().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault()))) {
                            edit_from_t.setText(secondWord(s.substring(s.indexOf(supp))));
                            isEx = true;
                            isOk = true;
                            break outerloop;
                        }
                    }
                    if (!isEx) {

                        boolean isItemEx = false;
                        for (int j = 0; j < names.size(); j++) {
                            if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
                                isItemEx = true;
                        }
                        if (!isItemEx)
                            names.add(secondWord(s.substring(s.indexOf(supp))));
                        isOk = true;
                        isSmartDetection = true;
                        break outerloop;
                    }
                }
            }
        }
        if (isSmartDetection) {
            if (names.size() == 1) {
                if (basic == 1)
                    edit_from.setText(names.get(0));
                else
                    edit_from_t.setText(names.get(0));
            } else if (names.size() > 1) {
                createSuggestionsDialog(false, 1, 2, names, null, null, null, null);
                isVoiceControlingEnabled = false;
            }
        }

        return isOk;
    }

    private boolean checkSTo(ArrayList<String> voiceResults) {
        boolean isOk = false;
        boolean isSmartDetection = false;
        ArrayList<String> names = new ArrayList<>();
        outerloop:
        for (int p = 0; p < voiceResults.size(); p++) {
            String s = voiceResults.get(p).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            String cus = "";
            if (countWords(s) >= 2 && (s.contains(" " + FIRST_BILL_WORDS[2] + " ") || s.contains(" " + FIRST_BILL_WORDS[3] + " "))) {
                if (s.contains(FIRST_BILL_WORDS[2]))
                    cus = FIRST_BILL_WORDS[2];
                else
                    cus = FIRST_BILL_WORDS[3];
                if (basic == 2) {
                    boolean isEx = false;
                    for (int k = 0; k < dbc.allCustomers().size(); k++) {
                        if (dbc.allCustomers().get(k).getCustomer().toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()))) {
                            edit_to.setText(secondWord(s.substring(s.indexOf(cus))));
                            isEx = true;
                            isOk = true;
                            break outerloop;
                        }
                    }
                    if (!isEx) {
                        boolean isItemEx = false;
                        for (int j = 0; j < names.size(); j++) {
                            if (secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())))
                                isItemEx = true;
                        }
                        if (!isItemEx)
                            names.add(secondWord(s.substring(s.indexOf(cus))));
                        isOk = true;
                        isSmartDetection = true;
                        break outerloop;
                    }
                } else if (basic == 1) {
                    boolean isPointExist = false;
                    for (int k = 0; k < point.length; k++) {
                        if (secondWord(s.substring(s.indexOf(cus))).contains(point[k])) {
                            spin_point_.setSelection(k);
                            isPointExist = true;
                        }
                    }
                    if (!isPointExist) {
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_no_exist_point), Toast.LENGTH_LONG).show();
                        isWordsNotCompleted = true;
                        isOk = true;
                    } else {
                        isOk = true;
                        break outerloop;
                    }
                } else {
                    boolean isEx = false;
                    for (int k = 0; k < dbc.allCustomers().size(); k++) {
                        if (dbc.allCustomers().get(k).getCustomer().toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()))) {
                            edit_to_t.setText(secondWord(s.substring(s.indexOf(cus))));
                            isEx = true;
                            isOk = true;
                            break outerloop;
                        }
                    }
                    if (!isEx) {
                        boolean isItemEx = false;
                        for (int j = 0; j < names.size(); j++) {
                            if (secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())))
                                isItemEx = true;
                        }
                        if (!isItemEx)
                            names.add(secondWord(s.substring(s.indexOf(cus))));
                        isSmartDetection = true;
                        isOk = true;
                        break outerloop;
                    }
                }
            }
        }
        if (isSmartDetection) {
            if (names.size() == 1) {
                if (basic == 2)
                    edit_to.setText(names.get(0));
                else
                    edit_to_t.setText(names.get(0));
            } else if (names.size() > 1) {
                createSuggestionsDialog(false, 1, 3, names, null, null, null, null);
                isVoiceControlingEnabled = false;
            }
        }
        return isOk;
    }

    private boolean checkSDate(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("/", " ").replaceAll("-", " ").replaceAll(FIRST_BILL_WORDS[41], "1").replaceAll(FIRST_BILL_WORDS[42], "1").replaceAll(FIRST_BILL_WORDS[43], "2").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + "2").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + "2").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + "2").replaceAll(FIRST_BILL_WORDS[45], "3").replaceAll(FIRST_BILL_WORDS[46], "3").replaceAll(FIRST_BILL_WORDS[47], "4").replaceAll(FIRST_BILL_WORDS[48], "4").replaceAll(FIRST_BILL_WORDS[49], "5").replaceAll(FIRST_BILL_WORDS[50], "5").replaceAll(FIRST_BILL_WORDS[51], "6").replaceAll(FIRST_BILL_WORDS[52], "6").replaceAll(FIRST_BILL_WORDS[53], "7").replaceAll(FIRST_BILL_WORDS[54], "7").replaceAll(FIRST_BILL_WORDS[55], "8").replaceAll(FIRST_BILL_WORDS[56], "8").replaceAll(FIRST_BILL_WORDS[57], "8").replaceAll(FIRST_BILL_WORDS[58], "9").replaceAll(FIRST_BILL_WORDS[59], "9").replaceAll(FIRST_BILL_WORDS[60], "10").replaceAll(FIRST_BILL_WORDS[61], "10");
            if (countWords(s) >= 4 && s.contains(FIRST_BILL_WORDS[14]) && isNumeric(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1))) && isNumeric(nthWord(3, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1))) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1)))) {
                year = Integer.valueOf(nthWord(2, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1)));
                month = Integer.valueOf(nthWord(3, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1)));
                day = Integer.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[14]) + 1)));
                txt_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                isOk = true;
                break outerloop;
            } else if (countWords(s) >= 5 && s.contains(FIRST_BILL_WORDS[7]) && !isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1))) && isNumeric(nthWord(3, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1))) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1))) && isNumeric(nthWord(5, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1)))) {
                year = Integer.valueOf(nthWord(3, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1)));
                month = Integer.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1)));
                day = Integer.valueOf(nthWord(5, s.substring(s.indexOf(FIRST_BILL_WORDS[7]) + 1)));
                txt_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                isOk = true;
                break outerloop;
            } else if (s.contains(FIRST_BILL_WORDS[14])) {
                if (!isErrorVoiceExist) {
                    isErrorVoiceExist = true;
                    Toast.makeText(this, res.getString(R.string.error_voice_change_date), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, res.getString(R.string.error_voice_change_date_), Toast.LENGTH_LONG).show();
                }
                isOk = true;
            }
        }
        isErrorVoiceExist = false;
        return isOk;
    }

    private boolean checkSDiscount(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if (countWords(s) >= 2 && s.contains(FIRST_BILL_WORDS[26]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[26]) + 1)))) {
                edit_discount.setText(String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[26]) + 1)))));
                isOk = true;
                break outerloop;
            } else if (s.contains(" " + FIRST_BILL_WORDS[26] + " ")) {
                Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_discount_correct), Toast.LENGTH_LONG).show();
                isOk = true;
            }
        }
        return isOk;
    }

    private boolean checkSAddition(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll("أ", "ا").replaceAll("إ", "ا").replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if (countWords(s) >= 2 && s.contains(FIRST_BILL_WORDS[27]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[27]) + 1)))) {
                edit_addition.setText(String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[27]) + 1)))));
                isOk = true;
                break outerloop;
            } else if (s.contains(" " + FIRST_BILL_WORDS[27] + " ")) {
                Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_addition_correct), Toast.LENGTH_LONG).show();
                isOk = true;
            }
        }
        return isOk;
    }

    private boolean checkSCategory(ArrayList<String> voiceResults) {
        boolean isOk = false;
        boolean isSmartDetection = false;
        ArrayList<String> names = new ArrayList<>();
        outerloop:
        for (int p = 0; p < voiceResults.size(); p++) {
            String s = voiceResults.get(p).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if (countWords(s) >= 2 && s.contains(FIRST_BILL_WORDS[36]) && s.substring(s.indexOf(FIRST_BILL_WORDS[26]) + 1).contains(" ")) {
                boolean isEx = false;
                for (int k = 0; k < dbs.allFolders().size(); k++) {
                    if (dbs.allFolders().get(k).toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]) + 1)).toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا"))) {
                        category.setText(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36])+1)));
                        isEx = true;
                        isOk = true;
                        break outerloop;
                    }
                }
                if (!isEx) {
                        boolean isItemEx = false;
                        for (int j = 0; j < names.size(); j++) {
                            if (secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36])+1)).toLowerCase(Locale.getDefault()).matches(names.get(j)))
                                isItemEx = true;
                        }
                        if (!isItemEx)
                            names.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36])+1)));
                    isOk = true;
                    break outerloop;
                }
            } else if (s.contains(" " + FIRST_BILL_WORDS[36] + " ")) {
                Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_category_correct), Toast.LENGTH_LONG).show();
                isOk = true;
            }
        }
        if(isSmartDetection) {
            if (names.size() == 1)
                category.setText(names.get(0));
            else if (names.size() > 1) {
                createSuggestionsDialog(false,1,4, names, null, null, null, null);
                isVoiceControlingEnabled = false;
            }
        }
        return isOk;
    }

    private boolean checkSBillType(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if(countWords(s) >= 2 && s.contains(res.getString(R.string.drawer_purchases).toLowerCase(Locale.getDefault()))){
                spin_basic.setSelection(0);
                isOk = true;
                break outerloop;
            }else if(countWords(s) >= 2 && s.contains(res.getString(R.string.drawer_sales).toLowerCase(Locale.getDefault()))){
                spin_basic.setSelection(1);
                isOk = true;
                break outerloop;
            }else if(countWords(s) >= 2 && s.contains(res.getString(R.string.drawer_temporary).toLowerCase(Locale.getDefault()))){
                spin_basic.setSelection(2);
                isOk = true;
                break outerloop;
            }
        }
        return isOk;
    }

    private boolean checkSCurrency(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if(countWords(s) >= 2){
                if (s.contains(FIRST_BILL_WORDS[23])) {
                    spin_currency.setSelection(0);
                    isOk = true;
                    break outerloop;
                } else if (s.contains(FIRST_BILL_WORDS[24]) || s.contains(FIRST_BILL_WORDS[25])) {
                    spin_currency.setSelection(1);
                    isOk = true;
                    break outerloop;
                }
            }
        }
        return isOk;
    }

    private boolean checkSExitBill(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if(countWords(s)>=2){
    if (s.contains(FIRST_BILL_WORDS[11]) || s.contains(FIRST_BILL_WORDS[12]) || s.contains(FIRST_BILL_WORDS[13])) {
        onBackPressed();
        isOk = true;
        isVoiceControlingEnabled = false;
        break outerloop;
    }
}
        }
        return isOk;
    }

    private boolean checkSStopVoice(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if(countWords(s)>=2){
    if (s.contains(FIRST_BILL_WORDS[28])) {
        isVoiceControlingEnabled = true;
        voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_f_));
        pref.edit().putBoolean("isBillVoiceControlEnable", false).commit();
        isVoiceControlingEnabled = false;
        isOk = true;
        break outerloop;
    }
}
        }
        return isOk;
    }

    private boolean checkSSave(ArrayList<String> voiceResults) {
        boolean isOk = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k).replaceAll(" point ", ".").replaceAll(" a ", " ").replaceAll("-", "").replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
            if (countWords(s) >= 2 &&(s.contains(FIRST_BILL_WORDS[16]) || s.contains(FIRST_BILL_WORDS[17]))) {
                boolean isThereWrong = false;
                if (basic == 1) {
                    if (!edit_from.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                        isThereWrong = true;
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_from.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_from.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                } else if (basic == 2) {
                    if (!edit_to.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                        isThereWrong = true;
                        if (pref.getString("Language", "arabic").matches("arabic"))
                            edit_to.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                        else
                            edit_to.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                    }
                }
                if (!isThereWrong) {
                    boolean isThereSupCus = false;
                    if (basic == 1) {
                        for (int i = 0; i < dbsu.allSuppliersNames().size(); i++) {
                            if (dbsu.allSuppliersNames().get(i).matches(edit_from.getText().toString()))
                                isThereSupCus = true;
                        }
                    } else if (basic == 2) {
                        for (int i = 0; i < dbc.allCustomersNames().size(); i++) {
                            if (dbc.allCustomersNames().get(i).matches(edit_to.getText().toString()))
                                isThereSupCus = true;
                        }
                    }
                    if (isThereSupCus || basic == 3) {
                        if (bun.getBoolean("isView", false))
                            createBillNameDialog(spinBasicSelected + 1, true);
                        else
                            createBillNameDialog(spinBasicSelected + 1, false);
                    } else {
                        if (basic == 1) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                            alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                            alertDialog.setMessage(res.getString(R.string.alertNewSupplierMessage));
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSupplierColumn", 0);
                                            dbsu.addSupplier(new SupplierListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_from.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSupplierColumn", column + 1).commit();
                                            if (bun.getBoolean("isView", false))
                                                createBillNameDialog(spinBasicSelected + 1, true);
                                            else
                                                createBillNameDialog(spinBasicSelected + 1, false);
                                        }
                                    });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                    if (bun.getBoolean("isView", false))
                                        createBillNameDialog(spinBasicSelected + 1, true);
                                    else
                                        createBillNameDialog(spinBasicSelected + 1, false);
                                }
                            });
                            alertDialog.show();
                        } else if (basic == 2) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                            alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                            alertDialog.setMessage(res.getString(R.string.alertNewCustomerMessage));
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentCustomerColumn", 0);
                                            dbc.addCustomer(new CustomerListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_to.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentCustomerColumn", column + 1).commit();
                                            if (bun.getBoolean("isView", false))
                                                createBillNameDialog(spinBasicSelected + 1, true);
                                            else
                                                createBillNameDialog(spinBasicSelected + 1, false);
                                        }
                                    });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                    if (bun.getBoolean("isView", false))
                                        createBillNameDialog(spinBasicSelected + 1, true);
                                    else
                                        createBillNameDialog(spinBasicSelected + 1, false);
                                }
                            });
                            alertDialog.show();
                        }
                    }

                }
                isOk = true;
                break outerloop;
            }
        }
        return isOk;
    }

    /**
     * private boolean checkSmartSentences(ArrayList<String> a) {
     * boolean isMatches = false;
     * ArrayList<String> names = new ArrayList<>();
     * ArrayList<Double> amounts = new ArrayList<>();
     * ArrayList<Double> offers = new ArrayList<>();
     * ArrayList<Double> units = new ArrayList<>();
     * ArrayList<String> states = new ArrayList<>();
     * for (int i = 0; i < a.size(); i++) {
     * String s = a.get(i);
     * if ((s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) && (s.contains(FIRST_BILL_WORDS[32]) || s.contains(FIRST_BILL_WORDS[33]))) {
     * boolean isOk = true;
     * for (int j = 0; j < names.size(); j++) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * }
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10]))
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10]))
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * <p>
     * }
     * }
     * if (isOk) {
     * if (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))) {
     * units.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))) {
     * units.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])));
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])));
     * } else {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10])));
     * }
     * if (s.contains(FIRST_BILL_WORDS[34])) {
     * try {
     * offers.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", "")));
     * } catch (Exception e) {
     * offers.add(0.0);
     * }
     * } else
     * offers.add(0.0);
     * if (s.contains(FIRST_BILL_WORDS[35]))
     * states.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]))));
     * else
     * states.add("");
     * }
     * } else if (countWords(s) >= 4 && (s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10]))) {
     * boolean isOk = true;
     * for (int j = 0; j < names.size(); j++) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10]))
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10]))
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else {
     * if (s.contains(FIRST_BILL_WORDS[8])) {
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[8])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[9])) {
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[9])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * } else if (s.contains(FIRST_BILL_WORDS[10]))
     * if (s.substring(0, s.indexOf(FIRST_BILL_WORDS[10])).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))).matches(String.valueOf(amounts.get(j))) && (isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", "")) && String.valueOf(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))))
     * isOk = false;
     * <p>
     * }
     * }
     * if (isOk) {
     * if (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))) {
     * amounts.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "")));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", "")))
     * units.add(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", "")));
     * else if (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", "")))
     * units.add(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", "")));
     * else if (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", "")))
     * units.add(Double.valueOf(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", "")));
     * else
     * isOk = false;
     * }
     * if (isOk) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])));
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(FIRST_BILL_WORDS[10])));
     * } else {
     * if (s.contains(FIRST_BILL_WORDS[8]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8])));
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9])));
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * names.add(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10])));
     * }
     * if (s.contains(FIRST_BILL_WORDS[34])) {
     * try {
     * offers.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", "")));
     * } catch (Exception e) {
     * offers.add(0.0);
     * }
     * } else
     * offers.add(0.0);
     * if (s.contains(FIRST_BILL_WORDS[35]))
     * states.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]))));
     * else
     * states.add("");
     * }
     * } else if (countWords(s) >= 4 && (s.contains(FIRST_BILL_WORDS[32]) || s.contains(FIRST_BILL_WORDS[33]))) {
     * boolean isOk = true;
     * String amountn = s.substring(s.substring(0, s.substring(0, s.indexOf(FIRST_BILL_WORDS[32]) - 2).lastIndexOf(" ") - 1).lastIndexOf(" "), s.substring(0, s.indexOf(FIRST_BILL_WORDS[32]) - 2).lastIndexOf(" ")).replaceAll(" ", "");
     * String amount = s.substring(s.substring(0, s.indexOf(FIRST_BILL_WORDS[32]) - 3).lastIndexOf(" "), s.substring(0, s.indexOf(FIRST_BILL_WORDS[32])).lastIndexOf(" ")).replaceAll(" ", "");
     * for (int j = 0; j < names.size(); j++) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * } else {
     * if (s.substring(0, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && ((s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j)))) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")) && String.valueOf(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))).matches(String.valueOf(units.get(j))))))
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (isNumeric(amount)) {
     * amounts.add(Double.valueOf(amount));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", ""))) {
     * units.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")));
     * } else if (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", ""))) {
     * units.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(amountn)));
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(amountn)));
     * } else {
     * names.add(s.substring(0, s.indexOf(amountn)));
     * }
     * if (s.contains(FIRST_BILL_WORDS[34])) {
     * try {
     * offers.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", "")));
     * } catch (Exception e) {
     * offers.add(0.0);
     * }
     * } else
     * offers.add(0.0);
     * if (s.contains(FIRST_BILL_WORDS[35]))
     * states.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]))));
     * else
     * states.add("");
     * }
     * } else if (countWords(s) >= 4) {
     * boolean isOk = true;
     * String words[] = s.split("\\s+");
     * for (int p = 0; p < words.length; p++) {
     * words[p] = words[p].replaceAll("[^\\w]", "");
     * }
     * String amountn = "";
     * String amount = "";
     * String unitn = "";
     * String unit = "";
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (isNumeric(words[3].replaceAll(" ", ""))) {
     * isOk = true;
     * amountn = words[2].replaceAll(" ", "");
     * amount = words[3].replaceAll(" ", "");
     * if (isNumeric(words[5].replaceAll(" ", ""))) {
     * unitn = words[4].replaceAll(" ", "");
     * unit = words[5].replaceAll(" ", "");
     * } else {
     * isOk = false;
     * }
     * } else if (isNumeric(words[4].replaceAll(" ", ""))) {
     * isOk = true;
     * amountn = words[3].replaceAll(" ", "");
     * amount = words[4].replaceAll(" ", "");
     * if (isNumeric(words[6].replaceAll(" ", ""))) {
     * unitn = words[5].replaceAll(" ", "");
     * unit = words[6].replaceAll(" ", "");
     * } else {
     * isOk = false;
     * }
     * } else {
     * isOk = false;
     * }
     * } else {
     * if (isNumeric(words[2].replaceAll(" ", ""))) {
     * isOk = true;
     * amountn = words[1].replaceAll(" ", "");
     * amount = words[2].replaceAll(" ", "");
     * if (isNumeric(words[4].replaceAll(" ", ""))) {
     * unitn = words[3].replaceAll(" ", "");
     * unit = words[4].replaceAll(" ", "");
     * } else {
     * isOk = false;
     * }
     * } else if (isNumeric(words[3].replaceAll(" ", ""))) {
     * isOk = true;
     * amountn = words[2].replaceAll(" ", "");
     * amount = words[3].replaceAll(" ", "");
     * if (isNumeric(words[5].replaceAll(" ", ""))) {
     * unitn = words[4].replaceAll(" ", "");
     * unit = words[3].replaceAll(" ", "");
     * } else {
     * isOk = false;
     * }
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * for (int j = 0; j < names.size(); j++) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && isNumeric(unit) && String.valueOf(Double.valueOf(unit)).matches(String.valueOf(units.get(j))))
     * isOk = false;
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && isNumeric(unit) && String.valueOf(Double.valueOf(unit)).matches(String.valueOf(units.get(j))))
     * isOk = false;
     * } else {
     * if (s.substring(0, s.indexOf(amountn)).toLowerCase(Locale.getDefault()).matches(names.get(j).toLowerCase(Locale.getDefault())) && isNumeric(amount) && String.valueOf(Double.valueOf(amount)).matches(String.valueOf(amounts.get(j))) && isNumeric(unit) && String.valueOf(Double.valueOf(unit)).matches(String.valueOf(units.get(j))))
     * isOk = false;
     * }
     * }
     * }
     * if (isOk) {
     * if (isNumeric(amount)) {
     * amounts.add(Double.valueOf(amount));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (isNumeric(unit)) {
     * units.add(Double.valueOf(unit));
     * } else {
     * isOk = false;
     * }
     * }
     * if (isOk) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4])) {
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[4]) + FIRST_BILL_WORDS[4].length() + 1, s.indexOf(amountn)));
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * names.add(s.substring(s.indexOf(FIRST_BILL_WORDS[5]) + FIRST_BILL_WORDS[5].length() + 1, s.indexOf(amountn)));
     * } else {
     * names.add(s.substring(0, s.indexOf(amountn)));
     * }
     * if (s.contains(FIRST_BILL_WORDS[34])) {
     * try {
     * offers.add(Double.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", "")));
     * } catch (Exception e) {
     * offers.add(0.0);
     * }
     * } else
     * offers.add(0.0);
     * if (s.contains(FIRST_BILL_WORDS[35]))
     * states.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]))));
     * else
     * states.add("");
     * }
     * }
     * }
     * if (names.size() == 1) {
     * int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
     * curSubject = String.valueOf(id_) + "-" + names.get(0);
     * curAmount = String.valueOf(amounts.get(0));
     * curOffer = String.valueOf(offers.get(0));
     * curUnit = String.valueOf(units.get(0));
     * curTotal = String.valueOf(amounts.get(0) * units.get(0));
     * curStatement = String.valueOf(states.get(0));
     * addRowNext();
     * getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id_ + 1).commit();
     * setTotal();
     * productNames.add(curSubject);
     * isMatches = true;
     * } else if (names.size() > 1) {
     * createSuggestionsDialog(1, names, amounts, offers, units, states);
     * isMatches = true;
     * isVoiceControlingEnabled = false;
     * }
     * return isMatches;
     * }
     **/

    private void createSuggestionsDialog(final boolean isChangeRow, final int row, final int type, final ArrayList<String> names, final ArrayList<Double> amounts, final ArrayList<Double> offers, final ArrayList<Double> units, final ArrayList<String> states) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_voice_suggestions);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        titleSugg = (TextView) dialog.findViewById(R.id.title_voice_sugg);
        listSugg = (ListView) dialog.findViewById(R.id.list_voice_sugg);
        voiceSuggestionListItems = new ArrayList<>();
        isSuggVoiceControlingEnabled = true;
        if (type == 1) {
            titleSugg.setText(res.getString(R.string.dialog_title_sugg_1));
            for (int i = 0; i < names.size(); i++) {
                VoiceSuggestionListItem item = new VoiceSuggestionListItem(i + 1, res.getString(R.string.subject) + " : " + names.get(i)
                        + '\n' + res.getString(R.string.amount) + " : " + String.valueOf(amounts.get(i))
                        + '\n' + res.getString(R.string.offer) + " : " + String.valueOf(offers.get(i))
                        + '\n' + res.getString(R.string.unitprice) + " : " + String.valueOf(units.get(i))
                        + '\n' + res.getString(R.string.statement) + " : " + units.get(i));
                voiceSuggestionListItems.add(item);
            }
        } else if (type == 2) {
            titleSugg.setText(res.getString(R.string.dialog_title_sugg_2));
            for (int i = 0; i < names.size(); i++) {
                VoiceSuggestionListItem item = new VoiceSuggestionListItem(i + 1, names.get(i));
                voiceSuggestionListItems.add(item);
            }
        } else if (type == 3) {
            titleSugg.setText(res.getString(R.string.dialog_title_sugg_3));
            for (int i = 0; i < names.size(); i++) {
                VoiceSuggestionListItem item = new VoiceSuggestionListItem(i + 1, names.get(i));
                voiceSuggestionListItems.add(item);
            }
        } else if (type == 4) {
            titleSugg.setText(res.getString(R.string.dialog_title_sugg_4));
            for (int i = 0; i < names.size(); i++) {
                VoiceSuggestionListItem item = new VoiceSuggestionListItem(i + 1, names.get(i));
                voiceSuggestionListItems.add(item);
            }
        }
        customVoiceSuggListAdapter = new CustomVoiceSuggListAdapter(BillActivity.this, voiceSuggestionListItems);
        listSugg.setAdapter(customVoiceSuggListAdapter);
        listSugg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isSuggVoiceControlingEnabled = false;
                if (type == 1) {
                    int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                    if (!isChangeRow)
                        productUnits.add(units.get(i));
                    else
                        productUnits.set(row - 1, units.get(i));
                    curSubject = String.valueOf(id_) + "-" + names.get(i);
                    curAmount = String.valueOf(amounts.get(i));
                    curOffer = String.valueOf(offers.get(i));
                    curUnit = String.valueOf(units.get(i));
                    curTotal = String.valueOf(amounts.get(i) * units.get(i));
                    curStatement = states.get(i);
                    addRowNext();
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id_ + 1).commit();
                    setTotal();
                    if (!isChangeRow)
                        productNames.add(curSubject);
                    else
                        productNames.set(row - 1, curSubject);
                } else if (type == 2) {
                    if (basic == 1)
                        edit_from.setText(names.get(i));
                    else if (basic == 3)
                        edit_from_t.setText(names.get(i));
                } else if (type == 3) {
                    if (basic == 2)
                        edit_to.setText(names.get(i));
                    else if (basic == 3)
                        edit_to_t.setText(names.get(i));
                } else if (type == 4) {
                    category.setText(names.get(i));
                }
                dialog.cancel();
                isVoiceControlingEnabled = true;
                billVoice();
            }
        });
        suggestionSmartBillVoice(isChangeRow, row, type, names, amounts, offers, units, states, dialog);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                isSuggVoiceControlingEnabled = false;
                isVoiceControlingEnabled = true;
                billVoice();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void suggestionSmartBillVoice(final boolean isChangeRow, final int row, final int type, final ArrayList<String> names, final ArrayList<Double> amounts, final ArrayList<Double> offers, final ArrayList<Double> units, final ArrayList<String> states, final Dialog dialog) {
        if (isSuggVoiceControlingEnabled) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    "com.domain.app");

            SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(BillActivity.this);
            RecognitionListener listener = new RecognitionListener() {
                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> voiceResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (voiceResults == null) {
                        Toast.makeText(BillActivity.this, res.getString(R.string.error_no_voice_results), Toast.LENGTH_LONG).show();
                    } else {
                        boolean isOk = false;
                        boolean isNumberExist = true;
                        int number = 0;
                        for (int i = 0; i < voiceResults.size(); i++) {
                            String s = voiceResults.get(i).replaceAll(FIRST_BILL_WORDS[41], " 1 ").replaceAll(FIRST_BILL_WORDS[42], " 1 ").replaceAll(FIRST_BILL_WORDS[43], " 2 ").replaceAll(FIRST_BILL_WORDS[8] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[8] + " 2 ").replaceAll(FIRST_BILL_WORDS[9] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[9] + " 2 ").replaceAll(FIRST_BILL_WORDS[10] + FIRST_BILL_WORDS[44], FIRST_BILL_WORDS[10] + " 2 ").replaceAll(FIRST_BILL_WORDS[45], " 3 ").replaceAll(FIRST_BILL_WORDS[46], " 3 ").replaceAll(FIRST_BILL_WORDS[47], " 4 ").replaceAll(FIRST_BILL_WORDS[48], " 4 ").replaceAll(FIRST_BILL_WORDS[49], " 5 ").replaceAll(FIRST_BILL_WORDS[50], " 5 ").replaceAll(FIRST_BILL_WORDS[51], " 6 ").replaceAll(FIRST_BILL_WORDS[52], " 6 ").replaceAll(FIRST_BILL_WORDS[53], " 7 ").replaceAll(FIRST_BILL_WORDS[54], " 7 ").replaceAll(FIRST_BILL_WORDS[55], " 8 ").replaceAll(FIRST_BILL_WORDS[56], " 8 ").replaceAll(FIRST_BILL_WORDS[57], " 8 ").replaceAll(FIRST_BILL_WORDS[58], " 9 ").replaceAll(FIRST_BILL_WORDS[59], " 9 ").replaceAll(FIRST_BILL_WORDS[60], " 10 ").replaceAll(FIRST_BILL_WORDS[61], " 10 ");
                            if (countWords(s) == 1 && isNumeric(s)) {
                                if (Integer.valueOf(s.replaceAll(" ", "")) - 1 < names.size()) {
                                    number = Integer.valueOf(s.replaceAll(" ", "")) - 1;
                                    isOk = true;
                                } else
                                    isNumberExist = false;
                            }
                        }
                        if (!isNumberExist) {
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_no_number_exist), Toast.LENGTH_SHORT).show();
                            suggestionSmartBillVoice(isChangeRow, row, type, names, amounts, offers, units, states, dialog);
                        } else if (!isOk) {
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_no_number), Toast.LENGTH_SHORT).show();
                            suggestionSmartBillVoice(isChangeRow, row, type, names, amounts, offers, units, states, dialog);
                        } else {
                            isSuggVoiceControlingEnabled = false;
                            if (type == 1) {
                                if (!isChangeRow)
                                    productUnits.add(units.get(number));
                                else
                                    productUnits.set(row - 1, units.get(number));
                                int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
                                curSubject = String.valueOf(id_) + "-" + names.get(number);
                                curAmount = String.valueOf(amounts.get(number));
                                curOffer = String.valueOf(offers.get(number));
                                curUnit = String.valueOf(units.get(number));
                                curTotal = String.valueOf(amounts.get(number) * units.get(number));
                                curStatement = states.get(number);
                                addRowNext();
                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", id_ + 1).commit();
                                setTotal();
                                if (!isChangeRow)
                                    productNames.add(curSubject);
                                else
                                    productNames.set(row - 1, curSubject);
                            } else if (type == 2) {
                                if (basic == 1)
                                    edit_from.setText(names.get(number));
                                else if (basic == 3)
                                    edit_from_t.setText(names.get(number));
                            } else if (type == 3) {
                                if (basic == 2)
                                    edit_to.setText(names.get(number));
                                else if (basic == 3)
                                    edit_to_t.setText(names.get(number));
                            } else if (type == 4) {
                                category.setText(names.get(number));
                            }
                            isVoiceControlingEnabled = true;
                            dialog.cancel();
                            billVoice();
                        }
                    }
                }

                @Override
                public void onReadyForSpeech(Bundle params) {
                }

                @Override
                public void onError(int error) {
                    if (error != 8)
                        suggestionSmartBillVoice(isChangeRow, row, type, names, amounts, offers, units, states, dialog);
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

    private boolean checkBasicSentences(ArrayList<String> voiceResults) {
        boolean isWork = false;

        return isWork;
    }

    private boolean checkBasicLengthOne(ArrayList<String> voiceResults) {
        boolean isWork = false;
        outerloop:
        for (int k = 0; k < voiceResults.size(); k++) {
            String s = voiceResults.get(k);
            if (countWords(s) == 1) {
                if (s.contains(FIRST_BILL_WORDS[11]) || s.contains(FIRST_BILL_WORDS[12]) || s.contains(FIRST_BILL_WORDS[13])) {
                    onBackPressed();
                    isWork = true;
                } else if (s.contains(res.getString(R.string.drawer_purchases).toLowerCase(Locale.getDefault()))) {
                    spin_basic.setSelection(0);
                    isWork = true;
                } else if (s.contains(res.getString(R.string.drawer_sales).toLowerCase(Locale.getDefault()))) {
                    spin_basic.setSelection(1);
                    isWork = true;
                } else if (s.contains(res.getString(R.string.drawer_temporary).toLowerCase(Locale.getDefault()))) {
                    spin_basic.setSelection(2);
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[28])) {
                    isVoiceControlingEnabled = true;
                    voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_f_));
                    pref.edit().putBoolean("isBillVoiceControlEnable", false).commit();
                    isVoiceControlingEnabled = false;
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[20])) {
                    spin_type.setSelection(0);
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[21])) {
                    spin_type.setSelection(1);
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[22])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_currency_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[23])) {
                    spin_currency.setSelection(0);
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[24]) || s.contains(FIRST_BILL_WORDS[25])) {
                    spin_currency.setSelection(1);
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[14])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_date_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[16]) || s.contains(FIRST_BILL_WORDS[17])) {
                    boolean isThereWrong = false;
                    if (basic == 1) {
                        if (!edit_from.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                            isThereWrong = true;
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_from.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_from.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        }
                    } else if (basic == 2) {
                        if (!edit_to.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                            isThereWrong = true;
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_to.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_to.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        }
                    }
                    if (!isThereWrong) {
                        boolean isThereSupCus = false;
                        if (basic == 1) {
                            for (int i = 0; i < dbsu.allSuppliersNames().size(); i++) {
                                if (dbsu.allSuppliersNames().get(i).matches(edit_from.getText().toString()))
                                    isThereSupCus = true;
                            }
                        } else if (basic == 2) {
                            for (int i = 0; i < dbc.allCustomersNames().size(); i++) {
                                if (dbc.allCustomersNames().get(i).matches(edit_to.getText().toString()))
                                    isThereSupCus = true;
                            }
                        }
                        if (isThereSupCus || basic == 3) {
                            if (bun.getBoolean("isView", false))
                                createBillNameDialog(spinBasicSelected + 1, true);
                            else
                                createBillNameDialog(spinBasicSelected + 1, false);
                        } else {
                            if (basic == 1) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                                alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                                alertDialog.setMessage(res.getString(R.string.alertNewSupplierMessage));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSupplierColumn", 0);
                                                dbsu.addSupplier(new SupplierListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_from.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSupplierColumn", column + 1).commit();
                                                if (bun.getBoolean("isView", false))
                                                    createBillNameDialog(spinBasicSelected + 1, true);
                                                else
                                                    createBillNameDialog(spinBasicSelected + 1, false);
                                            }
                                        });
                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialog.dismiss();
                                        if (bun.getBoolean("isView", false))
                                            createBillNameDialog(spinBasicSelected + 1, true);
                                        else
                                            createBillNameDialog(spinBasicSelected + 1, false);
                                    }
                                });
                                alertDialog.show();
                            } else if (basic == 2) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                                alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                                alertDialog.setMessage(res.getString(R.string.alertNewCustomerMessage));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentCustomerColumn", 0);
                                                dbc.addCustomer(new CustomerListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_to.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentCustomerColumn", column + 1).commit();
                                                if (bun.getBoolean("isView", false))
                                                    createBillNameDialog(spinBasicSelected + 1, true);
                                                else
                                                    createBillNameDialog(spinBasicSelected + 1, false);
                                            }
                                        });
                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialog.dismiss();
                                        if (bun.getBoolean("isView", false))
                                            createBillNameDialog(spinBasicSelected + 1, true);
                                        else
                                            createBillNameDialog(spinBasicSelected + 1, false);
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                    }
                    isWork = true;
                } else if (s.contains(FIRST_BILL_WORDS[0]) || s.contains(FIRST_BILL_WORDS[1])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_from_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[2]) || s.contains(FIRST_BILL_WORDS[3])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_to_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[4]) || s.contains(FIRST_BILL_WORDS[5])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[6]) || s.contains(FIRST_BILL_WORDS[7])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[26])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_discount_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[27])) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_addition_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                } else if (s.contains(FIRST_BILL_WORDS[36]) && basic == 1) {
                    Toast.makeText(BillActivity.this, res.getString(R.string.error_voice_category_correct), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                }
                if (isWork)
                    break outerloop;
            }
        }
        return isWork;
    }

    int row, b = 0;
    String name = "", amount = "0", unit = "0", offer = "0", state = "", id = "";
    double amount_, unit_, offer_;

    /**
     * private boolean checkBasicLengthMoreOne(String s) {
     * boolean isWork = false;
     * boolean isAddRow = false;
     * boolean isChangeRow = false;
     * boolean isExist = false;
     * ArrayList<SubjectListChildItem> subjects = new ArrayList<SubjectListChildItem>();
     * String words[] = s.split("\\s+");
     * for (int p = 0; p < words.length; p++) {
     * words[p] = words[p].replaceAll("[^\\w]", "");
     * }
     * for (int n = 0; n < dbs.allSubjects().size(); n++) {
     * if (s.toLowerCase(Locale.getDefault()).contains(dbs.allSubjects().get(n).getName().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault())) || dbs.allSubjects().get(n).getName().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault()).contains(s.toLowerCase(Locale.getDefault()))) {
     * boolean isOk = false;
     * isExist = true;
     * if ((s.contains(FIRST_BILL_WORDS[8]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", ""))) || (!s.contains(FIRST_BILL_WORDS[8]) && dbs.allSubjects().get(n).getAmountLast() != 0 && basic != 1)) {
     * if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[8]) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[8]) + 1)).replaceAll(" ", ""))) || (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5]) && (isNumeric(nthWord(6, s)) || isNumeric(nthWord(7, s)))) || (isNumeric(nthWord(5, s)) || isNumeric(nthWord(6, s)))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * }
     * } else if ((s.contains(FIRST_BILL_WORDS[9]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", ""))) || (!s.contains(FIRST_BILL_WORDS[9]) && dbs.allSubjects().get(n).getAmountLast() != 0 && basic != 1)) {
     * if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[9]) && isNumeric(nthWord(4, s.substring(s.indexOf(FIRST_BILL_WORDS[9]) + 1)).replaceAll(" ", ""))) || (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5]) && (isNumeric(nthWord(6, s)) || isNumeric(nthWord(7, s)))) || (isNumeric(nthWord(5, s)) || isNumeric(nthWord(6, s)))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * }
     * } else if ((s.contains(FIRST_BILL_WORDS[10]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", ""))) || (!s.contains(FIRST_BILL_WORDS[10]) && dbs.allSubjects().get(n).getAmountLast() != 0 && basic != 1)) {
     * if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[10]) && isNumeric(nthWord(3, s.substring(s.indexOf(FIRST_BILL_WORDS[10]) + 1)).replaceAll(" ", ""))) || (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5]) && (isNumeric(nthWord(5, s)) || isNumeric(nthWord(6, s)))) || (isNumeric(nthWord(4, s)) || isNumeric(nthWord(5, s)))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * }
     * } else if ((((firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) && (isNumeric(words[3].replaceAll(" ", "")) || isNumeric(words[4].replaceAll(" ", "")))) || (isNumeric(words[2].replaceAll(" ", "")) || isNumeric(words[3].replaceAll(" ", "")))) || (!(isNumeric(words[2].replaceAll(" ", "")) || isNumeric(words[3].replaceAll(" ", "")) || isNumeric(words[4].replaceAll(" ", ""))) && dbs.allSubjects().get(n).getAmountLast() != 0 && basic != 1)) {
     * if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[32]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (s.contains(FIRST_BILL_WORDS[33]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "")))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * } else if ((basic != 1 && (dbs.allSubjects().get(n).getLast() != 0 || dbs.allSubjects().get(n).getLock() != 0)) || (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5]) && (isNumeric(nthWord(6, s)) || isNumeric(nthWord(7, s)))) || (isNumeric(nthWord(5, s)) || isNumeric(nthWord(6, s)))) {
     * if (s.contains(FIRST_BILL_WORDS[34]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", ""))) {
     * isOk = true;
     * subjects.add(dbs.allSubjects().get(n));
     * } else if (s.contains(FIRST_BILL_WORDS[34]))
     * isOk = false;
     * else {
     * subjects.add(dbs.allSubjects().get(n));
     * isOk = true;
     * }
     * }
     * }
     * if (isOk) {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[6]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", ""))) {
     * if (row >= 1 && row <= 50) {
     * isChangeRow = true;
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", ""));
     * name = dbs.allSubjects().get(n).getName();
     * id = String.valueOf(dbs.allSubjects().get(n).getId());
     * b = n;
     * } else {
     * Toast.makeText(this, res.getString(R.string.error_no_exist_row), Toast.LENGTH_LONG).show();
     * isWork = true;
     * }
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[7]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))).replaceAll(" ", ""))) {
     * if (row >= 1 && row <= 50) {
     * isChangeRow = true;
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))).replaceAll(" ", ""));
     * name = dbs.allSubjects().get(n).getName();
     * id = String.valueOf(dbs.allSubjects().get(n).getId());
     * b = n;
     * } else {
     * Toast.makeText(this, res.getString(R.string.error_no_exist_row), Toast.LENGTH_LONG).show();
     * isWork = true;
     * }
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[6]) || firstWord(s).contains(FIRST_BILL_WORDS[7])) {
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * } else {
     * isAddRow = true;
     * b = n;
     * name = dbs.allSubjects().get(n).getName();
     * id = String.valueOf(dbs.allSubjects().get(n).getId());
     * }
     * } else {
     * isExist = true;
     * Toast.makeText(this, res.getString(R.string.error_voice_add_correct), Toast.LENGTH_SHORT).show();
     * Toast.makeText(this, res.getString(R.string.error_voice_add_correct_), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * }
     * }
     * }
     * if (basic == 1 && !isAddRow && !isChangeRow && !isExist) {
     * int id_ = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId_", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectId", 10001));
     * id = String.valueOf(id_);
     * if (firstWord(s).contains(FIRST_BILL_WORDS[6]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", ""))) {
     * if (row >= 1 && row <= 50) {
     * isChangeRow = true;
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", ""));
     * if (checkIsRowEmpty(row)) {
     * isAddRow = true;
     * isChangeRow = false;
     * } else {
     * <p>
     * }
     * if (s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) {
     * if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 3)
     * name = thirdWord(s);
     * else if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 4 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 4 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 4)
     * name = nthWord(3, s) + " " + nthWord(4, s);
     * } else if (isNumeric(nthWord(5, s))) {
     * name = nthWord(3, s);
     * } else if (isNumeric(nthWord(6, s))) {
     * name = nthWord(4, s);
     * }
     * } else {
     * Toast.makeText(this, res.getString(R.string.error_no_exist_row), Toast.LENGTH_LONG).show();
     * isWork = true;
     * }
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[7]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))).replaceAll(" ", ""))) {
     * if (row >= 1 && row <= 50) {
     * isChangeRow = true;
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))).replaceAll(" ", ""));
     * if (checkIsRowEmpty(row)) {
     * isAddRow = true;
     * isChangeRow = false;
     * } else {
     * <p>
     * }
     * if (s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) {
     * if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 3)
     * name = thirdWord(s);
     * else if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 4 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 4 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 4)
     * name = nthWord(3, s) + " " + nthWord(4, s);
     * } else if (isNumeric(nthWord(5, s))) {
     * name = nthWord(3, s);
     * } else if (isNumeric(nthWord(6, s))) {
     * name = nthWord(4, s);
     * }
     * } else {
     * Toast.makeText(this, res.getString(R.string.error_no_exist_row), Toast.LENGTH_LONG).show();
     * isWork = true;
     * }
     * } else {
     * if (firstWord(s).contains(FIRST_BILL_WORDS[6]) || firstWord(s).contains(FIRST_BILL_WORDS[7])) {
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * } else {
     * isAddRow = true;
     * if (s.contains(FIRST_BILL_WORDS[4]))
     * name = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[4]))).replaceAll(" ", "");
     * else if (s.contains(FIRST_BILL_WORDS[5]))
     * name = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[5]))).replaceAll(" ", "");
     * else if (s.contains(FIRST_BILL_WORDS[8]))
     * name = beforeWord(s, FIRST_BILL_WORDS[8]).replaceAll(" ", "");
     * else if (s.contains(FIRST_BILL_WORDS[9]))
     * name = beforeWord(s, FIRST_BILL_WORDS[9]).replaceAll(" ", "");
     * else if (s.contains(FIRST_BILL_WORDS[10]))
     * name = beforeWord(s, FIRST_BILL_WORDS[10]).replaceAll(" ", "");
     * if (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) {
     * if (s.contains(FIRST_BILL_WORDS[8]) || s.contains(FIRST_BILL_WORDS[9]) || s.contains(FIRST_BILL_WORDS[10])) {
     * if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 2 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 2 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 2)
     * name = secondWord(s);
     * else if (countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[8]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[9]) - 1)) == 3 || countWords(s.substring(0, s.indexOf(FIRST_BILL_WORDS[10]) - 1)) == 3)
     * name = nthWord(2, s) + " " + nthWord(3, s);
     * } else if (isNumeric(nthWord(4, s))) {
     * name = nthWord(2, s);
     * } else if (isNumeric(nthWord(5, s))) {
     * name = nthWord(3, s);
     * }
     * }
     * }
     * }
     * } else if (basic == 1 && !isAddRow && !isChangeRow && !isExist) {
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct), Toast.LENGTH_SHORT).show();
     * Toast.makeText(this, res.getString(R.string.error_voice_change_correct_), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * }
     * if (isAddRow || isChangeRow) {
     * isWork = addRowByVoice(isChangeRow, s, isExist);
     * } else if ((s.contains(FIRST_BILL_WORDS[15]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[15]))).replaceAll(" ", ""))) || (s.contains(FIRST_BILL_WORDS[15]) && secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[15]))).replaceAll(" ", "").contains(FIRST_BILL_WORDS[6]) && isNumeric(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", "")))) {
     * if (secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[15]))).replaceAll(" ", "").contains(FIRST_BILL_WORDS[6]))
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[6]))).replaceAll(" ", ""));
     * else
     * row = Integer.valueOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[15]))).replaceAll(" ", ""));
     * if (row >= 1 && row <= 50) {
     * deleteRow(row);
     * isWork = true;
     * } else {
     * Toast.makeText(this, res.getString(R.string.error_no_exist_row), Toast.LENGTH_LONG).show();
     * isWork = true;
     * }
     * } else if (s.contains(FIRST_BILL_WORDS[0]) || (s.contains(FIRST_BILL_WORDS[1]))) {
     * String supp = "";
     * if (s.contains(FIRST_BILL_WORDS[0]))
     * supp = FIRST_BILL_WORDS[0];
     * else
     * supp = FIRST_BILL_WORDS[1];
     * if (basic == 1) {
     * boolean isEx = false;
     * for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
     * if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).contains(dbsu.allSuppliers().get(k).getSupplier().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault()))) {
     * edit_from.setText(secondWord(s.substring(s.indexOf(supp))));
     * isEx = true;
     * isWork = true;
     * }
     * }
     * if (!isEx) {
     * ArrayList<String> names = new ArrayList<>();
     * for (int i = 0; i < voice_results.size(); i++) {
     * boolean isItemEx = false;
     * for (int j = 0; j < names.size(); j++) {
     * if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
     * isItemEx = true;
     * }
     * if (!isItemEx)
     * names.add(secondWord(s.substring(s.indexOf(supp))));
     * }
     * if (names.size() == 1)
     * edit_from.setText(names.get(0));
     * else if (names.size() > 1) {
     * createSuggestionsDialog(2, names, null, null, null, null);
     * isVoiceControlingEnabled = false;
     * }
     * isWork = true;
     * }
     * } else if (basic == 2) {
     * boolean isPointExist = false;
     * for (int k = 0; k < point.length; k++) {
     * if (secondWord(s.substring(s.indexOf(supp))).contains(point[k])) {
     * spin_point.setSelection(k);
     * isPointExist = true;
     * }
     * }
     * if (!isPointExist) {
     * Toast.makeText(BillActivity.this, res.getString(R.string.error_no_exist_point), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * } else {
     * isWork = true;
     * }
     * } else {
     * boolean isEx = false;
     * for (int k = 0; k < dbsu.allSuppliers().size(); k++) {
     * if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).contains(dbsu.allSuppliers().get(k).getSupplier().replaceAll("أ", "ا").replaceAll("ة", "ه").toLowerCase(Locale.getDefault()))) {
     * edit_from_t.setText(secondWord(s.substring(s.indexOf(supp))));
     * isEx = true;
     * isWork = true;
     * }
     * }
     * if (!isEx) {
     * ArrayList<String> names = new ArrayList<>();
     * for (int i = 0; i < voice_results.size(); i++) {
     * boolean isItemEx = false;
     * for (int j = 0; j < names.size(); j++) {
     * if (secondWord(s.substring(s.indexOf(supp))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
     * isItemEx = true;
     * }
     * if (!isItemEx)
     * names.add(secondWord(s.substring(s.indexOf(supp))));
     * }
     * if (names.size() == 1)
     * edit_from_t.setText(names.get(0));
     * else if (names.size() > 1) {
     * createSuggestionsDialog(2, names, null, null, null, null);
     * isVoiceControlingEnabled = false;
     * }
     * isWork = true;
     * }
     * }
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[2]) || (firstWord(s).contains(FIRST_BILL_WORDS[3]))) {
     * String cus = "";
     * if (s.contains(FIRST_BILL_WORDS[2]))
     * cus = FIRST_BILL_WORDS[2];
     * else
     * cus = FIRST_BILL_WORDS[3];
     * if (basic == 2) {
     * boolean isEx = false;
     * for (int k = 0; k < dbc.allCustomers().size(); k++) {
     * if (dbc.allCustomers().get(k).getCustomer().toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()))) {
     * edit_to.setText(secondWord(s.substring(s.indexOf(cus))));
     * isEx = true;
     * isWork = true;
     * }
     * }
     * if (!isEx) {
     * ArrayList<String> names = new ArrayList<>();
     * for (int i = 0; i < voice_results.size(); i++) {
     * boolean isItemEx = false;
     * for (int j = 0; j < names.size(); j++) {
     * if (secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
     * isItemEx = true;
     * }
     * if (!isItemEx)
     * names.add(secondWord(s.substring(s.indexOf(cus))));
     * }
     * if (names.size() == 1)
     * edit_to.setText(names.get(0));
     * else if (names.size() > 1) {
     * createSuggestionsDialog(3, names, null, null, null, null);
     * isVoiceControlingEnabled = false;
     * }
     * isWork = true;
     * }
     * } else if (basic == 1) {
     * boolean isPointExist = false;
     * for (int k = 0; k < point.length; k++) {
     * if (secondWord(s.substring(s.indexOf(cus))).contains(point[k])) {
     * spin_point_.setSelection(k);
     * isPointExist = true;
     * }
     * }
     * if (!isPointExist) {
     * Toast.makeText(BillActivity.this, res.getString(R.string.error_no_exist_point), Toast.LENGTH_LONG).show();
     * isWordsNotCompleted = true;
     * isWork = true;
     * } else {
     * isWork = true;
     * }
     * } else {
     * boolean isEx = false;
     * for (int k = 0; k < dbc.allCustomers().size(); k++) {
     * if (dbc.allCustomers().get(k).getCustomer().toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()))) {
     * edit_to_t.setText(secondWord(s.substring(s.indexOf(cus))));
     * isEx = true;
     * isWork = true;
     * }
     * }
     * if (!isEx) {
     * ArrayList<String> names = new ArrayList<>();
     * for (int i = 0; i < voice_results.size(); i++) {
     * boolean isItemEx = false;
     * for (int j = 0; j < names.size(); j++) {
     * if (secondWord(s.substring(s.indexOf(cus))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
     * isItemEx = true;
     * }
     * if (!isItemEx)
     * names.add(secondWord(s.substring(s.indexOf(cus))));
     * }
     * if (names.size() == 1)
     * edit_to_t.setText(names.get(0));
     * else if (names.size() > 1) {
     * createSuggestionsDialog(3, names, null, null, null, null);
     * isVoiceControlingEnabled = false;
     * }
     * isWork = true;
     * }
     * }
     * } else if (s.contains(FIRST_BILL_WORDS[7]) && secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))).contains(FIRST_BILL_WORDS[14])) {
     * if (isNumeric(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))).replaceAll(" ", "")) && isNumeric(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))))).replaceAll(" ", "")) && isNumeric(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))))))))).replaceAll(" ", ""))) {
     * year = Integer.valueOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))))))))).replaceAll(" ", ""));
     * month = Integer.valueOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))))).replaceAll(" ", ""));
     * day = Integer.valueOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))).replaceAll(" ", ""));
     * txt_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
     * isWork = true;
     * } else if (secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))).contains("/")) {
     * if (s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))) + 1).contains("/")) {
     * if (isNumeric(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("/") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.indexOf("/") + 1, s.lastIndexOf("/") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.lastIndexOf("/") + 1, s.length() - 1).replaceAll(" ", ""))) {
     * day = Integer.valueOf(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("/") - 1).replaceAll(" ", ""));
     * month = Integer.valueOf(s.substring(s.indexOf("/") + 1, s.lastIndexOf("/") - 1).replaceAll(" ", ""));
     * year = Integer.valueOf(s.substring(s.lastIndexOf("/") + 1, s.length() - 1).replaceAll(" ", ""));
     * }
     * } else {
     * if (isNumeric(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("/") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.indexOf("/") + 1, s.lastIndexOf(" ") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.lastIndexOf(" ") + 1, s.length() - 1).replaceAll(" ", ""))) {
     * day = Integer.valueOf(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("/") - 1).replaceAll(" ", ""));
     * month = Integer.valueOf(s.substring(s.indexOf("/") + 1, s.lastIndexOf(" ") - 1).replaceAll(" ", ""));
     * year = Integer.valueOf(s.substring(s.lastIndexOf(" ") + 1, s.length() - 1).replaceAll(" ", ""));
     * }
     * }
     * txt_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
     * isWork = true;
     * } else if (secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7])))))).contains("-")) {
     * if (s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))) + 1).contains("-")) {
     * if (isNumeric(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("-") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.indexOf("-") + 1, s.lastIndexOf("-") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.lastIndexOf("-") + 1, s.length() - 1).replaceAll(" ", ""))) {
     * day = Integer.valueOf(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("-") - 1).replaceAll(" ", ""));
     * month = Integer.valueOf(s.substring(s.indexOf("-") + 1, s.lastIndexOf("-") - 1).replaceAll(" ", ""));
     * year = Integer.valueOf(s.substring(s.lastIndexOf("-") + 1, s.length() - 1).replaceAll(" ", ""));
     * }
     * } else {
     * if (isNumeric(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("-") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.indexOf("-") + 1, s.lastIndexOf(" ") - 1).replaceAll(" ", "")) && isNumeric(s.substring(s.lastIndexOf(" ") + 1, s.length() - 1).replaceAll(" ", ""))) {
     * day = Integer.valueOf(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[7]))))))), s.indexOf("-") - 1).replaceAll(" ", ""));
     * month = Integer.valueOf(s.substring(s.indexOf("-") + 1, s.lastIndexOf(" ") - 1).replaceAll(" ", ""));
     * year = Integer.valueOf(s.substring(s.lastIndexOf(" ") + 1, s.length() - 1).replaceAll(" ", ""));
     * }
     * }
     * txt_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
     * isWork = true;
     * } else {
     * DatePickerDialog datePickerDialog = new DatePickerDialog(BillActivity.this, new DatePickerDialog.OnDateSetListener() {
     *
     * @Override public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
     * year = i;
     * month = i1;
     * day = i2;
     * txt_date.setText(i2 + "/" + i1 + "/" + i);
     * }
     * }, year, month, day);
     * datePickerDialog.show();
     * isWork = true;
     * }
     * } else if (s.contains(FIRST_BILL_WORDS[27])) {
     * edit_discount.setText(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[27]))));
     * isWork = true;
     * } else if (firstWord(s).contains(FIRST_BILL_WORDS[28])) {
     * edit_addition.setText(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[28]))));
     * isWork = true;
     * } else if (s.contains(FIRST_BILL_WORDS[28])) {
     * isVoiceControlingEnabled = false;
     * voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_f_));
     * isWork = true;
     * } else if (s.contains(FIRST_BILL_WORDS[36]) && basic == 1) {
     * category.setText(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]))));
     * boolean isEx = false;
     * for (int k = 0; k < dbs.allFolders().size(); k++) {
     * if (dbs.allFolders().get(k).toLowerCase(Locale.getDefault()).replaceAll("ة", "ه").replaceAll("أ", "ا").contains(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]))).toLowerCase(Locale.getDefault()))) {
     * category.setText(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]))));
     * isEx = true;
     * isWork = true;
     * }
     * }
     * if (!isEx) {
     * ArrayList<String> names = new ArrayList<>();
     * for (int i = 0; i < voice_results.size(); i++) {
     * boolean isItemEx = false;
     * for (int j = 0; j < names.size(); j++) {
     * if (secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]))).toLowerCase(Locale.getDefault()).matches(names.get(j)))
     * isItemEx = true;
     * }
     * if (!isItemEx)
     * names.add(secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[36]))));
     * }
     * if (names.size() == 1)
     * category.setText(names.get(0));
     * else if (names.size() > 1) {
     * createSuggestionsDialog(4, names, null, null, null, null);
     * isVoiceControlingEnabled = false;
     * }
     * isWork = true;
     * }
     * }
     * return isWork;
     * }
     **/

    private String getSubjectByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = sub1.getText().toString();
                break;
            case 2:
                s = sub2.getText().toString();
                break;
            case 3:
                s = sub3.getText().toString();
                break;
            case 4:
                s = sub4.getText().toString();
                break;
            case 5:
                s = sub5.getText().toString();
                break;
            case 6:
                s = sub6.getText().toString();
                break;
            case 7:
                s = sub7.getText().toString();
                break;
            case 8:
                s = sub8.getText().toString();
                break;
            case 9:
                s = sub9.getText().toString();
                break;
            case 10:
                s = sub10.getText().toString();
                break;
            case 11:
                s = sub11.getText().toString();
                break;
            case 12:
                s = sub12.getText().toString();
                break;
            case 13:
                s = sub13.getText().toString();
                break;
            case 14:
                s = sub14.getText().toString();
                break;
            case 15:
                s = sub15.getText().toString();
                break;
            case 16:
                s = sub16.getText().toString();
                break;
            case 17:
                s = sub17.getText().toString();
                break;
            case 18:
                s = sub18.getText().toString();
                break;
            case 19:
                s = sub19.getText().toString();
                break;
            case 20:
                s = sub20.getText().toString();
                break;
            case 21:
                s = sub21.getText().toString();
                break;
            case 22:
                s = sub22.getText().toString();
                break;
            case 23:
                s = sub23.getText().toString();
                break;
            case 24:
                s = sub24.getText().toString();
                break;
            case 25:
                s = sub25.getText().toString();
                break;
            case 26:
                s = sub26.getText().toString();
                break;
            case 27:
                s = sub27.getText().toString();
                break;
            case 28:
                s = sub28.getText().toString();
                break;
            case 29:
                s = sub29.getText().toString();
                break;
            case 30:
                s = sub30.getText().toString();
                break;
            case 31:
                s = sub31.getText().toString();
                break;
            case 32:
                s = sub32.getText().toString();
                break;
            case 33:
                s = sub33.getText().toString();
                break;
            case 34:
                s = sub34.getText().toString();
                break;
            case 35:
                s = sub35.getText().toString();
                break;
            case 36:
                s = sub36.getText().toString();
                break;
            case 37:
                s = sub37.getText().toString();
                break;
            case 38:
                s = sub38.getText().toString();
                break;
            case 39:
                s = sub39.getText().toString();
                break;
            case 40:
                s = sub40.getText().toString();
                break;
            case 41:
                s = sub41.getText().toString();
                break;
            case 42:
                s = sub42.getText().toString();
                break;
            case 43:
                s = sub43.getText().toString();
                break;
            case 44:
                s = sub44.getText().toString();
                break;
            case 45:
                s = sub45.getText().toString();
                break;
            case 46:
                s = sub46.getText().toString();
                break;
            case 47:
                s = sub47.getText().toString();
                break;
            case 48:
                s = sub48.getText().toString();
                break;
            case 49:
                s = sub49.getText().toString();
                break;
            case 50:
                s = sub50.getText().toString();
                break;
        }
        return s;
    }

    private String getAmountByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = amo1.getText().toString();
                break;
            case 2:
                s = amo2.getText().toString();
                break;
            case 3:
                s = amo3.getText().toString();
                break;
            case 4:
                s = amo4.getText().toString();
                break;
            case 5:
                s = amo5.getText().toString();
                break;
            case 6:
                s = amo6.getText().toString();
                break;
            case 7:
                s = amo7.getText().toString();
                break;
            case 8:
                s = amo8.getText().toString();
                break;
            case 9:
                s = amo9.getText().toString();
                break;
            case 10:
                s = amo10.getText().toString();
                break;
            case 11:
                s = amo11.getText().toString();
                break;
            case 12:
                s = amo12.getText().toString();
                break;
            case 13:
                s = amo13.getText().toString();
                break;
            case 14:
                s = amo14.getText().toString();
                break;
            case 15:
                s = amo15.getText().toString();
                break;
            case 16:
                s = amo16.getText().toString();
                break;
            case 17:
                s = amo17.getText().toString();
                break;
            case 18:
                s = amo18.getText().toString();
                break;
            case 19:
                s = amo19.getText().toString();
                break;
            case 20:
                s = amo20.getText().toString();
                break;
            case 21:
                s = amo21.getText().toString();
                break;
            case 22:
                s = amo22.getText().toString();
                break;
            case 23:
                s = amo23.getText().toString();
                break;
            case 24:
                s = amo24.getText().toString();
                break;
            case 25:
                s = amo25.getText().toString();
                break;
            case 26:
                s = amo26.getText().toString();
                break;
            case 27:
                s = amo27.getText().toString();
                break;
            case 28:
                s = amo28.getText().toString();
                break;
            case 29:
                s = amo29.getText().toString();
                break;
            case 30:
                s = amo30.getText().toString();
                break;
            case 31:
                s = amo31.getText().toString();
                break;
            case 32:
                s = amo32.getText().toString();
                break;
            case 33:
                s = amo33.getText().toString();
                break;
            case 34:
                s = amo34.getText().toString();
                break;
            case 35:
                s = amo35.getText().toString();
                break;
            case 36:
                s = amo36.getText().toString();
                break;
            case 37:
                s = amo37.getText().toString();
                break;
            case 38:
                s = amo38.getText().toString();
                break;
            case 39:
                s = amo39.getText().toString();
                break;
            case 40:
                s = amo40.getText().toString();
                break;
            case 41:
                s = amo41.getText().toString();
                break;
            case 42:
                s = amo42.getText().toString();
                break;
            case 43:
                s = amo43.getText().toString();
                break;
            case 44:
                s = amo44.getText().toString();
                break;
            case 45:
                s = amo45.getText().toString();
                break;
            case 46:
                s = amo46.getText().toString();
                break;
            case 47:
                s = amo47.getText().toString();
                break;
            case 48:
                s = amo48.getText().toString();
                break;
            case 49:
                s = amo49.getText().toString();
                break;
            case 50:
                s = amo50.getText().toString();
                break;
        }
        return s;
    }

    private String getOfferByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = off1.getText().toString();
                break;
            case 2:
                s = off2.getText().toString();
                break;
            case 3:
                s = off3.getText().toString();
                break;
            case 4:
                s = off4.getText().toString();
                break;
            case 5:
                s = off5.getText().toString();
                break;
            case 6:
                s = off6.getText().toString();
                break;
            case 7:
                s = off7.getText().toString();
                break;
            case 8:
                s = off8.getText().toString();
                break;
            case 9:
                s = off9.getText().toString();
                break;
            case 10:
                s = off10.getText().toString();
                break;
            case 11:
                s = off11.getText().toString();
                break;
            case 12:
                s = off12.getText().toString();
                break;
            case 13:
                s = off13.getText().toString();
                break;
            case 14:
                s = off14.getText().toString();
                break;
            case 15:
                s = off15.getText().toString();
                break;
            case 16:
                s = off16.getText().toString();
                break;
            case 17:
                s = off17.getText().toString();
                break;
            case 18:
                s = off18.getText().toString();
                break;
            case 19:
                s = off19.getText().toString();
                break;
            case 20:
                s = off20.getText().toString();
                break;
            case 21:
                s = off21.getText().toString();
                break;
            case 22:
                s = off22.getText().toString();
                break;
            case 23:
                s = off23.getText().toString();
                break;
            case 24:
                s = off24.getText().toString();
                break;
            case 25:
                s = off25.getText().toString();
                break;
            case 26:
                s = off26.getText().toString();
                break;
            case 27:
                s = off27.getText().toString();
                break;
            case 28:
                s = off28.getText().toString();
                break;
            case 29:
                s = off29.getText().toString();
                break;
            case 30:
                s = off30.getText().toString();
                break;
            case 31:
                s = off31.getText().toString();
                break;
            case 32:
                s = off32.getText().toString();
                break;
            case 33:
                s = off33.getText().toString();
                break;
            case 34:
                s = off34.getText().toString();
                break;
            case 35:
                s = off35.getText().toString();
                break;
            case 36:
                s = off36.getText().toString();
                break;
            case 37:
                s = off37.getText().toString();
                break;
            case 38:
                s = off38.getText().toString();
                break;
            case 39:
                s = off39.getText().toString();
                break;
            case 40:
                s = off40.getText().toString();
                break;
            case 41:
                s = off41.getText().toString();
                break;
            case 42:
                s = off42.getText().toString();
                break;
            case 43:
                s = off43.getText().toString();
                break;
            case 44:
                s = off44.getText().toString();
                break;
            case 45:
                s = off45.getText().toString();
                break;
            case 46:
                s = off46.getText().toString();
                break;
            case 47:
                s = off47.getText().toString();
                break;
            case 48:
                s = off48.getText().toString();
                break;
            case 49:
                s = off49.getText().toString();
                break;
            case 50:
                s = off50.getText().toString();
                break;
        }
        return s;
    }

    private String getUnitByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = uni1.getText().toString();
                break;
            case 2:
                s = uni2.getText().toString();
                break;
            case 3:
                s = uni3.getText().toString();
                break;
            case 4:
                s = uni4.getText().toString();
                break;
            case 5:
                s = uni5.getText().toString();
                break;
            case 6:
                s = uni6.getText().toString();
                break;
            case 7:
                s = uni7.getText().toString();
                break;
            case 8:
                s = uni8.getText().toString();
                break;
            case 9:
                s = uni9.getText().toString();
                break;
            case 10:
                s = uni10.getText().toString();
                break;
            case 11:
                s = uni11.getText().toString();
                break;
            case 12:
                s = uni12.getText().toString();
                break;
            case 13:
                s = uni13.getText().toString();
                break;
            case 14:
                s = uni14.getText().toString();
                break;
            case 15:
                s = uni15.getText().toString();
                break;
            case 16:
                s = uni16.getText().toString();
                break;
            case 17:
                s = uni17.getText().toString();
                break;
            case 18:
                s = uni18.getText().toString();
                break;
            case 19:
                s = uni19.getText().toString();
                break;
            case 20:
                s = uni20.getText().toString();
                break;
            case 21:
                s = uni21.getText().toString();
                break;
            case 22:
                s = uni22.getText().toString();
                break;
            case 23:
                s = uni23.getText().toString();
                break;
            case 24:
                s = uni24.getText().toString();
                break;
            case 25:
                s = uni25.getText().toString();
                break;
            case 26:
                s = uni26.getText().toString();
                break;
            case 27:
                s = uni27.getText().toString();
                break;
            case 28:
                s = uni28.getText().toString();
                break;
            case 29:
                s = uni29.getText().toString();
                break;
            case 30:
                s = uni30.getText().toString();
                break;
            case 31:
                s = uni31.getText().toString();
                break;
            case 32:
                s = uni32.getText().toString();
                break;
            case 33:
                s = uni33.getText().toString();
                break;
            case 34:
                s = uni34.getText().toString();
                break;
            case 35:
                s = uni35.getText().toString();
                break;
            case 36:
                s = uni36.getText().toString();
                break;
            case 37:
                s = uni37.getText().toString();
                break;
            case 38:
                s = uni38.getText().toString();
                break;
            case 39:
                s = uni39.getText().toString();
                break;
            case 40:
                s = uni40.getText().toString();
                break;
            case 41:
                s = uni41.getText().toString();
                break;
            case 42:
                s = uni42.getText().toString();
                break;
            case 43:
                s = uni43.getText().toString();
                break;
            case 44:
                s = uni44.getText().toString();
                break;
            case 45:
                s = uni45.getText().toString();
                break;
            case 46:
                s = uni46.getText().toString();
                break;
            case 47:
                s = uni47.getText().toString();
                break;
            case 48:
                s = uni48.getText().toString();
                break;
            case 49:
                s = uni49.getText().toString();
                break;
            case 50:
                s = uni50.getText().toString();
                break;
        }
        return s;
    }

    private String getTotalByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = tot1.getText().toString();
                break;
            case 2:
                s = tot2.getText().toString();
                break;
            case 3:
                s = tot3.getText().toString();
                break;
            case 4:
                s = tot4.getText().toString();
                break;
            case 5:
                s = tot5.getText().toString();
                break;
            case 6:
                s = tot6.getText().toString();
                break;
            case 7:
                s = tot7.getText().toString();
                break;
            case 8:
                s = tot8.getText().toString();
                break;
            case 9:
                s = tot9.getText().toString();
                break;
            case 10:
                s = tot10.getText().toString();
                break;
            case 11:
                s = tot11.getText().toString();
                break;
            case 12:
                s = tot12.getText().toString();
                break;
            case 13:
                s = tot13.getText().toString();
                break;
            case 14:
                s = tot14.getText().toString();
                break;
            case 15:
                s = tot15.getText().toString();
                break;
            case 16:
                s = tot16.getText().toString();
                break;
            case 17:
                s = tot17.getText().toString();
                break;
            case 18:
                s = tot18.getText().toString();
                break;
            case 19:
                s = tot19.getText().toString();
                break;
            case 20:
                s = tot20.getText().toString();
                break;
            case 21:
                s = tot21.getText().toString();
                break;
            case 22:
                s = tot22.getText().toString();
                break;
            case 23:
                s = tot23.getText().toString();
                break;
            case 24:
                s = tot24.getText().toString();
                break;
            case 25:
                s = tot25.getText().toString();
                break;
            case 26:
                s = tot26.getText().toString();
                break;
            case 27:
                s = tot27.getText().toString();
                break;
            case 28:
                s = tot28.getText().toString();
                break;
            case 29:
                s = tot29.getText().toString();
                break;
            case 30:
                s = tot30.getText().toString();
                break;
            case 31:
                s = tot31.getText().toString();
                break;
            case 32:
                s = tot32.getText().toString();
                break;
            case 33:
                s = tot33.getText().toString();
                break;
            case 34:
                s = tot34.getText().toString();
                break;
            case 35:
                s = tot35.getText().toString();
                break;
            case 36:
                s = tot36.getText().toString();
                break;
            case 37:
                s = tot37.getText().toString();
                break;
            case 38:
                s = tot38.getText().toString();
                break;
            case 39:
                s = tot39.getText().toString();
                break;
            case 40:
                s = tot40.getText().toString();
                break;
            case 41:
                s = tot41.getText().toString();
                break;
            case 42:
                s = tot42.getText().toString();
                break;
            case 43:
                s = tot43.getText().toString();
                break;
            case 44:
                s = tot44.getText().toString();
                break;
            case 45:
                s = tot45.getText().toString();
                break;
            case 46:
                s = tot46.getText().toString();
                break;
            case 47:
                s = tot47.getText().toString();
                break;
            case 48:
                s = tot48.getText().toString();
                break;
            case 49:
                s = tot49.getText().toString();
                break;
            case 50:
                s = tot50.getText().toString();
                break;
        }
        return s;
    }

    private String getStatementByRow(int row) {
        String s = "";
        switch (row) {
            case 1:
                s = sta1.getText().toString();
                break;
            case 2:
                s = sta2.getText().toString();
                break;
            case 3:
                s = sta3.getText().toString();
                break;
            case 4:
                s = sta4.getText().toString();
                break;
            case 5:
                s = sta5.getText().toString();
                break;
            case 6:
                s = sta6.getText().toString();
                break;
            case 7:
                s = sta7.getText().toString();
                break;
            case 8:
                s = sta8.getText().toString();
                break;
            case 9:
                s = sta9.getText().toString();
                break;
            case 10:
                s = sta10.getText().toString();
                break;
            case 11:
                s = sta11.getText().toString();
                break;
            case 12:
                s = sta12.getText().toString();
                break;
            case 13:
                s = sta13.getText().toString();
                break;
            case 14:
                s = sta14.getText().toString();
                break;
            case 15:
                s = sta15.getText().toString();
                break;
            case 16:
                s = sta16.getText().toString();
                break;
            case 17:
                s = sta17.getText().toString();
                break;
            case 18:
                s = sta18.getText().toString();
                break;
            case 19:
                s = sta19.getText().toString();
                break;
            case 20:
                s = sta20.getText().toString();
                break;
            case 21:
                s = sta21.getText().toString();
                break;
            case 22:
                s = sta22.getText().toString();
                break;
            case 23:
                s = sta23.getText().toString();
                break;
            case 24:
                s = sta24.getText().toString();
                break;
            case 25:
                s = sta25.getText().toString();
                break;
            case 26:
                s = sta26.getText().toString();
                break;
            case 27:
                s = sta27.getText().toString();
                break;
            case 28:
                s = sta28.getText().toString();
                break;
            case 29:
                s = sta29.getText().toString();
                break;
            case 30:
                s = sta30.getText().toString();
                break;
            case 31:
                s = sta31.getText().toString();
                break;
            case 32:
                s = sta32.getText().toString();
                break;
            case 33:
                s = sta33.getText().toString();
                break;
            case 34:
                s = sta34.getText().toString();
                break;
            case 35:
                s = sta35.getText().toString();
                break;
            case 36:
                s = sta36.getText().toString();
                break;
            case 37:
                s = sta37.getText().toString();
                break;
            case 38:
                s = sta38.getText().toString();
                break;
            case 39:
                s = sta39.getText().toString();
                break;
            case 40:
                s = sta40.getText().toString();
                break;
            case 41:
                s = sta41.getText().toString();
                break;
            case 42:
                s = sta42.getText().toString();
                break;
            case 43:
                s = sta43.getText().toString();
                break;
            case 44:
                s = sta44.getText().toString();
                break;
            case 45:
                s = sta45.getText().toString();
                break;
            case 46:
                s = sta46.getText().toString();
                break;
            case 47:
                s = sta47.getText().toString();
                break;
            case 48:
                s = sta48.getText().toString();
                break;
            case 49:
                s = sta49.getText().toString();
                break;
            case 50:
                s = sta50.getText().toString();
                break;
        }
        return s;
    }

    private boolean addRowByVoice(final boolean isChangeRow, String s, final boolean isExis) {
        boolean isWork = false;
        if (s.contains(FIRST_BILL_WORDS[8]))
            amount = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[8]))).replaceAll(" ", "");
        else if (s.contains(FIRST_BILL_WORDS[9]))
            amount = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[9]))).replaceAll(" ", "");
        else if (s.contains(FIRST_BILL_WORDS[10]))
            amount = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[10]))).replaceAll(" ", "");
        else if (!isChangeRow && (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) && isNumeric(nthWord(4, s).replaceAll(" ", "")))
            amount = nthWord(4, s).replaceAll(" ", "");
        else if (!isChangeRow && (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) && isNumeric(nthWord(5, s).replaceAll(" ", "")))
            amount = nthWord(5, s).replaceAll(" ", "");
        else if (!isChangeRow && isNumeric(nthWord(3, s).replaceAll(" ", "")))
            amount = nthWord(3, s).replaceAll(" ", "");
        else if (!isChangeRow && isNumeric(nthWord(4, s).replaceAll(" ", "")))
            amount = nthWord(4, s).replaceAll(" ", "");
        else if (isChangeRow && isNumeric(nthWord(5, s).replaceAll(" ", "")))
            amount = nthWord(5, s).replaceAll(" ", "");
        else if (isChangeRow && isNumeric(nthWord(6, s).replaceAll(" ", "")))
            amount = nthWord(6, s).replaceAll(" ", "");
        else if (basic != 1 && dbs.allSubjects().get(b).getAmountLast() != 0)
            amount = String.valueOf(dbs.allSubjects().get(b).getAmountLast());
        if (s.contains(FIRST_BILL_WORDS[32]))
            unit = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[32]))).replaceAll(" ", "");
        else if (s.contains(FIRST_BILL_WORDS[33]))
            unit = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[33]))).replaceAll(" ", "");
        else if (!isChangeRow && (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) && isNumeric(nthWord(6, s).replaceAll(" ", "")))
            unit = nthWord(6, s).replaceAll(" ", "");
        else if (!isChangeRow && (firstWord(s).contains(FIRST_BILL_WORDS[4]) || firstWord(s).contains(FIRST_BILL_WORDS[5])) && isNumeric(nthWord(7, s).replaceAll(" ", "")))
            unit = nthWord(7, s).replaceAll(" ", "");
        else if (!isChangeRow && isNumeric(nthWord(5, s).replaceAll(" ", "")))
            unit = nthWord(5, s).replaceAll(" ", "");
        else if (!isChangeRow && isNumeric(nthWord(6, s).replaceAll(" ", "")))
            unit = nthWord(6, s).replaceAll(" ", "");
        else if (isChangeRow && isNumeric(nthWord(7, s).replaceAll(" ", "")))
            unit = nthWord(7, s).replaceAll(" ", "");
        else if (isChangeRow && isNumeric(nthWord(8, s).replaceAll(" ", "")))
            unit = nthWord(8, s).replaceAll(" ", "");
        else if (basic != 1 && dbs.allSubjects().get(b).getLast() == 0)
            unit = String.valueOf(dbs.allSubjects().get(b).getLock());
        else if (basic != 1)
            unit = String.valueOf(dbs.allSubjects().get(b).getLast());
        else
            unit = String.valueOf(dbs.allSubjects().get(b).getCost());
        if (s.contains(FIRST_BILL_WORDS[34]))
            offer = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[34]))).replaceAll(" ", "");
        if (s.contains(FIRST_BILL_WORDS[35]))
            state = secondWord(s.substring(s.indexOf(FIRST_BILL_WORDS[35]))).replaceAll(" ", "");
        try {
            amount_ = Double.valueOf(amount);
            unit_ = Double.valueOf(unit);
            offer_ = Double.valueOf(offer);
            if (basic != 1 && dbs.allSubjects().get(b).getAmount() < amount_ && isExis) {
                Toast.makeText(this, res.getString(R.string.error_lower_amount), Toast.LENGTH_LONG).show();
                isWordsNotCompleted = true;
            } else {
                if (basic == 1 && dbs.allSubjects().get(b).getCost() != unit_ && isExis) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                    alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                    alertDialog.setMessage(res.getString(R.string.alertCalcNewValue));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean isThereRow = false;
                                    for (int k = 0; k < productNames.size(); k++) {
                                        if (productNames.get(k).matches(id + "-" + name)) {
                                            Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                                            isThereRow = true;
                                            isWordsNotCompleted = true;
                                        }
                                    }
                                    if (!isThereRow) {
                                        if (!isChangeRow)
                                            productUnits.add((Double.valueOf(unit) + dbs.allSubjects().get(b).getCost()) / 2);
                                        else
                                            productUnits.set(row - 1, (Double.valueOf(unit) + dbs.allSubjects().get(b).getCost()) / 2);
                                        curSubject = id + "-" + name;
                                        curAmount_ = amount_;
                                        curOffer_ = offer_;
                                        curUnit_ = unit_;
                                        curTotal_ = curAmount_ * curUnit_;
                                        curStatement = state;
                                        curAmount = String.valueOf(curAmount_);
                                        curOffer = String.valueOf(curOffer_);
                                        curUnit = String.valueOf(curUnit_);
                                        curTotal = String.valueOf(curTotal_);
                                        addRowNext();
                                        setTotal();
                                        boolean isExi = false;
                                        for (int d = 0; d < dbs.allSubjects().size(); d++) {
                                            if (Integer.valueOf(id) == dbs.allSubjects().get(d).getId())
                                                isExi = true;
                                        }
                                        if (basic == 1 && !isExi)
                                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", Integer.valueOf(id) + 1).commit();
                                    }

                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, res.getString(R.string.alertSaveAsNewPrice),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean isThereRow = false;
                                    for (int k = 0; k < productNames.size(); k++) {
                                        if (productNames.get(k).matches(id + "-" + name)) {
                                            Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                                            isThereRow = true;
                                            isWordsNotCompleted = true;
                                        }
                                    }
                                    if (!isThereRow) {
                                        if (!isChangeRow)
                                            productUnits.add(Double.valueOf(unit));
                                        else
                                            productUnits.set(row - 1, Double.valueOf(unit));
                                        curSubject = id + "-" + name;
                                        curAmount_ = amount_;
                                        curOffer_ = offer_;
                                        curUnit_ = unit_;
                                        curTotal_ = curAmount_ * curUnit_;
                                        curStatement = state;
                                        curAmount = String.valueOf(curAmount_);
                                        curOffer = String.valueOf(curOffer_);
                                        curUnit = String.valueOf(curUnit_);
                                        curTotal = String.valueOf(curTotal_);
                                        addRowNext();
                                        setTotal();
                                        boolean isExi = false;
                                        for (int d = 0; d < dbs.allSubjects().size(); d++) {
                                            if (Integer.valueOf(id) == dbs.allSubjects().get(d).getId())
                                                isExi = true;
                                        }
                                        if (basic == 1 && !isExi)
                                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", Integer.valueOf(id) + 1).commit();
                                    }
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                    isWork = true;
                } else if (basic != 1 && dbs.allSubjects().get(b).getLock() != 0 && unit_ < dbs.allSubjects().get(b).getLock() && isExis) {
                    Toast.makeText(this, res.getString(R.string.error_lower_lock), Toast.LENGTH_LONG).show();
                    isWork = true;
                } else if (basic != 1 && unit_ < dbs.allSubjects().get(b).getCost() && isExis) {
                    Toast.makeText(this, res.getString(R.string.error_lower_net), Toast.LENGTH_LONG).show();
                    isWordsNotCompleted = true;
                    isWork = true;
                } else {
                    boolean isThereRow = false;
                    for (int k = 0; k < productNames.size(); k++) {
                        if (productNames.get(k).matches(id + "-" + name)) {
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_product_row), Toast.LENGTH_LONG).show();
                            isThereRow = true;
                            isWordsNotCompleted = true;
                            isWork = true;
                        }
                    }
                    if (!isThereRow) {
                        curSubject = id + "-" + name;
                        curAmount_ = amount_;
                        curOffer_ = offer_;
                        curUnit_ = unit_;
                        curTotal_ = curAmount_ * curUnit_;
                        curStatement = state;
                        curAmount = String.valueOf(curAmount_);
                        curOffer = String.valueOf(curOffer_);
                        curUnit = String.valueOf(curUnit_);
                        curTotal = String.valueOf(curTotal_);
                        setTotal();
                        if (!isChangeRow) {
                            productNames.add(id + "-" + name);
                            addRowNext();
                        } else {
                            productNames.set(row - 1, id + "-" + name);
                            editCurrentRow(row);
                        }
                        boolean isExi = false;
                        for (int d = 0; d < dbs.allSubjects().size(); d++) {
                            if (Integer.valueOf(id) == dbs.allSubjects().get(d).getId())
                                isExi = true;
                        }
                        if (basic == 1 && !isExi)
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId_", Integer.valueOf(id) + 1).commit();
                        isWork = true;
                    }
                }
            }
        } catch (Exception e) {
            isWordsNotCompleted = true;
            Toast.makeText(this, res.getString(R.string.error), Toast.LENGTH_LONG).show();
        }
        return isWork;
    }

    private boolean checkIsRowEmpty(int row) {
        boolean isEmpty = true;
        int clickedRow = 0;
        switch (row) {
            case 1:
                clickedRow = 1;
                if (sub1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 2:
                clickedRow = 2;
                if (sub2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 3:
                clickedRow = 3;
                if (sub3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 4:
                clickedRow = 4;
                if (sub4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 5:
                clickedRow = 5;
                if (sub5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 6:
                clickedRow = 6;
                if (sub6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 7:
                clickedRow = 7;
                if (sub7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 8:
                clickedRow = 8;
                if (sub8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 9:
                clickedRow = 9;
                if (sub9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 10:
                clickedRow = 10;
                if (sub10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 11:
                clickedRow = 11;
                if (sub11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 12:
                clickedRow = 12;
                if (sub12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 13:
                clickedRow = 13;
                if (sub13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 14:
                clickedRow = 14;
                if (sub14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 15:
                clickedRow = 15;
                if (sub15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 16:
                clickedRow = 16;
                if (sub16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 17:
                clickedRow = 17;
                if (sub17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 18:
                clickedRow = 18;
                if (sub18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 19:
                clickedRow = 19;
                if (sub19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 20:
                clickedRow = 20;
                if (sub20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 21:
                clickedRow = 21;
                if (sub21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 22:
                clickedRow = 22;
                if (sub22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 23:
                clickedRow = 23;
                if (sub23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 24:
                clickedRow = 24;
                if (sub24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 25:
                clickedRow = 25;
                if (sub25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 26:
                clickedRow = 26;
                if (sub26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 27:
                clickedRow = 27;
                if (sub27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 28:
                clickedRow = 28;
                if (sub28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 29:
                clickedRow = 29;
                if (sub29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 30:
                clickedRow = 30;
                if (sub30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 31:
                clickedRow = 31;
                if (sub31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 32:
                clickedRow = 32;
                if (sub32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 33:
                clickedRow = 33;
                if (sub33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 34:
                clickedRow = 34;
                if (sub34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 35:
                clickedRow = 35;
                if (sub35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 36:
                clickedRow = 36;
                if (sub36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 37:
                clickedRow = 37;
                if (sub37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 38:
                clickedRow = 38;
                if (sub38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 39:
                clickedRow = 39;
                if (sub39.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 40:
                clickedRow = 40;
                if (sub40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 41:
                clickedRow = 41;
                if (sub41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 42:
                clickedRow = 42;
                if (sub42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 43:
                clickedRow = 43;
                if (sub43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 44:
                clickedRow = 44;
                if (sub44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 45:
                clickedRow = 45;
                if (sub45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 46:
                clickedRow = 46;
                if (sub46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 47:
                clickedRow = 47;
                if (sub47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 48:
                clickedRow = 48;
                if (sub48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 49:
                clickedRow = 49;
                if (sub49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
            case 50:
                clickedRow = 50;
                if (sub50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    isEmpty = false;
                } else {

                }
                break;
        }
        return isEmpty;
    }

    private void deleteRow(int row) {
        boolean isThereAfter = false;
        for (int l = row; l < 50; l++) {
            if (!checkIsRowEmpty(l + 1)) {
                Toast.makeText(this, getSubjectByRow(l + 1), Toast.LENGTH_LONG).show();
                curSubject = getSubjectByRow(l + 1);
                curAmount = getAmountByRow(l + 1);
                curOffer = getOfferByRow(l + 1);
                curUnit = getUnitByRow(l + 1);
                curTotal = getTotalByRow(l + 1);
                curStatement = getStatementByRow(l + 1);
                editCurrentRow(l);
                curSubject = "";
                curUnit = "";
                curOffer = "";
                curStatement = "";
                curAmount = "";
                curTotal = "";
                editCurrentRow(l + 1);
                try {
                    productNames.set(l - 1, curSubject);
                    productUnits.set(l - 1, Double.valueOf(curUnit));
                } catch (Exception e) {

                }
                isThereAfter = true;
            }
        }
        if (!isThereAfter) {
            curSubject = "";
            curUnit = "";
            curOffer = "";
            curStatement = "";
            curAmount = "";
            curTotal = "";
            editCurrentRow(row);
        }
    }

    private String beforeWord(String s, String w) {
        String str = "";
        Pattern p = Pattern.compile("(\\w+)\\W+" + w + "\\W+(\\w+)");
        Matcher m = p.matcher(s);
        if (m.find())
            str = m.group(1);
        return str;
    }

    public static int countWords(String s) {

        int wordCount = 0;

        boolean word = false;
        int endOfLine = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetterOrDigit(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetterOrDigit(s.charAt(i)) && s.charAt(i) != '.' && s.charAt(i) != '/' && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetterOrDigit(s.charAt(i)) && i == endOfLine) {
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

    public String thirdWord(String s) {
        String s_ = s;
        if (countWords(s) > 3) {
            s_ = s.substring(s.indexOf(" ", s.indexOf(" ") + 2), s.indexOf(" ", s.indexOf(" ", s.indexOf(" ") + 2) + 2)).replaceAll(" ", "");
        } else if (countWords(s) == 3) {
            s_ = s.substring(s.indexOf(" ", s.indexOf(" ") + 2)).replaceAll(" ", "");
        }
        return s_;
    }

    public String nthWord(int n, String s) {
        String words[] = s.split("\\s+");
        for (int p = 0; p < words.length; p++) {
            words[p] = words[p].replaceAll("[^\\w]", "");
        }
        return words[n - 1].replaceAll(" ", "");
    }

    public boolean isNumeric(String strNum) {
        try {
            double d = Double.valueOf(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int clickedRow = 0;
        switch (view.getId()) {
            case R.id.row_1:
                clickedRow = 1;
                if (!sub1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_2:
                clickedRow = 2;
                if (!sub2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_3:
                clickedRow = 3;
                if (!sub3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_4:
                clickedRow = 4;
                if (!sub4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_5:
                clickedRow = 5;
                if (!sub5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_6:
                clickedRow = 6;
                if (!sub6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_7:
                clickedRow = 7;
                if (!sub7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_8:
                clickedRow = 8;
                if (!sub8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_9:
                clickedRow = 9;
                if (!sub9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_10:
                clickedRow = 10;
                if (!sub10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_11:
                clickedRow = 11;
                if (!sub11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_12:
                clickedRow = 12;
                if (!sub12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_13:
                clickedRow = 13;
                if (!sub13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_14:
                clickedRow = 14;
                if (!sub14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_15:
                clickedRow = 15;
                if (!sub15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_16:
                clickedRow = 16;
                if (!sub16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_17:
                clickedRow = 17;
                if (!sub17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_18:
                clickedRow = 18;
                if (!sub18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_19:
                clickedRow = 19;
                if (!sub19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_20:
                clickedRow = 20;
                if (!sub20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_21:
                clickedRow = 21;
                if (!sub21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_22:
                clickedRow = 22;
                if (!sub22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_23:
                clickedRow = 23;
                if (!sub23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_24:
                clickedRow = 24;
                if (!sub24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_25:
                clickedRow = 25;
                if (!sub25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_26:
                clickedRow = 26;
                if (!sub26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_27:
                clickedRow = 27;
                if (!sub27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_28:
                clickedRow = 28;
                if (!sub28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_29:
                clickedRow = 29;
                if (!sub29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_30:
                clickedRow = 30;
                if (!sub30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_31:
                clickedRow = 31;
                if (!sub31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_32:
                clickedRow = 32;
                if (!sub32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_33:
                clickedRow = 33;
                if (!sub33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_34:
                clickedRow = 34;
                if (!sub34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_35:
                clickedRow = 35;
                if (!sub35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_36:
                clickedRow = 36;
                if (!sub36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_37:
                clickedRow = 37;
                if (!sub37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_38:
                clickedRow = 38;
                if (!sub38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_39:
                clickedRow = 39;
                if (!sub39.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_40:
                clickedRow = 40;
                if (!sub40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_41:
                clickedRow = 41;
                if (!sub41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_42:
                clickedRow = 42;
                if (!sub42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_43:
                clickedRow = 43;
                if (!sub43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_44:
                clickedRow = 44;
                if (!sub44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_45:
                clickedRow = 45;
                if (!sub45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_46:
                clickedRow = 46;
                if (!sub46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_47:
                clickedRow = 47;
                if (!sub47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_48:
                clickedRow = 48;
                if (!sub48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_49:
                clickedRow = 49;
                if (!sub49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
            case R.id.row_50:
                clickedRow = 50;
                if (!sub50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    createDialog(clickedRow, true);
                } else {
                    createDialog(clickedRow, false);
                }
                break;
        }
    }

    boolean isCategoryNotExceptedChanged = false;

    boolean keepCategories = false;

    private void setOnClickListenerMethods() {
        edit_discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double dis = 0;
                double add = 0;
                if (edit_addition.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    add = Double.valueOf(edit_addition.getText().toString());
                } else {
                    add = 0;
                }
                if (edit_discount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    dis = Double.valueOf(edit_discount.getText().toString());
                } else {
                    dis = 0;
                }
                ep = add - dis;
                txt_final.setText(String.valueOf(tp + ep) + " " + code);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_addition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double add = 0;
                double dis = 0;
                if (edit_addition.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    add = Double.valueOf(edit_addition.getText().toString());
                } else {
                    add = 0;
                }
                if (edit_discount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    dis = Double.valueOf(edit_discount.getText().toString());
                } else {
                    dis = 0;
                }
                ep = add - dis;
                txt_final.setText(String.valueOf(tp + ep) + " " + code);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        voiceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pref.getBoolean("isBillVoiceControlEnable", false)) {
                    isVoiceControlingEnabled = true;
                    voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_f_));
                    pref.edit().putBoolean("isBillVoiceControlEnable", false).commit();
                    isVoiceControlingEnabled = false;
                } else {
                    isVoiceControlingEnabled = false;
                    voiceImage.setImageDrawable(res.getDrawable(R.drawable.ic_action_record_));
                    pref.edit().putBoolean("isBillVoiceControlEnable", true).commit();
                    isVoiceControlingEnabled = true;
                    billVoice();
                }
            }
        });
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProductDialog();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCategoryNotExcepted = false;
                if (basic == 1 && !isCategoryNotExceptedChanged) {
                    for (int i = 1; i <= 50; i++) {
                        for (int k = 0; k < dbs.allSubjects().size(); k++) {
                            if (getSubjectByRow(i).matches(".*[0-9a-zA-Zأ-ي]+.*") && Integer.valueOf(getSubjectByRow(i).substring(0, getSubjectByRow(i).indexOf("-")).replaceAll(" ", "")) == dbs.allSubjects().get(k).getId() && category.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && !dbs.allSubjects().get(k).getFolder().toLowerCase(Locale.getDefault()).matches(category.getText().toString().toLowerCase(Locale.getDefault()))) {
                                isCategoryNotExcepted = true;
                            }
                        }
                    }
                }
                if (!isCategoryNotExcepted) {
                    boolean isThereWrong = false;
                    if (basic == 1) {
                        if (!edit_from.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                            if (res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            isThereWrong = true;
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_from.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_from.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        }
                    } else if (basic == 2) {
                        if (!edit_to.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                            if (res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            isThereWrong = true;
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_to.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_to.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        }
                    }
                    if (!isThereWrong) {
                        boolean isThereSupCus = false;
                        if (basic == 1) {
                            for (int i = 0; i < dbsu.allSuppliersNames().size(); i++) {
                                if (dbsu.allSuppliersNames().get(i).matches(edit_from.getText().toString()))
                                    isThereSupCus = true;
                            }
                        } else if (basic == 2) {
                            for (int i = 0; i < dbc.allCustomersNames().size(); i++) {
                                if (dbc.allCustomersNames().get(i).matches(edit_to.getText().toString()))
                                    isThereSupCus = true;
                            }
                        }
                        if (isThereSupCus || basic == 3) {
                            if (bun.getBoolean("isView", false))
                                createBillNameDialog(spinBasicSelected + 1, true);
                            else
                                createBillNameDialog(spinBasicSelected + 1, false);
                        } else {
                            if (basic == 1) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                                alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                                alertDialog.setMessage(res.getString(R.string.alertNewSupplierMessage));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSupplierColumn", 0);
                                                dbsu.addSupplier(new SupplierListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_from.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSupplierColumn", column + 1).commit();
                                                if (bun.getBoolean("isView", false))
                                                    createBillNameDialog(spinBasicSelected + 1, true);
                                                else
                                                    createBillNameDialog(spinBasicSelected + 1, false);
                                            }
                                        });
                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialog.dismiss();
                                        if (bun.getBoolean("isView", false))
                                            createBillNameDialog(spinBasicSelected + 1, true);
                                        else
                                            createBillNameDialog(spinBasicSelected + 1, false);
                                    }
                                });
                                alertDialog.show();
                            } else if (basic == 2) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                                alertDialog.setTitle(res.getString(R.string.alertTitleNote));
                                alertDialog.setMessage(res.getString(R.string.alertNewCustomerMessage));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentCustomerColumn", 0);
                                                dbc.addCustomer(new CustomerListChildItem(column + 1, 0, res.getString(R.string.without_folder), edit_to.getText().toString(), "", "", "", "", res.getString(R.string.cash), res.getString(R.string.sypCode), getTime(), getDate()));
                                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentCustomerColumn", column + 1).commit();
                                                if (bun.getBoolean("isView", false))
                                                    createBillNameDialog(spinBasicSelected + 1, true);
                                                else
                                                    createBillNameDialog(spinBasicSelected + 1, false);
                                            }
                                        });
                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialog.dismiss();
                                        if (bun.getBoolean("isView", false))
                                            createBillNameDialog(spinBasicSelected + 1, true);
                                        else
                                            createBillNameDialog(spinBasicSelected + 1, false);
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                    }
                } else {
                    final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                    alertDialog.setTitle(res.getString(R.string.alertTitle));
                    alertDialog.setMessage(res.getString(R.string.alertChangedCategory));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    keepCategories = false;
                                    isCategoryNotExceptedChanged = true;
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, res.getString(R.string.alertStayCategory),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    keepCategories = true;
                                    isCategoryNotExceptedChanged = true;
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            isCategoryNotExceptedChanged = false;
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
        row1.setOnClickListener(this);
        row2.setOnClickListener(this);
        row3.setOnClickListener(this);
        row4.setOnClickListener(this);
        row5.setOnClickListener(this);
        row6.setOnClickListener(this);
        row7.setOnClickListener(this);
        row8.setOnClickListener(this);
        row9.setOnClickListener(this);
        row10.setOnClickListener(this);
        row11.setOnClickListener(this);
        row12.setOnClickListener(this);
        row13.setOnClickListener(this);
        row14.setOnClickListener(this);
        row15.setOnClickListener(this);
        row16.setOnClickListener(this);
        row17.setOnClickListener(this);
        row18.setOnClickListener(this);
        row19.setOnClickListener(this);
        row20.setOnClickListener(this);
        row21.setOnClickListener(this);
        row22.setOnClickListener(this);
        row23.setOnClickListener(this);
        row24.setOnClickListener(this);
        row25.setOnClickListener(this);
        row26.setOnClickListener(this);
        row27.setOnClickListener(this);
        row28.setOnClickListener(this);
        row29.setOnClickListener(this);
        row30.setOnClickListener(this);
        row31.setOnClickListener(this);
        row32.setOnClickListener(this);
        row33.setOnClickListener(this);
        row34.setOnClickListener(this);
        row35.setOnClickListener(this);
        row36.setOnClickListener(this);
        row37.setOnClickListener(this);
        row38.setOnClickListener(this);
        row39.setOnClickListener(this);
        row40.setOnClickListener(this);
        row41.setOnClickListener(this);
        row42.setOnClickListener(this);
        row43.setOnClickListener(this);
        row44.setOnClickListener(this);
        row45.setOnClickListener(this);
        row46.setOnClickListener(this);
        row47.setOnClickListener(this);
        row48.setOnClickListener(this);
        row49.setOnClickListener(this);
        row50.setOnClickListener(this);
    }

    private void createProductDialog() {
        childPCheckStates = null;
        groupPCheckStates = null;
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_bill_product);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        searchPList = (ListView) dialog.findViewById(R.id.list_subjects_search);
        searchPView = (SearchView) dialog.findViewById(R.id.edit_search_subjects);
        expPListView = (ExpandableListView) dialog.findViewById(R.id.subjectsList);
        checkSellectAll = (AppCompatCheckBox) dialog.findViewById(R.id.check_sellectAll);
        enterProducts = (TextView) dialog.findViewById(R.id.enterProduct);
        imgPBack = (ImageView) dialog.findViewById(R.id.imageBack);
        productsListP = new ArrayList<>();
        amountsListP = new ArrayList<>();
        offersListP = new ArrayList<>();
        unitsListP = new ArrayList<>();
        totalsListP = new ArrayList<>();
        statesListP = new ArrayList<>();
        checkSellectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isPAllCheckedProg) {
                    List<String> folders1 = dbs.allFolders();
                    List<SubjectListChildItem> subjects1 = dbs.allSubjects();
                    boolean x1[] = new boolean[folders1.size()];
                    for (int i = 0; i < folders1.size(); i++) {
                        x1[i] = expPListView.isGroupExpanded(i);
                    }
                    int index = expPListView.getFirstVisiblePosition();
                    View v = expPListView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - expPListView.getPaddingTop());


                    String[] itemsFolders1 = new String[folders1.size()];
                    for (int i = 0; i < folders1.size(); i++) {
                        itemsFolders1[i] = folders1.get(i);
                    }
                    int[] itemsSubjectsColumns1 = new int[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsColumns1[i] = subjects1.get(i).getColumn();
                    }
                    int[] itemsSubjectsIds1 = new int[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsIds1[i] = subjects1.get(i).getId();
                    }
                    String[] itemsSubjectsNames1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsNames1[i] = subjects1.get(i).getName();
                    }
                    double[] itemsSubjectsAmounts1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsAmounts1[i] = subjects1.get(i).getAmount();
                    }
                    double[] itemsSubjectsAmountLasts1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsAmountLasts1[i] = subjects1.get(i).getAmountLast();
                    }
                    double[] itemsSubjectsAmountLocks1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsAmountLocks1[i] = subjects1.get(i).getAmountLock();
                    }
                    double[] itemsSubjectsCosts1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsCosts1[i] = subjects1.get(i).getCost();
                    }
                    double[] itemsSubjectsLocks1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsLocks1[i] = subjects1.get(i).getLock();
                    }
                    double[] itemsSubjectsLasts1 = new double[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsLasts1[i] = subjects1.get(i).getLast();
                    }
                    String[] itemsSubjectsSpecs1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsSpecs1[i] = subjects1.get(i).getSpec();
                    }
                    String[] itemsSubjectsCodes1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsCodes1[i] = subjects1.get(i).getCode();
                    }
                    String[] itemsSubjectsBuyers1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsBuyers1[i] = subjects1.get(i).getBuyer();
                    }
                    String[] itemsSubjectsTimes1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsTimes1[i] = subjects1.get(i).getTime();
                    }
                    String[] itemsSubjectsDates1 = new String[subjects1.size()];
                    for (int i = 0; i < subjects1.size(); i++) {
                        itemsSubjectsDates1[i] = subjects1.get(i).getDate();
                    }

                    // Setting up list
                    listPGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders1));
                    listPChildData = new HashMap<String, List<SubjectListChildItem>>();
                    // Adding district names and number of population as children
                    for (int i1 = 0; i1 < listPGroupTitles.size(); i1++) {
                        String folder = itemsFolders1[i1];
                        List<SubjectListChildItem> pDistricts = new ArrayList<SubjectListChildItem>();
                        for (int i = 0; i < subjects1.size(); i++) {
                            if (subjects1.get(i).getFolder().matches(folder)) {
                                pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns1[i], itemsSubjectsIds1[i], itemsSubjectsBuyers1[i], folder, itemsSubjectsNames1[i], itemsSubjectsAmounts1[i], itemsSubjectsAmountLasts1[i], itemsSubjectsAmountLocks1[i], itemsSubjectsCosts1[i], itemsSubjectsLasts1[i], itemsSubjectsLocks1[i], itemsSubjectsSpecs1[i], itemsSubjectsCodes1[i], itemsSubjectsTimes1[i], itemsSubjectsDates1[i]));
                            }
                        }
                        listPChildData.put(folder, pDistricts);
                    }
                    groupPCheckStates = new ArrayList<>();
                    childPCheckStates = new HashMap<>();
                    for (int i = 0; i < listPGroupTitles.size(); i++) {
                        groupPCheckStates.add(b);
                        ArrayList<Boolean> a = new ArrayList<>();
                        for (int j = 0; j < listPChildData.get(listPGroupTitles.get(i)).size(); j++) {
                            a.add(b);
                        }
                        childPCheckStates.put(i, a);
                    }
                    expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                    expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                    // Setting list adapter
                    expPListView.setAdapter(expandablePListAdapter);
                    //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                    for (int i = 0; i < folders1.size(); i++) {
                        if (x1[i])
                            expPListView.expandGroup(i);
                    }
                    expPListView.setSelectionFromTop(index, top);
                    setPNumberSelected();
                }
            }
        });
        enterProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedItems = 0;
                for (int i = 0; i < listPGroupTitles.size(); i++) {
                    for (int j = 0; j < listPChildData.get(listPGroupTitles.get(i)).size(); j++) {
                        if (childPCheckStates.get(i).get(j)) {
                            SubjectListChildItem s = listPChildData.get(listPGroupTitles.get(i)).get(j);
                            boolean isExist = false;
                            for (int x = 1; x < 51; x++) {
                                if (getSubjectByRow(x).contains("-") && Integer.valueOf(getSubjectByRow(x).substring(0, getSubjectByRow(x).indexOf("-"))) == s.getId())
                                    isExist = true;
                            }
                            if (!isExist) {
                                checkedItems++;
                                productsListP.add(String.valueOf(s.getId()) + "-" + s.getName());
                                double amount, unit;
                                if (s.getAmountLast() != 0)
                                    amount = s.getAmountLast();
                                else if (s.getAmountLock() != 0)
                                    amount = s.getAmountLock();
                                else
                                    amount = 1.0;
                                if (s.getLast() != 0)
                                    unit = s.getLast();
                                else if (s.getLock() != 0)
                                    unit = s.getLock();
                                else
                                    unit = s.getCost() + 50;
                                amountsListP.add(amount);
                                unitsListP.add(unit);
                                offersListP.add(0.0);
                                totalsListP.add(unit * amount);
                                statesListP.add("");
                            }
                        }
                    }
                }
                for (int i = 0; i < productsListP.size(); i++) {
                    productNames.add(productsListP.get(i));
                    curSubject = productsListP.get(i);
                    curAmount = String.valueOf(amountsListP.get(i));
                    curUnit = String.valueOf(unitsListP.get(i));
                    curOffer = String.valueOf(offersListP.get(i));
                    curTotal = String.valueOf(totalsListP.get(i));
                    curStatement = statesListP.get(i);
                    addRowNext();
                }
                dialog.cancel();
                setTotal();
            }
        });
        imgPBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expPListView.getVisibility() == VISIBLE)
                    dialog.cancel();
                else {
                    expPListView.setVisibility(View.VISIBLE);
                    createPListFromSQL(dialog);
                    searchPList.setVisibility(View.GONE);
                }
            }
        });
        createPListFromSQL(dialog);
        setPNumberSelected();
        listAllSubjects = new ArrayList<SubjectListChildItem>();
        listAllSubjects.addAll(dbs.allSubjects());
        customSearchSubjectsAdapter = new CustomSearchSubjectsCheckAdapter(this, R.layout.list_subjects_item, listAllSubjects, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
        searchPList.setAdapter(customSearchSubjectsAdapter);
        searchPView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    expPListView.setVisibility(View.GONE);
                    searchPList.setVisibility(View.VISIBLE);
                    String text = s;
                    customSearchSubjectsAdapter.filter(text);
                    List<String> folders = dbs.allFolders();
                    xPExp = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        xPExp[i] = expPListView.isGroupExpanded(i);
                    }
                    indexPExp = expPListView.getFirstVisiblePosition();
                    vPExp = expPListView.getChildAt(0);
                    topPExp = (vPExp == null) ? 0 : (vPExp.getTop() - expPListView.getPaddingTop());
                } else {
                    expPListView.setVisibility(View.VISIBLE);
                    createPListFromSQL(dialog);
                    searchPList.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.matches(".*[a-zA-Z0-9أ-ي]+.*")) {
                    expPListView.setVisibility(View.GONE);
                    searchPList.setVisibility(View.VISIBLE);
                    String text = s;
                    customSearchSubjectsAdapter.filter(text);
                    List<String> folders = dbs.allFolders();
                    xPExp = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        xPExp[i] = expPListView.isGroupExpanded(i);
                    }
                    indexPExp = expPListView.getFirstVisiblePosition();
                    vPExp = expPListView.getChildAt(0);
                    topPExp = (vPExp == null) ? 0 : (vPExp.getTop() - expPListView.getPaddingTop());
                } else {
                    expPListView.setVisibility(View.VISIBLE);
                    createPListFromSQL(dialog);
                    searchPList.setVisibility(View.GONE);
                }

                return false;
            }
        });
        searchPList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) BillActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_subjects_item, null);
                }
                TextView subjectName = (TextView) convertView.findViewById(R.id.subjectName);
                CheckBox check = (CheckBox) convertView.findViewById(R.id.subjectCheck);
                String name = subjectName.getText().toString();
                SubjectListChildItem SLCI = dbs.getSubjectById(Integer.valueOf(name.substring(0, 5).replace(" ", "")));
                List<String> folders = dbs.allFolders();
                for (int k = 0; k < folders.size(); k++) {
                    for (int m = 0; m < listPChildData.get(folders.get(k)).size(); m++) {
                        if (listPChildData.get(folders.get(k)).get(m).getColumn() == SLCI.getColumn()) {
                            ArrayList<Boolean> a = childPCheckStates.get(k);
                            a.set(m, !check.isChecked());
                            childPCheckStates.put(k, a);
                        }
                    }
                }
                int indexPSearch = searchPList.getFirstVisiblePosition();
                View vPSearch = searchPList.getChildAt(0);
                int topPSearch = (vPSearch == null) ? 0 : (vPSearch.getTop() - searchPList.getPaddingTop());
                String text = searchPView.getQuery().toString();
                listAllSubjects = new ArrayList<SubjectListChildItem>();
                listAllSubjects.addAll(dbs.allSubjects());
                customSearchSubjectsAdapter = new CustomSearchSubjectsCheckAdapter(BillActivity.this, R.layout.list_subjects_item, listAllSubjects, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                searchPList.setAdapter(customSearchSubjectsAdapter);
                searchPView.setQuery(text, true);
                searchPList.setSelectionFromTop(indexPSearch, topPSearch);
                setPNumberSelected();
                /**currentColumn = SLCI.getColumn();
                 currentId = SLCI.getId();
                 currentFolder = SLCI.getFolder();
                 currentName = SLCI.getName();
                 currentAmount = SLCI.getAmount();
                 currentAmountLast = SLCI.getAmountLast();
                 currentAmountLock = SLCI.getAmountLock();
                 currentCost = SLCI.getCost();
                 currentLast = SLCI.getLast();
                 currentLock = SLCI.getLock();
                 currentSpec = SLCI.getSpec();
                 currentCode = SLCI.getCode();
                 currentBuyer = SLCI.getBuyer();
                 currentTime = SLCI.getTime();
                 currentDate = SLCI.getTime();**/
            }
        });
        searchPList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) BillActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_subjects_item, null);
                }
                TextView subjectName = (TextView) convertView.findViewById(R.id.subjectName);
                TextView subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
                TextView subjectCost = (TextView) convertView.findViewById(R.id.subjectCost);
                TextView subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
                String name = subjectName.getText().toString();
                SubjectListChildItem SLCI = dbs.getSubjectById(Integer.valueOf(name.substring(0, 5).replace(" ", "")));
                //createPopupChildItemMenu(convertView, SLCI);
                return true;
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.show();
    }

    void createPListFromSQL(Dialog view) {
        // list all subjects
        List<SubjectListChildItem> subjects = dbs.allSubjects();
        List<String> folders = dbs.allFolders();
        if (subjects != null) {
            createPList(folders, subjects, view);
        }
    }

    private void createPList(final List<String> folders, final List<SubjectListChildItem> subjects, final Dialog view) {
        // Get the expandable list
        expPListView = (ExpandableListView) view.findViewById(R.id.subjectsList);
        if (childPCheckStates == null) {
            childPCheckStates = new HashMap<>();
            groupPCheckStates = new ArrayList<Boolean>();
            int max = 0;
            for (int i = 0; i < folders.size(); i++) {
                int a = 0;
                for (int b = 0; b < subjects.size(); b++) {
                    if (subjects.get(b).getFolder().matches(folders.get(i)))
                        a++;
                }
                if (a > max)
                    max = a;
            }
            for (int i = 0; i < folders.size(); i++) {
                ArrayList<Boolean> a = new ArrayList<>();
                for (int b = 0; b < max; b++) {
                    a.add(false);
                }
                childPCheckStates.put(i, a);
            }
            for (int k = 0; k < folders.size(); k++) {
                groupPCheckStates.add(false);
            }
        }
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
        }
        int[] itemsSubjectsColumns = new int[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsColumns[i] = subjects.get(i).getColumn();
        }
        int[] itemsSubjectsIds = new int[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsIds[i] = subjects.get(i).getId();
        }
        String[] itemsSubjectsNames = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsNames[i] = subjects.get(i).getName();
        }
        double[] itemsSubjectsAmounts = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsAmounts[i] = subjects.get(i).getAmount();
        }
        double[] itemsSubjectsAmountLasts = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsAmountLasts[i] = subjects.get(i).getAmountLast();
        }
        double[] itemsSubjectsAmountLocks = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsAmountLocks[i] = subjects.get(i).getAmountLock();
        }
        double[] itemsSubjectsCosts = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsCosts[i] = subjects.get(i).getCost();
        }
        double[] itemsSubjectsLocks = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsLocks[i] = subjects.get(i).getLock();
        }
        double[] itemsSubjectsLasts = new double[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsLasts[i] = subjects.get(i).getLast();
        }
        String[] itemsSubjectsSpecs = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsSpecs[i] = subjects.get(i).getSpec();
        }
        String[] itemsSubjectsCodes = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsCodes[i] = subjects.get(i).getCode();
        }
        String[] itemsSubjectsBuyers = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsBuyers[i] = subjects.get(i).getBuyer();
        }
        String[] itemsSubjectsTimes = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsTimes[i] = subjects.get(i).getTime();
        }
        String[] itemsSubjectsDates = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsDates[i] = subjects.get(i).getDate();
        }

        // Setting up list
        listPGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listPChildData = new HashMap<String, List<SubjectListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listPGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getFolder().matches(folder)) {
                    pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                }
            }
            listPChildData.put(folder, pDistricts);
        }
        for (int i = 0; i < folders.size(); i++) {
            boolean isAll = true;
            for (int j = 0; j < listPChildData.get(folders.get(i)).size(); j++) {
                if (!childPCheckStates.get(i).get(j)) {
                    isAll = false;
                    break;
                }
            }
            if (isAll)
                groupPCheckStates.set(i, true);
            else
                groupPCheckStates.set(i, false);
        }
        expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
        // Setting list adapter
        expPListView.setAdapter(expandablePListAdapter);
        if (vPExp != null) {
            for (int i = 0; i < folders.size(); i++) {
                if (xPExp[i])
                    expPListView.expandGroup(i);
            }
            expPListView.setSelectionFromTop(indexPExp, topPExp);
        }
        expPListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                List<SubjectListChildItem> subjects = dbs.allSubjects();
                List<String> folders = dbs.allFolders();
                boolean x[] = new boolean[folders.size()];
                for (int i = 0; i < folders.size(); i++) {
                    x[i] = expPListView.isGroupExpanded(i);
                }
                if (childPCheckStates.get(1).get(0))
                    Toast.makeText(BillActivity.this, "0true", Toast.LENGTH_LONG).show();
                CheckBox check = (CheckBox) view.findViewById(R.id.subjectCheck);
                ArrayList<Boolean> a = childPCheckStates.get(groupPosition);
                a.set(childPosition, !check.isChecked());
                childPCheckStates.put(groupPosition, a);
                if (childPCheckStates.get(1).get(0))
                    Toast.makeText(BillActivity.this, "1true", Toast.LENGTH_LONG).show();
                int index = expPListView.getFirstVisiblePosition();
                View v = expPListView.getChildAt(0);
                int top = (v == null) ? 0 : (v.getTop() - expPListView.getPaddingTop());
                for (int i = 0; i < folders.size(); i++) {
                    boolean isAll = true;
                    for (int j = 0; j < listPChildData.get(folders.get(i)).size(); j++) {
                        if (!childPCheckStates.get(i).get(j)) {
                            isAll = false;
                            break;
                        }
                    }
                    if (isAll)
                        groupPCheckStates.set(i, true);
                    else
                        groupPCheckStates.set(i, false);
                }
                /**Toast.makeText(context,"click " + String.valueOf(groupPosition) + "-" + String.valueOf(childPosition),Toast.LENGTH_LONG).show();
                 for (int i = 0; i < groupCheckStates.size(); i++) {
                 for (int j = 0; j < childCheckStates.get(i).size(); j++)
                 if (childCheckStates.get(i).get(j))
                 Toast.makeText(context, String.valueOf(i) + "-" + String.valueOf(j), Toast.LENGTH_LONG).show();
                 }**/
                String[] itemsFolders = new String[folders.size()];
                for (int i = 0; i < folders.size(); i++) {
                    itemsFolders[i] = folders.get(i);
                }
                int[] itemsSubjectsColumns = new int[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsColumns[i] = subjects.get(i).getColumn();
                }
                int[] itemsSubjectsIds = new int[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsIds[i] = subjects.get(i).getId();
                }
                String[] itemsSubjectsNames = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsNames[i] = subjects.get(i).getName();
                }
                double[] itemsSubjectsAmounts = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsAmounts[i] = subjects.get(i).getAmount();
                }
                double[] itemsSubjectsAmountLasts = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsAmountLasts[i] = subjects.get(i).getAmountLast();
                }
                double[] itemsSubjectsAmountLocks = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsAmountLocks[i] = subjects.get(i).getAmountLock();
                }
                double[] itemsSubjectsCosts = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsCosts[i] = subjects.get(i).getCost();
                }
                double[] itemsSubjectsLocks = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsLocks[i] = subjects.get(i).getLock();
                }
                double[] itemsSubjectsLasts = new double[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsLasts[i] = subjects.get(i).getLast();
                }
                String[] itemsSubjectsSpecs = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsSpecs[i] = subjects.get(i).getSpec();
                }
                String[] itemsSubjectsCodes = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsCodes[i] = subjects.get(i).getCode();
                }
                String[] itemsSubjectsBuyers = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsBuyers[i] = subjects.get(i).getBuyer();
                }
                String[] itemsSubjectsTimes = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsTimes[i] = subjects.get(i).getTime();
                }
                String[] itemsSubjectsDates = new String[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsDates[i] = subjects.get(i).getDate();
                }

                // Setting up list
                listPGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                listPChildData = new HashMap<String, List<SubjectListChildItem>>();
                // Adding district names and number of population as children
                for (int i1 = 0; i1 < listPGroupTitles.size(); i1++) {
                    String folder = itemsFolders[i1];
                    List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                    for (int i = 0; i < subjects.size(); i++) {
                        if (subjects.get(i).getFolder().matches(folder)) {
                            pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                        }
                    }
                    listPChildData.put(folder, pDistricts);
                }
                expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                // Setting list adapter

                expPListView.setAdapter(expandablePListAdapter);
                if (childPCheckStates.get(1).get(0))
                    Toast.makeText(BillActivity.this, "2true", Toast.LENGTH_LONG).show();
                //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                for (int i = 0; i < folders.size(); i++) {
                    if (x[i])
                        expPListView.expandGroup(i);
                }
                expPListView.setSelectionFromTop(index, top);
                setPNumberSelected();
                if (childPCheckStates.get(1).get(0))
                    Toast.makeText(BillActivity.this, "3true", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        expPListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                    List<SubjectListChildItem> subjects = dbs.allSubjects();
                    List<String> folders = dbs.allFolders();
                    boolean x[] = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        x[i] = expPListView.isGroupExpanded(i);
                    }
                    CheckBox check = (CheckBox) view.findViewById(R.id.subjectCheck);
                    ArrayList<Boolean> a = childPCheckStates.get(groupPos);
                    a.set(childPos, !check.isChecked());
                    childPCheckStates.put(groupPos, a);
                    int index = expPListView.getFirstVisiblePosition();
                    View v = expPListView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - expPListView.getPaddingTop());
                    for (int i = 0; i < folders.size(); i++) {
                        boolean isAll = true;
                        for (int j = 0; j < listPChildData.get(folders.get(i)).size(); j++) {
                            if (!childPCheckStates.get(i).get(j)) {
                                isAll = false;
                                break;
                            }
                        }
                        if (isAll)
                            groupPCheckStates.set(i, true);
                        else
                            groupPCheckStates.set(i, false);
                    }
                    /**Toast.makeText(context,"click " + String.valueOf(groupPosition) + "-" + String.valueOf(childPosition),Toast.LENGTH_LONG).show();
                     for (int i = 0; i < groupCheckStates.size(); i++) {
                     for (int j = 0; j < childCheckStates.get(i).size(); j++)
                     if (childCheckStates.get(i).get(j))
                     Toast.makeText(context, String.valueOf(i) + "-" + String.valueOf(j), Toast.LENGTH_LONG).show();
                     }**/
                    String[] itemsFolders = new String[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        itemsFolders[i] = folders.get(i);
                    }
                    int[] itemsSubjectsColumns = new int[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsColumns[i] = subjects.get(i).getColumn();
                    }
                    int[] itemsSubjectsIds = new int[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsIds[i] = subjects.get(i).getId();
                    }
                    String[] itemsSubjectsNames = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsNames[i] = subjects.get(i).getName();
                    }
                    double[] itemsSubjectsAmounts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmounts[i] = subjects.get(i).getAmount();
                    }
                    double[] itemsSubjectsAmountLasts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmountLasts[i] = subjects.get(i).getAmountLast();
                    }
                    double[] itemsSubjectsAmountLocks = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmountLocks[i] = subjects.get(i).getAmountLock();
                    }
                    double[] itemsSubjectsCosts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsCosts[i] = subjects.get(i).getCost();
                    }
                    double[] itemsSubjectsLocks = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsLocks[i] = subjects.get(i).getLock();
                    }
                    double[] itemsSubjectsLasts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsLasts[i] = subjects.get(i).getLast();
                    }
                    String[] itemsSubjectsSpecs = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsSpecs[i] = subjects.get(i).getSpec();
                    }
                    String[] itemsSubjectsCodes = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsCodes[i] = subjects.get(i).getCode();
                    }
                    String[] itemsSubjectsBuyers = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsBuyers[i] = subjects.get(i).getBuyer();
                    }
                    String[] itemsSubjectsTimes = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsTimes[i] = subjects.get(i).getTime();
                    }
                    String[] itemsSubjectsDates = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsDates[i] = subjects.get(i).getDate();
                    }

                    // Setting up list
                    listPGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                    listPChildData = new HashMap<String, List<SubjectListChildItem>>();
                    // Adding district names and number of population as children
                    for (int i1 = 0; i1 < listPGroupTitles.size(); i1++) {
                        String folder = itemsFolders[i1];
                        List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                        for (int i = 0; i < subjects.size(); i++) {
                            if (subjects.get(i).getFolder().matches(folder)) {
                                pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                            }
                        }
                        listPChildData.put(folder, pDistricts);
                    }
                    expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                    // Setting list adapter
                    expPListView.setAdapter(expandablePListAdapter);
                    //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                    for (int i = 0; i < folders.size(); i++) {
                        if (x[i])
                            expPListView.expandGroup(i);
                    }
                    expPListView.setSelectionFromTop(index, top);
                    setPNumberSelected();
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    int index = expPListView.getFirstVisiblePosition();
                    View v = expPListView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - expPListView.getPaddingTop());
                    boolean x[] = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        x[i] = expPListView.isGroupExpanded(i);
                    }
                    CheckBox check = (CheckBox) view.findViewById(R.id.groupSubjectCheck);
                    for (int i = 0; i < childPCheckStates.get(groupPos).size(); i++) {
                        ArrayList<Boolean> a = childPCheckStates.get(groupPos);
                        a.set(i, !check.isChecked());
                        childPCheckStates.put(groupPos, a);
                    }
                    groupPCheckStates.set(groupPos, !check.isChecked());
                    String[] itemsFolders = new String[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        itemsFolders[i] = folders.get(i);
                    }
                    int[] itemsSubjectsColumns = new int[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsColumns[i] = subjects.get(i).getColumn();
                    }
                    int[] itemsSubjectsIds = new int[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsIds[i] = subjects.get(i).getId();
                    }
                    String[] itemsSubjectsNames = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsNames[i] = subjects.get(i).getName();
                    }
                    double[] itemsSubjectsAmounts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmounts[i] = subjects.get(i).getAmount();
                    }
                    double[] itemsSubjectsAmountLasts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmountLasts[i] = subjects.get(i).getAmountLast();
                    }
                    double[] itemsSubjectsAmountLocks = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsAmountLocks[i] = subjects.get(i).getAmountLock();
                    }
                    double[] itemsSubjectsCosts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsCosts[i] = subjects.get(i).getCost();
                    }
                    double[] itemsSubjectsLocks = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsLocks[i] = subjects.get(i).getLock();
                    }
                    double[] itemsSubjectsLasts = new double[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsLasts[i] = subjects.get(i).getLast();
                    }
                    String[] itemsSubjectsSpecs = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsSpecs[i] = subjects.get(i).getSpec();
                    }
                    String[] itemsSubjectsCodes = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsCodes[i] = subjects.get(i).getCode();
                    }
                    String[] itemsSubjectsBuyers = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsBuyers[i] = subjects.get(i).getBuyer();
                    }
                    String[] itemsSubjectsTimes = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsTimes[i] = subjects.get(i).getTime();
                    }
                    String[] itemsSubjectsDates = new String[subjects.size()];
                    for (int i = 0; i < subjects.size(); i++) {
                        itemsSubjectsDates[i] = subjects.get(i).getDate();
                    }

                    // Setting up list
                    listPGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                    listPChildData = new HashMap<String, List<SubjectListChildItem>>();
                    // Adding district names and number of population as children
                    for (int i1 = 0; i1 < listPGroupTitles.size(); i1++) {
                        String folder = itemsFolders[i1];
                        List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                        for (int i = 0; i < subjects.size(); i++) {
                            if (subjects.get(i).getFolder().matches(folder)) {
                                pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                            }
                        }
                        listPChildData.put(folder, pDistricts);
                    }
                    expandablePListAdapter = new CustomSubjectExpandableListAdapter(BillActivity.this, listPGroupTitles, listPChildData, childPCheckStates, groupPCheckStates,basic);
                    // Setting list adapter
                    expPListView.setAdapter(expandablePListAdapter);
                    for (int i = 0; i < folders.size(); i++) {
                        if (x[i])
                            expPListView.expandGroup(i);
                    }
                    expPListView.setSelectionFromTop(index, top);
                }
                setPNumberSelected();
                return true;
            }
        });

    }

    private void setPNumberSelected() {
        int numberChecked = 0;
        boolean isAll = true;
        for (int i = 0; i < listPGroupTitles.size(); i++) {
            for (int j = 0; j < listPChildData.get(listPGroupTitles.get(i)).size(); j++) {
                if (childPCheckStates.get(i).get(j))
                    numberChecked++;
                else
                    isAll = false;
            }
        }
        if (!checkSellectAll.getText().toString().contains("("))
            checkSellectAll.setText(checkSellectAll.getText() + "(" + String.valueOf(numberChecked) + ")");
        else
            checkSellectAll.setText(getResources().getString(R.string.all) + "(" + String.valueOf(numberChecked) + ")");
        if (isAll)
            checkSellectAll.setChecked(true);
        else {
            isPAllCheckedProg = true;
            checkSellectAll.setChecked(false);
            isPAllCheckedProg = false;
        }
    }

    private void createBillNameDialog(final int type, final boolean isEdit) {
        if (code.matches(res.getString(R.string.sypCode))) {
            Total = Double.valueOf(txt_total.getText().toString().substring(0, txt_total.getText().toString().indexOf(res.getString(R.string.first_let_of_syp)) - 2));
            Final = Double.valueOf(txt_final.getText().toString().substring(0, txt_final.getText().toString().indexOf(res.getString(R.string.first_let_of_syp)) - 2));
        } else if (code.matches("$")) {
            Total = Double.valueOf(txt_total.getText().toString().substring(0, txt_total.getText().toString().indexOf("$") - 2));
            Final = Double.valueOf(txt_final.getText().toString().substring(0, txt_final.getText().toString().indexOf("$") - 2));
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BillActivity.this);
        if (basic == 1) {
            alertDialog.setTitle(res.getString(R.string.bill_name_edit));
            alertDialog.setIcon(R.drawable.con_pur_bill);
        } else if (basic == 2) {
            alertDialog.setTitle(res.getString(R.string.invoice_name_edit));
            alertDialog.setIcon(R.drawable.con_sales_bill);
        } else {
            alertDialog.setTitle(res.getString(R.string.draft_name_edit));
            alertDialog.setIcon(R.drawable.con_tem_bill);
        }

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout = layoutInflater.inflate(R.layout.dialog_bill_name, null);
        final EditText edit = (EditText) layout.findViewById(R.id.dialog_edit_bill_name);
        final EditText edit1 = (EditText) layout.findViewById(R.id.dialog_edit_bill_code);
        final CheckBox check = (CheckBox) layout.findViewById(R.id.dialog_check_bill_name);
        alertDialog.setView(layout);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int k, int i1, int i2) {
                if (!bun.getBoolean("isView", false)) {
                    List<BillListChildItem> bills = db.allBills();
                    for (int i = 0; i < db.allBills().size(); i++) {
                        BillListChildItem bill = bills.get(i);
                        if (bill.getName().matches(charSequence.toString())) {
                            if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic"))
                                edit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_bill_name), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int k, int i1, int i2) {
                if (!bun.getBoolean("isView", false)) {
                    List<BillListChildItem> bills = db.allBills();
                    for (int i = 0; i < db.allBills().size(); i++) {
                        BillListChildItem bill = bills.get(i);
                        if (String.valueOf(bill.getCode()).matches(charSequence.toString())) {
                            if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic"))
                                edit1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(BillActivity.this, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (bun.getBoolean("isView", false)) {
            edit1.setText(String.valueOf(bill.getCode()));
            edit.setText(bill.getName());
        } else {
            int id = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillId", 10001);
            int num1 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillNum", 1);
            int num2 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentInvoiceNum", 1);
            int num3 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentDraftNum", 1);
            edit1.setText(String.valueOf(id));

            if (basic == 1) {
                edit.setText(res.getString(R.string.bill) + "_" + String.valueOf(num1));
            } else if (basic == 2) {
                edit.setText(res.getString(R.string.invoice) + "_" + String.valueOf(num2));
            } else {
                edit.setText(res.getString(R.string.draft) + "_" + String.valueOf(num3));
            }

        }

        alertDialog.setPositiveButton(res.getString(R.string.dialog_save),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isBillNameExist = false;
                        boolean isBillIdExist = false;
                        if (!bun.getBoolean("isView", false)) {
                            List<BillListChildItem> bills = db.allBills();
                            for (int i = 0; i < db.allBills().size(); i++) {
                                BillListChildItem bill = bills.get(i);
                                if (bill.getName().matches(edit.getText().toString())) {
                                    if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic"))
                                        edit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                    else
                                        edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                                    Toast.makeText(BillActivity.this, res.getString(R.string.error_bill_name), Toast.LENGTH_LONG).show();
                                    isBillNameExist = true;
                                }
                            }
                            for (int i = 0; i < db.allBills().size(); i++) {
                                BillListChildItem bill = bills.get(i);
                                if (String.valueOf(bill.getCode()).matches(edit1.getText().toString())) {
                                    if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic"))
                                        edit1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                    else
                                        edit1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                                    Toast.makeText(BillActivity.this, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
                                    isBillIdExist = true;
                                }
                            }
                        }
                        if (edit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && edit1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && !isBillNameExist && !isBillIdExist) {
                            if (bun.getBoolean("isView", false) && !bun.getBoolean("isExtractItems", false)) {
                                String from = "", to = "";
                                if (spinBasicSelected + 1 == 1) {
                                    from = edit_from.getText().toString();
                                    to = spin_point_.getSelectedItem().toString();
                                } else if (spinBasicSelected + 1 == 2) {
                                    from = spin_point.getSelectedItem().toString();
                                    to = edit_to.getText().toString();
                                } else {
                                    from = edit_from_t.getText().toString();
                                    if (!edit_to_t.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*") && !edit_from_t.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                        to = res.getString(R.string.without_folder);
                                    } else {
                                        to = edit_to_t.getText().toString();
                                    }
                                }
                                double A1 = 0, A2 = 0, A3 = 0, A4 = 0, A5 = 0, A6 = 0, A7 = 0, A8 = 0, A9 = 0, A10 = 0, A11 = 0, A12 = 0, A13 = 0, A14 = 0, A15 = 0, A16 = 0, A17 = 0, A18 = 0, A19 = 0, A20 = 0, A21 = 0, A22 = 0, A23 = 0, A24 = 0, A25 = 0, A26 = 0, A27 = 0, A28 = 0, A29 = 0, A30 = 0, A31 = 0, A32 = 0, A33 = 0, A34 = 0, A35 = 0, A36 = 0, A37 = 0, A38 = 0, A39 = 0, A40 = 0, A41 = 0, A42 = 0, A43 = 0, A44 = 0, A45 = 0, A46 = 0, A47 = 0, A48 = 0, A49 = 0, A50 = 0;
                                double O1 = 0, O2 = 0, O3 = 0, O4 = 0, O5 = 0, O6 = 0, O7 = 0, O8 = 0, O9 = 0, O10 = 0, O11 = 0, O12 = 0, O13 = 0, O14 = 0, O15 = 0, O16 = 0, O17 = 0, O18 = 0, O19 = 0, O20 = 0, O21 = 0, O22 = 0, O23 = 0, O24 = 0, O25 = 0, O26 = 0, O27 = 0, O28 = 0, O29 = 0, O30 = 0, O31 = 0, O32 = 0, O33 = 0, O34 = 0, O35 = 0, O36 = 0, O37 = 0, O38 = 0, O39 = 0, O40 = 0, O41 = 0, O42 = 0, O43 = 0, O44 = 0, O45 = 0, O46 = 0, O47 = 0, O48 = 0, O49 = 0, O50 = 0;
                                double U1 = 0, U2 = 0, U3 = 0, U4 = 0, U5 = 0, U6 = 0, U7 = 0, U8 = 0, U9 = 0, U10 = 0, U11 = 0, U12 = 0, U13 = 0, U14 = 0, U15 = 0, U16 = 0, U17 = 0, U18 = 0, U19 = 0, U20 = 0, U21 = 0, U22 = 0, U23 = 0, U24 = 0, U25 = 0, U26 = 0, U27 = 0, U28 = 0, U29 = 0, U30 = 0, U31 = 0, U32 = 0, U33 = 0, U34 = 0, U35 = 0, U36 = 0, U37 = 0, U38 = 0, U39 = 0, U40 = 0, U41 = 0, U42 = 0, U43 = 0, U44 = 0, U45 = 0, U46 = 0, U47 = 0, U48 = 0, U49 = 0, U50 = 0;
                                double T1 = 0, T2 = 0, T3 = 0, T4 = 0, T5 = 0, T6 = 0, T7 = 0, T8 = 0, T9 = 0, T10 = 0, T11 = 0, T12 = 0, T13 = 0, T14 = 0, T15 = 0, T16 = 0, T17 = 0, T18 = 0, T19 = 0, T20 = 0, T21 = 0, T22 = 0, T23 = 0, T24 = 0, T25 = 0, T26 = 0, T27 = 0, T28 = 0, T29 = 0, T30 = 0, T31 = 0, T32 = 0, T33 = 0, T34 = 0, T35 = 0, T36 = 0, T37 = 0, T38 = 0, T39 = 0, T40 = 0, T41 = 0, T42 = 0, T43 = 0, T44 = 0, T45 = 0, T46 = 0, T47 = 0, T48 = 0, T49 = 0, T50 = 0;
                                if (amo1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A1 = Double.valueOf(amo1.getText().toString());
                                if (amo2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A2 = Double.valueOf(amo2.getText().toString());
                                if (amo3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A3 = Double.valueOf(amo3.getText().toString());
                                if (amo4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A4 = Double.valueOf(amo4.getText().toString());
                                if (amo5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A5 = Double.valueOf(amo5.getText().toString());
                                if (amo6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A6 = Double.valueOf(amo6.getText().toString());
                                if (amo7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A7 = Double.valueOf(amo7.getText().toString());
                                if (amo8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A8 = Double.valueOf(amo8.getText().toString());
                                if (amo9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A9 = Double.valueOf(amo9.getText().toString());
                                if (amo10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A10 = Double.valueOf(amo10.getText().toString());
                                if (amo11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A11 = Double.valueOf(amo11.getText().toString());
                                if (amo12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A12 = Double.valueOf(amo12.getText().toString());
                                if (amo13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A13 = Double.valueOf(amo13.getText().toString());
                                if (amo14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A14 = Double.valueOf(amo14.getText().toString());
                                if (amo15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A15 = Double.valueOf(amo15.getText().toString());
                                if (amo16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A16 = Double.valueOf(amo16.getText().toString());
                                if (amo17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A17 = Double.valueOf(amo17.getText().toString());
                                if (amo18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A18 = Double.valueOf(amo18.getText().toString());
                                if (amo19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A19 = Double.valueOf(amo19.getText().toString());
                                if (amo20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A20 = Double.valueOf(amo20.getText().toString());
                                if (amo21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A21 = Double.valueOf(amo21.getText().toString());
                                if (amo22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A22 = Double.valueOf(amo22.getText().toString());
                                if (amo23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A23 = Double.valueOf(amo23.getText().toString());
                                if (amo24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A24 = Double.valueOf(amo24.getText().toString());
                                if (amo25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A25 = Double.valueOf(amo25.getText().toString());
                                if (amo26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A26 = Double.valueOf(amo26.getText().toString());
                                if (amo27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A7 = Double.valueOf(amo27.getText().toString());
                                if (amo28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A28 = Double.valueOf(amo28.getText().toString());
                                if (amo29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A29 = Double.valueOf(amo29.getText().toString());
                                if (amo30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A30 = Double.valueOf(amo30.getText().toString());
                                if (amo31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A31 = Double.valueOf(amo31.getText().toString());
                                if (amo32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A32 = Double.valueOf(amo32.getText().toString());
                                if (amo33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A33 = Double.valueOf(amo33.getText().toString());
                                if (amo34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A34 = Double.valueOf(amo34.getText().toString());
                                if (amo35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A35 = Double.valueOf(amo35.getText().toString());
                                if (amo36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A36 = Double.valueOf(amo36.getText().toString());
                                if (amo37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A37 = Double.valueOf(amo37.getText().toString());
                                if (amo38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A38 = Double.valueOf(amo38.getText().toString());
                                if (amo9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A39 = Double.valueOf(amo39.getText().toString());
                                if (amo40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A40 = Double.valueOf(amo40.getText().toString());
                                if (amo41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A41 = Double.valueOf(amo41.getText().toString());
                                if (amo42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A42 = Double.valueOf(amo42.getText().toString());
                                if (amo43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A43 = Double.valueOf(amo43.getText().toString());
                                if (amo44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A44 = Double.valueOf(amo44.getText().toString());
                                if (amo45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A45 = Double.valueOf(amo45.getText().toString());
                                if (amo46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A46 = Double.valueOf(amo46.getText().toString());
                                if (amo47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A47 = Double.valueOf(amo47.getText().toString());
                                if (amo48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A48 = Double.valueOf(amo48.getText().toString());
                                if (amo49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A49 = Double.valueOf(amo49.getText().toString());
                                if (amo50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    A50 = Double.valueOf(amo50.getText().toString());

                                if (off1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O1 = Double.valueOf(off1.getText().toString());
                                if (off2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O2 = Double.valueOf(off2.getText().toString());
                                if (off3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O3 = Double.valueOf(off3.getText().toString());
                                if (off4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O4 = Double.valueOf(off4.getText().toString());
                                if (off5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O5 = Double.valueOf(off5.getText().toString());
                                if (off6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O6 = Double.valueOf(off6.getText().toString());
                                if (off7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O7 = Double.valueOf(off7.getText().toString());
                                if (off8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O8 = Double.valueOf(off8.getText().toString());
                                if (off9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O9 = Double.valueOf(off9.getText().toString());
                                if (off10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O10 = Double.valueOf(off10.getText().toString());
                                if (off11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O11 = Double.valueOf(off11.getText().toString());
                                if (off12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O12 = Double.valueOf(off12.getText().toString());
                                if (off13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O13 = Double.valueOf(off13.getText().toString());
                                if (off14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O14 = Double.valueOf(off14.getText().toString());
                                if (off15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O15 = Double.valueOf(off15.getText().toString());
                                if (off16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O16 = Double.valueOf(off16.getText().toString());
                                if (off17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O17 = Double.valueOf(off17.getText().toString());
                                if (off18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O18 = Double.valueOf(off18.getText().toString());
                                if (off19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O19 = Double.valueOf(off19.getText().toString());
                                if (off20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O20 = Double.valueOf(off20.getText().toString());
                                if (off21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O21 = Double.valueOf(off21.getText().toString());
                                if (off22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O22 = Double.valueOf(off22.getText().toString());
                                if (off23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O23 = Double.valueOf(off23.getText().toString());
                                if (off24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O24 = Double.valueOf(off24.getText().toString());
                                if (off25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O25 = Double.valueOf(off25.getText().toString());
                                if (off26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O26 = Double.valueOf(off26.getText().toString());
                                if (off27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O7 = Double.valueOf(off27.getText().toString());
                                if (off28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O28 = Double.valueOf(off28.getText().toString());
                                if (off29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O29 = Double.valueOf(off29.getText().toString());
                                if (off30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O30 = Double.valueOf(off30.getText().toString());
                                if (off31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O31 = Double.valueOf(off31.getText().toString());
                                if (off32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O32 = Double.valueOf(off32.getText().toString());
                                if (off33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O33 = Double.valueOf(off33.getText().toString());
                                if (off34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O34 = Double.valueOf(off34.getText().toString());
                                if (off35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O35 = Double.valueOf(off35.getText().toString());
                                if (off36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O36 = Double.valueOf(off36.getText().toString());
                                if (off37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O37 = Double.valueOf(off37.getText().toString());
                                if (off38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O38 = Double.valueOf(off38.getText().toString());
                                if (off9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O39 = Double.valueOf(off39.getText().toString());
                                if (off40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O40 = Double.valueOf(off40.getText().toString());
                                if (off41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O41 = Double.valueOf(off41.getText().toString());
                                if (off42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O42 = Double.valueOf(off42.getText().toString());
                                if (off43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O43 = Double.valueOf(off43.getText().toString());
                                if (off44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O44 = Double.valueOf(off44.getText().toString());
                                if (off45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O45 = Double.valueOf(off45.getText().toString());
                                if (off46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O46 = Double.valueOf(off46.getText().toString());
                                if (off47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O47 = Double.valueOf(off47.getText().toString());
                                if (off48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O48 = Double.valueOf(off48.getText().toString());
                                if (off49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O49 = Double.valueOf(off49.getText().toString());
                                if (off50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    O50 = Double.valueOf(off50.getText().toString());

                                if (uni1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U1 = Double.valueOf(uni1.getText().toString());
                                if (uni2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U2 = Double.valueOf(uni2.getText().toString());
                                if (uni3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U3 = Double.valueOf(uni3.getText().toString());
                                if (uni4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U4 = Double.valueOf(uni4.getText().toString());
                                if (uni5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U5 = Double.valueOf(uni5.getText().toString());
                                if (uni6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U6 = Double.valueOf(uni6.getText().toString());
                                if (uni7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U7 = Double.valueOf(uni7.getText().toString());
                                if (uni8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U8 = Double.valueOf(uni8.getText().toString());
                                if (uni9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U9 = Double.valueOf(uni9.getText().toString());
                                if (uni10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U10 = Double.valueOf(uni10.getText().toString());
                                if (uni11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U11 = Double.valueOf(uni11.getText().toString());
                                if (uni12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U12 = Double.valueOf(uni12.getText().toString());
                                if (uni13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U13 = Double.valueOf(uni13.getText().toString());
                                if (uni14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U14 = Double.valueOf(uni14.getText().toString());
                                if (uni15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U15 = Double.valueOf(uni15.getText().toString());
                                if (uni16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U16 = Double.valueOf(uni16.getText().toString());
                                if (uni17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U17 = Double.valueOf(uni17.getText().toString());
                                if (uni18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U18 = Double.valueOf(uni18.getText().toString());
                                if (uni19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U19 = Double.valueOf(uni19.getText().toString());
                                if (uni20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U20 = Double.valueOf(uni20.getText().toString());
                                if (uni21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U21 = Double.valueOf(uni21.getText().toString());
                                if (uni22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U22 = Double.valueOf(uni22.getText().toString());
                                if (uni23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U23 = Double.valueOf(uni23.getText().toString());
                                if (uni24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U24 = Double.valueOf(uni24.getText().toString());
                                if (uni25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U25 = Double.valueOf(uni25.getText().toString());
                                if (uni26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U26 = Double.valueOf(uni26.getText().toString());
                                if (uni27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U7 = Double.valueOf(uni27.getText().toString());
                                if (uni28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U28 = Double.valueOf(uni28.getText().toString());
                                if (uni29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U29 = Double.valueOf(uni29.getText().toString());
                                if (uni30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U30 = Double.valueOf(uni30.getText().toString());
                                if (uni31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U31 = Double.valueOf(uni31.getText().toString());
                                if (uni32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U32 = Double.valueOf(uni32.getText().toString());
                                if (uni33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U33 = Double.valueOf(uni33.getText().toString());
                                if (uni34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U34 = Double.valueOf(uni34.getText().toString());
                                if (uni35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U35 = Double.valueOf(uni35.getText().toString());
                                if (uni36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U36 = Double.valueOf(uni36.getText().toString());
                                if (uni37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U37 = Double.valueOf(uni37.getText().toString());
                                if (uni38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U38 = Double.valueOf(uni38.getText().toString());
                                if (uni9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U39 = Double.valueOf(uni39.getText().toString());
                                if (uni40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U40 = Double.valueOf(uni40.getText().toString());
                                if (uni41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U41 = Double.valueOf(uni41.getText().toString());
                                if (uni42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U42 = Double.valueOf(uni42.getText().toString());
                                if (uni43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U43 = Double.valueOf(uni43.getText().toString());
                                if (uni44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U44 = Double.valueOf(uni44.getText().toString());
                                if (uni45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U45 = Double.valueOf(uni45.getText().toString());
                                if (uni46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U46 = Double.valueOf(uni46.getText().toString());
                                if (uni47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U47 = Double.valueOf(uni47.getText().toString());
                                if (uni48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U48 = Double.valueOf(uni48.getText().toString());
                                if (uni49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U49 = Double.valueOf(uni49.getText().toString());
                                if (uni50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    U50 = Double.valueOf(uni50.getText().toString());

                                if (tot1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T1 = Double.valueOf(tot1.getText().toString());
                                if (tot2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T2 = Double.valueOf(tot2.getText().toString());
                                if (tot3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T3 = Double.valueOf(tot3.getText().toString());
                                if (tot4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T4 = Double.valueOf(tot4.getText().toString());
                                if (tot5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T5 = Double.valueOf(tot5.getText().toString());
                                if (tot6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T6 = Double.valueOf(tot6.getText().toString());
                                if (tot7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T7 = Double.valueOf(tot7.getText().toString());
                                if (tot8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T8 = Double.valueOf(tot8.getText().toString());
                                if (tot9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T9 = Double.valueOf(tot9.getText().toString());
                                if (tot10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T10 = Double.valueOf(tot10.getText().toString());
                                if (tot11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T11 = Double.valueOf(tot11.getText().toString());
                                if (tot12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T12 = Double.valueOf(tot12.getText().toString());
                                if (tot13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T13 = Double.valueOf(tot13.getText().toString());
                                if (tot14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T14 = Double.valueOf(tot14.getText().toString());
                                if (tot15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T15 = Double.valueOf(tot15.getText().toString());
                                if (tot16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T16 = Double.valueOf(tot16.getText().toString());
                                if (tot17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T17 = Double.valueOf(tot17.getText().toString());
                                if (tot18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T18 = Double.valueOf(tot18.getText().toString());
                                if (tot19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T19 = Double.valueOf(tot19.getText().toString());
                                if (tot20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T20 = Double.valueOf(tot20.getText().toString());
                                if (tot21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T21 = Double.valueOf(tot21.getText().toString());
                                if (tot22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T22 = Double.valueOf(tot22.getText().toString());
                                if (tot23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T23 = Double.valueOf(tot23.getText().toString());
                                if (tot24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T24 = Double.valueOf(tot24.getText().toString());
                                if (tot25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T25 = Double.valueOf(tot25.getText().toString());
                                if (tot26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T26 = Double.valueOf(tot26.getText().toString());
                                if (tot27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T7 = Double.valueOf(tot27.getText().toString());
                                if (tot28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T28 = Double.valueOf(tot28.getText().toString());
                                if (tot29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T29 = Double.valueOf(tot29.getText().toString());
                                if (tot30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T30 = Double.valueOf(tot30.getText().toString());
                                if (tot31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T31 = Double.valueOf(tot31.getText().toString());
                                if (tot32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T32 = Double.valueOf(tot32.getText().toString());
                                if (tot33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T33 = Double.valueOf(tot33.getText().toString());
                                if (tot34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T34 = Double.valueOf(tot34.getText().toString());
                                if (tot35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T35 = Double.valueOf(tot35.getText().toString());
                                if (tot36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T36 = Double.valueOf(tot36.getText().toString());
                                if (tot37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T37 = Double.valueOf(tot37.getText().toString());
                                if (tot38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T38 = Double.valueOf(tot38.getText().toString());
                                if (tot9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T39 = Double.valueOf(tot39.getText().toString());
                                if (tot40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T40 = Double.valueOf(tot40.getText().toString());
                                if (tot41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T41 = Double.valueOf(tot41.getText().toString());
                                if (tot42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T42 = Double.valueOf(tot42.getText().toString());
                                if (tot43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T43 = Double.valueOf(tot43.getText().toString());
                                if (tot44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T44 = Double.valueOf(tot44.getText().toString());
                                if (tot45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T45 = Double.valueOf(tot45.getText().toString());
                                if (tot46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T46 = Double.valueOf(tot46.getText().toString());
                                if (tot47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T47 = Double.valueOf(tot47.getText().toString());
                                if (tot48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T48 = Double.valueOf(tot48.getText().toString());
                                if (tot49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T49 = Double.valueOf(tot49.getText().toString());
                                if (tot50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    T50 = Double.valueOf(tot50.getText().toString());
                                if (edit_discount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    Discount = Double.valueOf(edit_discount.getText().toString());
                                if (edit_addition.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    Addition = Double.valueOf(edit_addition.getText().toString());
                                BillListChildItem b = new BillListChildItem(bill.getCOLUMN(), Integer.valueOf(edit1.getText().toString()), edit.getText().toString(), basic, txt_date.getText().toString(), getTime(), from, to, spin_type.getSelectedItem().toString(), spin_currency.getSelectedItem().toString(), Discount, Addition, Total, Final);
                                b.SETTABLE1(sub1.getText().toString(), sub2.getText().toString(), sub3.getText().toString(), sub4.getText().toString(), sub5.getText().toString(), sub6.getText().toString(), sub7.getText().toString(), sub8.getText().toString(), sub9.getText().toString(), sub10.getText().toString()
                                        , sub11.getText().toString(), sub12.getText().toString(), sub13.getText().toString(), sub14.getText().toString(), sub15.getText().toString(), sub16.getText().toString(), sub17.getText().toString(), sub18.getText().toString(), sub19.getText().toString(), sub20.getText().toString()
                                        , sub21.getText().toString(), sub22.getText().toString(), sub23.getText().toString(), sub24.getText().toString(), sub25.getText().toString(), sub26.getText().toString(), sub27.getText().toString(), sub28.getText().toString(), sub29.getText().toString(), sub30.getText().toString()
                                        , sub31.getText().toString(), sub32.getText().toString(), sub33.getText().toString(), sub34.getText().toString(), sub35.getText().toString(), sub36.getText().toString(), sub37.getText().toString(), sub38.getText().toString(), sub39.getText().toString(), sub40.getText().toString()
                                        , sub41.getText().toString(), sub42.getText().toString(), sub43.getText().toString(), sub44.getText().toString(), sub45.getText().toString(), sub46.getText().toString(), sub47.getText().toString(), sub48.getText().toString(), sub49.getText().toString(), sub50.getText().toString()
                                        , A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48, A49, A50
                                        , O1, O2, O3, O4, O5, O6, O7, O8, O9, O10, O11, O12, O13, O14, O15, O16, O17, O18, O19, O20, O21, O22, O23, O24, O25, O26, O27, O28, O29, O30, O31, O32, O33, O34, O35, O36, O37, O38, O39, O40, O41, O42, O43, O44, O45, O46, O47, O48, O49, O50);
                                b.SETTABLE2(U1, U2, U3, U4, U5, U6, U7, U8, U9, U10, U11, U12, U13, U14, U15, U16, U17, U18, U19, U20, U21, U22, U23, U24, U25, U26, U27, U28, U29, U30, U31, U32, U33, U34, U35, U36, U37, U38, U39, U40, U41, U42, U43, U44, U45, U46, U47, U48, U49, U50
                                        , T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, T44, T45, T46, T47, T48, T49, T50
                                        , sta1.getText().toString(), sta2.getText().toString(), sta3.getText().toString(), sta4.getText().toString(), sta5.getText().toString(), sta6.getText().toString(), sta7.getText().toString(), sta8.getText().toString(), sta9.getText().toString(), sta10.getText().toString()
                                        , sta11.getText().toString(), sta12.getText().toString(), sta13.getText().toString(), sta14.getText().toString(), sta15.getText().toString(), sta16.getText().toString(), sta17.getText().toString(), sta18.getText().toString(), sta19.getText().toString(), sta20.getText().toString()
                                        , sta21.getText().toString(), sta22.getText().toString(), sta23.getText().toString(), sta24.getText().toString(), sta25.getText().toString(), sta26.getText().toString(), sta27.getText().toString(), sta28.getText().toString(), sta29.getText().toString(), sta30.getText().toString()
                                        , sta31.getText().toString(), sta32.getText().toString(), sta33.getText().toString(), sta34.getText().toString(), sta35.getText().toString(), sta36.getText().toString(), sta37.getText().toString(), sta38.getText().toString(), sta39.getText().toString(), sta40.getText().toString()
                                        , sta41.getText().toString(), sta42.getText().toString(), sta43.getText().toString(), sta44.getText().toString(), sta45.getText().toString(), sta46.getText().toString(), sta47.getText().toString(), sta48.getText().toString(), sta49.getText().toString(), sta50.getText().toString());
                                editFromSQL(b, b.getFrom(), b.getTo());
                            } else {
                                saveAsSql(isEdit, Integer.valueOf(edit1.getText().toString()), edit.getText().toString());
                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentBillId", Integer.valueOf(edit1.getText().toString()) + 1).commit();
                                if (basic == 1)
                                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentBillNum", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillNum", 1) + 1).commit();
                                else if (basic == 2)
                                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentInvoiceNum", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentInvoiceNum", 1) + 1).commit();
                                else
                                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentDraftNum", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentDraftNum", 1) + 1).commit();
                            }
                            dialog.cancel();
                            Intent resultIntent = getIntent();
                            setResult(Activity.RESULT_CANCELED, resultIntent);
                            finish();
                        } else {
                            if (!edit.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                if (pref.getString("Language", "arabic").matches("arabic"))
                                    edit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                else
                                    edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            if (!edit1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                if (pref.getString("Language", "arabic").matches("arabic"))
                                    edit1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                                else
                                    edit1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                        }

                    }
                });

        alertDialog.setNegativeButton(res.getString(R.string.dialog_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();

    }

    private void saveAsSql(boolean isEdit, int code, String name) {

        if (!isEdit || bun.getBoolean("isExtractItems", false)) {
            int column = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentBillColumn", 0);
            String from = "", to = "";
            if (spinBasicSelected + 1 == 1) {
                from = edit_from.getText().toString();
                to = spin_point_.getSelectedItem().toString();
            } else if (spinBasicSelected + 1 == 2) {
                from = spin_point.getSelectedItem().toString();
                to = edit_to.getText().toString();
            } else {
                from = edit_from_t.getText().toString();
                if (!edit_to_t.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    to = res.getString(R.string.without_folder);
                } else {
                    to = edit_to_t.getText().toString();
                }
            }

            double A1 = 0, A2 = 0, A3 = 0, A4 = 0, A5 = 0, A6 = 0, A7 = 0, A8 = 0, A9 = 0, A10 = 0, A11 = 0, A12 = 0, A13 = 0, A14 = 0, A15 = 0, A16 = 0, A17 = 0, A18 = 0, A19 = 0, A20 = 0, A21 = 0, A22 = 0, A23 = 0, A24 = 0, A25 = 0, A26 = 0, A27 = 0, A28 = 0, A29 = 0, A30 = 0, A31 = 0, A32 = 0, A33 = 0, A34 = 0, A35 = 0, A36 = 0, A37 = 0, A38 = 0, A39 = 0, A40 = 0, A41 = 0, A42 = 0, A43 = 0, A44 = 0, A45 = 0, A46 = 0, A47 = 0, A48 = 0, A49 = 0, A50 = 0;
            double O1 = 0, O2 = 0, O3 = 0, O4 = 0, O5 = 0, O6 = 0, O7 = 0, O8 = 0, O9 = 0, O10 = 0, O11 = 0, O12 = 0, O13 = 0, O14 = 0, O15 = 0, O16 = 0, O17 = 0, O18 = 0, O19 = 0, O20 = 0, O21 = 0, O22 = 0, O23 = 0, O24 = 0, O25 = 0, O26 = 0, O27 = 0, O28 = 0, O29 = 0, O30 = 0, O31 = 0, O32 = 0, O33 = 0, O34 = 0, O35 = 0, O36 = 0, O37 = 0, O38 = 0, O39 = 0, O40 = 0, O41 = 0, O42 = 0, O43 = 0, O44 = 0, O45 = 0, O46 = 0, O47 = 0, O48 = 0, O49 = 0, O50 = 0;
            double U1 = 0, U2 = 0, U3 = 0, U4 = 0, U5 = 0, U6 = 0, U7 = 0, U8 = 0, U9 = 0, U10 = 0, U11 = 0, U12 = 0, U13 = 0, U14 = 0, U15 = 0, U16 = 0, U17 = 0, U18 = 0, U19 = 0, U20 = 0, U21 = 0, U22 = 0, U23 = 0, U24 = 0, U25 = 0, U26 = 0, U27 = 0, U28 = 0, U29 = 0, U30 = 0, U31 = 0, U32 = 0, U33 = 0, U34 = 0, U35 = 0, U36 = 0, U37 = 0, U38 = 0, U39 = 0, U40 = 0, U41 = 0, U42 = 0, U43 = 0, U44 = 0, U45 = 0, U46 = 0, U47 = 0, U48 = 0, U49 = 0, U50 = 0;
            double T1 = 0, T2 = 0, T3 = 0, T4 = 0, T5 = 0, T6 = 0, T7 = 0, T8 = 0, T9 = 0, T10 = 0, T11 = 0, T12 = 0, T13 = 0, T14 = 0, T15 = 0, T16 = 0, T17 = 0, T18 = 0, T19 = 0, T20 = 0, T21 = 0, T22 = 0, T23 = 0, T24 = 0, T25 = 0, T26 = 0, T27 = 0, T28 = 0, T29 = 0, T30 = 0, T31 = 0, T32 = 0, T33 = 0, T34 = 0, T35 = 0, T36 = 0, T37 = 0, T38 = 0, T39 = 0, T40 = 0, T41 = 0, T42 = 0, T43 = 0, T44 = 0, T45 = 0, T46 = 0, T47 = 0, T48 = 0, T49 = 0, T50 = 0;

            if (edit_discount.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                Discount = Double.valueOf(edit_discount.getText().toString());
            if (edit_addition.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                Addition = Double.valueOf(edit_addition.getText().toString());

            if (amo1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A1 = Double.valueOf(amo1.getText().toString());
            if (amo2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A2 = Double.valueOf(amo2.getText().toString());
            if (amo3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A3 = Double.valueOf(amo3.getText().toString());
            if (amo4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A4 = Double.valueOf(amo4.getText().toString());
            if (amo5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A5 = Double.valueOf(amo5.getText().toString());
            if (amo6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A6 = Double.valueOf(amo6.getText().toString());
            if (amo7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A7 = Double.valueOf(amo7.getText().toString());
            if (amo8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A8 = Double.valueOf(amo8.getText().toString());
            if (amo9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A9 = Double.valueOf(amo9.getText().toString());
            if (amo10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A10 = Double.valueOf(amo10.getText().toString());
            if (amo11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A11 = Double.valueOf(amo11.getText().toString());
            if (amo12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A12 = Double.valueOf(amo12.getText().toString());
            if (amo13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A13 = Double.valueOf(amo13.getText().toString());
            if (amo14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A14 = Double.valueOf(amo14.getText().toString());
            if (amo15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A15 = Double.valueOf(amo15.getText().toString());
            if (amo16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A16 = Double.valueOf(amo16.getText().toString());
            if (amo17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A17 = Double.valueOf(amo17.getText().toString());
            if (amo18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A18 = Double.valueOf(amo18.getText().toString());
            if (amo19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A19 = Double.valueOf(amo19.getText().toString());
            if (amo20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A20 = Double.valueOf(amo20.getText().toString());
            if (amo21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A21 = Double.valueOf(amo21.getText().toString());
            if (amo22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A22 = Double.valueOf(amo22.getText().toString());
            if (amo23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A23 = Double.valueOf(amo23.getText().toString());
            if (amo24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A24 = Double.valueOf(amo24.getText().toString());
            if (amo25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A25 = Double.valueOf(amo25.getText().toString());
            if (amo26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A26 = Double.valueOf(amo26.getText().toString());
            if (amo27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A7 = Double.valueOf(amo27.getText().toString());
            if (amo28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A28 = Double.valueOf(amo28.getText().toString());
            if (amo29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A29 = Double.valueOf(amo29.getText().toString());
            if (amo30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A30 = Double.valueOf(amo30.getText().toString());
            if (amo31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A31 = Double.valueOf(amo31.getText().toString());
            if (amo32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A32 = Double.valueOf(amo32.getText().toString());
            if (amo33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A33 = Double.valueOf(amo33.getText().toString());
            if (amo34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A34 = Double.valueOf(amo34.getText().toString());
            if (amo35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A35 = Double.valueOf(amo35.getText().toString());
            if (amo36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A36 = Double.valueOf(amo36.getText().toString());
            if (amo37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A37 = Double.valueOf(amo37.getText().toString());
            if (amo38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A38 = Double.valueOf(amo38.getText().toString());
            if (amo9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A39 = Double.valueOf(amo39.getText().toString());
            if (amo40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A40 = Double.valueOf(amo40.getText().toString());
            if (amo41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A41 = Double.valueOf(amo41.getText().toString());
            if (amo42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A42 = Double.valueOf(amo42.getText().toString());
            if (amo43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A43 = Double.valueOf(amo43.getText().toString());
            if (amo44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A44 = Double.valueOf(amo44.getText().toString());
            if (amo45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A45 = Double.valueOf(amo45.getText().toString());
            if (amo46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A46 = Double.valueOf(amo46.getText().toString());
            if (amo47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A47 = Double.valueOf(amo47.getText().toString());
            if (amo48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A48 = Double.valueOf(amo48.getText().toString());
            if (amo49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A49 = Double.valueOf(amo49.getText().toString());
            if (amo50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                A50 = Double.valueOf(amo50.getText().toString());

            if (off1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O1 = Double.valueOf(off1.getText().toString());
            if (off2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O2 = Double.valueOf(off2.getText().toString());
            if (off3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O3 = Double.valueOf(off3.getText().toString());
            if (off4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O4 = Double.valueOf(off4.getText().toString());
            if (off5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O5 = Double.valueOf(off5.getText().toString());
            if (off6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O6 = Double.valueOf(off6.getText().toString());
            if (off7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O7 = Double.valueOf(off7.getText().toString());
            if (off8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O8 = Double.valueOf(off8.getText().toString());
            if (off9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O9 = Double.valueOf(off9.getText().toString());
            if (off10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O10 = Double.valueOf(off10.getText().toString());
            if (off11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O11 = Double.valueOf(off11.getText().toString());
            if (off12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O12 = Double.valueOf(off12.getText().toString());
            if (off13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O13 = Double.valueOf(off13.getText().toString());
            if (off14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O14 = Double.valueOf(off14.getText().toString());
            if (off15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O15 = Double.valueOf(off15.getText().toString());
            if (off16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O16 = Double.valueOf(off16.getText().toString());
            if (off17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O17 = Double.valueOf(off17.getText().toString());
            if (off18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O18 = Double.valueOf(off18.getText().toString());
            if (off19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O19 = Double.valueOf(off19.getText().toString());
            if (off20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O20 = Double.valueOf(off20.getText().toString());
            if (off21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O21 = Double.valueOf(off21.getText().toString());
            if (off22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O22 = Double.valueOf(off22.getText().toString());
            if (off23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O23 = Double.valueOf(off23.getText().toString());
            if (off24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O24 = Double.valueOf(off24.getText().toString());
            if (off25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O25 = Double.valueOf(off25.getText().toString());
            if (off26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O26 = Double.valueOf(off26.getText().toString());
            if (off27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O7 = Double.valueOf(off27.getText().toString());
            if (off28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O28 = Double.valueOf(off28.getText().toString());
            if (off29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O29 = Double.valueOf(off29.getText().toString());
            if (off30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O30 = Double.valueOf(off30.getText().toString());
            if (off31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O31 = Double.valueOf(off31.getText().toString());
            if (off32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O32 = Double.valueOf(off32.getText().toString());
            if (off33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O33 = Double.valueOf(off33.getText().toString());
            if (off34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O34 = Double.valueOf(off34.getText().toString());
            if (off35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O35 = Double.valueOf(off35.getText().toString());
            if (off36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O36 = Double.valueOf(off36.getText().toString());
            if (off37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O37 = Double.valueOf(off37.getText().toString());
            if (off38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O38 = Double.valueOf(off38.getText().toString());
            if (off9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O39 = Double.valueOf(off39.getText().toString());
            if (off40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O40 = Double.valueOf(off40.getText().toString());
            if (off41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O41 = Double.valueOf(off41.getText().toString());
            if (off42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O42 = Double.valueOf(off42.getText().toString());
            if (off43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O43 = Double.valueOf(off43.getText().toString());
            if (off44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O44 = Double.valueOf(off44.getText().toString());
            if (off45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O45 = Double.valueOf(off45.getText().toString());
            if (off46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O46 = Double.valueOf(off46.getText().toString());
            if (off47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O47 = Double.valueOf(off47.getText().toString());
            if (off48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O48 = Double.valueOf(off48.getText().toString());
            if (off49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O49 = Double.valueOf(off49.getText().toString());
            if (off50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                O50 = Double.valueOf(off50.getText().toString());

            if (uni1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U1 = Double.valueOf(uni1.getText().toString());
            if (uni2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U2 = Double.valueOf(uni2.getText().toString());
            if (uni3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U3 = Double.valueOf(uni3.getText().toString());
            if (uni4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U4 = Double.valueOf(uni4.getText().toString());
            if (uni5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U5 = Double.valueOf(uni5.getText().toString());
            if (uni6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U6 = Double.valueOf(uni6.getText().toString());
            if (uni7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U7 = Double.valueOf(uni7.getText().toString());
            if (uni8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U8 = Double.valueOf(uni8.getText().toString());
            if (uni9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U9 = Double.valueOf(uni9.getText().toString());
            if (uni10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U10 = Double.valueOf(uni10.getText().toString());
            if (uni11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U11 = Double.valueOf(uni11.getText().toString());
            if (uni12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U12 = Double.valueOf(uni12.getText().toString());
            if (uni13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U13 = Double.valueOf(uni13.getText().toString());
            if (uni14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U14 = Double.valueOf(uni14.getText().toString());
            if (uni15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U15 = Double.valueOf(uni15.getText().toString());
            if (uni16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U16 = Double.valueOf(uni16.getText().toString());
            if (uni17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U17 = Double.valueOf(uni17.getText().toString());
            if (uni18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U18 = Double.valueOf(uni18.getText().toString());
            if (uni19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U19 = Double.valueOf(uni19.getText().toString());
            if (uni20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U20 = Double.valueOf(uni20.getText().toString());
            if (uni21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U21 = Double.valueOf(uni21.getText().toString());
            if (uni22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U22 = Double.valueOf(uni22.getText().toString());
            if (uni23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U23 = Double.valueOf(uni23.getText().toString());
            if (uni24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U24 = Double.valueOf(uni24.getText().toString());
            if (uni25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U25 = Double.valueOf(uni25.getText().toString());
            if (uni26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U26 = Double.valueOf(uni26.getText().toString());
            if (uni27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U7 = Double.valueOf(uni27.getText().toString());
            if (uni28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U28 = Double.valueOf(uni28.getText().toString());
            if (uni29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U29 = Double.valueOf(uni29.getText().toString());
            if (uni30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U30 = Double.valueOf(uni30.getText().toString());
            if (uni31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U31 = Double.valueOf(uni31.getText().toString());
            if (uni32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U32 = Double.valueOf(uni32.getText().toString());
            if (uni33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U33 = Double.valueOf(uni33.getText().toString());
            if (uni34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U34 = Double.valueOf(uni34.getText().toString());
            if (uni35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U35 = Double.valueOf(uni35.getText().toString());
            if (uni36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U36 = Double.valueOf(uni36.getText().toString());
            if (uni37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U37 = Double.valueOf(uni37.getText().toString());
            if (uni38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U38 = Double.valueOf(uni38.getText().toString());
            if (uni9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U39 = Double.valueOf(uni39.getText().toString());
            if (uni40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U40 = Double.valueOf(uni40.getText().toString());
            if (uni41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U41 = Double.valueOf(uni41.getText().toString());
            if (uni42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U42 = Double.valueOf(uni42.getText().toString());
            if (uni43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U43 = Double.valueOf(uni43.getText().toString());
            if (uni44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U44 = Double.valueOf(uni44.getText().toString());
            if (uni45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U45 = Double.valueOf(uni45.getText().toString());
            if (uni46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U46 = Double.valueOf(uni46.getText().toString());
            if (uni47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U47 = Double.valueOf(uni47.getText().toString());
            if (uni48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U48 = Double.valueOf(uni48.getText().toString());
            if (uni49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U49 = Double.valueOf(uni49.getText().toString());
            if (uni50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                U50 = Double.valueOf(uni50.getText().toString());

            if (tot1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T1 = Double.valueOf(tot1.getText().toString());
            if (tot2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T2 = Double.valueOf(tot2.getText().toString());
            if (tot3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T3 = Double.valueOf(tot3.getText().toString());
            if (tot4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T4 = Double.valueOf(tot4.getText().toString());
            if (tot5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T5 = Double.valueOf(tot5.getText().toString());
            if (tot6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T6 = Double.valueOf(tot6.getText().toString());
            if (tot7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T7 = Double.valueOf(tot7.getText().toString());
            if (tot8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T8 = Double.valueOf(tot8.getText().toString());
            if (tot9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T9 = Double.valueOf(tot9.getText().toString());
            if (tot10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T10 = Double.valueOf(tot10.getText().toString());
            if (tot11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T11 = Double.valueOf(tot11.getText().toString());
            if (tot12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T12 = Double.valueOf(tot12.getText().toString());
            if (tot13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T13 = Double.valueOf(tot13.getText().toString());
            if (tot14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T14 = Double.valueOf(tot14.getText().toString());
            if (tot15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T15 = Double.valueOf(tot15.getText().toString());
            if (tot16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T16 = Double.valueOf(tot16.getText().toString());
            if (tot17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T17 = Double.valueOf(tot17.getText().toString());
            if (tot18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T18 = Double.valueOf(tot18.getText().toString());
            if (tot19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T19 = Double.valueOf(tot19.getText().toString());
            if (tot20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T20 = Double.valueOf(tot20.getText().toString());
            if (tot21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T21 = Double.valueOf(tot21.getText().toString());
            if (tot22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T22 = Double.valueOf(tot22.getText().toString());
            if (tot23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T23 = Double.valueOf(tot23.getText().toString());
            if (tot24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T24 = Double.valueOf(tot24.getText().toString());
            if (tot25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T25 = Double.valueOf(tot25.getText().toString());
            if (tot26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T26 = Double.valueOf(tot26.getText().toString());
            if (tot27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T7 = Double.valueOf(tot27.getText().toString());
            if (tot28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T28 = Double.valueOf(tot28.getText().toString());
            if (tot29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T29 = Double.valueOf(tot29.getText().toString());
            if (tot30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T30 = Double.valueOf(tot30.getText().toString());
            if (tot31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T31 = Double.valueOf(tot31.getText().toString());
            if (tot32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T32 = Double.valueOf(tot32.getText().toString());
            if (tot33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T33 = Double.valueOf(tot33.getText().toString());
            if (tot34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T34 = Double.valueOf(tot34.getText().toString());
            if (tot35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T35 = Double.valueOf(tot35.getText().toString());
            if (tot36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T36 = Double.valueOf(tot36.getText().toString());
            if (tot37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T37 = Double.valueOf(tot37.getText().toString());
            if (tot38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T38 = Double.valueOf(tot38.getText().toString());
            if (tot9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T39 = Double.valueOf(tot39.getText().toString());
            if (tot40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T40 = Double.valueOf(tot40.getText().toString());
            if (tot41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T41 = Double.valueOf(tot41.getText().toString());
            if (tot42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T42 = Double.valueOf(tot42.getText().toString());
            if (tot43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T43 = Double.valueOf(tot43.getText().toString());
            if (tot44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T44 = Double.valueOf(tot44.getText().toString());
            if (tot45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T45 = Double.valueOf(tot45.getText().toString());
            if (tot46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T46 = Double.valueOf(tot46.getText().toString());
            if (tot47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T47 = Double.valueOf(tot47.getText().toString());
            if (tot48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T48 = Double.valueOf(tot48.getText().toString());
            if (tot49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T49 = Double.valueOf(tot49.getText().toString());
            if (tot50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                T50 = Double.valueOf(tot50.getText().toString());

            BillListChildItem bill = new BillListChildItem(column, code, name, basic, txt_date.getText().toString(), getTime(), from, to, spin_type.getSelectedItem().toString(), spin_currency.getSelectedItem().toString(), Discount, Addition, Total, Final);
            bill.SETTABLE1(sub1.getText().toString(), sub2.getText().toString(), sub3.getText().toString(), sub4.getText().toString(), sub5.getText().toString(), sub6.getText().toString(), sub7.getText().toString(), sub8.getText().toString(), sub9.getText().toString(), sub10.getText().toString()
                    , sub11.getText().toString(), sub12.getText().toString(), sub13.getText().toString(), sub14.getText().toString(), sub15.getText().toString(), sub16.getText().toString(), sub17.getText().toString(), sub18.getText().toString(), sub19.getText().toString(), sub20.getText().toString()
                    , sub21.getText().toString(), sub22.getText().toString(), sub23.getText().toString(), sub24.getText().toString(), sub25.getText().toString(), sub26.getText().toString(), sub27.getText().toString(), sub28.getText().toString(), sub29.getText().toString(), sub30.getText().toString()
                    , sub31.getText().toString(), sub32.getText().toString(), sub33.getText().toString(), sub34.getText().toString(), sub35.getText().toString(), sub36.getText().toString(), sub37.getText().toString(), sub38.getText().toString(), sub39.getText().toString(), sub40.getText().toString()
                    , sub41.getText().toString(), sub42.getText().toString(), sub43.getText().toString(), sub44.getText().toString(), sub45.getText().toString(), sub46.getText().toString(), sub47.getText().toString(), sub48.getText().toString(), sub49.getText().toString(), sub50.getText().toString()
                    , A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48, A49, A50
                    , O1, O2, O3, O4, O5, O6, O7, O8, O9, O10, O11, O12, O13, O14, O15, O16, O17, O18, O19, O20, O21, O22, O23, O24, O25, O26, O27, O28, O29, O30, O31, O32, O33, O34, O35, O36, O37, O38, O39, O40, O41, O42, O43, O44, O45, O46, O47, O48, O49, O50);
            bill.SETTABLE2(U1, U2, U3, U4, U5, U6, U7, U8, U9, U10, U11, U12, U13, U14, U15, U16, U17, U18, U19, U20, U21, U22, U23, U24, U25, U26, U27, U28, U29, U30, U31, U32, U33, U34, U35, U36, U37, U38, U39, U40, U41, U42, U43, U44, U45, U46, U47, U48, U49, U50
                    , T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, T44, T45, T46, T47, T48, T49, T50
                    , sta1.getText().toString(), sta2.getText().toString(), sta3.getText().toString(), sta4.getText().toString(), sta5.getText().toString(), sta6.getText().toString(), sta7.getText().toString(), sta8.getText().toString(), sta9.getText().toString(), sta10.getText().toString()
                    , sta11.getText().toString(), sta12.getText().toString(), sta13.getText().toString(), sta14.getText().toString(), sta15.getText().toString(), sta16.getText().toString(), sta17.getText().toString(), sta18.getText().toString(), sta19.getText().toString(), sta20.getText().toString()
                    , sta21.getText().toString(), sta22.getText().toString(), sta23.getText().toString(), sta24.getText().toString(), sta25.getText().toString(), sta26.getText().toString(), sta27.getText().toString(), sta28.getText().toString(), sta29.getText().toString(), sta30.getText().toString()
                    , sta31.getText().toString(), sta32.getText().toString(), sta33.getText().toString(), sta34.getText().toString(), sta35.getText().toString(), sta36.getText().toString(), sta37.getText().toString(), sta38.getText().toString(), sta39.getText().toString(), sta40.getText().toString()
                    , sta41.getText().toString(), sta42.getText().toString(), sta43.getText().toString(), sta44.getText().toString(), sta45.getText().toString(), sta46.getText().toString(), sta47.getText().toString(), sta48.getText().toString(), sta49.getText().toString(), sta50.getText().toString());
            addToSQL(bill);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentBillColumn", column + 1).commit();
            if (basic == 1) {
                for (int i = 1; i <= 50; i++) {
                    if (!checkIsRowEmpty(i)) {
                        boolean isExist = false;
                        int h = 0;
                        for (int k = 0; k < dbs.allSubjects().size(); k++) {
                            if (getSubjectByRow(i).matches(".*[0-9a-zA-Zأ-ي]+.*") && Integer.valueOf(getSubjectByRow(i).substring(0, getSubjectByRow(i).indexOf("-")).replaceAll(" ", "")) == dbs.allSubjects().get(k).getId()) {
                                isExist = true;
                                h = k;
                            }
                        }
                        if (isExist) {
                            SubjectListChildItem SLCI = dbs.allSubjects().get(h);
                            SubjectListChildItem SLCI_ = new SubjectListChildItem();
                            SLCI_.setDate(txt_date.getText().toString());
                            SLCI_.setTime(getTime());
                            SLCI_.setBuyer(edit_from.getText().toString());
                            SLCI_.setId(SLCI.getId());
                            if (keepCategories || !category.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                SLCI_.setFolder(SLCI.getFolder());
                            else {
                                if (category.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                    SLCI_.setFolder(category.getText().toString());
                                else
                                    SLCI_.setFolder(res.getString(R.string.without_folder));
                            }
                            SLCI_.setAmount(SLCI.getAmount() + Double.valueOf(getAmountByRow(i)) + Double.valueOf(getOfferByRow(i)));
                            SLCI_.setCode(SLCI.getCode());
                            SLCI_.setColumn(SLCI.getColumn());
                            SLCI_.setCost(productUnits.get(i - 1));
                            SLCI_.setLast(SLCI.getLast());
                            SLCI_.setLock(SLCI.getLock());
                            SLCI_.setAmountLast(SLCI.getAmountLast());
                            SLCI_.setAmountLock(SLCI.getAmountLock());
                            SLCI_.setName(SLCI.getName());
                            SLCI_.setSpec(SLCI.getSpec());
                            dbs.updateSubject(SLCI_);
                        } else {
                            SubjectListChildItem SLCI = new SubjectListChildItem();
                            SLCI.setDate(txt_date.getText().toString());
                            SLCI.setTime(getTime());
                            SLCI.setBuyer(edit_from.getText().toString());
                            SLCI.setId(Integer.valueOf(getSubjectByRow(i).substring(0, getSubjectByRow(i).indexOf("-")).replaceAll(" ", "")));
                            if (category.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*"))
                                SLCI.setFolder(category.getText().toString());
                            else
                                SLCI.setFolder(res.getString(R.string.without_folder));
                            SLCI.setAmount(Double.valueOf(getAmountByRow(i)) + Double.valueOf(getOfferByRow(i)));
                            SLCI.setCode(spin_currency.getSelectedItem().toString());
                            SLCI.setColumn(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectColumn", 0));
                            SLCI.setCost(productUnits.get(i - 1));
                            SLCI.setLast(0);
                            SLCI.setLock(0);
                            SLCI.setAmountLast(0);
                            SLCI.setAmountLock(0);
                            SLCI.setName(getSubjectByRow(i).substring(getSubjectByRow(i).indexOf("-")).replaceAll("-", ""));
                            SLCI.setSpec("");
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectColumn", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectColumn", 0) + 1).commit();
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("currentSubjectId", Integer.valueOf(getSubjectByRow(i).substring(0, getSubjectByRow(i).indexOf("-")).replaceAll("-", "").replaceAll(" ", "")) + 1).commit();
                            dbs.addSubject(SLCI);
                        }
                    }
                }
            } else if (basic == 2) {
                boolean isThereNotExist = false;
                for (int i = 1; i <= 50; i++) {
                    if (!checkIsRowEmpty(i)) {
                        boolean isExist = false;
                        int h = 0;
                        for (int k = 0; k < dbs.allSubjects().size(); k++) {
                            if (Integer.valueOf(getSubjectByRow(i).substring(0, getSubjectByRow(i).indexOf("-")).replaceAll(" ", "")) == dbs.allSubjects().get(k).getId()) {
                                isExist = true;
                                h = k;
                            }
                        }
                        if (isExist) {
                            SubjectListChildItem SLCI = dbs.allSubjects().get(h);
                            SubjectListChildItem SLCI_ = new SubjectListChildItem();
                            SLCI_.setDate(SLCI.getDate());
                            SLCI_.setTime(SLCI.getTime());
                            SLCI_.setBuyer(SLCI.getBuyer());
                            SLCI_.setId(SLCI.getId());
                            SLCI_.setFolder(SLCI.getFolder());
                            SLCI_.setAmount(SLCI.getAmount() - Double.valueOf(getAmountByRow(i)) - Double.valueOf(getOfferByRow(i)));
                            SLCI_.setCode(SLCI.getCode());
                            SLCI_.setColumn(SLCI.getColumn());
                            SLCI_.setCost(SLCI.getCost());
                            SLCI_.setLast(SLCI.getLast());
                            SLCI_.setLock(SLCI.getLock());
                            SLCI_.setAmountLast(SLCI.getAmountLast());
                            SLCI_.setAmountLock(SLCI.getAmountLock());
                            SLCI_.setName(SLCI.getName());
                            SLCI_.setSpec(SLCI.getSpec());
                            dbs.updateSubject(SLCI_);
                        } else {
                            isThereNotExist = true;
                        }
                    }
                }
                if (isThereNotExist) {
                    Toast.makeText(this, res.getString(R.string.warning_not_exist_product), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new BillSQLDatabaseHandler(this);
    }

    private void addToSQL(BillListChildItem bill) {
        db.addBill(bill);
    }

    private void deleteFromSQL(BillListChildItem bill) {
        db.deleteBill(bill);
    }

    private void editFromSQL(BillListChildItem bill, String from_, String to_) {
        db.updateBill(bill, from_, to_);
    }

    private void addRowNext() {
        if (!sub1.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
            sub1.setText(curSubject);
            amo1.setText(curAmount);
            off1.setText(curOffer);
            uni1.setText(curUnit);
            tot1.setText(curTotal);
            sta1.setText(curStatement);
        } else {
            if (!sub2.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                sub2.setText(curSubject);
                amo2.setText(curAmount);
                off2.setText(curOffer);
                uni2.setText(curUnit);
                tot2.setText(curTotal);
                sta2.setText(curStatement);
            } else {
                if (!sub3.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                    sub3.setText(curSubject);
                    amo3.setText(curAmount);
                    off3.setText(curOffer);
                    uni3.setText(curUnit);
                    tot3.setText(curTotal);
                    sta3.setText(curStatement);
                } else {
                    if (!sub4.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                        sub4.setText(curSubject);
                        amo4.setText(curAmount);
                        off4.setText(curOffer);
                        uni4.setText(curUnit);
                        tot4.setText(curTotal);
                        sta4.setText(curStatement);
                    } else {
                        if (!sub5.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                            sub5.setText(curSubject);
                            amo5.setText(curAmount);
                            off5.setText(curOffer);
                            uni5.setText(curUnit);
                            tot5.setText(curTotal);
                            sta5.setText(curStatement);
                        } else {
                            if (!sub6.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                sub6.setText(curSubject);
                                amo6.setText(curAmount);
                                off6.setText(curOffer);
                                uni6.setText(curUnit);
                                tot6.setText(curTotal);
                                sta6.setText(curStatement);
                            } else {
                                if (!sub7.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                    sub7.setText(curSubject);
                                    amo7.setText(curAmount);
                                    off7.setText(curOffer);
                                    uni7.setText(curUnit);
                                    tot7.setText(curTotal);
                                    sta7.setText(curStatement);
                                } else {
                                    if (!sub8.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                        sub8.setText(curSubject);
                                        amo8.setText(curAmount);
                                        off8.setText(curOffer);
                                        uni8.setText(curUnit);
                                        tot8.setText(curTotal);
                                        sta8.setText(curStatement);
                                    } else {
                                        if (!sub9.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                            sub9.setText(curSubject);
                                            amo9.setText(curAmount);
                                            off9.setText(curOffer);
                                            uni9.setText(curUnit);
                                            tot9.setText(curTotal);
                                            sta9.setText(curStatement);
                                        } else {
                                            if (!sub10.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                sub10.setText(curSubject);
                                                amo10.setText(curAmount);
                                                off10.setText(curOffer);
                                                uni10.setText(curUnit);
                                                tot10.setText(curTotal);
                                                sta10.setText(curStatement);
                                            } else {
                                                if (!sub11.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                    sub11.setText(curSubject);
                                                    amo11.setText(curAmount);
                                                    off11.setText(curOffer);
                                                    uni11.setText(curUnit);
                                                    tot11.setText(curTotal);
                                                    sta11.setText(curStatement);
                                                } else {
                                                    if (!sub12.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                        sub12.setText(curSubject);
                                                        amo12.setText(curAmount);
                                                        off12.setText(curOffer);
                                                        uni12.setText(curUnit);
                                                        tot12.setText(curTotal);
                                                        sta12.setText(curStatement);
                                                    } else {
                                                        if (!sub13.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                            sub13.setText(curSubject);
                                                            amo13.setText(curAmount);
                                                            off13.setText(curOffer);
                                                            uni13.setText(curUnit);
                                                            tot13.setText(curTotal);
                                                            sta13.setText(curStatement);
                                                        } else {
                                                            if (!sub14.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                sub14.setText(curSubject);
                                                                amo14.setText(curAmount);
                                                                off14.setText(curOffer);
                                                                uni14.setText(curUnit);
                                                                tot14.setText(curTotal);
                                                                sta14.setText(curStatement);
                                                            } else {
                                                                if (!sub15.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                    sub15.setText(curSubject);
                                                                    amo15.setText(curAmount);
                                                                    off15.setText(curOffer);
                                                                    uni15.setText(curUnit);
                                                                    tot15.setText(curTotal);
                                                                    sta15.setText(curStatement);
                                                                } else {
                                                                    if (!sub16.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                        sub16.setText(curSubject);
                                                                        amo16.setText(curAmount);
                                                                        off16.setText(curOffer);
                                                                        uni16.setText(curUnit);
                                                                        tot16.setText(curTotal);
                                                                        sta16.setText(curStatement);
                                                                    } else {
                                                                        if (!sub17.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                            sub17.setText(curSubject);
                                                                            amo17.setText(curAmount);
                                                                            off17.setText(curOffer);
                                                                            uni17.setText(curUnit);
                                                                            tot17.setText(curTotal);
                                                                            sta17.setText(curStatement);
                                                                        } else {
                                                                            if (!sub18.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                sub18.setText(curSubject);
                                                                                amo18.setText(curAmount);
                                                                                off18.setText(curOffer);
                                                                                uni18.setText(curUnit);
                                                                                tot18.setText(curTotal);
                                                                                sta18.setText(curStatement);
                                                                            } else {
                                                                                if (!sub19.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                    sub19.setText(curSubject);
                                                                                    amo19.setText(curAmount);
                                                                                    off19.setText(curOffer);
                                                                                    uni19.setText(curUnit);
                                                                                    tot19.setText(curTotal);
                                                                                    sta19.setText(curStatement);
                                                                                } else {
                                                                                    if (!sub20.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                        sub20.setText(curSubject);
                                                                                        amo20.setText(curAmount);
                                                                                        off20.setText(curOffer);
                                                                                        uni20.setText(curUnit);
                                                                                        tot20.setText(curTotal);
                                                                                        sta20.setText(curStatement);
                                                                                    } else {
                                                                                        if (!sub21.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                            sub21.setText(curSubject);
                                                                                            amo21.setText(curAmount);
                                                                                            off21.setText(curOffer);
                                                                                            uni21.setText(curUnit);
                                                                                            tot21.setText(curTotal);
                                                                                            sta21.setText(curStatement);
                                                                                        } else {
                                                                                            if (!sub22.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                sub22.setText(curSubject);
                                                                                                amo22.setText(curAmount);
                                                                                                off22.setText(curOffer);
                                                                                                uni22.setText(curUnit);
                                                                                                tot22.setText(curTotal);
                                                                                                sta22.setText(curStatement);
                                                                                            } else {
                                                                                                if (!sub23.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                    sub23.setText(curSubject);
                                                                                                    amo23.setText(curAmount);
                                                                                                    off23.setText(curOffer);
                                                                                                    uni23.setText(curUnit);
                                                                                                    tot23.setText(curTotal);
                                                                                                    sta23.setText(curStatement);
                                                                                                } else {
                                                                                                    if (!sub24.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                        sub24.setText(curSubject);
                                                                                                        amo24.setText(curAmount);
                                                                                                        off24.setText(curOffer);
                                                                                                        uni24.setText(curUnit);
                                                                                                        tot24.setText(curTotal);
                                                                                                        sta24.setText(curStatement);
                                                                                                    } else {
                                                                                                        if (!sub25.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                            sub25.setText(curSubject);
                                                                                                            amo25.setText(curAmount);
                                                                                                            off25.setText(curOffer);
                                                                                                            uni25.setText(curUnit);
                                                                                                            tot25.setText(curTotal);
                                                                                                            sta25.setText(curStatement);
                                                                                                        } else {
                                                                                                            if (!sub26.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                sub26.setText(curSubject);
                                                                                                                amo26.setText(curAmount);
                                                                                                                off26.setText(curOffer);
                                                                                                                uni26.setText(curUnit);
                                                                                                                tot26.setText(curTotal);
                                                                                                                sta26.setText(curStatement);
                                                                                                            } else {
                                                                                                                if (!sub27.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                    sub27.setText(curSubject);
                                                                                                                    amo27.setText(curAmount);
                                                                                                                    off27.setText(curOffer);
                                                                                                                    uni27.setText(curUnit);
                                                                                                                    tot27.setText(curTotal);
                                                                                                                    sta27.setText(curStatement);
                                                                                                                } else {
                                                                                                                    if (!sub28.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                        sub28.setText(curSubject);
                                                                                                                        amo28.setText(curAmount);
                                                                                                                        off28.setText(curOffer);
                                                                                                                        uni28.setText(curUnit);
                                                                                                                        tot28.setText(curTotal);
                                                                                                                        sta28.setText(curStatement);
                                                                                                                    } else {
                                                                                                                        if (!sub29.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                            sub29.setText(curSubject);
                                                                                                                            amo29.setText(curAmount);
                                                                                                                            off29.setText(curOffer);
                                                                                                                            uni29.setText(curUnit);
                                                                                                                            tot29.setText(curTotal);
                                                                                                                            sta29.setText(curStatement);
                                                                                                                        } else {
                                                                                                                            if (!sub30.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                sub30.setText(curSubject);
                                                                                                                                amo30.setText(curAmount);
                                                                                                                                off30.setText(curOffer);
                                                                                                                                uni30.setText(curUnit);
                                                                                                                                tot30.setText(curTotal);
                                                                                                                                sta30.setText(curStatement);
                                                                                                                            } else {
                                                                                                                                if (!sub31.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                    sub31.setText(curSubject);
                                                                                                                                    amo31.setText(curAmount);
                                                                                                                                    off31.setText(curOffer);
                                                                                                                                    uni31.setText(curUnit);
                                                                                                                                    tot31.setText(curTotal);
                                                                                                                                    sta31.setText(curStatement);
                                                                                                                                } else {
                                                                                                                                    if (!sub32.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                        sub32.setText(curSubject);
                                                                                                                                        amo32.setText(curAmount);
                                                                                                                                        off32.setText(curOffer);
                                                                                                                                        uni32.setText(curUnit);
                                                                                                                                        tot32.setText(curTotal);
                                                                                                                                        sta32.setText(curStatement);
                                                                                                                                    } else {
                                                                                                                                        if (!sub33.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                            sub33.setText(curSubject);
                                                                                                                                            amo33.setText(curAmount);
                                                                                                                                            off33.setText(curOffer);
                                                                                                                                            uni33.setText(curUnit);
                                                                                                                                            tot33.setText(curTotal);
                                                                                                                                            sta33.setText(curStatement);
                                                                                                                                        } else {
                                                                                                                                            if (!sub34.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                sub34.setText(curSubject);
                                                                                                                                                amo34.setText(curAmount);
                                                                                                                                                off34.setText(curOffer);
                                                                                                                                                uni34.setText(curUnit);
                                                                                                                                                tot34.setText(curTotal);
                                                                                                                                                sta34.setText(curStatement);
                                                                                                                                            } else {
                                                                                                                                                if (!sub35.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                    sub35.setText(curSubject);
                                                                                                                                                    amo35.setText(curAmount);
                                                                                                                                                    off35.setText(curOffer);
                                                                                                                                                    uni35.setText(curUnit);
                                                                                                                                                    tot35.setText(curTotal);
                                                                                                                                                    sta35.setText(curStatement);
                                                                                                                                                } else {
                                                                                                                                                    if (!sub36.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                        sub36.setText(curSubject);
                                                                                                                                                        amo36.setText(curAmount);
                                                                                                                                                        off36.setText(curOffer);
                                                                                                                                                        uni36.setText(curUnit);
                                                                                                                                                        tot36.setText(curTotal);
                                                                                                                                                        sta36.setText(curStatement);
                                                                                                                                                    } else {
                                                                                                                                                        if (!sub37.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                            sub37.setText(curSubject);
                                                                                                                                                            amo37.setText(curAmount);
                                                                                                                                                            off37.setText(curOffer);
                                                                                                                                                            uni37.setText(curUnit);
                                                                                                                                                            tot37.setText(curTotal);
                                                                                                                                                            sta37.setText(curStatement);
                                                                                                                                                        } else {
                                                                                                                                                            if (!sub38.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                sub38.setText(curSubject);
                                                                                                                                                                amo38.setText(curAmount);
                                                                                                                                                                off38.setText(curOffer);
                                                                                                                                                                uni38.setText(curUnit);
                                                                                                                                                                tot38.setText(curTotal);
                                                                                                                                                                sta38.setText(curStatement);
                                                                                                                                                            } else {
                                                                                                                                                                if (!sub39.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                    sub39.setText(curSubject);
                                                                                                                                                                    amo39.setText(curAmount);
                                                                                                                                                                    off39.setText(curOffer);
                                                                                                                                                                    uni39.setText(curUnit);
                                                                                                                                                                    tot39.setText(curTotal);
                                                                                                                                                                    sta39.setText(curStatement);
                                                                                                                                                                } else {
                                                                                                                                                                    if (!sub40.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                        sub40.setText(curSubject);
                                                                                                                                                                        amo40.setText(curAmount);
                                                                                                                                                                        off40.setText(curOffer);
                                                                                                                                                                        uni40.setText(curUnit);
                                                                                                                                                                        tot40.setText(curTotal);
                                                                                                                                                                        sta40.setText(curStatement);
                                                                                                                                                                    } else {
                                                                                                                                                                        if (!sub41.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                            sub41.setText(curSubject);
                                                                                                                                                                            amo41.setText(curAmount);
                                                                                                                                                                            off41.setText(curOffer);
                                                                                                                                                                            uni41.setText(curUnit);
                                                                                                                                                                            tot41.setText(curTotal);
                                                                                                                                                                            sta41.setText(curStatement);
                                                                                                                                                                        } else {
                                                                                                                                                                            if (!sub42.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                sub42.setText(curSubject);
                                                                                                                                                                                amo42.setText(curAmount);
                                                                                                                                                                                off42.setText(curOffer);
                                                                                                                                                                                uni42.setText(curUnit);
                                                                                                                                                                                tot42.setText(curTotal);
                                                                                                                                                                                sta42.setText(curStatement);
                                                                                                                                                                            } else {
                                                                                                                                                                                if (!sub43.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                    sub43.setText(curSubject);
                                                                                                                                                                                    amo43.setText(curAmount);
                                                                                                                                                                                    off43.setText(curOffer);
                                                                                                                                                                                    uni43.setText(curUnit);
                                                                                                                                                                                    tot43.setText(curTotal);
                                                                                                                                                                                    sta43.setText(curStatement);
                                                                                                                                                                                } else {
                                                                                                                                                                                    if (!sub44.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                        sub44.setText(curSubject);
                                                                                                                                                                                        amo44.setText(curAmount);
                                                                                                                                                                                        off44.setText(curOffer);
                                                                                                                                                                                        uni44.setText(curUnit);
                                                                                                                                                                                        tot44.setText(curTotal);
                                                                                                                                                                                        sta44.setText(curStatement);
                                                                                                                                                                                    } else {
                                                                                                                                                                                        if (!sub45.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                            sub45.setText(curSubject);
                                                                                                                                                                                            amo45.setText(curAmount);
                                                                                                                                                                                            off45.setText(curOffer);
                                                                                                                                                                                            uni45.setText(curUnit);
                                                                                                                                                                                            tot45.setText(curTotal);
                                                                                                                                                                                            sta45.setText(curStatement);
                                                                                                                                                                                        } else {
                                                                                                                                                                                            if (!sub46.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                                sub46.setText(curSubject);
                                                                                                                                                                                                amo46.setText(curAmount);
                                                                                                                                                                                                off46.setText(curOffer);
                                                                                                                                                                                                uni46.setText(curUnit);
                                                                                                                                                                                                tot46.setText(curTotal);
                                                                                                                                                                                                sta46.setText(curStatement);
                                                                                                                                                                                            } else {
                                                                                                                                                                                                if (!sub47.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                                    sub47.setText(curSubject);
                                                                                                                                                                                                    amo47.setText(curAmount);
                                                                                                                                                                                                    off47.setText(curOffer);
                                                                                                                                                                                                    uni47.setText(curUnit);
                                                                                                                                                                                                    tot47.setText(curTotal);
                                                                                                                                                                                                    sta47.setText(curStatement);
                                                                                                                                                                                                } else {
                                                                                                                                                                                                    if (!sub48.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                                        sub48.setText(curSubject);
                                                                                                                                                                                                        amo48.setText(curAmount);
                                                                                                                                                                                                        off48.setText(curOffer);
                                                                                                                                                                                                        uni48.setText(curUnit);
                                                                                                                                                                                                        tot48.setText(curTotal);
                                                                                                                                                                                                        sta48.setText(curStatement);
                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        if (!sub49.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                                            sub49.setText(curSubject);
                                                                                                                                                                                                            amo49.setText(curAmount);
                                                                                                                                                                                                            off49.setText(curOffer);
                                                                                                                                                                                                            uni49.setText(curUnit);
                                                                                                                                                                                                            tot49.setText(curTotal);
                                                                                                                                                                                                            sta49.setText(curStatement);
                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            if (!sub50.getText().toString().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                                                                                                                                                                                                                sub50.setText(curSubject);
                                                                                                                                                                                                                amo50.setText(curAmount);
                                                                                                                                                                                                                off50.setText(curOffer);
                                                                                                                                                                                                                uni50.setText(curUnit);
                                                                                                                                                                                                                tot50.setText(curTotal);
                                                                                                                                                                                                                sta50.setText(curStatement);
                                                                                                                                                                                                            } else {

                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String getTime() {
        String time = "";
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm");
        time = simpledateformat.format(calander.getTime());
        return time;
    }

    private String getDate() {
        String date = "";
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        date = new SimpleDateFormat("yyyy/MM/dd").format(now);
        return date;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        if (!isLayoutsHided || res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (isChanged) {
                final AlertDialog alertDialog = new AlertDialog.Builder(BillActivity.this).create();
                alertDialog.setTitle(res.getString(R.string.alertTitle));
                if (bun.getBoolean("isView", false)) {
                    alertDialog.setMessage(res.getString(R.string.alertDontSaveMessage));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertPositiveButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                    createBillNameDialog(spinBasicSelected + 1, true);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertNegativeButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                            Intent resultIntent = getIntent();
                            setResult(Activity.RESULT_CANCELED, resultIntent);
                            finish();
                        }
                    });

                } else {
                    alertDialog.setMessage(res.getString(R.string.alertContinueLaterMessage));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.alertIgnoreButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                    Intent resultIntent = getIntent();
                                    setResult(Activity.RESULT_CANCELED, resultIntent);
                                    finish();
                                }
                            });
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, res.getString(R.string.alertSaveAsTemButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                    if (basic == 1) {
                                        edit_from_t.setText(edit_from.getText().toString());
                                        spin_basic.setSelection(2);
                                        basic = 3;
                                        createBillNameDialog(3, false);
                                    } else if (basic == 2) {
                                        edit_to_t.setText(edit_to.getText().toString());
                                        spin_basic.setSelection(2);
                                        basic = 3;
                                        createBillNameDialog(3, false);
                                    }
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, res.getString(R.string.alertCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                }

                alertDialog.show();
            } else {
                Intent resultIntent = getIntent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        } else {
            isLayoutsHided = false;
            // Prepare the View for the animation
            linearLayoutOfItemsDidden.setVisibility(View.VISIBLE);
            linearLayoutOfItemsDidden.setAlpha(0.0f);
            linearLayoutOfItemsDidden.animate()
                    .translationY(0)
                    .alpha(1.0f).setListener(null);
        }
    }

    public static class MyParcelable implements Parcelable {
        private int mData;

        public int describeContents() {
            return 0;
        }

        /**
         * save object in parcel
         */
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(mData);
        }

        public static final Parcelable.Creator<MyParcelable> CREATOR
                = new Parcelable.Creator<MyParcelable>() {
            public MyParcelable createFromParcel(Parcel in) {
                return new MyParcelable(in);
            }

            public MyParcelable[] newArray(int size) {
                return new MyParcelable[size];
            }
        };

        /**
         * recreate object from parcel
         */
        private MyParcelable(Parcel in) {
            mData = in.readInt();
        }


    }

}

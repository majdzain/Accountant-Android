package com.zezoo.accountant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;


import android.app.Dialog;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class ListSubjectsActivity extends Fragment implements SearchView.OnQueryTextListener {
    AutoCompleteTextView edit_new_folder, edit_new_buyer;
    EditText edit_new_code, edit_new_subject, edit_new_amount, edit_new_lock, edit_new_a_lock, edit_new_spec, edit_new_unit, edit_new_last, edit_new_a_last;
    Button btn_save_subject, btn_cancel_subject;
    Resources res;
    FloatingActionButton fab;
    TextInputLayout til_subject, til_amount, til_unit, til_code, til_buyer;
    Spinner spin_currency;
    String code;
    SubjectSQLDatabaseHandler db;
    SupplierSQLDatabaseHandler dbs;

    ExpandableListView expListView;
    CustomSubjectExpandableListAdapter expandableListAdapter;
    CustomSearchSubjectsAdapter customSearchSubjectsAdapter;
    ArrayList<String> listGroupTitles;
    HashMap<String, List<SubjectListChildItem>> listChildData;
    ListView searchList;
    SearchView searchView;
    ArrayList<SubjectListChildItem> listAllSubjects;

    int currentColumn;
    String currentFolder;
    int currentId;
    String currentName;
    double currentAmount;
    double currentAmountLast;
    double currentAmountLock;
    double currentCost;
    double currentLast;
    double currentLock;
    String currentSpec;
    String currentCode;
    String currentBuyer;
    String currentTime;
    String currentDate;
    Context context;
    View view;
    SharedPreferences pref;
    boolean isCheckEnabled = false;
    ArrayList<Boolean> groupCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childCheckStates;
    LinearLayout addInvoice, addBill, addDraft;
    CheckBox checkIsShowBillChoiceDialog;
    Button cancelChoice;
    private ImageView spin_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_subjects, container, false);
        context = view.getContext();
        res = getResources();
        res.getString(R.string.sypCode);
        pref = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonS);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTypeDialog(false);
            }
        });
        createSQLDatabase();
        createListFromSQL();
        setHasOptionsMenu(true);
        return view;
    }

    private void createSQLDatabase() {
        // create our sqlite helper class
        db = new SubjectSQLDatabaseHandler(context);
        dbs = new SupplierSQLDatabaseHandler(context);
    }

    private void addToSQL(int Column, int Id, String Buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        SubjectListChildItem subject = new SubjectListChildItem(Column, Id, Buyer, Folder, Name, Amount, AmountLast, AmountLock, Cost, Last, Lock, Spec, Code, Time, Date);
        db.addSubject(subject);
    }

    private void deleteFromSQL(int Column, int Id, String Buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        SubjectListChildItem subject = new SubjectListChildItem(Column, Id, Buyer, Folder, Name, Amount, AmountLast, AmountLock, Cost, Last, Lock, Spec, Code, Time, Date);
        db.deleteSubject(subject);
    }

    private void editFromSQL(int Column, int Id, String Buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        SubjectListChildItem subject = new SubjectListChildItem(Column, Id, Buyer, Folder, Name, Amount, AmountLast, AmountLock, Cost, Last, Lock, Spec, Code, Time, Date);
        db.updateSubject(subject);
    }

    void createListFromSQL() {
        // list all subjects
        List<SubjectListChildItem> subjects = db.allSubjects();
        List<String> folders = db.allFolders();
        if (subjects != null) {
            createList(folders, subjects);
        }
    }

    private void createList(final List<String> folders, final List<SubjectListChildItem> subjects) {
        // Get the expandable list
        expListView = (ExpandableListView) view.findViewById(R.id.subjectsList);
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
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<SubjectListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getFolder().matches(folder)) {
                    pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                }
            }
            listChildData.put(folder, pDistricts);
        }
        expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData);
        // Setting list adapter
        expListView.setAdapter(expandableListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                if (isCheckEnabled) {
                    List<SubjectListChildItem> subjects = db.allSubjects();
                    List<String> folders = db.allFolders();
                    boolean x[] = new boolean[folders.size()];
                    for (int i = 0; i < folders.size(); i++) {
                        x[i] = expListView.isGroupExpanded(i);
                    }
                    CheckBox check = (CheckBox) view.findViewById(R.id.subjectCheck);
                    ArrayList<Boolean> a = childCheckStates.get(groupPosition);
                    a.set(childPosition, !check.isChecked());
                    childCheckStates.put(groupPosition, a);
                    int index = expListView.getFirstVisiblePosition();
                    View v = expListView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
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
                    listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                    listChildData = new HashMap<String, List<SubjectListChildItem>>();
                    // Adding district names and number of population as children
                    for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                        String folder = itemsFolders[i1];
                        List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                        for (int i = 0; i < subjects.size(); i++) {
                            if (subjects.get(i).getFolder().matches(folder)) {
                                pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                            }
                        }
                        listChildData.put(folder, pDistricts);
                    }
                    expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData, childCheckStates, groupCheckStates);
                    // Setting list adapter
                    expListView.setAdapter(expandableListAdapter);
                    //expandableListAdapter.refresh(context,listGroupTitles,listChildData,childCheckStates,groupCheckStates);
                    for (int i = 0; i < folders.size(); i++) {
                        if (x[i])
                            expListView.expandGroup(i);
                    }
                    expListView.setSelectionFromTop(index, top);
                } else {
                    currentColumn = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getColumn();
                    currentId = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getId();
                    currentFolder = listGroupTitles.get(groupPosition);
                    currentName = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getName();
                    currentAmount = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAmount();
                    currentAmountLast = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAmountLast();
                    currentAmountLock = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getAmountLock();
                    currentCost = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCost();
                    currentLast = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getLast();
                    currentLock = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getLock();
                    currentSpec = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getSpec();
                    currentCode = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getCode();
                    currentBuyer = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getBuyer();
                    currentTime = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getTime();
                    currentDate = listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition).getDate();
                    createTypeDialog(true);
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
                    /**SubjectListChildItem SLCI = (SubjectListChildItem) expandableListAdapter.getChild(groupPos, childPos);
                     createPopupChildItemMenu(view, SLCI);**/
                    if (!isCheckEnabled) {
                        isCheckEnabled = true;
                        menuRecordItem.setVisible(false);
                        menuExtractItem.setVisible(true);
                        menuDeleteItem.setVisible(true);
                        menuSelectaItem.setVisible(true);
                        menuDeselectaItem.setVisible(true);
                        menuDeleteaItem.setVisible(false);
                        menuSearchItem.setVisible(false);
                        menuNewItem.setVisible(false);
                        List<SubjectListChildItem> subjects = db.allSubjects();
                        List<String> folders = db.allFolders();
                        int index = expListView.getFirstVisiblePosition();
                        View v = expListView.getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());
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
                        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                        listChildData = new HashMap<String, List<SubjectListChildItem>>();
                        // Adding district names and number of population as children
                        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                            String folder = itemsFolders[i1];
                            List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                            for (int i = 0; i < subjects.size(); i++) {
                                if (subjects.get(i).getFolder().matches(folder)) {
                                    pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                                }
                            }
                            listChildData.put(folder, pDistricts);
                        }
                        expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData, childCheckStates, groupCheckStates);
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
                        CheckBox check = (CheckBox) view.findViewById(R.id.groupSubjectCheck);
                        for (int i = 0; i < childCheckStates.get(groupPos).size(); i++) {
                            ArrayList<Boolean> a = childCheckStates.get(groupPos);
                            a.set(i, !check.isChecked());
                            childCheckStates.put(groupPos, a);
                        }
                        groupCheckStates.set(groupPos, !check.isChecked());
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
                        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                        listChildData = new HashMap<String, List<SubjectListChildItem>>();
                        // Adding district names and number of population as children
                        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                            String folder = itemsFolders[i1];
                            List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                            for (int i = 0; i < subjects.size(); i++) {
                                if (subjects.get(i).getFolder().matches(folder)) {
                                    pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                                }
                            }
                            listChildData.put(folder, pDistricts);
                        }
                        expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData, childCheckStates, groupCheckStates);
                        // Setting list adapter
                        expListView.setAdapter(expandableListAdapter);
                        for (int i = 0; i < folders.size(); i++) {
                            if (x[i])
                                expListView.expandGroup(i);
                        }
                        expListView.setSelectionFromTop(index, top);
                    } else {
                        createPopupGroupItemMenu(view, groupPos);
                    }
                }
                return true;
            }
        });

    }

    TextView txt_date, txt_time;

    private void createTypeDialog(final boolean isEdit) {
        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog_new_subject);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edit_new_folder = (AutoCompleteTextView) d.findViewById(R.id.edit_new_folder);
        edit_new_buyer = (AutoCompleteTextView) d.findViewById(R.id.edit_new_buyer);
        edit_new_code = (EditText) d.findViewById(R.id.edit_new_code);
        edit_new_subject = (EditText) d.findViewById(R.id.edit_new_subject);
        edit_new_amount = (EditText) d.findViewById(R.id.edit_new_amount);
        edit_new_lock = (EditText) d.findViewById(R.id.edit_new_lock);
        edit_new_a_lock = (EditText) d.findViewById(R.id.edit_new_a_lock);
        edit_new_spec = (EditText) d.findViewById(R.id.edit_new_spec);
        edit_new_unit = (EditText) d.findViewById(R.id.edit_new_unit);
        edit_new_last = (EditText) d.findViewById(R.id.edit_new_last);
        edit_new_a_last = (EditText) d.findViewById(R.id.edit_new_a_last);
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
        spin_img = (ImageView) d.findViewById(R.id.spin_img);
        String[] currency = res.getStringArray(R.array.dola_sy);
        ArrayAdapter<CharSequence> spinnerCurrencyArrayAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_item, currency);
        spinnerCurrencyArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spin_currency.setAdapter(spinnerCurrencyArrayAdapter);
        ArrayAdapter<String> folderSuggestionAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, db.allFolders());
        edit_new_folder.setAdapter(folderSuggestionAdapter);
        String allSuppliers[] = new String[dbs.allSuppliers().size()];
        for (int k = 0; k < dbs.allSuppliers().size(); k++) {
            allSuppliers[k] = dbs.allSuppliers().get(k).getSupplier();
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
                    for (int u = 0; u < db.allSubjects().size(); u++) {
                        if (Integer.valueOf(edit_new_code.getText().toString()) == db.allSubjects().get(u).getId() && !isEdit) {
                            if (pref.getString("Language", "arabic").matches("arabic"))
                                edit_new_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_error, 0, 0, 0);
                            else
                                edit_new_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit_error, 0);
                            Toast.makeText(context, res.getString(R.string.error_code), Toast.LENGTH_LONG).show();
                            isCodeExist = true;
                            break;
                        }
                    }
                    for (int v = 0; v < db.allSubjects().size(); v++) {
                        if (edit_new_subject.getText().toString().matches(db.allSubjects().get(v).getName()) && !isEdit) {
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
                            editFromSQL(currentColumn
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
                                    , getTime()
                                    , getDate());
                            createListFromSQL();
                            d.cancel();
                        } else {
                            int column = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("currentSubjectColumn", 0);
                            addToSQL(column + 1
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
                        createListFromSQL();
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
        if (context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("Language", "arabic").matches("arabic")) {
            spin_img.setScaleX(-1);
        } else {

        }
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
            edit_new_folder.setText(currentFolder);
            edit_new_code.setText(String.valueOf(currentId));
            edit_new_subject.setText(currentName);
            edit_new_amount.setText(String.valueOf(currentAmount));
            edit_new_unit.setText(String.valueOf(currentCost));
            edit_new_last.setText(String.valueOf(currentLast));
            edit_new_lock.setText(String.valueOf(currentLock));
            edit_new_a_last.setText(String.valueOf(currentAmountLast));
            edit_new_a_lock.setText(String.valueOf(currentAmountLock));
            edit_new_buyer.setText(String.valueOf(currentBuyer));
            edit_new_spec.setText(currentSpec);
            txt_time.setText(currentTime);
            txt_date.setText(currentDate);
            if (currentCode.matches(res.getString(R.string.sypCode)))
                spin_currency.setSelection(0);
            else
                spin_currency.setSelection(1);
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
                for (int u = 0; u < db.allSubjects().size(); u++) {
                    if (Integer.valueOf(edit_new_code.getText().toString()) == db.allSubjects().get(u).getId()) {
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
                for (int v = 0; v < db.allSubjects().size(); v++) {
                    if (edit_new_subject.getText().toString().matches(db.allSubjects().get(v).getName())) {
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

    private void createPopupChildItemMenu(View v, final SubjectListChildItem SLCI) {
        PopupMenu popup = new PopupMenu(context, v);
        currentColumn = SLCI.getColumn();
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
        currentDate = SLCI.getTime();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pop_sub_edit_item:
                        createTypeDialog(true);
                        return true;
                    case R.id.pop_sub_delete_item:
                        deleteFromSQL(SLCI.getColumn(), SLCI.getId(), SLCI.getBuyer(), SLCI.getFolder(), SLCI.getName(), SLCI.getAmount(), SLCI.getAmountLast(), SLCI.getAmountLock(), SLCI.getCost(), SLCI.getLast(), SLCI.getLock(), SLCI.getSpec(), SLCI.getCode(), SLCI.getTime(), SLCI.getDate());
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
                        List<SubjectListChildItem> subjects = db.allSubjects();
                        for (int i = 0; i < db.allSubjects().size(); i++) {
                            SubjectListChildItem subject = subjects.get(i);
                            if (subject.getFolder().matches(listGroupTitles.get(groupPos))) {
                                deleteFromSQL(subject.getColumn(), subject.getId(), subject.getBuyer(), listGroupTitles.get(groupPos), subject.getName(), subject.getAmount(), subject.getAmountLast(), subject.getAmountLock(), subject.getCost(), subject.getLast(), subject.getLock(), subject.getSpec(), subject.getCode(), subject.getTime(), subject.getDate());
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
                List<SubjectListChildItem> subjects = db.allSubjects();
                for (int i = 0; i < db.allSubjects().size(); i++) {
                    SubjectListChildItem subject = subjects.get(i);
                    if (subject.getFolder().matches(listGroupTitles.get(groupPos))) {
                        if (editFolder.getText().toString().matches(".*[a-zA-Z0-9أ-ي]+.*"))
                            editFromSQL(subject.getColumn(), subject.getId(), subject.getBuyer(), editFolder.getText().toString(), subject.getName(), subject.getAmount(), subject.getAmountLast(), subject.getAmountLock(), subject.getCost(), subject.getLast(), subject.getLock(), subject.getSpec(), subject.getCode(), subject.getTime(), subject.getDate());
                        else
                            editFromSQL(subject.getColumn(), subject.getId(), subject.getBuyer(), res.getString(R.string.without_folder), subject.getName(), subject.getAmount(), subject.getAmountLast(), subject.getAmountLock(), subject.getCost(), subject.getLast(), subject.getLock(), subject.getSpec(), subject.getCode(), subject.getTime(), subject.getDate());
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

    MenuItem menuRecordItem ,menuExtractItem, menuDeleteItem, menuSelectaItem, menuDeselectaItem, menuSearchItem, menuNewItem, menuDeleteaItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subjects, menu);
        menuRecordItem = menu.getItem(0);
        menuExtractItem = menu.getItem(1);
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
            case R.id.menu_search:
                createSearchDialog();
                return true;
            case R.id.menu_extract:
                createExtractDialog();
                return true;
            case R.id.menu_delete:
                List<String> folders = db.allFolders();
                List<SubjectListChildItem> subjects = db.allSubjects();
                listChildData = new HashMap<String, List<SubjectListChildItem>>();
                boolean x[] = new boolean[folders.size()];
                for (int i = 0; i < folders.size(); i++) {
                    x[i] = expListView.isGroupExpanded(i);
                }
                int[] itemsSubjectsColumns = new int[subjects.size()];
                for (int i = 0; i < subjects.size(); i++) {
                    itemsSubjectsColumns[i] = subjects.get(i).getColumn();
                }
                String[] itemsFolders = new String[folders.size()];
                for (int i = 0; i < folders.size(); i++) {
                    itemsFolders[i] = folders.get(i);
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
                listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
                listChildData = new HashMap<String, List<SubjectListChildItem>>();
                // Adding district names and number of population as children
                for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                    String folder = itemsFolders[i1];
                    List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                    for (int i = 0; i < subjects.size(); i++) {
                        if (subjects.get(i).getFolder().matches(folder)) {
                            pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns[i], itemsSubjectsIds[i], itemsSubjectsBuyers[i], folder, itemsSubjectsNames[i], itemsSubjectsAmounts[i], itemsSubjectsAmountLasts[i], itemsSubjectsAmountLocks[i], itemsSubjectsCosts[i], itemsSubjectsLasts[i], itemsSubjectsLocks[i], itemsSubjectsSpecs[i], itemsSubjectsCodes[i], itemsSubjectsTimes[i], itemsSubjectsDates[i]));
                        }
                    }
                    listChildData.put(folder, pDistricts);
                }
                // Adding district names and number of population as children
                for (int i = 0; i < groupCheckStates.size(); i++) {
                    for (int j = 0; j < childCheckStates.get(i).size(); j++) {
                        if (childCheckStates.get(i).get(j)) {
                            db.deleteSubject(listChildData.get(folders.get(i)).get(j));
                        }
                    }
                }
                createListFromSQL();
                isCheckEnabled = false;
                menuExtractItem.setVisible(false);
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
                createTypeDialog(false);
                return true;
            case R.id.menu_deleteAll:
                createAlertDeleteAllDialog();
                return true;
            case R.id.menu_selectAll:
                List<String> folders1 = db.allFolders();
                List<SubjectListChildItem> subjects1 = db.allSubjects();
                boolean x1[] = new boolean[folders1.size()];
                for (int i = 0; i < folders1.size(); i++) {
                    x1[i] = expListView.isGroupExpanded(i);
                }
                int index = expListView.getFirstVisiblePosition();
                View v = expListView.getChildAt(0);
                int top = (v == null) ? 0 : (v.getTop() - expListView.getPaddingTop());


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
                listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders1));
                listChildData = new HashMap<String, List<SubjectListChildItem>>();
                // Adding district names and number of population as children
                for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                    String folder = itemsFolders1[i1];
                    List<SubjectListChildItem> pDistricts = new ArrayList<SubjectListChildItem>();
                    for (int i = 0; i < subjects1.size(); i++) {
                        if (subjects1.get(i).getFolder().matches(folder)) {
                            pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns1[i], itemsSubjectsIds1[i], itemsSubjectsBuyers1[i], folder, itemsSubjectsNames1[i], itemsSubjectsAmounts1[i], itemsSubjectsAmountLasts1[i], itemsSubjectsAmountLocks1[i], itemsSubjectsCosts1[i], itemsSubjectsLasts1[i], itemsSubjectsLocks1[i], itemsSubjectsSpecs1[i], itemsSubjectsCodes1[i], itemsSubjectsTimes1[i], itemsSubjectsDates1[i]));
                        }
                    }
                    listChildData.put(folder, pDistricts);
                }
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
                expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData, childCheckStates, groupCheckStates);
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
                List<String> folders2 = db.allFolders();
                List<SubjectListChildItem> subjects2 = db.allSubjects();
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
                String[] itemsFolders2 = new String[folders2.size()];
                for (int i = 0; i < folders2.size(); i++) {
                    itemsFolders2[i] = folders2.get(i);
                }
                int[] itemsSubjectsColumns2 = new int[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsColumns2[i] = subjects2.get(i).getColumn();
                }
                int[] itemsSubjectsIds2 = new int[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsIds2[i] = subjects2.get(i).getId();
                }
                String[] itemsSubjectsNames2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsNames2[i] = subjects2.get(i).getName();
                }
                double[] itemsSubjectsAmounts2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsAmounts2[i] = subjects2.get(i).getAmount();
                }
                double[] itemsSubjectsAmountLasts2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsAmountLasts2[i] = subjects2.get(i).getAmountLast();
                }
                double[] itemsSubjectsAmountLocks2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsAmountLocks2[i] = subjects2.get(i).getAmountLock();
                }
                double[] itemsSubjectsCosts2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsCosts2[i] = subjects2.get(i).getCost();
                }
                double[] itemsSubjectsLocks2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsLocks2[i] = subjects2.get(i).getLock();
                }
                double[] itemsSubjectsLasts2 = new double[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsLasts2[i] = subjects2.get(i).getLast();
                }
                String[] itemsSubjectsSpecs2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsSpecs2[i] = subjects2.get(i).getSpec();
                }
                String[] itemsSubjectsCodes2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsCodes2[i] = subjects2.get(i).getCode();
                }
                String[] itemsSubjectsBuyers2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsBuyers2[i] = subjects2.get(i).getBuyer();
                }
                String[] itemsSubjectsTimes2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsTimes2[i] = subjects2.get(i).getTime();
                }
                String[] itemsSubjectsDates2 = new String[subjects2.size()];
                for (int i = 0; i < subjects2.size(); i++) {
                    itemsSubjectsDates2[i] = subjects2.get(i).getDate();
                }

                // Setting up list
                listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders2));
                listChildData = new HashMap<String, List<SubjectListChildItem>>();
                // Adding district names and number of population as children
                for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
                    String folder = itemsFolders2[i1];
                    List<SubjectListChildItem> pDistricts = pDistricts = new ArrayList<SubjectListChildItem>();
                    for (int i = 0; i < subjects2.size(); i++) {
                        if (subjects2.get(i).getFolder().matches(folder)) {
                            pDistricts.add(new SubjectListChildItem(itemsSubjectsColumns2[i], itemsSubjectsIds2[i], itemsSubjectsBuyers2[i], folder, itemsSubjectsNames2[i], itemsSubjectsAmounts2[i], itemsSubjectsAmountLasts2[i], itemsSubjectsAmountLocks2[i], itemsSubjectsCosts2[i], itemsSubjectsLasts2[i], itemsSubjectsLocks2[i], itemsSubjectsSpecs2[i], itemsSubjectsCodes2[i], itemsSubjectsTimes2[i], itemsSubjectsDates2[i]));
                        }
                    }
                    listChildData.put(folder, pDistricts);
                }
                expandableListAdapter = new CustomSubjectExpandableListAdapter(context, listGroupTitles, listChildData, childCheckStates, groupCheckStates);
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
                for (int i = 0; i < db.allFolders().size(); i++) {
                    if (!expListView.isGroupExpanded(i))
                        expListView.expandGroup(i, true);
                }
                return true;
            case R.id.menu_colAll:
                for (int i = 0; i < db.allFolders().size(); i++) {
                    if (expListView.isGroupExpanded(i))
                        expListView.collapseGroup(i);
                }
                return true;
            case R.id.menu_record:

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
                        List<SubjectListChildItem> subjects = db.allSubjects();
                        for (int i = 0; i < subjects.size(); i++) {
                            SubjectListChildItem SLCI = subjects.get(i);
                            deleteFromSQL(SLCI.getColumn(), SLCI.getId(), SLCI.getBuyer(), SLCI.getFolder(), SLCI.getName(), SLCI.getAmount(), SLCI.getAmountLast(), SLCI.getAmountLock(), SLCI.getCost(), SLCI.getLast(), SLCI.getLock(), SLCI.getSpec(), SLCI.getCode(), SLCI.getTime(), SLCI.getDate());
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
        d1.setContentView(R.layout.dialog_search_subject);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancel_search = (Button) d1.findViewById(R.id.cancel_dialog_search);
        searchList = (ListView) d1.findViewById(R.id.list_subjects_search);
        searchView = (SearchView) d1.findViewById(R.id.edit_search_subjects);
        listAllSubjects = new ArrayList<SubjectListChildItem>();
        listAllSubjects.addAll(db.allSubjects());
        customSearchSubjectsAdapter = new CustomSearchSubjectsAdapter(context, R.layout.list_subjects_item, listAllSubjects);
        searchList.setAdapter(customSearchSubjectsAdapter);
        searchView.setOnQueryTextListener(this);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_subjects_item, null);
                }
                TextView subjectName = (TextView) convertView.findViewById(R.id.subjectName);
                TextView subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
                TextView subjectCost = (TextView) convertView.findViewById(R.id.subjectCost);
                TextView subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
                String name = subjectName.getText().toString();
                SubjectListChildItem SLCI = db.getSubjectById(Integer.valueOf(name.substring(0, 5).replace(" ", "")));
                currentColumn = SLCI.getColumn();
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
                currentDate = SLCI.getTime();
                d1.cancel();
                createTypeDialog(true);
            }
        });
        searchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_subjects_item, null);
                }
                TextView subjectName = (TextView) convertView.findViewById(R.id.subjectName);
                TextView subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
                TextView subjectCost = (TextView) convertView.findViewById(R.id.subjectCost);
                TextView subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
                String name = subjectName.getText().toString();
                SubjectListChildItem SLCI = db.getSubjectById(Integer.valueOf(name.substring(0, 5).replace(" ", "")));
                createPopupChildItemMenu(convertView, SLCI);
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

    double totalAndFinal = 0.0;

    private void createExtractDialog() {
        final Dialog d1 = new Dialog(context);
        d1.setContentView(R.layout.dialog_bill_choice);
        d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        addInvoice = (LinearLayout) d1.findViewById(R.id.addSalesBill);
        addBill = (LinearLayout) d1.findViewById(R.id.addPurBill);
        addDraft = (LinearLayout) d1.findViewById(R.id.addTemporaryBill);
        checkIsShowBillChoiceDialog = (CheckBox) d1.findViewById(R.id.checkIsShowBillChoiceDialog);
        cancelChoice = (Button) d1.findViewById(R.id.cancel_dialog_choice);
        List<String> folders = db.allFolders();
        List<SubjectListChildItem> subjects = db.allSubjects();
        int[] itemsSubjectsColumns = new int[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            itemsSubjectsColumns[i] = subjects.get(i).getColumn();
        }
        String[] itemsFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            itemsFolders[i] = folders.get(i);
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
        listGroupTitles = new ArrayList<String>(Arrays.asList(itemsFolders));
        listChildData = new HashMap<String, List<SubjectListChildItem>>();
        // Adding district names and number of population as children
        for (int i1 = 0; i1 < listGroupTitles.size(); i1++) {
            String folder = itemsFolders[i1];
            List<SubjectListChildItem> pDistricts = new ArrayList<SubjectListChildItem>();
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getFolder().matches(folder)) {
                    pDistricts.add(subjects.get(i));
                }
            }
            listChildData.put(folder, pDistricts);
        }
        ArrayList<String> productsList = new ArrayList<>();
        ArrayList<Double> amountsList = new ArrayList<>();
        ArrayList<Double> offersList = new ArrayList<>();
        ArrayList<Double> unitsList = new ArrayList<>();
        ArrayList<Double> totalsList = new ArrayList<>();
        ArrayList<String> statesList = new ArrayList<>();
        final String[] products = new String[50];
        final double[] amounts = new double[50];
        final double[] offers = new double[50];
        final double[] units = new double[50];
        final double[] totals = new double[50];
        final String[] states = new String[50];

        for (int o = 0; o < groupCheckStates.size(); o++) {
            for (int p = 0; p < childCheckStates.get(o).size(); p++) {
                if (childCheckStates.get(o).get(p)) {
                    SubjectListChildItem s = listChildData.get(itemsFolders[o]).get(p);
                    productsList.add(String.valueOf(s.getId()) + "-" + s.getName());
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
                    amountsList.add(amount);
                    unitsList.add(unit);
                    offersList.add(0.0);
                    totalsList.add(unit * amount);
                    statesList.add("");
                    totalAndFinal = totalAndFinal + (unit * amount);
                }
            }
        }
        for (int k = 0; k < 50; k++) {
            if (k < productsList.size()) {
                products[k] = productsList.get(k);
                amounts[k] = amountsList.get(k);
                units[k] = unitsList.get(k);
                offers[k] = offersList.get(k);
                totals[k] = totalsList.get(k);
                states[k] = statesList.get(k);
            } else if (k >= productsList.size()) {
                products[k] = "";
                amounts[k] = 0.0;
                units[k] = 0.0;
                offers[k] = 0.0;
                totals[k] = 0.0;
                states[k] = "";
            }
        }

        addInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 2);
                i.putExtra("isView", true);
                i.putExtra("isExtractItems", true);
                i.putExtra("products", products);
                i.putExtra("amounts", amounts);
                i.putExtra("offers", offers);
                i.putExtra("units", units);
                i.putExtra("totals", totals);
                i.putExtra("states", states);
                i.putExtra("total", totalAndFinal);
                startActivity(i);
                d1.cancel();
            }
        });
        addBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 1);
                i.putExtra("isView", true);
                i.putExtra("isExtractItems", true);
                i.putExtra("products", products);
                i.putExtra("amounts", amounts);
                i.putExtra("offers", offers);
                i.putExtra("units", units);
                i.putExtra("totals", totals);
                i.putExtra("states", states);
                startActivity(i);
                d1.cancel();
            }
        });
        addDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BillActivity.class);
                i.putExtra("sub", 3);
                i.putExtra("isView", true);
                i.putExtra("isExtractItems", true);
                i.putExtra("products", products);
                i.putExtra("amounts", amounts);
                i.putExtra("offers", offers);
                i.putExtra("units", units);
                i.putExtra("totals", totals);
                i.putExtra("states", states);
                startActivity(i);
                d1.cancel();
            }
        });
        cancelChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1.cancel();
            }
        });
        d1.setCancelable(false);
        d1.setCanceledOnTouchOutside(false);
        d1.show();
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
        customSearchSubjectsAdapter.filter(text);
        return false;
    }


}

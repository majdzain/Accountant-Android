package com.zezoo.accountant;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.LinkAddress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomSubjectExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listGroupTitle; // header titles
    private HashMap<String, List<SubjectListChildItem>> listChildData;
    private List<Boolean> groupCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childCheckStates;

    private static final int[] EMPTY_STATE_SET = {};
    private static final int[] GROUP_EXPANDED_STATE_SET = {android.R.attr.state_expanded};
    private static final int[][] GROUP_STATE_SETS = {EMPTY_STATE_SET, // 0
            GROUP_EXPANDED_STATE_SET // 1
    };
    private boolean isChecking;
    private boolean isBillPage = false;
    private int basic = 1;

    public CustomSubjectExpandableListAdapter(Context context, List<String> listGroupTitle, HashMap<String, List<SubjectListChildItem>> listChildData) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.isChecking = false;
    }

    public CustomSubjectExpandableListAdapter(Context context, List<String> listGroupTitle, HashMap<String, List<SubjectListChildItem>> listChildData, HashMap<Integer, ArrayList<Boolean>> childCheckStates, List<Boolean> groupCheckStates) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.isChecking = true;
        this.groupCheckStates = groupCheckStates;
        this.childCheckStates = childCheckStates;
    }

    public CustomSubjectExpandableListAdapter(Context context, List<String> listGroupTitle, HashMap<String, List<SubjectListChildItem>> listChildData, HashMap<Integer, ArrayList<Boolean>> childCheckStates, List<Boolean> groupCheckStates, int basic) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.isChecking = true;
        this.groupCheckStates = groupCheckStates;
        this.childCheckStates = childCheckStates;
        this.isBillPage = true;
        this.basic = basic;
    }

    public void refresh(Context context, List<String> listGroupTitle, HashMap<String, List<SubjectListChildItem>> listChildData, HashMap<Integer, ArrayList<Boolean>> childCheckStates, List<Boolean> groupCheckStates) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.isChecking = true;
        this.groupCheckStates = groupCheckStates;
        this.childCheckStates = childCheckStates;
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
        return this.listChildData.get(this.listGroupTitle.get(groupPosition)).get(childPosition);
    }

    public class ChildHolder {
        private HashMap<Integer, View> storedViews = new HashMap<Integer, View>();


        public ChildHolder addView(View view)
        {
            int id = view.getId();
            storedViews.put(id, view);
            return this;
        }

        public View getView(int id)
        {
            return storedViews.get(id);
        }
        TextView subjectName;
        TextView subjectAmount;
        TextView subjectCost;
        TextView subjectLast;
        CheckBox subjectCheck;
        LinearLayout subjectLinear;
        ImageView subjectImage;
        SeekBar seekBarA;
        SeekBar seekBarU;
        LinearLayout linearSeeks;
        TextView txtAmountS;
        TextView txtUnitS;
    }

    public class GroupHolder {
        TextView groupName;
        CheckBox groupCheck;
        ImageView groupImage;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder vh;
        final int mGroupPos = groupPosition;
        final int mChildPos = childPosition;
        final SubjectListChildItem memData = (SubjectListChildItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_subjects_item, null);
            vh = new ChildHolder();
            vh.subjectName = (TextView) convertView.findViewById(R.id.subjectName);
            vh.subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
            vh.subjectCost = (TextView) convertView.findViewById(R.id.subjectCost);
            vh.subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
            vh.subjectCheck = (CheckBox) convertView.findViewById(R.id.subjectCheck);
            vh.seekBarA = (SeekBar) convertView.findViewById(R.id.seekBar2);
            vh.seekBarU = (SeekBar) convertView.findViewById(R.id.seekBar1);
            vh.txtAmountS = (TextView) convertView.findViewById(R.id.txt_amount);
            vh.txtUnitS = (TextView) convertView.findViewById(R.id.txt_unit);
            vh.subjectImage = (ImageView) convertView.findViewById(R.id.groupSubjectsIndicator);
            vh.subjectLinear = (LinearLayout) convertView.findViewById(R.id.list_subjects_it);
            vh.linearSeeks = (LinearLayout) convertView.findViewById(R.id.linearSeeks);
            convertView.setTag(vh);
        } else {
            vh = (ChildHolder) convertView.getTag();
        }

        vh.subjectName.setText(String.valueOf(memData.getId()) + " - " + memData.getName());
        vh.subjectAmount.setText(String.valueOf(memData.getAmount()));
        vh.subjectCost.setText(String.valueOf(memData.getCost()) + " " + memData.getCode());
        vh.subjectLast.setText(String.valueOf(memData.getLast()) + " " + memData.getCode());
        if (isChecking) {
            vh.subjectCheck.setVisibility(View.VISIBLE);
            vh.subjectCheck.setChecked(childCheckStates.get(groupPosition).get(childPosition));
            vh.subjectImage.setVisibility(View.GONE);
            if (isBillPage) {
                vh.linearSeeks.setVisibility(View.VISIBLE);
                vh.txtUnitS.setVisibility(View.VISIBLE);
                vh.txtAmountS.setVisibility(View.VISIBLE);
                if (basic == 1) {
                    vh.seekBarA.setMax(100);
                    vh.txtAmountS.setText("0");
                    if (memData.getCost() >= 1 && memData.getCost() < 100) {
                        vh.seekBarU.setMax(500);
                        vh.seekBarU.setProgress((int) memData.getCost());
                    } else if (memData.getCost() >= 100 && memData.getCost() <= 1000) {
                        vh.seekBarU.setMax(5000);
                        vh.seekBarU.setProgress((int) memData.getCost());
                    } else if (memData.getCost() > 1000 && memData.getCost() <= 10000) {
                        vh.seekBarU.setMax(50000);
                        vh.seekBarU.setProgress((int) memData.getCost());
                    } else if (memData.getCost() > 10000 && memData.getCost() <= 100000) {
                        vh.seekBarU.setMax(500000);
                        vh.seekBarU.setProgress((int) memData.getCost());
                    } else {
                        vh.seekBarU.setMax(5000000);
                        vh.seekBarU.setProgress((int) memData.getCost());
                    }
                } else {
                    if (memData.getAmountLock() == 0) {
                        vh.seekBarA.setMax((int) memData.getAmount());
                        vh.txtAmountS.setText("0");
                    } else {
                        vh.seekBarA.setMax(100);
                        vh.seekBarA.setProgress((int) memData.getAmountLock());
                    }
                    if (memData.getLock() == 0) {
                        if (memData.getCost() >= 1 && memData.getCost() < 100) {
                            vh.seekBarU.setMax(500);
                            vh.seekBarU.setProgress((int) memData.getCost());
                        } else if (memData.getCost() >= 100 && memData.getCost() <= 1000) {
                            vh.seekBarU.setMax(5000);
                            vh.seekBarU.setProgress((int) memData.getCost());
                        } else if (memData.getCost() > 1000 && memData.getCost() <= 10000) {
                            vh.seekBarU.setMax(50000);
                            vh.seekBarU.setProgress((int) memData.getCost());
                        } else if (memData.getCost() > 10000 && memData.getCost() <= 100000) {
                            vh.seekBarU.setMax(500000);
                            vh.seekBarU.setProgress((int) memData.getCost());
                        } else {
                            vh.seekBarU.setMax(5000000);
                            vh.seekBarU.setProgress((int) memData.getCost());
                        }
                    }else {
                        if (memData.getCost() >= 1 && memData.getCost() < 100) {
                            vh.seekBarU.setMax(500);
                            vh.seekBarU.setProgress((int) memData.getLock());
                        } else if (memData.getCost() >= 100 && memData.getCost() <= 1000) {
                            vh.seekBarU.setMax(5000);
                            vh.seekBarU.setProgress((int) memData.getLock());
                        } else if (memData.getCost() > 1000 && memData.getCost() <= 10000) {
                            vh.seekBarU.setMax(50000);
                            vh.seekBarU.setProgress((int) memData.getLock());
                        } else if (memData.getCost() > 10000 && memData.getCost() <= 100000) {
                            vh.seekBarU.setMax(500000);
                            vh.seekBarU.setProgress((int) memData.getLock());
                        } else {
                            vh.seekBarU.setMax(5000000);
                            vh.seekBarU.setProgress((int) memData.getLock());
                        }
                    }
                }
                vh.seekBarA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        vh.txtAmountS.setText(String.valueOf(seekBar.getProgress()));
                        if(basic != 1&& memData.getAmountLock() != 0 && seekBar.getProgress() < (int) memData.getAmountLock()){
                            seekBar.setProgress((int) memData.getAmountLock());
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                vh.seekBarU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        vh.txtUnitS.setText(String.valueOf(seekBar.getProgress()));
                        if(basic != 1&& memData.getLock() != 0  && seekBar.getProgress() < (int) memData.getLock()){
                            seekBar.setProgress((int) memData.getLock());
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        } else {
            vh.subjectCheck.setVisibility(View.GONE);
            vh.subjectImage.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChildData.get(this.listGroupTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGroupTitle.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listGroupTitle.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final String listTitle = (String) getGroup(groupPosition);
        final GroupHolder vh;
        final int mGroupPos = groupPosition;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_subjects_item, null);
            vh = new GroupHolder();
            vh.groupName = (TextView) convertView.findViewById(R.id.listSubjectItemTitle);
            vh.groupCheck = (CheckBox) convertView.findViewById(R.id.groupSubjectCheck);
            vh.groupImage = (ImageView) convertView.findViewById(R.id.groupSubjectsIndicator);
            convertView.setTag(vh);
        } else {
            vh = (GroupHolder) convertView.getTag();
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listSubjectItemTitle);
        vh.groupName.setTypeface(null, Typeface.BOLD);
        vh.groupName.setText(listTitle);
        //Image view which you put in row_group_list.xml

        if (isChecking) {
            vh.groupImage.setVisibility(View.GONE);
            vh.groupCheck.setVisibility(View.VISIBLE);
            vh.groupCheck.setChecked(groupCheckStates.get(groupPosition));
        } else {
            vh.groupCheck.setVisibility(View.GONE);
            vh.groupImage.setVisibility(View.VISIBLE);
            View ind = convertView.findViewById(R.id.groupSubjectsIndicator);
            if (ind != null) {
                ImageView indicator = (ImageView) ind;
                if (getChildrenCount(groupPosition) == 0) {
                    indicator.setVisibility(View.INVISIBLE);
                } else {
                    indicator.setVisibility(View.VISIBLE);
                    int stateSetIndex = (isExpanded ? 1 : 0);
                    Drawable drawable = indicator.getDrawable();
                    drawable.setState(GROUP_STATE_SETS[stateSetIndex]);
                }
            }
        }
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


}
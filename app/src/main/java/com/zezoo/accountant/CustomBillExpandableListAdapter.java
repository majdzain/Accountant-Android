package com.zezoo.accountant;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomBillExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listGroupTitle; // header titles
    private HashMap<String, List<BillListChildItem>> listChildData;
    private List<Boolean> groupCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childCheckStates;
    private static final int[] EMPTY_STATE_SET = {};
    private static final int[] GROUP_EXPANDED_STATE_SET = {android.R.attr.state_expanded};
    private static final int[][] GROUP_STATE_SETS = {EMPTY_STATE_SET, // 0
            GROUP_EXPANDED_STATE_SET // 1
    };
    private int type;
    private boolean isChecking;

    public CustomBillExpandableListAdapter(Context context, List<String> listGroupTitle, HashMap<String, List<BillListChildItem>> listChildData, int type) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.type = type;
        this.isChecking = false;
    }

    public CustomBillExpandableListAdapter(Context context, List<String> listGroupTitle, HashMap<String, List<BillListChildItem>> listChildData, int type,HashMap<Integer, ArrayList<Boolean>> childCheckStates, List<Boolean> groupCheckStates) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.type = type;
        this.isChecking = true;
        this.groupCheckStates = groupCheckStates;
        this.childCheckStates = childCheckStates;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
        return this.listChildData.get(this.listGroupTitle.get(groupPosition)).get(childPosition);
    }
    public class ChildHolder {
        TextView billName;
        TextView billDate;
        TextView billFinal;
        TextView billSupCus;
        CheckBox billCheckP;
        CheckBox billCheckS;
        CheckBox billCheckT;
        LinearLayout billLinear;
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
        BillListChildItem memData = (BillListChildItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_bills_item, null);
            vh = new ChildHolder();
            vh.billName = (TextView) convertView.findViewById(R.id.billName);
            vh.billDate = (TextView) convertView.findViewById(R.id.billDate);
            vh.billFinal = (TextView) convertView.findViewById(R.id.billFinal);
            vh.billSupCus = (TextView) convertView.findViewById(R.id.billSupCus);
            vh.billCheckP = (CheckBox) convertView.findViewById(R.id.billCheckP);
            vh.billCheckS = (CheckBox) convertView.findViewById(R.id.billCheckS);
            vh.billCheckT = (CheckBox) convertView.findViewById(R.id.billCheckT);
            vh.billLinear = (LinearLayout) convertView.findViewById(R.id.bills_linear);
            convertView.setTag(vh);
        } else {
            vh = (ChildHolder) convertView.getTag();
        }

        if (type == 1) {
            vh.billName.setText(memData.getName());
            vh.billSupCus.setText(memData.getTo());
            vh.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_purchases_item_selector));
            vh.billCheckS.setVisibility(View.GONE);
            vh.billCheckT.setVisibility(View.GONE);
            if (isChecking) {
                vh.billCheckP.setVisibility(View.VISIBLE);
                vh.billCheckP.setChecked(childCheckStates.get(groupPosition).get(childPosition));
            } else {
                vh.billCheckP.setVisibility(View.GONE);
            }
        } else if (type == 2) {
            vh.billName.setText(memData.getName());
            vh.billSupCus.setText(memData.getFrom());
            vh.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_sales_item_selector));
            vh.billCheckP.setVisibility(View.GONE);
            vh.billCheckT.setVisibility(View.GONE);
            if (isChecking) {
                vh.billCheckS.setVisibility(View.VISIBLE);
                vh.billCheckS.setChecked(childCheckStates.get(groupPosition).get(childPosition));
            } else {
                vh.billCheckS.setVisibility(View.GONE);
            }
        } else {
            vh.billName.setText(memData.getName());
            vh.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_temporary_item_selector));
            if (memData.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*") && !memData.getTo().matches(context.getResources().getString(R.string.without_folder))) {
                vh.billSupCus.setText(context.getResources().getString(R.string.F) + ": " + memData.getFrom() + " " + context.getResources().getString(R.string.T) + ": " + memData.getTo());
            } else if (!memData.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*") && memData.getTo().matches(context.getResources().getString(R.string.without_folder))) {
                vh.billSupCus.setText("");
            } else if (!memData.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
                vh.billSupCus.setText(context.getResources().getString(R.string.T) + ": " + memData.getTo());
            } else if (memData.getTo().matches(context.getResources().getString(R.string.without_folder))) {
                vh.billSupCus.setText(context.getResources().getString(R.string.F) + ": " + memData.getFrom());
            }
            vh.billCheckP.setVisibility(View.GONE);
            vh.billCheckS.setVisibility(View.GONE);
            if (isChecking) {
                vh.billCheckT.setVisibility(View.VISIBLE);
                vh.billCheckT.setChecked(childCheckStates.get(groupPosition).get(childPosition));
            } else {
                vh.billCheckT.setVisibility(View.GONE);
            }
        }
        vh.billDate.setText(memData.getDate() + " | " + memData.getTime());
        vh.billFinal.setText(String.valueOf(memData.getFinal()) + " " + memData.getCurrency());

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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        final GroupHolder vh;
        final int mGroupPos = groupPosition;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vh = new GroupHolder();
            if (type == 1) {
                convertView = infalInflater.inflate(R.layout.list_group_purchases_item, null);
                vh.groupCheck = (CheckBox) convertView.findViewById(R.id.groupPurCheck);
            } else if (type == 2) {
                convertView = infalInflater.inflate(R.layout.list_group_sales_item, null);
                vh.groupCheck = (CheckBox) convertView.findViewById(R.id.groupSalCheck);
            } else {
                convertView = infalInflater.inflate(R.layout.list_group_temporary_item, null);
                vh.groupCheck = (CheckBox) convertView.findViewById(R.id.groupTemCheck);
            }
            vh.groupName = (TextView) convertView.findViewById(R.id.listBillItemTitle);
            vh.groupImage = (ImageView) convertView.findViewById(R.id.groupBillsIndicator);
            convertView.setTag(vh);
        } else {
            vh = (GroupHolder) convertView.getTag();
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listBillItemTitle);
        vh.groupName.setTypeface(null, Typeface.BOLD);
        vh.groupName.setText(listTitle);
        //Image view which you put in row_group_list.xml
        View ind = convertView.findViewById(R.id.groupBillsIndicator);
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
        if (isChecking) {
            vh.groupImage.setVisibility(View.GONE);
            vh.groupCheck.setVisibility(View.VISIBLE);
            vh.groupCheck.setChecked(groupCheckStates.get(groupPosition));
        } else {
            vh.groupCheck.setVisibility(View.GONE);
            vh.groupImage.setVisibility(View.VISIBLE);
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

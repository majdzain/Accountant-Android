package com.zezoo.accountant;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class CustomBondExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listGroupTitle; // header titles
    private List<PBondListChildItem> listChildDataP;
    private List<RBondListChildItem> listChildDataR;
    private List<JBondListChildItem> listChildDataJ;
    private static final int[] EMPTY_STATE_SET = {};
    private static final int[] GROUP_EXPANDED_STATE_SET = {android.R.attr.state_expanded};
    private static final int[][] GROUP_STATE_SETS = {EMPTY_STATE_SET, // 0
            GROUP_EXPANDED_STATE_SET // 1
    };

    public CustomBondExpandableListAdapter(Context context, List<String> listGroupTitle, List<PBondListChildItem> listChildDataP, List<RBondListChildItem> listChildDataR, List<JBondListChildItem> listChildDataJ) {
        this.context = context;
        this.listGroupTitle = listGroupTitle;
        this.listChildDataP = listChildDataP;
        this.listChildDataR = listChildDataR;
        this.listChildDataJ = listChildDataJ;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
        if (this.listGroupTitle.get(groupPosition).matches("Payment Bonds"))
            return this.listChildDataP.get(childPosition);
        else if (this.listGroupTitle.get(groupPosition).matches("Receipt Bonds"))
            return this.listChildDataR.get(childPosition);
        else
            return this.listChildDataJ.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition,int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_bonds_item, null);
        }
        TextView bondName = (TextView) convertView.findViewById(R.id.bondName);
        TextView bondDate = (TextView) convertView.findViewById(R.id.bondDate);
        TextView bondNumber = (TextView) convertView.findViewById(R.id.bondNumber);
        TextView bondAmount = (TextView) convertView.findViewById(R.id.bondAmount);
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.bonds_linear);
        if (this.listGroupTitle.get(groupPosition).matches("Payment Bonds")) {
            PBondListChildItem memData = (PBondListChildItem) getChild(groupPosition, childPosition);
            ll.setBackground(context.getResources().getDrawable(R.drawable.list_p_bond_item_selector));
            bondName.setText(memData.getDebtor());
            bondDate.setText(memData.getDate() + " | " + memData.getTime());
            bondNumber.setText(memData.getPhone());
            bondAmount.setText(String.valueOf(memData.getAmount()) + " " + memData.getCurrency());
        } else if (this.listGroupTitle.get(groupPosition).matches("Receipt Bonds")) {
            ll.setBackground(context.getResources().getDrawable(R.drawable.list_r_bond_item_selector));
            RBondListChildItem memData = (RBondListChildItem) getChild(groupPosition, childPosition);
            bondName.setText(memData.getCreditor());
            bondDate.setText(memData.getDate() + " | " + memData.getTime());
            bondNumber.setText(memData.getPhone());
            bondAmount.setText(String.valueOf(memData.getAmount()) + " " + memData.getCurrency());
        } else {
            ll.setBackground(context.getResources().getDrawable(R.drawable.list_j_bond_item_selector));
            JBondListChildItem memData = (JBondListChildItem) getChild(groupPosition, childPosition);
            bondName.setText(memData.getDebCre());
            bondDate.setText(memData.getDate() + " | " + memData.getTime());
            bondNumber.setText(memData.getPhone());
            bondAmount.setText("D: " + String.valueOf(memData.getDAmount()) + " C: " + String.valueOf(memData.getCAmount()) + " " + memData.getCurrency());
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listGroupTitle.get(groupPosition).matches("Payment Bonds"))
            return this.listChildDataP.size();
        else if (this.listGroupTitle.get(groupPosition).matches("Receipt Bonds"))
            return this.listChildDataR.size();
        else
            return this.listChildDataJ.size();
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
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_bond_item, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listBondItemTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        //Image view which you put in row_group_list.xml
        View ind = convertView.findViewById(R.id.groupBondsIndicator);
        ImageView indicator = (ImageView) ind;
        if (this.listGroupTitle.get(groupPosition).matches("Payment Bonds"))
            indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.expander_group_p_bonds));
        else if (this.listGroupTitle.get(groupPosition).matches("Receipt Bonds"))
            indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.expander_group_r_bonds));
        else
            indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.expander_group_j_bonds));
        if (ind != null) {
            if (getChildrenCount(groupPosition) == 0) {
                indicator.setVisibility(View.INVISIBLE);
            } else {
                indicator.setVisibility(View.VISIBLE);
                int stateSetIndex = (isExpanded ? 1 : 0);
                Drawable drawable = indicator.getDrawable();
                drawable.setState(GROUP_STATE_SETS[stateSetIndex]);
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

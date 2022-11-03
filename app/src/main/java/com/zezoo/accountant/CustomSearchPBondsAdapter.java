package com.zezoo.accountant;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomSearchPBondsAdapter extends ArrayAdapter<PBondListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<PBondListChildItem> bondsList;
    private ArrayList<PBondListChildItem> filteredPBondsList;
    String filteredText;

    public CustomSearchPBondsAdapter(Context context, int resId, ArrayList<PBondListChildItem> bonds) {
        super(context, resId);
        this.bondsList = bonds;
        this.filteredPBondsList = bonds;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredPBondsList = new ArrayList<PBondListChildItem>();
        this.filteredPBondsList.addAll(bondsList);
        bondsList.clear();
    }

    /*private view holder class*/
    private class ViewHolder {
        LinearLayout bondLinear;
        TextView bondName;
        TextView bondDate;
        TextView bondNumber;
        TextView bondAmount;
    }

    @Override
    public int getCount() {
        return bondsList.size();
    }

    @Override
    public PBondListChildItem getItem(int position) {
        return bondsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchPBondsAdapter.ViewHolder holder = null;
        PBondListChildItem bond = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_bonds_item, null);
            holder = new CustomSearchPBondsAdapter.ViewHolder();
            holder.bondLinear = (LinearLayout) convertView.findViewById(R.id.bonds_linear);
            holder.bondName = (TextView) convertView.findViewById(R.id.bondName);
            holder.bondDate = (TextView) convertView.findViewById(R.id.bondDate);
            holder.bondNumber = (TextView) convertView.findViewById(R.id.bondNumber);
            holder.bondAmount = (TextView) convertView.findViewById(R.id.bondAmount);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchPBondsAdapter.ViewHolder) convertView.getTag();
        holder.bondLinear.setBackground(context.getResources().getDrawable(R.drawable.list_p_bond_item_selector));
        String name = bond.getDebtor();
        String number = (bond.getPhone());
        String date = bond.getDate();
        String time = bond.getTime();


        int startPosN = name.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosN = startPosN + filteredText.length();
        int startPosC = number.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosC = startPosC + filteredText.length();
        int startPosD = date.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosD = startPosD + filteredText.length();
        int startPosT = time.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosT = startPosT + filteredText.length();

        if (startPosN != -1) // This should always be true, just a sanity check
        {
            Spannable spannableN = new SpannableString(name);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableN.setSpan(highlightSpan, startPosN, endPosN, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.bondName.setText(spannableN);
        } else
            holder.bondName.setText(name);
        if (startPosC != -1) // This should always be true, just a sanity check
        {
            Spannable spannableC = new SpannableString(number);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableC.setSpan(highlightSpan, startPosC, endPosC, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.bondNumber.setText(spannableC);
        } else
            holder.bondNumber.setText(number);
        if (startPosD != -1) // This should always be true, just a sanity check
        {
            Spannable spannableD = new SpannableString(date);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableD.setSpan(highlightSpan, startPosD, endPosD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.bondDate.setText(spannableD + " | " + time);
        } else
            holder.bondDate.setText(date + " | " + time);
        if (startPosT != -1) // This should always be true, just a sanity check
        {
            Spannable spannableT = new SpannableString(time);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableT.setSpan(highlightSpan, startPosT, endPosT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.bondDate.setText(date + " | " + spannableT);
        } else
            holder.bondDate.setText(date + " | " + time);
        holder.bondAmount.setText(bond.getAmount() + " " + bond.getCurrency());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        bondsList.clear();
        if (charText.length() == 0) {
        } else {
            for (PBondListChildItem bond : filteredPBondsList) {
                if ((bond.getDebtor() + bond.getTime() + bond.getDate()+ bond.getPhone()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    bondsList.add(bond);
                }
            }
        }
        notifyDataSetChanged();
    }

}

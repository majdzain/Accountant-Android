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

public class CustomSearchBillsAdapter extends ArrayAdapter<BillListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<BillListChildItem> billsList;
    private ArrayList<BillListChildItem> filteredBillsList;
    String filteredText;

    public CustomSearchBillsAdapter(Context context, int resId, ArrayList<BillListChildItem> bills) {
        super(context, resId);
        this.billsList = bills;
        this.filteredBillsList = bills;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredBillsList = new ArrayList<BillListChildItem>();
        this.filteredBillsList.addAll(billsList);
        billsList.clear();
    }

    /*private view holder class*/
    private class ViewHolder {
        LinearLayout billLinear;
        TextView billName;
        TextView billDate;
        TextView billFinal;
        TextView billSupCus;
    }

    @Override
    public int getCount() {
        return billsList.size();
    }

    @Override
    public BillListChildItem getItem(int position) {
        return billsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchBillsAdapter.ViewHolder holder = null;
        BillListChildItem bill = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_bills_item, null);
            holder = new CustomSearchBillsAdapter.ViewHolder();
            holder.billLinear = (LinearLayout) convertView.findViewById(R.id.bills_linear);
            holder.billName = (TextView) convertView.findViewById(R.id.billName);
            holder.billDate = (TextView) convertView.findViewById(R.id.billDate);
            holder.billFinal = (TextView) convertView.findViewById(R.id.billFinal);
            holder.billSupCus = (TextView) convertView.findViewById(R.id.billSupCus);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchBillsAdapter.ViewHolder) convertView.getTag();
        String name = bill.getName();
        String date = bill.getDate();
        String time = bill.getTime();
        String sup = bill.getFrom();
        String cus = bill.getTo();


        int startPosN = name.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosN = startPosN + filteredText.length();
        int startPosD = date.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosD = startPosD + filteredText.length();
        int startPosT = time.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosT = startPosT + filteredText.length();
        int startPosS = sup.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosS = startPosS + filteredText.length();
        int startPosC = cus.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosC = startPosC + filteredText.length();

        if (startPosN != -1) // This should always be true, just a sanity check
        {
            Spannable spannableN = new SpannableString(name);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableN.setSpan(highlightSpan, startPosN, endPosN, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.billName.setText(spannableN);
        }
        else
            holder.billName.setText(name);
        if (startPosD != -1) // This should always be true, just a sanity check
        {
            Spannable spannableD = new SpannableString(date);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableD.setSpan(highlightSpan, startPosD, endPosD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.billDate.setText(spannableD + " | " + time);
        }
        else
            holder.billDate.setText(date + " | " + time);
        if (startPosT != -1) // This should always be true, just a sanity check
        {
            Spannable spannableT = new SpannableString(time);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableT.setSpan(highlightSpan, startPosT, endPosT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.billDate.setText(date + " | " + spannableT);
        }
        else
            holder.billDate.setText(date + " | " + time);
        if(bill.getType() == 1){
            holder.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_purchases_item_selector));
            if (startPosC != -1) // This should always be true, just a sanity check
            {
                Spannable spannableC = new SpannableString(cus);
                ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
                TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
                spannableC.setSpan(highlightSpan, startPosC, endPosC, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.billSupCus.setText(spannableC);
            }
            else
                holder.billSupCus.setText(cus);
        }else if(bill.getType() == 2){
            holder.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_sales_item_selector));
            if (startPosS != -1) // This should always be true, just a sanity check
            {
                Spannable spannableS = new SpannableString(sup);
                ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
                TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
                spannableS.setSpan(highlightSpan, startPosS, endPosS, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.billSupCus.setText(spannableS);
            }
            else
                holder.billSupCus.setText(sup);
        }else{
            holder.billLinear.setBackground(context.getResources().getDrawable(R.drawable.list_temporary_item_selector));
            if (bill.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*") && !bill.getTo().matches(context.getResources().getString(R.string.without_folder))) {
               holder.billSupCus.setText(context.getResources().getString(R.string.F) + ": " + bill.getFrom() + " " + context.getResources().getString(R.string.T) + ": " + bill.getTo());
            } else if (!bill.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*") && bill.getTo().matches(context.getResources().getString(R.string.without_folder))) {
               holder.billSupCus.setText("");
            } else if (!bill.getFrom().matches(".*[0-9a-zA-Zأ-ي]+.*")) {
               holder.billSupCus.setText(context.getResources().getString(R.string.T) + ": " + bill.getTo());
            } else if (bill.getTo().matches(context.getResources().getString(R.string.without_folder))) {
               holder.billSupCus.setText(context.getResources().getString(R.string.F) + ": " + bill.getFrom());
            }
        }
        holder.billFinal.setText(String.valueOf(bill.getFinal()) + " " + bill.getCurrency());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        billsList.clear();
        if (charText.length() == 0) {
        } else {
            for (BillListChildItem bill : filteredBillsList) {
                if ((bill.getName() + bill.getDate() + bill.getTime() + bill.getFrom() + bill.getTo()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    billsList.add(bill);
                }
            }
        }
        notifyDataSetChanged();
    }

}

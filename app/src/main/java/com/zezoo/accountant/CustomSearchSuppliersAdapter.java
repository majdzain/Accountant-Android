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

public class CustomSearchSuppliersAdapter extends ArrayAdapter<SupplierListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<SupplierListChildItem> suppliersList;
    private ArrayList<SupplierListChildItem> filteredSuppliersList;
    String filteredText;

    public CustomSearchSuppliersAdapter(Context context, int resId, ArrayList<SupplierListChildItem> suppliers) {
        super(context, resId);
        this.suppliersList = suppliers;
        this.filteredSuppliersList = suppliers;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredSuppliersList = new ArrayList<SupplierListChildItem>();
        this.filteredSuppliersList.addAll(suppliersList);
        suppliersList.clear();
    }

    /*private view holder class*/
    private class ViewHolder {
        LinearLayout supplierLinear;
        TextView supplierName;
        TextView supplierDate;
        TextView supplierNumber;
        TextView supplierAccount;
    }

    @Override
    public int getCount() {
        return suppliersList.size();
    }

    @Override
    public SupplierListChildItem getItem(int position) {
        return suppliersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchSuppliersAdapter.ViewHolder holder = null;
        SupplierListChildItem supplier = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_suppliers_item, null);
            holder = new CustomSearchSuppliersAdapter.ViewHolder();
            holder.supplierLinear = (LinearLayout) convertView.findViewById(R.id.suppliers_linear);
            holder.supplierName = (TextView) convertView.findViewById(R.id.supplierName);
            holder.supplierDate = (TextView) convertView.findViewById(R.id.supplierDate);
            holder.supplierNumber = (TextView) convertView.findViewById(R.id.supplierNumber);
            holder.supplierAccount = (TextView) convertView.findViewById(R.id.supplierAccount);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchSuppliersAdapter.ViewHolder) convertView.getTag();
        String name = supplier.getSupplier();
        String namePh = (supplier.getSupplierPho());
        String date = supplier.getDate();
        String time = supplier.getTime();
        String company = supplier.getCompany();
        String companyPh = supplier.getCompanyPho();
        String account = String.valueOf(supplier.getAccount());
        String cash = supplier.getCash();


        int startPosN = name.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosN = startPosN + filteredText.length();
        int startPosNP = namePh.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosNP = startPosNP + filteredText.length();
        int startPosD = date.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosD = startPosD + filteredText.length();
        int startPosT = time.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosT = startPosT + filteredText.length();
        int startPosC = company.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosC = startPosC + filteredText.length();
        int startPosCP = companyPh.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosCP = startPosCP + filteredText.length();

        if (startPosN != -1) // This should always be true, just a sanity check
        {
            Spannable spannableN = new SpannableString(name);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableN.setSpan(highlightSpan, startPosN, endPosN, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.supplierName.setText(spannableN);
        } else
            holder.supplierName.setText(name);
        if (startPosNP != -1) // This should always be true, just a sanity check
        {
            Spannable spannableNP = new SpannableString(namePh);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableNP.setSpan(highlightSpan, startPosNP, endPosNP, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.supplierNumber.setText(spannableNP);
        } else
            holder.supplierNumber.setText(namePh);
        if (startPosD != -1) // This should always be true, just a sanity check
        {
            Spannable spannableD = new SpannableString(date);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableD.setSpan(highlightSpan, startPosD, endPosD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.supplierDate.setText(spannableD + " | " + time);
        } else
            holder.supplierDate.setText(date + " | " + time);
        if (startPosT != -1) // This should always be true, just a sanity check
        {
            Spannable spannableT = new SpannableString(time);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableT.setSpan(highlightSpan, startPosT, endPosT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.supplierDate.setText(date + " | " + spannableT);
        } else
            holder.supplierDate.setText(date + " | " + time);
        holder.supplierAccount.setText(cash.charAt(0) + " : " + account + " " + supplier.getCurrency());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        suppliersList.clear();
        if (charText.length() == 0) {
        } else {
            for (SupplierListChildItem supplier : filteredSuppliersList) {
                if ((supplier.getSupplier() + supplier.getTime() + supplier.getDate() + supplier.getCompany() + supplier.getCompanyPho() + supplier.getSupplierPho()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    suppliersList.add(supplier);
                }
            }
        }
        notifyDataSetChanged();
    }

}

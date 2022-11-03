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

public class CustomSearchCustomersAdapter extends ArrayAdapter<CustomerListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<CustomerListChildItem> customersList;
    private ArrayList<CustomerListChildItem> filteredCustomersList;
    String filteredText;

    public CustomSearchCustomersAdapter(Context context, int resId, ArrayList<CustomerListChildItem> customers) {
        super(context, resId);
        this.customersList = customers;
        this.filteredCustomersList = customers;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredCustomersList = new ArrayList<CustomerListChildItem>();
        this.filteredCustomersList.addAll(customersList);
        customersList.clear();
    }

    /*private view holder class*/
    private class ViewHolder {
        LinearLayout customerLinear;
        TextView customerName;
        TextView customerDate;
        TextView customerNumber;
        TextView customerAccount;
    }

    @Override
    public int getCount() {
        return customersList.size();
    }

    @Override
    public CustomerListChildItem getItem(int position) {
        return customersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchCustomersAdapter.ViewHolder holder = null;
        CustomerListChildItem customer = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_customers_item, null);
            holder = new CustomSearchCustomersAdapter.ViewHolder();
            holder.customerLinear = (LinearLayout) convertView.findViewById(R.id.customers_linear);
            holder.customerName = (TextView) convertView.findViewById(R.id.customerName);
            holder.customerDate = (TextView) convertView.findViewById(R.id.customerDate);
            holder.customerNumber = (TextView) convertView.findViewById(R.id.customerNumber);
            holder.customerAccount = (TextView) convertView.findViewById(R.id.customerAccount);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchCustomersAdapter.ViewHolder) convertView.getTag();
        String name = customer.getCustomer();
        String namePh = (customer.getCustomerPho());
        String date = customer.getDate();
        String time = customer.getTime();
        String company = customer.getCompany();
        String companyPh = customer.getCompanyPho();
        String account = String.valueOf(customer.getAccount());
        String cash = customer.getCash();


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
            holder.customerName.setText(spannableN);
        } else
            holder.customerName.setText(name);
        if (startPosNP != -1) // This should always be true, just a sanity check
        {
            Spannable spannableNP = new SpannableString(namePh);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableNP.setSpan(highlightSpan, startPosNP, endPosNP, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.customerNumber.setText(spannableNP);
        } else
            holder.customerNumber.setText(namePh);
        if (startPosD != -1) // This should always be true, just a sanity check
        {
            Spannable spannableD = new SpannableString(date);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableD.setSpan(highlightSpan, startPosD, endPosD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.customerDate.setText(spannableD + " | " + time);
        } else
            holder.customerDate.setText(date + " | " + time);
        if (startPosT != -1) // This should always be true, just a sanity check
        {
            Spannable spannableT = new SpannableString(time);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableT.setSpan(highlightSpan, startPosT, endPosT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.customerDate.setText(date + " | " + spannableT);
        } else
            holder.customerDate.setText(date + " | " + time);
        holder.customerAccount.setText(cash.charAt(0) + " : " + account + " " + customer.getCurrency());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        customersList.clear();
        if (charText.length() == 0) {
        } else {
            for (CustomerListChildItem customer : filteredCustomersList) {
                if ((customer.getCustomer() + customer.getTime() + customer.getDate() + customer.getCompany() + customer.getCompanyPho() + customer.getCustomerPho()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    customersList.add(customer);
                }
            }
        }
        notifyDataSetChanged();
    }

}

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

public class CustomSearchExpensesAdapter extends ArrayAdapter<ExpenseListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<ExpenseListChildItem> expensesList;
    private ArrayList<ExpenseListChildItem> filteredExpensesList;
    String filteredText;

    public CustomSearchExpensesAdapter(Context context, int resId, ArrayList<ExpenseListChildItem> expenses) {
        super(context, resId);
        this.expensesList = expenses;
        this.filteredExpensesList = expenses;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredExpensesList = new ArrayList<ExpenseListChildItem>();
        this.filteredExpensesList.addAll(expensesList);
        expensesList.clear();
    }

    /*private view holder class*/
    private class ViewHolder {
        LinearLayout expenseLinear;
        TextView expenseName;
        TextView expenseDate;
        TextView expenseConsumer;
        TextView expenseAmount;
    }

    @Override
    public int getCount() {
        return expensesList.size();
    }

    @Override
    public ExpenseListChildItem getItem(int position) {
        return expensesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchExpensesAdapter.ViewHolder holder = null;
        ExpenseListChildItem expense = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_expense_item, null);
            holder = new CustomSearchExpensesAdapter.ViewHolder();
            holder.expenseLinear = (LinearLayout) convertView.findViewById(R.id.expenses_linear);
            holder.expenseName = (TextView) convertView.findViewById(R.id.expenseName);
            holder.expenseDate = (TextView) convertView.findViewById(R.id.expenseDate);
            holder.expenseConsumer = (TextView) convertView.findViewById(R.id.expenseConsumer);
            holder.expenseAmount = (TextView) convertView.findViewById(R.id.expenseAmount);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchExpensesAdapter.ViewHolder) convertView.getTag();
        String name = expense.getExpense();
        String consumer = (expense.getConsumer());
        String date = expense.getDate();
        String time = expense.getTime();


        int startPosN = name.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPosN = startPosN + filteredText.length();
        int startPosC = consumer.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
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
            holder.expenseName.setText(spannableN);
        } else
            holder.expenseName.setText(name);
        if (startPosC != -1) // This should always be true, just a sanity check
        {
            Spannable spannableC = new SpannableString(consumer);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
            spannableC.setSpan(highlightSpan, startPosC, endPosC, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.expenseConsumer.setText(spannableC);
        } else
            holder.expenseConsumer.setText(consumer);
        if (startPosD != -1) // This should always be true, just a sanity check
        {
            Spannable spannableD = new SpannableString(date);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableD.setSpan(highlightSpan, startPosD, endPosD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.expenseDate.setText(spannableD + " | " + time);
        } else
            holder.expenseDate.setText(date + " | " + time);
        if (startPosT != -1) // This should always be true, just a sanity check
        {
            Spannable spannableT = new SpannableString(time);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannableT.setSpan(highlightSpan, startPosT, endPosT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.expenseDate.setText(date + " | " + spannableT);
        } else
            holder.expenseDate.setText(date + " | " + time);
        holder.expenseAmount.setText(expense.getAmount() + " " + expense.getCurrency());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        expensesList.clear();
        if (charText.length() == 0) {
        } else {
            for (ExpenseListChildItem expense : filteredExpensesList) {
                if ((expense.getExpense() + expense.getTime() + expense.getDate() + expense.getCategory() + expense.getConsumer()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    expensesList.add(expense);
                }
            }
        }
        notifyDataSetChanged();
    }

}

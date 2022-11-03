package com.zezoo.accountant;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CustomSearchSubjectsAdapter extends ArrayAdapter<SubjectListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<SubjectListChildItem> subjectsList;
    private ArrayList<SubjectListChildItem> filteredSubjectsList;
    String filteredText;

    public CustomSearchSubjectsAdapter(Context context, int resId, ArrayList<SubjectListChildItem> subjects) {
        super(context, resId);
        this.subjectsList = subjects;
        this.filteredSubjectsList = subjects;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredSubjectsList = new ArrayList<SubjectListChildItem>();
        this.filteredSubjectsList.addAll(subjectsList);
        subjectsList.clear();

    }

    /*private view holder class*/
    private class ViewHolder {
        TextView subjectName;
        TextView subjectAmount;
        TextView subjectUnit;
        TextView subjectLast;
    }

    @Override
    public int getCount() {
        return subjectsList.size();
    }

    @Override
    public SubjectListChildItem getItem(int position) {
        return subjectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomSearchSubjectsAdapter.ViewHolder holder = null;
        SubjectListChildItem subject = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_subjects_item, null);
            holder = new ViewHolder();
            holder.subjectName = (TextView) convertView.findViewById(R.id.subjectName);
            holder.subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
            holder.subjectUnit = (TextView) convertView.findViewById(R.id.subjectCost);
            holder.subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        String nameAndId = String.valueOf(subject.getId()) + " - " + subject.getName();

        int startPos = nameAndId.toLowerCase(Locale.getDefault()).indexOf(filteredText.toLowerCase(Locale.getDefault()));
        int endPos = startPos + filteredText.length();

        if (startPos != -1) // This should always be true, just a sanity check
        {
            Spannable spannable = new SpannableString(nameAndId);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { context.getResources().getColor(R.color.yellow)});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.subjectName.setText(spannable);
        }
        else
            holder.subjectName.setText(nameAndId);
        holder.subjectAmount.setText(String.valueOf(subject.getAmount()));
        holder.subjectUnit.setText(String.valueOf(subject.getCost()) + " " + subject.getCode());
        holder.subjectLast.setText(String.valueOf(subject.getLast()) + " " + subject.getCode());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredText = charText;
        subjectsList.clear();
        if (charText.length() == 0) {
        } else {
            for (SubjectListChildItem subject : filteredSubjectsList) {
                if ((String.valueOf(subject.getId()) + subject.getName().toLowerCase(Locale.getDefault())).contains(charText.toLowerCase(Locale.getDefault()))) {
                    subjectsList.add(subject);
                }
            }
        }
        notifyDataSetChanged();
    }

}

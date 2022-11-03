package com.zezoo.accountant;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomSubjectArrayAdapter extends ArrayAdapter<SubjectListChildItem>{
    Context context;
    LayoutInflater inflater;
    private ArrayList<SubjectListChildItem> subjects;
    int basic;

    public CustomSubjectArrayAdapter(Context context, int resId, ArrayList<SubjectListChildItem> subjects,int type) {
        super(context, 0, subjects);
        this.subjects = subjects;
        this.context = context;
        inflater = LayoutInflater.from(context);
        basic = type;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_subjects_item, parent, false);
        SubjectListChildItem subject = subjects.get(position);
        TextView name = (TextView) listItem.findViewById(R.id.subjectName);
        TextView amount = (TextView) listItem.findViewById(R.id.subjectAmount);
        TextView cost = (TextView) listItem.findViewById(R.id.subjectCost);
        TextView last = (TextView) listItem.findViewById(R.id.subjectLast);
        ImageView img = (ImageView) listItem.findViewById(R.id.groupSubjectsIndicator);
        img.setVisibility(View.GONE);
        amount.setText(String.valueOf(subject.getAmount()));
        if(basic != 1) {
            name.setText(String.valueOf(subject.getId()) + " - " + subject.getName());
            cost.setText(subject.getCost() + " " + subject.getCode());
            last.setText(subject.getLast() + " " + subject.getCode());
        }else{
            name.setText(String.valueOf(subject.getId()));
            cost.setText(subject.getName());
            last.setText(subject.getCost() + " " + subject.getCode());
        }
        return listItem;
    }
}

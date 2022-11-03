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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CustomSearchSubjectsCheckAdapter extends ArrayAdapter<SubjectListChildItem> {

    Context context;
    LayoutInflater inflater;
    private List<SubjectListChildItem> subjectsList;
    private ArrayList<SubjectListChildItem> filteredSubjectsList;
    String filteredText;
    private List<Boolean> groupCheckStates;
    private HashMap<Integer, ArrayList<Boolean>> childCheckStates;
    private List<String> listGroupTitle; // header titles
    private HashMap<String, List<SubjectListChildItem>> listChildData;
    private int basic = 1;

    public CustomSearchSubjectsCheckAdapter(Context context, int resId, ArrayList<SubjectListChildItem> subjects, List<String> listGroupTitle, HashMap<String, List<SubjectListChildItem>> listChildData, HashMap<Integer, ArrayList<Boolean>> childCheckStates, List<Boolean> groupCheckStates,int basic) {
        super(context, resId);
        this.subjectsList = subjects;
        this.filteredSubjectsList = subjects;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filteredSubjectsList = new ArrayList<SubjectListChildItem>();
        this.filteredSubjectsList.addAll(subjectsList);
        subjectsList.clear();
        this.groupCheckStates = groupCheckStates;
        this.childCheckStates = childCheckStates;
        this.listGroupTitle = listGroupTitle;
        this.listChildData = listChildData;
        this.basic = basic;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView subjectName;
        TextView subjectAmount;
        TextView subjectUnit;
        TextView subjectLast;
        AppCompatCheckBox subjectCheck;
        ImageView subjectImg;
        SeekBar seekBarA;
        SeekBar seekBarU;
        LinearLayout linearSeeks;
        TextView txtAmountS;
        TextView txtUnitS;
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
        CustomSearchSubjectsCheckAdapter.ViewHolder holder = null;
        final SubjectListChildItem subject = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_subjects_item, null);
            holder = new CustomSearchSubjectsCheckAdapter.ViewHolder();
            holder.subjectName = (TextView) convertView.findViewById(R.id.subjectName);
            holder.subjectAmount = (TextView) convertView.findViewById(R.id.subjectAmount);
            holder.subjectUnit = (TextView) convertView.findViewById(R.id.subjectCost);
            holder.subjectLast = (TextView) convertView.findViewById(R.id.subjectLast);
            holder.subjectCheck = (AppCompatCheckBox)convertView.findViewById(R.id.subjectCheck);
            holder.subjectImg = (ImageView) convertView.findViewById(R.id.groupSubjectsIndicator);
            holder.seekBarA = (SeekBar) convertView.findViewById(R.id.seekBar2);
            holder.seekBarU = (SeekBar) convertView.findViewById(R.id.seekBar1);
            holder.txtAmountS = (TextView) convertView.findViewById(R.id.txt_amount);
            holder.txtUnitS = (TextView) convertView.findViewById(R.id.txt_unit);
            holder.linearSeeks = (LinearLayout) convertView.findViewById(R.id.linearSeeks);
            convertView.setTag(holder);
        } else
            holder = (CustomSearchSubjectsCheckAdapter.ViewHolder) convertView.getTag();
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
        holder.subjectImg.setVisibility(View.GONE);
        holder.subjectCheck.setVisibility(View.VISIBLE);
        int group = 0;
        int child = 0;
        for(int i = 0;i <listGroupTitle.size();i++){
            for(int j = 0;j< listChildData.get(listGroupTitle.get(i)).size();j++){
                if( listChildData.get(listGroupTitle.get(i)).get(j).getColumn() == subject.getColumn()) {
                    group = i;
                    child = j;
                }
            }
        }
        holder.subjectCheck.setChecked(childCheckStates.get(group).get(child));
            holder.linearSeeks.setVisibility(View.VISIBLE);
            holder.txtUnitS.setVisibility(View.VISIBLE);
            holder.txtAmountS.setVisibility(View.VISIBLE);
            if (basic == 1) {
                holder.seekBarA.setMax(100);
                holder.txtAmountS.setText("0");
                if (subject.getCost() >= 1 && subject.getCost() < 100) {
                    holder.seekBarU.setMax(500);
                    holder.seekBarU.setProgress((int) subject.getCost());
                } else if (subject.getCost() >= 100 && subject.getCost() <= 1000) {
                    holder.seekBarU.setMax(5000);
                    holder.seekBarU.setProgress((int) subject.getCost());
                } else if (subject.getCost() > 1000 && subject.getCost() <= 10000) {
                    holder.seekBarU.setMax(50000);
                    holder.seekBarU.setProgress((int) subject.getCost());
                } else if (subject.getCost() > 10000 && subject.getCost() <= 100000) {
                    holder.seekBarU.setMax(500000);
                    holder.seekBarU.setProgress((int) subject.getCost());
                } else {
                    holder.seekBarU.setMax(5000000);
                    holder.seekBarU.setProgress((int) subject.getCost());
                }
            } else {
                if (subject.getAmountLock() == 0) {
                    holder.seekBarA.setMax((int) subject.getAmount());
                    holder.txtAmountS.setText("0");
                } else {
                    holder.seekBarA.setMax(100);
                    holder.seekBarA.setProgress((int) subject.getAmountLock());
                }
                if (subject.getLock() == 0) {
                    if (subject.getCost() >= 1 && subject.getCost() < 100) {
                        holder.seekBarU.setMax(500);
                        holder.seekBarU.setProgress((int) subject.getCost());
                    } else if (subject.getCost() >= 100 && subject.getCost() <= 1000) {
                        holder.seekBarU.setMax(5000);
                        holder.seekBarU.setProgress((int) subject.getCost());
                    } else if (subject.getCost() > 1000 && subject.getCost() <= 10000) {
                        holder.seekBarU.setMax(50000);
                        holder.seekBarU.setProgress((int) subject.getCost());
                    } else if (subject.getCost() > 10000 && subject.getCost() <= 100000) {
                        holder.seekBarU.setMax(500000);
                        holder.seekBarU.setProgress((int) subject.getCost());
                    } else {
                        holder.seekBarU.setMax(5000000);
                        holder.seekBarU.setProgress((int) subject.getCost());
                    }
                }else {
                    if (subject.getCost() >= 1 && subject.getCost() < 100) {
                        holder.seekBarU.setMax(500);
                        holder.seekBarU.setProgress((int) subject.getLock());
                    } else if (subject.getCost() >= 100 && subject.getCost() <= 1000) {
                        holder.seekBarU.setMax(5000);
                        holder.seekBarU.setProgress((int) subject.getLock());
                    } else if (subject.getCost() > 1000 && subject.getCost() <= 10000) {
                        holder.seekBarU.setMax(50000);
                        holder.seekBarU.setProgress((int) subject.getLock());
                    } else if (subject.getCost() > 10000 && subject.getCost() <= 100000) {
                        holder.seekBarU.setMax(500000);
                        holder.seekBarU.setProgress((int) subject.getLock());
                    } else {
                        holder.seekBarU.setMax(5000000);
                        holder.seekBarU.setProgress((int) subject.getLock());
                    }
                }
            }
        final ViewHolder finalHolder = holder;
        holder.seekBarA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    finalHolder.txtAmountS.setText(String.valueOf(seekBar.getProgress()));
                    if(basic != 1&& subject.getAmountLock() != 0 && seekBar.getProgress() < (int) subject.getAmountLock()){
                        seekBar.setProgress((int) subject.getAmountLock());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            holder.seekBarU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    finalHolder.txtUnitS.setText(String.valueOf(seekBar.getProgress()));
                    if(basic != 1&& subject.getLock() != 0  && seekBar.getProgress() < (int) subject.getLock()){
                        seekBar.setProgress((int) subject.getLock());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
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

package com.zezoo.accountant;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<ListItem> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<ListItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    public class ViewHolder {
        TextView bilCustomerName;
        TextView bilDate;
        TextView bilTotal;
        TextView bilName;
        TextView bilColor;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListItem listItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_bills_item, null);
            holder = new ViewHolder();
            holder.bilCustomerName = (TextView) convertView.findViewById(R.id.billSupCus);
            holder.bilDate = (TextView) convertView.findViewById(R.id.billDate);
            holder.bilTotal = (TextView) convertView.findViewById(R.id.billFinal);
            holder.bilName = (TextView) convertView.findViewById(R.id.billName);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.bilCustomerName.setText(listItem.getCustomerName());
        holder.bilDate.setText(listItem.getDate());
        holder.bilTotal.setText(listItem.getTotal());
        holder.bilName.setText(listItem.getName());
        holder.bilColor.setText(listItem.getBasic());
        holder.bilColor.setBackground(translateCodeColorDrawable(listItem.getColor()));
        ((FrameLayout)convertView).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,150 ));
        return convertView;
    }
    Drawable translateCodeColorDrawable(int colorNum){
        Drawable dr;
        Resources resources = context.getResources();
        if(colorNum == 1){
            dr = resources.getDrawable(R.drawable.cir_blue);
        } else if(colorNum == 2){
            dr = resources.getDrawable(R.drawable.cir_green);
        } else {
            dr = resources.getDrawable(R.drawable.cir_purple);
        }
        return dr;
    }
}

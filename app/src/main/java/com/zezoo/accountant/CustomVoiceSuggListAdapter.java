package com.zezoo.accountant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomVoiceSuggListAdapter extends ArrayAdapter<VoiceSuggestionListItem> {
    public CustomVoiceSuggListAdapter(Context context, ArrayList<VoiceSuggestionListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VoiceSuggestionListItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.voice_sugg_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvNum = (TextView) convertView.findViewById(R.id.num);
        TextView tvBody = (TextView) convertView.findViewById(R.id.body);
        // Populate the data into the template view using the data object
        tvNum.setText(String.valueOf(item.num));
        tvBody.setText(item.body);
        // Return the completed view to render on screen
        return convertView;
    }
}

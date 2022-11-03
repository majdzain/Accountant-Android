package com.zezoo.accountant;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CustomValueFormatter implements IValueFormatter {
    String currency = "SYP";

    CustomValueFormatter(String currency) {
        this.currency = currency;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return String.valueOf((int) value) + " " + currency;
    }
}

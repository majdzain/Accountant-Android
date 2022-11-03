package com.zezoo.accountant;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class CurrencyAxisValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;
    private String currency;

    public CurrencyAxisValueFormatter(String currency) {
        mFormat = new DecimalFormat("###,###,###,##0.0");
        this.currency = currency;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " " + currency;
    }
}

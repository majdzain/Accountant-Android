package com.zezoo.accountant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CheckableLinearLayout extends LinearLayout implements Checkable{

    private boolean isChecked;
    private ArrayList<Checkable> checkableViews;

    public CheckableLinearLayout(Context context) {
        super(context);
    }


    @Override
    public void setChecked(boolean checked) {
        this.isChecked = checked;
        for (Checkable view : checkableViews) {
            view.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
        for (Checkable view : checkableViews)       {
            view.toggle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i=0; i<this.getChildCount(); i++) {
            findCheckableChild(this.getChildAt(i));
        }
    }

    private void findCheckableChild(View child) {
        if (child instanceof Checkable) {
            checkableViews.add((Checkable)child);
        }

        if (child instanceof ViewGroup) {
            for (int i=0; i<((ViewGroup)child).getChildCount(); i++) {
                findCheckableChild(((ViewGroup) child).getChildAt(i));
            }
        }
    }
}

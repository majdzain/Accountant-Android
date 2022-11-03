package com.zezoo.accountant;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CheckBox;

import androidx.appcompat.widget.AppCompatCheckBox;

public class InertCheckBox extends AppCompatCheckBox {

    public InertCheckBox(Context context) {
        super(context);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Returns false
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Returns false
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Returns false
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        //Returns false
        return false;
    }
}

package com.dip.dailyexpenses;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.TextView;

import java.util.Calendar;

public class DatePicker
{
        DatePickerDialog datePickerDialog;
        Context context;

        public DatePicker(){}
        public void DatePicker(Context cn, final TextView tv) {
            context=cn;
            final TextView tview=tv;
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(context,
                    new DatePickerDialog.OnDateSetListener() {
                @Override
                        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                           tview.setText((day + "-" + (month + 1) + "-" + year));
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(2020);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
}

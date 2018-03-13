package com.dn.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    TextView tvd, tvt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        tvd =  findViewById(R.id.textDate);
        tvt =  findViewById(R.id.textTime);
        setUpTimePicker();
        setUpDatePicker();
    }




    private void setUpTimePicker(){
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                tvt.setText("Time: " + hour + " : " + minute);
            }
        });
    }



    private void setUpDatePicker(){
        Calendar c = Calendar.getInstance();
        datePicker.init(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener(){

                     @Override
                     public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                         Calendar calendar = Calendar.getInstance();
                         calendar.set(year, month, dayOfMonth);
                         tvd.setText("Date: " + DateFormat.format("dd-MM-yyyy", calendar.getTime()));
                     }
                 });
    }


    /**
     *
     * Use for open TimePickerFragment
     * */
    public void openTimePicker(View v){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), "TimePickerDialog");
    }


    /**
     *
     * Use for open DatePickerFragment
     * */
    public void openDatePicker(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private TextView textView;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            textView = getActivity().findViewById(R.id.textTime);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textView.setText("Time: " + hourOfDay + " : " + minute);
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            textView.setText("Cancel");
        }


    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private TextView textView;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            textView = getActivity().findViewById(R.id.textDate);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            textView.setText("Date: " + DateFormat.format("dd-MM-yyyy", calendar.getTime()));
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            textView.setText("Cancel");
        }

    }
}

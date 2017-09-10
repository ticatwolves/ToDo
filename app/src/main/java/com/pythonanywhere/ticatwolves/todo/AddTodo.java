package com.pythonanywhere.ticatwolves.todo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddTodo extends AppCompatActivity {


    //Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime,task;
    private int mYear, mMonth, mDay, mHour, mMinute;
    FloatingActionButton add;
    String time, date, tasks;
    TasksDB tasksDB;

    AlarmManager alarmManager;

    public int day,month,yearr,hour,mins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //btnDatePicker=(Button)findViewById(R.id.btn_date);
        //btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        task = (EditText)findViewById(R.id.task);

        //txtDate.setEnabled(false);
        //txtTime.setEnabled(false);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datebtn();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timebtn();
            }
        });

        add = (FloatingActionButton)findViewById(R.id.addtask);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks = task.getText().toString();
                time = txtTime.getText().toString();
                date = txtDate.getText().toString();

                if(tasks.equals("")) {
                    Toast.makeText(getApplicationContext(),"Add some task",Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent i1 = new Intent();
                    i1.setAction("com.pythonanywhere.ticatwolves.receiver.Message");
                    i1.addCategory("android.intent.category.DEFAULT");
                    PendingIntent pd = PendingIntent.getBroadcast(getApplicationContext(),0,i1,0);
                    Long tslong = System.currentTimeMillis();
                    long ts = tslong;

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(yearr,month, day, hour, mins, 0);
                    long startTime = calendar.getTimeInMillis();

                    if(!((startTime-ts)<5)){
                        tasksDB.insertdata(tasks,date,time);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),(startTime-ts),pd);
                        finish();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Enter Valid Date and Time",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tasksDB = new TasksDB(this);

    }

    public void datebtn(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        day = dayOfMonth;
                        month = monthOfYear;
                        yearr = year;


                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void timebtn(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        hour = hourOfDay;
                        mins = minute;
                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

}

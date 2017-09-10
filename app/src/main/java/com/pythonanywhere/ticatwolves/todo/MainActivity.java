package com.pythonanywhere.ticatwolves.todo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    Boolean cont = true;
    RecyclerView taskview;
    TasksDB tasksDB;
    TasksAdaptor tasksAdaptor;
    List<String> tasks,time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        tasksDB = new TasksDB(this);

        if(tasksDB.getCount()>0) {
            setContentView(R.layout.activity_main_content);
            cont = false;
            taskview = (RecyclerView)findViewById(R.id.tasksview);
            tasks = tasksDB.getdata().get(0);
            date = tasksDB.getdata().get(1);
            time = tasksDB.getdata().get(2);

            tasksAdaptor = new TasksAdaptor(this,tasks,time,date);
            taskview.setAdapter(tasksAdaptor);
            taskview.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            setContentView(R.layout.activity_main);
        }
        add = (FloatingActionButton)findViewById(R.id.addactivity);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,AddTodo.class);
                startActivity(in);
            }
        });
    }
}

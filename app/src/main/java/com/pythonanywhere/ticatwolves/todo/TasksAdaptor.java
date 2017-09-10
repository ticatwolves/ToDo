package com.pythonanywhere.ticatwolves.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ticat on 12/8/17.
 */

public class TasksAdaptor extends RecyclerView.Adapter<TasksAdaptor.Myholder> {

    TasksDB tasksDB;

    Context ctx;
    List<String> data;
    List<String> dtime;
    List<String> ddate;
    public TasksAdaptor(Context ct, List<String> task,List<String> date,List<String> time){
        ctx = ct;
        data = task;
        dtime = time;
        ddate = date;
        tasksDB = new TasksDB(ctx);
    }

    @Override
    public TasksAdaptor.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myinflat = LayoutInflater.from(ctx);
        View myOwnView = myinflat.inflate(R.layout.tasksskull,parent,false);
        return new Myholder(myOwnView);
    }

    @Override
    public void onBindViewHolder(TasksAdaptor.Myholder holder, final int position) {
        holder.ta.setText((String)data.get(position));
        holder.ic.setText((String)data.get(position).substring(0,1));
        holder.dtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx,"Delete",Toast.LENGTH_SHORT).show();
                tasksDB.deleteTask((String)data.get(position));
                String item = data.get(position);
                data.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,data.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class Myholder extends RecyclerView.ViewHolder {
        TextView ta,ic;
        ImageView dtask;
        public Myholder(View itemView) {
            super(itemView);
            ta = (TextView)itemView.findViewById(R.id.task);
            ic = (TextView)itemView.findViewById(R.id.timage);
            dtask = (ImageView)itemView.findViewById(R.id.deletetask);
        }
    }
}

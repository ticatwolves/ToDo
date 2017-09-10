package com.pythonanywhere.ticatwolves.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ticat Wolves on 6/16/2017.
 */

public class TasksDB extends SQLiteOpenHelper {

    static final private String DB_Name = "ToDo";
    static final private String DB_Table = "Tasks";
    static final private int DB_Version = 1;
    Context ctx;
    SQLiteDatabase mydb;

    public TasksDB(Context ct) {
        super(ct, DB_Name, null, DB_Version);
        ctx=ct;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+DB_Table+" (id integer primary key autoincrement,task,date,time)");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DB_Table);
        onCreate(sqLiteDatabase);
    }

    public void insertdata(String s1,String s2,String s3){
        mydb = getWritableDatabase();
        mydb.execSQL("insert into "+DB_Table+" (task,date,time) values('"+s1+"','"+s2+"','"+s3+"')");
        Toast.makeText(ctx,"Inserted",Toast.LENGTH_SHORT).show();
    }

    public void deleteTask(String time){

        mydb = getWritableDatabase();
        mydb.execSQL("delete from "+DB_Table+" where task = '"+time+"'");
        mydb.close();
        Log.i("KKKKKKK","Deleted");
    }

    public List<List<String>> getdata(){
        mydb=getReadableDatabase();
        Cursor cr = mydb.rawQuery("Select * from "+DB_Table,null);
        StringBuilder str = new StringBuilder();

        Log.i("Fuck",String.valueOf(cr.getCount()));

        //List<String> mydata = new ArrayList<>(Arrays.asList(data));

        List<String> task = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> time = new ArrayList<>();
        List<List<String>> result = new ArrayList<List<String>>();
        while (cr.moveToNext()){

            String s1 = cr.getString(1);
            String s2 = cr.getString(2);
            String s3 = cr.getString(3);
            str.append(s1+"   "+s2+"  "+s3+"\n");
            task.add(s1);
            date.add(s2);
            time.add(s3);
        }
        result.add(task);
        result.add(date);
        result.add(time);
        return result;
//        Toast.makeText(ctx,str,Toast.LENGTH_SHORT).show();
    }
    public int getCount(){
        try{
            mydb=getReadableDatabase();
            Cursor cr = mydb.rawQuery("Select * from "+DB_Table,null);
            return cr.getCount();
        }
        catch (Exception e){
            return 0;
        }
    }
}

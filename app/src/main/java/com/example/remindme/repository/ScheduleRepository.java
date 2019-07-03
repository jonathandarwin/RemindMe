package com.example.remindme.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.remindme.model.Schedule;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository extends SQLiteOpenHelper {

    public ScheduleRepository(Context context){
        super(context, "RemindMeDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE msSchedule(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time TEXT," +
                "description TEXT," +
                "isOn INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Schedule getScheduleById(int id){
        SQLiteDatabase db = getWritableDatabase();
        Schedule schedule = new Schedule();
        try{
            String query = "SELECT * FROM msSchedule WHERE id = " + id;
            Cursor cursor = db.rawQuery(query, null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                schedule.setId(id);
                schedule.setTime(cursor.getString(cursor.getColumnIndex("time")));
                schedule.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                schedule.setIsOn(cursor.getInt(cursor.getColumnIndex("isOn")));
                break;
            }
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
            db.close();
            schedule = new Schedule();
        }
        return schedule;
    }

    public List<Schedule> getListSchedule(){
        SQLiteDatabase db = getWritableDatabase();
        List<Schedule> listSchedule = new ArrayList<>();
        try{
            String query = "SELECT * FROM msSchedule ORDER BY time ASC";
            Cursor cursor = db.rawQuery(query, null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                listSchedule.add(new Schedule()
                        .setId(cursor.getInt(cursor.getColumnIndex("id")))
                        .setTime(cursor.getString(cursor.getColumnIndex("time")))
                        .setDescription(cursor.getString(cursor.getColumnIndex("description")))
                        .setIsOn(cursor.getInt(cursor.getColumnIndex("isOn"))));
                cursor.moveToNext();
            }
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
            db.close();
            listSchedule = new ArrayList<>();
        }
        return listSchedule;
    }

    public long insertSchedule(Schedule schedule){
        SQLiteDatabase db = getWritableDatabase();
        long id = -1;
        try{
            ContentValues value = new ContentValues();
            value.put("time", schedule.getTime());
            value.put("description", schedule.getDescription());
            value.put("isOn", 1);

            id = db.insert("msSchedule", null, value);
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
            id = -1;
            db.close();
        }
        return id;
    }

    public boolean deleteSchedule(int id){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String query = "DELETE FROM msSchedule WHERE id = " + id;
            db.execSQL(query);
            db.close();
        }
        catch(Exception e){
            e.printStackTrace();
            db.close();
            return false;
        }
        return true;
    }

    public boolean updateSchedule(Schedule schedule){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String query = "UPDATE msSchedule SET " +
                    "time = \"" + schedule.getTime() + "\"," +
                    "description = \"" +  schedule.getDescription()+ "\"," +
                    "isOn = " + schedule.getIsOn() +
                    " WHERE id = " + schedule.getId();

            db.execSQL(query);
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
            db.close();
            return false;
        }
        return true;
    }
}

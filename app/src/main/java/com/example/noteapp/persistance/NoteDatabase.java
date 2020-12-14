package com.example.noteapp.persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.noteapp.async.PopulateDbAsyncTask;
import com.example.noteapp.model.Note;

@Database(entities={Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
       instance= Room.databaseBuilder(context.getApplicationContext(),
               NoteDatabase.class,"note_database")
               .addCallback(roomCallBack)
               .fallbackToDestructiveMigration()
               .build();
        }
        return  instance;
    }
    public  abstract NoteDao noteDao();
private static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback(){
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new PopulateDbAsyncTask(instance).execute();
    }
};
}

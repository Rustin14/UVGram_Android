package com.example.uvgram.Connection;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.uvgram.Models.File;

@androidx.room.Database(entities = {File.class}, version = 1)
public abstract class UVGramDatabase extends RoomDatabase {

    private static UVGramDatabase instance;

    public abstract IFileDAO fileDAO();

    public static synchronized UVGramDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UVGramDatabase.class, "uvgram_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private IFileDAO fileDAO;

        private PopulateDbAsyncTask(UVGramDatabase db) {
            fileDAO = db.fileDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fileDAO.insertFile(new File("1"));
            fileDAO.insertFile(new File("2"));
            fileDAO.insertFile(new File("2"));
            return null;
        }

    }

}

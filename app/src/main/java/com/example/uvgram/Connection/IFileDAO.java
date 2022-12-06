package com.example.uvgram.Connection;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uvgram.Models.File;

import java.util.List;

@Dao
public interface IFileDAO {

    @Insert
    void insertFile(File file);

    @Update
    void updateFile(File file);

    @Delete
    void deleteFile(File file);

    @Query("DELETE FROM file")
    void deleteAllFiles();

    @Query("SELECT * FROM file ORDER BY id DESC")
    LiveData<List<File>> getAllFiles();

}

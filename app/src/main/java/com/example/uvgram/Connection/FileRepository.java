package com.example.uvgram.Connection;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.uvgram.Models.File;

import java.util.List;

public class FileRepository {

    private IFileDAO fileDAO;
    private LiveData<List<File>> allFiles;

    public FileRepository(Application application) {
        UVGramDatabase database = UVGramDatabase.getInstance(application);
        fileDAO = database.fileDAO();
        allFiles = fileDAO.getAllFiles();
    }

    public void insertFile(File file) {
        new InsertFileAsyncTask(fileDAO).execute(file);
    }

    public void updateFile(File file) {
        new UpdateFileAsyncTask(fileDAO).execute(file);
    }

    public void delete(File file) {
        new DeleteFileAsyncTask(fileDAO).execute(file);
    }

    public void deleteAllFiles() {
        new DeleteAllFilesAsyncTask(fileDAO).execute();
    }

    public LiveData<List<File>> getAllFiles() {
        return allFiles;
    }

    private static class InsertFileAsyncTask extends AsyncTask<File, Void, Void> {
        private IFileDAO fileDAO;

        private InsertFileAsyncTask(IFileDAO fileDAO) {
            this.fileDAO = fileDAO;
        }

        @Override
        protected Void doInBackground(File... files) {
            fileDAO.insertFile(files[0]);
            return null;
        }
    }

    private static class UpdateFileAsyncTask extends AsyncTask<File, Void, Void> {
        private IFileDAO fileDAO;

        private UpdateFileAsyncTask(IFileDAO fileDAO) {
            this.fileDAO = fileDAO;
        }

        @Override
        protected Void doInBackground(File... files) {
            fileDAO.updateFile(files[0]);
            return null;
        }
    }

    private static class DeleteFileAsyncTask extends AsyncTask<File, Void, Void> {
        private IFileDAO fileDAO;

        private DeleteFileAsyncTask(IFileDAO fileDAO) {
            this.fileDAO = fileDAO;
        }

        @Override
        protected Void doInBackground(File... files) {
            fileDAO.deleteFile(files[0]);
            return null;
        }
    }

    private static class DeleteAllFilesAsyncTask extends AsyncTask<Void, Void, Void> {
        private IFileDAO fileDAO;

        private DeleteAllFilesAsyncTask(IFileDAO fileDAO) {
            this.fileDAO = fileDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fileDAO.deleteAllFiles();
            return null;
        }
    }


}

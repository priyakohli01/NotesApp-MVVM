package com.example.notes.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.db.NoteDao;
import com.example.notes.model.Note;
import com.example.notes.db.NoteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application) {
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        executor.execute(() -> noteDao.insert(note));
    }

    public void delete(Note note) {
        executor.execute(() -> noteDao.delete(note));
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}

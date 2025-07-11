package com.example.notes.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes.R;
import com.example.notes.model.Note;
import com.example.notes.viewmodel.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity {
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.editTitle);
        EditText descInput = findViewById(R.id.editDescription);
        Button saveButton = findViewById(R.id.buttonSave);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String desc = descInput.getText().toString();
            if (!title.isEmpty() && !desc.isEmpty()) {
                viewModel.insert(new Note(title, desc));
                finish();
            }
        });
    }
}

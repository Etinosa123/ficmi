package com.hfad.myficmiapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.fragments.NotesFragment;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();

        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){
            editText.setText(NotesFragment.notes.get(noteId));
        }else {
            NotesFragment.notes.add(" ");
            noteId = NotesFragment.notes.size() - 1;
            NotesFragment.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NotesFragment.notes.set(noteId,String.valueOf(s));
                NotesFragment.arrayAdapter.notifyDataSetChanged();
                SharedPreferences preferences = getApplicationContext()
                        .getSharedPreferences("com.example.note", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(NotesFragment.notes);
                preferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
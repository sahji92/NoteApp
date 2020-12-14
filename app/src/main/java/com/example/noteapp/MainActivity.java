package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.noteapp.adapters.NoteAdapter;
import com.example.noteapp.model.Note;

import java.time.DayOfWeek;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private NoteViewModel noteViewModel;
private RecyclerView recyclerView;
private NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recyclerview
                noteAdapter.setNotes(notes);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        noteAdapter=new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
    }
}
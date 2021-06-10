package com.hfad.myficmiapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.activities.NoteEditorActivity;

import java.util.ArrayList;
import java.util.HashSet;


public class NotesFragment extends Fragment {

    public static ArrayList<String> notes = new ArrayList<>();
    public static ArrayAdapter arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        ListView listView = root.findViewById(R.id.listnote);
        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) preferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Create your first note");
        } else {
            notes = new ArrayList(set);
        }
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemToDelete = position;

                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Warning")
                        .setMessage("Do you want to delete this note")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getActivity()
                                        .getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet(NotesFragment.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });
        return root;
    }



    //creating menu option to add new notes
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate the menu, this adds item to the app bar
        inflater.inflate(R.menu.main_menu, menu);//menu in the second arg is a menu object thats a java representation of the menu resource file
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notes:
                Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
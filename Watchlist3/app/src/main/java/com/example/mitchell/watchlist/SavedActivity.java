package com.example.mitchell.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SavedActivity extends AppCompatActivity {
    ArrayList<String> savedMovies = new ArrayList<String>();
    ListView lvSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        lvSaved = (ListView) findViewById(R.id.lvSaved);
//        onclick event on the listView
        lvSaved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moviePicked = "You selected: "
                        + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(SavedActivity.this, moviePicked, Toast.LENGTH_SHORT).show();

//                makes intent with the the selected title
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("choice", String.valueOf(parent.getItemAtPosition(position)));
                startActivity(intent);
                finish();
            }
        });

        makeSavedAdapter();
    }
// save and make adapter
    public void makeSavedAdapter() {
        SharedPreferences spSaved = getSharedPreferences("Saved", Context.MODE_PRIVATE);
        Set<String> moviesSet = new HashSet<String>(spSaved.getStringSet("Saved", new HashSet<String>()));
        savedMovies.addAll(moviesSet);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, savedMovies);
        assert lvSaved != null;
        lvSaved.setAdapter(arrayAdapter);
    }
}

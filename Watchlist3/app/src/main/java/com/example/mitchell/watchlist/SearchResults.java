package com.example.mitchell.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchResults extends AppCompatActivity {
    TextView tvResult;
    ListView lvItems;
    ArrayList<ArrayList<String>> movieArray;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
//        get intent and extras
        lvItems = (ListView) findViewById(R.id.listViewID);
        Bundle extras = getIntent().getExtras();
        titles = (ArrayList<String>) extras.getSerializable("data");

        makeMovieAdapter();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moviePicked = "You selected: "
                        + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(SearchResults.this, moviePicked, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("choice", String.valueOf(parent.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
    }
//    make adapter
    public void makeMovieAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, titles);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}

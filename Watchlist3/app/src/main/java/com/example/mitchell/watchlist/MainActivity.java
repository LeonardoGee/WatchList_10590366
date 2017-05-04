package com.example.mitchell.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovie = (EditText) findViewById(R.id.etMovie);
        assert etMovie != null;
    }

    public void movieSearch(View view) {
        String movieSearch = etMovie.getText().toString();
//        checks if the editText contains something
        if (!movieSearch.isEmpty()) {
//            // when searching the progress bar becomes visible
            toggleProgressBar();
//            makes a asyncTask and clears editText
            MovieAsyncTask asyncTask = new MovieAsyncTask(this);
            asyncTask.execute(movieSearch);
            etMovie.getText().clear();
        }
        else {
            Toast.makeText(MainActivity.this, "Enter something to search",
                    Toast.LENGTH_SHORT).show();
        }
    }

//    makes intent
    public void movieStartIntent(ArrayList<String> movieTitles){
        Intent dataIntent = new Intent(this, SearchResults.class);
        dataIntent.putExtra("data", movieTitles);
        this.startActivity(dataIntent);
    }

    public void goToSaved(View view) {
        Intent savedIntent = new Intent(this, SavedActivity.class);
        this.startActivity(savedIntent);
    }

//    toggle progress bar
    public void toggleProgressBar (){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.searchProgressBar);

        if (progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            }
        }
    }




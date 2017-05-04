package com.example.mitchell.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

public class DetailsActivity extends AppCompatActivity {

    String title;
    Button btSave;
    Button btDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
//        get intent
        Bundle extras = getIntent().getExtras();
        title = extras.getString("choice");
        tvTitle.setText(title);


        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);;
        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
//        checks of the movie titel already is saved and adapt the visibility of the save and delete button
        if (s.contains(title)) {
            btDelete = (Button) findViewById(R.id.btDelete);
            btDelete.setVisibility(View.VISIBLE);
        }
        else {
            btSave = (Button) findViewById(R.id.btSave);
            btSave.setVisibility(View.VISIBLE);
        }
    }
// saving the movie titles in sharedPreferences
    public void saveTitle(View view) {
        SharedPreferences.Editor sPEditor = getSharedPreferences("Saved", Context.MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);

        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
        s.add(title);

        sPEditor.putStringSet("Saved", s);
        sPEditor.apply();
        Toast.makeText(DetailsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
// deleting the movie title
    public void deleteTitle(View view) {
        SharedPreferences.Editor sPEditor = getSharedPreferences("Saved", Context.MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);
        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
        s.remove(title);

        sPEditor.putStringSet("Saved", s);
        sPEditor.apply();
        Toast.makeText(DetailsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        Log.d("deleted", "now change visibility");
        finish();
        goToSaved(view);
    }

    public void goToSaved(View view) {
        Intent savedIntent = new Intent(this, SavedActivity.class);
        this.startActivity(savedIntent);
    }
}
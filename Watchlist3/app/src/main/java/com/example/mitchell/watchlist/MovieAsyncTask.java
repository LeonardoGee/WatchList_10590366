package com.example.mitchell.watchlist;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class MovieAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    public MovieAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return HttpRequestHelper.downloadFromServer(params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        JSONArray resultsObj = null;

        ArrayList<String> titles = new ArrayList<String>();


        try {
            JSONObject movieStreamObj = new JSONObject(result);
            resultsObj = movieStreamObj.getJSONArray("Search");

            for (int i = 0; i < resultsObj.length(); i++) {
                try {
                    titles.add(resultsObj.getJSONObject(i).getString("Title"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mainAct.toggleProgressBar();
            this.mainAct.movieStartIntent(titles);

        }
        catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Movie Was Not Found" ,Toast.LENGTH_SHORT).show();
            mainAct.toggleProgressBar();
            }
    }
}
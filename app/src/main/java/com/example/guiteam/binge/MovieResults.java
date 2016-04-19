package com.example.guiteam.binge;
import com.google.gson.Gson;
/**
 * Created by Brandi on 4/18/2016.
 */
public class MovieResults {
    String[] results = new String[50];
    int total_results;
    String development_api_key;
    public MovieObject[] objects;

    public void addMovies() throws Exception{
        for(int i=0; i<total_results; i++){
            Gson gson = new Gson();
            objects = new MovieObject[total_results];
            objects[i] = gson.fromJson(results[i], MovieObject.class);
        }
        return;
    }
}

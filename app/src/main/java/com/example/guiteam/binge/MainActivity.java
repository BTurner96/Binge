package com.example.guiteam.binge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import com.example.guiteam.binge.MovieResults;
import com.example.guiteam.binge.MovieObject;
import com.example.guiteam.binge.SearchHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView editText = (TextView)findViewById(R.id.textView);
        editText.setText("New text");
    }

    public void changeActivity(View view){
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }
    /*
    *The search method takes a search request and obtains the results in a list of movie objects
    * displaying all the movies that match the search criteria.
     */
    public void search(View view) throws Exception{
        SearchHandler request = new SearchHandler();
        MovieObject[] result = new MovieObject[100];
        result = request.movieSearch("Tangled");
        TextView editText = (TextView)findViewById(R.id.textView);
        String newText = result[0].title;
        editText.setText(newText);
    }
}

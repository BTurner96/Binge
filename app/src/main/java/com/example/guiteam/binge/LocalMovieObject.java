package com.example.guiteam.binge;

/**
 * Created by Samantha on 4/21/2016.
 */
public class LocalMovieObject {
    String title;
    String genre;
    int year;
    public LocalMovieObject(String title, String genre, int year)
    {
        this.title= title;
        this.genre= genre;
        this.year= year;
    }

    public boolean matchTitle(String title)
    {
        return title.toLowerCase().indexOf(title.toLowerCase())>=0;
    }

    public String toString()
    {
        return String.format("%-35s %-12s %4d",title,genre,year);
    }
}

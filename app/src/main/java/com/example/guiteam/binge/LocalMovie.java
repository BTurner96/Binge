package com.example.guiteam.binge;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
/**
 * Created by Samantha on 4/21/2016.
 *
 * Reads movie objects from local text file and searches objects by title.
 */
public class LocalMovie{
    LocalMovieObject [] movies; //array of movie objects
    String movieList="tinymovielist.txt"; //file movies are stored in
    int max=10000; //max amount of movies
    int n; //number of movies

    /*
    * Reads movie file and puts the movies into movie object.
     */
    public void readLocalMovie()
    {
        Scanner f;
        try
        {
            this.movieList = movieList;
            f = new Scanner(new File(movieList));
            movies = new LocalMovieObject[max];

            n=0;
            while(f.hasNextLine())
            {
                movies[n]=new LocalMovieObject(f.nextLine(), f.nextLine(), f.nextInt());
                f.nextLine();
                n++;
            }
            f.close();
        }
        catch (Exception e)
        {
            System.err.println("Invalid data file: "+movieList);
            System.err.println(e);
        }
    }

<<<<<<< HEAD
    public LocalMovieObject[] searchTitle(String search) throws Exception {


        LocalMovieObject[] result = new LocalMovieObject[1000];
        int j = 0;
        for (int i = 0; i < n; i++){
            if (movies[i].matchTitle(search)) {
                result[j].title = movies[i].title;
                result[j].genre = movies[i].genre;
                result[j].year = movies[i].year;
                j++;
            }
        }
        LocalMovieObject[] finalResults = new LocalMovieObject[result.length];
        for(int x=0; x<result.length; x++){
            
        }
=======
    /*
    *Search movies by title. Case-Insensitive.
     */
    public LocalMovieObject[] searchTitle(String search) throws Exception
    {
        LocalMovieObject [] result = new LocalMovieObject[1000];

        int j=0;
        for (int i=0; i<n; i++)
            if(movies[i].matchTitle(search))
                result[j++]=movies[i];
>>>>>>> 1d97d2f6aa5e46f76e7ab5bf533e35b06cd5a843

        return result;
    }
}
